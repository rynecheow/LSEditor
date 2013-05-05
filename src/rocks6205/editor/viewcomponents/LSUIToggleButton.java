package rocks6205.editor.viewcomponents;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import rocks6205.system.properties.LSEditorGUIConstants;

/**
 * Custom <code>JToggleButton</code> with icons.
 *
 * @author Komalah Nair
 * @since 2.1
 *
 */
public class LSUIToggleButton extends JToggleButton {

    /*
     * CONSTRUCTORS
     */

    /**
     * Default constructor.<p>
     * Set border of button to <code>null</code>.
     */
    private LSUIToggleButton() {
        super();
    }

    /**
     * Factory method.
     * @return new instance of <code>LSUIToggleButton</code>
     */
    public static LSUIToggleButton create() {
        return new LSUIToggleButton();
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
        setBackground(null);
        setBorder(null);
        setBorderPainted(false);
    }
}