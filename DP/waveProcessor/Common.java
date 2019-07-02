package waveProcessor;


public class Common{
	public double[] zhiShu(double[] input, double scale,double shehold) {
		double [] output= new double [input.length];
		for(int i= 0; i<input.length; i++) {
			output[i]= input[i];
			if(output[i]< shehold)
				for(int j=0; j<scale-1; j++) {
					output[i]*= output[i];
				}	
		}
		return output;
	};/*
	public double[] zhiShu(double[] input, double scale,double shehold) {
		double [] output= new double [input.length];
		for(int i=0;i<input.length;i++) {
			output[i]=input[i];
			if(output[i]<shehold)
			for(int j=0;j<scale-1;j++) {
				output[i] *= output[i];
			}	
		}
		return output;
	};
	 */
}