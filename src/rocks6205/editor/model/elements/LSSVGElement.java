
/**
 *
 * Class: LSSVGElement
 * Description:
 *
 * @author: Cheow Yeong Chi
 * @date: 14/03/2013
 *
 */
package rocks6205.editor.model.elements;

//~--- non-JDK imports --------------------------------------------------------

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import rocks6205.editor.model.adt.LSLength;
import rocks6205.editor.model.adt.LSLengthUnitType;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 * The <code>LSSVGElement</code> class is a container element
 * corresponds to the <<code>svg</code>> element in the SVG document.<p>
 * It can be used to nest a standalone SVG fragment inside the current document
 * elements.
 *
 * @author Cheow Yeong Chi
 *
 * @since 1.1
 *
 */
public class LSSVGElement extends LSGenericContainer {

    /**
     * Width of SVG fragment
     */
    private LSLength width = new LSLength(1000);    // /default to 1000x1000

    /**
     * Height of SVG fragment
     */
    private LSLength height = new LSLength(1000);

    /**
     * Construct an instance of <code>LSSVGElement</code> with <code>width</code> and
     * <code>height</code>
     *
     * @param width
     * @param height
     */
    public LSSVGElement(LSLength width, LSLength height) {
        setWidth(width);
        setHeight(height);
        setFill(SVG_FILL_DEFAULT);
        setStroke(SVG_STROKE_DEFAULT);
        setStrokeWidth(SVG_STROKE_WIDTH_DEFAULT);
        setStrokeLineCap(SVG_STROKE_LINECAP_DEFAULT);
        setStrokeLineJoin(SVG_STROKE_LINEJOIN_DEFAULT);
    }

    /**
     * @return Width of SVG fragment
     */
    public LSLength getWidth() {
        return width;
    }

    /**
     * @param width Width of SVG fragment
     */
    public final void setWidth(LSLength width) {
        if (width.getValue() < 0) {
            throw new IllegalArgumentException("Width must not be negative.");
        }

        this.width = width;
    }

    /**
     * @return Height of SVG fragment
     */
    public LSLength getHeight() {
        return height;
    }

    /**
     * @param height Height of SVG fragment
     */
    public final void setHeight(LSLength height) {
        if (height.getValue() < 0) {
            throw new IllegalArgumentException("Height must not be negative");
        }

        this.height = height;
    }

    /**
     * {@inheritDoc}
     */
    public Rectangle2D.Float getBounds() {
        return new Rectangle2D.Float(0, 0, width.getValue(LSLengthUnitType.PX), height.getValue(LSLengthUnitType.PX));
    }

    /**
     * {@inheritDoc}
     */
    public void drawShape(Graphics2D g) {
        for (LSGenericElement child : getDescendants()) {
            child.drawShape(g);
        }
    }

    /**
     * Parses the attributes and the nested <node>elements</code> of the SVG document,
     * consequently constructs the vector of elements in a nest.
     *
     * @param document <code>Document</code> from XML parsed and returned by the <code>XMLParser</code>
     * @return <code>LSSVGElement</code> object
     */
    public static LSSVGElement parseDocument(Document document) {
        LSSVGElement       svg_e           = null;
        LSGenericContainer ancestorElement = null, newAncestorElement;
        Node                node            = document.getDocumentElement();
        Element             e;

        while (node != null) {
            newAncestorElement = null;

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                e = (Element) node;

                switch (e.getTagName()) {
                case "svg" :
                    LSLength width     = null;
                    LSLength height    = null;
                    Attr          widthAttr = e.getAttributeNodeNS(null, "width");

                    if (widthAttr != null) {
                        width = LSLength.parse(widthAttr.getValue());
                    }

                    Attr heightAttr = e.getAttributeNodeNS(null, "height");

                    if (heightAttr != null) {
                        height = LSLength.parse(heightAttr.getValue());
                    }

                    if ((width == null) || (height == null)) {
                        break;
                    }

                    svg_e           = new LSSVGElement(width, height);
                    ancestorElement = svg_e;

                    break;

                case "g" :
                    LSGroup g_e = LSGroup.parseElement(e);

                    ancestorElement.addDescendant(g_e);
                    newAncestorElement = g_e;

                    break;

                case "rect" :
                    ancestorElement.addDescendant(LSShapeRect.parseElement(e));

                    break;

                case "circle" :
                    ancestorElement.addDescendant(LSShapeCircle.parseElement(e));

                    break;

                case "line" :
                    ancestorElement.addDescendant(LSShapeLine.parseElement(e));

                    break;
                }
            }

            if (node.hasChildNodes()) {
                node = node.getFirstChild();

                if (newAncestorElement != null) {
                    ancestorElement = newAncestorElement;
                }
            } else if (node.getNextSibling() != null) {
                node = node.getNextSibling();
            } else {
                node = node.getParentNode().getNextSibling();

                if (ancestorElement.getAncestorElement() != null) {
                    ancestorElement = ancestorElement.getAncestorElement();
                }
            }
        }

        return svg_e;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String getElementType() {
        return "SVGSVGElement";
    }

    public void listDescendants() {
        super.listDescendants(this, 0);
    }
}