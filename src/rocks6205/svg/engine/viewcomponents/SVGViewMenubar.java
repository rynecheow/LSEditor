/**
 *
 * Class: SVGViewMenubar
 * Description: Displays the menu bar for the GUI
 *
 * @author: Toh Huey Jing
 * @date: 17/03/2013
 *
 */

package rocks6205.svg.engine.viewcomponents;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import rocks6205.svg.engine.SVGView;
import rocks6205.svg.engine.events.SVGViewMenuAction;
import rocks6205.svg.engine.events.SVGViewMenuAction.ExitAction;
import rocks6205.svg.engine.events.SVGViewMenuAction.OpenFileAction;
import rocks6205.svg.engine.events.SVGViewMenuAction.ZoomInViewAction;
import rocks6205.svg.engine.events.SVGViewMenuAction.ZoomOutViewAction;

public class SVGViewMenubar extends JMenuBar {

	private static final long serialVersionUID = 57709812552137078L;

	/*
	 * PARENT COMPONENT
	 */
	private SVGView parent;

	/*
	 * GUI COMPONENTS
	 */
	JMenu fileMenu, editMenu, insertMenu, windowMenu, helpMenu;
	JMenuItem newMenuItem, openMenuItem, saveMenuItem, saveAsMenuItem, docPropMenuItem, exitMenuItem;
	JMenuItem selectAllMenuItem, groupMenuItem, ungroupMenuItem, deleteMenuItem;
	JMenuItem insertRectMenuItem, insertCircleMenuItem, insertLineMenuItem;
	JMenuItem zoomInMenuItem, zoomOutMenuItem;
	JMenuItem faqMenuItem, aboutMenuItem;

	/*
	 * ACTION COMPONENTS
	 */
	private OpenFileAction openAct;
	private ExitAction exitAct;
	private ZoomInViewAction zoomInAction;
	private ZoomOutViewAction zoomOutAction;

	/*
	 * CONSTRUCTOR
	 */
	public SVGViewMenubar(SVGView view) {
		super();
		parent = view;
		initialise();
		customise();
	}

	/**
	 * Initialisation of GUI components
	 */
	private void initialise() {
		fileMenu = new JMenu("File");
		editMenu = new JMenu("Edit");
		insertMenu = new JMenu("Insert");
		windowMenu = new JMenu("Window");
		helpMenu = new JMenu("Help");

		newMenuItem = new JMenuItem("New");
		openMenuItem = new JMenuItem("Open File...");
		saveMenuItem = new JMenuItem("Save");
		saveAsMenuItem = new JMenuItem("Save As...");
		docPropMenuItem = new JMenuItem("Document Properties");
		exitMenuItem = new JMenuItem("Exit");
		selectAllMenuItem = new JMenuItem("Select All");
		groupMenuItem = new JMenuItem("Group");
		ungroupMenuItem = new JMenuItem("Ungroup");
		deleteMenuItem = new JMenuItem("Delete");
		insertRectMenuItem = new JMenuItem("Rectangle");
		insertCircleMenuItem = new JMenuItem("Circle");
		insertLineMenuItem = new JMenuItem("Line");
		zoomInMenuItem = new JMenuItem("Zoom In");
		zoomOutMenuItem = new JMenuItem("Zoom Out");
		faqMenuItem = new JMenuItem("FAQ");
		aboutMenuItem = new JMenuItem("About");

		openAct = new OpenFileAction(parent);
		zoomOutAction = new ZoomOutViewAction(parent);
		zoomInAction = new ZoomInViewAction(parent);
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
		setActionForMenuItem();
		disableUnused();
		add(fileMenu);
		add(editMenu);
		add(insertMenu);
		add(windowMenu);
		add(helpMenu);
		zoomInAction.setZoomOutPartnerAction(zoomOutAction);
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

	/*
	 * MUTATORS
	 */
	public SVGViewMenuAction.OpenFileAction getOpenAction() {
		if(openAct == null) openAct = new SVGViewMenuAction.OpenFileAction(parent);
		return openAct;
	}

	public ExitAction getExitAct() {
		if(exitAct == null) exitAct = new SVGViewMenuAction.ExitAction(parent);
		return exitAct;
	}

	public ZoomInViewAction getZoomInAction() {
		if(zoomInAction == null) zoomInAction = new ZoomInViewAction(parent);
		return zoomInAction;
	}

	public ZoomOutViewAction getZoomOutAction() {
		if(zoomOutAction == null) zoomOutAction = new ZoomOutViewAction(parent);
		return zoomOutAction;
	}
}
