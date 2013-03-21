/**
 * 
 * Class: SVGViewTopToolbar
 * Description: The toolbar containing the New, Open File and Save File icons at the top of the screen.
 * 
 * @author: Toh Huey Jing
 * @version: 1.0
 * @date: 17/03/2013
 * 
 */

package rocks6205.svg.engine.viewcomponents;

import javax.swing.JButton;
import javax.swing.JPanel;



public class SVGViewTopToolbar extends JPanel {

	private static final long serialVersionUID = -295721542850966526L;
	/*
	 * GUI COMPONENTS
	 */
	JButton newButton, openButton, saveButton;

	/*
	 * CONSTRUCTOR
	 */
	public SVGViewTopToolbar() {
		initialise();
		add(newButton);
		add(openButton);
		add(saveButton);
	}

	/**
	 * 
	 */
	private void initialise() {
		newButton = new JButton("New");
		openButton = new JButton("Open");
		saveButton = new JButton("Save");
	}

}
