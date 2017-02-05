package assign12;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/** 
 * A simple image processing application. Opens an image file, gives options of filters that can be applied 
 * to the image, displays the original and filtered images, saves the filtered image, and allows filters to be applied cumulatively. 
 * 
 * @author Nickolas Lee
 */
public class ImageProcessor extends JFrame implements ActionListener, ChangeListener{
	private static final long serialVersionUID = 1L;
	private JTabbedPane pane;
	private JFileChooser getFile;
	private BufferedImage originalImage;
	private BufferedImage filteredImage;
	private ImageFilter filter;
	private boolean isFiltered;
	private boolean isCumulative;
	private ImagePanel oPanel;
	private ImagePanel fPanel;
	private String filterMessage;

	private JMenu imageFilterMenu;
	private JMenu colorFilterMenu;
	private JMenu blurMenu;
	private JMenu biasMenu;
	private JMenu gainMenu;
	private JSlider blurSlider;
	private JSlider biasSlider;
	private JSlider gainSlider;

	private JMenuItem openItem;
	private JMenuItem saveItem;
	private JMenuItem cumulativeItem;
	private JMenuItem cropItem;
	private JMenuItem mirrorLRFilterItem;
	private JMenuItem mirrorUDFilterItem;
	private JMenuItem rotateClockwiseFilterItem;
	private JMenuItem rotateCounterClockwiseFilterItem;

	private JMenuItem blackWhiteFilterItem;
	private JMenuItem maxRedFilterItem;
	private JMenuItem maxGreenFilterItem;
	private JMenuItem maxBlueFilterItem;
	private JMenuItem redBlueSwapFilterItem;
	private JMenuItem redGreenSwapFilterItem;
	private JMenuItem greenBlueSwapFilterItem;
	private JMenuItem funkyFilterItem;

	/**
	 * initializes the program. 
	 */
	public ImageProcessor(){ 
		// initialize variables
		pane = new JTabbedPane();
		originalImage = null;
		filteredImage = null;
		isFiltered = false;
		isCumulative = false;
		fPanel = null; // needs to be set up here so that selections can be made to the panel where the previous filters were made
		filterMessage = "";

		// file menu
		JMenu optionsMenu = new JMenu("File");
		openItem = new JMenuItem("Open");
		openItem.addActionListener(this);
		optionsMenu.add(openItem);
		openItem.setToolTipText("Opens a picture to edit");

		saveItem = new JMenuItem("Save");
		saveItem.addActionListener(this);
		optionsMenu.add(saveItem);
		saveItem.setEnabled(false);
		saveItem.setToolTipText("Saves the filtered image");

		// image menu
		imageFilterMenu = new JMenu("Image");
		imageFilterMenu.setEnabled(false);

		cumulativeItem = new JMenuItem();
		cumulativeItem.setText("Cumulative Changes: Off");
		cumulativeItem.setEnabled(false);
		imageFilterMenu.add(cumulativeItem);
		cumulativeItem.addActionListener(this);
		cumulativeItem.setToolTipText("When off, only one change can be applied to the original image. When on, changes can continue to be made to the previously changed image.");

		cropItem = new JMenuItem("Crop");
		cropItem.addActionListener(this);
		imageFilterMenu.add(cropItem);
		cropItem.setEnabled(false);
		cropItem.setToolTipText("Crops the image");

		mirrorLRFilterItem = new JMenuItem("Mirror Horizontal");
		mirrorLRFilterItem.addActionListener(this);
		imageFilterMenu.add(mirrorLRFilterItem);
		mirrorLRFilterItem.setToolTipText("Flips the image so that the right side is on the left");

		mirrorUDFilterItem = new JMenuItem("Mirror Vertical");
		mirrorUDFilterItem.addActionListener(this);
		imageFilterMenu.add(mirrorUDFilterItem);
		mirrorUDFilterItem.setToolTipText("Flips the image so that the top is on the bottom");

		rotateClockwiseFilterItem = new JMenuItem("Rotate Clockwise");
		rotateClockwiseFilterItem.addActionListener(this);
		imageFilterMenu.add(rotateClockwiseFilterItem);
		rotateClockwiseFilterItem.setToolTipText("Rotates the image a quarter circle to the right");

		rotateCounterClockwiseFilterItem = new JMenuItem("Rotate Counter Clockwise");
		rotateCounterClockwiseFilterItem.addActionListener(this);
		imageFilterMenu.add(rotateCounterClockwiseFilterItem);
		rotateCounterClockwiseFilterItem.setToolTipText("Rotates the image a quarter circle to the left");

		// color menu
		colorFilterMenu = new JMenu("Color");
		colorFilterMenu.setEnabled(false);

		blackWhiteFilterItem = new JMenuItem("Black and White");
		blackWhiteFilterItem.addActionListener(this);
		colorFilterMenu.add(blackWhiteFilterItem);
		blackWhiteFilterItem.setToolTipText("Removes all color from the image");

		maxRedFilterItem = new JMenuItem("Max Red");
		maxRedFilterItem.addActionListener(this);
		colorFilterMenu.add(maxRedFilterItem);
		maxRedFilterItem.setToolTipText("Any pixels in the image with red get set to the maximum amount of red");

		maxGreenFilterItem = new JMenuItem("Max Green");
		maxGreenFilterItem.addActionListener(this);
		colorFilterMenu.add(maxGreenFilterItem);
		maxGreenFilterItem.setToolTipText("Any pixels in the image with green get set to the maximum amount of green");

		maxBlueFilterItem = new JMenuItem("Max Blue");
		maxBlueFilterItem.addActionListener(this);
		colorFilterMenu.add(maxBlueFilterItem);
		maxBlueFilterItem.setToolTipText("Any pixels in the image with blue get set to the maximum amount of blue");

		redBlueSwapFilterItem = new JMenuItem("Red Blue Swap");
		redBlueSwapFilterItem.addActionListener(this);
		colorFilterMenu.add(redBlueSwapFilterItem);
		redBlueSwapFilterItem.setToolTipText("Swaps the red and blue colors in each pixel");

		redGreenSwapFilterItem = new JMenuItem("Red Green Swap");
		redGreenSwapFilterItem.addActionListener(this);
		colorFilterMenu.add(redGreenSwapFilterItem);
		redGreenSwapFilterItem.setToolTipText("Swaps the red and green colors in each pixel");

		greenBlueSwapFilterItem = new JMenuItem("Green Blue Swap");
		greenBlueSwapFilterItem.addActionListener(this);
		colorFilterMenu.add(greenBlueSwapFilterItem);
		greenBlueSwapFilterItem.setToolTipText("Swaps the green and blue colors in each pixel");

		funkyFilterItem = new JMenuItem("Funky");
		funkyFilterItem.addActionListener(this);
		colorFilterMenu.add(funkyFilterItem);
		funkyFilterItem.setToolTipText("Makes the image look funky");

		// sliders
		biasSlider = new JSlider(-255, 255, 0); 
		biasMenu = new JMenu("Bias");
		biasMenu.add(biasSlider);
		biasSlider.setToolTipText("Changes the brightness of the image. Zero does nothing to the image.");
		biasSlider.setMajorTickSpacing(510 / 5); 
		biasSlider.setMinorTickSpacing(510 / 10); 
		biasSlider.setPaintTicks(true);
		biasSlider.setPaintLabels(true);
		biasSlider.addChangeListener(this);
		biasMenu.setEnabled(false);
		imageFilterMenu.add(biasMenu);

		gainSlider = new JSlider(-100, 100, 0);
		gainMenu = new JMenu("Gain");
		gainMenu.add(gainSlider);
		gainSlider.setToolTipText("Changes the contrast of the image. Zero does nothing to the image.");
		gainSlider.setMajorTickSpacing(50);
		gainSlider.setMinorTickSpacing(25);
		gainSlider.setPaintTicks(true);
		gainSlider.setPaintLabels(true);
		gainSlider.addChangeListener(this);
		gainMenu.setEnabled(false);	
		imageFilterMenu.add(gainMenu);

		blurSlider = new JSlider(0, 5, 0); 
		blurMenu = new JMenu("Blur");
		blurMenu.add(blurSlider);
		blurSlider.setToolTipText("Obscures the details of the picture. Zero does nothing to the image.");
		blurSlider.setMajorTickSpacing(1); 
		blurSlider.setSnapToTicks(true);
		blurSlider.setPaintTicks(true);
		blurSlider.setPaintLabels(true);
		blurSlider.addChangeListener(this);
		blurMenu.setEnabled(false);
		imageFilterMenu.add(blurMenu);

		// menu bar
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(optionsMenu);
		menuBar.add(imageFilterMenu);
		menuBar.add(colorFilterMenu);
		setJMenuBar(menuBar); 

		// set up everything
		setContentPane(pane);
		setTitle("Nick's Image Processor");
		setPreferredSize(new Dimension(900, 600));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		this.setVisible(true);
	}

	/**
	 * Executes when a button is clicked and executes the requisite action for each button. 
	 */
	public void actionPerformed(ActionEvent arg0) {
		JMenuItem item = (JMenuItem)arg0.getSource();

		if(item == openItem)
			open();
		else if(item == saveItem) 
			UsefulStuff.save(filteredImage);
		else if(item == cumulativeItem){
			if(isCumulative){ // if on turn off
				isCumulative = false;
				cumulativeItem.setText("Cumulative Changes: Off");
				gainMenu.setEnabled(true); // they can't handle the cumulative setting
				biasMenu.setEnabled(true);
			}
			else{ // if off turn on
				isCumulative = true;
				cumulativeItem.setText("Cumulative Changes: On");
				gainMenu.setEnabled(false);
				biasMenu.setEnabled(false);
			}
		}
		// image
		else if(item == cropItem){
			filter = new CropFilter();
			isFiltered = true;
		}
		else if(item == mirrorLRFilterItem){
			filter = new MirrorFilter(true);
			isFiltered = true;
		}
		else if(item == mirrorUDFilterItem){
			filter = new MirrorFilter(false);
			isFiltered = true;
		}
		else if(item == rotateClockwiseFilterItem){
			filter = new RotateClockwiseFilter();
			isFiltered = true;
		}
		else if(item == rotateCounterClockwiseFilterItem){
			filter = new RotateCounterClockwiseFilter();
			isFiltered = true;
		}
		// color
		else if(item == blackWhiteFilterItem){
			filter = new BlackAndWhiteFilterR();
			isFiltered = true;
		}
		else if(item == maxRedFilterItem){
			filter = new MaximizeRedFilterR();
			isFiltered = true;
		}
		else if(item == maxBlueFilterItem){
			filter = new MaximizeBlueFilterR();
			isFiltered = true;
		}
		else if(item == maxGreenFilterItem){
			filter = new MaximizeGreenFilterR();
			isFiltered = true;
		}
		else if(item == redBlueSwapFilterItem){
			filter = new RedBlueSwapFilterR();
			isFiltered = true;
		}
		else if(item == redGreenSwapFilterItem){
			filter = new RedGreenSwapFilterR();
			isFiltered = true;
		}
		else if(item == greenBlueSwapFilterItem){
			filter = new GreenBlueSwapFilterR();
			isFiltered = true;
		}
		else if(item == funkyFilterItem){
			filter = new FunkyFilterR();
			isFiltered = true;
		}
		updateFilter();
	}

	/**
	 * Actions to be performed when the image is filtered.  
	 */
	private void updateFilter(){ 
		if (filter instanceof ImageRegionFilter) { // sets the region if it is a region filter
			Region2d r = oPanel.getSelectedRegion();
			if(isCumulative)
				r = fPanel.getSelectedRegion();
			else
				r = oPanel.getSelectedRegion();
			((ImageRegionFilter) filter).setRegion(r);
		}

		if(originalImage != null){ // protects against closing the open file chooser without opening a file
			if(!isCumulative){ // toggles the cumulative effect
				if(filter != null){ // prevents filters from acting before being chosen 
					filteredImage = filter.filter(originalImage);
					filterMessage = filter.toString() + ""; // resets the tool tip message 
				}
				oPanel.setSelectingEnabled(true); // can only select the image to be filtered 
			}
			else { // if cumulative 
				if(filter != null){ 
					filteredImage = filter.filter(filteredImage);
					filterMessage += ", " + filter.toString();
				}
				oPanel.setSelectingEnabled(false);
			}
			filter = null;
		}

		if(isFiltered){// Control how many tabs are open at once.
			pane.removeAll(); // resets the tabs
			fPanel = new ImagePanel(filteredImage, this);
			pane.add("Filtered image", fPanel);
			fPanel.setToolTipText(filterMessage); // reports a list of the filters applied
			pane.add("Original image", oPanel);
			oPanel.setToolTipText(getFile.getSelectedFile().getPath()); 
			saveItem.setEnabled(true); // can only save and set cumulative effect after a filter has been applied
			cumulativeItem.setEnabled(true); 
		}
	}

	/**
	 * Enables or disables buttons that can only be applied to the whole image. 
	 * @param enabled true enables the crop button and disables the other Image buttons and the Blur menu
	 */
	public void setEnableNonRegionButtons(boolean enabled) {
		this.cropItem.setEnabled(enabled);
		this.mirrorLRFilterItem.setEnabled(!enabled);
		this.mirrorUDFilterItem.setEnabled(!enabled);
		this.rotateClockwiseFilterItem.setEnabled(!enabled);
		this.rotateCounterClockwiseFilterItem.setEnabled(!enabled);
		this.blurMenu.setEnabled(!enabled); 
	} 

	/**
	 * Reports whether the Cumulative button has been turn on. 
	 * @return true if the Cumulative button is on
	 */
	public boolean isCumulative() {
		return isCumulative;
	}

	/**
	 * Performs the specified actions when the sliders are changed.
	 */
	public void stateChanged(ChangeEvent arg0) {
		JSlider src = (JSlider)arg0.getSource();
		if(src == biasSlider){
			BiasFilterR b = new BiasFilterR();
			b.setLevel(src.getValue());
			filter = b;
			isFiltered = true;
		}
		else if(src == gainSlider){
			GainFilterR g = new GainFilterR();
			g.setLevel(src.getValue());
			filter = g;
			isFiltered = true;
		}
		else if(src == blurSlider){ 
			if(!src.getValueIsAdjusting()) {
				BlurFilter2 g = new BlurFilter2();
				g.setLevel(src.getValue());
				filter = g;
				isFiltered = true;
			}
		}
		updateFilter();
	}

	/**
	 * Opens an image to work on. 
	 */
	private void open(){
		try {
			FileNameExtensionFilter extentionFilter = new FileNameExtensionFilter(".jpg, .jpeg, .png, .bmp, .gif", "jpg", "jpeg", "png", "bmp", "gif");
			getFile = new JFileChooser();
			getFile.addChoosableFileFilter(extentionFilter);
			getFile.setFileFilter(extentionFilter);
			if(getFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
				if(extentionFilter.accept(getFile.getSelectedFile())){ // only allows files in the extension filter 
					originalImage = ImageIO.read(getFile.getSelectedFile());
					filter = new NoFilter(); // does nothing to the image
					filteredImage = filter.filter(originalImage); // catches files that can't be read
					pane.removeAll(); // resets the tabs
					oPanel = new ImagePanel(originalImage, this);
					pane.add("Original image", oPanel);
					oPanel.setToolTipText(getFile.getSelectedFile().getPath()); // identifies which is the original image
					isFiltered = false; // used to determine how many tabs should be open
					saveItem.setEnabled(false); 
					imageFilterMenu.setEnabled(true); // only enabled when there is an image open
					colorFilterMenu.setEnabled(true);
					blurMenu.setEnabled(true);
					biasMenu.setEnabled(true);
					gainMenu.setEnabled(true);
				}
				else
					throw new NullPointerException();
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "I'm sorry, I could not open your file. ");
		}
	}

	/**
	 * Starts the program from this file
	 * @param args does nothing
	 */
	public static void main(String[] args){
		new ImageProcessor();
	}
}
