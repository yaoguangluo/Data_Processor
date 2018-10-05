package imageProcessor;
import imageProcessor.ReadWritePng;

import java.io.IOException;
public class RegionGet {
	int[] pix;
	public int []new_region;
	public int scale;
	public RegionGet(int[][] g) throws IOException{
		int n=0;
		pix=new int[256];
		for (int i=0;i<g.length;i++){
			for(int j=0;j<g[0].length;j++) {
				pix[g[i][j]]++;
			}
		}  
		for(int i=0;i<256;i++){
			if(pix[i]>0){
				n++;
			}
		}
		System.out.println(n);
		new_region=new int[n];
		n=0;
		for(int i = 0; i < 256; i++){
			if(pix[i]>0) {
				new_region[n++] = i;
			}
		}
		System.out.println("value"+n);
		scale=new_region.length-1;
		//return new_region;	  
	}

	public void buildGraph(int[][] g,String output) throws IOException{
		int[][]temp=new int[g.length][g[0].length];
		for(int q=0;q<g.length;q++) {
			for(int p=0;p<g[0].length;p++) { 
				temp[q][p]=g[q][p];
			}
		}
		for(int q=0;q<g.length;q++) {
			for(int p=0;p<g[0].length;p++){
				for(int i=0;i<scale;i++){
					if (temp[p][q]==new_region[i]){
						temp[p][q]=255/scale*i;	
					}
				}
			}
		}
		new ReadWritePng().writePNG(output, temp); 		
	}
}