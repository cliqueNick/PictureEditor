package assign12;

import java.awt.image.BufferedImage;

/**
 * Gives the mirror image of the picture.
 * @author Nickolas Lee
 *
 */
public class MirrorFilter implements ImageFilter{
	
	private final int horizontalFlip;
	private final int verticalFlip;
	
	/**
	 * Determines whether to flip the image horizontally or vertically.
	 * @param horizontal set to true to flip horizontally
	 */
	public MirrorFilter(boolean horizontal){ 
		if(horizontal){
			this.horizontalFlip = 1;
			this.verticalFlip = 0;
		}
		else {
			this.verticalFlip = 1;
			this.horizontalFlip = 0;
		}
	}

	/**
	 * Gives the mirror image of the picture.
	 */
	public BufferedImage filter(BufferedImage i) {
		BufferedImage result = new BufferedImage(i.getWidth(), i.getHeight(), BufferedImage.TYPE_INT_RGB); 
		
		// For each pixel in the image . . . 
		for(int y = 0; y < i.getHeight(); y++) // column
			for(int x = 0; x < i.getWidth(); x++) { // row
				int pixel = i.getRGB(x, y);
				int newX = horizontalFlip * (i.getWidth() - 1 - x) + verticalFlip * x;
				int newY = verticalFlip * (i.getHeight() - 1 - y) + horizontalFlip * y;
				
				if(newX < 0)
					newX = 0;
				if(newX >= i.getWidth() - 1)
					newX = i.getWidth() - 1;
				if(newY < 0)
					newY = 0;
				if(newY >= i.getHeight() - 1)
					newY = i.getHeight() - 1;
				
				result.setRGB(newX, newY, pixel); 
			}

		return result;
	}
	
	/**
	 * Gives a description of the filter. 
	 */
	public String toString(){
		if(this.horizontalFlip == 1)
			return "Horizontal Mirror";
		else
			return "Vertical Mirror";
	}
}
