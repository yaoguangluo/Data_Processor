package imageProcessor;
import java.io.IOException;
public class CheckRange {
	//因为g变量是算子，所以就不做修正。
	public int[][] Processor(int[][] g) throws IOException {		 	
		for(int i= 0;i< g.length; i++){
			for(int j= 0; j< g[0].length; j++){   		
				if(g[i][j]< 0){ 
					g[i][j]= 0;
				}
				if (g[i][j]> 255){ 
					g[i][j]= 255;
				}
			}
		}
		return g; 
	}	      
}	
