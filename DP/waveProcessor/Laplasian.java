package waveProcessor;
public class Laplasian{
	@SuppressWarnings("unused")
	public double[] laplasian1d(double[] input, double scale ) {
		double [] output = new double [input.length];
		double []lap=new double[5];
		double sig = scale; //default 1.6
		double t = 0;
		double sumhere = 0;
		lap[0]=0;
		lap[1]=-3;
		lap[2]=scale;//default=7
		lap[3]=-3;
		lap[4]=0;
		for(int l = 0; l<5; ++l){    	    
			sumhere = sumhere + lap[l];
		}	   
		//   System.out.println("--->"+sumhere);	
		//--normalization      
		double sum1=0;
		for(int j=0; j<5; ++j){
			lap[j] = lap[j]/sumhere;
			sum1 = sum1 + lap[j];
		}  
		//--end of producing gaussian matrix
		// System.out.println("gaussian sum: " + sum1);
		double sum=0;
		for(int i=2;i<input.length-2;i++){  
			sum=0;
			for(int j=-2;j<3;j++){
				sum = sum + (input[i+j]* lap[j+2]);	
			}
			output[i]=sum;
		}
		return output;
	};
}