package rocks6205.editor.viewcomponents;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.bridge.actions.LSAction;
import rocks6205.editor.bridge.actions.LSAction.ExitAction;
import rocks6205.editor.bridge.actions.LSAction.OpenFileAction;
import rocks6205.editor.bridge.actions.LSAction.ZoomInViewAction;
import rocks6205.editor.bridge.actions.LSAction.ZoomOutViewAction;
import rocks6205.editor.mvc.SVGEditorView;

//~--- JDK imports ------------------------------------------------------------

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import rocks6205.editor.bridge.actions.LSAction.DeleteAction;
import rocks6205.editor.bridge.actions.LSAction.DeselectAllAction;
import rocks6205.editor.bridge.actions.LSAction.GroupAction;
import rocks6205.editor.bridge.actions.LSAction.NewDocumentAction;
import rocks6205.editor.bridge.actions.LSAction.SaveFileAction;
import rocks6205.editor.bridge.actions.LSAction.SaveFileAsAction;
import rocks6205.editor.bridge.actions.LSAction.SelectAllAction;
import rocks6205.editor.bridge.actions.LSAction.UngroupAction;

/**
 * The main menu bar for the GUI.
 *
 * @author Toh Huey Jing
 *
 * @since 1.2
 *
 */
public final class LSUIMenubar extends JMenuBar implements LSUIProtocol {
    private static final long serialVersionUID = 57709812552137078L;

    /**
     * Parent component
     */
    private SVGEditorView parent;

    /*
     * GUI COMPONENTS
     */
    private JMenu     fileMenu;
    private JMenu     editMenu;
    private JMenu     insertMenu;
    private JMenu     windowMenu;
    private JMenu     helpMenu;
    private JMenuItem newMenuItem;
    private JMenuItem openMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem saveAsMenuItem;
    private JMenuItem docPropMenuItem;
    private JMenuItem exitMenuItem;
    private JMenuItem selectAllMenuItem;
    private JMenuItem deselectAllMenuItem;
    private JMenuItem groupMenuItem;
    private JMenuItem ungroupMenuItem;
    private JMenuItem deleteMenuItem;
    private JMenuItem insertRectMenuItem;
    private JMenuItem insertCircleMenuItem;
    private JMenuItem insertLineMenuItem;
    private JMenuItem zoomInMenuItem;
    private JMenuItem zoomOutMenuItem;
    private JMenuItem faqMenuItem;
    private JMenuItem aboutMenuItem;

    /*
     * ACTION COMPONENTS
     */
    private NewDocumentAction newAct;
    private OpenFileAction    openAct;
    private SaveFileAction    saveAct;
    private SaveFileAsAction  saveAsAct;
    private ExitAction        exitAct;
    private SelectAllAction   selectAllAct;
    private DeselectAllAction deselectAllAct;
    private GroupAction       groupAction;
    private UngroupAction     ungroupAction;
    private DeleteAction      deleteAction;
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
    public LSUIMenubar(SVGEditorView view) {
        super();
        parent = view;
        initialise();
        customise();
    }

    /**
     * Initialisation of GUI components.
     */
    @Override
    public void initialise() {
        fileMenu             = new JMenu("File");
        editMenu             = new JMenu("Edit");
        insertMenu           = new JMenu("Insert");
        windowMenu           = new JMenu("Window");
        helpMenu             = new JMenu("Help");
        newMenuItem          = new JMenuItem();
        openMenuItem         = new JMenuItem();
        saveMenuItem         = new JMenuItem();
        saveAsMenuItem       = new JMenuItem();
        docPropMenuItem      = new JMenuItem("Document Properties");
        exitMenuItem         = new JMenuItem();
        selectAllMenuItem    = new JMenuItem();
        deselectAllMenuItem  = new JMenuItem();
        groupMenuItem        = new JMenuItem();
        ungroupMenuItem      = new JMenuItem();
        deleteMenuItem       = new JMenuItem();
        insertRectMenuItem   = new JMenuItem("Rectangle");
        insertCircleMenuItem = new JMenuItem("Circle");
        insertLineMenuItem   = new JMenuItem("Line");
        zoomInMenuItem       = new JMenuItem();
        zoomOutMenuItem      = new JMenuItem();
        faqMenuItem          = new JMenuItem("FAQ");
        aboutMenuItem        = new JMenuItem("About");
        
        newAct               = new NewDocumentAction(parent,"New");
        openAct              = new OpenFileAction(parent,"Open File...");
        saveAct              = new SaveFileAction(parent,"Save");
        saveAsAct            = new SaveFileAsAction(parent,"Save As...");
        exitAct              = new LSAction.ExitAction(parent,"Exit");
        selectAllAct         = new SelectAllAction(parent,"Select All");
        deselectAllAct       = new DeselectAllAction(parent,"Deselect All");
        groupAction          = new GroupAction(parent,"Group");
        ungroupAction        = new UngroupAction(parent,"Ungroup");
        deleteAction         = new DeleteAction(parent,"Delete");    
        zoomOutAction        = new ZoomOutViewAction(parent,"Zoom In");
        zoomInAction         = new ZoomInViewAction(parent,"Zoom Out");
    }

    /**
     * Customisation of GUI Components
     */
    @Override
    public void customise() {
        setMnemonicForMenus();
        layoutFileMenuItemList();
        layoutEditMenuItemList();
//        layoutInsertMenuItemList();
        layoutWindowMenuItemList();
//        layoutHelpMenuItemList();
        add(fileMenu);
        add(editMenu);
//        add(insertMenu);
        add(windowMenu);
//        add(helpMenu);
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
        editMenu.add(deselectAllMenuItem);
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
        newMenuItem.setAction(newAct);
        openMenuItem.setAction(openAct);
        saveMenuItem.setAction(saveAct);
        saveAsMenuItem.setAction(saveAsAct);
        exitMenuItem.setAction(exitAct);
        selectAllMenuItem.setAction(selectAllAct);
        deselectAllMenuItem.setAction(deselectAllAct);
        groupMenuItem.setAction(groupAction);
        ungroupMenuItem.setAction(ungroupAction);
        deleteMenuItem.setAction(deleteAction);
        zoomInMenuItem.setAction(zoomInAction);
        zoomOutMenuItem.setAction(zoomOutAction);
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

    public void updateActionStatusFromView(boolean status) {
        selectAllMenuItem.setEnabled(status);
        deselectAllMenuItem.setEnabled(status);
    }
}