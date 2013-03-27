/**
 * 
 * Class: SVGViewTopToolbar
 * Description: The toolbar containing the New, Open File and Save File icons at the top of the screen.
 * 
 * @author: Toh Huey Jing
 * @date: 17/03/2013
 * 
 */

package rocks6205.svg.engine.viewcomponents;

import javax.swing.JPanel;

import rocks6205.svg.engine.SVGView;
import rocks6205.svg.engine.events.SVGViewMenuAction.OpenFileAction;
import rocks6205.svg.engine.events.SVGViewMenuAction.ZoomInViewAction;
import rocks6205.svg.engine.events.SVGViewMenuAction.ZoomOutViewAction;



public class SVGViewTopToolbar extends JPanel {

    private static final long serialVersionUID = -295721542850966526L;

    /*
     * PARENT COMPONENT
     */
    SVGView parent;

    /*
     * GUI COMPONENTS
     */
    SVGViewButton newButton, openButton, saveButton, zoomInButton, zoomOutButton;

    /*
     * ACTION COMPONENTS
     */
    private ZoomInViewAction zoomInAction;
    private ZoomOutViewAction zoomOutAction;
    private OpenFileAction openAct;

    /*
     * CONSTRUCTOR
     */
    public SVGViewTopToolbar(SVGView view) {
	super();
	parent = view;

	initialise();
	customise();
    }



    /**
     * Initialisation of GUI Components
     */
    private void initialise() {
	newButton = new SVGViewButton();
	openButton = new SVGViewButton();
	saveButton = new SVGViewButton();
	zoomInButton = new SVGViewButton();
	zoomOutButton = new SVGViewButton();

	openAct = new OpenFileAction(parent);
	zoomInAction = new ZoomInViewAction(parent);
	zoomOutAction = new ZoomOutViewAction(parent);
    }
    /**
     * Customisation of GUI components
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
    }

    /**
     * Disable temporarily unused buttons
     */
    private void disableUnused() {
	newButton.setEnabled(false);
	saveButton.setEnabled(false);
    }

    /**
     * Setup actions for button
     */
    private void setActionForButtons() {
	openButton.setAction(getOpenAction());
	zoomInButton.setAction(getZoomInAction());
	zoomOutButton.setAction(getZoomOutAction());
    }

    /**
     * Setup icons for button
     */
    private void setIconsForButtons() {
	String newFileIconPath = "imageicon/newfile.png";
	String openFileIconPath = "imageicon/openfolder.png";
	String saveFileIconPath = "imageicon/save.png";
	String zoomInIconPath = "imageicon/zoomin.png";
	String zoomOutIconPath = "imageicon/zoomout.png";

	newButton.setIcon(newFileIconPath);
	openButton.setIcon(openFileIconPath);
	saveButton.setIcon(saveFileIconPath);
	zoomInButton.setIcon(zoomInIconPath);
	zoomOutButton.setIcon(zoomOutIconPath);
    }	

    /*
     * ACCESSORS
     */
    private OpenFileAction getOpenAction() {
	return openAct;
    }

    private ZoomInViewAction getZoomInAction() {
	return zoomInAction;
    }

    private ZoomOutViewAction getZoomOutAction() {
	return zoomOutAction;
    }

}
