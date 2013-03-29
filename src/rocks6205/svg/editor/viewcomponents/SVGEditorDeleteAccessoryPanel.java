package rocks6205.svg.editor.viewcomponents;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.svg.mvc.SVGEditorView;
import rocks6205.svg.properties.SVGEditorTheme;


//~--- JDK imports ------------------------------------------------------------

import javax.swing.JPanel;

/**
 *
 * An accessory panel containing the Delete button.
 *
 * @author Toh Huey Jing
 *
 * @since 1.2
 *
 */
public class SVGEditorDeleteAccessoryPanel extends JPanel {
    private static final long serialVersionUID = 4013696005435780830L;

    /**
     * Parent component
     */
    SVGEditorView parent;

    /*
     * GUI COMPONENTS
     */
    SVGEditorButton deleteButton;

    /*
     * CONSRTUCTOR
     */

    /**
     * Constructs an <code>SVGViewDeleteAccessoryPanel</code> instance with
     * parent component <code>view</code> and with components initialised
     * and properly customised.
     *
     * @param view Parent component
     */
    public SVGEditorDeleteAccessoryPanel(SVGEditorView view) {
        super();
        parent = view;
        initialise();
        customise();
    }

    /**
     * Initialisation of GUI components.
     */
    private void initialise() {
        deleteButton = new SVGEditorButton();
    }

    /**
     * Customisation of GUI components.
     */
    private void customise() {
        add(deleteButton);
        setIconsForButtons();
        deleteButton.setEnabled(true);
        setBackground(SVGEditorTheme.MASTER_DEFAULT_COLOR);
        this.setSize(this.getSize().width, deleteButton.getPreferredSize().height * 2);
    }

    /**
     * Setup icons for buttons
     */
    private void setIconsForButtons() {
        String deleteIconPath = "imageicon/delete.png";

        deleteButton.setIcon(deleteIconPath);
    }
}