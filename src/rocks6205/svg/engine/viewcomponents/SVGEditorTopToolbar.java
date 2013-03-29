package rocks6205.svg.engine.viewcomponents;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.svg.engine.SVGEditorView;
import rocks6205.svg.engine.events.SVGEditorMenuAction.OpenFileAction;
import rocks6205.svg.engine.events.SVGEditorMenuAction.ZoomInViewAction;
import rocks6205.svg.engine.events.SVGEditorMenuAction.ZoomOutViewAction;

import rocks6205.svgFamily.SVGEditorTheme;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.JPanel;

/**
 *
 * Toolbar containing the New, Open File and Save File icons at the top of the screen.
 *
 * @author Toh Huey Jing
 *
 * @since 1.2
 *
 */
public class SVGEditorTopToolbar extends JPanel {
    private static final long serialVersionUID = -295721542850966526L;

    /**
     * Parent component
     */
    SVGEditorView parent;

    /*
     * GUI COMPONENTS
     */
    SVGEditorButton newButton, openButton, saveButton, zoomInButton, zoomOutButton;

    /*
     * ACTION COMPONENTS
     */
    private ZoomInViewAction  zoomInAction;
    private ZoomOutViewAction zoomOutAction;
    private OpenFileAction    openAct;

    /*
     * CONSTRUCTOR
     */

    /**
     * Constructs a toolbar with parent component <code>view</code> and
     * with components initialised and properly customised.
     *
     * @param view Parent component
     */
    public SVGEditorTopToolbar(SVGEditorView view) {
        super();
        parent = view;
        initialise();
        customise();
    }

    /**
     * Initialisation of GUI components.
     */
    private void initialise() {
        newButton     = new SVGEditorButton();
        openButton    = new SVGEditorButton();
        saveButton    = new SVGEditorButton();
        zoomInButton  = new SVGEditorButton();
        zoomOutButton = new SVGEditorButton();
        openAct       = new OpenFileAction(parent);
        zoomInAction  = new ZoomInViewAction(parent);
        zoomOutAction = new ZoomOutViewAction(parent);
    }

    /**
     * Customisation of GUI components.
     */
    private void customise() {
        add(newButton);
        add(openButton);
        add(saveButton);
        add(zoomInButton);
        add(zoomOutButton);
        disableUnused();
        zoomInAction.setZoomOutPartnerAction(zoomOutAction);
        setActionForButtons();
        setIconsForButtons();
        setBackground(SVGEditorTheme.MASTER_DEFAULT_COLOR);
    }

    /**
     * Disable temporarily unused buttons.
     */
    private void disableUnused() {
        newButton.setEnabled(false);
        saveButton.setEnabled(false);
    }

    /**
     * Enable zoom in button after SVG file is opened sucessfully
     */
    public void enableZoomIn() {
        zoomInButton.setEnabled(true);
    }

    /**
     * Disable zoom out button when it has resumed to its original size of image
     */
    public void setZoomOut(boolean flag) {
        zoomOutButton.setEnabled(flag);
    }

    /**
     * Setup actions for button.
     */
    private void setActionForButtons() {
        openButton.setAction(getOpenAction());
        zoomInButton.setAction(getZoomInAction());
        zoomOutButton.setAction(getZoomOutAction());
    }

    /**
     * Setup icons for button.
     */
    private void setIconsForButtons() {
        String newFileIconPath  = "imageicon/newfile.png";
        String openFileIconPath = "imageicon/openfolder.png";
        String saveFileIconPath = "imageicon/save.png";
        String zoomInIconPath   = "imageicon/zoomin.png";
        String zoomOutIconPath  = "imageicon/zoomout.png";

        newButton.setIcon(newFileIconPath);
        openButton.setIcon(openFileIconPath);
        saveButton.setIcon(saveFileIconPath);
        zoomInButton.setIcon(zoomInIconPath);
        zoomOutButton.setIcon(zoomOutIconPath);
    }

    /*
     * ACCESSORS
     */

    /**
     * @return Open file action
     */
    private OpenFileAction getOpenAction() {
        return openAct;
    }

    /**
     * @return Zoom in view action
     */
    private ZoomInViewAction getZoomInAction() {
        if (zoomInAction == null) {
            zoomInAction = new ZoomInViewAction(parent, "Zoom In");
        }

        return zoomInAction;
    }

    /**
     * @return Zoom out view action
     */
    private ZoomOutViewAction getZoomOutAction() {
        if (zoomOutAction == null) {
            zoomOutAction = new ZoomOutViewAction(parent, "Zoom Out");
        }

        return zoomOutAction;
    }
}