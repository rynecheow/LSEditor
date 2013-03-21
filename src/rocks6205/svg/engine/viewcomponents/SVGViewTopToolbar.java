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
	SVGViewButton newButton, openButton, saveButton, zoomInButton, zoomOutButton;

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

	private void initialise() {
		newButton = new SVGViewButton("", "imageicon/newfile.png");
		openButton = new SVGViewButton("", "imageicon/openfile.png");
		saveButton = new SVGViewButton("", "imageicon/save.png");
		zoomInButton = new SVGViewButton("","imageicon/zoomin.png");
		zoomOutButton = new SVGViewButton("", "imageicon/zoomout.png");
	}
}
