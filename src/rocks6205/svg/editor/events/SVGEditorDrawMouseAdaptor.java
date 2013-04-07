package rocks6205.svg.editor.events;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.svg.editor.viewcomponents.SVGEditorEditingPanel;

//~--- JDK imports ------------------------------------------------------------

import java.awt.event.MouseEvent;

/**
 *
 * Handles event for drawing 3 different shapes on canvas
 * <p>
 *
 * @author: Sugar CheeSheen Chan
 *
 * @since 2.1
 *
 */
public class SVGEditorDrawMouseAdaptor {

    /**
     * {@inheritDoc}<p>
     *
     */
    public void mousePressed(MouseEvent event) {
        SVGEditorEditingPanel editor = (SVGEditorEditingPanel) event.getSource();

        System.out.printf("Pressed at x:%d y:%d", event.getX(), event.getY());
    }

    /**
     * {@inheritDoc}<p>
     *
     */
    public void mouseReleased(MouseEvent event) {
        SVGEditorEditingPanel editor = (SVGEditorEditingPanel) event.getSource();

        System.out.printf("Released at x:%d y:%d", event.getX(), event.getY());
    }

    /**
     * {@inheritDoc}<p>
     *
     */
    public void mouseDragged(MouseEvent event) {
        SVGEditorEditingPanel editor = (SVGEditorEditingPanel) event.getSource();

        System.out.printf("Dragged at x:%d y:%d", event.getX(), event.getY());
    }
}
