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
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

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

	JPanel panelTop, panelBottom;
	SVGViewport renderPanel;

	Container container = getContentPane();
	JScrollPane scrollPane;
	Dimension renderAreaSize;
	/*
	 * ACTION COMPONENTS
	 */

	/*
	 * CONSTRUCTOR
	 */
	public SVGView() {
		initialise();
		customise();
		panelTop.add(topTool, BorderLayout.WEST);
		panelBottom.add(bottomTool, BorderLayout.WEST);
		panelBottom.add(delete, BorderLayout.EAST);
		setupLayoutForContainer();
		setJMenuBar(menuBar);
		//		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setSize(1000,1000);
		setTitle("SVG Editor");
		setVisible(true);
		setMinimumSize(new Dimension(700,700));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	private void customise() {
		scrollPane.setBounds(new Rectangle(renderAreaSize));
		renderPanel.setBackground(Color.WHITE);

		container.setLayout(new BorderLayout());
		panelTop.setLayout(new BorderLayout());
		panelBottom.setLayout(new BorderLayout());
	}

	/**
	 * Initialisation of GUI components
	 */
	private void initialise() {
		renderAreaSize = new Dimension(0,0);
		menuBar = new SVGViewMenubar(this);
		topTool = new SVGViewTopToolbar();
		bottomTool = new SVGViewBottomToolbar();
		delete = new SVGViewDeleteAccessoryPanel();

		renderPanel = new SVGViewport(this);
		panelTop = new JPanel();
		panelBottom = new JPanel();
		scrollPane = new JScrollPane(renderPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	}

	/**
	 *  Set components via BorderLayout to Center, North, South, East, West
	 */
	private void setupLayoutForContainer() {
		container.add(scrollPane, BorderLayout.CENTER);
		container.add(panelTop, BorderLayout.NORTH);
		container.add(panelBottom, BorderLayout.SOUTH);
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
			float w = ((SVGModel) o).getSVGElement().getWidth().getValue();
			float h = ((SVGModel) o).getSVGElement().getHeight().getValue();
			renderAreaSize.height = (int) h;
			renderAreaSize.width = (int) w;
			scrollPane.setPreferredSize(renderAreaSize);
			renderPanel.setPreferredSize(renderAreaSize);

			renderPanel.setCanvas((SVGImageCanvas) arg);
			renderPanel.repaint();
			scrollPane.setViewportView(renderPanel);
//			scrollPane.revalidate();
			scrollPane.repaint();
		}
	}



}
