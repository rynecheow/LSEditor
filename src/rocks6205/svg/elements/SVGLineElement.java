/**
 * 
 * Class: SVGLineElement
 * Description: 
 * 
 * @author: Sugar CheeSheen Chan
 * @version: 1.0
 * @date: 14/03/2013
 * 
 */

package rocks6205.svg.elements;

import java.awt.geom.Rectangle2D.Double;

import rocks6205.svg.adt.SVGLengthUnit;
import rocks6205.svg.adt.SVGPainting;
import rocks6205.svgFamily.SVGImageCanvas;

public class SVGLineElement extends SVGGenericElement {

	/*
	 * PROPERTIES
	 */
	private SVGLengthUnit x1 = new SVGLengthUnit(0);
	private SVGLengthUnit y1 = new SVGLengthUnit(0);
	private SVGLengthUnit x2 = new SVGLengthUnit(0);
	private SVGLengthUnit y2 = new SVGLengthUnit(0);

	/*
	 * CONSTRUCTOR
	 */
	public SVGLineElement( SVGLengthUnit x1 , SVGLengthUnit y1 , SVGLengthUnit x2 , SVGLengthUnit y2 , 
			SVGPainting stroke , SVGPainting strokeWidth ) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

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
	public void setX( SVGLengthUnit x1 ) {
		this.x1 = x1;
	}

	/**
	 * @param y1 contains value for field y1
	 */
	public void setY( SVGLengthUnit y1 ) {
		this.y1 = y1;
	}

	/**
	 * @param x2 contains value for field x2
	 */
	public void setWidth( SVGLengthUnit x2 ) {
		this.x2 = x2;
	}

	/**
	 * @param y2 contains value for field y2
	 */
	public void setY2( SVGLengthUnit y2 ) {
		this.y2 = y2;
	}

	@Override
	public Double getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SVGImageCanvas draw() {
		// TODO Auto-generated method stub
		return null;
	}
}
