package rocks6205.svg.elements;

//~--- non-JDK imports --------------------------------------------------------

import org.w3c.dom.Element;

import rocks6205.svg.adt.SVGLengthUnit;
import rocks6205.svg.adt.SVGPaintingType;

import rocks6205.svgFamily.SVGImageCanvas;

//~--- JDK imports ------------------------------------------------------------

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 * The <code>SVGLineElement</code> class is used to parse <<code>line</code>> element in
 * SVG documents and convert to <code>SVGLineElement</code> which is subsequently drawn on
 * the image canvas.
 *
 * @author Sugar CheeSheen Chan
 *
 * @since 1.1
 *
 */
public class SVGLineElement extends SVGGenericElement {

    /*
     * PROPERTIES
     */

    /**
     * x-coordinate of the first point
     */
    private SVGLengthUnit x1;

    /**
     * y-coordinate of the first point
     */
    private SVGLengthUnit y1;

    /**
     * x-coordinate of the second point
     */
    private SVGLengthUnit x2;

    /**
     * y-coordinate of the second point
     */
    private SVGLengthUnit y2;

    /**
     * Default constructor.
     * Construct an instance of <code>SVGLineElement</code> with coordinates initialised to zero.
     */
    public SVGLineElement() {
        x1 = new SVGLengthUnit(0);
        y1 = new SVGLengthUnit(0);
        x2 = new SVGLengthUnit(0);
        y2 = new SVGLengthUnit(0);
    }

    /*
     * CONSTRUCTORS
     */

    /**
     * Construct an instance of <code>SVGLineElement</code> with the coordinates
     * of the first point and the second point.
     *
     * @param x1 x-coordinate of the first point
     * @param y1 y-coordinate of the first point
     * @param x2 x-coordinate of the second point
     * @param y2 y-coordinate of the second point
     */
    public SVGLineElement(SVGLengthUnit x1, SVGLengthUnit y1, SVGLengthUnit x2, SVGLengthUnit y2) {
        setX1(x1);
        setY1(y1);
        setX2(x2);
        setY2(y2);
    }

    /*
     * ACCESSORS
     */

    /**
     * @return x1 x-coordinate of the first point
     */
    public SVGLengthUnit getX1() {
        return this.x1;
    }

    /**
     * @return y1 y-coordinate of the first point
     */
    public SVGLengthUnit getY1() {
        return this.y1;
    }

    /**
     * @return x2 x-coordinate of the second point
     */
    public SVGLengthUnit getX2() {
        return this.x2;
    }

    /**
     * @return y2 y-coordinate of the second point
     */
    public SVGLengthUnit getY2() {
        return this.y2;
    }

    /*
     * MUTATORS
     */

    /**
     * @param x1 x-coordinate of the first point
     */
    public void setX1(SVGLengthUnit x1) {
        if (x1 != null) {
            this.x1 = x1;
        }
    }

    /**
     * @param y1 y-coordinate of the first point
     */
    public void setY1(SVGLengthUnit y1) {
        if (y1 != null) {
            this.y1 = y1;
        }
    }

    /**
     * @param x2 x-coordinate of the second point
     */
    public void setX2(SVGLengthUnit x2) {
        if (x2 != null) {
            this.x2 = x2;
        }
    }

    /**
     * @param y2 y-coordinate of the second point
     */
    public void setY2(SVGLengthUnit y2) {
        if (y2 != null) {
            this.y2 = y2;
        }
    }

    /**
     * {@inheritDoc}
     */
    public Rectangle2D.Float getBounds() {
        float padding = 0;

        if (getStroke().getPaintType() != SVGPaintingType.NONE) {
            padding = getStrokeWidth().getValue() / 2;
        }

        float computedX      = Math.min(x1.getValue(), x2.getValue()) - padding;
        float computedY      = Math.min(y1.getValue(), y2.getValue()) - padding;
        float computedWidth  = Math.abs(x2.getValue() - x1.getValue()) + 2 * padding;
        float computedHeight = Math.abs(y2.getValue() - y1.getValue()) + 2 * padding;

        return new Rectangle2D.Float(computedX, computedY, computedWidth, computedHeight);
    }

    /**
     * {@inheritDoc}
     */
    public SVGImageCanvas draw() {
        Rectangle2D.Float bounds = getBounds();
        SVGImageCanvas    canvas = SVGImageCanvas.getBlankCanvas(bounds);

        if (canvas != null) {
            Graphics2D    graphics = canvas.createGraphics();
            Line2D.Double line     = new Line2D.Double(x1.getValue() - bounds.x, y1.getValue() - bounds.y,
                                         x2.getValue() - bounds.x, y2.getValue() - bounds.y);

            graphics.scale(SVGImageCanvas.getZoomScale(), SVGImageCanvas.getZoomScale());
            graphics.setPaint(getStroke().getPaintColor());
            graphics.setStroke(new BasicStroke((float) getStrokeWidth().getValue(), getStrokeLineCap(),
                                               getStrokeLineJoin()));
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.draw(line);
        }

        return canvas;
    }

    /*
     * METHOD
     */

    /**
     * Parse line element from DOM level to object interpretable as defined
     *
     * @param element         Element from the document returned by the XMLParser
     * @return                Line element parsed from Element to be drawn
     */
    public static SVGLineElement parseElement(Element element) {
        SVGLineElement line = new SVGLineElement();

        line.setX1(SVGLengthUnit.parse(element.getAttributeNS(null, "x1")));
        line.setY1(SVGLengthUnit.parse(element.getAttributeNS(null, "y1")));
        line.setX2(SVGLengthUnit.parse(element.getAttributeNS(null, "x2")));
        line.setY2(SVGLengthUnit.parse(element.getAttributeNS(null, "y2")));
        line.parseAttributes(element);

        return line;
    }
}
