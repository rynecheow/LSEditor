package rocks6205.editor.viewcomponents;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.mvc.SVGEditorView;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Cheow Yeong Chi
 */
public final class LSUIStatusPanel extends JPanel implements LSUIProtocol {

    /**
     * PARENT COMPONENT
     */
    private SVGEditorView parentView;

    /*
     * GUI COMPONENTS
     */
    private JLabel statusLabel;

    public LSUIStatusPanel(SVGEditorView parent) {
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