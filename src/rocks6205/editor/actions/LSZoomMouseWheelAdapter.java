package rocks6205.editor.actions;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.viewcomponents.panels.LSCanvasViewport;
import rocks6205.system.properties.OSValidator;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;

//~--- JDK imports ------------------------------------------------------------

/**
 * Handles event while user try to zoom image by command-key on Mac OS or
 * CTRL-key paired together with scrolling on other operating systems.
 *
 * @author Cheow Yeong Chi
 * @since 1.6
 */
public class LSZoomMouseWheelAdapter extends MouseAdapter {

    /**
     * {@inheritDoc}<p>
     */
    public void mouseWheelMoved(MouseWheelEvent event) {
        LSCanvasViewport viewport = (LSCanvasViewport) event.getSource();
        boolean isMacAndMetaDown = OSValidator.isMac() && event.isMetaDown();
        boolean isKeyPressed = ((isMacAndMetaDown || event.isControlDown())
                && !(isMacAndMetaDown && event.isControlDown()));    // XOR
        boolean isScrolledUp = event.getWheelRotation() < 0;

        if (isKeyPressed) {
            if (isScrolledUp) {

//              SVGImageCanvas.setZoomScale(SVGImageCanvas.getZoomScale() + 1);
//              viewport.getParentView().getModel().render();
            } else {

//              SVGImageCanvas.setZoomScale(SVGImageCanvas.getZoomScale() - 1);
//              viewport.getParentView().getModel().render();
            }
        }
    }
}