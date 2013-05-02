package rocks6205.editor.viewcomponents;

//~--- JDK imports ------------------------------------------------------------

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LSUIIconLabel extends JLabel {
    public LSUIIconLabel() {}

    public LSUIIconLabel(String imagePath) {
        super(createIcon(imagePath));
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
    }
}