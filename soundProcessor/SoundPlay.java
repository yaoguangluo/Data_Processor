package soundProcessor;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import movieProcessor.AdataFrame;
public class SoundPlay{
	public SoundPlay(){
	}
	public void Play(AudioInputStream ais1){
		if (ais1 == null) {
			return;
		}
		AudioFormat format = ais1.getFormat();
		AudioInputStream playbackInputStream = AudioSystem.getAudioInputStream(format, ais1);             
		if (playbackInputStream == null) {
			return;
		}
		SourceDataLine line1;
		DataLine.Info info1 = new DataLine.Info(SourceDataLine.class, format);
		try {
			line1 = (SourceDataLine) AudioSystem.getLine(info1);
			int frameSizeInBytes = ais1.getFormat().getFrameSize();
			int bufferLengthInFrames = line1.getBufferSize() / (8*frameSizeInBytes);
			int bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
			int bufSize=bufferLengthInBytes;       
			line1.open(format, bufSize);
		} catch (LineUnavailableException ex) { 
			return;
		}
		int frameSizeInBytes = format.getFrameSize();
		int bufferLengthInFrames = line1.getBufferSize() /(8*frameSizeInBytes) ;
		int bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
		byte[] data = new byte[bufferLengthInBytes];
		int numBytesRead = 0;
		line1.start();
		while(true){
			try {
				if ((numBytesRead = ais1.read(data)) == -1) {
					break;
				}
				int numBytesRemaining = numBytesRead;
				while (numBytesRemaining > 0 ) {
					numBytesRemaining -= line1.write(data, 0, numBytesRemaining);
				}
			} catch (Exception e) {
				break;
			}
		}
		line1.stop();
		line1.close();
		line1 = null;
	}

	public void Play(AdataFrame af) throws IOException{
		if (af.audioIS == null) {
			return;
		}
		AudioFormat format = af.audioIS.getFormat();
		AudioInputStream playbackInputStream = AudioSystem.getAudioInputStream(format, af.audioIS);             
		if (playbackInputStream == null) {
			return;
		}
		SourceDataLine line1;
		DataLine.Info info1 = new DataLine.Info(SourceDataLine.class, format);
		try {
			line1 = (SourceDataLine) AudioSystem.getLine(info1);    
			int frameSizeInBytes = af.audioIS.getFormat().getFrameSize();
			int bufferLengthInFrames = line1.getBufferSize() / (8*frameSizeInBytes);
			int bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
			int bufSize=bufferLengthInBytes;       
			line1.open(format, bufSize);
		} catch (LineUnavailableException ex) { 
			return;
		}
		int frameSizeInBytes = format.getFrameSize();
		int bufferLengthInFrames = line1.getBufferSize() /(8*frameSizeInBytes) ;
		int bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
		byte[] data = new byte[bufferLengthInBytes];
		int numBytesRead = 0;
		line1.start();
		af.audioIS.reset();
		while(true) {
			try {
				if ((numBytesRead = af.audioIS.read(data)) == -1) {
					break;
				}
				int numBytesRemaining = numBytesRead;
				while (numBytesRemaining > 0 ) {
					numBytesRemaining -= line1.write(data, 0, numBytesRemaining);
				}
			} catch (Exception e) {
				break;
			}
		}
		while(af.next!=null){
			af=af.next;
			af.audioIS.reset();
			while(true)  {
				try {
					if ((numBytesRead = af.audioIS.read(data)) == -1) {
						break;
					}
					int numBytesRemaining = numBytesRead;
					while (numBytesRemaining > 0 ) {
						numBytesRemaining -= line1.write(data, 0, numBytesRemaining);
					}
				} catch (Exception e) {
					break;
				}
			}   
		}   
		line1.stop();
		line1.close();
		line1 = null;
	}

	public void Play(byte[] secbytesarray, AudioFormat af, SourceDataLine line1, int sp, byte[] data) throws LineUnavailableException{
		for(int i=0;i<sp;i++){
			for(int j=0;j<data.length;j++){
				data[j]=secbytesarray[j+i*data.length];
			}
			line1.write(data, 0, data.length);
		}
	}

	@SuppressWarnings("unused")
	public void Play1(AudioInputStream ais1, AudioFormat af) throws IOException{
		SourceDataLine line1;
		DataLine.Info info1 = new DataLine.Info(SourceDataLine.class,af);
		try {
			line1 = (SourceDataLine) AudioSystem.getLine(info1);
			int bufSize =line1.getBufferSize() / 8;
			line1.open(af, bufSize);
		} catch (LineUnavailableException ex) { 
			return;
		}
		int bufSize =line1.getBufferSize() / 8;
		byte[] data = new byte[bufSize];
		int numBytesRead = 0;
		line1.start(); 
		byte all[]=new byte[(int) (ais1.getFrameLength()*2)];
		ais1.read(all);  
		int sp=all.length/data.length;
		int pos=0;  
		for(int i=0;i<sp;i++){
			for(int j=0;j<data.length;j++){
				data[j]=all[j+i*data.length];
			}
			int numBytesRemaining = data.length;//343
			while (numBytesRemaining > 0 ) {
				numBytesRemaining -= line1.write(data, 0, numBytesRemaining);
			}
		} 
		line1.stop();
		line1.close();
		line1 = null;
	}

	public void PlayArray(AdataFrame af) {
		if (af.audioIS == null) {
			return;
		} 
		AudioInputStream ais = new BytestoAIS().getAIS(af.audioArray,af.audioIS);
		AudioFormat format = af.audioIS.getFormat();
		AudioInputStream playbackInputStream = AudioSystem.getAudioInputStream(format, af.audioIS);             
		if (playbackInputStream == null) {
			return;
		}
		SourceDataLine line1;
		DataLine.Info info1 = new DataLine.Info(SourceDataLine.class, format);
		try {
			line1 = (SourceDataLine) AudioSystem.getLine(info1);

			int frameSizeInBytes = af.audioIS.getFormat().getFrameSize();
			int bufferLengthInFrames = line1.getBufferSize() / (8*frameSizeInBytes);
			int bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
			int bufSize=bufferLengthInBytes;       
			line1.open(format, bufSize);
		} catch (LineUnavailableException ex) { 
			return;
		}
		int frameSizeInBytes = format.getFrameSize();
		int bufferLengthInFrames = line1.getBufferSize() /(8*frameSizeInBytes) ;
		int bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
		byte[] data = new byte[bufferLengthInBytes];
		int numBytesRead = 0;
		line1.start();   
		while(true) {
			try {
				if ((numBytesRead = ais.read(data)) == -1) {
					break;
				}
				int numBytesRemaining = numBytesRead;
				while (numBytesRemaining > 0 ) {
					numBytesRemaining -= line1.write(data, 0, numBytesRemaining);
				}
			} catch (Exception e) {
				break;
			}
		}
		while(af.next!=null)  {
			af=af.next;
			ais = new BytestoAIS().getAIS(af.audioArray,af.audioIS);
			while(true)  {
				try {
					if ((numBytesRead = ais.read(data)) == -1) {
						break;
					}
					int numBytesRemaining = numBytesRead;
					while (numBytesRemaining > 0 ) {
						numBytesRemaining -= line1.write(data, 0, numBytesRemaining);
					}
				} catch (Exception e) {
					break;
				}
			}   
		}   
		line1.stop();
		line1.close();
		line1 = null;
	}

	public void PlayMasterArray(AdataFrame af) {
		// TODO Auto-generated method stub
		if (af.audioIS == null) {
			return;
		}
		double master[]= new double[af.master.length];                              
		int psita=0;    
		for(int i = 2;i<master.length-2;i++){  
			psita=0;
			for(int p=-2;p<3;p++)
			{
				psita+=(af.master[i+p]);
			}
			master[i]=psita/5;                 
		}   
		AudioInputStream ais = new BytestoAIS().getAIS(master,af.audioIS); 
		AudioFormat format = af.audioIS.getFormat();
		AudioInputStream playbackInputStream = AudioSystem.getAudioInputStream(format, af.audioIS);             
		if (playbackInputStream == null) {
			return;
		}
		SourceDataLine line1;
		DataLine.Info info1 = new DataLine.Info(SourceDataLine.class, format);
		try {
			line1 = (SourceDataLine) AudioSystem.getLine(info1);

			int frameSizeInBytes = af.audioIS.getFormat().getFrameSize();
			int bufferLengthInFrames = line1.getBufferSize() / (8*frameSizeInBytes);
			int bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
			int bufSize=bufferLengthInBytes;       
			line1.open(format, bufSize);
		} catch (LineUnavailableException ex) { 
			return;
		}
		int frameSizeInBytes = format.getFrameSize();
		int bufferLengthInFrames = line1.getBufferSize() /(8*frameSizeInBytes) ;
		int bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
		byte[] data = new byte[bufferLengthInBytes];
		int numBytesRead = 0;
		line1.start();    
		while(true)  {
			try {
				if ((numBytesRead = ais.read(data)) == -1) {
					break;
				}
				int numBytesRemaining = numBytesRead;
				while (numBytesRemaining > 0 ) {
					numBytesRemaining -= line1.write(data, 0, numBytesRemaining);
				}
			} catch (Exception e) {
				break;
			}
		}
		while(af.next!=null)   {
			af=af.next;       
			psita=0;    
			for(int i = 2;i<master.length-2;i++){
				//find high  
				psita=0;
				for(int p=-2;p<3;p++)
				{
					psita+=(af.master[i+p]);
				}
				master[i]=psita/5;                
			}  
			ais = new BytestoAIS().getAIS(master,af.audioIS);
			while(true)     {
				try {
					if ((numBytesRead = ais.read(data)) == -1)       {
						break;
					}
					int numBytesRemaining = numBytesRead;
					while (numBytesRemaining > 0 )      {
						numBytesRemaining -= line1.write(data, 0, numBytesRemaining);
					}
				} catch (Exception e)   {
					break;
				}
			}   
		}   
		line1.stop();
		line1.close();
		line1 = null;
	}
	public void PlaySlaveArray(AdataFrame af) {
		if (af.audioIS == null) {
			return;
		} 
		double slave[]= new double[af.slave.length]; 
		int psita=0;
		for(int i = 2;i<slave.length-2;i++)
		{
			//find high  
			psita=0;
			for(int p=-2;p<3;p++)
			{
				psita+=(af.slave[i+p]);
			}
			slave[i]=psita/5;

		}         
		AudioInputStream ais = new BytestoAIS().getAIS(slave,af.audioIS);
		AudioFormat format = af.audioIS.getFormat();
		AudioInputStream playbackInputStream = AudioSystem.getAudioInputStream(format, af.audioIS);             
		if (playbackInputStream == null) {
			return;
		}
		SourceDataLine line1;
		DataLine.Info info1 = new DataLine.Info(SourceDataLine.class, format);
		try {
			line1 = (SourceDataLine) AudioSystem.getLine(info1);

			int frameSizeInBytes = af.audioIS.getFormat().getFrameSize();
			int bufferLengthInFrames = line1.getBufferSize() / (8*frameSizeInBytes);
			int bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
			int bufSize=bufferLengthInBytes;       
			line1.open(format, bufSize);
		} catch (LineUnavailableException ex) { 
			return;
		}
		int frameSizeInBytes = format.getFrameSize();
		int bufferLengthInFrames = line1.getBufferSize() /(8*frameSizeInBytes) ;
		int bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
		byte[] data = new byte[bufferLengthInBytes];
		int numBytesRead = 0;
		line1.start();
		while(true)
		{
			try {
				if ((numBytesRead = ais.read(data)) == -1) {
					break;
				}
				int numBytesRemaining = numBytesRead;
				while (numBytesRemaining > 0 )   {
					numBytesRemaining -= line1.write(data, 0, numBytesRemaining);
				}
			} catch (Exception e) {
				break;
			}
		}
		while(af.next!=null)
		{
			af=af.next;


			psita=0;

			for(int i = 2;i<slave.length-2;i++){
				//find high


				psita=0;
				for(int p=-2;p<3;p++)      {
					psita+=(af.slave[i+p]);
				}
				slave[i]=psita/5;

			}    
			ais = new BytestoAIS().getAIS(slave,af.audioIS);
			while(true)  {
				try {
					if ((numBytesRead = ais.read(data)) == -1)       {
						break;
					}
					int numBytesRemaining = numBytesRead;
					while (numBytesRemaining > 0 ) 
					{
						numBytesRemaining -= line1.write(data, 0, numBytesRemaining);
					}
				} catch (Exception e) 
				{
					break;
				}
			}   
		}   
		line1.stop();
		line1.close();
		line1 = null;

		// TODO Auto-generated method stub

	}
	public void PlayFftArray(AdataFrame af) {
		if (af.audioIS == null) {
			return;
		}
		double slave[]= new double[af.fftarray.length];
		int psita=0;    
		for(int i = 2;i<slave.length-2;i++){

			psita=0;
			for(int p=-2;p<3;p++)
			{
				psita+=(af.fftarray[i+p]);
			}
			slave[i]=psita/5;

		}  

		AudioInputStream ais = new BytestoAIS().getAIS(slave,af.audioIS);
		AudioFormat format = af.audioIS.getFormat();
		AudioInputStream playbackInputStream = AudioSystem.getAudioInputStream(format, af.audioIS);             
		if (playbackInputStream == null) {
			return;
		}
		SourceDataLine line1;
		DataLine.Info info1 = new DataLine.Info(SourceDataLine.class, format);
		try {
			line1 = (SourceDataLine) AudioSystem.getLine(info1);

			int frameSizeInBytes = af.audioIS.getFormat().getFrameSize();
			int bufferLengthInFrames = line1.getBufferSize() / (8*frameSizeInBytes);
			int bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
			int bufSize=bufferLengthInBytes;       
			line1.open(format, bufSize);
		} catch (LineUnavailableException ex) { 
			return;
		}
		int frameSizeInBytes = format.getFrameSize();
		int bufferLengthInFrames = line1.getBufferSize() /(8*frameSizeInBytes) ;
		int bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
		byte[] data = new byte[bufferLengthInBytes];
		int numBytesRead = 0;
		line1.start();

		while(true)
		{
			try {
				if ((numBytesRead = ais.read(data)) == -1) 
				{
					break;
				}
				int numBytesRemaining = numBytesRead;
				while (numBytesRemaining > 0 ) 
				{
					numBytesRemaining -= line1.write(data, 0, numBytesRemaining);
				}
			} catch (Exception e) 
			{
				break;
			}
		}
		while(af.next!=null)
		{
			af=af.next;
			slave= new double[af.fftarray.length];

			psita=0;

			for(int i = 2;i<slave.length-2;i++)
			{
				//find high


				psita=0;
				for(int p=-2;p<3;p++)
				{
					psita+=(af.fftarray[i+p]);
				}
				slave[i]=psita/5;

			}  

			ais = new BytestoAIS().getAIS(slave,af.audioIS);
			while(true)
			{
				try {
					if ((numBytesRead = ais.read(data)) == -1) 
					{
						break;
					}
					int numBytesRemaining = numBytesRead;
					while (numBytesRemaining > 0 ) 
					{
						numBytesRemaining -= line1.write(data, 0, numBytesRemaining);
					}
				} catch (Exception e) 
				{
					break;
				}
			}   
		}   
		line1.stop();
		line1.close();
		line1 = null;
	}
}