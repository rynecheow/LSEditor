package rocks6205.svg.editor.events;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.svg.editor.viewcomponents.SVGEditorCanvasViewport;
import rocks6205.svg.properties.OSValidator;


//~--- JDK imports ------------------------------------------------------------

import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;

/**
 * Handles event while user try to zoom image by command-key on Mac OS or
 * CTRL-key paired together with scrolling on other operating systems.
 *
 * @author Cheow Yeong Chi
 *
 * @since 1.6
 */
public class SVGEditorMouseWheelZoomAdaptor extends MouseAdapter {

    /**
     * {@inheritDoc}<p>
     *
     */
    public void mouseWheelMoved(MouseWheelEvent event) {
        SVGEditorCanvasViewport viewport         = (SVGEditorCanvasViewport) event.getSource();
        boolean                isMacAndMetaDown = OSValidator.isMac() && event.isMetaDown();
        boolean                isKeyPressed     = ((isMacAndMetaDown || event.isControlDown())
                                                   &&!(isMacAndMetaDown && event.isControlDown()));    // XOR
        boolean isScrolledUp = event.getWheelRotation() < 0;

        if (isKeyPressed) {
            if (isScrolledUp) {
//                SVGImageCanvas.setZoomScale(SVGImageCanvas.getZoomScale() + 1);
//                viewport.getParentView().getModel().render();
            } else {
//                SVGImageCanvas.setZoomScale(SVGImageCanvas.getZoomScale() - 1);
//                viewport.getParentView().getModel().render();
            }
        }
    }
}