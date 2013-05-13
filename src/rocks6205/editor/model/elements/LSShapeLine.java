package rocks6205.editor.model.elements;

//~--- non-JDK imports --------------------------------------------------------

import org.w3c.dom.Attr;
import org.w3c.dom.Element;

import rocks6205.editor.model.adt.LSLength;
import rocks6205.editor.model.adt.LSLengthUnitType;
import rocks6205.editor.model.adt.LSPaintingType;

//~--- JDK imports ------------------------------------------------------------

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import static rocks6205.editor.model.elements.LSGenericShape.SHAPE_CIRCLE;

/**
 * The <code>LSShapeLine</code> class is used to parse <<code>line</code>> element in
 * SVG documents and convert to <code>LSShapeLine</code> which is subsequently drawn on
 * the image canvas.
 *
 * @author Sugar CheeSheen Chan
 *
 * @since 1.1
 *
 */
public final class LSShapeLine extends LSGenericShape {

    /*
     * PROPERTIES
     */

    /**
     * x-coordinate of the first point
     */
    private LSLength x1;

    /**
     * y-coordinate of the first point
     */
    private LSLength y1;

    /**
     * x-coordinate of the second point
     */
    private LSLength x2;

    /**
     * y-coordinate of the second point
     */
    private LSLength y2;

    /**
     * Default constructor.
     * Construct an instance of <code>LSShapeLine</code> with coordinates initialised to zero.
     */
    public LSShapeLine() {
        x1 = new LSLength(0);
        y1 = new LSLength(0);
        x2 = new LSLength(0);
        y2 = new LSLength(0);
    }

    /*
     * CONSTRUCTORS
     */

    /**
     * Construct an instance of <code>LSShapeLine</code> with the coordinates
     * of the first point and the second point.
     *
     * @param x1 x-coordinate of the first point
     * @param y1 y-coordinate of the first point
     * @param x2 x-coordinate of the second point
     * @param y2 y-coordinate of the second point
     */
    public LSShapeLine(LSLength x1, LSLength y1, LSLength x2, LSLength y2) {
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
    public LSLength getX1() {
        return this.x1;
    }

    /**
     * @return y1 y-coordinate of the first point
     */
    public LSLength getY1() {
        return this.y1;
    }

    /**
     * @return x2 x-coordinate of the second point
     */
    public LSLength getX2() {
        return this.x2;
    }

    /**
     * @return y2 y-coordinate of the second point
     */
    public LSLength getY2() {
        return this.y2;
    }

    /*
     * MUTATORS
     */

    /**
     * @param x1 x-coordinate of the first point
     */
    public void setX1(LSLength x1) {
        if (x1 != null) {
            this.x1 = x1;
        }
    }

    /**
     * @param y1 y-coordinate of the first point
     */
    public void setY1(LSLength y1) {
        if (y1 != null) {
            this.y1 = y1;
        }
    }

    /**
     * @param x2 x-coordinate of the second point
     */
    public void setX2(LSLength x2) {
        if (x2 != null) {
            this.x2 = x2;
        }
    }

    /**
     * @param y2 y-coordinate of the second point
     */
    public void setY2(LSLength y2) {
        if (y2 != null) {
            this.y2 = y2;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle2D.Float getBounds() {
        Rectangle2D.Float bounds;
        float             padding = 0;

        if (getResultantStroke().getPaintType() != LSPaintingType.NONE) {
            padding = getResultantStrokeWidth().getValue(LSLengthUnitType.PX) / 2;
        }

        float computedX = (float) (Math.min(x1.getValue(LSLengthUnitType.PX), x2.getValue(LSLengthUnitType.PX))
                                   - padding);
        float computedY = (float) (Math.min(y1.getValue(LSLengthUnitType.PX), y2.getValue(LSLengthUnitType.PX))
                                   - padding);
        float computedWidth = (float) (Math.abs(x2.getValue(LSLengthUnitType.PX) - x1.getValue(LSLengthUnitType.PX))
                                       + 2 * padding);
        float computedHeight = (float) (Math.abs(y2.getValue(LSLengthUnitType.PX) - y1.getValue(LSLengthUnitType.PX))
                                        + 2 * padding);

        bounds = new Rectangle2D.Float(computedX, computedY, computedWidth, computedHeight);

        Rectangle2D.Double rect = (Rectangle2D.Double) getTransform().createTransformedShape(bounds).getBounds2D();

        return new Rectangle2D.Float((float) rect.x, (float) rect.y, (float) rect.width, (float) rect.height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawShape(Graphics2D g) {
        Shape line = new Line2D.Float(x1.getValue(LSLengthUnitType.PX), y1.getValue(LSLengthUnitType.PX),
                                      x2.getValue(LSLengthUnitType.PX), y2.getValue(LSLengthUnitType.PX));

        line = getTransform().createTransformedShape(line);
        g.setPaint(getResultantStroke().getPaintColor());
        g.setStroke(new BasicStroke((float) getResultantStrokeWidth().getValue(LSLengthUnitType.PX),
                                    getStrokeLineCap(), getStrokeLineJoin()));
        g.draw(line);
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
    public static LSShapeLine parseElement(Element element) {
        LSShapeLine line   = new LSShapeLine();
        Attr           x1Attr = element.getAttributeNodeNS(null, "x1");

        if (x1Attr != null) {
            line.setX1(LSLength.parse(x1Attr.getValue()));
        }

        Attr y1Attr = element.getAttributeNodeNS(null, "y1");

        if (y1Attr != null) {
            line.setY1(LSLength.parse(y1Attr.getValue()));
        }

        Attr x2Attr = element.getAttributeNodeNS(null, "x2");

        if (x2Attr != null) {
            line.setX2(LSLength.parse(x2Attr.getValue()));
        }

        Attr y2Attr = element.getAttributeNodeNS(null, "y2");

        if (y2Attr != null) {
            line.setY2(LSLength.parse(y2Attr.getValue()));
        }

        line.parseAttributes(element);

        return line;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return LSShapeLine.class.getSimpleName();
    }
    
    /**
      * {@inheritDoc}
      */
    @Override
    public int getShapeType() {
        return SHAPE_LINE;
    }
}