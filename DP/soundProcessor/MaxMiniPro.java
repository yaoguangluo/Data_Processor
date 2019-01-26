package soundProcessor;
import java.awt.Dimension;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
import java.util.Vector;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
public class MaxMiniPro{
		 Vector <Double> lines = new Vector<Double>();
		 byte[] audioBytes;
		 public double []guassian;
		 AudioFormat af;
		 AudioInputStream ais;
		 public double[] audioData;
         @SuppressWarnings("rawtypes")
		public Vector getVectorLines(AudioInputStream ais1){
         ais=ais1;
         lines.removeAllElements();  // clear the old vector
         af = ais.getFormat();
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
         if (af.getSampleSizeInBits() == 16) {
              int nlengthInSamples = audioBytes.length / 2;
              audioData = new double[nlengthInSamples];
              if (af.isBigEndian())   {
                 for (int i = 0; i < nlengthInSamples; i++)  {
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
          } else if (af.getSampleSizeInBits() == 8)  {
              int nlengthInSamples = audioBytes.length;
              audioData = new double[nlengthInSamples];
              if (af.getEncoding().toString().startsWith("PCM_SIGN"))  {
                  for (int i = 0; i < audioBytes.length; i++)  {
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
         int count = 0;
         int comp = 2000;
         int find = 0;
         int max = 1;
         for(int i = 0;i<audioData.length;i++) {  
        	 if(audioData[i]<0&&audioData[i+1]>0){
        		 if(find==0) {
        			 find=0;
        			 for(int j=0;j<count;j++) {
        			   audioData[i-j]=0;
        			 }
        		 }
        		 if(find==1) {
        			 find=0;
        			 for(int j=0;j<count;j++) {
        			   audioData[i-j]=audioData[i-j]*max;
        			 }
        		 }
        		 //System.out.println(count);
        		 count = 0;
             }
        	 count++;
        	 if(audioData[i] > comp||audioData[i] < -comp) {
        		 find=1;
        	 }
         }
         count=0;
         for(int i=0 ; i<audioData.length ; i++) {  
        	 if(audioData[i]<0 && audioData[i+1]>0) {
        		 System.out.println("cur count"+count);
        		 if(count<5||count>200)	 {
        			 for(int j=0;j<count;j++) {
        				 audioData[i-j]=0; 
        			 }			 
        		 }
        		 count=0;
             }    	 
        	 count++;
         }
         double y_last = 0;
         System.out.println(w);
         System.out.println(audioData.length);
         int frames_per_pixel = audioBytes.length / af.getFrameSize()/w;
         byte my_byte = 0;
         int idx=0;
         int numChannels = af.getChannels();
         for (double x = 0; x < w && audioData != null; x++)  {
             idx = (int) (frames_per_pixel * numChannels * x);
             if (af.getSampleSizeInBits() == 8)  {
                  my_byte = (byte) audioData[idx];
             } else 	{
                  my_byte = (byte)(128*audioData[idx]/32768);
             	}
             double y_new = (double)(h*(128-my_byte)/256);
             lines.add(new Line2D.Double(x, y_last,x,y_new));
             y_last=y_new;
         }
         return lines;
     }
}