/**
 * 
 * Class: SVGCircleElement
 * Description: 
 * 
 * @author: Sugar CheeSheen Chan
 * @date: 15/03/2013
 * 
 */

package rocks6205.svg.elements;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import org.w3c.dom.Element;

import rocks6205.svg.adt.SVGLengthUnit;
import rocks6205.svg.adt.SVGLengthUnitType;
import rocks6205.svg.adt.SVGPaintingType;
import rocks6205.svgFamily.SVGImageCanvas;

public class SVGCircleElement extends SVGGenericElement {

	/*
	 * PROPERTIES
	 */
	private SVGLengthUnit cx = new SVGLengthUnit(0);
	private SVGLengthUnit cy = new SVGLengthUnit(0);
	private SVGLengthUnit radius = new SVGLengthUnit(0);

	/*
	 * CONSTRUCTORS
	 */
	public SVGCircleElement( SVGLengthUnit cx , SVGLengthUnit cy , SVGLengthUnit radius) {
		setCx(cx);
		setCy(cy);
		setRadius(radius);
	}

	public SVGCircleElement(){}
	/*
	 * ACCESSORS
	 */
	/**
	 * @return Value of cx
	 */
	public SVGLengthUnit getCx() {
		return this.cx;
	}

	/**
	 * @return Value of cy
	 */
	public SVGLengthUnit getCy() {
		return this.cy;
	}

	/**
	 * @return Value of radius
	 */
	public SVGLengthUnit getRadius() {
		return this.radius;
	}

	/*
	 * MUTATORS
	 */
	/**
	 * @param cx contains value for field 'cx'
	 */
	public final void setCx( SVGLengthUnit cx ) {
		if (cx != null)	this.cx = cx;
	}

	/**
	 * @param cy contains value for field 'cy'
	 */
	public final void setCy( SVGLengthUnit cy ) {
		if (cy != null)	this.cy = cy;
	}

	/**

	 * @param radius contains value for field radius
	 */
	public final void setRadius( SVGLengthUnit radius ) {
		if (radius != null && radius.getValue() >= 0)	this.radius = radius;
	}

	@Override
	public Rectangle2D.Float getBounds() {
		float r = radius.getValue(SVGLengthUnitType.PX);
		float xPos = cx.getValue(SVGLengthUnitType.PX);
		float yPos = cy.getValue(SVGLengthUnitType.PX);
		float padding = 0;

		Rectangle2D.Float bounds = new Rectangle2D.Float();
		if (r > 0) {
			if (getStroke().getPaintType() != SVGPaintingType.NONE) {
				padding = getStrokeWidth().getValue(SVGLengthUnitType.PX) / 2;
			}

		} 
		return new Rectangle2D.Float (
				xPos - r - padding, 
				yPos - r - padding, 
				2 * r + 2 * padding,
				2 * r + 2 * padding);
	}

	@Override
	public SVGImageCanvas draw() {
		Rectangle2D.Float bounds = getBounds();
		SVGImageCanvas canvas = SVGImageCanvas.getBlankCanvas(bounds);
		float r = radius.getValue(SVGLengthUnitType.PX);
		float xPos = cx.getValue(SVGLengthUnitType.PX);
		float yPos = cy.getValue(SVGLengthUnitType.PX);

		if(canvas!=null){
			Graphics2D graphics = canvas.createGraphics();

			Ellipse2D.Double circle = new Ellipse2D.Double( xPos - r - bounds.x , yPos - r - bounds.y , r * 2 , r * 2 );
			graphics.scale(SVGImageCanvas.getZoomScale(), SVGImageCanvas.getZoomScale());
			graphics.setPaint( getFill().getPaintColor() );
			graphics.fill( circle );
			graphics.setPaint( getStroke().getPaintColor() );
			graphics.setStroke( new BasicStroke( getStrokeWidth().getValue() , getStrokeLineCap(), getStrokeLineJoin() ) );
			graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			graphics.draw( circle );
		}

		return canvas;
	}

	/*
	 * METHOD
	 */
	public static SVGCircleElement parseElement(Element element) {
		SVGCircleElement circ = new SVGCircleElement();

		circ.setCx(SVGLengthUnit.parse(element.getAttributeNS(null, "cx")));
		circ.setCy(SVGLengthUnit.parse(element.getAttributeNS(null, "cy")));
		circ.setRadius(SVGLengthUnit.parse(element.getAttributeNS(null, "r")));

		circ.parseAttributes(element);

		return circ;
	}
}
