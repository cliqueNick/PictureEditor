package assign12;

import java.awt.image.BufferedImage;

/**
 * An image 'filter' that does nothing to the image. Useful in proving that an image can be read.  
 * @author Nickolas Lee
 */
public class NoFilter implements ImageFilter {
	public BufferedImage filter(BufferedImage i) {
		BufferedImage result = new BufferedImage(i.getWidth(), i.getHeight(), BufferedImage.TYPE_INT_RGB);
		return result;
	}
}