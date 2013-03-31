package rocks6205.svg.elements;

//~--- non-JDK imports --------------------------------------------------------

import org.w3c.dom.Element;

import rocks6205.svg.adt.SVGLengthUnit;
import rocks6205.svg.adt.SVGPainting;
import rocks6205.svg.properties.SVGImageCanvas;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import java.util.Vector;

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

    public Vector<SVGGenericElement> ungroup() {
        recurseAttributes(this);

        return getDescendants();
    }

    /**
     * {@inheritDoc}
     */
    public Rectangle2D.Float getBounds() {
        Rectangle2D.Float rect           = null;
        Rectangle2D.Float descendantRect = null;

        for (SVGGenericElement descendant : getDescendants()) {
            descendantRect = descendant.getBounds();

            if ((descendantRect.width > 0) && (descendantRect.height > 0)) {
                if (rect == null) {
                    rect = descendantRect;
                } else {
                    Rectangle2D.union(descendantRect, rect, rect);
                }
            }
        }

        return (Rectangle2D.Float) getTransform().createTransformedShape(rect).getBounds2D();
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
    private static void recurseAttributes(SVGGElement group) {
        SVGPainting   fill        = group.getFill();
        SVGPainting   stroke      = group.getStroke();
        SVGLengthUnit strokeWidth = group.getStrokeWidth();
        SVGLengthUnit tx          = group.getTranslateX();
        SVGLengthUnit ty          = group.getTranslateY();
        SVGLengthUnit descendantTx;
        SVGLengthUnit descendantTy;

        for (SVGGenericElement descendant : group.getDescendants()) {
            if (descendant.getFill() == null) {
                descendant.setFill(fill);
            }

            if (descendant.getStroke() == null) {
                descendant.setStroke(stroke);
            }

            if (descendant.getStrokeWidth() == null) {
                descendant.setStrokeWidth(strokeWidth);
            }

            if (tx != null) {
                descendantTx = descendant.getTranslateX();

                if (descendantTx == null) {
                    descendantTx = new SVGLengthUnit(tx.getValue());
                } else {
                    descendantTx = new SVGLengthUnit(descendantTx.getValue() + tx.getValue());
                }

                descendant.setTranslateX(descendantTx);
            }

            if (ty != null) {
                descendantTy = descendant.getTranslateY();

                if (descendantTy == null) {
                    descendantTy = new SVGLengthUnit(ty.getValue());
                } else {
                    descendantTy = new SVGLengthUnit(descendantTy.getValue() + ty.getValue());
                }

                descendant.setTranslateY(descendantTy);
            }
        }
    }

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