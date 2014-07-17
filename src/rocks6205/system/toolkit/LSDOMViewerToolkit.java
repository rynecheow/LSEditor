package rocks6205.system.toolkit;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.text.StyledEditorKit;
import javax.swing.text.ViewFactory;

/**
 * Editor kit which builds the factory for <code>LSSVGPlainView</code>.
 *
 * @author Cheow Yeong Chi
 * @since 2.5
 */
public class LSDOMViewerToolkit extends StyledEditorKit {
    private ViewFactory svgViewFactory;

    /*
     * CONSTRUCTOR
     */

    /**
     * builds the factory for <code>LSSVGPlainView</code>
     */
    public LSDOMViewerToolkit() {
        svgViewFactory = SVGViewFactory.buildFactory();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewFactory getViewFactory() {
        return svgViewFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getContentType() {
        return "text/xml";
    }
}