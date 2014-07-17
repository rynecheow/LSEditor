package rocks6205.editor.model.elements;

//~--- non-JDK imports --------------------------------------------------------

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import rocks6205.editor.model.adt.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//~--- JDK imports ------------------------------------------------------------

/**
 * The <code>LSGenericElement</code> class models a generic SVG Element
 * as either container type or graphical type of SVG element would look like.
 * <p>
 * The five attributes that is common in SVG Elements are <<code>fill</code>>,
 * <<code>stroke</code>>, <<code>stroke-width</code>>, <<code>stroke-linecap</code>>,
 * and <<code>stroke-linejoin</code>>.
 *
 * @author Cheow Yeong Chi
 * @since 1.1
 */
public abstract class LSGenericElement {

    /*
     * DEFAULT ATTRIBUTES FOR PRESENTATION
     */

    /**
     * Default <<code>fill</code>> properties, set to <code>black<code>.
     */
    public static final LSPainting SVG_FILL_DEFAULT = new LSPainting(LSColor.getColorFromKeyword("black"));

    /**
     * Default <<code>stroke</code>> properties, set to <code>NONE</code>.
     */
    public static final LSPainting SVG_STROKE_DEFAULT = new LSPainting(LSPaintingType.NONE);

    /**
     * Default <<code>stroke-width</code>> properties, set to <code>1<code>.
     */
    public static final LSLength SVG_STROKE_WIDTH_DEFAULT = new LSLength(1);

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
    private LSGenericContainer ancestorElement;

    /**
     * <<code>fill</code>> property of current instance.
     */
    private LSPainting fill;

    /**
     * <<code>stroke</code>> property of current instance.
     */
    private LSPainting stroke;

    /**
     * <<code>strokeWidth</code>> property of current instance.
     */
    private LSLength strokeWidth;

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
    private LSLength translateX;

    /**
     * <<code>translateY</code>> property of current instance
     */
    private LSLength translateY;

    /*
     * ABSTRACT METHODS
     */

    /**
     * Calculate and return bounds of an SVG element
     *
     * @return <code>Rectangle2D.Float</code> element
     */
    public abstract Rectangle2D.Float getBounds();

    /**
     * Draw the shape on the canvas accordingly with the attributes correctly parsed.
     *
     * @param g Graphics to be drawn
     */
    public abstract void drawShape(Graphics2D g);

    /**
     * Return type of itself in <code>String</code> format
     */
    @Override
    public abstract String toString();

    /*
     * ACCESSORS
     */

    /**
     * @return Ancestor element of current instance.
     */
    public LSGenericContainer getAncestorElement() {
        return this.ancestorElement;
    }

    /**
     * Returns the sibling element of the same ancestor element/parent node.
     *
     * @return Sibling element
     */
    public LSGenericElement getNextSiblingElement() {
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
     *
     * @return Translate instance of current element
     */
    public AffineTransform getTransform() {
        double tx = 0;
        double ty = 0;

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
    public LSPainting getFill() {
        return this.fill;
    }

    /**
     * Get the effective <<code>fill</code>> as ancestor elements might have defined
     * for.
     *
     * @return <<code>fill</code>> property of current instance.
     */
    public LSPainting getResultantFill() {
        LSPainting effFill = this.fill;
        boolean isFillNull = (effFill == null);

        if (!isFillNull) {
            return effFill;
        }

        for (LSGenericContainer origin = getAncestorElement(); (origin != null);
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
    public LSPainting getStroke() {
        return this.stroke;
    }

    /**
     * Get the effective <<code>stroke</code>> as ancestor elements might have defined
     * for.
     *
     * @return <<code>stroke</code>> property of current instance.
     */
    public LSPainting getResultantStroke() {
        LSPainting effStroke = this.stroke;
        boolean isStrokeNull = (effStroke == null);

        if (!isStrokeNull) {
            return effStroke;
        }

        for (LSGenericContainer origin = getAncestorElement(); (origin != null);
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
    public LSLength getStrokeWidth() {
        return this.strokeWidth;
    }

    /**
     * Get the effective <<code>stroke-width</code>> as ancestor elements might have defined
     * for.
     *
     * @return <<code>strokeWidth</code>> property of current instance.
     */
    public LSLength getResultantStrokeWidth() {
        LSLength effStrokeWidth = this.strokeWidth;
        boolean isStrokeWidthNull = (effStrokeWidth == null);

        if (!isStrokeWidthNull) {
            return effStrokeWidth;
        }

        for (LSGenericContainer origin = getAncestorElement(); (origin != null);
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
    public LSLength getTranslateX() {
        return translateX;
    }

    /**
     * @return <<code>translateY</code>> property of current instance.
     */
    public LSLength getTranslateY() {
        return translateY;
    }

    /*
     * MUTATORS
     */

    /**
     * @param ancestorElement Ancestor element of current instance.
     */
    public void setAncestorElement(LSGenericContainer ancestorElement) {
        this.ancestorElement = ancestorElement;
    }

    /**
     * @param fill <code>fill</code>> property of current instance.
     */
    public void setFill(LSPainting fill) {
        this.fill = fill;
    }

    /**
     * @param stroke <code>stroke</code>> property of current instance.
     */
    public void setStroke(LSPainting stroke) {
        this.stroke = stroke;
    }

    /**
     * @param strokeWidth <code>strokeWidth</code>> property of current instance.
     */
    public void setStrokeWidth(LSLength strokeWidth) {
        boolean valid = (strokeWidth == null) || (strokeWidth.getValue() >= 0);

        if (valid) {
            this.strokeWidth = strokeWidth;
        }
    }

    /**
     * @param strokeLineCap <code>strokeLineCap</code>> property of current instance.
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
    public void setTranslateX(LSLength translateX) {
        if (translateX.getUnitType() != LSLengthUnitType.NUMBER) {
            throw new IllegalArgumentException("tx must be in user units");
        }

        this.translateX = translateX;
    }

    /**
     * @param translateY <code>translateY</code>> property of current instance.
     */
    public void setTranslateY(LSLength translateY) {
        if (translateY.getUnitType() != LSLengthUnitType.NUMBER) {
            throw new IllegalArgumentException("ty must be in user units");
        }

        this.translateY = translateY;
    }

    /*
     * METHODS
     */

    /**
     * Parse all other common attributes.
     *
     * @param e Element from the document returned by the XMLParser
     */
    public void parseAttributes(Element e) {
        parseGeometricalTransformation(e);
        parsePresentationalAttributes(e);
    }

    private void parsePresentationalAttributes(Element e) {
        setFill(LSPainting.parse(e.getAttributeNS(null, "fill")));
        setStroke(LSPainting.parse(e.getAttributeNS(null, "stroke")));
        setStrokeWidth(LSLength.parse(e.getAttributeNS(null, "stroke-width")));
    }

    private void parseGeometricalTransformation(Element e) {
        Attr transformAttr = e.getAttributeNodeNS(null, "transform");

        if (transformAttr != null) {
            Matcher translateMatcher = Pattern.compile(LSSVGPrimitive.TRANSLATE).matcher(transformAttr.getValue());
            LSLength txLength;
            LSLength tyLength;

            while (translateMatcher.find()) {
                txLength = LSLength.parse(translateMatcher.group(1));

                if ((txLength != null) && (txLength.getUnitType() == LSLengthUnitType.NUMBER)) {
                    if (translateX == null) {
                        translateX = new LSLength(txLength.getValue());
                    } else {
                        translateX = new LSLength(translateX.getValue() + txLength.getValue());
                    }
                }

                tyLength = LSLength.parse(translateMatcher.group(7));

                if ((tyLength != null) && (tyLength.getUnitType() == LSLengthUnitType.NUMBER)) {
                    if (translateY == null) {
                        translateY = new LSLength(tyLength.getValue());
                    } else {
                        translateY = new LSLength(translateY.getValue() + tyLength.getValue());
                    }
                }
            }
        }
    }
}