package rocks6205.svg.engine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import rocks6205.svg.engine.events.SVGViewPanMouseAdaptor;
import rocks6205.svg.engine.viewcomponents.SVGViewBottomToolbar;
import rocks6205.svg.engine.viewcomponents.SVGViewDeleteAccessoryPanel;
import rocks6205.svg.engine.viewcomponents.SVGViewMenubar;
import rocks6205.svg.engine.viewcomponents.SVGViewTopToolbar;
import rocks6205.svg.engine.viewcomponents.SVGViewport;
import rocks6205.svgFamily.SVGImageCanvas;

/**
 * A class defining how the main user interface should look like.
 * <p>
 *
 * @author Toh Huey Jing
 * @author Cheow Yeong Chi
 * 
 * @date 17/03/2013
 *
 *
 * @since 1.4
 */
public class SVGView extends JFrame implements Observer {

    private static final long serialVersionUID = 6764861773639452353L;

    /*
     * PROPERTIES
     */
    private SVGModel model;
    private SVGViewController controller;

    /*
     * GUI COMPONENTS
     */
    SVGViewMenubar menuBar;
    SVGViewTopToolbar topTool;
    SVGViewBottomToolbar bottomTool;
    SVGViewDeleteAccessoryPanel delete;

    JPanel panelTop, panelBottom;
    SVGViewport renderPanel;
    JViewport viewport;
    Container container = getContentPane();
    JScrollPane scrollPane;
    Dimension renderAreaSize;
    /*
     * ACTION COMPONENTS
     */
    private MouseAdapter panListener;
    /*
     * CONSTRUCTOR
     */
    public SVGView() {
	super();
	initialise();
	customise();
    }

    /**
     * Initialisation of GUI components
     */
    private void initialise() {
	renderAreaSize = new Dimension(0,0);
	menuBar = new SVGViewMenubar(this);
	topTool = new SVGViewTopToolbar(this);
	bottomTool = new SVGViewBottomToolbar(this);
	delete = new SVGViewDeleteAccessoryPanel(this);

	panListener = new SVGViewPanMouseAdaptor();

	renderPanel = new SVGViewport(this);
	panelTop = new JPanel();
	panelBottom = new JPanel();
	scrollPane = new JScrollPane(renderPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	viewport = new JViewport();
	viewport = scrollPane.getViewport();
	viewport.addMouseListener(panListener);
	viewport.addMouseMotionListener(panListener);
    }

    /**
     * Customisation of GUI components
     * 
     */
    private void customise() {
	setSize(1000,1000);
	setTitle("SVG Editor");
	scrollPane.setBounds(new Rectangle(renderAreaSize));
	renderPanel.setBackground(Color.WHITE);

	container.setLayout(new BorderLayout());
	panelTop.setLayout(new BorderLayout());
	panelBottom.setLayout(new BorderLayout());

	panelTop.add(topTool, BorderLayout.WEST);
	panelBottom.add(bottomTool, BorderLayout.WEST);
	panelBottom.add(delete, BorderLayout.EAST);	

	setVisible(true);
	setMinimumSize(new Dimension(700,700));
	setClosingEvent();
	setJMenuBar(menuBar);

	container.add(scrollPane, BorderLayout.CENTER);
	container.add(panelTop, BorderLayout.NORTH);
	container.add(panelBottom, BorderLayout.SOUTH);
    }

    /**
     * Prompt exit confirmation while user clicks on 'x' button on the window
     * 
     */
    private void setClosingEvent() {
	addWindowListener(new WindowAdapter() {
	    @Override
	    public void windowClosing(WindowEvent winEvt) {
		int closeCf = JOptionPane.showConfirmDialog(null, "Exit SVG Editor?", "Confirm exit", JOptionPane.WARNING_MESSAGE);
		if (closeCf == JOptionPane.YES_OPTION) {
		    System.exit(0);
		}
	    }
	});
    }
    /*
     * ACCESSORS
     */
    public SVGModel getModel() {
	return model;
    }

    public SVGViewController getController() {
	return controller;
    }

    /*
     * MUTATORS
     */
    public void setController(SVGViewController controller) {
	this.controller = controller;
    }

    public void setModel(SVGModel model) {
	this.model = model;
    }

    @Override
    public void update(Observable o, Object arg) {
	if (o instanceof SVGModel) {
	    float w = ((SVGModel) o).getSVGElement().getWidth().getValue();
	    float h = ((SVGModel) o).getSVGElement().getHeight().getValue();
	    renderAreaSize.height = (int) h;
	    renderAreaSize.width = (int) w;
	    scrollPane.setPreferredSize(renderAreaSize);
	    renderPanel.setPreferredSize(renderAreaSize);

	    renderPanel.setCanvas((SVGImageCanvas) arg);
	    renderPanel.repaint();
	    scrollPane.setViewportView(renderPanel);
	    scrollPane.repaint();
	}


    }
}
