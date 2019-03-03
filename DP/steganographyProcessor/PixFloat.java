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
}