package imageProcessor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class ReadWritePng {	
	public int h;
	public int w;
	public void writePNG(String args,  int[][] outmag) throws IOException{
		BufferedImage image = new BufferedImage(outmag[0].length, outmag.length, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < image.getHeight(); ++i) {
			for (int j = 0; j < image.getWidth(); ++j) {
				int val = (int) outmag[i][j];
				int pixel = (val << 16) | (val << 8) | (val);
				image.setRGB(j, i, pixel);
			}
		}
		String pathBin = args;//output path
		File outputBin = new File(pathBin);
		ImageIO.write(image, "png", outputBin);	
	}

	public ReadWritePng(){	
	}

	public int[][] GRNpngRead(String args) throws IOException {
		BufferedImage image = ImageIO.read(new File(args));
		// extract R, G, B values
		h = image.getHeight();
		w = image.getWidth();
		System.out.println(h+"&&"+w);
		int[][] g = new int[h][w];
		for (int i = 0; i < h; i++){
			for (int j = 0; j < w; j++){
				g[i][j] = image.getRGB(j, i) >> 8 & 0xFF;		
			}
		}
		return g;
	}
	
	public int[][] GRNpngRead(BufferedImage image) throws IOException {
		// extract R, G, B values
		h = image.getHeight();
		w = image.getWidth();
		System.out.println(h+"&&"+w);
		int[][] g = new int[h][w];
		for (int i = 0; i < h; i++){
			for (int j = 0; j < w; j++){
				g[i][j] = image.getRGB(j, i) >> 8 & 0xFF;		
			}
		}
		return g;
	}
	
	public int[][] REDpngRead(String args) throws IOException {
		BufferedImage image = ImageIO.read(new File(args));
		// extract R, G, B values
		h = image.getHeight();
		w = image.getWidth();
		int[][] r = new int[h][w];
		for (int i = 0; i < h; i++){
			for (int j = 0; j < w; j++){
				r[i][j] = image.getRGB(j, i) >> 16 & 0xFF;//x,y coordinates opposite to array   	  		
			}
		}
		return r;
	}
	
	public int[][] REDpngRead(BufferedImage image) throws IOException {
		// extract R, G, B values
		h = image.getHeight();
		w = image.getWidth();
		int[][] r = new int[h][w];
		for (int i = 0; i < h; i++){
			for (int j = 0; j < w; j++){
				r[i][j] = image.getRGB(j, i) >> 16 & 0xFF;//x,y coordinates opposite to array   	  		
			}
		}
		return r;
	}

	public int[][] BLUpngRead(String args) throws IOException {
		BufferedImage image = ImageIO.read(new File(args));
		// extract R, G, B values
		h = image.getHeight();
		w = image.getWidth();
		int[][] b = new int[h][w];
		for (int i = 0; i < h; i++){
			for (int j = 0; j < w; j++){
				b[i][j] = image.getRGB(j, i) & 0xFF;	
			}
		}
		return b;
	}
	
	public int[][] BLUpngRead(BufferedImage image) throws IOException {
		// extract R, G, B values
		h = image.getHeight();
		w = image.getWidth();
		int[][] b = new int[h][w];
		for (int i = 0; i < h; i++){
			for (int j = 0; j < w; j++){
				b[i][j] = image.getRGB(j, i) & 0xFF;	
			}
		}
		return b;
	}

	public int[] sizeHW(String args) throws IOException {
		BufferedImage image = ImageIO.read(new File(args));
		int size[] =new int[2];
		size[0]= image.getHeight();
		size[1]= image.getWidth();
		return size;
	}
	
	public BufferedImage createBufferImage(int[][] r, int[][] g, int[][] b) {
		BufferedImage image = new BufferedImage(r[0].length, r.length, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < image.getHeight(); ++i) {
			for (int j1 = 0; j1 < image.getWidth(); ++j1) {
				int rr = (int) r[i][j1];
				int gg = (int) g[i][j1];
				int bb = (int) b[i][j1];
				int pixel = (rr << 16) | (gg << 8) | (bb);
				image.setRGB(j1, i, pixel);
			}
		}
		return image;
	}
}		