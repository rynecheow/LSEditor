/**
 *
 * Class: SVGView
 * Description: General layout of the GUI
 *
 * @author: Toh Huey Jing
 * @version: 1.0
 * @date: 17/03/2013
 *
 */

package rocks6205.svg.engine;

import java.awt.*;
import javax.swing.*;

public class SVGView extends JFrame {

	JPanel panel, panelTop, panelLeft, panelRight, panelBottom;
	JPanel inPanel, inPanelTop, inPanelLeft, inPanelRight, inPanelBottom;

	public SVGView() {

		panel = new JPanel();
		panelTop = new JPanel();
		panelLeft = new JPanel();
		panelRight = new JPanel();
		panelBottom = new JPanel();
		
		inPanel = new JPanel();
		inPanelTop = new JPanel();
		inPanelLeft = new JPanel();
		inPanelRight = new JPanel();
		inPanelBottom = new JPanel();

		Container c = getContentPane();
		
		c.setLayout(new BorderLayout());
		panel.setLayout(new BorderLayout());
		
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panelTop.setBorder(BorderFactory.createLineBorder(Color.black));
		panelLeft.setBorder(BorderFactory.createLineBorder(Color.black));
		panelRight.setBorder(BorderFactory.createLineBorder(Color.black));
		panelBottom.setBorder(BorderFactory.createLineBorder(Color.black));
		
		inPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
		inPanelTop.setBorder(BorderFactory.createLineBorder(Color.blue));
		inPanelLeft.setBorder(BorderFactory.createLineBorder(Color.blue));
		inPanelRight.setBorder(BorderFactory.createLineBorder(Color.blue));
		inPanelBottom.setBorder(BorderFactory.createLineBorder(Color.blue));
		
		panel.add(inPanel);
		panel.add(inPanelTop, BorderLayout.NORTH);
		panel.add(inPanelLeft, BorderLayout.WEST);
		panel.add(inPanelRight, BorderLayout.EAST);
		panel.add(inPanelBottom, BorderLayout.SOUTH);
		
		c.add(panel);
		c.add(panelTop, BorderLayout.NORTH);
		c.add(panelLeft, BorderLayout.WEST);
		c.add(panelRight, BorderLayout.EAST);
		c.add(panelBottom, BorderLayout.SOUTH);
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		setTitle("SVG Editor");
		setVisible(true);
		setResizable(false);
	}

	public static void main (String args[]) {
		SVGView view = new SVGView();
	}

}
