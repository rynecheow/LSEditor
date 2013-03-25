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

import rocks6205.svg.engine.SVGView;



public class SVGViewDeleteAccessoryPanel extends JPanel {

	private static final long serialVersionUID = 4013696005435780830L;
	/*
	 * PARENT COMPONENT
	 */
	SVGView parent;
	/*
	 * GUI COMPONENTS
	 */
	SVGViewButton deleteButton;

	/*
	 * CONSRTUCTOR
	 */
	public SVGViewDeleteAccessoryPanel(SVGView view) {
		super();
		parent = view;

		initialise();
		customise();

	}

	/**
	 * Customisation of GUI components
	 */
	private void customise() {
		add(deleteButton);
		deleteButton.setEnabled(false);
		setIconsForButtons();
	}

	/**
	 * Setup icons for buttons
	 */
	private void setIconsForButtons() {
		String deleteIconPath = "imageicon/delete.png";
		deleteButton.setIcon(deleteIconPath);
	}

	/**
	 * Initialisation of GUI components
	 */
	private void initialise() {
		deleteButton = new SVGViewButton();
	}
}
