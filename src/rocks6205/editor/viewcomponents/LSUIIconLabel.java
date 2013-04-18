package rocks6205.editor.viewcomponents;

import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LSUIIconLabel extends JLabel {

    public LSUIIconLabel() {
    }

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
    }

}
