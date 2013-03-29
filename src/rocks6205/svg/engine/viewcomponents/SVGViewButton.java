package rocks6205.svg.engine.viewcomponents;

//~--- JDK imports ------------------------------------------------------------

import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * Class: SVGViewButton
 * Description:
 *
 * @author Komalah Nair
 * @since 1.3
 *
 */
public class SVGViewButton extends JButton {
    private static final long serialVersionUID = -7348665839241378305L;

    /*
     * CONSTRUCTORS
     */

    /**
     * Default constructor.<p>
     * Set border of button to <code>null</code>.
     */
    public SVGViewButton() {
        setBorder(null);
        setBackground(null);
    }

    /**
     * Constructs a <code>SVGViewButton</code> instance with defined Action <code>a</code>.
     *
     * @param a Action component
     */
    public SVGViewButton(Action a) {
        super(a);
        setBorder(null);
    }

    /**
     * Constructs a <code>SVGViewButton</code> instance with defined <code>icon</code>.
     *
     * @param icon Button icon
     */
    public SVGViewButton(Icon icon) {
        super(icon);
        setBorder(null);
    }

    /**
     * Constructs a <code>SVGViewButton</code> instance with defined title <code>text</code>.
     *
     * @param text Title text
     */
    public SVGViewButton(String text) {
        super(text);
        setBorder(null);
    }

    /**
     * Constructs a <code>SVGViewButton</code> instance with defined title <code>text</code>
     * and <code>icon</code>.
     *
     * @param text Title text
     * @param icon Button icon
     */
    public SVGViewButton(String text, Icon icon) {
        super(text, icon);
        setBorder(null);
    }

    /**
     * Constructs a <code>SVGViewButton</code> instance with defined title <code>text</code>
     * and path to icon image <code>iconPath</code>.
     * @param text Title text
     * @param iconPath Path to icon image
     */
    public SVGViewButton(String text, String iconPath) {
        super(text, createIcon(iconPath));
        setBorder(null);
    }

    /**
     * Constructs a <code>SVGViewButton</code> instance with defined title <code>text</code>,
     * path to icon image <code>iconPath</code> and Action <code>action</code>.
     *
     * @param text Title text
     * @param iconPath Path to icon image
     * @param action Action component
     */
    public SVGViewButton(String text, String iconPath, AbstractAction action) {
        super(text, createIcon(iconPath));
        setAction(action);
        setBorder(null);
    }

    /*
     * METHODS
     */

    /**
     * Creates an <code>ImageIcon</code> instance from a image path <code>path</code>
     * provided.
     *
     * @param path Path to icon image
     * @return <code>ImageIcon</code> object
     */
    protected static ImageIcon createIcon(String path) {
        URL imgURL = SVGViewButton.class.getResource(path);

        if (imgURL != null) {
            return new ImageIcon(imgURL);
        }

        System.err.println("Could not find file: " + path);

        return null;
    }

    /**
     * Sets the button's default icon with path to icon image <code>iconPath</code>.
     *
     * @param iconPath Path to icon image
     */
    public void setIcon(String iconPath) {
        ImageIcon icon = createIcon(iconPath);

        super.setIcon(icon);
        setBorder(null);
    }
}