package imageProcessor;
import java.awt.image.BufferedImage;
import java.io.IOException;
public class Strech {
	public int[][] Processor(int[][] g,double d,double e) throws IOException {
		int histgram[] = new int[256];
		for (int i = 0; i < g.length; i++) {
			for (int j = 0; j < g[0].length; j++) {
				++histgram[g[i][j]];
			}
		}
		float p = 0;
		int br = 0,dr = 0;
		for(int i=0;i<256;i++) {
			p=p+histgram[i];
			if(p > d* g.length*g[0].length){
				dr=i;
				break;
			}
		}
		System.out.println("dr:"+dr);
		p = 0;
		for(int i=0;i<256;i++){
			p=p+histgram[i];
			if(p > e* g.length*g[0].length){
				br=i;
				break;
			}
		}
		System.out.println("br:"+br);
		int out[][] = new int[g.length][g[0].length];
		for (int i = 0; i < g.length; i++) {
			for (int j = 0; j < g[0].length; j++) {
				out[i][j]=(g[i][j]-dr)*255/(br-dr);
			}
		}
		return  new CheckRange().Processor(out);   
	}
	public BufferedImage Processor(BufferedImage lygimage, double d, double e) throws IOException {
		//image to r[][] g[][] b[][]
		//r[][]
		//g[][]
		//b[][]
		//r[][]g[][]b[][] to image
		//image to r[][] g[][] b[][]
		int r[][]=new ReadWritePng().REDpngRead(lygimage);
		int g[][]=new ReadWritePng().GRNpngRead(lygimage);
		int b[][]=new ReadWritePng().BLUpngRead(lygimage);
		//r[][]
		r=Processor(r,d,e); 
		//g[][]
		g=Processor(g,d,e); 
		//b[][]
		b=Processor(b,d,e); 
		//r[][]g[][]b[][] to image
		lygimage=new ReadWritePng().createBufferImage(r,g,b);
		return lygimage;
	}
}