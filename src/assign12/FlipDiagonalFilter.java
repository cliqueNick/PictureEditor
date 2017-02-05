package assign12;

import java.awt.image.BufferedImage;

/**
 * Flips the image along the line x = y 
 * Changes the width and height accordingly. 
 * @author Nickolas Lee
 *
 */
public class FlipDiagonalFilter implements ImageFilter{

	/**
	 * Flips the image along the line x = y 
	 */
	public BufferedImage filter(BufferedImage i) {
		BufferedImage result = new BufferedImage(i.getHeight(), i.getWidth(), BufferedImage.TYPE_INT_RGB); // reversed width and height 
		
		// For each pixel in the image . . . 
		for(int y = 0; y < i.getHeight(); y++) // column
			for(int x = 0; x < i.getWidth(); x++) { // row
				int pixel = i.getRGB(x, y);
				result.setRGB(y, x, pixel); 
			} 

		return result;
	}

	/**
	 * Gives a description of the filter. 
	 */
	public String toString(){
		return "Flip Diagonal";
	}
}
