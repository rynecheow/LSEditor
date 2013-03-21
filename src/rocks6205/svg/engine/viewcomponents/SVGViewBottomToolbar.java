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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;


public class SVGViewBottomToolbar extends JPanel {

	private static final long serialVersionUID = 4968792782186848487L;

	/*
	 * GUI COMPONENTS
	 */
	JButton insertRectButton, insertCircButton,insertLineButton;	//group 1
	JButton fillButton, strokeButton, strokeWidthButton;			//group 2
	JButton groupButton, ungroupButton;								//group 3

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
	}

	/**
	 * Initialisation of GUI components
	 */
	private void initialise() {
		insertRectButton = new JButton("", createImageIcon("imageicon/rectangle.jpg"));
		insertCircButton = new JButton("", createImageIcon("imageicon/circle.jpg"));
		insertLineButton = new JButton("", createImageIcon("imageicon/line.jpg"));

		fillButton = new JButton("", createImageIcon("imageicon/fillcolor.jpg"));
		strokeButton = new JButton("", createImageIcon("imageicon/stroke.jpg"));
		strokeWidthButton = new JButton("", createImageIcon("imageicon/width.jpg"));

		groupButton = new JButton("", createImageIcon("imageicon/grouped.jpg"));
		ungroupButton = new JButton("", createImageIcon("imageicon/ungrp.jpg"));

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

	/**
	 * image insertion
	 */

	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = SVGViewBottomToolbar.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Could not find file: " + path);
			return null;
		}
	}

}


