/**
 *
 * Class: SVGViewPanMouseAdaptor
 * Description: General layout of the GUI
 *
 * @author: Toh Huey Jing
 * @date: 17/03/2013
 *
 */
package rocks6205.svg.engine.events;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.JComponent;
import javax.swing.JViewport;

public class SVGViewPanMouseAdaptor extends MouseAdapter {

    private final Point panPoint = new Point();

    /**
     * {@inheritDoc}
     */
    public void mouseClicked(MouseEvent event) {}

    /**
     * {@inheritDoc}
     * Set pan starting point to be the point where the mouse currently located
     */
    public void mousePressed(MouseEvent event) {
	JViewport viewport = (JViewport)event.getSource();
	JComponent component = (JComponent)viewport.getView();
	panPoint.setLocation(event.getPoint());
	component.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * {@inheritDoc}
     * Reset cursor
     */
    public void mouseReleased(MouseEvent event) {
	JViewport viewport = (JViewport)event.getSource();
	JComponent component = (JComponent)viewport.getView();
	component.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    /**
     * {@inheritDoc}
     */
    public void mouseEntered(MouseEvent event) {}

    /**
     * {@inheritDoc}
     */
    public void mouseExited(MouseEvent event) {}

    /**
     * {@inheritDoc}
     * @since 1.6
     */
    public void mouseWheelMoved(MouseWheelEvent event){}

    /**
     * {@inheritDoc}
     * @since 1.6
     */
    public void mouseDragged(MouseEvent event){
	JViewport viewport = (JViewport)event.getSource();
	JComponent component = (JComponent)viewport.getView();
	Point currentPoint = event.getPoint();
	Point viewPoint = viewport.getViewPosition();
	viewPoint.translate(panPoint.x-currentPoint.x, panPoint.y-currentPoint.y);
	component.scrollRectToVisible(new Rectangle(viewPoint, viewport.getSize()));
	//vport.setViewPosition(vp);
	panPoint.setLocation(currentPoint);
    }


    /**
     * {@inheritDoc}
     * @since 1.6
     */
    public void mouseMoved(MouseEvent e){}

    public Point getPanPoint() {
	return panPoint;
    }

    /*
     * ACCESSORS
     */


    /*
     * MUTATORS
     */

}
