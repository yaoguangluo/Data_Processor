package steganographyProcessor;
public class PixFloat{
	public void floatPix(int[][] pix, int floatPix, int scale) {
		for(int x = 0; x < pix[0].length; x += scale) {
			for(int y = 0;y < pix.length;y += scale) {
				pix[x][y] += floatPix;
			}
		}
	}

	public void arrangePix(int[][] pix, int arrangePix, int scale) {
		for(int x = 0; x < pix[0].length; x += scale) {
			for(int y = 0;y< pix.length;y += scale) {
				pix[x][y] += arrangePix;
				pix[x+1][y] += arrangePix-1;
				pix[x-1][y] += arrangePix-1;
				pix[x][y+1] += arrangePix-1;
				pix[x][y-1] += arrangePix-1;
			}
		}
	}

	public int[] matrixToLiner(int[][] pix) {
		int[] output = new int[pix.length * pix[0].length];
		for(int x = 0; x < pix[0].length; x ++) {
			for(int y = 0; y < pix.length; y ++) {
				output[x * pix[0].length + y] = pix[x][y];
			}
		}
		return output;
	}

	public String LinerToLineCode(int[] pix) {
		StringBuilder code = new StringBuilder();
		for(int i = 0; i < pix.length; i++){
			String register = String.valueOf(pix[i]);
			code.append("" + register.charAt(0));
			if(register.length()>1) {
				code.append("" + register.charAt(1));
			}
			if(register.length()>2) {
				code.append("" + register.charAt(2));
			}
		}
		return code.toString();
	}
}