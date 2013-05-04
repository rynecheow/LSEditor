package rocks6205.system.parser;

import javax.swing.text.StyledEditorKit;
import javax.swing.text.ViewFactory;

/**
 * 
 * @author Cheow Yeong Chi
 */
public class LSDOMViewerToolkit  extends StyledEditorKit {
    private ViewFactory svgViewFactory;
    
    public LSDOMViewerToolkit() {
        svgViewFactory = new SVGViewFactory();
    }
    
    @Override
    public ViewFactory getViewFactory() {
        return svgViewFactory;
    }
 
    @Override
    public String getContentType() {
        return "text/xml";
    }
}
