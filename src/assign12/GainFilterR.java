package assign12;

import java.awt.image.BufferedImage;

/**
 * Changes the contrast of the image.
 * @author Nickolas Lee
 */
public class GainFilterR extends ImageRegionFilter {
	
	private double level;
	private int fac;
	
	public BufferedImage filter(BufferedImage img) {
				
		BufferedImage result = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		for(int y = 0; y < img.getHeight(); y++)
			for(int x = 0; x < img.getWidth(); x++) {

				int pixel = img.getRGB(x, y);
				if(y >= this.getMinY() && y <= this.getMaxY() && x >= this.getMinX() && x <= this.getMaxX()){
					int redAmount = (pixel >> 16) & 0xff;
					int greenAmount = (pixel >> 8) & 0xff;
					int blueAmount = (pixel >> 0) & 0xff;

					redAmount *= level;
					if(redAmount > 255) {
						redAmount = 255;
					}

					greenAmount *= level;
					if(greenAmount > 255) {
						greenAmount = 255;
					}

					blueAmount *= level;
					if(blueAmount > 255) {
						blueAmount = 255;
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
	 * Reports the current contrast.
	 * @return The contrast
	 */
	public double getLevel() {
		return level;
	}

	/**
	 * Sets the contrast. Clamps values to be between -100 and 100.
	 * @param factor - The contrast factor
	 */
	public void setLevel(int factor) {
		fac = factor;
		if(factor <= -100)
			this.level = 0;
		else if(factor >= 100)
			this.level = 2;
		else{
			factor += 100;
			this.level = factor * .01;
		}
	}
	
	/**
	 * Gives a description of the filter. 
	 */
	public String toString(){
		return "Gain " + fac;
	}
}