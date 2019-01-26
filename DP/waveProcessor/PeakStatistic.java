package waveProcessor;
public class PeakStatistic{
	public double[][] fengTong1(double[] input) {
		double[][] output = new double[input.length][2];
		for(int i=0; i<input.length; i++) {
			output[i][0] = i;
			output[i][1] = input[i];
		}
		return output;
	}

	public double[][] fengPaixX(double[][] input) {
		double[][] output =new waveProcessor.Copy().copy2d(input,input.length);
		for(int i = 0; i < input.length; i++) {
			for(int j = 0; j < input.length; j++) {
				if(output[i][0]<output[j][0]) {
					double tempc[]=new double [2];
					tempc[0]=output[i][0];
					tempc[1]=output[i][1];
					output[i][0]=output[j][0];
					output[i][1]=output[j][1];
					output[j][0]=tempc[0];
					output[j][1]=tempc[1];
				}
			}
		}
		return output;
	}

	public double[][] fengPaiyY(double[][] input) {
		double[][] output =new waveProcessor.Copy().copy2d(input,input.length);
		for(int i = 0; i < input.length; i++) {
			for(int j = 0; j < input.length; j++) {
				if(output[i][1]<output[j][1]) {
					double tempc[]=new double [2];
					tempc[0]=output[i][0];
					tempc[1]=output[i][1];
					output[i][0]=output[j][0];
					output[i][1]=output[j][1];
					output[j][0]=tempc[0];
					output[j][1]=tempc[1];
				}
			}
		}
		return output;
	}

	public double[][] fengPaiXx(double[][] input) {
		double[][] output =new waveProcessor.Copy().copy2d(input,input.length);
		for(int i = 0; i < input.length; i++) {
			for(int j = 0; j < input.length; j++) {
				if(output[i][0]>output[j][0]) {
					double tempc[]=new double [2];
					tempc[0]=output[i][0];
					tempc[1]=output[i][1];
					output[i][0]=output[j][0];
					output[i][1]=output[j][1];
					output[j][0]=tempc[0];
					output[j][1]=tempc[1];
				}
			}
		}
		return output;
	}

	public double[][] fengPaiYy(double[][] input) {
		double[][] output =new waveProcessor.Copy().copy2d(input,input.length);
		for(int i = 0; i < input.length; i++) {
			for(int j = 0; j < input.length; j++) {
				if(output[i][1]>output[j][1]) {
					double tempc[]=new double [2];
					tempc[0]=output[i][0];
					tempc[1]=output[i][1];
					output[i][0]=output[j][0];
					output[i][1]=output[j][1];
					output[j][0]=tempc[0];
					output[j][1]=tempc[1];
				}
			}
		}
		return output;
	}
}