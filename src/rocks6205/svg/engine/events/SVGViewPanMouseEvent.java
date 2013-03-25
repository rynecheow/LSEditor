package rocks6205.svg.engine.events;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.event.MouseInputAdapter;

public class SVGViewPanMouseEvent extends MouseInputAdapter {

	private Point panStartPoint;
	private Point panEndPoint;

	/**
	 * {@inheritDoc}
	 */
	public void mouseClicked(MouseEvent event) {}

	/**
	 * {@inheritDoc}
	 */
	public void mousePressed(MouseEvent event) {
		setPanStartPoint(event.getPoint());
		setPanEndPoint(null);
	}

	/**
	 * {@inheritDoc}
	 */
	public void mouseReleased(MouseEvent event) {}

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
	public void mouseDragged(MouseEvent event){}

	/**
	 * {@inheritDoc}
	 * @since 1.6
	 */
	public void mouseMoved(MouseEvent e){}

	/*
	 * ACCESSORS
	 */
	public Point getPanStartPoint() {
		return panStartPoint;
	}

	public Point getPanEndPoint() {
		return panEndPoint;
	}
	
	/*
	 * MUTATORS
	 */
	public void setPanStartPoint(Point panStartPoint) {
		this.panStartPoint = panStartPoint;
	}

	public void setPanEndPoint(Point panEndPoint) {
		this.panEndPoint = panEndPoint;
	}
}
