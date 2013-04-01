package rocks6205.svg.editor.events;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JViewport;

/**
 *
 * Handles event for drawing 3 different shapes on canvas
 * <p>
 *
 * @author: Suagr CheeSheen Chan
 *
 * @since 1.0
 *
 */
public class SVGEditorDrawMouseAdaptor {
    
    /**
     * {@inheritDoc}<p>
     * 
     */
    public void mousePressed(MouseEvent event) {
	System.out.printf("Pressed at x:%d y:%d" , 
		event.getX() , event.getY() );
    }

    /**
     * {@inheritDoc}<p>
     * 
     */
    public void mouseReleased(MouseEvent event) {
	System.out.printf("Released at x:%d y:%d" , 
		event.getX() , event.getY() );
    }

    /**
     * {@inheritDoc}<p>
     * 
     */
    public void mouseDragged(MouseEvent event) {
	System.out.printf("Dragged at x:%d y:%d" , 
		event.getX() , event.getY() );
    }

}
