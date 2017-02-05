package assign12;

import java.awt.image.BufferedImage;

/**
 * Blurs the image. 
 * @author Nickolas Lee
 *
 */
public class BlurFilter implements ImageFilter{

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
		for(int y = 0; y < i.getHeight(); y++) // column
			for(int x = 0; x < i.getWidth(); x++) { // row
				
				n = y - 1; // north
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
				
				int[] pixels = new int[9];

				pixels[0] = i.getRGB(x, y); // self
				pixels[1] = i.getRGB(x, n); // N
				pixels[2] = i.getRGB(e, n); // NE
				pixels[3] = i.getRGB(e, y); // E
				pixels[4] = i.getRGB(e, s); // SE
				pixels[5] = i.getRGB(x, s); // S
				pixels[6] = i.getRGB(w, s); // SW
				pixels[7] = i.getRGB(w, y); // W
				pixels[8] = i.getRGB(w, n); // NW
				
				int red = 0;
				int green = 0;
				int blue = 0;
				
				for(int j = 0; j < 9; j++){
					// decompose the amounts of red, green, and blue for each pixel neighbor
					red += (pixels[j] >> 16) & 0xff;
					green += (pixels[j] >> 8) & 0xff;
					blue += (pixels[j] >> 0) & 0xff;
				}
				
				// average and compose the new pixel.
				int newPixel = (((int) (red / 9.0)) << 16 ) | (((int) (green / 9.0)) << 8) | ((int) (blue / 9.0));
				
				// Set the pixel of the new image.
				result.setRGB(x, y, newPixel);
			}
		
		return result;
	}
	
	/**
	 * Gives a description of the filter. 
	 */
	public String toString(){
		return "Blur";
	}
}
