package rocks6205.svg.editor.viewcomponents;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.svg.editor.events.SVGEditorMenuAction.FillAction;
import rocks6205.svg.mvc.SVGEditorView;
import rocks6205.svg.properties.SVGEditorTheme;


//~--- JDK imports ------------------------------------------------------------

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

/**
 *
 * Toolbar containing all the SVG editing functions as required in for editing.
 *
 * @author Toh Huey Jing
 * @author Sugar CheeSheen Chan
 *
 * @since 1.2
 *
 */
public class SVGEditorBottomToolbar extends JPanel {
    private static final long serialVersionUID = 4968792782186848487L;

    /**
     * Parent component
     */
    @SuppressWarnings("unused")
    private SVGEditorView parent;

    /*
     * GUI COMPONENTS
     */
    SVGEditorButton insertRectButton, insertCircButton, insertLineButton;    // group 1
    SVGEditorButton fillButton, strokeButton, strokeWidthButton;             // group 2
    SVGEditorButton groupButton, ungroupButton;                              // group 3
    JPanel          backgroundPanel;
    JSeparator      separator_1, separator_2;

    /*
     * ACTION COMPONENTS
     */
    private FillAction fillAction;

    /*
     * CONSTRUCTOR
     */

    /**
     * Constructs a toolbar with parent component <code>view</code> and
     * with components initialised and properly customised.
     * @param view Parent component
     */
    public SVGEditorBottomToolbar(SVGEditorView view) {
        super();
        parent = view;
        initialise();
        customise();
    }

    /**
     * Initialisation of GUI components.
     */
    private void initialise() {
        insertRectButton  = new SVGEditorButton();
        insertCircButton  = new SVGEditorButton();
        insertLineButton  = new SVGEditorButton();
        fillButton        = new SVGEditorButton();
        strokeButton      = new SVGEditorButton();
        strokeWidthButton = new SVGEditorButton();
        groupButton       = new SVGEditorButton();
        ungroupButton     = new SVGEditorButton();
        backgroundPanel   = new JPanel();
        separator_1       = new JSeparator(SwingConstants.VERTICAL);
        separator_2       = new JSeparator(SwingConstants.VERTICAL);
        fillAction        = new FillAction(parent);
    }

    /**
     * Customisation of GUI components.
     */
    private void customise() {
        setBackground(SVGEditorTheme.MASTER_DEFAULT_COLOR);
        setupSeparators();
        layoutBackgroundPanel();
        add(backgroundPanel);
        setActionForButtons();
        setIconsForButtons();
    }

    /**
     * Customise image icons for buttons.
     */
    private void setIconsForButtons() {
        String rectangleIconPath   = "imageicon/rectangle.png";
        String circleIconPath      = "imageicon/circle.png";
        String lineIconPath        = "imageicon/line.png";
        String fillColorIconPath   = "imageicon/fillcolor.png";
        String strokeColorIconPath = "imageicon/stroke.png";
        String strokeWidthIconPath = "imageicon/width.png";
        String groupIconPath       = "imageicon/grouped.png";
        String ungroupIconPath     = "imageicon/ungrp.png";

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
     * Set separator size.
     */
    private void setupSeparators() {
        Dimension d = separator_1.getPreferredSize();

        d.height = insertRectButton.getPreferredSize().height * 2;
        separator_1.setPreferredSize(d);
        separator_2.setPreferredSize(d);
    }

    /**
     * Layouts the background panel with GUI components.
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
        backgroundPanel.setBackground(SVGEditorTheme.MASTER_DEFAULT_COLOR);
    }

    /**
     * Setup actions for button.
     */
    private void setActionForButtons() {
	fillButton.setAction(getFillAction());
    }
    
    /**
     * @return Zoom in view action
     */
    private FillAction getFillAction() {
        if (fillAction == null) {
            fillAction = new FillAction(parent);
        }

        return fillAction;
    }
}