package rocks6205.svg.engine;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.svg.engine.events.SVGViewPanMouseAdaptor;
import rocks6205.svg.engine.viewcomponents.SVGViewBottomToolbar;
import rocks6205.svg.engine.viewcomponents.SVGViewDeleteAccessoryPanel;
import rocks6205.svg.engine.viewcomponents.SVGViewMenubar;
import rocks6205.svg.engine.viewcomponents.SVGViewTopToolbar;
import rocks6205.svg.engine.viewcomponents.SVGViewport;

import rocks6205.svgFamily.OSValidator;
import rocks6205.svgFamily.SVGEditorTheme;
import rocks6205.svgFamily.SVGImageCanvas;

//~--- JDK imports ------------------------------------------------------------

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * A class defining how the main user interface should look like.
 * <p>
 *
 * @author Toh Huey Jing
 * @author Cheow Yeong Chi
 *
 * @since 1.2
 */
public class SVGView extends JFrame implements Observer {
    private static final long serialVersionUID = 6764861773639452353L;

    /*
     * PROPERTIES
     */

    /**
     * Model object
     */
    private SVGModel model;

    /**
     * Controller object
     */
    private SVGViewController controller;

    /*
     * GUI COMPONENTS
     */
    SVGViewMenubar              menuBar;
    SVGViewTopToolbar           topTool;
    SVGViewBottomToolbar        bottomTool;
    SVGViewDeleteAccessoryPanel delete;
    JPanel                      panelTop, panelBottom;
    SVGViewport                 renderPanel;
    JViewport                   viewport;
    Container                   container;
    JScrollPane                 scrollPane;
    Dimension                   renderAreaSize;

    /*
     * ACTION COMPONENTS
     */

    /**
     * Handle pan events.
     */
    private SVGViewPanMouseAdaptor panListener;

    /*
     * CONSTRUCTOR
     */

    /**
     * Construct an <code>SVGView</code> instance with components initialised
     * and properly customised.
     */
    public SVGView() {
        super();
        initialise();
        customise();
    }

    /**
     * Initialisation of GUI components.
     */
    private void initialise() {
        menuBar        = new SVGViewMenubar(this);
        topTool        = new SVGViewTopToolbar(this);
        bottomTool     = new SVGViewBottomToolbar(this);
        delete         = new SVGViewDeleteAccessoryPanel(this);
        panelTop       = new JPanel();
        panelBottom    = new JPanel();
        renderPanel    = new SVGViewport(this);
        viewport       = new JViewport();
        container      = getContentPane();
        renderAreaSize = new Dimension(0, 0);
        scrollPane     = new JScrollPane(renderPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                                         JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panListener = new SVGViewPanMouseAdaptor();
    }

    /**
     * Customisation of GUI components.
     *
     */
    private void customise() {
        setUpLookAndFeel("Nimbus");
        setSize(1000, 1000);
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
        setMinimumSize(new Dimension(700, 700));
        setClosingEvent();
        setJMenuBar(menuBar);
        container.add(scrollPane, BorderLayout.CENTER);
        container.add(panelTop, BorderLayout.NORTH);
        container.add(panelBottom, BorderLayout.SOUTH);
        viewport = scrollPane.getViewport();
        viewport.addMouseListener(panListener);
        viewport.addMouseMotionListener(panListener);
        panelTop.setBackground(SVGEditorTheme.MASTER_DEFAULT_COLOR);
        panelBottom.setBackground(SVGEditorTheme.MASTER_DEFAULT_COLOR);
    }

    /**
     * Setup look and feel for GUI.
     * @param style <code>String</code> containing the name
     */
    private void setUpLookAndFeel(String style) {
        if (OSValidator.isWindows()) {
            System.out.println(UIManager.getLookAndFeel().toString());

            try {
                for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    System.out.println(info.getName());

                    if (style.equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());

                        break;
                    }
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                     | UnsupportedLookAndFeelException e) {}
        }
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
    public SVGModel getModel() {
        return model;
    }

    /**
     * @return Controller object
     */
    public SVGViewController getController() {
        return controller;
    }

    /*
     * MUTATORS
     */

    /**
     * @param controller Controller object
     */
    public void setController(SVGViewController controller) {
        this.controller = controller;
    }

    /**
     * @param model Model object
     */
    public void setModel(SVGModel model) {
        this.model = model;
    }

    /**
     * <p>Updates the image canvas whenever a change is made to the observed object
     * which is the <code>SVGModel.</code></p>
     * {@inheritDoc}
     */
    public void update(Observable o, Object arg) {
        if (o instanceof SVGModel) {
            float w = ((SVGModel) o).getSVGElement().getWidth().getValue();
            float h = ((SVGModel) o).getSVGElement().getHeight().getValue();

            renderAreaSize.height = (int) h;
            renderAreaSize.width  = (int) w;
            scrollPane.setPreferredSize(renderAreaSize);
            renderPanel.setPreferredSize(renderAreaSize);
            renderPanel.setCanvas((SVGImageCanvas) arg);
            renderPanel.revalidate();
            renderPanel.repaint();
            scrollPane.setViewportView(renderPanel);
            scrollPane.revalidate();
            scrollPane.repaint();
        }
    }
}