package rocks6205.editor.viewcomponents;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.system.properties.LSEditorGUIConstants;

//~--- JDK imports ------------------------------------------------------------

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Custom JButtons with icons.
 *
 * @author Komalah Nair
 * @since 1.3
 *
 */
public class LSUIButton extends JButton {
    private LSUIButton() {
        super();
    }

    /**
     * Factory method.
     * @return new instance of <code>LSUIButton</code>
     */
    public static LSUIButton create() {
        return new LSUIButton();
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
        setOpaque(false);
        setContentAreaFilled(false);
        setBorder(null);
        setBorderPainted(false);
    }

    private ImageIcon createIcon(String iconName) {
        String    path   = LSEditorGUIConstants.DEFAULT_PATH_TO_TOOLBAR_ICONS + iconName;
        URL       imgURL = LSUIToggleButton.class.getResource(path);
        
        ImageIcon icon   = new ImageIcon(imgURL);

        return icon;
    }
}