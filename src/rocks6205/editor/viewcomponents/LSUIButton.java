package rocks6205.editor.viewcomponents;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 *
 * @author Komalah Nair
 * @since 1.3
 *
 */
public class LSUIButton extends JButton {
    private static final long serialVersionUID = -7348665839241378305L;

    /*
     * CONSTRUCTORS
     */

    /**
     * Default constructor.<p>
     * Set border of button to <code>null</code>.
     */
    private LSUIButton() {
        super();
    }

    public static LSUIButton create() {
        return new LSUIButton();
    }

    /*
     * METHODS
     */


    /**
     * Creates an <code>ImageIcon</code> instance from a image path <code>path</code>
     * provided.
     *
     * @param iconName Name to icon image
     * @return <code>ImageIcon</code> object
     */
    protected static ImageIcon createIcon(String iconName) {
        String string = "resources/toolbar-logo/" + iconName;
        ImageIcon icon = new ImageIcon(string);
        return icon;
    }

    /**
     * Sets the button's default icon with name to icon image <code>iconName</code>.
     *
     * @param iconPath Name to icon image
     */
    public void setIcon(String iconName) {
        ImageIcon icon = createIcon(iconName);

        super.setIcon(icon);
        setBorder(null);
        setBorderPainted(false);
    }
}