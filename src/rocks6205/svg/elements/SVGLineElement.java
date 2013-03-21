/**
 * 
 * Class: SVGLineElement
 * Description: 
 * 
 * @author: Sugar CheeSheen Chan
 * @date: 14/03/2013
 * 
 */

package rocks6205.svg.elements;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import org.w3c.dom.Element;

import rocks6205.svg.adt.SVGLengthUnit;
import rocks6205.svg.adt.SVGPainting;
import rocks6205.svg.adt.SVGPaintingType;
import rocks6205.svgFamily.SVGImageCanvas;

public class SVGLineElement extends SVGGenericElement {

	/*
	 * PROPERTIES
	 */
	private SVGLengthUnit x1 = new SVGLengthUnit( 0 );
	private SVGLengthUnit y1 = new SVGLengthUnit( 0 );
	private SVGLengthUnit x2 = new SVGLengthUnit( 0 );
	private SVGLengthUnit y2 = new SVGLengthUnit( 0 );

	/*
	 * CONSTRUCTORS
	 */
	public SVGLineElement( SVGLengthUnit x1 , SVGLengthUnit y1 , SVGLengthUnit x2 , SVGLengthUnit y2 , 
			SVGPainting stroke , SVGLengthUnit strokeWidth ) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public SVGLineElement(){}
	/*
	 * ACCESSORS
	 */
	/**
	 * @return Value of x1
	 */
	public SVGLengthUnit getX1() {
		return this.x1;
	}

	/**
	 * @return Value of y1
	 */
	public SVGLengthUnit getY1() {
		return this.y1;
	}

	/**
	 * @return Value of x2
	 */
	public SVGLengthUnit getX2() {
		return this.x2;
	}

	/**
	 * @return Value of y2
	 */
	public SVGLengthUnit getY2() {
		return this.y2;
	}

	/*
	 * MUTATORS
	 */
	/**
	 * @param x1 contains value for field x1
	 */
	public void setX1( SVGLengthUnit x1 ) {
		if(x1 != null)	this.x1 = x1;
	}

	/**
	 * @param y1 contains value for field y1
	 */
	public void setY1( SVGLengthUnit y1 ) {
		if(y1 != null)	this.y1 = y1;
	}

	/**
	 * @param x2 contains value for field x2
	 */
	public void setX2( SVGLengthUnit x2 ) {
		if(x2 != null)	this.x2 = x2;
	}

	/**
	 * @param y2 contains value for field y2
	 */
	public void setY2( SVGLengthUnit y2 ) {
		if(y2!= null)	this.y2 = y2;
	}

	@Override
	public Rectangle2D.Float getBounds() {
		float padding = 0;
		if (getStroke().getPaintType() != SVGPaintingType.NONE) {
			padding = getStrokeWidth().getValue() / 2;
		}
		float computedX = Math.min(x1.getValue(), x2.getValue()) - padding;
		float computedY = Math.min(y1.getValue(), y2.getValue()) - padding;
		float computedWidth = Math.abs(x2.getValue() - x1.getValue()) + 2
				* padding;
		float computedHeight = Math.abs(y2.getValue() - y1.getValue()) + 2
				* padding;

		return new Rectangle2D.Float(computedX, computedY, computedWidth,
				computedHeight);
	}

	@Override
	public SVGImageCanvas draw() {
		Rectangle2D.Float bounds = getBounds();
		SVGImageCanvas canvas = SVGImageCanvas.getBlankCanvas(bounds);

		if(canvas!= null){
			Graphics2D graphics = canvas.createGraphics();
			Line2D.Double line = new Line2D.Double(x1.getValue() - bounds.x,
					y1.getValue() - bounds.y, x2.getValue() - bounds.x,
					y2.getValue() - bounds.y);

			graphics.scale(SVGImageCanvas.getZoomScale(), SVGImageCanvas.getZoomScale());
			graphics.setPaint(getStroke().getPaintColor());
			graphics.setStroke(new BasicStroke((float) getStrokeWidth().getValue(),
					getStrokeLineCap(), getStrokeLineJoin()));
			graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			graphics.draw(line);
		}
		return canvas;
	}

	/*
	 * METHOD
	 */
	/**
	 * Parse line element from DOM level to object interpretable as defined
	 * 
	 * @param e		Element get from the XML Parser
	 * @return		Line element parsed from Element to be drawn
	 */
	public static SVGLineElement parseElement(Element e) {
		SVGLineElement line = new SVGLineElement();

		line.setX1(SVGLengthUnit.parse(e.getAttributeNS(null, "x1")));
		line.setY1(SVGLengthUnit.parse(e.getAttributeNS(null, "y1")));
		line.setX2(SVGLengthUnit.parse(e.getAttributeNS(null, "x2")));
		line.setY2(SVGLengthUnit.parse(e.getAttributeNS(null, "y2")));

		line.parseAttributes(e);

		return line;
	}
}
