package soundProcessor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
public class SoundRecord  implements Runnable {
	public AudioInputStream ais1;
	public int big;
	TargetDataLine line;
	Thread thread;
	AudioFormat format;
	public boolean stoped=false;
	@SuppressWarnings("unused")
	public SoundRecord(AudioInputStream ais2){
		ais1 = null;  

		AudioFormat.Encoding encoding = AudioFormat.Encoding.PCM_SIGNED;  
		float rate = 9600f;  
		int sampleSize = 16;  
		String signedString = "signed";  
		boolean bigEndian = true;  
		int channels = 2;  
		format = new AudioFormat(encoding, rate, sampleSize,  
				channels, (sampleSize / 8) * channels, rate, bigEndian);  
		//ais1=ais2;
	}
	
	public void run(){
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
		if (!AudioSystem.isLineSupported(info)) {
			return;
		}
		// get and open the target data line for capture.
		try {
			line = (TargetDataLine) AudioSystem.getLine(info);
			line.open(format, line.getBufferSize());
		} catch (LineUnavailableException ex) { 
			return;
		} catch (SecurityException ex) { 
			//shutDown(ex.toString());
			return;
		} catch (Exception ex) { 
			//shutDown(ex.toString());
			return;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int frameSizeInBytes = format.getFrameSize();
		int bufferLengthInFrames = line.getBufferSize() / 8;
		int bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
		System.out.println("bufferLengthInBytes : " + bufferLengthInBytes +" **");
		byte[] data = new byte[bufferLengthInBytes];
		int numBytesRead = 0;
		line.start();
		//System.out.println(line);
		while (thread != null) {
			if((numBytesRead = line.read(data, 0, bufferLengthInBytes)) == -1) {
				break;
			}
			out.write(data, 0, numBytesRead);
		}
		// line.drain();
		// we reached the end of the stream.  stop and close the line.
		line.stop();
		line.close();
		line = null;
		// stop and close the output stream
		try {
			out.flush();
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		// load bytes into the audio input stream for playback
		byte[] audioBytes = out.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(audioBytes);
		ais1 = new AudioInputStream(bais, format, audioBytes.length / format.getFrameSize());
		stoped=true;
	}
	
	public void start() {
		// TODO Auto-generated method stub
		stoped=false;
		thread = new Thread(this);
		thread.setName("Capture");
		thread.run();
	}
	
	public void stop() {
		thread = null;
	}		
}