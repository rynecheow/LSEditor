/**
 * 
 * Class: SVGViewDeleteAccessoryPanel
 * Description: JPanel containing the Delete button.
 * 
 * @author: Toh Huey Jing
 * @date: 18/03/2013
 * 
 */

package rocks6205.svg.engine.viewcomponents;

import javax.swing.JPanel;



public class SVGViewDeleteAccessoryPanel extends JPanel {

	private static final long serialVersionUID = 4013696005435780830L;

	/*
	 * GUI COMPONENTS
	 */
	SVGViewButton deleteButton;

	/*
	 * CONSRTUCTOR
	 */
	public SVGViewDeleteAccessoryPanel() {
		initialise();
		add(deleteButton);
		deleteButton.setEnabled(false);
	}

	/**
	 * Initialisation of GUI components
	 */
	private void initialise() {
		deleteButton = new SVGViewButton("", "imageicon/delete.png");
	}
}
