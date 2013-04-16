package rocks6205.editor.viewcomponents;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.events.LSAction.FillAction;
import rocks6205.editor.mvc.SVGEditorView;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.JToolBar;
import javax.swing.SwingConstants;

/**
 *
 * @author Cheow Yeong Chi
 *
 * @since 2.2
 */
public final class LSUISideToolbar extends JToolBar implements LSUIProtocol {

    /*
     * PARENT COMPONENT
     */
    private SVGEditorView parentView;

    /*
     * GUI COMPONENTS
     */
    private LSUIButton insertRectButton;
    private LSUIButton insertCircButton;
    private LSUIButton insertLineButton;     // group 1
    private LSUIButton fillButton;
    private LSUIButton strokeButton;
    private LSUIButton strokeWidthButton;    // group 2
    private LSUIButton groupButton;
    private LSUIButton ungroupButton;        // group 3

    /*
     * ACTION COMPONENTS
     */
    private FillAction fillAction;

    public LSUISideToolbar(String name, SVGEditorView parent) {
        super(name);
        setParentView(parent);
        initialise();
        customise();
    }

    @Override
    public void initialise() {
        insertRectButton  = new LSUIButton();
        insertCircButton  = new LSUIButton();
        insertLineButton  = new LSUIButton();
        fillButton        = new LSUIButton();
        strokeButton      = new LSUIButton();
        strokeWidthButton = new LSUIButton();
        groupButton       = new LSUIButton();
        ungroupButton     = new LSUIButton();
        fillAction        = new FillAction(parentView);
    }

    @Override
    public void customise() {
        layoutView();
        setActionForButtons();
        setIconsForButtons();
        setFloatable(false);
        setRollover(true);
        setOrientation(SwingConstants.VERTICAL);
    }

    private void setParentView(SVGEditorView parent) {
        parentView = parent;
    }

    /**
     * Setup actions for button.
     */
    private void setActionForButtons() {
        fillButton.setAction(getFillAction());
    }

    /**
     * Setup icons for button.
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

    /*
     * ACCESORS
     */
    private FillAction getFillAction() {
        if (fillAction == null) {
            fillAction = new FillAction(parentView);
        }

        return fillAction;
    }

    private void layoutView() {
        add(insertRectButton);
        add(insertCircButton);
        add(insertLineButton);
        addSeparator();
        add(fillButton);
        add(strokeButton);
        addSeparator();
        add(strokeWidthButton);
        addSeparator();
        add(groupButton);
        add(ungroupButton);
    }
}
