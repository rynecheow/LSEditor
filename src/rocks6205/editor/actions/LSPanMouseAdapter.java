package rocks6205.editor.actions;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JViewport;

/**
 *
 * Handles event while user try to drag on the canvas, which results in a
 * image panning effect (hand scrolling).
 * <p>
 * Event handling starts while user presses on the canvas.
 *
 * @author: Cheow Yeong Chi
 *
 * @since 1.4
 *
 */
public class LSPanMouseAdapter extends MouseAdapter {

    /**
     * Panning pivot point
     */
    private final Point panPoint = new Point();

    /**
     * {@inheritDoc}<p>
     * Set pan starting point to be the point where the mouse currently located, and change the
     * current cursor to <code>HAND_CURSOR</code>.
     */
    @Override
    public void mousePressed(MouseEvent event) {
        System.out.println(event.getSource().getClass().toString());
        JViewport  viewport  = (JViewport) event.getSource();
        JComponent component = (JComponent) viewport.getView();

        panPoint.setLocation(event.getPoint());
        component.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * {@inheritDoc}<p>
     * Reset cursor to <code>DEFAULT_CURSOR</code>.
     */
    @Override
    public void mouseReleased(MouseEvent event) {
        JViewport  viewport  = (JViewport) event.getSource();
        JComponent component = (JComponent) viewport.getView();

        component.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    /**
     * {@inheritDoc}<p>
     * Get the current viewport of the canvas and move the viewable area respective to the
     * event cursor location.
     */
    @Override
    public void mouseDragged(MouseEvent event) {
        JViewport  viewport     = (JViewport) event.getSource();
        JComponent component    = (JComponent) viewport.getView();
        Point      currentPoint = event.getPoint();
        Point      viewPoint    = viewport.getViewPosition();

        viewPoint.translate(panPoint.x - currentPoint.x, panPoint.y - currentPoint.y);
        component.scrollRectToVisible(new Rectangle(viewPoint, viewport.getSize()));
        panPoint.setLocation(currentPoint);
    }
}