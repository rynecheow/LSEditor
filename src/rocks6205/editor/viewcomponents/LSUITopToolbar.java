package rocks6205.editor.viewcomponents;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.actions.LSAbstractAction.NewDocumentAction;
import rocks6205.editor.actions.LSAbstractAction.OpenFileAction;
import rocks6205.editor.actions.LSAbstractAction.SaveFileAction;
import rocks6205.editor.actions.LSAbstractAction.ZoomInViewAction;
import rocks6205.editor.actions.LSAbstractAction.ZoomOutViewAction;
import rocks6205.editor.mvc.SVGEditorView;

import rocks6205.system.properties.LSSVGEditorGUITheme;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Color;
import java.awt.Dimension;

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
    private LSUIButton       newButton;
    private LSUIButton       openButton;
    private LSUIButton       saveButton;
    private LSUIButton       zoomInButton;
    private LSUIButton       zoomOutButton;
    private LSUIIconLabel    fillLabel;
    private LSUIIconLabel    strokeLabel;
    private JPanel           fillButton;
    private LSUIToggleButton strokeButton;

    /*
     * ACTION COMPONENTS
     */
    private NewDocumentAction newAct;
    private OpenFileAction    openAct;
    private SaveFileAction    saveAct;
    private ZoomInViewAction  zoomInAction;
    private ZoomOutViewAction zoomOutAction;

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
        fillButton    = new JPanel();
        strokeButton  = LSUIToggleButton.create();
        newAct        = new NewDocumentAction(parentView);
        openAct       = new OpenFileAction(parentView);
        saveAct       = new SaveFileAction(parentView);
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
        newButton.setAction(newAct);
        openButton.setAction(openAct);
        saveButton.setAction(saveAct);
        zoomInButton.setAction(zoomInAction);
        zoomOutButton.setAction(zoomOutAction);
        zoomInAction.setZoomOutPartnerAction(zoomOutAction);
    }

    /**
     * Setup icons for button.
     */
    private void setIconsForButtons() {
        String newFileIconPath  = "newfile.png";
        String openFileIconPath = "openfolder.png";
        String saveFileIconPath = "save.png";
        String zoomInIconPath   = "zoomin.png";
        String zoomOutIconPath  = "zoomout.png";
        String fillIconPath     = "fillIcon.png";
        String strokeIconPath   = "strokeIcon.png";

        newButton.setIcon(newFileIconPath);
        openButton.setIcon(openFileIconPath);
        saveButton.setIcon(saveFileIconPath);
        zoomInButton.setIcon(zoomInIconPath);
        zoomOutButton.setIcon(zoomOutIconPath);
        fillLabel.setIcon(fillIconPath);
        strokeLabel.setIcon(strokeIconPath);
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