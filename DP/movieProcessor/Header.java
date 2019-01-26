package movieProcessor;
import javax.sound.sampled.AudioFormat.Encoding;
public class Header{
	public int MWFrame;
	public int MHFrame;
	public int MFrameLeangth;
	public int MFrameRate;
	//en /1sample rate /2samplesize /3 channels /4framesize  /5 framrate/bigendianture 
	public Encoding SEn;
	public float SSampleRate;
	public int SSampleSizeInBits;
	public int SChannels;
	public int SFrameSize;
	public float SFrameRate;
	public boolean SBigEndian;
	public long SFrameLeangth;
	public long MovPos;
	public long SWavPos;
	public long SWavCurrentPos;
	public long SMovCurrentPos;
	public Header(){	
		MWFrame=0;
		MHFrame=0;
		MFrameLeangth=0;
		MFrameRate=0;
		//en /1sample rate /2samplesize /3 channels /4framesize  /5 framrate/bigendianture 
		SEn=null;
		SSampleRate=0;
		SSampleSizeInBits=0;
		SChannels=0;
		SFrameSize=0;
		SFrameRate=0;
		SBigEndian=false;
		SFrameLeangth=0;
		SWavPos=0;
		MovPos=0;
		SWavCurrentPos=0;
		SMovCurrentPos=0;
	}
}