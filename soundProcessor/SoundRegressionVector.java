package soundProcessor;
import java.awt.Dimension;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
import java.util.Vector;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
public class SoundRegressionVector{
	Vector<Double> lines= new Vector<Double>();
	byte[] audioBytes;
	@SuppressWarnings("rawtypes")
	public Vector getVectorLines(AudioInputStream ais){
		lines.removeAllElements();  // clear the old vector
		AudioFormat af = ais.getFormat();
		audioBytes=null;
		if (audioBytes == null) {
			try {
				audioBytes = new byte[(int) (ais.getFrameLength() *af.getFrameSize())];
				System.out.println("audiobyte length:"+audioBytes.length);
				ais.read(audioBytes);
			} catch (Exception ex) { 
				//reportStatus(ex.toString());
				return null; 
			}
		}
		Dimension d = new Dimension(800,600);
		int w = d.width;
		int h = d.height-15;
		int[] audioData = null;
		if (af.getSampleSizeInBits() == 16) {
			int nlengthInSamples = audioBytes.length / 2;
			audioData = new int[nlengthInSamples];
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
			audioData = new int[nlengthInSamples];
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
		int idx=0;
		int numChannels = af.getChannels();
		double med= new Convolution().medGet(audioData,af,w,h,frames_per_pixel);
		for (double x = 0; x < w && audioData != null; x+=3) {
			idx = (int) (frames_per_pixel * numChannels * x);
			if (af.getSampleSizeInBits() == 8) {
				my_byte = (byte) audioData[idx];
			} else {
				my_byte = (byte)(128 * audioData[idx]/32768);
			}
			double y_new = (double)(h*(128-my_byte)/256);
			lines.add(new Line2D.Double(x, med, x,y_new));
		}
		return lines;
	}
}