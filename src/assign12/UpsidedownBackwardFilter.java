package assign12;

import java.awt.image.BufferedImage;

public class UpsidedownBackwardFilter implements ImageFilter{
	/**
	 * Rotates an image counter clockwise.
	 */
	public BufferedImage filter(BufferedImage i) {
		BufferedImage result = new BufferedImage(i.getHeight(), i.getWidth(), BufferedImage.TYPE_INT_RGB); // reversed width and height 
		
		ImageFilter mirror = new MirrorFilter(true); // flip horizontally
		result = mirror.filter(i);
		
		ImageFilter mirror2 = new MirrorFilter(false); // flip vertically
		result = mirror2.filter(result);

		return result;
	}
	
	/**
	 * Gives a description of the filter. 
	 */
	public String toString(){
		return "Upsidedown and Backwards";
	}
}
