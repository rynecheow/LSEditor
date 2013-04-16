package rocks6205.editor.mvc;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.viewcomponents.LSUIEditingPanel;
import rocks6205.editor.viewcomponents.LSUIMenubar;
import rocks6205.editor.viewcomponents.LSUIMiscPanel;
import rocks6205.editor.viewcomponents.LSUINavigationPanel;
import rocks6205.editor.viewcomponents.LSUIProtocol;
import rocks6205.editor.viewcomponents.LSUIRGBColorChooserPanel;
import rocks6205.editor.viewcomponents.LSUISideToolbar;
import rocks6205.editor.viewcomponents.LSUIStatusPanel;
import rocks6205.editor.viewcomponents.LSUITopToolbar;
import rocks6205.editor.viewcomponents.LSUIWelcomeDialog;

import rocks6205.system.properties.OSValidator;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

/**
 * A class defining how the main user interface should look like.
 * <p>
 *
 * @author Toh Huey Jing
 * @author Cheow Yeong Chi
 *
 * @since 1.2
 */
public final class SVGEditorView extends JFrame implements LSUIProtocol {
    private static final long serialVersionUID = 6764861773639452353L;

    /*
     * GUI COMPONENTS
     */
    private Container c = getContentPane();

    /*
     * PROPERTIES
     */
    private float zoomScale;

    /**
     * Model object
     */
    private SVGEditorModel model;

    /**
     * Controller object
     */
    private SVGEditorViewController controller;

    /*
     * GUI COMPONENTS
     */
    private JScrollPane              scrollPane;
    private LSUIEditingPanel         editPanel;
    private LSUIMenubar              menuBar;
    private LSUIRGBColorChooserPanel colorChooserPanel;
    private LSUITopToolbar           topBar;
    private LSUISideToolbar          sideBar;
    private LSUIStatusPanel          statusPanel;
    private LSUINavigationPanel      navPanel;
    private LSUIMiscPanel            miscPanel;

    /*
     * ACTION COMPONENTS
     */

    /**
     * Handle pan events.
     */

    /*
     * CONSTRUCTOR
     */

    /**
     * Construct an <code>SVGView</code> instance with components initialised
     * and properly customised.
     */
    public SVGEditorView() {
        super();
        initialise();
        customise();

//      showWelcomeScreen();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialise() {
        menuBar           = new LSUIMenubar(this);
        colorChooserPanel = new LSUIRGBColorChooserPanel(this);
        topBar            = new LSUITopToolbar("Editing Tools", this);
        sideBar           = new LSUISideToolbar("Selection Tools", this);
        statusPanel       = new LSUIStatusPanel(this);
        navPanel          = new LSUINavigationPanel(this);
        miscPanel         = new LSUIMiscPanel(this);
        scrollPane        = new JScrollPane();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void customise() {
        layoutFrame();
        setClosingEvent();
    }

    /**
     * Prompt exit confirmation while user clicks on 'x' button on the window
     */
    private void setClosingEvent() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent winEvt) {
                int closeCf = JOptionPane.showConfirmDialog(null, "Exit SVG Editor?", "Confirm exit",
                                  JOptionPane.WARNING_MESSAGE);

                if (closeCf == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    /*
     * ACCESSORS
     */

    /**
     * @return Model object
     */
    public SVGEditorModel getModel() {
        return model;
    }

    /**
     * @return Controller object
     */
    public SVGEditorViewController getController() {
        return controller;
    }

    /*
     * MUTATORS
     */

    /**
     * @param controller Controller object
     */
    public void setController(SVGEditorViewController controller) {
        this.controller = controller;
    }

    /**
     * @param model Model object
     */
    public void setModel(SVGEditorModel model) {
        this.model = model;
    }

    public void update() {

        // TODO Auto-generated method stub
    }

    public float getZoomScale() {
        return zoomScale;
    }

    private void showWelcomeScreen() {
        new LSUIWelcomeDialog(this).display();
    }

    public void layoutFrame() {
        int margin = OSValidator.isMac() ? -21 : 0;

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new Dimension(1180, 830 + margin));
        setMinimumSize(new Dimension(1180, 830 + margin));
        topBar.setBackground(new Color(0, 51, 51));
        sideBar.setBackground(new Color(51, 51, 51));
        statusPanel.setBackground(new Color(0, 0, 51));
        navPanel.setBackground(new Color(0, 51, 0));
        miscPanel.setBackground(new Color(51, 0, 0));
        c.setLayout(null);
        c.add(topBar);
        topBar.setBounds(0, 0, 1180, 35);
        c.add(sideBar);
        sideBar.setBounds(0, 35, 35, 760);
        c.add(statusPanel);
        statusPanel.setBounds(35, 35, 920, 20);
        c.add(navPanel);
        navPanel.setBounds(955, 35, 225, 752);
        c.add(miscPanel);
        miscPanel.setBounds(35, 555 + 81, 920, 151);
        c.add(scrollPane);
        scrollPane.setBounds(35, 55, 920, 581);
        setJMenuBar(menuBar);
        scrollPane.setViewportView(editPanel);
        pack();
    }

    public void layoutChildComponents() {}
}