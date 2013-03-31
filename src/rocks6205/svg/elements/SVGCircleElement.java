package rocks6205.svg.elements;

//~--- non-JDK imports --------------------------------------------------------

import org.w3c.dom.Attr;
import org.w3c.dom.Element;

import rocks6205.svg.adt.SVGLengthUnit;
import rocks6205.svg.adt.SVGLengthUnitType;
import rocks6205.svg.adt.SVGPaintingType;
import rocks6205.svg.properties.SVGImageCanvas;

//~--- JDK imports ------------------------------------------------------------

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * The <code>SVGCircleElement</code> class is used to parse <<code>circle</code>> element in
 * SVG documents and convert to <code>SVGCircleElement</code> which is subsequently drawn on
 * the image canvas.
 *
 * @author Sugar CheeSheen Chan
 *
 * @since 1.1
 *
 */
public class SVGCircleElement extends SVGGenericElement {

    /*
     * PROPERTIES
     */

    /**
     * x-coordinate of the centre of the circle
     */
    private SVGLengthUnit cx;

    /**
     * y-coordinate of the centre of the circle
     */
    private SVGLengthUnit cy;

    /**
     * radius of the centre of the circle
     */
    private SVGLengthUnit radius;

    /*
     * CONSTRUCTORS
     */

    /**
     * Default constructor.<p>
     * Construct an instance of <code>SVGCircleElement</code> with length initialised to zero.
     */
    public SVGCircleElement() {
        cx     = new SVGLengthUnit(0);
        cy     = new SVGLengthUnit(0);
        radius = new SVGLengthUnit(0);
    }

    /**
     * Construct an instance of <code>SVGCircleElement</code> with the coordinates
     * of the centre and its radius.
     *
     * @param cx x-coordinate of the centre of the circle
     * @param cy y-coordinate of the centre of the circle
     * @param radius radius of the centre of the circle
     */
    public SVGCircleElement(SVGLengthUnit cx, SVGLengthUnit cy, SVGLengthUnit radius) {
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
    public SVGLengthUnit getCx() {
        return this.cx;
    }

    /**
     * @return y-coordinate of the centre of the circle
     */
    public SVGLengthUnit getCy() {
        return this.cy;
    }

    /**
     * @return radius radius of the centre of the circle
     */
    public SVGLengthUnit getRadius() {
        return this.radius;
    }

    /*
     * MUTATORS
     */

    /**
     * @param x-coordinate of the centre of the circle
     */
    public void setCx(SVGLengthUnit cx) {
        if (cx != null) {
            this.cx = cx;
        }
    }

    /**
     * @param cy y-coordinate of the centre of the circle
     */
    public void setCy(SVGLengthUnit cy) {
        if (cy != null) {
            this.cy = cy;
        }
    }

    /**
     * @param radius radius of the centre of the circle
     */
    public void setRadius(SVGLengthUnit radius) {
        if ((radius != null) && (radius.getValue() >= 0)) {
            this.radius = radius;
        }
    }

    /**
     * {@inheritDoc}
     */
    public Rectangle2D.Float getBounds() {
        float             r       = radius.getValue(SVGLengthUnitType.PX);
        float             xPos    = cx.getValue(SVGLengthUnitType.PX);
        float             yPos    = cy.getValue(SVGLengthUnitType.PX);
        float             padding = 0;
        Rectangle2D.Float bounds  = new Rectangle2D.Float();

        if (r > 0) {
            if (getResultantStroke().getPaintType() != SVGPaintingType.NONE) {
                padding = getResultantStrokeWidth().getValue(SVGLengthUnitType.PX) / 2;
            }

            bounds.x      = xPos - r - padding;
            bounds.y      = yPos - r - padding;
            bounds.width  = 2 * r + 2 * padding;
            bounds.height = 2 * r + 2 * padding;
        }

        return (Rectangle2D.Float) getTransform().createTransformedShape(bounds).getBounds2D();
    }

    /**
     * {@inheritDoc}
     */
    public SVGImageCanvas draw() {
        Rectangle2D.Float bounds = getBounds();
        SVGImageCanvas    canvas = SVGImageCanvas.getBlankCanvas(bounds);
        float             r      = radius.getValue();
        float             xPos   = cx.getValue();
        float             yPos   = cy.getValue();

        if (canvas != null) {
            Graphics2D       graphics = canvas.createGraphics();
            Ellipse2D.Double circle   = new Ellipse2D.Double(xPos - r - bounds.x, yPos - r - bounds.y, r * 2, r * 2);

            graphics.scale(SVGImageCanvas.getZoomScale(), SVGImageCanvas.getZoomScale());
            graphics.setStroke(new BasicStroke(getResultantStrokeWidth().getValue(), getStrokeLineCap(),
                                               getStrokeLineJoin()));
            graphics.setPaint(getResultantFill().getPaintColor());
            graphics.fill(circle);
            graphics.setPaint(getResultantStroke().getPaintColor());
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.draw(circle);
        }

        return canvas;
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
     * @return <code>SVGCircleElement</code> object
     */
    public static SVGCircleElement parseElement(Element element) {
        SVGCircleElement circ        = new SVGCircleElement();
        Attr             centreXAttr = element.getAttributeNodeNS(null, "cx");

        if (centreXAttr != null) {
            circ.setCx(SVGLengthUnit.parse(centreXAttr.getValue()));
        }

        Attr centreYAttr = element.getAttributeNodeNS(null, "cy");

        if (centreYAttr != null) {
            circ.setCy(SVGLengthUnit.parse(centreYAttr.getValue()));
        }

        Attr radiusAttr = element.getAttributeNodeNS(null, "r");

        if (radiusAttr != null) {
            circ.setRadius(SVGLengthUnit.parse(radiusAttr.getValue()));
        }

        circ.parseAttributes(element);

        return circ;
    }
}