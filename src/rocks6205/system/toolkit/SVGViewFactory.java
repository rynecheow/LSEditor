package rocks6205.system.toolkit;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.text.Element;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;

/**
 * Factory class for <code>LSSVGPlainView</code>
 *
 * @author Cheow Yeong Chi
 * 
 * @since 2.5
 */
class SVGViewFactory implements ViewFactory {

    /*
     * CONSTRUCTOR
     */

    /**
     * Default constructor
     */
    private SVGViewFactory() {}

    /**
     * {@inheritDoc}
     */
    @Override
    public View create(Element elem) {
        return new LSSVGPlainView(elem);
    }

    public static SVGViewFactory buildFactory() {
        return new SVGViewFactory();
    }
}