package waveProcessor;
public class Copy{
	public double[][] copy2d(double[][] input,double scale) {
		double[][] output = new double[(int)scale][input[0].length];
		for(int i=0;i<scale;i++) {
			for(int j=0;j<input[0].length;j++) {
				output[i][j]=input[i][j];
			}
		}
		return output;
	};

	public double[]copy1d(double[] input,double scale) {
		double[] output = new double[(int)scale];
		for(int i=0;i<scale;i++) {
			output[i]=input[i];	
		}
		return output;
	};

	public double[]copy1dx2(double[] input,double scale) {
		double[] output = new double[(int)(scale*input.length)];
		for(int i=0;i<scale;i++) {
			for(int j=0;j<input.length;j++) {
				output[i*input.length+j]=input[j];	
			}
		}
		return output;
	};
}