package rocks6205.system.parser;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.text.Element;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;

/**
 *
 * @author Cheow Yeong Chi
 */
class SVGViewFactory implements ViewFactory {
    public SVGViewFactory() {}

    @Override
    public View create(Element elem) {
        return new LSSVGPlainView(elem);
    }
}