package waveProcessor;
public class Tailor{
	public double[] caiJian1(double[] input, double left,double right) {
		double w= (int)(right-left);
		double [] output= new double [(int)w];
		for(int i=(int)left;i<right;i++) {
			output[(int)(i-left)]=input[i];
		}
		return output;
	};
}