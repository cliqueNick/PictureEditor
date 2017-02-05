package assign12;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * A library of useful methods. 
 * @author Nickolas Lee
 *
 */
public class UsefulStuff {

	/**
	 * Saves a filtered image. 
	 */
	public static void save(BufferedImage filteredImage){ 
		// This is meant to be a library to contain useful code so this method 
		// is static with no constructor so that no one can make an object out of this class
		try{
			JFileChooser saveFile = new JFileChooser();
			FileNameExtensionFilter saveExtentionFilter = new FileNameExtensionFilter(".jpg", "jpg");
			saveFile.addChoosableFileFilter(saveExtentionFilter);
			saveFile.setAcceptAllFileFilterUsed(false);
			if(saveFile.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
				File fileToSave = saveFile.getSelectedFile();
				String name = fileToSave.getCanonicalPath();
				if(!name.endsWith(".jpg")) // if dosen't have the .jpg extension it adds it
					name += ".jpg";
				fileToSave = new File(name);
				if(fileToSave.createNewFile() == false){ // protects against overwriting existing files
					int confirm = JOptionPane.showConfirmDialog(null, "Do you want to overwrite this file?");
					if(confirm != JOptionPane.YES_OPTION){
						JOptionPane.showMessageDialog(null, "Nothing was saved. ");
						return;
					}
				}
				ImageIO.write(filteredImage, "jpg", fileToSave);
				JOptionPane.showMessageDialog(null, "Your filtered image was saved. ");
			}
		}catch(Exception e){
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "I'm sorry, I was unable to save your file.");
		}
	}
}
