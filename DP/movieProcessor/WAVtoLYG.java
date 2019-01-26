package movieProcessor;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import soundProcessor.SoundWaveVector;
public class WAVtoLYG{
	@SuppressWarnings({ "rawtypes", "unused" })
	public WAVtoLYG(String WAVf, String LYGf) throws IOException, UnsupportedAudioFileException{
		LYGFileIO IO = new LYGFileIO();
		IO.reset();
		//IO.creat();
		IO.header=new Header();
		IO.adataFrame=new AdataFrame();
		//IO.WAVRead("C:\\Users\\yaoguang\\Desktop\\study\\sound\\AHag.wav");
		//File F=new File("C:\\Users\\yaoguang\\Desktop\\study\\sound\\AHag.wav");
		File F=new File(WAVf);
		System.out.println(F.length());
		AudioInputStream ais=AudioSystem.getAudioInputStream(F);
		//get header
		IO.header.SBigEndian=ais.getFormat().isBigEndian();
		IO.header.SChannels=ais.getFormat().getChannels();
		IO.header.SEn=ais.getFormat().getEncoding();
		IO.header.SFrameRate=ais.getFormat().getFrameRate();
		IO.header.SFrameSize=ais.getFormat().getFrameSize();
		IO.header.SSampleRate=ais.getFormat().getSampleRate();
		IO.header.SSampleSizeInBits=ais.getFormat().getSampleSizeInBits();
		IO.header.SFrameLeangth=ais.getFrameLength();
		//get array
		//time
		double times;
		long milliseconds = (long)((IO.header.SFrameLeangth * 1000) / IO.header.SFrameRate);
		times = milliseconds / 1000.0;
		//
		if((int)times<times){
			times=(int)(times+1);
		}
		//loop store main array to sub array
		SoundWaveVector sv = new SoundWaveVector();
		Vector lines = sv.getVectorLines(ais,IO.header.SFrameRate);
		IO.adataFrame.audioArray = sv.audioData;
		for(int i=0;i<times-1;i++){
			IO.adataFrame.next = new AdataFrame();
			IO.adataFrame.next.prev = IO.adataFrame;
			IO.adataFrame = IO.adataFrame.next;
			sv = new SoundWaveVector();
			lines = sv.getVectorLines(ais,IO.header.SFrameRate);
			IO.adataFrame.audioArray = sv.audioData;
		}
		//out
		IO.toHead();
		IO.lygWrite(LYGf);
		IO.reset();
	}
}