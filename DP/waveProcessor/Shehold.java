package waveProcessor;
public class Shehold{
	public double[] shehold(double[] input, double scale) {
		double[] output= new double[input.length];
		for(int i= 0;i< input.length; i++) {
			if(input[i]> scale) {
				//output[i]=input[i];
				output[i]= 100;
			}
		}
		return output;
	};

	public double[] shehold(double[] input, double scale, int destination) {
		double[] output= new double[input.length];
		for(int i= 0;i< input.length; i++) {
			if(input[i]> scale) {
				//output[i]=input[i];
				output[i]= destination;
			}
		}
		return output;
	};
}