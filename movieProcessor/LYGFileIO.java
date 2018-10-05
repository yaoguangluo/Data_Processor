package movieProcessor;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFormat.Encoding;
import soundProcessor.BytestoAIS;
public class LYGFileIO{
	public Header header;
	public AdataFrame adataFrame;
	public MdataFrame mdataframe;
	public double duration;
	public String inputPath;
	public String outputPath;
	public int[] fsize;
	public double[] master;
	public double[] slave;
	public double[][] fftmatrix;
	public int[] fftLogCount;
	public BufferedImage image;
	public LYGFileIO()
	{
	}

	public void lygRead(String filepath) throws IOException {
		//read head by av
		InputStream is = new FileInputStream(filepath);
		String line;
		BufferedReader reader=new BufferedReader(new InputStreamReader(is));
		while((line=reader.readLine())!=null){
			//read head
			if(line.equals("<HEAD>")){
				System.out.println("has HEAD");
				header=new Header();
			}	    
			if(line.equals("<MFR>")){
				line=reader.readLine();
				header.MFrameRate=Integer.parseInt(line);
			}
			if(line.equals("<MHR>")){
				line=reader.readLine();
				header.MHFrame= Integer.parseInt(line);
			}
			if(line.equals("<MWR>")){
				line=reader.readLine();
				header.MWFrame= Integer.parseInt(line);
			}
			if(line.equals("<MFL>")){
				line=reader.readLine();
				header.MFrameLeangth=Integer.parseInt(line);
			}
			//
			if(line.equals("<AEN>")){
				line = reader.readLine();
				header.SEn=new Encoding(line);
			}
			if(line.equals("<AFL>")){
				line = reader.readLine();
				header.SFrameLeangth=Long.parseLong(line);
			}
			if(line.equals("<ASR>")){
				line = reader.readLine();
				header.SSampleRate = Float.parseFloat(line);
			}
			if(line.equals("<ASS>")){
				line = reader.readLine();
				header.SSampleSizeInBits = Integer.parseInt(line);
			}
			if(line.equals("<ACH>")){
				line = reader.readLine();
				header.SChannels = Integer.parseInt(line);
			}
			if(line.equals("<AFS>")){
				line = reader.readLine();
				header.SFrameSize = Integer.parseInt(line);
			}
			if(line.equals("<AFR>")){
				line = reader.readLine();
				header.SFrameRate = Float.parseFloat(line);
			}
			if(line.equals("<ABE>")){
				line = reader.readLine();
				header.SBigEndian = Boolean.parseBoolean(line); 
			}
			//read frame
			if(line.equals("<FRAME>")){
				System.out.println("has FRAME");
			}
			if(line.equals("<AF>")){
				adataFrame = new AdataFrame();
				adataFrame.audioArray = new double[(int) header.SFrameRate];
				int i = 0;
				while((!(line = reader.readLine()).equals("</AF>"))&& i<(int) header.SFrameRate){
					adataFrame.audioArray[i++] = Integer.parseInt(line);
				}
				double times;
				long milliseconds = (long)((header.SFrameLeangth * 1000) / header.SFrameRate);
				times = milliseconds / 1000.0;
				//
				int cur=(int)times;
				if(cur < times)
				{
					times = cur+1;
				}	 
				for(int i1=0;i1<times;i1++){
					adataFrame.next = new AdataFrame();
					adataFrame.next.prev=adataFrame;
					adataFrame = adataFrame.next;
					adataFrame.audioArray = new double[(int) header.SFrameRate];
					int i2 = 0;
					line = reader.readLine();
					while(!(line.equals("</AF>"))
							&& i2<(int) header.SFrameRate
							&& !line.equals("<MF>")	
							){
						System.out.println(i2);
						adataFrame.audioArray[i2++] = Integer.parseInt(line);
						line = reader.readLine();
					}
				}
			}
			if(line.equals("<MF>")){
				int frameID=0;
				mdataframe = new MdataFrame();
				mdataframe.RBGimage=new int[header.MHFrame][header.MWFrame];
				for(int i=0;i<header.MHFrame;i++){
					for(int j=0;j<header.MWFrame;j++){
						if(!(line = reader.readLine()).equals("</MF>")){
							mdataframe.RBGimage[i][j]=Integer.parseInt(line);
							mdataframe.frameID = frameID++;
						}
					}
				}	
				while(!(line = reader.readLine()).equals("</MF>")){
					mdataframe.next=new MdataFrame();
					mdataframe.next.prev=mdataframe;
					mdataframe=mdataframe.next;
					mdataframe.RBGimage=new int[header.MHFrame][header.MWFrame];
					for(int i=0;i<header.MHFrame;i++){
						for(int j=0;j<header.MWFrame;j++){
							if(!(line = reader.readLine()).equals("</MF>")){
								mdataframe.RBGimage[i][j]=Integer.parseInt(line);
								mdataframe.frameID = frameID++;
							}
						}
					}	
				}
			}	
		}
		is.close();
	}

	public void reset() {
		header=null;
		adataFrame=null;
		mdataframe=null;
	}

	public void lygWrite(String string) throws IOException {
		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(string),"GBK"));
		wr.write("<HEAD>"+"\n");
		wr.write("<MFR>\n"+header.MFrameRate+"\n</MFR>\n");
		System.out.println("<MFR>\n"+header.MFrameRate+"\n</MFR>\n");
		wr.write("<MHR>\n"+header.MHFrame+"\n</MHR>\n");
		System.out.println("<MHR>\n"+header.MHFrame+"\n</MHR>\n");
		wr.write("<MWR>\n"+header.MWFrame+"\n</MWR>\n");
		System.out.println("<MWR>\n"+header.MWFrame+"\n</MWR>\n");
		wr.write("<MFL>\n"+header.MFrameLeangth+"\n</MFL>\n");
		System.out.println("<MFL>\n"+header.MFrameLeangth+"\n</MFL>\n");
		//en /1sample rate /2samplesize /3 channels /4framesize  /5 framrate/bigendianture 
		wr.write("<AEN>\n"+header.SEn.toString()+"\n</AEN>\n");
		System.out.println("<AEN>\n"+header.SEn.toString()+"\n</AEN>\n");
		wr.write("<ASR>\n"+header.SSampleRate+"\n</ASR>\n");
		System.out.println("<ASR>\n"+header.SSampleRate+"\n</ASR>\n");
		wr.write("<ASS>\n"+header.SSampleSizeInBits+"\n</ASS>\n");
		System.out.println("<ASS>\n"+header.SSampleSizeInBits+"\n</ASS>\n");
		wr.write("<ACH>\n"+header.SChannels+"\n</ACH>\n");
		System.out.println("<ACH>\n"+header.SChannels+"\n</ACH>\n");
		wr.write("<AFS>\n"+header.SFrameSize+"\n</AFS>\n");
		System.out.println("<AFS>\n"+header.SFrameSize+"\n</AFS>\n");
		wr.write("<AFR>\n"+header.SFrameRate+"\n</AFR>\n");
		System.out.println("<AFR>\n"+header.SFrameRate+"\n</AFR>\n");
		wr.write("<ABE>\n"+header.SBigEndian+"\n</ABE>\n");
		System.out.println("<ABE>\n"+header.SBigEndian+"\n</ABE>\n");
		wr.write("<AFL>\n"+header.SFrameLeangth+"\n</AFL>\n");
		System.out.println("<AFL>\n"+header.SFrameLeangth+"\n</AFL>\n");
		wr.write("</HEAD>"+"\n");
		wr.write("<FRAME>"+"\n");
		wr.write("<AF>"+"\n");
		if(adataFrame!=null) {
			for(int i=0;i<adataFrame.audioArray.length;i++){
				wr.write(adataFrame.audioArray[i]+"\n");    
			}
			while(adataFrame.next!=null){
				adataFrame=adataFrame.next;
				for(int i=0;i<adataFrame.audioArray.length;i++){
					wr.write(adataFrame.audioArray[i]+"\n");    
				}

			}            	
		}
		wr.write("</AF>"+"\n");
		wr.write("<MF>"+"\n");
		long fr=0;
		if(mdataframe != null) {
			for (int i = 0; i < header.MHFrame; i++){
				for (int j = 0; j < header.MWFrame; j++){
					wr.write(mdataframe.image.getRGB(j, i)+"\n");		
				}
			}
			fr++;
			wr.write(fr+"\n");
			while(mdataframe.next!=null){
				mdataframe=mdataframe.next;
				for (int i = 0; i < header.MHFrame; i++){
					for (int j = 0; j < header.MWFrame; j++){
						wr.write(mdataframe.image.getRGB(j, i)+"\n");		
					}
				}
				fr++;
				wr.write(fr+"\n");
			}
		}
		wr.write("</MF>"+"\n");
		wr.write("</FRAME>"+"\n");
		wr.flush();
		wr.close();
	}
	
	public void toHead() {	
		while(adataFrame!=null&&adataFrame.prev!=null){
			adataFrame=adataFrame.prev;
		}
		while(mdataframe!=null&&mdataframe.prev!=null){
			mdataframe=mdataframe.prev;
		}	
	}
	
	public void init(){
		toHead();
		AudioFormat af=new AudioFormat(header.SEn,
				header.SSampleRate , 
				header.SSampleSizeInBits,
				header.SChannels,
				header.SFrameSize,
				header.SFrameRate,
				header.SBigEndian);
		//en /1sample rate /2samplesize /3 channels /4framesize  /5 framrate /bigendianture 
		int times=0;
		if(adataFrame != null){
			times++;
			adataFrame.audioIS = new BytestoAIS().getAIS(adataFrame.audioArray, af, adataFrame.audioIS);
			while(adataFrame.next != null){
				times++;
				adataFrame = adataFrame.next;
				adataFrame.audioIS = new BytestoAIS().getAIS(adataFrame.audioArray, af, adataFrame.audioIS);        	   
			}
		}
		this.duration=times;
		toHead();
	}
	
	public void getheader(RandomAccessFile raf) throws IOException {
		header=new Header();
		raf.seek(0);
		long headpos=0;
		byte[] iv = new byte[4];
		raf.read(iv);
		int f0 = ((int)iv[0]&0x000000ff);
		int f1 = ((int)iv[1]<<8&0x0000ff00);
		int f2 = ((int)iv[2]<<16&0x00ff0000);
		int f3 = ((int)iv[3]<<24&0xff000000);
		f0 = f3|f2|f1|f0;
		header.MWFrame = f0;
		System.out.println("<MWR>\n"+header.MWFrame+"\n</MWR>\n");
		// TODO Auto-generated method stub
		headpos+=4;
		iv = new byte[4];
		raf.read(iv);
		f0 = ((int)iv[0]&0x000000ff);
		f1 = ((int)iv[1]<<8&0x0000ff00);
		f2 = ((int)iv[2]<<16&0x00ff0000);
		f3 = ((int)iv[3]<<24&0xff000000);
		f0 = f3|f2|f1|f0;
		header.MHFrame = f0;
		System.out.println("<MWR>\n"+header.MHFrame+"\n</MWR>\n");
		headpos+=4;
		//3
		iv = new byte[4];
		raf.read(iv);
		f0 = ((int)iv[0]&0x000000ff);
		f1 = ((int)iv[1]<<8&0x0000ff00);
		f2 = ((int)iv[2]<<16&0x00ff0000);
		f3 = ((int)iv[3]<<24&0xff000000);
		f0 = f3|f2|f1|f0;
		header.MFrameLeangth = f0;
		System.out.println("<MWR>\n"+header.MFrameLeangth+"\n</MWR>\n");
		headpos+=4;
		//4
		iv = new byte[4];
		raf.read(iv);
		f0 = (int)iv[0]&0x000000ff;
		f1 = (int)iv[1]<<8&0x0000ff00;
		f2 = (int)iv[2]<<16&0x00ff0000;
		f3 = (int)iv[3]<<24&0xff000000;
		f0 = f3|f2|f1|f0;
		header.MFrameRate = f0;
		System.out.println("<MWR>\n"+header.MFrameLeangth+"\n</MWR>\n");
		headpos+=4;
		//5
		byte[] size=new byte[1];
		raf.read(size);
		headpos+=1;
		if(size[0]!=0)
		{
			byte array[]=new byte[size[0]*2];
			raf.read(array);
			char[] ch=new char[size[0]];
			for(int reg = 0;reg<size[0];reg++)
			{
				int c0=((int)array[reg*2]<<8&0xff00);
				int c1=(int)array[reg*2+1]&0x00ff;
				ch[reg]= (char) (c1|c0);	 
			}

			String se=new String(ch);
			header.SEn=new Encoding(se);
			System.out.println("good");
			headpos+=array.length;
		}
		//6
		iv = new byte[4];
		raf.read(iv);
		f0 = ((int)iv[0]&0x000000ff);
		f1 = ((int)iv[1]<<8&0x0000ff00);
		f2 = ((int)iv[2]<<16&0x00ff0000);
		f3 = ((int)iv[3]<<24&0xff000000);
		f0 = f3|f2|f1|f0;
		float fl=Float.intBitsToFloat(f0);//.floatToIntBits(IO.header.SSampleRate);
		header.SSampleRate = fl;
		System.out.println("<MWR>\n"+header.SSampleRate+"\n</MWR>\n");
		headpos+=4;
		//7
		iv = new byte[4];
		raf.read(iv);
		f0 = (int)iv[0]&0x000000ff;
		f1 = (int)iv[1]<<8&0x0000ff00;
		f2 = (int)iv[2]<<16&0x00ff0000;
		f3 = (int)iv[3]<<24&0xff000000;
		f0 = f3|f2|f1|f0;
		header.SSampleSizeInBits = f0;
		System.out.println("<MWR>\n"+header.SSampleSizeInBits+"\n</MWR>\n");
		headpos+=4;
		//8
		iv = new byte[4];
		raf.read(iv);
		f0 = (int)iv[0]&0x000000ff;
		f1 = (int)iv[1]<<8&0x0000ff00;
		f2 = (int)iv[2]<<16&0x00ff0000;
		f3 = (int)iv[3]<<24&0xff000000;
		f0 = f3|f2|f1|f0;
		header.SChannels = f0;
		System.out.println("<MWR>\n"+header.SChannels+"\n</MWR>\n");
		headpos+=4;
		//9
		iv = new byte[4];
		raf.read(iv);
		f0 = (int)iv[0]&0x000000ff;
		f1 = (int)iv[1]<<8&0x0000ff00;
		f2 = (int)iv[2]<<16&0x00ff0000;
		f3 = (int)iv[3]<<24&0xff000000;
		f0 = f3|f2|f1|f0;
		header.SFrameSize = f0;
		System.out.println("<MWR>\n"+header.SFrameSize+"\n</MWR>\n");
		headpos+=4;
		//10
		iv = new byte[4];
		raf.read(iv);
		f0 = ((int)iv[0]&0x000000ff);
		f1 = ((int)iv[1]<<8&0x0000ff00);
		f2 = ((int)iv[2]<<16&0x00ff0000);
		f3 = ((int)iv[3]<<24&0xff000000);
		f0 = f3|f2|f1|f0;
		fl=Float.intBitsToFloat(f0);//.floatToIntBits(IO.header.SSampleRate);
		header.SFrameRate = fl;
		System.out.println("<MWR>\n"+header.SFrameRate+"\n</MWR>\n");
		headpos+=4;
		//11
		byte []bein=new byte[1];
		raf.read(bein);
		if(bein[0]==0){
			header.SBigEndian=false;
		}else{
			header.SBigEndian=true;
		}
		headpos+=1;
		//12
		byte[] audiopos= new byte[8];
		raf.read(audiopos);
		long l0 = ((long)audiopos[0]&0x00000000000000ff);
		long l1 = ((long)audiopos[1]&0x00000000000000ff)<<8;
		long l2 = ((long)audiopos[2]&0x00000000000000ff)<<16;
		long l3 = ((long)audiopos[3]&0x00000000000000ff)<<24;
		long l4 = ((long)audiopos[4]&0x00000000000000ff)<<32;
		long l5 = ((long)audiopos[5]&0x00000000000000ff)<<40;
		long l6 = ((long)audiopos[6]&0x00000000000000ff)<<48;
		long l7 = ((long)audiopos[7]&0x00000000000000ff)<<56;
		l0=l7|l6|l5|l4|l3|l2|l1|l0;
		header.SFrameLeangth=l0;
		headpos+=8;
		//13
		audiopos=new byte[16];
		raf.read(audiopos);
		headpos+=16;
		//14
		audiopos = new byte[8];
		raf.read(audiopos);
		l0 = ((long)audiopos[0]&0x00000000000000ff);
		l1 = ((long)audiopos[1]&0x00000000000000ff)<<8;
		l2 = ((long)audiopos[2]&0x00000000000000ff)<<16;
		l3 = ((long)audiopos[3]&0x00000000000000ff)<<24;
		l4 = ((long)audiopos[4]&0x00000000000000ff)<<32;
		l5 = ((long)audiopos[5]&0x00000000000000ff)<<40;
		l6 = ((long)audiopos[6]&0x00000000000000ff)<<48;
		l7 = ((long)audiopos[7]&0x00000000000000ff)<<56;
		l0=l7|l6|l5|l4|l3|l2|l1|l0;
		header.SWavPos=l0;
		headpos+=8;
		header.MovPos=headpos;
		duration=header.MFrameLeangth/header.MFrameRate;
		System.out.println("end header get");	
	}
	
	public void initByAudioBytes(AudioFormat af) {	
		//en /1sample rate /2samplesize /3 channels /4framesize  /5 framrate /bigendianture 
		adataFrame.audioIS = new BytestoAIS().getAIS(adataFrame.audioBytes, af, adataFrame.audioIS);		
		// TODO Auto - generated method stub

	}
}