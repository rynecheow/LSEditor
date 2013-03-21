/**
 * 
 * Class: SVGRectElement
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
import java.awt.geom.Rectangle2D;
import org.w3c.dom.Element;

import rocks6205.svg.adt.SVGLengthUnit;
import rocks6205.svg.adt.SVGPaintingType;
import rocks6205.svgFamily.SVGImageCanvas;

public class SVGRectElement extends SVGGenericElement {

	/*
	 * PROPERTIES
	 */
	private SVGLengthUnit x = new SVGLengthUnit( 0 );
	private SVGLengthUnit y = new SVGLengthUnit( 0 );
	private SVGLengthUnit width = new SVGLengthUnit( 0 );
	private SVGLengthUnit height = new SVGLengthUnit( 0 );

	/*
	 * CONSTRUCTOR
	 */
	public SVGRectElement( SVGLengthUnit x , SVGLengthUnit y , SVGLengthUnit width , SVGLengthUnit height) {
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
	}
	public SVGRectElement(){}
	/*
	 * ACCESSORS
	 */
	/**
	 * @return Value of x
	 */
	public SVGLengthUnit getX() {
		return this.x;
	}

	/**
	 * @return Value of y
	 */
	public SVGLengthUnit getY() {
		return this.y;
	}

	/**
	 * @return Value of width
	 */
	public SVGLengthUnit getWidth() {
		return this.width;
	}

	/**
	 * @return Value of height
	 */
	public SVGLengthUnit getHeight() {
		return this.height;
	}

	/*
	 * MUTATORS
	 */
	/**
	 * @param x contains value for field x
	 */
	public void setX( SVGLengthUnit x ) {
		if (x != null)	this.x = x;
	}

	/**
	 * @param y contains value for field y
	 */
	public void setY( SVGLengthUnit y ) {
		if (y != null)	this.y = y;
	}

	/**
	 * @param width contains value for field width
	 */
	public void setWidth( SVGLengthUnit width ) {
		boolean shouldSetWidth = width!= null && width.getValue() >= 0 ;
		if (shouldSetWidth)	this.width = width;
	}

	/**
	 * @param height contains value for field height
	 */
	public void setHeight( SVGLengthUnit height ) {
		boolean shouldSetHeight = height!= null && height.getValue() >= 0;
		if (shouldSetHeight)	this.height = height;
	}

	@Override
	public Rectangle2D.Float getBounds() {
		float w = width.getValue();
		float h = height.getValue();
		boolean valid = (w > 0) && (h > 0);

		if(valid){
			float padding = 0;
			if (getStroke().getPaintType() != SVGPaintingType.NONE) {
				padding = getStrokeWidth().getValue() / 2;
			}

			return new Rectangle2D.Float( x.getValue() - padding , 
					y.getValue() - padding , w + 2 * padding , h + 2 * padding );
		}else{
			return null;
		}
	}

	@Override
	public SVGImageCanvas draw() {
		Rectangle2D.Float bounds = getBounds();
		SVGImageCanvas canvas = SVGImageCanvas.getBlankCanvas(bounds);
		if(canvas != null){
			Graphics2D graphics = canvas.createGraphics();

			Rectangle2D rect = new Rectangle2D.Double( x.getValue() - bounds.x , 
					y.getValue() - bounds.y , width.getValue() , height.getValue() );
			graphics.scale(SVGImageCanvas.getZoomScale(), SVGImageCanvas.getZoomScale());
			graphics.setStroke( new BasicStroke((float)getStrokeWidth().getValue() , getStrokeLineCap(), getStrokeLineJoin() ) );
			graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			graphics.setPaint( getFill().getPaintColor() );
			graphics.fill( rect );
			graphics.setPaint( getStroke().getPaintColor() );
			graphics.draw( rect );
		}
		return canvas;
	}

	public static SVGRectElement parseElement(Element element) {
		SVGRectElement rect = new SVGRectElement();

		rect.setX(SVGLengthUnit.parse(element.getAttributeNS(null, "x")));
		rect.setY(SVGLengthUnit.parse(element.getAttributeNS(null, "y")));
		rect.setWidth(SVGLengthUnit.parse(element.getAttributeNS(null, "width")));
		rect.setHeight(SVGLengthUnit.parse(element.getAttributeNS(null, "height")));
		rect.parseAttributes(element);

		return rect;
	}
}
