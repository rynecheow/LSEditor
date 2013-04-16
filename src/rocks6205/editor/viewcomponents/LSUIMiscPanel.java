package rocks6205.editor.viewcomponents;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.mvc.SVGEditorView;

import rocks6205.system.properties.SVGEditorTheme;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.BorderFactory;
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

    public LSUIMiscPanel(SVGEditorView parent) {
        super();
        parentView = parent;
        initialise();
        customise();
    }

    @Override
    public void initialise() {}

    @Override
    public void customise() {
        setBorder(SVGEditorTheme.MASTER_DEFAULT_PANEL_BORDER);
    }
}