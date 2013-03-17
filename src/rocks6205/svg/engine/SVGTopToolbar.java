/**
 * 
 * Class: SVGTopToolbar
 * Description: The toolbar containing the New, Open File and Save File icons at the top of the screen.
 * 
 * @author: Toh Huey Jing
 * @version: 1.0
 * @date: 17/03/2013
 * 
 */

package rocks6205.svg.engine;

import java.awt.*;
import javax.swing.*;

public class SVGTopToolbar extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * GUI COMPONENTS
	 */
	JButton newBut, openBut, saveBut;
	
	/*
	 * CONSTRUCTOR
	 */
	public SVGTopToolbar() {
		
		newBut = new JButton("New");
		openBut = new JButton("Open");
		saveBut = new JButton("Save");
		
		this.add(newBut);
		this.add(openBut);
		this.add(saveBut);
	}

}
