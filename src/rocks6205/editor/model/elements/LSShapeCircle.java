package rocks6205.editor.model.elements;

//~--- non-JDK imports --------------------------------------------------------

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import rocks6205.editor.model.adt.LSLength;
import rocks6205.editor.model.adt.LSLengthUnitType;
import rocks6205.editor.model.adt.LSPaintingType;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

//~--- JDK imports ------------------------------------------------------------

/**
 * The <code>LSShapeCircle</code> class is used to parse <<code>circle</code>> element in
 * SVG documents and convert to <code>LSShapeCircle</code> which is subsequently drawn on
 * the image canvas.
 *
 * @author Sugar CheeSheen Chan
 * @since 1.1
 */
public final class LSShapeCircle extends LSGenericShape {

    /*
     * PROPERTIES
     */

    /**
     * x-coordinate of the centre of the circle
     */
    private LSLength cx;

    /**
     * y-coordinate of the centre of the circle
     */
    private LSLength cy;

    /**
     * radius of the centre of the circle
     */
    private LSLength radius;

    /*
     * CONSTRUCTORS
     */

    /**
     * Default constructor.<p>
     * Construct an instance of <code>LSShapeCircle</code> with length initialised to zero.
     */
    public LSShapeCircle() {
        cx = new LSLength(0);
        cy = new LSLength(0);
        radius = new LSLength(0);
    }

    /**
     * Construct an instance of <code>LSShapeCircle</code> with the coordinates
     * of the centre and its radius.
     *
     * @param cx     x-coordinate of the centre of the circle
     * @param cy     y-coordinate of the centre of the circle
     * @param radius radius of the centre of the circle
     */
    public LSShapeCircle(LSLength cx, LSLength cy, LSLength radius) {
        setCx(cx);
        setCy(cy);
        setRadius(radius);
    }

    /*
     * ACCESSORS
     */

    /**
     * @return x-coordinate of the centre of the circle
     */
    public LSLength getCx() {
        return this.cx;
    }

    /**
     * @return y-coordinate of the centre of the circle
     */
    public LSLength getCy() {
        return this.cy;
    }

    /**
     * @return radius radius of the centre of the circle
     */
    public LSLength getRadius() {
        return this.radius;
    }

    /*
     * MUTATORS
     */

    /**
     * @param x-coordinate of the centre of the circle
     */
    public void setCx(LSLength cx) {
        if (cx != null) {
            this.cx = cx;
        }
    }

    /**
     * @param cy y-coordinate of the centre of the circle
     */
    public void setCy(LSLength cy) {
        if (cy != null) {
            this.cy = cy;
        }
    }

    /**
     * @param radius radius of the centre of the circle
     */
    public void setRadius(LSLength radius) {
        if ((radius != null) && (radius.getValue() >= 0)) {
            this.radius = radius;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle2D.Float getBounds() {
        float r = radius.getValue(LSLengthUnitType.PX);
        float xPos = cx.getValue(LSLengthUnitType.PX);
        float yPos = cy.getValue(LSLengthUnitType.PX);
        float padding = 0;
        Rectangle2D.Float bounds = new Rectangle2D.Float();

        if (r > 0) {
            if (getResultantStroke().getPaintType() != LSPaintingType.NONE) {
                padding = getResultantStrokeWidth().getValue(LSLengthUnitType.PX) / 2;
            }

            bounds.x = xPos - r - padding;
            bounds.y = yPos - r - padding;
            bounds.width = 2 * r + 2 * padding;
            bounds.height = 2 * r + 2 * padding;
        }

        Rectangle2D.Double rect = (Rectangle2D.Double) getTransform().createTransformedShape(bounds).getBounds2D();

        return new Rectangle2D.Float((float) rect.x, (float) rect.y, (float) rect.width, (float) rect.height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawShape(Graphics2D g) {
        if (radius.getValue(LSLengthUnitType.PX) > 0) {
            Shape ellipse = new Ellipse2D.Float(cx.getValue(LSLengthUnitType.PX)
                    - radius.getValue(LSLengthUnitType.PX), cy.getValue(LSLengthUnitType.PX)
                    - radius.getValue(LSLengthUnitType.PX), 2 * radius.getValue(LSLengthUnitType.PX), 2
                    * radius.getValue(LSLengthUnitType.PX));

            ellipse = getTransform().createTransformedShape(ellipse);
            g.setPaint(getResultantFill().getPaintColor());
            g.fill(ellipse);
            g.setPaint(getResultantStroke().getPaintColor());
            g.setStroke(new BasicStroke((float) getResultantStrokeWidth().getValue(LSLengthUnitType.PX),
                    getStrokeLineCap(), getStrokeLineJoin()));
            g.draw(ellipse);
        }
    }

    /*
     * METHOD
     */

    /**
     * Parsing <<code>circle</code>> element by extracting coordinates of the
     * centre and the radius, then parse all other supported attributes in
     * the <<code>circle</code>> element
     *
     * @param element Element from the document returned by the XMLParser
     * @return <code>LSShapeCircle</code> object
     */
    public static LSShapeCircle parseElement(Element element) {
        LSShapeCircle circ = new LSShapeCircle();
        Attr centreXAttr = element.getAttributeNodeNS(null, "cx");

        if (centreXAttr != null) {
            circ.setCx(LSLength.parse(centreXAttr.getValue()));
        }

        Attr centreYAttr = element.getAttributeNodeNS(null, "cy");

        if (centreYAttr != null) {
            circ.setCy(LSLength.parse(centreYAttr.getValue()));
        }

        Attr radiusAttr = element.getAttributeNodeNS(null, "r");

        if (radiusAttr != null) {
            circ.setRadius(LSLength.parse(radiusAttr.getValue()));
        }

        circ.parseAttributes(element);

        return circ;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return LSShapeCircle.class.getSimpleName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getShapeType() {
        return SHAPE_CIRCLE;
    }
}
