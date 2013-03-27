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

import rocks6205.svg.engine.SVGView;


public class SVGViewBottomToolbar extends JPanel {

    private static final long serialVersionUID = 4968792782186848487L;

    /*
     * PARENT COMPONENT
     */
    @SuppressWarnings("unused")
    private SVGView parent;

    /*
     * GUI COMPONENTS
     */
    private SVGViewButton insertRectButton, insertCircButton,insertLineButton;	//group 1
    private SVGViewButton fillButton, strokeButton, strokeWidthButton;			//group 2
    private SVGViewButton groupButton, ungroupButton;							//group 3
    private JPanel backgroundPanel;
    private JSeparator separator_1, separator_2;

    /*
     * ACTION COMPONENTS
     */


    /*
     * CONSTRUCTOR
     */
    public SVGViewBottomToolbar(SVGView view) {
	super();
	parent = view;

	initialise();
	customise();
    }

    /**
     * Initialisation of GUI components
     */
    private void initialise() {
	insertRectButton = new SVGViewButton();
	insertCircButton = new SVGViewButton();
	insertLineButton = new SVGViewButton();

	fillButton = new SVGViewButton();
	strokeButton = new SVGViewButton();
	strokeWidthButton = new SVGViewButton();

	groupButton = new SVGViewButton();
	ungroupButton = new SVGViewButton();

	backgroundPanel = new JPanel();

	separator_1 = new JSeparator(SwingConstants.VERTICAL);
	separator_2 = new JSeparator(SwingConstants.VERTICAL);
    }

    /**
     * Customisation of GUI components
     */
    private void customise() {
	setupSeparators();
	layoutBackgroundPanel();
	add(backgroundPanel);
	disableUnused();
	setIconsForButtons();
    }

    private void setIconsForButtons() {
	String rectangleIconPath = "imageicon/rectangle.png";
	String circleIconPath = "imageicon/circle.png";
	String lineIconPath = "imageicon/line.png";

	String fillColorIconPath = "imageicon/fillcolor.png";
	String strokeColorIconPath = "imageicon/stroke.png";
	String strokeWidthIconPath = "imageicon/width.png";

	String groupIconPath = "imageicon/grouped.png";
	String ungroupIconPath = "imageicon/ungrp.png";

	insertRectButton.setIcon(rectangleIconPath);
	insertCircButton.setIcon(circleIconPath);
	insertLineButton.setIcon(lineIconPath);

	fillButton.setIcon(fillColorIconPath);
	strokeButton.setIcon(strokeColorIconPath);
	strokeWidthButton.setIcon(strokeWidthIconPath);

	groupButton.setIcon(groupIconPath);
	ungroupButton.setIcon(ungroupIconPath);
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
     * Disable temporarily unused buttons
     */
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
}


