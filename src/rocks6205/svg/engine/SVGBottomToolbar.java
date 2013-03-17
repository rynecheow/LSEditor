/**
 * 
 * Class: SVGBottomToolbar
 * Description: Toolbar containing all the SVG editing functions as required in Assignment 4.
 * 
 * @author: Toh Huey Jing
 * @version: 1.0
 * @date:17/03/2013
 * 
 */

package rocks6205.svg.engine;

import java.awt.*;
import javax.swing.*;

public class SVGBottomToolbar extends JPanel {
	
	JButton rectBut, circleBut, lineBut;
	JButton fillBut, strokeBut, widthBut;
	JButton groupBut, ungroupBut;
//	JButton delBut;
	
	JPanel panel;
	
	JSeparator separate, separate2;
	
	public SVGBottomToolbar() {
		
		rectBut = new JButton("Rectangle");
		circleBut = new JButton("Circle");
		lineBut = new JButton("Line");
		
		fillBut = new JButton("Fill Colour");
		strokeBut = new JButton("Stroke Colour");
		widthBut = new JButton("Stroke Width");
		
		groupBut = new JButton("Group");
		ungroupBut = new JButton("Ungroup");
		
//		delBut = new JButton("Delete");
		
		panel = new JPanel();
		
		separate = new JSeparator(SwingConstants.VERTICAL);
		separate2 = new JSeparator(SwingConstants.VERTICAL);
		
		Dimension d = separate.getPreferredSize();
		d.height = rectBut.getPreferredSize().height * 2;
		
		separate.setPreferredSize(d);
		separate2.setPreferredSize(d);
		
		panel.add(rectBut);
		panel.add(circleBut);
		panel.add(lineBut);
		panel.add(separate);
		panel.add(fillBut);
		panel.add(strokeBut);
		panel.add(widthBut);
		panel.add(separate2);
		panel.add(groupBut);
		panel.add(ungroupBut);
		
		this.add(panel);
//		this.add(delBut);
		
	}


}
