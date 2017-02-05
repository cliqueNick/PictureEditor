package assign12;

import java.awt.image.BufferedImage;

/**
 * Makes a new image including only the selected part of the image.
 * @author Nickolas Lee
 *
 */
public class CropFilter extends ImageRegionFilter{

	/**
	 * Makes a new image including only the selected part of the image.
	 */
	public BufferedImage filter(BufferedImage i) { 
		BufferedImage result = new BufferedImage(Math.abs(this.getMaxX() - this.getMinX()), Math.abs(this.getMaxY() - this.getMinY()), BufferedImage.TYPE_INT_RGB);
		
		// For each pixel in the cropped image 
		for(int y = 0; y < result.getHeight(); y++){
			for(int x = 0; x < result.getWidth(); x++) {
				result.setRGB(x, y, i.getRGB(x + this.getMinX(), y + this.getMinY()));
			}
		}
		return result;
	}
	
	/**
	 * Gives a description of the filter. 
	 */
	public String toString(){
		return "Crop";
	}
}
