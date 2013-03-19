/**
 * 
 * Class: SVGRectElement
 * Description: 
 * 
 * @author: Sugar CheeSheen Chan
 * @version: 1.0
 * @date: 15/03/2013
 * 
 */

package rocks6205.svg.elements;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

import rocks6205.svg.adt.SVGLengthUnit;
import rocks6205.svg.adt.SVGPainting;
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
	private SVGPainting fill;
	
	/*
	 * CONSTRUCTOR
	 */
	public SVGRectElement( SVGLengthUnit x , SVGLengthUnit y , SVGLengthUnit width , SVGLengthUnit height ,
			SVGPainting fill , SVGPainting stroke , SVGLengthUnit strokeWidth ) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.fill = fill;
	}
	
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
	
	/**
	 * @return Value of fill
	 */
	public SVGPainting getFill() {
		return this.fill;
	}
	
	/*
	 * MUTATORS
	 */
	/**
	 * @param x contains value for field x
	 */
	public void setX( SVGLengthUnit x ) {
		this.x = x;
	}

	/**
	 * @param y contains value for field y
	 */
	public void setY( SVGLengthUnit y ) {
		this.y = y;
	}

	/**
	 * @param width contains value for field width
	 */
	public void setWidth( SVGLengthUnit width ) {
		this.width = width;
	}
	
	/**
	 * @param height contains value for field height
	 */
	public void setHeight( SVGLengthUnit height ) {
		this.height = height;
	}
	
	/**
	 * @param fill contains value for field fill
	 */
	public void setFill( SVGPainting fill ) {
		this.fill = fill;
	}

	@Override
	public Double getBounds() {
		float padding = 0;
		if (getStroke().getPaintType() != SVGPaintingType.NONE) {
			padding = getStrokeWidth().getValue() / 2;
		}
		return new Rectangle2D.Double( x.getValue() - padding , 
				y.getValue() - padding , width.getValue() , height.getValue() );
	}

	@Override
	public SVGImageCanvas draw() {
		Rectangle2D.Double bounds = getBounds();
		SVGImageCanvas canvas = SVGImageCanvas.getBlankCanvas(bounds);
		Graphics2D graphics = canvas.createGraphics();
		
		Rectangle2D rect = new Rectangle2D.Double( x.getValue() - bounds.x , 
				 y.getValue() - bounds.y , width.getValue() , height.getValue() );
		graphics.scale(SVGImageCanvas.getZoomScale(), SVGImageCanvas.getZoomScale());
		graphics.setStroke( new BasicStroke( super.strokeWidth.getValue() , getStrokeLineCap(), getStrokeLineJoin() ) );
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		graphics.setPaint( this.fill.getPaintColor() );
		graphics.fill( rect );
		graphics.setPaint( stroke.getPaintColor() );
		graphics.draw( rect );
		
		return canvas;
	}
}
