package rocks6205.editor.viewcomponents.panels;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.mvc.LSView;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import rocks6205.editor.viewcomponents.LSUIProtocol;

/**
 *
 * @author Cheow Yeong Chi
 */
public final class LSUIStatusPanel extends JPanel implements LSUIProtocol {

    /**
     * PARENT COMPONENT
     */
    private LSView parentView;

    /*
     * GUI COMPONENTS
     */
    private JLabel statusLabel;

    public LSUIStatusPanel(LSView parent) {
        super();
        parentView = parent;
        initialise();
        customise();
    }

    @Override
    public void initialise() {
        statusLabel = new JLabel("");
    }

    @Override
    public void customise() {
        setBackground(new Color(0x2e2c2c));
        statusLabel.setForeground(Color.LIGHT_GRAY);
        add(statusLabel);
    }

    public void updateStatus(String status) {
        statusLabel.setText(status);
    }
}