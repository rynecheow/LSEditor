package rocks6205.editor.viewcomponents;

//~--- JDK imports ------------------------------------------------------------

import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

/**
 *
 *
 * @author Komalah Nair
 * @since 1.3
 *
 */
public class LSUIButton extends JToggleButton {
    private static final long serialVersionUID = -7348665839241378305L;

   public static LSUIButton create() {
      return new LSUIButton();
   }

    /*
     * CONSTRUCTORS
     */

    /**
     * Default constructor.<p>
     * Set border of button to <code>null</code>.
     */
    private LSUIButton() {
        super();
//        setBorder(null);
//        setBackground(null);
//        setBorderPainted(false);
    }

    /**
     * Constructs a <code>SVGViewButton</code> instance with defined Action <code>a</code>.
     *
     * @param a Action component
     */
    public LSUIButton(Action a) {
        super(a);
        setBorder(null);
        setBorderPainted(false);
    }

    /**
     * Constructs a <code>SVGViewButton</code> instance with defined <code>icon</code>.
     *
     * @param icon Button icon
     */
    public LSUIButton(Icon icon) {
        super(icon);
        setBorder(null);
        setBorderPainted(false);
    }

    /**
     * Constructs a <code>SVGViewButton</code> instance with defined title <code>text</code>.
     *
     * @param text Title text
     */
    public LSUIButton(String text) {
        super(text);
        setBorder(null);
        setBorderPainted(false);
    }

    /**
     * Constructs a <code>SVGViewButton</code> instance with defined title <code>text</code>
     * and <code>icon</code>.
     *
     * @param text Title text
     * @param icon Button icon
     */
    public LSUIButton(String text, Icon icon) {
        super(text, icon);
        setBorder(null);
        setBorderPainted(false);
    }

    /**
     * Constructs a <code>SVGViewButton</code> instance with defined title <code>text</code>
     * and path to icon image <code>iconPath</code>.
     * @param text Title text
     * @param iconPath Path to icon image
     */
    public LSUIButton(String text, String iconPath) {
        super(text, createIcon(iconPath));
        setBorder(null);
        setBorderPainted(false);
    }

    /**
     * Constructs a <code>SVGViewButton</code> instance with defined title <code>text</code>,
     * path to icon image <code>iconPath</code> and Action <code>action</code>.
     *
     * @param text Title text
     * @param iconPath Path to icon image
     * @param action Action component
     */
    public LSUIButton(String text, String iconPath, AbstractAction action) {
        super(text, createIcon(iconPath));
        setAction(action);
        setBorder(null);
        setBorderPainted(false);
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
        URL imgURL = LSUIButton.class.getResource(path);

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
        setBorderPainted(false);
    }
}