package rocks6205.svg.elements;

//~--- non-JDK imports --------------------------------------------------------

import org.w3c.dom.Element;

import rocks6205.svg.properties.SVGImageCanvas;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 * The <code>SVGGElement</code> class is a container used to group objects
 * corresponds to the <<code>g</code>> element in the SVG document.
 * Transformations applied to the <<code>g</code>> element are performed on all of its child
 * elements.
 *
 * @author Cheow Yeong Chi
 * @since 1.1
 *
 */
public class SVGGElement extends SVGContainerElement {

    /*
     * CONSTRUCTOR
     */

    /**
     * Default constructor
     */
    public SVGGElement() {}

    /**
     * {@inheritDoc}
     */
    public Rectangle2D.Float getBounds() {
        Rectangle2D.Float descendantRect = null,
                          rect           = null;
        float             computedX      = 0;
        float             computedY      = 0;
        float             computedWidth  = 0;
        float             computedHeight = 0;

        for (SVGGenericElement descendants : getDescendants()) {
            descendantRect = descendants.getBounds();

            if (descendantRect != null) {
                if (rect == null) {
                    rect = descendantRect;
                } else {
                    Rectangle2D.union(descendantRect, rect, rect);
                }

                computedX      = rect.x;
                computedY      = rect.y;
                computedWidth  = rect.width;
                computedHeight = rect.height;
            }
        }

        if (rect != null) {
            return new Rectangle2D.Float(computedX, computedY, computedWidth, computedHeight);
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public SVGImageCanvas draw() {
        Rectangle2D.Float bounds = getBounds(), descendantRect;
        SVGImageCanvas    canvas = SVGImageCanvas.getBlankCanvas(bounds);

        if (canvas != null) {
            Graphics2D g = canvas.createGraphics();

            for (SVGGenericElement descendants : getDescendants()) {
                descendantRect = descendants.getBounds();

                if (descendantRect != null) {
                    g.drawImage(descendants.draw(), null,
                                (int) (SVGImageCanvas.getZoomScale() * (descendantRect.x - bounds.x)),
                                (int) (SVGImageCanvas.getZoomScale() * (descendantRect.y - bounds.y)));
                }
            }
        }

        return canvas;
    }

    /*
     * METHODS
     */

    /**
     * Parses the attributes on the  <<code>g</code>> element in the SVG document
     * @param e lement from the document returned by the XMLParser
     * @return <code>SVGGElement</code> object
     */
    public static SVGGElement parseElement(Element e) {
        SVGGElement g_e = new SVGGElement();

        g_e.parseAttributes(e);

        return g_e;
    }
}