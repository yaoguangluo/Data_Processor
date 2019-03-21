package matrixProcessor;
public class CnnMeasure{
	public double[][] getCnnMeansure(double inputs[][]){
		double[][] output = new double[inputs.length][inputs[0].length];
		double[] Kernel= new double[inputs[0].length];
		for(int j = 0; j<inputs[0].length;j++) {
			double sum=0;
			for(int k = 0; k<inputs.length;k++) {
				sum+=inputs[k][j];
			}
			Kernel[j] = sum;
		}
		for(int i = 0; i<inputs.length;i++) {
			for(int j = 0; j<inputs[0].length;j++) {
				output[i][j] = inputs[i][j]/Kernel[j];
			}
		}
		return output;
	}
}