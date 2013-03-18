/**
 * 
 * Class: SVGCircleElement
 * Description: 
 * 
 * @author: Sugar CheeSheen Chan
 * @version: 1.0
 * @date: 15/03/2013
 * 
 */

package rocks6205.svg.elements;

import java.awt.geom.Rectangle2D.Double;

import rocks6205.svg.adt.SVGLengthUnit;
import rocks6205.svg.adt.SVGPainting;
import rocks6205.svgFamily.SVGImageCanvas;

public class SVGCircleElement extends SVGGenericElement {

	/*
	 * PROPERTIES
	 */
	private SVGLengthUnit cx;
	private SVGLengthUnit cy;
	private SVGLengthUnit radius;
	private SVGPainting fill;

	public SVGCircleElement( SVGLengthUnit cx , SVGLengthUnit cy , SVGLengthUnit radius , 
			SVGPainting fill) {
		this.cx = cx;
		this.cy = cy;
		this.radius = radius;
		this.fill = fill;
	}

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

	/**
	 * @param fill contains value for field 'fill'
	 */
	public void setFill( SVGPainting fill ) {
		this.fill = fill;
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
