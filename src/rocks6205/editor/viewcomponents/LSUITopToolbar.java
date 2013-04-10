package rocks6205.editor.viewcomponents;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.events.SVGEditorMenuAction.OpenFileAction;
import rocks6205.editor.events.SVGEditorMenuAction.ZoomInViewAction;
import rocks6205.editor.events.SVGEditorMenuAction.ZoomOutViewAction;
import rocks6205.editor.mvc.SVGEditorView;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.JToolBar;

/**
 *
 * @author Cheow Yeong Chi
 *
 * @since 2.2
 */
public final class LSUITopToolbar extends JToolBar implements LSUIProtocol {

    /*
     * PARENT COMPONENT
     */
    SVGEditorView parentView;

    /*
     * GUI COMPONENTS
     */
    LSUIButton newButton;
    LSUIButton openButton;
    LSUIButton saveButton;
    LSUIButton zoomInButton;
    LSUIButton zoomOutButton;

    /*
     * ACTION COMPONENTS
     */
    private ZoomInViewAction  zoomInAction;
    private ZoomOutViewAction zoomOutAction;
    private OpenFileAction    openAct;

    public LSUITopToolbar(String name, SVGEditorView parent) {
        super(name);
        setParentView(parent);
    }

    @Override
    public void initialise() {
        newButton     = new LSUIButton();
        openButton    = new LSUIButton();
        saveButton    = new LSUIButton();
        zoomInButton  = new LSUIButton();
        zoomOutButton = new LSUIButton();
        openAct       = new OpenFileAction(parentView);
        zoomInAction  = new ZoomInViewAction(parentView);
        zoomOutAction = new ZoomOutViewAction(parentView);
    }

    @Override
    public void customise() {
        add(newButton);
        add(openButton);
        add(saveButton);
        add(zoomInButton);
        add(zoomOutButton);
        zoomInAction.setZoomOutPartnerAction(zoomOutAction);
        setActionForButtons();
        setIconsForButtons();
    }

    private void setParentView(SVGEditorView parent) {
        parentView = parent;
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
            zoomInAction = new ZoomInViewAction(parentView, "Zoom In");
        }

        return zoomInAction;
    }

    /**
     * @return Zoom out view action
     */
    private ZoomOutViewAction getZoomOutAction() {
        if (zoomOutAction == null) {
            zoomOutAction = new ZoomOutViewAction(parentView, "Zoom Out");
        }

        return zoomOutAction;
    }
}