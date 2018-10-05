package imageProcessor;
import java.io.IOException;
public class Mask {
	public int[][] Processor(int[][] mag,int[][]dir) throws IOException {
		for(int i =0;i< mag.length;i++){
			for(int j =0;j< mag[0].length;j++){
				if (mag[i][j]==0){
					dir[i][j]=0;
				}
			}
		}	
		return dir;		  
	}
}