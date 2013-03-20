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
import rocks6205.svg.adt.SVGPaintingType;
import rocks6205.svgFamily.SVGImageCanvas;

public class SVGCircleElement extends SVGGenericElement {

	/*
	 * PROPERTIES
	 */
	private SVGLengthUnit cx;
	private SVGLengthUnit cy;
	private SVGLengthUnit radius;

	/*
	 * CONSTRUCTORS
	 */
	public SVGCircleElement( SVGLengthUnit cx , SVGLengthUnit cy , SVGLengthUnit radius) {
		this.cx = cx;
		this.cy = cy;
		this.radius = radius;
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
	public void setCx( SVGLengthUnit cx ) {
		this.cx = cx;
	}

	/**
	 * @param cy contains value for field 'cy'
	 */
	public void setCy( SVGLengthUnit cy ) {
		this.cy = cy;
	}

	/**

	 * @param radius contains value for field radius
	 */
	public void setRadius( SVGLengthUnit radius ) {
		this.radius = radius;
	}

	@Override
	public Rectangle2D.Float getBounds() {
		float r = radius.getValue();
		float xPos = cx.getValue();
		float yPos = cy.getValue();

		if (r > 0) {
			float padding = 0;
			if (getStroke().getPaintType() != SVGPaintingType.NONE) {
				padding = getStrokeWidth().getValue() / 2;
			}
			return new Rectangle2D.Float (xPos - r - padding, yPos - r - padding, 2 * r + 2 * padding,2 * r + 2 * padding);
		} else {
			return null;
		}
	}

	@Override
	public SVGImageCanvas draw() {
		Rectangle2D.Float bounds = getBounds();
		SVGImageCanvas canvas = SVGImageCanvas.getBlankCanvas(bounds);
		float r = radius.getValue();
		float xPos = cx.getValue();
		float yPos = cy.getValue();

		if(canvas!=null){
			Graphics2D graphics = canvas.createGraphics();

			Ellipse2D.Double circle = new Ellipse2D.Double( xPos - r - bounds.x , yPos - r - bounds.y , r * 2 , r * 2 );
			graphics.scale(SVGImageCanvas.getZoomScale(), SVGImageCanvas.getZoomScale());
			graphics.setStroke( new BasicStroke( getStrokeWidth().getValue() , getStrokeLineCap(), getStrokeLineJoin() ) );

			graphics.setPaint( getFill().getPaintColor() );
			graphics.fill( circle );
			graphics.setPaint( getStroke().getPaintColor() );
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
