/**
 * 
 * Class: SVGGenericElement
 * Description: 
 * 
 * @author: Cheow Yeong Chi
 * @date: 14/03/2013
 * 
 */
package rocks6205.svg.elements;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Rectangle2D;

import org.w3c.dom.Element;

import rocks6205.svg.adt.SVGColorScheme;
import rocks6205.svg.adt.SVGLengthUnit;
import rocks6205.svg.adt.SVGPainting;
import rocks6205.svg.adt.SVGPaintingType;
import rocks6205.svgFamily.SVGImageCanvas;

public abstract class SVGGenericElement {

	public static final String SVGDefaultNamespace = "http://www.w3.org/2000/svg";

	/*
	 * DEFAULT ATTRIBUTES FOR PRESENTATION
	 */
	public static final SVGPainting SVG_FILL_DEFAULT = new SVGPainting(new SVGColorScheme(0,0,0,0));

	public static final SVGPainting SVG_STROKE_DEFAULT = new SVGPainting(SVGPaintingType.NONE);
	public static final SVGLengthUnit SVG_STROKE_WIDTH_DEFAULT = new SVGLengthUnit(1);
	public static final int SVG_STROKE_LINECAP_DEFAULT = BasicStroke.CAP_BUTT;
	public static final int SVG_STROKE_LINEJOIN_DEFAULT = BasicStroke.JOIN_MITER;

	/*
	 * PROPERTIES
	 */
	private SVGContainerElement ancestorElement;

	private SVGPainting fill;
	private SVGPainting stroke;
	private SVGLengthUnit strokeWidth;
	private int strokeLineCap;
	private int strokeLineJoin;

	/*
	 * ABSTRACT METHODS
	 */
	public abstract Rectangle2D.Float getBounds();
	public abstract SVGImageCanvas draw();

	/*
	 * ACCESSORS
	 */

	/**
	 * @return Origin element
	 */
	public SVGContainerElement getAncestorElement(){
		return this.ancestorElement;
	}

	/**
	 * 
	 * @return 
	 */
	public SVGPainting getFill() {
		SVGPainting fill = this.fill;
		
		boolean isFillNull = (fill == null);
		if (!isFillNull) return fill;

		for (SVGContainerElement origin = getAncestorElement(); isFillNull && origin !=null; origin = origin.getAncestorElement()) {
			if (origin.getFill() != null) {
				return origin.getFill();
			}
		}
		return SVG_FILL_DEFAULT;
	}

	/**
	 * 
	 * @return
	 */
	public SVGPainting getStroke() {
		SVGPainting stroke = this.stroke;
		boolean isStrokeNull = (stroke == null);
		if (!isStrokeNull) return stroke;

		for (SVGContainerElement origin = getAncestorElement(); isStrokeNull && origin != null; origin = origin.getAncestorElement()) {
			if (origin.getStroke() != null) {
				return origin.getStroke();
			}
		}
		return SVG_STROKE_DEFAULT;
	}

	/**
	 * 
	 * @return
	 */
	public SVGLengthUnit getStrokeWidth() {
		SVGLengthUnit strokeWidth = this.strokeWidth;
		
		boolean isStrokeWidthNull = (strokeWidth == null);
		if (!isStrokeWidthNull) return strokeWidth;

		for (SVGContainerElement origin = getAncestorElement(); isStrokeWidthNull && origin!=null; origin = origin.getAncestorElement()) {
			if (origin.getStrokeWidth() != null) {
				return origin.getStrokeWidth();
			}
		}
		return SVG_STROKE_WIDTH_DEFAULT;
	}

	/**
	 * 
	 * @return
	 */
	public int getStrokeLineCap() {
		return this.strokeLineCap;
	}

	/**
	 * 
	 * @return
	 */
	public int getStrokeLineJoin() {
		return strokeLineJoin;
	}

	/*
	 * MUTATORS
	 */
	/**
	 * @param originElement
	 */
	public void setAncestorElement(SVGContainerElement ancestorElement) {
		this.ancestorElement = ancestorElement;
	}

	/**
	 * @param fill
	 */
	public void setFill(SVGPainting fill) {
		this.fill = fill;
	}

	/**
	 * @param stroke
	 */
	public void setStroke(SVGPainting stroke) {
		this.stroke = stroke;
	}

	/**
	 * @param strokeWidth
	 */
	public void setStrokeWidth(SVGLengthUnit strokeWidth) {
		boolean valid = (strokeWidth == null) || (strokeWidth.getValue() >= 0);

		if (valid) this.strokeWidth = strokeWidth;
	}

	/**
	 * @param strokeLinecap
	 */
	public void setStrokeLineCap(int strokeLineCap) {
		boolean valid =  strokeLineCap == BasicStroke.CAP_ROUND; 
		valid |= strokeLineCap == BasicStroke.CAP_BUTT;
		valid |= strokeLineCap == BasicStroke.CAP_SQUARE;

		if (valid) this.strokeLineCap = strokeLineCap;
	}

	/**
	 * @param strokeLineJoin
	 */
	public void setStrokeLineJoin(int strokeLineJoin) {
		boolean valid = strokeLineJoin == BasicStroke.JOIN_BEVEL;
		valid |=  strokeLineJoin == BasicStroke.JOIN_MITER;
		valid |=  strokeLineJoin == BasicStroke.JOIN_ROUND;

		if (valid) this.strokeLineJoin = strokeLineJoin;
	}

	/*
	 * METHODS
	 */
	/**
	 * 
	 * @param e
	 */
	public void parseAttributes(Element e) {
		setFill(SVGPainting.parse(e.getAttributeNS(null, "fill")));
		setStroke(SVGPainting.parse(e.getAttributeNS(null, "stroke")));
		setStrokeWidth(SVGLengthUnit.parse(e.getAttributeNS(null,"stroke-width")));
	}
}
