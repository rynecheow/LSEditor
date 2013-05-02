
/**
 *
 * Class: SVGSVGElement
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

import rocks6205.editor.model.adt.SVGLengthUnit;
import rocks6205.editor.model.adt.SVGLengthUnitType;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 * The <code>SVGSVGElement</code> class is a container element
 * corresponds to the <<code>svg</code>> element in the SVG document.<p>
 * It can be used to nest a standalone SVG fragment inside the current document
 * elements.
 *
 * @author Cheow Yeong Chi
 *
 * @since 1.1
 *
 */
public class SVGSVGElement extends SVGContainerElement {

    /**
     * Width of SVG fragment
     */
    private SVGLengthUnit width = new SVGLengthUnit(1000);    // /default to 1000x1000

    /**
     * Height of SVG fragment
     */
    private SVGLengthUnit height = new SVGLengthUnit(1000);

    /**
     * Construct an instance of <code>SVGSVGElement</code> with <code>width</code> and
     * <code>height</code>
     *
     * @param width
     * @param height
     */
    public SVGSVGElement(SVGLengthUnit width, SVGLengthUnit height) {
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
    public SVGLengthUnit getWidth() {
        return width;
    }

    /**
     * @param width Width of SVG fragment
     */
    public final void setWidth(SVGLengthUnit width) {
        if (width.getValue() < 0) {
            throw new IllegalArgumentException("Width must not be negative.");
        }

        this.width = width;
    }

    /**
     * @return Height of SVG fragment
     */
    public SVGLengthUnit getHeight() {
        return height;
    }

    /**
     * @param height Height of SVG fragment
     */
    public final void setHeight(SVGLengthUnit height) {
        if (height.getValue() < 0) {
            throw new IllegalArgumentException("Height must not be negative");
        }

        this.height = height;
    }

    /**
     * {@inheritDoc}
     */
    public Rectangle2D.Float getBounds() {
        return new Rectangle2D.Float(0, 0, width.getValue(SVGLengthUnitType.PX), height.getValue(SVGLengthUnitType.PX));
    }

    /**
     * {@inheritDoc}
     */
    public void draw(Graphics2D g) {
        for (SVGGenericElement child : getDescendants()) {
            child.draw(g);
        }
    }

    /**
     * Parses the attributes and the nested <node>elements</code> of the SVG document,
     * consequently constructs the vector of elements in a nest.
     *
     * @param document <code>Document</code> from XML parsed and returned by the <code>XMLParser</code>
     * @return <code>SVGSVGElement</code> object
     */
    public static SVGSVGElement parseDocument(Document document) {
        SVGSVGElement       svg_e           = null;
        SVGContainerElement ancestorElement = null, newAncestorElement;
        Node                node            = document.getDocumentElement();
        Element             e;

        while (node != null) {
            newAncestorElement = null;

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                e = (Element) node;

                switch (e.getTagName()) {
                case "svg" :
                    SVGLengthUnit width     = null;
                    SVGLengthUnit height    = null;
                    Attr          widthAttr = e.getAttributeNodeNS(null, "width");

                    if (widthAttr != null) {
                        width = SVGLengthUnit.parse(widthAttr.getValue());
                    }

                    Attr heightAttr = e.getAttributeNodeNS(null, "height");

                    if (heightAttr != null) {
                        height = SVGLengthUnit.parse(heightAttr.getValue());
                    }

                    if ((width == null) || (height == null)) {
                        break;
                    }

                    svg_e           = new SVGSVGElement(width, height);
                    ancestorElement = svg_e;

                    break;

                case "g" :
                    SVGGElement g_e = SVGGElement.parseElement(e);

                    ancestorElement.addDescendant(g_e);
                    newAncestorElement = g_e;

                    break;

                case "rect" :
                    ancestorElement.addDescendant(SVGRectElement.parseElement(e));

                    break;

                case "circle" :
                    ancestorElement.addDescendant(SVGCircleElement.parseElement(e));

                    break;

                case "line" :
                    ancestorElement.addDescendant(SVGLineElement.parseElement(e));

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
   
   public void listDescendants(){
      super.listDescendants(this, 0);
   }
}