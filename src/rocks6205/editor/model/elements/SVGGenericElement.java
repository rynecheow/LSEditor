package rocks6205.editor.model.elements;

//~--- non-JDK imports --------------------------------------------------------

import org.w3c.dom.Element;

import rocks6205.editor.model.adt.SVGColorScheme;
import rocks6205.editor.model.adt.SVGLengthUnit;
import rocks6205.editor.model.adt.SVGLengthUnitType;
import rocks6205.editor.model.adt.SVGPainting;
import rocks6205.editor.model.adt.SVGPaintingType;

//~--- JDK imports ------------------------------------------------------------

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

/**
 * The <code>SVGGenericElement</code> class models a generic SVG Element
 * as either container type or graphical type of SVG element would look like.
 *
 * The five attributes that is common in SVG Elements are <<code>fill</code>>,
 * <<code>stroke</code>>, <<code>stroke-width</code>>, <<code>stroke-linecap</code>>,
 * and <<code>stroke-linejoin</code>>.
 *
 * @author Cheow Yeong Chi
 * @since 1.1
 *
 */
public abstract class SVGGenericElement {

    /*
     * DEFAULT ATTRIBUTES FOR PRESENTATION
     */

    /**
     * Default <<code>fill</code>> properties, set to <code>black<code>.
     */
    public static final SVGPainting SVG_FILL_DEFAULT = new SVGPainting(SVGColorScheme.getColorFromKeyword("black"));

    /**
     * Default <<code>stroke</code>> properties, set to <code>NONE</code>.
     */
    public static final SVGPainting SVG_STROKE_DEFAULT = new SVGPainting(SVGPaintingType.NONE);
    /**
     * Default <<code>stroke-width</code>> properties, set to <code>1<code>.
     */
    public static final SVGLengthUnit SVG_STROKE_WIDTH_DEFAULT = new SVGLengthUnit(1);
    /**
     * Default <<code>stroke-linecap</code>> properties, set to <code>butt</code>.
     */
    public static final int SVG_STROKE_LINECAP_DEFAULT = BasicStroke.CAP_BUTT;

    /**
     * Default <<code>stroke-linejoin</code>> properties, set to <code>miter<code>.
     */
    public static final int SVG_STROKE_LINEJOIN_DEFAULT = BasicStroke.JOIN_MITER;

    /*
     * PROPERTIES
     */

    /**
     * Ancestor element of current instance.
     */
    private SVGContainerElement ancestorElement;

    /**
     * <<code>fill</code>> property of current instance.
     */
    private SVGPainting fill;

    /**
     * <<code>stroke</code>> property of current instance.
     */
    private SVGPainting stroke;

    /**
     * <<code>strokeWidth</code>> property of current instance.
     */
    private SVGLengthUnit strokeWidth;

    /**
     * <<code>strokeLineCap</code>> property of current instance.
     */
    private int strokeLineCap;

    /**
     * <<code>strokeLineJoin</code>> property of current instance.
     */
    private int strokeLineJoin;

    /**
     * <<code>translateX</code>> property of current instance
     */
    private SVGLengthUnit translateX;

    /**
     * <<code>translateY</code>> property of current instance
     */
    private SVGLengthUnit translateY;

    /*
     * ABSTRACT METHODS
     */

    /**
     * Calculate and return bounds of an SVG element
     * @return <code>Rectangle2D.Float</code> element
     */
    public abstract Rectangle2D.Float getBounds();

    /**
     * Draw the shape on the canvas accordingly with the attributes correctly parsed.
     * @param g Graphics to be drawn
     */
    public abstract void draw(Graphics2D g);

    /*
     * ACCESSORS
     */

    /**
     * @return Ancestor element of current instance.
     */
    public SVGContainerElement getAncestorElement() {
        return this.ancestorElement;
    }

    /**
     * Returns the sibling element of the same ancestor element/parent node.
     * @return Sibling element
     */
    public SVGGenericElement getNextSiblingElement() {
        if (ancestorElement != null) {
            int index = ancestorElement.indexOf(this) + 1;

            if (index < ancestorElement.getDescendantCount()) {
                return ancestorElement.getDescendant(index);
            }
        }

        return null;
    }

    /**
     * Create an affine transform for current element instance if any.
     * @return Translate instance of current element
     */
    public AffineTransform getTransform() {
        float tx = 0;
        float ty = 0;

        if (translateX != null) {
            tx = translateX.getValue();
        }

        if (translateY != null) {
            ty = translateY.getValue();
        }

        return AffineTransform.getTranslateInstance(tx, ty);
    }

    /**
     * @return <<code>fill</code>> property of current instance.
     */
    public SVGPainting getFill() {
        return this.fill;
    }

    /**
     * Get the effective <<code>fill</code>> as ancestor elements might have defined
     * for.
     *
     * @return <<code>fill</code>> property of current instance.
     */
    public SVGPainting getResultantFill() {
        SVGPainting fill       = this.fill;
        boolean     isFillNull = (fill == null);

        if (!isFillNull) {
            return fill;
        }

        for (SVGContainerElement origin = getAncestorElement(); isFillNull && (origin != null);
                origin = origin.getAncestorElement()) {
            if (origin.getResultantFill() != null) {
                return origin.getResultantFill();
            }
        }

        return SVG_FILL_DEFAULT;
    }

    /**
     * @return <<code>stroke</code>> property of current instance.
     */
    public SVGPainting getStroke() {
        return this.stroke;
    }

    /**
     * Get the effective <<code>stroke</code>> as ancestor elements might have defined
     * for.
     *
     * @return <<code>stroke</code>> property of current instance.
     */
    public SVGPainting getResultantStroke() {
        SVGPainting stroke       = this.stroke;
        boolean     isStrokeNull = (stroke == null);

        if (!isStrokeNull) {
            return stroke;
        }

        for (SVGContainerElement origin = getAncestorElement(); isStrokeNull && (origin != null);
                origin = origin.getAncestorElement()) {
            if (origin.getResultantStroke() != null) {
                return origin.getResultantStroke();
            }
        }

        return SVG_STROKE_DEFAULT;
    }

    /**
     * @return <<code>strokeWidth</code>> property of current instance.
     */
    public SVGLengthUnit getStrokeWidth() {
        return this.strokeWidth;
    }

    /**
     * Get the effective <<code>stroke-width</code>> as ancestor elements might have defined
     * for.
     *
     * @return <<code>strokeWidth</code>> property of current instance.
     */
    public SVGLengthUnit getResultantStrokeWidth() {
        SVGLengthUnit strokeWidth       = this.strokeWidth;
        boolean       isStrokeWidthNull = (strokeWidth == null);

        if (!isStrokeWidthNull) {
            return strokeWidth;
        }

        for (SVGContainerElement origin = getAncestorElement(); isStrokeWidthNull && (origin != null);
                origin = origin.getAncestorElement()) {
            if (origin.getResultantStrokeWidth() != null) {
                return origin.getResultantStrokeWidth();
            }
        }

        return SVG_STROKE_WIDTH_DEFAULT;
    }

    /**
     * @return <<code>strokeLineCap</code>> property of current instance.
     */
    public int getStrokeLineCap() {
        return this.strokeLineCap;
    }

    /**
     * @return <<code>strokeLineJoin</code>> property of current instance.
     */
    public int getStrokeLineJoin() {
        return strokeLineJoin;
    }

    /**
     * @return <<code>translateY</code>> property of current instance.
     */
    public SVGLengthUnit getTranslateX() {
        return translateX;
    }

    /**
     * @return <<code>translateY</code>> property of current instance.
     */
    public SVGLengthUnit getTranslateY() {
        return translateY;
    }

    /*
     * MUTATORS
     */

    /**
     * @param ancestorElement Ancestor element of current instance.
     */
    public void setAncestorElement(SVGContainerElement ancestorElement) {
        this.ancestorElement = ancestorElement;
    }

    /**
     * @param fill <code>fill</code>> property of current instance.
     */
    public void setFill(SVGPainting fill) {
        this.fill = fill;
    }

    /**
     * @param stroke <code>stroke</code>> property of current instance.
     */
    public void setStroke(SVGPainting stroke) {
        this.stroke = stroke;
    }

    /**
     * @param strokeWidth <code>strokeWidth</code>> property of current instance.
     */
    public void setStrokeWidth(SVGLengthUnit strokeWidth) {
        boolean valid = (strokeWidth == null) || (strokeWidth.getValue() >= 0);

        if (valid) {
            this.strokeWidth = strokeWidth;
        }
    }

    /**
     * @param strokeLinecap <code>strokeLineCap</code>> property of current instance.
     */
    public void setStrokeLineCap(int strokeLineCap) {
        boolean valid = strokeLineCap == BasicStroke.CAP_ROUND;

        valid |= strokeLineCap == BasicStroke.CAP_BUTT;
        valid |= strokeLineCap == BasicStroke.CAP_SQUARE;

        if (valid) {
            this.strokeLineCap = strokeLineCap;
        }
    }

    /**
     * @param strokeLineJoin <code>strokeLineJoin</code>> property of current instance.
     */
    public void setStrokeLineJoin(int strokeLineJoin) {
        boolean valid = strokeLineJoin == BasicStroke.JOIN_BEVEL;

        valid |= strokeLineJoin == BasicStroke.JOIN_MITER;
        valid |= strokeLineJoin == BasicStroke.JOIN_ROUND;

        if (valid) {
            this.strokeLineJoin = strokeLineJoin;
        }
    }

    /**
     * @param translateX <code>translateX</code>> property of current instance.
     */
    public void setTranslateX(SVGLengthUnit translateX) {
        if (translateX.getUnitType() != SVGLengthUnitType.NUMBER) {
            throw new IllegalArgumentException("tx must be in user units");
        }

        this.translateX = translateX;
    }

    /**
     * @param translateY <code>translateY</code>> property of current instance.
     */
    public void setTranslateY(SVGLengthUnit translateY) {
        if (translateY.getUnitType() != SVGLengthUnitType.NUMBER) {
            throw new IllegalArgumentException("ty must be in user units");
        }

        this.translateY = translateY;
    }

    /*
     * METHODS
     */

    /**
     * Parsing other general element attributes such as fill, stroke and stroke-width from
     * <code>e</code>
     *
     * @param e Element from the document returned by the XMLParser
     */
    public void parseAttributes(Element e) {
        setFill(SVGPainting.parse(e.getAttributeNS(null, "fill")));
        setStroke(SVGPainting.parse(e.getAttributeNS(null, "stroke")));
        setStrokeWidth(SVGLengthUnit.parse(e.getAttributeNS(null, "stroke-width")));
    }
}