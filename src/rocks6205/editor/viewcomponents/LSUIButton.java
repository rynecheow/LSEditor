package rocks6205.editor.viewcomponents;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.ImageIcon;
import javax.swing.JButton;
import rocks6205.system.properties.LSEditorGUIConstants;

/**
 * Custom JButtons with icons.
 *
 * @author Komalah Nair
 * @since 1.3
 *
 */
public class LSUIButton extends JButton {

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
     * Creates an <code>ImageIcon</code> instance from a image name <code>iconName</code>
     * provided.
     *
     * @param iconName Name to icon image
     * @return <code>ImageIcon</code> object
     */
    private ImageIcon createIcon(String iconName) {
        String    string = LSEditorGUIConstants.DEFAULT_PATH_TO_TOOLBAR_ICONS + iconName;
        ImageIcon icon   = new ImageIcon(string);

        return icon;
    }

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
}