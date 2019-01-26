package imageProcessor;
public class Closing {
	public int[][] Processor(int[][]g,int[][]kenel){
		int[][] closing=new Dilation().Processor(g, kenel);
		closing= new Erosion().Processor(closing,kenel);
		return closing;
	}
}
