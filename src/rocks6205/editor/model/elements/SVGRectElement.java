package rocks6205.editor.model.elements;

//~--- non-JDK imports --------------------------------------------------------

import org.w3c.dom.Attr;
import org.w3c.dom.Element;

import rocks6205.editor.model.adt.SVGLengthUnit;
import rocks6205.editor.model.adt.SVGLengthUnitType;
import rocks6205.editor.model.adt.SVGPaintingType;

//~--- JDK imports ------------------------------------------------------------

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Shape;
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
public final class SVGRectElement extends SVGGenericElement {

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
    @Override
    public Rectangle2D.Float getBounds() {
        float             xVal      = x.getValue(SVGLengthUnitType.PX);
        float             yVal      = y.getValue(SVGLengthUnitType.PX);
        float             widthVal  = width.getValue(SVGLengthUnitType.PX);
        float             heightVal = height.getValue(SVGLengthUnitType.PX);
        float             padding   = 0;
        Rectangle2D.Float bounds    = new Rectangle2D.Float();

        if ((widthVal > 0) && (heightVal > 0)) {
            if (getResultantStroke().getPaintType() != SVGPaintingType.NONE) {
                padding = getResultantStrokeWidth().getValue(SVGLengthUnitType.PX) / 2;
            }

            bounds.x      = xVal - padding;
            bounds.y      = yVal - padding;
            bounds.width  = widthVal + 2 * padding;
            bounds.height = heightVal + 2 * padding;
        }

        Rectangle2D.Double rect = (Rectangle2D.Double) getTransform().createTransformedShape(bounds).getBounds2D();
        return new Rectangle2D.Float((float)rect.x, (float)rect.y, (float)rect.width, (float)rect.height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(Graphics2D g) {
        if ((width.getValue(SVGLengthUnitType.PX) > 0) && (height.getValue(SVGLengthUnitType.PX) > 0)) {
            Shape rect = new Rectangle2D.Float(x.getValue(SVGLengthUnitType.PX), y.getValue(SVGLengthUnitType.PX),
                             width.getValue(SVGLengthUnitType.PX), height.getValue(SVGLengthUnitType.PX));

            rect = getTransform().createTransformedShape(rect);
            g.setPaint(getResultantFill().getPaintColor());
            g.fill(rect);
            g.setPaint(getResultantStroke().getPaintColor());
            g.setStroke(new BasicStroke((float) getResultantStrokeWidth().getValue(SVGLengthUnitType.PX),
                                        getStrokeLineCap(), getStrokeLineJoin()));
            g.draw(rect);
        }
    }

    /**
     * Parse rectangle element from DOM level to object interpretable as defined
     *
     * @param element   Element from the document returned by the XMLParser
     * @return          Rectangle element to be drawn parsed from <code>e</code>
     */
    public static SVGRectElement parseElement(Element element) {
        Attr widthAttr  = element.getAttributeNodeNS(null, "width");
        Attr heightAttr = element.getAttributeNodeNS(null, "height");

        if ((widthAttr == null) || (heightAttr == null)) {
            return null;
        }

        SVGRectElement rect  = new SVGRectElement();
        Attr           xAttr = element.getAttributeNodeNS(null, "x");

        if (xAttr != null) {
            rect.setX(SVGLengthUnit.parse(xAttr.getValue()));
        }

        Attr yAttr = element.getAttributeNodeNS(null, "y");

        if (xAttr != null) {
            rect.setY(SVGLengthUnit.parse(yAttr.getValue()));
        }

        rect.setWidth(SVGLengthUnit.parse(widthAttr.getValue()));
        rect.setHeight(SVGLengthUnit.parse(heightAttr.getValue()));
        rect.parseAttributes(element);

        return rect;
    }
}