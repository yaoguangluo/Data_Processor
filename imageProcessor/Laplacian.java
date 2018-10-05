package imageProcessor;
import java.io.IOException;
public class Laplacian {
	public int[][] Processor( int[][] g) throws IOException {		 
		int[][] refG =  new Reflection().PadImage(g, 3, 3);  
		int[]size = {g.length,g[0].length};
		double[][] Gx = new double [size[0]][size[1]];
		int[][] gxk = new int[][]{{ 0,  1, 0},
			{ 1, -4, 1},
			{ 0,  1, 0}};;
			//GROUP OPERATION
			Gx= new GroupOperator().GO(gxk, refG, size);
			for(int i=0;i<Gx.length;i++){
				for(int j=0;j<Gx[0].length;j++){

					g[i][j]=(int) (Gx[i][j]/8+127);
				}

			}    
			return  new CheckRange().Processor(g); 
	}	      
}	
