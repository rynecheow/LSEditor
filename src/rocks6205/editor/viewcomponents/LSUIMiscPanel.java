package rocks6205.editor.viewcomponents;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.mvc.SVGEditorView;

import rocks6205.system.properties.LSSVGEditorGUITheme;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.JPanel;

/**
 *
 * @author Cheow Yeong Chi
 */
public final class LSUIMiscPanel extends JPanel implements LSUIProtocol {

    /**
     * PARENT COMPONENT
     */
    private SVGEditorView parentView;

    /*
     * GUI COMPONENTS
     */
    private LSUIRGBColorChooserPanel colorChooserPanel;

    public LSUIMiscPanel(SVGEditorView parent) {
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

    private void layoutView() {
        setBorder(LSSVGEditorGUITheme.MASTER_DEFAULT_PANEL_BORDER);
        setLayout(null);
    }

    private void layoutComponents() {
        colorChooserPanel.setBounds(10, 10, 335, 114);
        enableColorChooserInView();
    }
}