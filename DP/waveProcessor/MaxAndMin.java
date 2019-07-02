package waveProcessor;
public class MaxAndMin{
	public double max_v(double[] input) {
		double max= 0;
		for(int i= 0; i< input.length; i++) {
			if(input[i]> max) {
				max= input[i];
			}
		}
		return max;
	};

	public double max_i(double[] input) {
		double max= 0;
		for(int i= 0; i<input.length; i++) {
			if(input[i]> max) {
				max= i;
			}
		}
		return max;
	};

	@SuppressWarnings("unused")
	public double min_v(double[] input,double rank) {
		double min= 999999999;
		double[][] fengtong= new waveProcessor.PeakStatistic().fengTong1(input);	
		for(int i= 0; i<input.length; i++) {
			if(input[i]< min) {
				min= input[i];
			}
		}
		return min;
	};

	public double min_i(double[] input, double rank) {
		double min= 999999999;
		for(int i= 0; i< input.length; i++) {
			if(input[i]< min) {
				min= i;
			}
		}
		return min;
	};
}