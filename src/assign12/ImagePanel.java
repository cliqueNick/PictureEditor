package assign12;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/** 
 * A JPanel for displaying a BufferedImage.
 * Also listens for mouse events to allow user to select a region of the image.
 *
 * @author Erin Parker, Nickolas Lee
 */
public class ImagePanel extends JPanel implements MouseListener, MouseMotionListener {
	private BufferedImage image;
	private int minX;
	private int minY;
	private int maxX;
	private int maxY;
	private static final long serialVersionUID = 1L;
	private boolean selectBoxOn;
	private boolean selectingEnabled;
	private boolean areaSelected;
	private ImageProcessor ip;
	
	public ImagePanel(BufferedImage img) {
		image = img;
		setPreferredSize(new Dimension(img.getWidth(), image.getHeight()));
		addMouseListener(this);
		addMouseMotionListener(this);
		selectBoxOn = false;
		selectingEnabled = false;
		areaSelected = false;
	}
	
	public ImagePanel(BufferedImage img, ImageProcessor p){ 
		image = img;
		setPreferredSize(new Dimension(img.getWidth(), image.getHeight()));
		addMouseListener(this);
		addMouseMotionListener(this);
		selectBoxOn = false;
		this.ip = p;
		selectingEnabled = ip.isCumulative(); // this is set up for fPanel in ImageProcessor
		areaSelected = false;
		
	}
	
	public void mouseClicked(MouseEvent arg0) {
		// unused method
		selectBoxOn = false;
		areaSelected = false;
		repaint();
	}

	public void mouseEntered(MouseEvent arg0) {
		// unused method		
	}

	public void mouseExited(MouseEvent arg0) {
		// unused method		
	}
	
	/** 
	 * The method sets the minX and minY to the location where the mouse 
	 * is first pressed.
	 */
	public void mousePressed(MouseEvent e) {
		minX = e.getX();
		minY = e.getY();
	}

	/**
	 * Reorganizes the points gathered to make sure the maximum is always the maximum. 
	 */
	public void mouseReleased(MouseEvent arg0) {
		if(maxX < minX){ // makes sure max is the maximum
			int temp = maxX;
			maxX = minX;
			minX = temp;
		}

		if(maxY < minY){ 
			int temp = maxY;
			maxY = minY;
			minY = temp;
		}
	}

	/**
	 * This method sets the maxX and maxY values as the mouse is dragged.
	 */
	public void mouseDragged(MouseEvent e) {
		maxX = e.getX();
		maxY = e.getY();
		selectBoxOn = true;
		if(selectingEnabled){ // when selecting
			areaSelected = true;
			repaint();
		}
	}

	public void mouseMoved(MouseEvent arg0) { // TODO EXTRA idea: add displaying current coordinates of the mouse 
		// unused method		
	}
	
	/**
	 * This method paints the image panel, which includes painting a
	 * gray box to visually indicated the region being selected.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
		if(selectBoxOn && areaSelected){
			g.setColor(new Color(105, 105, 105, 125));
			g.fillRect(minX, minY, maxX - minX, maxY - minY);
			ip.setEnableNonRegionButtons(true);
		}
		else{ // resets the area selected, selects the whole picture 
			minX = 0;
			maxX = image.getWidth();
			minY = 0;
			maxY = image.getHeight();
			ip.setEnableNonRegionButtons(false);
		}
	}

	/**
	 * Getter for the selected region.
	 * 
	 * @return a Region2d object that represents the region selected on the panel
	 */
	public Region2d getSelectedRegion() {
		if(selectingEnabled)
			return new Region2d(minX, maxX, minY, maxY);
		else
			return new Region2d(0,image.getWidth(),0,image.getHeight()); // prevents a problem when resetting a cropped image
	}

	/**
	 * Sets whether this panel allows selecting on it. 
	 * @param enableSelecting true if selecting is allowed
	 */
	public void setSelectingEnabled(boolean enableSelecting) {
		this.selectingEnabled = enableSelecting;
	}

}