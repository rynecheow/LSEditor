/**
 * 
 * Class: SVGViewTopToolbar
 * Description: The toolbar containing the New, Open File and Save File icons at the top of the screen.
 * 
 * @author: Toh Huey Jing
 * @date: 17/03/2013
 * 
 */

package rocks6205.svg.engine.viewcomponents;

import javax.swing.*;
import javax.swing.JPanel;



public class SVGViewTopToolbar extends JPanel {

	private static final long serialVersionUID = -295721542850966526L;
	/*
	 * GUI COMPONENTS
	 */
	JButton newButton, openButton, saveButton, zoomInButton, zoomOutButton;

	/*
	 * CONSTRUCTOR
	 */
	public SVGViewTopToolbar() {
		initialise();
		disableUnused();
		add(newButton);
		add(openButton);
		add(saveButton);
		add(zoomInButton);
		add(zoomOutButton);
	}

	private void disableUnused() {
		newButton.setEnabled(false);
		saveButton.setEnabled(false);
	}

	/**
	 * 
	 */
	private void initialise() {
		newButton = new JButton("", createImageIcon("imageicon/newfile.jpg"));
		openButton = new JButton("", createImageIcon("imageicon/openfile.jpg"));
		saveButton = new JButton("", createImageIcon("imageicon/save.jpg"));
		zoomInButton = new JButton("", createImageIcon("imageicon/zoomin.png"));
		zoomOutButton = new JButton("", createImageIcon("imageicon/zoomout.png"));
	}
	
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = SVGViewTopToolbar.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Could not find file: " + path);
			return null;
		}
	}


}
