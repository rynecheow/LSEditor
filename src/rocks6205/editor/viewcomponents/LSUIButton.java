package rocks6205.editor.viewcomponents;

//~--- JDK imports ------------------------------------------------------------

import java.net.URL;

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