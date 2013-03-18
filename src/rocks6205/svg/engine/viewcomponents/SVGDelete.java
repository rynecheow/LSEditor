/**
 * 
 * Class: SVGDelete
 * Description: JPanel containing the Delete button.
 * 
 * @author: Toh Huey Jing
 * @version: 1.0
 * @date: 18/03/2013
 * 
 */

package rocks6205.svg.engine.viewcomponents;

import javax.swing.*;

public class SVGDelete extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * GUI COMPONENTS
	 */
	JButton delBut;

	/*
	 * CONSRTUCTOR
	 */
	public SVGDelete() {
		
		delBut = new JButton("Delete");
		
		this.add(delBut);
		
	}

}
