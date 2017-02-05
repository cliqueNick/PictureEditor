package assign12;

/**
 * Represents a two-dimensional region (i.e., a rectangle).
 * 
 * @author Erin Parker, last updated 4/20/16
 */
public class Region2d {
	
	private int minX;	
	private int maxX;	
	private int minY;
	private int maxY;
	
	public Region2d(int _minX, int _maxX, int _minY, int _maxY) {
		minX = _minX;
		maxX = _maxX;
		minY = _minY;
		maxY = _maxY;
	}
	
	public Region2d() {
		minX = 0;
		maxX = 0;
		minY = 0;
		maxY = 0;
	}
	
	/**
	 * Getter for the minX value of this object.
	 */
	public int getMinX() {
		return minX;
	}

	/**
	 * Getter for the maxX value of this object.
	 */
	public int getMaxX() {
		return maxX;
	}

	/**
	 * Getter for the minY value of this object.
	 */
	public int getMinY() {
		return minY;
	}

	/**
	 * Getter for the maxY value of this object.
	 */
	public int getMaxY() {
		return maxY;
	}
	
	/**
	 * Setter for the minX, minY, maxX, maxY values of this object.
	 */
	public void setPoints(int _minX, int _maxX, int _minY, int _maxY) {
		minX = _minX;
		maxX = _maxX;
		minY = _minY;
		maxY = _maxY;
	}
	
	/**
	 * Prints the current values of minX to maxX and minY to maxY
	 */
	public String toString(){
		return "From " + minX + " to " + maxX + ", and " + minY + " to " + maxY;
	}
}