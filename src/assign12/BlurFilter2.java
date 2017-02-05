package assign12;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Blurs the image to varying amounts. 
 * Warning, the computing time for high levels of blurriness may be significant! 
 * @author Nickolas Lee
 *
 */
public class BlurFilter2 implements ImageFilter{
	
	private int blurLevel;
	
	/**
	 * Constructor which gives the image the minimum level of blurriness. 
	 */
	public BlurFilter2(){
		setLevel(1);
	}
	
	/**
	 * Constructor which allows the level of blurriness to be set. 
	 */
	public BlurFilter2(int level){
		setLevel(level);
	}

	/**
	 * Averages a pixel with it's neighboring pixels to blur the image.
	 */
	public BufferedImage filter(BufferedImage i) { 
		
		BufferedImage result = new BufferedImage(i.getWidth(), i.getHeight(), BufferedImage.TYPE_INT_RGB);
		int n = 0;
		int e = 0;
		int s = 0;
		int w = 0;
		
		// For each pixel in the image . . . 
		for(int y = 0; y < i.getHeight(); y++){ // column
			for(int x = 0; x < i.getWidth(); x++) { // row
				
				ArrayList<Integer> neighbors = new ArrayList<Integer>();
				
				n = y - blurLevel; // north
				e = x + blurLevel;
				s = y + blurLevel;
				w = x - blurLevel;

				if(n < 0)
					n = 0;
				if(e > i.getWidth() - 1)
					e = i.getWidth() - 1;
				if(s > i.getHeight() - 1)
					s = i.getHeight() - 1;
				if(w < 0)
					w = 0;
				
				for(int h = w; h <= e; h++){ // horizontal
					for(int v = n; v <= s; v++){ // vertical
						neighbors.add(i.getRGB(h, v)); 
					}
				}
				
				int red = 0;
				int green = 0;
				int blue = 0;
				
				for(int j = 0; j < neighbors.size(); j++){
					// decompose the amounts of red, green, and blue for each pixel neighbor
					red += (neighbors.get(j) >> 16) & 0xff;
					green += (neighbors.get(j) >> 8) & 0xff;
					blue += (neighbors.get(j) >> 0) & 0xff;
				}
				
				// average and compose the new pixel.
				int newPixel = (((int) ((double) red / neighbors.size())) << 16 ) | (((int) ((double) green / neighbors.size())) << 8) | ((int) ( (double) blue / neighbors.size()));
				
				// Set the pixel of the new image.
				result.setRGB(x, y, newPixel);
			}
		}
		return result;
	}

	/**
	 * Provides the current level of blurriness. 
	 * @return the level of blurriness
	 */
	public int getLevel() {
		return blurLevel;
	}

	/**
	 * Sets the amount of blurriness of the image. Determines the size of the square grid
	 * around each pixel that gets averaged such that each side = 2*level+1
	 * The level cannot be set less than 0. Setting to zero does not change the image. 
	 * Warning, the computing time for high levels of blurriness may be significant! 
	 * @param level the level of blurriness 
	 */
	public void setLevel(int level) {
		if(level < 0)
			this.blurLevel = 0;
		else
			this.blurLevel = level;
	}
	
	/**
	 * Gives a description of the filter. 
	 */
	public String toString(){
		return "Blur " + blurLevel;
	}

}
