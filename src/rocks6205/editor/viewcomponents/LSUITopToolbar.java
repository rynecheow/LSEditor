package rocks6205.editor.viewcomponents;

//~--- non-JDK imports --------------------------------------------------------

import java.awt.Color;
import java.awt.Dimension;
import rocks6205.editor.events.LSAction.OpenFileAction;
import rocks6205.editor.events.LSAction.ZoomInViewAction;
import rocks6205.editor.events.LSAction.ZoomOutViewAction;
import rocks6205.editor.mvc.SVGEditorView;

import rocks6205.system.properties.LSSVGEditorGUITheme;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.JLabel;
import javax.swing.JPanel;
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
    private SVGEditorView parentView;

    /*
     * GUI COMPONENTS
     */
    private LSUIButton    newButton;
    private LSUIButton    openButton;
    private LSUIButton    saveButton;
    private LSUIButton    zoomInButton;
    private LSUIButton    zoomOutButton;
    private LSUIIconLabel fillLabel;
    private LSUIIconLabel strokeLabel;
    private JPanel fillButton;
    private LSUIButton strokeButton;
    /*
     * ACTION COMPONENTS
     */
    private ZoomInViewAction  zoomInAction;
    private ZoomOutViewAction zoomOutAction;
    private OpenFileAction    openAct;

    public LSUITopToolbar(String name, SVGEditorView parent) {
        super(name);
        setParentView(parent);
        initialise();
        customise();
    }

    @Override
    public void initialise() {
        newButton     = LSUIButton.create();
        openButton    = LSUIButton.create();
        saveButton    = LSUIButton.create();
        zoomInButton  = LSUIButton.create();
        zoomOutButton = LSUIButton.create();
        fillLabel     = new LSUIIconLabel();
        strokeLabel   = new LSUIIconLabel();
        fillButton= new JPanel();
                strokeButton= LSUIButton.create();
        openAct       = new OpenFileAction(parentView);
        zoomInAction  = new ZoomInViewAction(parentView);
        zoomOutAction = new ZoomOutViewAction(parentView);
    }

    @Override
    public void customise() {
        layoutChildComponents();
        layoutView();
        
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
        zoomInAction.setZoomOutPartnerAction(zoomOutAction);
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
        String fillIconPath     = "imageicon/fill.png";
        String strokeIconPath   = "imageicon/stroke.png";

        newButton.setIcon(newFileIconPath);
        openButton.setIcon(openFileIconPath);
        saveButton.setIcon(saveFileIconPath);
        zoomInButton.setIcon(zoomInIconPath);
        zoomOutButton.setIcon(zoomOutIconPath);
        fillLabel.setIcon(fillIconPath);
        strokeLabel.setIcon(strokeIconPath);
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

    private void layoutView() {
        setActionForButtons();
        setIconsForButtons();
        setBorder(LSSVGEditorGUITheme.MASTER_DEFAULT_PANEL_BORDER);
        addSeparator();
        add(newButton);
        add(openButton);
        add(saveButton);
        addSeparator();
        add(zoomInButton);
        add(zoomOutButton);
        addSeparator();
        add(fillLabel);
        add(fillButton);
        add(strokeLabel);
        add(strokeButton);
        addSeparator();
        
        setFloatable(false);
        setRollover(true);
    }

    private void layoutChildComponents() {
        fillButton.setMaximumSize(new Dimension(25, 25));
        fillButton.setMinimumSize(new Dimension(25, 25));
        fillButton.setBackground(Color.red);
        fillButton.setBorder(LSSVGEditorGUITheme.MASTER_DEFAULT_PANEL_BORDER);
        fillButton.setFocusable(false);
        strokeButton.setSize(new Dimension(30, 30));
        strokeButton.setBackground(Color.red);
    }
}