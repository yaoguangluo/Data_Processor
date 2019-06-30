package imageProcessor;
/*
 * Graph: Vincent Boucher refer https://www.linkedin.com/feed/hashtag/?keywords=%23PatternRecognition
 * Theory Development: Yaoguang Luo
 * For way one without test. ATTENSION PLEASE!!!!!!!!
 * */
public class LayerWiseRelevencePropagation{
	public int[][] forwardPassMask(int [][]input, int range, int scale){
		int[][] output = new int[input[0].length][input.length];
		for(int x= 0; x< input[0].length; x++) {
			for(int y= 0; y < input.length; y++) {
				int sum= 0;
				for(int z= -range/2; z< range/2; z++) {
					sum+= input[x+ z][y+ 1];
				}
				if(sum/ scale> input[x][y]) {
					output[x][y] = 255;
				}
			}
		}
		return output;
	}
	public int[][] backwardPassMask(int [][]input, int range, int scale){
		int[][] output= new int[input[0].length][input.length];
		for(int x= 0; x< input[0].length; x++) {
			for(int y= input.length - 1; y > 0; y--) {
				int sum= 0;
				for(int z= -range/2; z< range/2; z++) {
					sum+= input[x+ z][y- 1];
				}
				if(sum/scale > input[x][y]) {
					output[x][y]= 255;
				}
			}
		}
		return output;
	}
}