package rocks6205.svg.engine.viewcomponents;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.svg.engine.SVGView;

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
public class SVGViewDeleteAccessoryPanel extends JPanel {
    private static final long serialVersionUID = 4013696005435780830L;

    /**
     * Parent component
     */
    SVGView parent;

    /*
     * GUI COMPONENTS
     */
    SVGViewButton deleteButton;

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
    public SVGViewDeleteAccessoryPanel(SVGView view) {
        super();
        parent = view;
        initialise();
        customise();
    }

    /**
     * Initialisation of GUI components.
     */
    private void initialise() {
        deleteButton = new SVGViewButton();
    }

    /**
     * Customisation of GUI components.
     */
    private void customise() {
        add(deleteButton);
        deleteButton.setEnabled(false);
        setIconsForButtons();
    }

    /**
     * Setup icons for buttons
     */
    private void setIconsForButtons() {
        String deleteIconPath = "imageicon/delete.png";

        deleteButton.setIcon(deleteIconPath);
    }
}