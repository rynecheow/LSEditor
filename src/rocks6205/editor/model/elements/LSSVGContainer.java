package rocks6205.editor.model.elements;

//~--- non-JDK imports --------------------------------------------------------

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import rocks6205.editor.core.LSEditor;
import rocks6205.editor.model.adt.LSLength;
import rocks6205.editor.model.adt.LSLengthUnitType;

import java.awt.*;
import java.awt.geom.Rectangle2D;

//~--- JDK imports ------------------------------------------------------------

/**
 * The <code>LSSVGContainer</code> class is a container element
 * corresponds to the <<code>svg</code>> element in the SVG document.<p>
 * It can be used to nest a standalone SVG fragment inside the current document
 * elements.
 *
 * @author Cheow Yeong Chi
 * @since 1.1
 */
public class LSSVGContainer extends LSGenericContainer {

    /**
     * Width of SVG fragment
     */
    private LSLength width = new LSLength(1000);    // /default to 1000x1000

    /**
     * Height of SVG fragment
     */
    private LSLength height = new LSLength(1000);

    /**
     * Construct an instance of <code>LSSVGContainer</code> with <code>width</code> and
     * <code>height</code>
     *
     * @param width
     * @param height
     */
    public LSSVGContainer(LSLength width, LSLength height) {
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
    @Override
    public Rectangle2D.Float getBounds() {
        return new Rectangle2D.Float(0, 0, width.getValue(LSLengthUnitType.PX), height.getValue(LSLengthUnitType.PX));
    }

    /**
     * {@inheritDoc}
     */
    @Override
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
     * @return <code>LSSVGContainer</code> object
     */
    public static LSSVGContainer parseDocument(Document document) {
        LSSVGContainer svg_e = null;
        LSGenericContainer ancestorElement = null, newAncestorElement;
        Node node = document.getDocumentElement();
        Element e;

        while (node != null) {
            newAncestorElement = null;

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                e = (Element) node;

                switch (e.getTagName()) {
                    case "svg":
                        LSLength width = null;
                        LSLength height = null;
                        Attr widthAttr = e.getAttributeNodeNS(null, "width");

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

                        svg_e = new LSSVGContainer(width, height);
                        ancestorElement = svg_e;

                        break;

                    case "g":
                        LSGroupContainer g_e = LSGroupContainer.parseElement(e);

                        if (ancestorElement != null) {
                            ancestorElement.addDescendant(g_e);
                        } else {
                            LSEditor.logger.warning("Null pointer dereferencing while parsing group ancestor element\n");
                        }

                        newAncestorElement = g_e;

                        break;

                    case "rect":
                        if (ancestorElement != null) {
                            ancestorElement.addDescendant(LSShapeRect.parseElement(e));
                        } else {
                            LSEditor.logger.warning(
                                    "Null pointer dereferencing while parsing rectangle ancestor element\n");
                        }

                        break;

                    case "circle":
                        if (ancestorElement != null) {
                            ancestorElement.addDescendant(LSShapeCircle.parseElement(e));
                        } else {
                            LSEditor.logger.warning("Null pointer dereferencing while parsing circle ancestor element\n");
                        }

                        break;

                    case "line":
                        if (ancestorElement != null) {
                            ancestorElement.addDescendant(LSShapeLine.parseElement(e));
                        } else {
                            LSEditor.logger.warning("Null pointer dereferencing while parsing line ancestor element\n");
                        }

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

                if (ancestorElement != null) {
                    if (ancestorElement.getAncestorElement() != null) {
                        ancestorElement = ancestorElement.getAncestorElement();
                    }
                } else {
                    LSEditor.logger.warning("Null pointer dereferencing.\n");
                }
            }
        }

        return svg_e;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return LSSVGContainer.class.getSimpleName();
    }
}