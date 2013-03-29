package rocks6205.svg.engine.viewcomponents;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.svg.engine.SVGEditorView;
import rocks6205.svg.engine.events.SVGEditorMenuAction;
import rocks6205.svg.engine.events.SVGEditorMenuAction.ExitAction;
import rocks6205.svg.engine.events.SVGEditorMenuAction.OpenFileAction;
import rocks6205.svg.engine.events.SVGEditorMenuAction.ZoomInViewAction;
import rocks6205.svg.engine.events.SVGEditorMenuAction.ZoomOutViewAction;

//~--- JDK imports ------------------------------------------------------------

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * The main menu bar for the GUI.
 *
 * @author Toh Huey Jing
 *
 * @since 1.2
 *
 */
public class SVGEditorMenubar extends JMenuBar {
    private static final long serialVersionUID = 57709812552137078L;

    /**
     * Parent component
     */
    private SVGEditorView parent;

    /*
     * GUI COMPONENTS
     */
    JMenu     fileMenu, editMenu, insertMenu, windowMenu, helpMenu;
    JMenuItem newMenuItem, openMenuItem, saveMenuItem, saveAsMenuItem, docPropMenuItem, exitMenuItem;
    JMenuItem selectAllMenuItem, groupMenuItem, ungroupMenuItem, deleteMenuItem;
    JMenuItem insertRectMenuItem, insertCircleMenuItem, insertLineMenuItem;
    JMenuItem zoomInMenuItem, zoomOutMenuItem;
    JMenuItem faqMenuItem, aboutMenuItem;

    /*
     * ACTION COMPONENTS
     */
    private OpenFileAction    openAct;
    private ExitAction        exitAct;
    private ZoomInViewAction  zoomInAction;
    private ZoomOutViewAction zoomOutAction;

    /*
     * CONSTRUCTOR
     */

    /**
     * Constructs a <code>SVGViewMenubar</code> with parent component
     * <code>view</code> and with components initialised and properly customised.
     *
     * @param view Parent component
     */
    public SVGEditorMenubar(SVGEditorView view) {
        super();
        parent = view;
        initialise();
        customise();
    }

    /**
     * Initialisation of GUI components.
     */
    private void initialise() {
        fileMenu             = new JMenu("File");
        editMenu             = new JMenu("Edit");
        insertMenu           = new JMenu("Insert");
        windowMenu           = new JMenu("Window");
        helpMenu             = new JMenu("Help");
        newMenuItem          = new JMenuItem("New");
        openMenuItem         = new JMenuItem("Open File...");
        saveMenuItem         = new JMenuItem("Save");
        saveAsMenuItem       = new JMenuItem("Save As...");
        docPropMenuItem      = new JMenuItem("Document Properties");
        exitMenuItem         = new JMenuItem("Exit");
        selectAllMenuItem    = new JMenuItem("Select All");
        groupMenuItem        = new JMenuItem("Group");
        ungroupMenuItem      = new JMenuItem("Ungroup");
        deleteMenuItem       = new JMenuItem("Delete");
        insertRectMenuItem   = new JMenuItem("Rectangle");
        insertCircleMenuItem = new JMenuItem("Circle");
        insertLineMenuItem   = new JMenuItem("Line");
        zoomInMenuItem       = new JMenuItem("Zoom In");
        zoomOutMenuItem      = new JMenuItem("Zoom Out");
        faqMenuItem          = new JMenuItem("FAQ");
        aboutMenuItem        = new JMenuItem("About");
        openAct              = new OpenFileAction(parent, "Open File...");
        exitAct              = new SVGEditorMenuAction.ExitAction(parent, "Exit");
        zoomOutAction        = new ZoomOutViewAction(parent, "Zoom Out");
        zoomInAction         = new ZoomInViewAction(parent, "Zoom In");
    }

    /**
     * Customisation of GUI Components
     */
    private void customise() {
        setMnemonicForMenus();
        layoutFileMenuItemList();
        layoutEditMenuItemList();
        layoutInsertMenuItemList();
        layoutWindowMenuItemList();
        layoutHelpMenuItemList();
        disableUnused();
        add(fileMenu);
        add(editMenu);
        add(insertMenu);
        add(windowMenu);
        add(helpMenu);
        zoomInAction.setZoomOutPartnerAction(zoomOutAction);
        setActionForMenuItem();
    }

    /**
     * Setting up mnemonic keys events for respective file menu
     */
    private void setMnemonicForMenus() {
        fileMenu.setMnemonic(KeyEvent.VK_F);
        editMenu.setMnemonic(KeyEvent.VK_E);
        insertMenu.setMnemonic(KeyEvent.VK_I);
        windowMenu.setMnemonic(KeyEvent.VK_W);
        helpMenu.setMnemonic(KeyEvent.VK_H);
    }

    /**
     * Layout file menu with menu items
     */
    private void layoutFileMenuItemList() {
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(saveMenuItem);
        fileMenu.add(saveAsMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(docPropMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
    }

    /**
     * Layout file menu with menu items
     */
    private void layoutEditMenuItemList() {
        editMenu.add(selectAllMenuItem);
        editMenu.addSeparator();
        editMenu.add(groupMenuItem);
        editMenu.add(ungroupMenuItem);
        editMenu.addSeparator();
        editMenu.add(deleteMenuItem);
    }

    /**
     * Layout file menu with menu items
     */
    private void layoutInsertMenuItemList() {
        insertMenu.add(insertRectMenuItem);
        insertMenu.add(insertCircleMenuItem);
        insertMenu.add(insertLineMenuItem);
    }

    /**
     * Layout file menu with menu items
     */
    private void layoutHelpMenuItemList() {
        helpMenu.add(faqMenuItem);
        helpMenu.add(aboutMenuItem);
    }

    /**
     * Layout file menu with menu items
     */
    private void layoutWindowMenuItemList() {
        windowMenu.add(zoomInMenuItem);
        windowMenu.add(zoomOutMenuItem);
    }

    /**
     * Configure action for buttons
     */
    private void setActionForMenuItem() {
        openMenuItem.setAction(getOpenAction());
        exitMenuItem.setAction(getExitAct());
        zoomInMenuItem.setAction(getZoomInAction());
        zoomOutMenuItem.setAction(getZoomOutAction());
    }

    /**
     * Disables unused buttons
     */
    private void disableUnused() {
        newMenuItem.setEnabled(false);
        saveMenuItem.setEnabled(false);
        saveAsMenuItem.setEnabled(false);
        docPropMenuItem.setEnabled(false);
        editMenu.setEnabled(false);
        insertMenu.setEnabled(false);
        helpMenu.setEnabled(false);
    }

    /**
     * Enable zoom in button after SVG file is opened sucessfully
     */
    public void enableZoomIn() {
        zoomInMenuItem.setEnabled(true);
    }

    /**
     * Disable zoom out button when it has resumed to its original size of image
     */
    public void setZoomOut(boolean flag) {
        zoomOutMenuItem.setEnabled(flag);
    }

    /*
     * MUTATORS
     */

    /**
     * @return Open file action
     */
    public SVGEditorMenuAction.OpenFileAction getOpenAction() {
        if (openAct == null) {
            openAct = new SVGEditorMenuAction.OpenFileAction(parent, "Open File...");
        }

        return openAct;
    }

    /**
     * @return Exit program action
     */
    public ExitAction getExitAct() {
        if (exitAct == null) {
            exitAct = new SVGEditorMenuAction.ExitAction(parent, "Exit");
        }

        return exitAct;
    }

    /**
     * @return Zoom in view action
     */
    public ZoomInViewAction getZoomInAction() {
        if (zoomInAction == null) {
            zoomInAction = new ZoomInViewAction(parent, "Zoom In");
        }

        return zoomInAction;
    }

    /**
     * @return Zoom out view action
     */
    public ZoomOutViewAction getZoomOutAction() {
        if (zoomOutAction == null) {
            zoomOutAction = new ZoomOutViewAction(parent, "Zoom Out");
        }

        return zoomOutAction;
    }
}