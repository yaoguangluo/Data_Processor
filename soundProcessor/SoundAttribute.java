package soundProcessor;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
public class SoundAttribute{
	public SoundAttribute(){
	}
	public void printAttribute(AudioInputStream ais){
		long framelength= ais.getFrameLength();
		System.out.println("frame length : " + framelength);

		AudioFormat af = ais.getFormat();
		//DataLine.Info info = new DataLine.Info(SourceDataLine.class, af);
		int frameRate = (int)af.getFrameRate();
		System.out.println("Frames per second: " + frameRate);

		int sampleRate=(int)af.getSampleRate();
		System.out.println("Sample Rate: " + sampleRate);

		int frameSize = af.getFrameSize();
		System.out.println("each Frame Size: " + frameSize);

		int channels = af.getChannels();
		System.out.println("Channels : " + channels);

		int bps = af.getSampleSizeInBits();
		System.out.println("Bits per sample : " + bps);

		double times;
		long milliseconds = (long)((ais.getFrameLength() * 1000) / ais.getFormat().getFrameRate());
		times = milliseconds / 1000.0;
		System.out.println("Duration of the songs : " + times +" seconds");
	}
}