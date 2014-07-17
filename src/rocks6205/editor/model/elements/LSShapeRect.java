package rocks6205.editor.model.elements;

//~--- non-JDK imports --------------------------------------------------------

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import rocks6205.editor.model.adt.LSLength;
import rocks6205.editor.model.adt.LSLengthUnitType;
import rocks6205.editor.model.adt.LSPaintingType;

import java.awt.*;
import java.awt.geom.Rectangle2D;

//~--- JDK imports ------------------------------------------------------------

/**
 * The <code>LSShapeRect</code> class is used to parse <<code>rect</code>> element in
 * SVG documents and convert to <code>LSShapeRect</code> which is subsequently drawn on
 * the image canvas.
 *
 * @author Sugar CheeSheen Chan
 * @since 1.1
 */
public final class LSShapeRect extends LSGenericShape {

    /*
     * PROPERTIES
     */

    /**
     * x-axis coordinate of the side of the rectangle which has the smaller x-axis coordinate
     * value in the current user coordinate system.
     */
    private LSLength x;

    /**
     * y-axis coordinate of the side of the rectangle which has the smaller y-axis coordinate
     * value in the current user coordinate system.
     */
    private LSLength y;

    /**
     * Width of the rectangle
     */
    private LSLength width;

    /**
     * Height of the rectangle.
     */
    private LSLength height;

    /**
     * Default constructor.<p>
     * Construct an instance of <code>LSShapeRect</code> with coordinates and lengths initialised to zero.
     */
    public LSShapeRect() {
        x = new LSLength(0);
        y = new LSLength(0);
        width = new LSLength(0);
        height = new LSLength(0);
    }

    /**
     * Construct an instance of <code>LSShapeRect</code> with the coordinates
     * of the first point and the second point.
     *
     * @param x      x-coordinate of rectangle
     * @param y      y-coordinate of rectangle
     * @param width  Width of the rectangle
     * @param height Height of the rectangle
     */
    public LSShapeRect(LSLength x, LSLength y, LSLength width, LSLength height) {
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
    public LSLength getX() {
        return this.x;
    }

    /**
     * @return y y-coordinate of rectangle
     */
    public LSLength getY() {
        return this.y;
    }

    /**
     * @return width Width of the rectangle
     */
    public LSLength getWidth() {
        return this.width;
    }

    /**
     * @return height Height of the rectangle
     */
    public LSLength getHeight() {
        return this.height;
    }

    /*
     * MUTATORS
     */

    /**
     * @param x x-coordinate of rectangle
     */
    public void setX(LSLength x) {
        if (x != null) {
            this.x = x;
        }
    }

    /**
     * @param y y-coordinate of rectangle
     */
    public void setY(LSLength y) {
        if (y != null) {
            this.y = y;
        }
    }

    /**
     * @param width Width of the rectangle
     */
    public void setWidth(LSLength width) {
        boolean shouldSetWidth = (width != null) && (width.getValue() >= 0);

        if (shouldSetWidth) {
            this.width = width;
        }
    }

    /**
     * @param height Height of the rectangle
     */
    public void setHeight(LSLength height) {
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
        float xVal = x.getValue(LSLengthUnitType.PX);
        float yVal = y.getValue(LSLengthUnitType.PX);
        float widthVal = width.getValue(LSLengthUnitType.PX);
        float heightVal = height.getValue(LSLengthUnitType.PX);
        float padding = 0;
        Rectangle2D.Float bounds = new Rectangle2D.Float();

        if ((widthVal > 0) && (heightVal > 0)) {
            if (getResultantStroke().getPaintType() != LSPaintingType.NONE) {
                padding = getResultantStrokeWidth().getValue(LSLengthUnitType.PX) / 2;
            }

            bounds.x = xVal - padding;
            bounds.y = yVal - padding;
            bounds.width = widthVal + 2 * padding;
            bounds.height = heightVal + 2 * padding;
        }

        Rectangle2D.Double rect = (Rectangle2D.Double) getTransform().createTransformedShape(bounds).getBounds2D();

        return new Rectangle2D.Float((float) rect.x, (float) rect.y, (float) rect.width, (float) rect.height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawShape(Graphics2D g) {
        if ((width.getValue(LSLengthUnitType.PX) > 0) && (height.getValue(LSLengthUnitType.PX) > 0)) {
            Shape rect = new Rectangle2D.Float(x.getValue(LSLengthUnitType.PX), y.getValue(LSLengthUnitType.PX),
                    width.getValue(LSLengthUnitType.PX),
                    height.getValue(LSLengthUnitType.PX));

            rect = getTransform().createTransformedShape(rect);
            g.setPaint(getResultantFill().getPaintColor());
            g.fill(rect);
            g.setPaint(getResultantStroke().getPaintColor());
            g.setStroke(new BasicStroke((float) getResultantStrokeWidth().getValue(LSLengthUnitType.PX),
                    getStrokeLineCap(), getStrokeLineJoin()));
            g.draw(rect);
        }
    }

    /**
     * Parse rectangle element from DOM level to object interpretable as defined
     *
     * @param element Element from the document returned by the XMLParser
     * @return Rectangle element to be drawn parsed from <code>e</code>
     */
    public static LSShapeRect parseElement(Element element) {
        Attr widthAttr = element.getAttributeNodeNS(null, "width");
        Attr heightAttr = element.getAttributeNodeNS(null, "height");

        if ((widthAttr == null) || (heightAttr == null)) {
            return null;
        }

        LSShapeRect rect = new LSShapeRect();
        Attr xAttr = element.getAttributeNodeNS(null, "x");

        if (xAttr != null) {
            rect.setX(LSLength.parse(xAttr.getValue()));
        }

        Attr yAttr = element.getAttributeNodeNS(null, "y");

        if (xAttr != null) {
            rect.setY(LSLength.parse(yAttr.getValue()));
        }

        rect.setWidth(LSLength.parse(widthAttr.getValue()));
        rect.setHeight(LSLength.parse(heightAttr.getValue()));
        rect.parseAttributes(element);

        return rect;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return LSShapeRect.class.getSimpleName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getShapeType() {
        return SHAPE_RECT;
    }
}