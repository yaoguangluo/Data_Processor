package imageProcessor;
public class GroupOperator {
	public double[][] GO(int[][] kener,int[][] refG,int[] size ){
		double[][] Imagex=  new double[size[0]][size[1]];
		double sum;
		int filterHeight=kener.length;
		int filterWidth= kener[0].length;
		for(int i = filterHeight/2, p = 0; i<refG.length-(int)filterHeight/2; ++i, ++p){
			for(int j = filterWidth/2,q = 0; j<refG[0].length-(int)filterWidth/2; ++j, ++q){
				sum = 0;
				for(int k = -filterHeight/2; k<=filterHeight/2; ++k){
					for(int I = -filterWidth/2; I<=filterWidth/2; ++I){
						sum = sum + (refG[i + k][j + I] * kener[k + filterHeight/2][I + filterWidth/2]);
					}
				}
				Imagex[p][q] =sum;
			}
		}
		return Imagex;
	}	
}