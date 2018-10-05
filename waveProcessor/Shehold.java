package waveProcessor;
public class Shehold{
	public double[] shehold1(double[] input, double scale) {
		double [] output= new double [input.length];
		for(int i=0;i<input.length;i++) {
			if(input[i]>scale) {
				//output[i]=input[i];
				output[i]=100;
			}
		}
		return output;
	};
}