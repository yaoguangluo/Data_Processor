package imageProcessor;
import java.io.IOException;
public class Emboss {
	public int[][] Processor( int[][] g) throws IOException {		 
		int[][] refG =  new Reflection().PadImage(g, 3, 3);  
		int[]size = {g.length,g[0].length};
		double[][] Gx = new double [size[0]][size[1]];
		int[][] gxk = new int[][]{{ -1,  0,  0},
			{  0,  0,  0},
			{  0,  0,  1}};;
			//GROUP OPERATION
			Gx= new GroupOperator().GO(gxk, refG, size);
			for(int i=0;i<Gx.length;i++){
				for(int j=0;j<Gx[0].length;j++)
				{  
					g[i][j]=(int) (Gx[i][j]/2+127);
				}	  
			}  

			int out[][] = new int[g.length][g[0].length];
			for (int i = 0; i < g.length; i++) {
				for (int j = 0; j < g[0].length; j++) {

					out[i][j]=g[i][j];
				}
			}		
			return  new CheckRange().Processor(out); 
	}	      
}	
