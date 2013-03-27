/**
 * 
 * Class: SVGSVGElement
 * Description: 
 * 
 * @author: Cheow Yeong Chi
 * @date: 14/03/2013
 * 
 */

package rocks6205.svg.elements;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import rocks6205.svg.adt.SVGLengthUnit;
import rocks6205.svgFamily.SVGImageCanvas;


public class SVGSVGElement extends SVGContainerElement {
    private SVGLengthUnit width = new SVGLengthUnit(1000); ///default to 1000x1000
    private SVGLengthUnit height = new SVGLengthUnit(1000);

    public SVGSVGElement(SVGLengthUnit width, SVGLengthUnit height) {
	setWidth(width);
	setHeight(height);
	setFill(SVG_FILL_DEFAULT);
	setStroke(SVG_STROKE_DEFAULT);
	setStrokeWidth(SVG_STROKE_WIDTH_DEFAULT);
	setStrokeLineCap(SVG_STROKE_LINECAP_DEFAULT);
	setStrokeLineJoin(SVG_STROKE_LINEJOIN_DEFAULT);
    }

    public SVGLengthUnit getWidth() {
	return width;
    }

    public final void setWidth(SVGLengthUnit width) {
	if (width != null)		this.width = width;
    }

    public SVGLengthUnit getHeight() {
	return height;
    }

    public final void setHeight(SVGLengthUnit height) {
	if (height != null) 	this.height = height;
    }

    @Override
    public Rectangle2D.Float getBounds() {
	return new Rectangle2D.Float(0, 0, width.getValue(), height.getValue());
    }

    @Override
    public SVGImageCanvas draw() {
	Rectangle2D.Float childRect;
	SVGImageCanvas svgCanvas = SVGImageCanvas.getBlankCanvas(getBounds());

	if (svgCanvas != null) {
	    Graphics2D g = svgCanvas.createGraphics();

	    for (SVGGenericElement child : getDescendants()) {
		childRect = child.getBounds();
		if (childRect != null) {
		    g.drawImage(child.draw(), null, (int) (SVGImageCanvas.getZoomScale() *childRect.x),
			    (int) (SVGImageCanvas.getZoomScale() *childRect.y));
		}
	    }
	}

	return svgCanvas;
    }

    public static SVGSVGElement parseDocument(Document document) {
	SVGSVGElement svg_e = null;
	SVGContainerElement ancestorElement = null, newAncestorElement;
	Node node = document.getDocumentElement();
	Element e;

	while (node != null) {
	    newAncestorElement = null;

	    if (node.getNodeType() == Node.ELEMENT_NODE) {
		e = (Element) node;
		switch (e.getTagName()) {
		case "svg":
		    SVGLengthUnit width = SVGLengthUnit.parse(e.getAttributeNS(
			    null, "width"));
		    SVGLengthUnit height = SVGLengthUnit.parse(e.getAttributeNS(
			    null, "height"));
		    svg_e = new SVGSVGElement(width, height);
		    ancestorElement = svg_e;
		    break;
		case "g":
		    SVGGElement g_e = SVGGElement.parseElement(e);
		    ancestorElement.addDescendant(g_e);
		    newAncestorElement = g_e;
		    break;
		case "rect":
		    ancestorElement.addDescendant(SVGRectElement.parseElement(e));
		    break;
		case "circle":
		    ancestorElement.addDescendant(SVGCircleElement.parseElement(e));
		    break;
		case "line":
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
}