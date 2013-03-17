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
	
	JButton newBut, openBut, saveBut;
	
	public SVGTopToolbar() {
		
		newBut = new JButton("New");
		openBut = new JButton("Open");
		saveBut = new JButton("Save");
		
		this.add(newBut, FlowLayout.LEFT);
		this.add(openBut, FlowLayout.LEFT);
		this.add(saveBut, FlowLayout.LEFT);
	}

}
