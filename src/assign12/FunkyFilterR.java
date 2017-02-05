package assign12;

import java.awt.image.BufferedImage;

/**
 * Adds up the neighbors of a pixel to give the image a funky look.
 * @author Nickolas Lee
 *
 */
public class FunkyFilterR extends ImageRegionFilter{

	/**
	 * Adds up the neighbors of a pixel to give the image a funky look
	 */	
	public BufferedImage filter(BufferedImage i) { 
		BufferedImage result = new BufferedImage(i.getWidth(), i.getHeight(), BufferedImage.TYPE_INT_RGB);
		int n = 0;
		int e = 0;
		int s = 0;
		int w = 0;

		int pixel = 0;
		int pixelN = 0;
		int pixelNE = 0;
		int pixelE = 0;
		int pixelSE = 0;
		int pixelS = 0;
		int pixelSW = 0;
		int pixelW = 0;
		int pixelNW = 0;

		// For each pixel in the image . . . 
		for(int y = 0; y < i.getHeight(); y++) // column
			for(int x = 0; x < i.getWidth(); x++) { // row

				if(y >= this.getMinY() && y <= this.getMaxY() && x >= this.getMinX() && x <= this.getMaxX()){
					n = y - 1;
					e = x + 1;
					s = y + 1;
					w = x - 1;

					if(n < 0)
						n = 0;
					if(e > i.getWidth() - 1)
						e = i.getWidth() - 1;
					if(s > i.getHeight() - 1)
						s = i.getHeight() - 1;
					if(w < 0)
						w = 0;

					pixel = i.getRGB(x, y);
					pixelN = i.getRGB(x, n);
					pixelNE = i.getRGB(e, n);
					pixelE = i.getRGB(e, y);
					pixelSE = i.getRGB(e, s);
					pixelS = i.getRGB(x, s);
					pixelSW = i.getRGB(w, s);
					pixelW = i.getRGB(w, y);
					pixelNW = i.getRGB(w, n);

					int sum = (pixel + pixelN + pixelNE + pixelE + pixelSE + pixelS + pixelSW + pixelW + pixelNW);

					// Set the pixel of the new image.
					result.setRGB(x, y, sum);
				}
				else{
					result.setRGB(x, y, i.getRGB(x, y));
				}
			}

		return result;
	}
	
	/**
	 * Gives a description of the filter. 
	 */
	public String toString(){
		return "Funky";
	}
}
