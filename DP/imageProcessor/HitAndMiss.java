package imageProcessor;
import java.io.IOException;

import imageProcessor.Reflection;
public class HitAndMiss{
	int[][]kernel;
	int[][]GetRegion;
	int[][]tempG;
	private void initTemp(int[][]thisG) {
		tempG=new int[thisG.length][thisG[0].length];
		for(int i=0;i<thisG.length;i++){
			for(int j=0;j<thisG[0].length;j++){
				tempG[i][j]=0;
			}
		}		
	}
	public int[][] Processor(int[][] g, int pix, int[][] kenel) throws IOException{
		int [][]g1=new int[g.length][g[0].length];
		for(int i=0;i<g.length;i++){
			for(int j=0;j<g[0].length;j++){
				g1[i][j]=g[i][j];
			}
		}
		int thisw=g.length;
		int thish=g[0].length;
		int w=kenel.length;
		int h=kenel[0].length;
		int [][]thisG = null;
		if((kenel.length%2==0)&&(kenel[0].length%2==0))
			thisG= new Reflection().PadImage(g1, kenel.length+1, kenel[0].length+1); 
		if((kenel.length%2==0)&&(kenel[0].length%2==1))
			thisG= new Reflection().PadImage(g1, kenel.length+1, kenel[0].length); 
		if((kenel.length%2==1)&&(kenel[0].length%2==0))
			thisG= new Reflection().PadImage(g1, kenel.length, kenel[0].length+1); 
		if((kenel.length%2==1)&&(kenel[0].length%2==1))
			thisG= new Reflection().PadImage(g1, kenel.length, kenel[0].length); 

		for(int i=0;i<thisG.length;i++) {
			for(int j=0;j<thisG[0].length;j++){
				if (thisG[i][j]!=pix){
					thisG[i][j]=0; // filter
				}
			}
		}
		initTemp(thisG);	
		for(int i=w/2;i<thisw+w/2;i++){
			for(int j=h/2;j<thish+h/2;j++){
				int last=0;
				for(int p=0;p<w;p++){
					for(int q=0;q<h;q++){
						int temp = 0;
						if(kenel[p][q]==1){
							if(thisG[i-w/2+p][j-h/2+q]>0){
								temp=1;
							}
						}
						if(kenel[p][q]==0){
							if(thisG[i-w/2+p][j-h/2+q]==0){
								temp=1;
							}
						}
						if(kenel[p][q]==-1){
							temp=1;		
						}
						last=last+temp;
					}
				}
				if (last==w*h){
					tempG[i][j]=thisG[i][j];
				}
			}	        	
		}
		return tempG;
	}
}