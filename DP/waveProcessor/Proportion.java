package waveProcessor;
public class Proportion{
	public double[] newX(double[] input, double width) {//128 32
		double [] output= new double [(int)width];
		double bilix= input.length/ width;//4
		for(int i= 0; i< output.length; i++) {
			for(int j= 0;j< bilix; j++) {
				output[i]+= input[(int)(i*bilix+j)];	
			}
		}
		return output;
	};

	public double[] newY(double[] input, double hight) {
		double [] output= new double [input.length];
		double max= new waveProcessor.MaxAndMin().max_v(input);
		double biliy=  hight/ max;
		for(int i=0; i<output.length; i++) {
			output[i]= input[i]* biliy;
		}
		return output;
	};

	public double[] newXY(double[] input, double width, double hight ) {
		double [] output= new double [(int)width];
		double max= new waveProcessor.MaxAndMin().max_v(input);
		double biliy=  hight/max;
		double bilix=  input.length/width;
		for(int i=0; i<output.length; i++) {
			output[i]= input[(int)(i* bilix)]* biliy;
		}
		return output;
	};

	public double[] newXYBest(double[] input, double width, double hight ) {
		double [] output= new double [(int)width];
		double max= new waveProcessor.MaxAndMin().max_v(input);
		double biliy=  hight/ max;
		double bilix=  width/ input.length;
		for(int i=0; i< input.length- 1; i++) {
			double dc= (input[i+ 1]- input[i])/ bilix;
			for(int j=0; j<bilix; j++) {
				output[(int)(i* bilix)+ j]= (input[i]+ dc* j)* biliy;
			}
		}
		return output;
	};

	public double[] newYwithoutBound(double[] input, double hight) {
		double [] output= new double [input.length];
		for(int i= 0; i< output.length; i++) {
			output[i]= input[i]* hight;
		}
		return output;
	};

	public double[] newXYYwithoutBound(double[] input, double width, double hight ) {
		double[] output= new double [(int)width];
		double bilix=  input.length/ width;
		for(int i= 0; i< output.length; i++) {
			output[i]= input[(int)(i* bilix)]* hight;
		}
		return output;
	};
}