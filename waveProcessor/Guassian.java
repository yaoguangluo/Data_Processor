package waveProcessor;

public class Guassian{
	public double[] guassian1d(double[] input, double scale ) {
		double [] output = new double [input.length];
		double []gua=new double[5];
		double sig = scale; //default 1.6
		double t = 0;
		double sumhere = 0;
		for(int l = 0; l<5; ++l){    	    
			t = (Math.pow(l-(5/2), 2))/(Math.pow(sig, 2));
			t = Math.exp(-t); 
			t = (1*t)/(2*Math.PI*Math.pow(sig, 2));
			gua[l] = t;
			sumhere = sumhere + gua[l];
		}	   
		//   System.out.println("--->"+sumhere);	
		//--normalization      
		double sum1=0;
		for(int j=0; j<5; ++j){
			gua[j] = gua[j]/sumhere;
			sum1 = sum1 + gua[j];
		}  
		//--end of producing gaussian matrix
		// System.out.println("gaussian sum: " + sum1);
		double sum=0;
		for(int i=2;i<input.length-2;i++){  
			sum=0;
			for(int j=-2;j<3;j++){
				sum = sum + (input[i+j]* gua[j+2]);	
			}
			output[i]=sum;
		}
		return output;
	};
}