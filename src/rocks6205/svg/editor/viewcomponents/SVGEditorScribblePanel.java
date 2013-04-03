package rocks6205.svg.editor.viewcomponents;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import java.util.Vector;

import javax.swing.JPanel;

/**
 * This panel deals with events such as user drag-select, or
 * resize components.
 *
 * @author Cheow Yeong Chi
 *
 * @since 2.0
 *
 */
public class SVGEditorScribblePanel extends JPanel {
    private static final long             serialVersionUID = -8306121661640569510L;
    private Rectangle                     selectionBox;
    private Vector<? extends Rectangle2D> selectionRectangles;
    private Vector<? extends Rectangle2D> resizeRectangles;

    /**
     * Default constructor.
     */
    public SVGEditorScribblePanel() {}

    public void setSelectionBox(Rectangle box) {
        selectionBox = box;
    }

    public void setSelectionRectangles(Vector<? extends Rectangle2D> selRects) {
        selectionRectangles = selRects;
    }

    public void setResizeRectangles(Vector<? extends Rectangle2D> resizeRects) {
        resizeRectangles = resizeRects;
    }
    
    /**
     * 
     * @param g 
     */
    protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		if (selectionBox != null) {
			g2.setPaint(new Color(.3f, .3f, .3f, .25f));
			g2.fill(selectionBox);
			g2.setPaint(new Color(.3f, .3f, .3f, .5f));
			g2.draw(selectionBox);
		}

		if (selectionRectangles != null) {
			for (Rectangle2D rect : selectionRectangles) {
				g2.setPaint(new Color(.3f, .3f, .3f, .25f));
				g2.fill(rect);
				g2.setPaint(new Color(.3f, .3f, .3f, .5f));
				g2.draw(rect);
			}
		}

		if (resizeRectangles != null) {
			for (Rectangle2D rect : resizeRectangles) {
				g2.setPaint(Color.WHITE);
				g2.fill(rect);
				g2.setPaint(Color.BLACK);
				g2.draw(rect);
			}
		}
	}
}
