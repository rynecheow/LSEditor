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

import java.awt.geom.Rectangle2D.Double;

import rocks6205.svg.adt.SVGPainting;
import rocks6205.svgFamily.SVGImageCanvas;

public class SVGRectElement extends SVGGenericElement {
	
	/*
	 * PROPERTIES
	 */
	private int x;
	private int y;
	private int width;
	private int height;
	private SVGPainting fill;
	
	/*
	 * CONSTRUCTOR
	 */
	public SVGRectElement( int x , int y , int width , int height ,
			SVGPainting fill) {
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
	public int getX() {
		return this.x;
	}
	
	/**
	 * @return Value of y
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * @return Value of width
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * @return Value of height
	 */
	public int getHeight() {
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
	public void setX( int x ) {
		this.x = x;
	}

	/**
	 * @param y contains value for field y
	 */
	public void setY( int y ) {
		this.y = y;
	}

	/**
	 * @param width contains value for field width
	 */
	public void setWidth( int width ) {
		this.width = width;
	}
	
	/**
	 * @param height contains value for field height
	 */
	public void setHeight( int height ) {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SVGImageCanvas draw() {
		// TODO Auto-generated method stub
		return null;
	}
}
