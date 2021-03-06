package rocks6205.editor.viewcomponents;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.system.properties.LSEditorGUIConstants;

import javax.swing.*;
import java.net.URL;

//~--- JDK imports ------------------------------------------------------------

/**
 * Custom JLabel with icon.
 *
 * @author Cheow Yeong Chi
 * @since 2.2
 */
public class LSUIIconLabel extends JLabel {
    private LSUIIconLabel() {
        super();
    }

    private LSUIIconLabel(String imagePath) {
        super(createIcon(imagePath));
    }

    /**
     * Factory method.
     * Constructs a <code>LSUIIconLabel</code> with path to icon.
     *
     * @param imagePath path to Icon
     * @return new instance of <code>LSUIIconLabel</code> with icon set.
     */
    public static LSUIIconLabel create(String imagePath) {
        return new LSUIIconLabel(imagePath);
    }

    /**
     * Factory method.
     * Constructs a <code>LSUIIconLabel</code>.
     *
     * @return new instance of <code>LSUIIconLabel</code> with icon unset.
     */
    public static LSUIIconLabel create() {
        return new LSUIIconLabel();
    }

    /*
     * METHODS
     */

    /**
     * Sets the button's default icon with name to icon image <code>iconName</code>.
     *
     * @param iconName Name to icon image
     */
    public void setIcon(String iconName) {
        ImageIcon icon = createIcon(iconName);

        super.setIcon(icon);
    }

    private static ImageIcon createIcon(String iconName) {
        String path = LSEditorGUIConstants.DEFAULT_PATH_TO_TOOLBAR_ICONS + iconName;
        URL imgURL = LSUIIconLabel.class.getResource(path);
        ImageIcon icon = new ImageIcon(imgURL);

        return icon;
    }
}