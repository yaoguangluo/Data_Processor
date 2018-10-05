package movieProcessor;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import org.apache.commons.math.complex.Complex;
import soundProcessor.BytestoAIS;
public class AdataFrame{
	public int seconds;
	public AudioInputStream audioIS;
	public byte[] audioBytes;
	public double[] audioArray;
	public Complex[][] audiofft;
	public AdataFrame next;
	public AdataFrame prev;
	public double[] master;
	public double[] slave;
	public double[] fftarray;
	public double[] fftlooparray;
	public int[][] logfftarray;
	public double[] orge;
	public AdataFrame(){	
	}
	
	public void AUinit(Header header){
		AudioFormat af=new AudioFormat(header.SEn,
				header.SSampleRate , 
				header.SSampleSizeInBits,
				header.SChannels,
				header.SFrameSize,
				header.SFrameRate,
				header.SBigEndian);
		//en /1sample rate /2samplesize /3 channels /4framesize  /5 framrate/bigendianture 
		audioIS=new BytestoAIS().getAIS(audioArray, af, audioIS);
	}
}