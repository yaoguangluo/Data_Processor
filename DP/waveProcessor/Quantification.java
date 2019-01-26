package waveProcessor;
public class Quantification{
	public double[] liangHuaDengChaAdd(double[] input, double scale) {
		double[] output = new double[input.length];
		double sum = input.length/scale;
		for(int i=0;i<sum-1;i++) {
			double temp=0;
			for(int j=0;j<scale;j++) {
				temp+=input[(int)(i*scale+j)];
			}
			output[(int)(i*scale)]=temp;
		}

		return output;
	};

	public double[] liangHuaXiHua(double[] input,double scale) {
		double[] output = new double[input.length];
		double sum= input.length;
		int find=0;
		for(int i=1;i<=scale;i++) {
			if(input[0]>input[i]) {
				find+=1;	
			}
		}	
		if(find==scale) {
			output[0]=input[0];
		}
		for(int i=(int)(scale);i<(sum-(scale));i++) {
			find=0;
			for(int j=1;j<=scale;j++) {
				if(input[i]>input[i+j]) {
					find+=1;
				}
				if(input[i]>input[i-j]) {
					find+=1;
				}
			}
			if(find==scale*2) {
				output[i]=input[i];
			}

		}
		return output;
	}

	@SuppressWarnings("unused")
	public double[] liangHuaDengChaMines(double[] input, int scale) {
		double[] output = new double[input.length];
		double sum = input.length/scale;
		for(int i=0;i<sum-1;i++) {
			double temp=0;
			double max=input[(int)(i*scale)];
			double maxi=i*scale;
			for(int j=0;j<scale;j++) {
				if(input[(int)(i*scale+j)]>max) {
					max=input[(int)(i*scale+j)];
					maxi=(int)(i*scale+j);
				}
			}
			output[(int)(i*scale)] = max;
		}

		return output;
	}

	public double[] liangHuaEqualDelete(double[] input) {
		double[] output = new double[input.length];
		double pre=0;
		double next=0;
		for(int i=0;i<input.length;i++) {
			next=input[i];
			if(next!=pre) {
				output[i]=input[i];
			}else {
				output[i]=0;
			}
			pre=next;
		}
		// TODO Auto-generated method stub
		return output;
	}

	public double[] liangHuaXiHuaHalfSide(double[] input) {
		// TODO Auto-generated method stub
		double[] output = new double[input.length];
		for(int i=1;i<input.length-1;i++)
		{
			if(input[i]/input[i+1]<=1&&input[i]/input[i+1]>=0.5) {
				if(input[i]/input[i-1]<=1&&input[i]/input[i-1]>=0.5) {
					output[i]=input[i];
				}	
			}

		}
		return output;
	}



























	
	
	
	
	

}