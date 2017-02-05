package assign12;

/**
 * An abstract class that ensures all classes representing an image filter
 * implement the filter method as specified in ImageFilter.
 * Also provides data and methods for a region selected from an image to 
 * be filtered.
 * 
 * @author Erin Parker, Nickolas Lee
 */
public abstract class ImageRegionFilter implements ImageFilter {

	private Region2d r;
	
	public ImageRegionFilter() {
		r = new Region2d();
	}
	
	/**
	 * Setter for the Region2d value of this filter.
	 */
	public void setRegion(Region2d _r) {
		r = _r;
	}
	
	/**
	 * Setter for the Region2d value of this filter.
	 * 
	 * NOTE: This needs to be improved for the final project.  
	 * We cannot assume that the user will drag from the top-left to the bottom-right.
	 */
	public void setRegion(int minX, int maxX, int minY, int maxY) {
		r.setPoints(minX, maxX, minY, maxY);
	}
	
	/**
	 * Getter for the minX value of this filter.
	 */
	public int getMinX() {
		return r.getMinX();
	}
	
	/**
	 * Getter for the maxX value of this filter.
	 */
	public int getMaxX() {
		return r.getMaxX();
	}
	
	/**
	 * Getter for the minY value of this filter.
	 */
	public int getMinY() {
		return r.getMinY();
	}
	
	/**
	 * Getter for the maxY value of this filter.
	 */
	public int getMaxY() {
		return r.getMaxY();
	}
	
	public String toString(){
		return r.toString();
	}
}