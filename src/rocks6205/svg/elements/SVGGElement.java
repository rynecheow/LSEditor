/**
 * 
 * Class: SVGGElement
 * Description: 
 * 
 * @author: Cheow Yeong Chi
 * @date: 14/03/2013
 * 
 */

package rocks6205.svg.elements;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import org.w3c.dom.Element;

import rocks6205.svgFamily.SVGImageCanvas;

public class SVGGElement extends SVGContainerElement {

    /*
     * CONSTRUCTOR
     */
    public SVGGElement() {}

    @Override
    public Rectangle2D.Float getBounds() {
	Rectangle2D.Float descendantRect = null, rect = null;
	float computedX = 0;
	float computedY = 0;
	float computedWidth = 0;
	float computedHeight = 0;

	for (SVGGenericElement descendants : getDescendants()) {
	    descendantRect = descendants.getBounds();
	    if (descendantRect != null) {
		if (rect == null) {
		    rect = descendantRect;
		} else {
		    Rectangle2D.union(descendantRect, rect, rect);
		}

		computedX = rect.x;
		computedY = rect.y;
		computedWidth = rect.width;
		computedHeight = rect.height;
	    }
	}

	if (rect != null) {
	    return new Rectangle2D.Float(computedX, computedY, computedWidth,
		    computedHeight);
	} else {
	    return null;
	}
    }

    @Override
    public SVGImageCanvas draw() {
	Rectangle2D.Float bounds = getBounds(), descendantRect;
	SVGImageCanvas canvas = SVGImageCanvas.getBlankCanvas(bounds);

	if (canvas != null) {
	    Graphics2D g = canvas.createGraphics();

	    for (SVGGenericElement descendants : getDescendants()) {
		descendantRect = descendants.getBounds();
		if (descendantRect != null) {
		    g.drawImage(descendants.draw(), null,
			    (int) (SVGImageCanvas.getZoomScale() *(descendantRect.x - bounds.x)),
			    (int) (SVGImageCanvas.getZoomScale() *(descendantRect.y - bounds.y)));
		}
	    }
	}

	return canvas;
    }

    /*
     * METHODS
     */
    public static SVGGElement parseElement(Element e) {
	SVGGElement g_e = new SVGGElement();
	g_e.parseAttributes(e);
	return g_e;
    }
}
