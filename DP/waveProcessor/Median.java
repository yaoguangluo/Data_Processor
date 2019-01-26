package waveProcessor;
public class Median{
	public double[] median1d(double[] input, double scale ) {
		double [] output = new double [input.length];
		for(int i = (int)scale;i < input.length-scale; i++) {
			double sum = input[i];
			for(int j = 1;j < scale; j++) {
				sum += input[i + j];
				sum += input[i - j];
			}
			sum/=scale*2+1;
			output[i]=sum;		;
		}
		return output;
	};
}