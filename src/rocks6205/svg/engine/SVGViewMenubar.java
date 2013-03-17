/**
 *
 * Class: SVGViewMenu
 * Description: Displays the menu bar for the GUI
 *
 * @author: Toh Huey Jing
 * @version: 1.0
 * @date: 17/03/2013
 *
 */

package rocks6205.svg.engine;

import javax.swing.*;

public class SVGViewMenubar extends JMenuBar {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * GUI COMPONENTS
	 */
	JMenu fileMenu, editMenu, insertMenu, helpMenu;

	JMenuItem newItem, openItem, saveItem, saveAsItem, docPropItem, exitItem;
	JMenuItem selAllItem, groupItem, ungroupItem, deleteItem;
	JMenuItem rectItem, circleItem, lineItem;
	JMenuItem faqItem, aboutItem;

	/*
	 * CONSTRUCTOR
	 */
	public SVGViewMenubar() {

		fileMenu = new JMenu("File");
		editMenu = new JMenu("Edit");
		insertMenu = new JMenu("Insert");
		helpMenu = new JMenu("Help");

		newItem = new JMenuItem("New");
		openItem = new JMenuItem("Open File...");
		saveItem = new JMenuItem("Save");
		saveAsItem = new JMenuItem("Save As...");
		docPropItem = new JMenuItem("Document Properties");
		exitItem = new JMenuItem("Exit");
		selAllItem = new JMenuItem("Select All");
		groupItem = new JMenuItem("Group");
		ungroupItem = new JMenuItem("Ungroup");
		deleteItem = new JMenuItem("Delete");
		rectItem = new JMenuItem("Rectangle");
		circleItem = new JMenuItem("Circle");
		lineItem = new JMenuItem("Line");
		faqItem = new JMenuItem("FAQ");
		aboutItem = new JMenuItem("About");

		fileMenu.add(newItem);
		fileMenu.add(openItem);
		fileMenu.addSeparator();
		fileMenu.add(saveItem);
		fileMenu.add(saveAsItem);
		fileMenu.addSeparator();
		fileMenu.add(docPropItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);

		editMenu.add(selAllItem);
		editMenu.add(groupItem);
		editMenu.add(ungroupItem);
		editMenu.add(deleteItem);

		insertMenu.add(rectItem);
		insertMenu.add(circleItem);
		insertMenu.add(lineItem);

		helpMenu.add(faqItem);
		helpMenu.add(aboutItem);
		
		this.add(fileMenu);
		this.add(editMenu);
		this.add(insertMenu);
		this.add(helpMenu);
	}

}