package imageProcessor;
import java.awt.image.BufferedImage;
import java.io.IOException;
public class Median {
	public int[][] Processor(int[][] g,int d,int e) throws IOException {
		int[][] refG =  new Reflection().PadImage(g, d, e);  
		int[] neib=new int[d*e];//for sort
		for(int i=d/2;i<g.length+d/2;i++){
			for(int j=e/2;j<g[0].length+e/2;j++) {   
				int q=0;
				for(int k=0;k<d;k++) {
					for(int l=0;l<e;l++) {
						//find 3*3
						neib[q++]=refG[i+k-d/2][j+l-e/2];
						//sort 3*3 bbsort
					}}
				for(int o=0;o<9;o++) {
					for(int p=0;p<9;p++){ 
						if (neib[o] > neib[p]) {
							int temp = neib[o];  
							neib[o] = neib[p];  
							neib[p] = temp;  
						}  
					}
				}
				//get median
				g[i-d/2][j-e/2]=neib[(d*e)/2];// 4 is middle valueof sort 9  
			}
		}
		return  new CheckRange().Processor(g); 	  
	}

	public BufferedImage Processor(BufferedImage lygimage, int d, int e) throws IOException {
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