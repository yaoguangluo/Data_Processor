package steganographyProcessor;
public class LineCodeOperation{
	public String LineCodeADD(String lineCodeBoat, String lineCodeSet, int scale) {
		StringBuilder stringBuilder= new StringBuilder();
		for(int i= 0; i< lineCodeSet.length(); i+= scale) {
			int valueOfLineCodeBoat= Integer.valueOf(lineCodeBoat.charAt(i)).intValue();
			int valueOfLineCodeSet= Integer.valueOf(lineCodeSet.charAt(i)).intValue();
			int sum=valueOfLineCodeBoat+ valueOfLineCodeSet;
			stringBuilder.append(""+ sum);
		}
		return stringBuilder.toString();
	}
}