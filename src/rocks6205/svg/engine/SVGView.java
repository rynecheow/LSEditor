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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import rocks6205.svg.engine.viewcomponents.SVGViewDeleteAccessoryPanel;
import rocks6205.svg.engine.viewcomponents.SVGViewTopToolbar;
import rocks6205.svg.engine.viewcomponents.SVGViewBottomToolbar;
import rocks6205.svg.engine.viewcomponents.SVGViewMenubar;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

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
	JPanel inPanel, inPanelTop, inPanelLeft, inPanelRight, inPanelBottom;

	Container container = getContentPane();

	/*
	 * CONSTRUCTOR
	 */
	public SVGView() {
		initialise();
		setupBorder();
		panelTop.add(topTool, BorderLayout.WEST);
		inPanelBottom.add(bottomTool, BorderLayout.WEST);
		inPanelBottom.add(delete, BorderLayout.EAST);
		setupLayoutForMainPanel();
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
		menuBar = new SVGViewMenubar();
		topTool = new SVGViewTopToolbar();
		bottomTool = new SVGViewBottomToolbar();
		delete = new SVGViewDeleteAccessoryPanel();

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

		container.setLayout(new BorderLayout());
		panel.setLayout(new BorderLayout());
		panelTop.setLayout(new BorderLayout());
		inPanelBottom.setLayout(new BorderLayout());
	}

	/**
	 * Setting border color of JPanels
	 */
	private void setupBorder(){
		Color blueColor = Color.BLUE, blackColor = Color.black;
		setBorderColorForPanel(blackColor,panel);
		setBorderColorForPanel(blackColor,panelTop);
		setBorderColorForPanel(blackColor,panelLeft);
		setBorderColorForPanel(blackColor,panelRight);
		setBorderColorForPanel(blackColor,panelBottom);

		setBorderColorForPanel(blueColor,inPanel);
		setBorderColorForPanel(blueColor,inPanelTop);
		setBorderColorForPanel(blueColor,inPanelLeft);
		setBorderColorForPanel(blueColor,inPanelRight);
		setBorderColorForPanel(blueColor,inPanelBottom);
	}

	private void setBorderColorForPanel(Color color, JPanel p){
		p.setBorder(BorderFactory.createLineBorder(color));
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
	}

	private void setupLayoutForMainPanel(){
		panel.add(inPanel, BorderLayout.CENTER);
		panel.add(inPanelTop, BorderLayout.NORTH);
		panel.add(inPanelBottom, BorderLayout.SOUTH);
		panel.add(inPanelRight, BorderLayout.EAST);
		panel.add(inPanelLeft, BorderLayout.WEST);
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

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}
