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
import java.awt.geom.Rectangle2D;

/**
 * The <code>SVGRectElement</code> class is used to parse <<code>rect</code>> element in
 * SVG documents and convert to <code>SVGRectElement</code> which is subsequently drawn on
 * the image canvas.
 *
 * @author Sugar CheeSheen Chan
 *
 * @since 1.1
 *
 */
public class SVGRectElement extends SVGGenericElement {

    /*
     * PROPERTIES
     */

    /**
     * x-axis coordinate of the side of the rectangle which has the smaller x-axis coordinate
     * value in the current user coordinate system.
     */
    private SVGLengthUnit x;

    /**
     * y-axis coordinate of the side of the rectangle which has the smaller y-axis coordinate
     * value in the current user coordinate system.
     */
    private SVGLengthUnit y;

    /**
     *  Width of the rectangle
     */
    private SVGLengthUnit width;

    /**
     * Height of the rectangle.
     */
    private SVGLengthUnit height;

    /**
     * Default constructor.<p>
     * Construct an instance of <code>SVGRectElement</code> with coordinates and lengths initialised to zero.
     */
    public SVGRectElement() {
        x      = new SVGLengthUnit(0);
        y      = new SVGLengthUnit(0);
        width  = new SVGLengthUnit(0);
        height = new SVGLengthUnit(0);
    }

    /**
     * Construct an instance of <code>SVGRectElement</code> with the coordinates
     * of the first point and the second point.
     *
     * @param x x-coordinate of rectangle
     * @param y y-coordinate of rectangle
     * @param width Width of the rectangle
     * @param height Height of the rectangle
     */
    public SVGRectElement(SVGLengthUnit x, SVGLengthUnit y, SVGLengthUnit width, SVGLengthUnit height) {
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
    }

    /*
     * ACCESSORS
     */

    /**
     * @return x x-coordinate of rectangle
     */
    public SVGLengthUnit getX() {
        return this.x;
    }

    /**
     * @return y y-coordinate of rectangle
     */
    public SVGLengthUnit getY() {
        return this.y;
    }

    /**
     * @return width Width of the rectangle
     */
    public SVGLengthUnit getWidth() {
        return this.width;
    }

    /**
     * @return height Height of the rectangle
     */
    public SVGLengthUnit getHeight() {
        return this.height;
    }

    /*
     * MUTATORS
     */

    /**
     * @param x x-coordinate of rectangle
     */
    public void setX(SVGLengthUnit x) {
        if (x != null) {
            this.x = x;
        }
    }

    /**
     * @param y y-coordinate of rectangle
     */
    public void setY(SVGLengthUnit y) {
        if (y != null) {
            this.y = y;
        }
    }

    /**
     * @param width Width of the rectangle
     */
    public void setWidth(SVGLengthUnit width) {
        boolean shouldSetWidth = (width != null) && (width.getValue() >= 0);

        if (shouldSetWidth) {
            this.width = width;
        }
    }

    /**
     * @param height Height of the rectangle
     */
    public void setHeight(SVGLengthUnit height) {
        boolean shouldSetHeight = (height != null) && (height.getValue() >= 0);

        if (shouldSetHeight) {
            this.height = height;
        }
    }

    /**
     * {@inheritDoc}
     */
    public Rectangle2D.Float getBounds() {
        float   w     = width.getValue();
        float   h     = height.getValue();
        boolean valid = (w > 0) && (h > 0);

        if (valid) {
            float padding = 0;

            if (getStroke().getPaintType() != SVGPaintingType.NONE) {
                padding = getStrokeWidth().getValue() / 2;
            }

            return new Rectangle2D.Float(x.getValue() - padding, y.getValue() - padding, w + 2 * padding,
                                         h + 2 * padding);
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public SVGImageCanvas draw() {
        Rectangle2D.Float bounds = getBounds();
        SVGImageCanvas    canvas = SVGImageCanvas.getBlankCanvas(bounds);

        if (canvas != null) {
            Graphics2D  graphics = canvas.createGraphics();
            Rectangle2D rect     = new Rectangle2D.Double(x.getValue() - bounds.x, y.getValue() - bounds.y,
                                       width.getValue(), height.getValue());

            graphics.scale(SVGImageCanvas.getZoomScale(), SVGImageCanvas.getZoomScale());
            graphics.setStroke(new BasicStroke((float) getStrokeWidth().getValue(), getStrokeLineCap(),
                                               getStrokeLineJoin()));
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setPaint(getFill().getPaintColor());
            graphics.fill(rect);
            graphics.setPaint(getStroke().getPaintColor());
            graphics.draw(rect);
        }

        return canvas;
    }

    /**
     * Parse rectangle element from DOM level to object interpretable as defined
     *
     * @param element   Element from the document returned by the XMLParser
     * @return          Rectangle element to be drawn parsed from <code>e</code>
     */
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
