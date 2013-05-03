package rocks6205.editor.model.elements;

//~--- non-JDK imports --------------------------------------------------------

import org.w3c.dom.Element;

import rocks6205.editor.model.adt.LSLength;
import rocks6205.editor.model.adt.LSPainting;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Rectangle2D;

import java.util.ArrayList;

/**
 * The <code>LSGroupContainer</code> class is a container used to group objects
 * corresponds to the <<code>g</code>> element in the SVG document.
 * Transformations applied to the <<code>g</code>> element are performed on all of its child
 * elements.
 *
 * @author Cheow Yeong Chi
 * @since 1.1
 *
 */
public class LSGroupContainer extends LSGenericContainer {

    /*
     * CONSTRUCTOR
     */

    /**
     * Default constructor
     */
    public LSGroupContainer() {}

    public ArrayList<LSGenericElement> ungroup() {
        recurseAttributes(this);

        return getDescendants();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle2D.Float getBounds() {
        Rectangle2D.Float rect = null;
        Rectangle2D.Float descendantRect;

        for (LSGenericElement descendant : getDescendants()) {
            descendantRect = descendant.getBounds();

            if ((descendantRect.width > 0) && (descendantRect.height > 0)) {
                if (rect == null) {
                    rect = descendantRect;
                } else {
                    Rectangle2D.union(descendantRect, rect, rect);
                }
            }
        }

        Rectangle2D.Double bound = (Rectangle2D.Double) getTransform().createTransformedShape(rect).getBounds2D();

        return new Rectangle2D.Float((float) bound.x, (float) bound.y, (float) bound.width, (float) bound.height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawShape(Graphics2D g) {
        AffineTransform affTrans = getTransform();

        g.transform(affTrans);

        for (LSGenericElement descendant : getDescendants()) {
            descendant.drawShape(g);
        }

        try {
            affTrans.invert();
        } catch (NoninvertibleTransformException e) {
            System.err.println(e.getMessage());
        }

        g.transform(affTrans);
    }

    /*
     * METHODS
     */
    private static void recurseAttributes(LSGroupContainer group) {
        LSPainting   fill        = group.getFill();
        LSPainting   stroke      = group.getStroke();
        LSLength strokeWidth = group.getStrokeWidth();
        LSLength tx          = group.getTranslateX();
        LSLength ty          = group.getTranslateY();
        LSLength descendantTx;
        LSLength descendantTy;

        for (LSGenericElement descendant : group.getDescendants()) {
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
                    descendantTx = new LSLength(tx.getValue());
                } else {
                    descendantTx = new LSLength(descendantTx.getValue() + tx.getValue());
                }

                descendant.setTranslateX(descendantTx);
            }

            if (ty != null) {
                descendantTy = descendant.getTranslateY();

                if (descendantTy == null) {
                    descendantTy = new LSLength(ty.getValue());
                } else {
                    descendantTy = new LSLength(descendantTy.getValue() + ty.getValue());
                }

                descendant.setTranslateY(descendantTy);
            }
        }
    }

    /**
     * Parses the attributes on the  <<code>g</code>> element in the SVG document
     * @param e element from the document returned by the XMLParser
     * @return <code>LSGroupContainer</code> object
     */
    public static LSGroupContainer parseElement(Element e) {
        LSGroupContainer g_e = new LSGroupContainer();

        g_e.parseAttributes(e);

        return g_e;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String getElementType() {
        return "SVGGElement";
    }
}