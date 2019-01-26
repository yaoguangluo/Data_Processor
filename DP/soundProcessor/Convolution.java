package soundProcessor;
import javax.sound.sampled.AudioFormat;
public class Convolution{
	public Convolution(){
	}
	public double medGet(int[] audioData, AudioFormat af, int w, int h, int frames_per_pixel){
		int numChannels = af.getChannels();
		int idx=0;
		byte my_byte;
		double count=0;
		for (double x = 0; x < w && audioData != null; x++) {
			idx = (int) (frames_per_pixel * numChannels * x);
			if (af.getSampleSizeInBits() == 8)  {
				my_byte = (byte) audioData[idx];
			} else {
				my_byte = (byte) (128 * audioData[idx] / 32768 );
			}
			double y_new = (double) (h * (128 - my_byte) / 256);
			count+=y_new;
		}
		count=count/w;
		if(count>((double)(int)count+0.5)){
			count=(int)count+1;
		}
		System.out.println(count);
		return count;
	}
}