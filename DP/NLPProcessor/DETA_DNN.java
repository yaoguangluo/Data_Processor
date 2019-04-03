package NLPProcessor;
import java.io.IOException;
public class DETA_DNN {
	public String[][] summingProcessor(String[][] ann, String[][] rnn) throws IOException
	, InstantiationException, IllegalAccessException {
		//SUM OF LWA DNN 
		//1 DNN AOP OF DNN
		//IDE= "«◊√‹£¨¿Î…¢£¨Œª÷√"
		String[][] dnn = new String[rnn.length][4];
		for(int i = 0; i < rnn.length; i++) {
			dnn[i][0] = rnn[i][0];
			double important = 0;
			for(int j = 0; j < rnn.length; j++) {
				important += Double.parseDouble(rnn[j][2]);
			}
			dnn[i][1] = "" + Math.sqrt(important * Double.parseDouble(rnn[i][1]));
		}
		//2 DNN CORRELATION LWA
		for(int i = 0; i < rnn.length; i++) {
			double correlation = 0;
			double sumOfPow = 0;
			for(int j = 0; j < rnn.length; j++) {
				sumOfPow +=  Double.parseDouble(rnn[j][3]);
				//sumOfPow += Math.abs(Double.parseDouble(rnn[i][3]) - Double.parseDouble(rnn[j][3]));
			}
		//2	sumOfPow = Math.abs(Double.parseDouble(rnn[i][3]) * (double)rnn.length - sumOfPow);
		//	sumOfPow /= rnn.length;
			sumOfPow = Math.abs(Double.parseDouble(rnn[i][3]) - sumOfPow/rnn.length);//3
			correlation =  Double.parseDouble(dnn[i][1]) * sumOfPow;
			dnn[i][2] = "" + Math.sqrt(correlation);
		}
		//3 DNN and summing ANN combination
		for(int i = 0; i < ann.length; i++) {
			double summingANN = 0;
			double combination = 0;
			for(int j = 7; j < ann[0].length; j++) {
				summingANN += Double.parseDouble(ann[i][j]);
			}
			combination = summingANN *  Double.parseDouble(dnn[i][2]);
			dnn[i][3] = "" + combination;
		}			
		return dnn;		 	
	}	   
}	
