package rocks6205.editor.viewcomponents;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

/**
 * Tabbed pane handles different documents on different tabs.
 *
 * @author Cheow Yeong Chi
 *
 * @since 2.1
 */
public class LSUITabbedPane extends JTabbedPane {

    /**
     * Default constructor.
     */
    public LSUITabbedPane() {
        this.setTabPlacement(SwingConstants.LEFT);
    }
}