package rocks6205.editor.viewcomponents;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.mvc.SVGEditorView;

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

    public LSUIMiscPanel(SVGEditorView parent) {
        super();
        parentView = parent;
        initialise();
        customise();
    }

    @Override
    public void initialise() {}

    @Override
    public void customise() {}
}