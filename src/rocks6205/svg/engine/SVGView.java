/**
 *
 * Class: SVGView
 * Description: General layout of the GUI
 *
 * @author: Toh Huey Jing
 * @date: 17/03/2013
 *
 */

package rocks6205.svg.engine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import rocks6205.svg.engine.viewcomponents.SVGViewBottomToolbar;
import rocks6205.svg.engine.viewcomponents.SVGViewDeleteAccessoryPanel;
import rocks6205.svg.engine.viewcomponents.SVGViewMenubar;
import rocks6205.svg.engine.viewcomponents.SVGViewTopToolbar;
import rocks6205.svg.engine.viewcomponents.SVGViewport;
import rocks6205.svgFamily.SVGImageCanvas;

public class SVGView extends JFrame implements Observer {

	private static final long serialVersionUID = 6764861773639452353L;

	/*
	 * PROPERTIES
	 */
	private SVGModel model;
	private SVGViewController controller;
	
	/*
	 * GUI COMPONENTS
	 */
	SVGViewMenubar menuBar;
	SVGViewTopToolbar topTool;
	SVGViewBottomToolbar bottomTool;
	SVGViewDeleteAccessoryPanel delete;

	JPanel panel, panelTop, panelLeft, panelRight, panelBottom;
//	JPanel inPanelTop, inPanelLeft, inPanelRight, inPanelBottom;
	SVGViewport renderPanel;

	Container container = getContentPane();
	
	/*
	 * ACTION COMPONENTS
	 */

	/*
	 * CONSTRUCTOR
	 */
	public SVGView() {
		initialise();
		panelTop.add(topTool, BorderLayout.WEST);
		panelBottom.add(bottomTool, BorderLayout.WEST);
		panelBottom.add(delete, BorderLayout.EAST);
		setupLayoutForContainer();
		setJMenuBar(menuBar);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("SVG Editor");
		setVisible(true);
		setResizable(false);
	}

	/**
	 * Initialisation of GUI components
	 */
	private void initialise() {
		menuBar = new SVGViewMenubar(this);
		topTool = new SVGViewTopToolbar();
		bottomTool = new SVGViewBottomToolbar();
		delete = new SVGViewDeleteAccessoryPanel();
		
		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		renderPanel = new SVGViewport(this);
		renderPanel.setPreferredSize(new Dimension(500,500));
		renderPanel.setBackground(Color.WHITE);
		
		panelTop = new JPanel();
		panelLeft = new JPanel();
		panelRight = new JPanel();
		panelBottom = new JPanel();
		
		container.setLayout(new BorderLayout());
		
		panelTop.setLayout(new BorderLayout());
		panelBottom.setLayout(new BorderLayout());
		panel.setLayout(new GridBagLayout());
		panel.add(renderPanel, new GridBagConstraints());
	}

	/**
	 *  Set components via BorderLayout to Center, North, South, East, West
	 */
	private void setupLayoutForContainer() {
		container.add(panel, BorderLayout.CENTER);
		container.add(panelTop, BorderLayout.NORTH);
		container.add(panelBottom, BorderLayout.SOUTH);
		container.add(panelRight, BorderLayout.EAST);
		container.add(panelLeft, BorderLayout.WEST);
		
		panel.add(renderPanel);
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

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof SVGModel) {
			renderPanel.setCanvas((SVGImageCanvas) arg);
			renderPanel.repaint();
		}
	}



}
