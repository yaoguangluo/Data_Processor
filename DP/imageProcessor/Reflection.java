package imageProcessor;
public class Reflection {
	public int[][] PadImage (int[][] _image, int height, int width){
		int lines = _image.length;
		int samples = _image[0].length;

		int padded_lines = lines + height - 1;
		int padded_samples = samples + width - 1;

		int[][] paddedImage = new int[padded_lines][padded_samples];

		int halfWidth = width / 2;
		int halfHeight = height / 2;

		// -- Fill interior of padded array with actual image. 
		for (int i = 0; i < lines; ++i) {
			for (int j = 0; j < samples; ++j) {
				paddedImage[i + halfHeight][j + halfWidth] = _image[i][j];
			}
		}

		// -- Fill border of padded array with a mirror image of 
		//    the actual image reflected about the boundaries.   

		// left border 
		for (int i = 0; i < lines; ++i) {
			for (int j = 0, oj = halfWidth - 1; j < (int)halfWidth; ++j, --oj) {
				paddedImage[i + halfHeight][oj] = _image[i][j];
			}
		}

		// right border
		for (int i = 0; i < lines; ++i) {
			for (int j = samples - halfWidth, oj = samples + (2 * halfWidth) - 1; j < samples; ++j, --oj) {
				paddedImage[i + halfHeight][oj] = _image[i][j];
			}
		}

		// top border
		for (int i = 0, oi = halfHeight - 1; i < (int)halfHeight; ++i, --oi) {
			for (int j = 0; j < samples; ++j) {
				paddedImage[oi][j + halfWidth] = _image[i][j];
			}
		}

		// bottom border
		for (int i = lines - halfHeight, oi = lines + (2 * halfHeight) - 1; i < lines; ++i, --oi) {
			for (int j = 0; j < samples; ++j) {
				paddedImage[oi][j + halfWidth] = _image[i][j]; 
			}
		}

		// top left corner
		for (int i = 0; i < (int)halfHeight; ++i) {
			for (int j = 0, oj = halfWidth - 1; j < (int)halfWidth; ++j, --oj) {
				paddedImage[i][oj] = paddedImage[i][j + halfWidth];
			}
		}

		// bottom right corner
		for (int i = lines + halfHeight; i < lines + (2 * halfHeight); ++i) {
			for (int j = samples, oj = samples + (2 * halfWidth) - 1; j < samples + halfWidth; ++j, --oj) {
				paddedImage[i][oj] = paddedImage[i][j];
			}
		}

		// top right corner
		for (int i = 0; i < (int)halfHeight; ++i) {
			for (int j = samples, oj = samples + (2 * halfWidth) - 1; j < samples + halfWidth; ++j, --oj) {
				paddedImage[i][oj] = paddedImage[i][j];
			}
		}

		// bottom left corner
		for (int i = lines + halfHeight; i < lines + (2 * halfHeight); ++i) {
			for (int j = 0, oj = halfWidth - 1; j < (int)halfWidth; ++j, --oj) {
				paddedImage[i][oj] = paddedImage[i][j + halfWidth];
			}
		}

		return paddedImage;		
	}

}
