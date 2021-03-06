package rocks6205.editor.viewcomponents.panels;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.core.LSView;
import rocks6205.editor.viewcomponents.LSUIProtocol;
import rocks6205.editor.viewcomponents.LSUISVGCodePane;
import rocks6205.system.properties.LSEditorGUIConstants;

import javax.swing.*;

//~--- JDK imports ------------------------------------------------------------

/**
 * Panel which contains the code view or other miscellaneous functioning tools.
 *
 * @author Cheow Yeong Chi
 * @since 2.0
 */
public final class LSUIMiscPanel extends JPanel implements LSUIProtocol {

    /**
     * PARENT COMPONENT
     */
    private LSView parentView;

    /*
     * GUI COMPONENTS
     */
    private LSUIRGBColorChooserPanel colorChooserPanel;
    private JScrollPane textScrollPane;
    private LSUISVGCodePane textArea;

    public LSUIMiscPanel(LSView parent) {
        super();
        parentView = parent;
        initialise();
        customise();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialise() {
        colorChooserPanel = new LSUIRGBColorChooserPanel(parentView);
        textScrollPane = new JScrollPane();
        textArea = new LSUISVGCodePane();
        textScrollPane.setViewportView(textArea);
    }

    @Override
    public void customise() {
        layoutView();
        layoutComponents();
    }

    public void disableColorChooserInView() {
        remove(colorChooserPanel);
        colorChooserPanel.setVisible(false);
    }

    public void enableColorChooserInView() {
        add(colorChooserPanel);
        colorChooserPanel.setVisible(true);
    }

    public void enableTextAreaInView() {
        add(textScrollPane);
        textScrollPane.setVisible(true);
    }

    public void disableTextAreaInView() {
        textScrollPane.setVisible(false);
        remove(textScrollPane);
    }

    private void layoutView() {
        setBorder(LSEditorGUIConstants.MASTER_DEFAULT_PANEL_BORDER);
        setLayout(null);
    }

    private void layoutComponents() {
        colorChooserPanel.setBounds(10, 10, 335, 114);
        textScrollPane.setBounds(6, 6, 906, 138);
        disableColorChooserInView();
        enableTextAreaInView();
    }

    public void updateCode(String d) {
        textArea.setText(d);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bindHandlers() {
    }
}