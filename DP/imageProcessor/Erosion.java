package imageProcessor;
import imageProcessor.Reflection;
public class Erosion{
	int[][]kernel;
	int[][]GetRegion;
	int [][]tempG;
	private void initTemp(int[][]thisG) {
		tempG=new int[thisG.length][thisG[0].length];
		for(int i=0;i<thisG.length;i++){
			for(int j=0;j<thisG[0].length;j++){
				tempG[i][j]=thisG[i][j];
			}
		}	
	}
	public int[][] Processor(int[][] g, int[][] kenel) {		
		int thisw=g.length;
		int thish=g[0].length;
		int [][]thisG = null;
		if((kenel.length%2==0)&&(kenel[0].length%2==0))
			thisG= new Reflection().PadImage(g, kenel.length+1, kenel[0].length+1); 
		if((kenel.length%2==0)&&(kenel[0].length%2==1))
			thisG= new Reflection().PadImage(g, kenel.length+1, kenel[0].length); 
		if((kenel.length%2==1)&&(kenel[0].length%2==0))
			thisG= new Reflection().PadImage(g, kenel.length, kenel[0].length+1); 
		if((kenel.length%2==1)&&(kenel[0].length%2==1))
			thisG= new Reflection().PadImage(g, kenel.length, kenel[0].length); 
		initTemp(thisG);	
		int w=kenel.length;
		int h=kenel[0].length;
		for(int i=w/2;i<thisw+w/2;i++){
			for(int j=h/2;j<thish+h/2;j++){
				for(int p=0;p<w;p++){
					for(int q=0;q<h;q++){
						if(kenel[p][q]==1){
							if(thisG[i][j]==0){
								tempG[i-w/2+p][j-h/2+q]=0;
							}

						}
					}
				}	
			}
		}     
		return tempG;
	}
}