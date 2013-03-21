/**
 * 
 * Class: SVGViewBottomToolbar
 * Description: Toolbar containing all the SVG editing functions as required in Assignment 4.
 * 
 * @author: Toh Huey Jing
 * @date:17/03/2013
 * 
 */

package rocks6205.svg.engine.viewcomponents;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;


public class SVGViewBottomToolbar extends JPanel {

	private static final long serialVersionUID = 4968792782186848487L;

	/*
	 * GUI COMPONENTS
	 */
	SVGViewButton insertRectButton, insertCircButton,insertLineButton;	//group 1
	SVGViewButton fillButton, strokeButton, strokeWidthButton;			//group 2
	SVGViewButton groupButton, ungroupButton;								//group 3

	JPanel backgroundPanel;

	JSeparator separator_1, separator_2;

	/*
	 * CONSTRUCTOR
	 */
	public SVGViewBottomToolbar() {
		initialise();
		setupSeparators();
		layoutBackgroundPanel();
		add(backgroundPanel);
		disableUnused();
	}

	private void disableUnused() {
		insertRectButton.setEnabled(false);
		insertCircButton.setEnabled(false);
		insertLineButton.setEnabled(false);
		fillButton.setEnabled(false);
		strokeButton.setEnabled(false);
		strokeWidthButton.setEnabled(false);
		groupButton.setEnabled(false);
		ungroupButton.setEnabled(false);
	}

	/**
	 * Initialisation of GUI components
	 */
	private void initialise() {
		insertRectButton = new SVGViewButton("", "imageicon/rectangle.png");
		insertCircButton = new SVGViewButton("", "imageicon/circle.png");
		insertLineButton = new SVGViewButton("", "imageicon/line.png");

		fillButton = new SVGViewButton("", "imageicon/fillcolor.jpg");
		strokeButton = new SVGViewButton("", "imageicon/stroke.jpg");
		strokeWidthButton = new SVGViewButton("", "imageicon/width.jpg");

		groupButton = new SVGViewButton("", "imageicon/grouped.jpg");
		ungroupButton = new SVGViewButton("", "imageicon/ungrp.jpg");

		backgroundPanel = new JPanel();

		separator_1 = new JSeparator(SwingConstants.VERTICAL);
		separator_2 = new JSeparator(SwingConstants.VERTICAL);
	}

	/**
	 * Set separator size
	 */
	private void setupSeparators() {
		Dimension d = separator_1.getPreferredSize();
		d.height = insertRectButton.getPreferredSize().height * 2;
		separator_1.setPreferredSize(d);
		separator_2.setPreferredSize(d);
	}

	/**
	 * Layouts the background panel with GUI components
	 */
	private void layoutBackgroundPanel() {
		backgroundPanel.add(insertRectButton);
		backgroundPanel.add(insertCircButton);
		backgroundPanel.add(insertLineButton);
		backgroundPanel.add(separator_1);
		backgroundPanel.add(fillButton);
		backgroundPanel.add(strokeButton);
		backgroundPanel.add(strokeWidthButton);
		backgroundPanel.add(separator_2);
		backgroundPanel.add(groupButton);
		backgroundPanel.add(ungroupButton);
	}
}


