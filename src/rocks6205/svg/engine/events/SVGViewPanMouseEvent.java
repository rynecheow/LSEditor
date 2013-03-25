package rocks6205.svg.engine.events;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;

import javax.swing.JComponent;
import javax.swing.event.MouseInputAdapter;

public class SVGViewPanMouseEvent extends MouseInputAdapter {

	private Point panStartPoint;
	private Point panEndPoint;

	private AffineTransform coordinateTransform;

	private JComponent targetComponent;

	public SVGViewPanMouseEvent(JComponent targetComponent){
		setTargetComponent(targetComponent);
		setCoordinateTransform(new AffineTransform());
	}
	/**
	 * {@inheritDoc}
	 */
	public void mouseClicked(MouseEvent event) {}

	/**
	 * Set pan starting point to be the point where the mouse currently located
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
	public void mouseDragged(MouseEvent event){
		moveViewCamera(event);
	}

	/**
	 * 
	 */
	private void moveViewCamera(MouseEvent event) {
		try {
			setPanEndPoint(event.getPoint());
			Point2D.Float panStart = transformPoint(panStartPoint);
			Point2D.Float panEnd = transformPoint(panEndPoint);
			double dx = panEnd.getX() - panStart.getX();
			double dy = panEnd.getY() - panStart.getY();
			coordinateTransform.translate(dx, dy);
			panStartPoint = panEndPoint;
			panEndPoint = null;
			targetComponent.repaint();
		} catch (NoninvertibleTransformException ex) {
			ex.printStackTrace();
		}
	}

	private Point2D.Float transformPoint(Point point) throws NoninvertibleTransformException{
		AffineTransform inverseTransform = coordinateTransform.createInverse();
		Point2D.Float newPoint = new Point2D.Float();
		inverseTransform.transform(point, newPoint);
		return newPoint;
	}
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

	public AffineTransform getCoordinateTransform() {
		return coordinateTransform;
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

	public void setTargetComponent(JComponent c){
		targetComponent  = c;
	}

	public void setCoordinateTransform(AffineTransform coordinateTransform) {
		this.coordinateTransform = coordinateTransform;
	}
}
