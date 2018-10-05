package imageProcessor;
public class Threshold {
	public int[][]  Processor (int[][] g,int Td){
		// -- prepare output image for subtract the mean
		for (int i = 0; i <g.length; ++i) {
			for (int j = 0; j < g[0].length; ++j) {
				if( g[i][j]>Td){		
					g[i][j]=255;
				}
				else{
					g[i][j] = 0; // sho	
				}
			}
		}
		return g;
	}
}