package NLPProcessor;
import java.io.IOException;
public class DETA_ANN_HMM {
	public String[][] summingProcessor(String[][] inputNLP) throws IOException, InstantiationException, IllegalAccessException {
		String[][] outNLP = inputNLP.clone(); 
		for(int i = 7; i < inputNLP[0].length; i++) {
			for(int j = 0; j < inputNLP.length; j++) {
				double sum = 0;
				for(int k = 0;k < inputNLP.length; k++) {
					sum += Double.valueOf(inputNLP[k][i]);
				}
				//System.out.println(sum);
				outNLP[j][i] = "" + Double.valueOf(inputNLP[j][i])/sum;
			}
		}
		return outNLP;		 	
	}	   
}	
