package soundProcessor;
import java.awt.Dimension;

import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
import java.util.Vector;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;




import movieProcessor.LYGFileIO;
public class SoundWaveVector implements Runnable{
	Vector<Double> lines= new Vector<Double>();
	public byte[] audioBytes;
	public double[] audioData = null;
	public double pshock;
	public double nshock;
	@SuppressWarnings("rawtypes")
	public Vector getVectorLines(AudioInputStream ais, float sFrameRate){
		lines.removeAllElements();  // clear the old vector
		AudioFormat af = ais.getFormat();
		audioBytes=null;
		if (audioBytes == null) {
			try {
				int reg=0;
				audioBytes = new byte[(int) (sFrameRate * af.getFrameSize())];
				System.out.println("audiobyte length:"+audioBytes.length);
				reg= ais.read(audioBytes);
				if(reg < audioBytes.length);{
					byte[] iner=new byte[reg];
					for(int i=0;i<reg;i++){
						iner[i]=audioBytes[i];
					}
					audioBytes=iner;
				}                 
				System.out.println(reg);
			} catch (Exception ex) { 
				//reportStatus(ex.toString());
				return null; 
			}
		}

		Dimension d = new Dimension(800,600);
		int w = d.width;
		int h = d.height-15;

		if (af.getSampleSizeInBits() == 16) {
			int nlengthInSamples = audioBytes.length / 2;
			audioData = new double[nlengthInSamples];
			if (af.isBigEndian()) {
				for (int i = 0; i < nlengthInSamples; i++) {
					/* First byte is MSB (high order) */
					int MSB = (int) audioBytes[2*i];
					/* Second byte is LSB (low order) */
					int LSB = (int) audioBytes[2*i+1];
					audioData[i] = MSB << 8 | (255 & LSB);
				}
			} else {
				for (int i = 0; i < nlengthInSamples; i++){
					/* First byte is LSB (low order) */
					int LSB = (int) audioBytes[2*i];
					/* Second byte is MSB (high order) */
					int MSB = (int) audioBytes[2*i+1];
					audioData[i] = MSB << 8 | (255 & LSB);
				}
			}
		} else if (af.getSampleSizeInBits() == 8) {
			int nlengthInSamples = audioBytes.length;
			audioData = new double[nlengthInSamples];
			if (af.getEncoding().toString().startsWith("PCM_SIGN")) {
				for (int i = 0; i < audioBytes.length; i++) {
					audioData[i] = audioBytes[i];
				}
			} else {
				for (int i = 0; i < audioBytes.length; i++) {
					audioData[i] = audioBytes[i] - 128;
				}
			}
		}
		System.out.println(audioBytes.length);
		System.out.println(af.getFrameSize());
		System.out.println(w);
		System.out.println(audioData.length);
		int frames_per_pixel = audioBytes.length / af.getFrameSize()/w;
		byte my_byte = 0;
		double y_last = 0;
		int idx=0;
		int numChannels = af.getChannels();
		for (double x = 0; x < w && audioData != null; x++) {
			idx = (int) (frames_per_pixel * numChannels * x);
			if (af.getSampleSizeInBits() == 8) {
				my_byte = (byte) audioData[idx];
			} else {
				my_byte = (byte)(128 * audioData[idx]/32768);
			}
			double y_new = (double)(h*(128-my_byte)/256);
			lines.add(new Line2D.Double(x, y_last, x, y_new));
			y_last = y_new;
		}
		return lines;
	}
	//
	@SuppressWarnings({ "rawtypes", "unused" })
	public Vector getVectorLines1(AudioInputStream ais) {
		pshock=0;
		nshock=0;
		lines.removeAllElements();  // clear the old vector
		AudioFormat af = ais.getFormat();
		audioBytes=null;
		if (audioBytes == null) {
			try {
				audioBytes = new byte[(int) (ais.getFrameLength() *af.getFrameSize())];
				// System.out.println("audiobyte length:"+audioBytes.length);
				ais.read(audioBytes);
			} catch (Exception ex) { 
				//reportStatus(ex.toString());
				return null; 
			}
		}

		Dimension d = new Dimension(1600,600);
		int w = d.width;
		int h = d.height-15;    
		if (af.getSampleSizeInBits() == 16) {
			int nlengthInSamples = audioBytes.length / 2;
			audioData = new double[nlengthInSamples];
			if (af.isBigEndian()) {
				for (int i = 0; i < nlengthInSamples; i++) {
					/* First byte is MSB (high order) */
					int MSB = (int) audioBytes[2*i];
					/* Second byte is LSB (low order) */
					int LSB = (int) audioBytes[2*i+1];
					audioData[i] = MSB << 8 | (255 & LSB);
				}
			} else {
				for (int i = 0; i < nlengthInSamples; i++) {
					/* First byte is LSB (low order) */
					int LSB = (int) audioBytes[2*i];
					/* Second byte is MSB (high order) */
					int MSB = (int) audioBytes[2*i+1];
					audioData[i] = MSB << 8 | (255 & LSB);
				}
			}
		} else if (af.getSampleSizeInBits() == 8) {
			int nlengthInSamples = audioBytes.length;
			audioData = new double[nlengthInSamples];
			if (af.getEncoding().toString().startsWith("PCM_SIGN")) {
				for (int i = 0; i < audioBytes.length; i++) 
				{
					audioData[i] = audioBytes[i];
				}
			} else {
				for (int i = 0; i < audioBytes.length; i++) 
				{
					audioData[i] = audioBytes[i] - 128;
				}
			}
		}
		// System.out.println(audioBytes.length);
		//  System.out.println(af.getFrameSize());
		//  System.out.println(w);
		//   System.out.println(audioData.length);
		int frames_per_pixel = audioBytes.length/af.getFrameSize()/w;
		byte my_byte = 0;
		double y_last = 0;
		int idx=0;
		int numChannels = af.getChannels();
		double newdata[]= new double [w] ;
		double newdata1[]= new double [w] ;
		//caiyang
		for(int i=0; i < w && audioData != null;i++){
			idx = (int) (frames_per_pixel * numChannels * i);
			newdata[i]=audioData[idx];
		}
		System.out.println("good1");
		//lvbo
		for(int i=5;i<newdata.length-5;i++){
			double sum=0;
			for(int j=-2;j<3;j++){
				sum+=newdata[i+j]; 
			}
			sum/=5;
			newdata1[i]=sum/32;
			if(newdata1[i]<5&&newdata1[i]>-5)
				newdata1[i]=0;
			newdata1[i]*=2;  
		}
		System.out.println("good2");
		//xianfu
		for(double x = 0; x < w; x++) {  
			//System.out.println("->"+audioData[idx]);
			double y_new = newdata1[(int)x];
			lines.add(new Line2D.Double(x, y_last, x, y_new));
			y_last = y_new;
			if(y_new>pshock)
				pshock= y_new;
			if(y_new<nshock)
				nshock= y_new;
		}
		System.out.println("good3");
		return lines;
	}

	@SuppressWarnings("rawtypes")
	public Vector getVectorLines(AudioInputStream ais){
		pshock=0;
		nshock=0;
		lines.removeAllElements();  // clear the old vector
		AudioFormat af = ais.getFormat();
		audioBytes=null;
		if (audioBytes == null) {
			try {
				audioBytes = new byte[(int) (ais.getFrameLength() *af.getFrameSize())];
				// System.out.println("audiobyte length:"+audioBytes.length);
				ais.read(audioBytes);
			} catch (Exception ex) { 
				//reportStatus(ex.toString());
				return null; 
			}
		}

		Dimension d = new Dimension(800,6000);
		int w = d.width;
		int h = d.height-15;    
		if (af.getSampleSizeInBits() == 16) {
			int nlengthInSamples = audioBytes.length / 2;
			audioData = new double[nlengthInSamples];
			if (af.isBigEndian()) {
				for (int i = 0; i < nlengthInSamples; i++) {
					/* First byte is MSB (high order) */
					int MSB = (int) audioBytes[2*i];
					/* Second byte is LSB (low order) */
					int LSB = (int) audioBytes[2*i+1];
					audioData[i] = MSB << 8 | (255 & LSB);
				}
			} else {
				for (int i = 0; i < nlengthInSamples; i++) {
					/* First byte is LSB (low order) */
					int LSB = (int) audioBytes[2*i];
					/* Second byte is MSB (high order) */
					int MSB = (int) audioBytes[2*i+1];
					audioData[i] = MSB << 8 | (255 & LSB);
				}
			}
		} else if (af.getSampleSizeInBits() == 8) {
			int nlengthInSamples = audioBytes.length;
			audioData = new double[nlengthInSamples];
			if (af.getEncoding().toString().startsWith("PCM_SIGN")) {
				for (int i = 0; i < audioBytes.length; i++) 
				{
					audioData[i] = audioBytes[i];
				}
			} else {
				for (int i = 0; i < audioBytes.length; i++){
					audioData[i] = audioBytes[i] - 128;
				}
			}
		}
		// System.out.println(audioBytes.length);
		//  System.out.println(af.getFrameSize());
		//  System.out.println(w);
		//   System.out.println(audioData.length);
		int frames_per_pixel = audioBytes.length / af.getFrameSize()/w;

		byte my_byte = 0;
		double y_last = 0;
		int idx=0;
		int numChannels = af.getChannels();
		for (double x = 0; x < w && audioData != null; x++) {
			idx = (int) (frames_per_pixel * numChannels * x);
			if (af.getSampleSizeInBits() == 8) {
				my_byte = (byte) audioData[idx];
			} else {
				my_byte = (byte)(128 * audioData[idx]/32768);
			}
			System.out.println("->"+audioData[idx]);
			double y_new = (double)(h*(128-my_byte)/256);
			lines.add(new Line2D.Double(x, y_last, x, y_new));
			y_last = y_new;
			if(y_new>pshock)
				pshock= y_new;
			if(y_new<nshock)
				nshock= y_new;
		}
		return lines;
	}
	@SuppressWarnings("rawtypes")
	public Vector getVectorLines(LYGFileIO lygout) {
		lines.removeAllElements(); 
		AudioInputStream aisout;
		int pos=0;
		if(lygout.adataFrame!=null){
			aisout = new BytestoAIS().getAIS(lygout.adataFrame.audioArray,lygout.adataFrame.audioIS);
			addSerparate(aisout,lygout.duration,pos);
			while(lygout.adataFrame.next!=null){
				pos+=1;
				lygout.adataFrame=lygout.adataFrame.next;

				aisout = new BytestoAIS().getAIS(lygout.adataFrame.audioArray,lygout.adataFrame.audioIS);
				addSerparate(aisout,lygout.duration,pos);
			}
		}
		return lines;
	}

	private void addSerparate(AudioInputStream ais,
			double duration, int pos) {
		// clear the old vector
		AudioFormat af = ais.getFormat();
		audioBytes=null;
		if (audioBytes == null) {
			try {
				audioBytes = new byte[(int) (ais.getFrameLength() *af.getFrameSize())];
				System.out.println("audiobyte length:"+audioBytes.length);
				ais.read(audioBytes);
			} catch (Exception ex) { 
				//reportStatus(ex.toString());
				return; 
			}
		}

		Dimension d = new Dimension(110000,600);
		int w = d.width;
		int h = d.height-15;    
		if (af.getSampleSizeInBits() == 16) {
			int nlengthInSamples = audioBytes.length / 2;
			audioData = new double[nlengthInSamples];
			if (af.isBigEndian()) {
				for (int i = 0; i < nlengthInSamples; i++) {
					/* First byte is MSB (high order) */
					int MSB = (int) audioBytes[2*i];
					/* Second byte is LSB (low order) */
					int LSB = (int) audioBytes[2*i+1];
					audioData[i] = MSB << 8 | (255 & LSB);
				}
			} else {
				for (int i = 0; i < nlengthInSamples; i++) {
					/* First byte is LSB (low order) */
					int LSB = (int) audioBytes[2*i];
					/* Second byte is MSB (high order) */
					int MSB = (int) audioBytes[2*i+1];
					audioData[i] = MSB << 8 | (255 & LSB);
				}
			}
		} else if (af.getSampleSizeInBits() == 8) {
			int nlengthInSamples = audioBytes.length;
			audioData = new double[nlengthInSamples];
			if (af.getEncoding().toString().startsWith("PCM_SIGN")) {
				for (int i = 0; i < audioBytes.length; i++) {
					audioData[i] = audioBytes[i];
				}
			} else {
				for (int i = 0; i < audioBytes.length; i++) {
					audioData[i] = audioBytes[i] - 128;
				}
			}
		}
		int frames_per_pixel = audioBytes.length / af.getFrameSize()/w;
		byte my_byte = 0;
		double y_last = 0;
		int idx=0;
		int numChannels = af.getChannels();
		idx = (int) (w/duration);
		int i=0;
		i = audioData.length/idx;
		int j=0;
		for (double x = pos*w/duration; x < (pos+1)*w/duration && audioData != null; x++) {
			idx = (int) (frames_per_pixel * numChannels * (x/duration));
			if (af.getSampleSizeInBits() == 8) {
				my_byte = (byte) audioData[(j++)*i];
			} else {
				my_byte = (byte)(128 * audioData[(j++)*i]/32768);
			}
			double y_new = (double)(h*(128-my_byte)/256);
			lines.add(new Line2D.Double(x, y_last, x+1, y_new));
			y_last = y_new;
		}
		// TODO Auto-generated method stub
	}

	@SuppressWarnings("rawtypes")
	public Vector getVectorLinesbByArray(LYGFileIO lygout) {

		lines.removeAllElements(); 
		AudioInputStream aisout;
		int pos=0;
		if(lygout.adataFrame!=null){
			aisout = new BytestoAIS().getAIS(lygout.adataFrame.audioArray,lygout.adataFrame.audioIS);
			addSerparate(aisout,lygout.duration,pos);
			while(lygout.adataFrame.next!=null){
				pos+=1;
				lygout.adataFrame=lygout.adataFrame.next;

				aisout = new BytestoAIS().getAIS(lygout.adataFrame.audioArray,lygout.adataFrame.audioIS);
				//lygout.adataFrame.audioIS= new BytestoAIS().getAIS(lygout.adataFrame.audioArray,lygout.adataFrame.audioIS);
				addSerparate(aisout,lygout.duration,pos);
			}
		}
		return lines;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}        
}