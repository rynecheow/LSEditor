package rocks6205.editor.events;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.viewcomponents.LSUIEditingPanel;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

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
public class SVGEditorDrawMouseAdaptor extends MouseAdapter {

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
        if( editor.getEditMode().equals("drawRect") ) {
            editor.repaint();
        }else if( editor.getEditMode().equals("drawCircle") ) {
            editor.repaint();
        }else if( editor.getEditMode().equals("drawLine") ) {
            editor.repaint();
        }
        System.out.printf("Released at x:%d y:%d", event.getX(), event.getY());
    }

    /**
     * {@inheritDoc}<p>
     *
     */
    public void mouseDragged(MouseEvent event) {
        LSUIEditingPanel editor = (LSUIEditingPanel) event.getSource();
        int points = 0;
        Point pointsArray[] = new Point[ 10000 ];
        
        if ( points < pointsArray.length ) {
           pointsArray[ points ] = event.getPoint();
           points++;
           editor.repaint();
        }

        System.out.printf("Dragged at x:%d y:%d", event.getX(), event.getY());
    }
    
    /**
     * {@inheritDoc}<p>
     *
     */
    public void mouseEntered(MouseEvent event) {
        LSUIEditingPanel editor = (LSUIEditingPanel) event.getSource();
        editor.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        System.out.printf("Dragged at x:%d y:%d", event.getX(), event.getY());
    }
    
    /**
     * {@inheritDoc}<p>
     *
     */
    public void mouseExit(MouseEvent event) {
        LSUIEditingPanel editor = (LSUIEditingPanel) event.getSource();

        editor.setCursor(Cursor.getDefaultCursor());
    }
}
