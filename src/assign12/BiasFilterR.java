package assign12;

import java.awt.image.BufferedImage;

/**
 * Changes the brightness of the image. 
 * @author Nickolas Lee
 */
public class BiasFilterR extends ImageRegionFilter {
	
	private int level;
	
	public BufferedImage filter(BufferedImage img) {
				
		BufferedImage result = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		for(int y = 0; y < img.getHeight(); y++)
			for(int x = 0; x < img.getWidth(); x++) {

				int pixel = img.getRGB(x, y);
				if(y >= this.getMinY() && y <= this.getMaxY() && x >= this.getMinX() && x <= this.getMaxX()){
					int redAmount = (pixel >> 16) & 0xff;
					int greenAmount = (pixel >> 8) & 0xff;
					int blueAmount = (pixel >> 0) & 0xff;

					redAmount += level;
					if(redAmount > 255) {
						redAmount = 255;
					}
					if(redAmount < 0) {
						redAmount = 0;
					}

					greenAmount += level;
					if(greenAmount > 255) {
						greenAmount = 255;
					}
					if(greenAmount < 0) {
						greenAmount = 0;
					}

					blueAmount += level;
					if(blueAmount > 255) {
						blueAmount = 255;
					}
					if(blueAmount < 0) {
						blueAmount = 0;
					}

					int newPixel = (redAmount << 16 ) | (greenAmount << 8) | blueAmount;
					result.setRGB(x, y, newPixel);
				}
				else{
					result.setRGB(x, y, pixel);
				}
			}
		
		return result;
	}
	
	/**
	 * Reports the current brightness.
	 * @return The brightness
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * Sets the brightness. Clamps values to be between -255 and 255.
	 * @param level - The brightness level
	 */
	public void setLevel(int level) {
		if(level <= -255)
			this.level = -255;
		else if(level >= 255)
			this.level = 255;
		else
			this.level = level;
	}
	
	/**
	 * Gives a description of the filter. 
	 */
	public String toString(){
		return "Bias " + this.level;
	}

}