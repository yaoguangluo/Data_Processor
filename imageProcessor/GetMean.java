package imageProcessor;
public class GetMean {
	public double print_Mean(int[][] outDIR) {
		double sum = 0;
		double meanValue = sum;
		for(int i=0;i<outDIR.length;i++){
			for(int j=0;j<outDIR[0].length;j++){
				sum += outDIR[i][j];	 
			}
		}
		meanValue = sum/( outDIR.length * outDIR[0].length );
		return meanValue;
	}
	public double print_Mean(double[][] outDIR){
		double sum = 0;
		double meanValue = sum;
		for(int i=0;i<outDIR.length;i++){
			for(int j=0;j<outDIR[0].length;j++){
				sum += outDIR[i][j];	 
			}
		}
		meanValue = sum/( outDIR.length * outDIR[0].length );
		return meanValue;
	}


}