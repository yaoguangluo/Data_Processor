package soundProcessor;
import java.io.ByteArrayInputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
public class BytestoAIS{
	public AudioInputStream getAIS(int[] guassian2,AudioInputStream ais) {
		// TODO Auto-generated method stub
		//1:guassian to bytes
		AudioFormat af=ais.getFormat();
		byte[]audioBytes=new byte[guassian2.length*2];
		if (af.getSampleSizeInBits() == 16) {
			audioBytes=new byte[guassian2.length*2];
			if (af.isBigEndian())  {
				for (int i = 0; i < guassian2.length; i++)  {
					//
					audioBytes[2*i]=(byte)((int)guassian2[i]>>8);
					audioBytes[2*i+1]= (byte)((int)guassian2[i]);
					//
				}
			} else {
				for (int i = 0; i < guassian2.length; i++)  {
					//System.out.println(i+"*"+guassian2.length);
					audioBytes[2*i]=(byte)((int)guassian2[i]);
					audioBytes[2*i+1]= (byte)((int)guassian2[i]>>8);          
				}
			}
		} else if (af.getSampleSizeInBits() == 8)  {
			audioBytes=new byte[guassian2.length];
			if (af.getEncoding().toString().startsWith("PCM_SIGN"))  {
				for (int i = 0; i < guassian2.length; i++)  {
					audioBytes[i]=(byte) guassian2[i];
				}
			} else {
				for (int i = 0; i < guassian2.length; i++) {
					audioBytes[i]=(byte) ((byte) guassian2[i]+128);
				}
			}
		}
		//2:bytes to adataFrame
		System.out.println("now length-->"+audioBytes.length);
		ByteArrayInputStream bais = new ByteArrayInputStream(audioBytes); 
		ais = new AudioInputStream(bais, ais.getFormat(), audioBytes.length / af.getFrameSize());
		return ais;
	}

	public AudioInputStream getAIS(double[] guassian2,AudioFormat af,AudioInputStream ais) {
		// TODO Auto-generated method stub
		//1:guassian to bytes
		//AudioFormat af=adataFrame.getFormat();
		byte[]audioBytes=new byte[guassian2.length*2];
		if (af.getSampleSizeInBits() == 16) {
			audioBytes=new byte[guassian2.length*2];
			if (af.isBigEndian()) {
				for (int i = 0; i < guassian2.length; i++) {
					//
					audioBytes[2*i]=(byte)((int)guassian2[i]>>8);
					audioBytes[2*i+1]= (byte)((int)guassian2[i]);
					//
				}
			} else {
				for (int i = 0; i < guassian2.length; i++) {
					//System.out.println(i+"*"+guassian2.length);
					audioBytes[2*i]=(byte)((int)guassian2[i]);
					audioBytes[2*i+1]= (byte)((int)guassian2[i]>>8);          
				}
			}
		} else if (af.getSampleSizeInBits() == 8)  {
			audioBytes=new byte[guassian2.length];
			if (af.getEncoding().toString().startsWith("PCM_SIGN"))  {
				for (int i = 0; i < guassian2.length; i++)  {
					audioBytes[i]=(byte) guassian2[i];
				}
			} else {
				for (int i = 0; i < guassian2.length; i++)  {
					audioBytes[i]=(byte) ((byte) guassian2[i]+128);
				}
			}
		}
		//2:bytes to adataFrame
		System.out.println("now length-->"+audioBytes.length);
		ByteArrayInputStream bais = new ByteArrayInputStream(audioBytes);
		ais = new AudioInputStream(bais, af, audioBytes.length / af.getFrameSize());
		return ais;
	}

	public AudioInputStream getAIS(double[] guassian2,AudioInputStream ais) {
		// TODO Auto-generated method stub
		//1:guassian to bytes
		AudioInputStream ais1;
		AudioFormat af=ais.getFormat();
		byte[]audioBytes=new byte[guassian2.length*2];
		if (af.getSampleSizeInBits() == 16)  {
			audioBytes=new byte[guassian2.length*2];
			if (af.isBigEndian())  {
				for (int i = 0; i < guassian2.length; i++)  {
					//
					audioBytes[2*i]=(byte)((int)guassian2[i]>>8);
					audioBytes[2*i+1]= (byte)((int)guassian2[i]);
					//
				}
			} else 	{
				for (int i = 0; i < guassian2.length; i++) {
					//System.out.println(i+"*"+guassian2.length);
					audioBytes[2*i]=(byte)((int)guassian2[i]);
					audioBytes[2*i+1]= (byte)((int)guassian2[i]>>8);          
				}
			}
		} else if (af.getSampleSizeInBits() == 8)  {
			audioBytes=new byte[guassian2.length];
			if (af.getEncoding().toString().startsWith("PCM_SIGN"))  {
				for (int i = 0; i < guassian2.length; i++)  {
					audioBytes[i]=(byte) guassian2[i];
				}
			} else 	{
				for (int i = 0; i < guassian2.length; i++)  {
					audioBytes[i]=(byte) ((byte) guassian2[i]+128);
				}
			}
		}
		//2:bytes to adataFrame
		System.out.println("now length-->"+audioBytes.length);
		ByteArrayInputStream bais = new ByteArrayInputStream(audioBytes); 
		ais1 = new AudioInputStream(bais, ais.getFormat(), audioBytes.length / af.getFrameSize());
		return ais1;
	}

	public AudioInputStream getAIS(byte[] audioBytes, AudioFormat af,
			AudioInputStream audioIS) {
		ByteArrayInputStream bais = new ByteArrayInputStream(audioBytes);
		audioIS = new AudioInputStream(bais, af, audioBytes.length / af.getFrameSize());
		return audioIS;
	}
}