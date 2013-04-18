package rocks6205.editor.mvc;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.viewcomponents.LSUIEditingPanel;
import rocks6205.editor.viewcomponents.LSUIMenubar;
import rocks6205.editor.viewcomponents.LSUIMiscPanel;
import rocks6205.editor.viewcomponents.LSUINavigationPanel;
import rocks6205.editor.viewcomponents.LSUIProtocol;
import rocks6205.editor.viewcomponents.LSUISideToolbar;
import rocks6205.editor.viewcomponents.LSUIStatusPanel;
import rocks6205.editor.viewcomponents.LSUITopToolbar;
import rocks6205.editor.viewcomponents.LSUIWelcomeDialog;

import rocks6205.system.properties.LSSVGEditorGUITheme;
import rocks6205.system.properties.OSValidator;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
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
     * SIZE PROPERTIES
     */
    private int       width;
    private int       height;
    private Dimension screen;
    private int       x;
    private int       y;
    private int       margin;
    private Dimension frameDim;

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
    private JScrollPane         scrollPane;
    private LSUIEditingPanel    editPanel;
    private LSUIMenubar         menuBar;
    private LSUITopToolbar      topBar;
    private LSUISideToolbar     sideBar;
    private LSUIStatusPanel     statusPanel;
    private LSUINavigationPanel navPanel;
    private LSUIMiscPanel       miscPanel;

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
        setUpProperties();
        menuBar     = new LSUIMenubar(this);
        topBar      = new LSUITopToolbar("Editing Tools", this);
        sideBar     = new LSUISideToolbar("Selection Tools", this);
        statusPanel = new LSUIStatusPanel(this);
        navPanel    = new LSUINavigationPanel(this);
        miscPanel   = new LSUIMiscPanel(this);
        scrollPane  = new JScrollPane();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void customise() {
        layoutChildComponents();
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
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(frameDim);
        setMinimumSize(frameDim);
        setLayout(null);
        c.add(topBar);
        c.add(sideBar);
        c.add(statusPanel);
        c.add(navPanel);
        c.add(miscPanel);
        c.add(scrollPane);
        setJMenuBar(menuBar);
        pack();
    }

    public void layoutChildComponents() {
        scrollPane.setViewportView(editPanel);
        topBar.setBounds(0, 0, 1180, 35);
        sideBar.setBounds(0, 35, 35, 760);
        statusPanel.setBounds(35, 35, 920, 20);
        navPanel.setBounds(955, 35, 225, 752);
        miscPanel.setBounds(35, 555 + 81, 920, 151);
        scrollPane.setBounds(35, 55, 920, 582);
        topBar.setBackground(LSSVGEditorGUITheme.MASTER_DEFAULT_BACKGROUND_COLOR);
        sideBar.setBackground(LSSVGEditorGUITheme.MASTER_DEFAULT_BACKGROUND_COLOR);
        navPanel.setBackground(LSSVGEditorGUITheme.MASTER_DEFAULT_BACKGROUND_COLOR);
        miscPanel.setBackground(LSSVGEditorGUITheme.MASTER_DEFAULT_BACKGROUND_COLOR);
    }

    private void setUpProperties() {
        margin   = OSValidator.isMac()
                   ? -21
                   : 0;
        width    = 1180;
        height   = 830 + margin;
        frameDim = new Dimension(width, height);
        screen   = Toolkit.getDefaultToolkit().getScreenSize();
        x        = (screen.width - width) / 2;
        y        = (screen.height - height) / 2;
        this.setLocation(x, y);
    }
}