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
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

public class SVGView extends JFrame implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6764861773639452353L;
	
	/*
	 * PROPERTIES
	 */
	private SVGModel model;
	private SVGViewController controller;
	
	/*
	 * GUI COMPONENTS
	 */
	SVGViewMenu menuBar;
	
	JPanel panel, panelTop, panelLeft, panelRight, panelBottom;
	JPanel inPanel, inPanelTop, inPanelLeft, inPanelRight, inPanelBottom;

	/*
	 * CONSTRUCTOR
	 */
	public SVGView() {
		
		menuBar = new SVGViewMenu();

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
		
		setupLayout(panel, inPanel, inPanelTop, inPanelBottom, inPanelRight, inPanelLeft);
		setupLayout(c, panel, panelTop, panelBottom, panelRight, panelLeft);
		
		this.setJMenuBar(menuBar);
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		setTitle("SVG Editor");
		setVisible(true);
		setResizable(false);
	}

	public static void main (String args[]) {
		new SVGView();
	}

	/*
	 * ACCESSORS
	 */
	public SVGModel getModel() {
		return model;
	}
	
	public SVGViewController getController() {
		return controller;
	}

	/*
	 * MUTATORS
	 */
	public void setController(SVGViewController controller) {
		this.controller = controller;
	}

	public void setModel(SVGModel model) {
		this.model = model;
	}

	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 *  Set components via BorderLayout to Center, North, South, East and West
	 */
	public void setupLayout(JPanel pane, JPanel pane1, JPanel pane2, JPanel pane3, JPanel pane4, JPanel pane5) {
		pane.add(pane1, BorderLayout.CENTER);
		pane.add(pane2, BorderLayout.NORTH);
		pane.add(pane3, BorderLayout.SOUTH);
		pane.add(pane4, BorderLayout.EAST);
		pane.add(pane5, BorderLayout.WEST);
	}
	
	public void setupLayout(Container contain, JPanel pane1, JPanel pane2, JPanel pane3, JPanel pane4, JPanel pane5) {
		contain.add(pane1, BorderLayout.CENTER);
		contain.add(pane2, BorderLayout.NORTH);
		contain.add(pane3, BorderLayout.SOUTH);
		contain.add(pane4, BorderLayout.EAST);
		contain.add(pane5, BorderLayout.WEST);
	}

}
