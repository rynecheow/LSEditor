package rocks6205.svg.editor.viewcomponents;

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
public class LSTabbedPane extends JTabbedPane {

    /**
     * Default constructor.
     */
    public LSTabbedPane() {
        this.setTabPlacement(SwingConstants.LEFT);
    }
}