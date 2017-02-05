package assign12;

import java.awt.image.BufferedImage;

/**
 * Sets any pixels with blue to the maximum. 
 * @author Nickolas Lee
 *
 */
public class MaximizeBlueFilterR extends ImageRegionFilter{

	/**
	 * Sets any pixels with blue to the maximum. 
	 */
	public BufferedImage filter(BufferedImage i) {
		
		BufferedImage result = new BufferedImage(i.getWidth(), i.getHeight(), BufferedImage.TYPE_INT_RGB);

		// For each pixel in the image . . . 
		for(int y = 0; y < i.getHeight(); y++)
			for(int x = 0; x < i.getWidth(); x++) {

				int pixel = i.getRGB(x, y);
				if(y >= this.getMinY() && y <= this.getMaxY() && x >= this.getMinX() && x <= this.getMaxX()){
					// Decompose the pixel in the amounts of red, green, and blue.
					int redAmount = (pixel >> 16) & 0xff;
					int greenAmount = (pixel >> 8) & 0xff;
					int blueAmount = (pixel >> 0) & 0xff;

					// Build a new pixel with the red and blue the same as the original, 
					// but all green components set to the max value of 255.
					blueAmount = 255;

					// Compose the new pixel.
					int newPixel = (redAmount << 16 ) | (greenAmount << 8) | blueAmount;

					// Set the pixel of the new image.
					result.setRGB(x, y, newPixel);
				}
				else{
					result.setRGB(x, y, pixel);
				}
			}
		
		return result;
	}
	
	/**
	 * Gives a description of the filter. 
	 */
	public String toString(){
		return "Maximize Blue";
	}
}
