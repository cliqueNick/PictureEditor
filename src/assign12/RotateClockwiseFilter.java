package assign12;

import java.awt.image.BufferedImage;

/**
 * Rotates an image clockwise.
 * @author Nickolas Lee
 *
 */
public class RotateClockwiseFilter implements ImageFilter{
	
	/**
	 * Rotates an image clockwise.
	 */
	public BufferedImage filter(BufferedImage i) {
		BufferedImage result = new BufferedImage(i.getHeight(), i.getWidth(), BufferedImage.TYPE_INT_RGB); // reversed width and height 
		
		ImageFilter upBack = new FlipDiagonalFilter();
		result = upBack.filter(i);
		
		ImageFilter mirror = new MirrorFilter(true); // flip horizontally
		result = mirror.filter(result);

		return result;
	}
	/**
	 * Gives a description of the filter. 
	 */
	public String toString(){
		return "Rotate Clockwise";
	}
}
