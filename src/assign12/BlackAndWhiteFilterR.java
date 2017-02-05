package assign12;

import java.awt.image.BufferedImage;

/**
 * Make a color image black and white.
 * @author Nickolas Lee
 *
 */
public class BlackAndWhiteFilterR extends ImageRegionFilter {

	public BufferedImage filter(BufferedImage i) {
		BufferedImage result = new BufferedImage(i.getWidth(), i.getHeight(), BufferedImage.TYPE_INT_RGB);

		// For each pixel in the image . . . 
		for(int y = 0; y < i.getHeight(); y++)
			for(int x = 0; x < i.getWidth(); x++) {

				int pixel = i.getRGB(x, y);
				int newPixel = 0;

				if(y >= this.getMinY() && y <= this.getMaxY() && x >= this.getMinX() && x <= this.getMaxX()){
					// Decompose the pixel in the amounts of red, green, and blue.
					int redAmount = (pixel >> 16) & 0xff;
					int greenAmount = (pixel >> 8) & 0xff;
					int blueAmount = (pixel >> 0) & 0xff;

					int grayScale = (int) (greenAmount * 0.71 + blueAmount * 0.07 + redAmount * 0.21) ; // / 3 ? 
					//				int grayScale = (greenAmount + blueAmount + redAmount) / 3; // alternate algorithm 

					// Compose the new pixel.
					newPixel =  (grayScale << 16 ) | (grayScale << 8) | grayScale;
				}
				else
					newPixel = pixel;

				// Set the pixel of the new image.
				result.setRGB(x, y, newPixel);
			}
		
		return result;
	}
	
	/**
	 * Gives a description of the filter. 
	 */
	public String toString(){
		return "Black and White";
	}

}
