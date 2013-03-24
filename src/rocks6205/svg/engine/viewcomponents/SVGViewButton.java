/**
 * 
 * Class: SVGViewButton
 * Description: 
 * 
 * @author: Komalah Nair
 * @date: 21/03/2013
 * 
 */
package rocks6205.svg.engine.viewcomponents;

import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class SVGViewButton extends JButton {

	private static final long serialVersionUID = -7348665839241378305L;
	
	/*
	 * CONSTRUCTORS
	 */
	public SVGViewButton() {
		setBorder(null);
	}

	public SVGViewButton(Icon icon) {
		super(icon);
		setBorder(null);
	}

	public SVGViewButton(String text) {
		super(text);
		setBorder(null);
	}

	public SVGViewButton(Action a) {
		super(a);
		setBorder(null);
	}

	public SVGViewButton(String text, Icon icon) {
		super(text, icon);
		setBorder(null);
	}
	
	public SVGViewButton(String text, String iconPath) {
		super(text, createIcon(iconPath));
		setBorder(null);
	}

	public SVGViewButton(String text, String iconPath, AbstractAction action){
		super(text, createIcon(iconPath));
		setAction(action);
		setBorder(null);
	}

	protected static ImageIcon createIcon(String path){
		URL imgURL= SVGViewButton.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		}
		System.err.println("Could not find file: " + path);
		return null;
	}
	/**
	 * Sets the button's default icon. 
     * 
	 * @param iconPath
	 */
	public void setIcon(String iconPath){
		ImageIcon icon = createIcon(iconPath);
		super.setIcon(icon);
		setBorder(null);
	}
}
