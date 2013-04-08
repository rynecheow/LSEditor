package rocks6205.editor.events;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.viewcomponents.LSUIEditingPanel;

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
        LSUIEditingPanel editor = (LSUIEditingPanel) event.getSource();

        System.out.printf("Pressed at x:%d y:%d", event.getX(), event.getY());
    }

    /**
     * {@inheritDoc}<p>
     *
     */
    public void mouseReleased(MouseEvent event) {
        LSUIEditingPanel editor = (LSUIEditingPanel) event.getSource();

        System.out.printf("Released at x:%d y:%d", event.getX(), event.getY());
    }

    /**
     * {@inheritDoc}<p>
     *
     */
    public void mouseDragged(MouseEvent event) {
        LSUIEditingPanel editor = (LSUIEditingPanel) event.getSource();

        System.out.printf("Dragged at x:%d y:%d", event.getX(), event.getY());
    }
}
