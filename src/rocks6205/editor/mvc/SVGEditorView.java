package rocks6205.editor.mvc;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.viewcomponents.LSUIProtocol;
import rocks6205.editor.viewcomponents.LSUIRGBColorChooserPanel;
import rocks6205.editor.viewcomponents.LSUIWelcomeDialog;

//~--- JDK imports ------------------------------------------------------------

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
    private LSUIRGBColorChooserPanel colorChooserPanel;

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
        showWelcomeScreen();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialise() {
        colorChooserPanel = new LSUIRGBColorChooserPanel(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void customise() {
        layoutFrame();
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
        throw new UnsupportedOperationException("Not supported yet.");    // To change body of generated methods, choose Tools | Templates.
    }

    public void layoutChildComponents() {
        throw new UnsupportedOperationException("Not supported yet.");    // To change body of generated methods, choose Tools | Templates.
    }
}