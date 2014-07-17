package rocks6205.editor.viewcomponents.panels;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * This panel deals with events such as user drag-select, or
 * resize components.
 *
 * @author Cheow Yeong Chi
 * @since 2.0
 */
public class LSScribblePanel extends JPanel {
    private static final long serialVersionUID = -8306121661640569510L;
    private Rectangle selectionBox;
    private ArrayList<? extends Rectangle2D> selectionRectangles;
    private ArrayList<? extends Rectangle2D> resizeRectangles;

    /**
     * Default constructor.
     */
    public LSScribblePanel() {
    }

    public void setSelectionBox(Rectangle box) {
        selectionBox = box;
    }

    public void setSelectionRectangles(ArrayList<? extends Rectangle2D> selRects) {
        selectionRectangles = selRects;
    }

    public void setResizeRectangles(ArrayList<? extends Rectangle2D> resizeRects) {
        resizeRectangles = resizeRects;
    }

    /**
     * @param g graphic
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        if (selectionBox != null) {
            g2d.setPaint(new Color(.8f, .8f, .8f, .2f));
            g2d.fill(selectionBox);
            g2d.setPaint(new Color(.3f, .3f, .3f, .5f));
            g2d.draw(selectionBox);
        }

        if (selectionRectangles != null) {
            for (Rectangle2D rect : selectionRectangles) {
                g2d.setPaint(new Color(.3f, .3f, .3f, .25f));
                g2d.fill(rect);
                g2d.setPaint(new Color(.3f, .3f, .3f, .5f));
                g2d.draw(rect);
            }
        }

        if (resizeRectangles != null) {
            for (Rectangle2D rect : resizeRectangles) {
                g2d.setPaint(Color.WHITE);
                g2d.fill(rect);
                g2d.setPaint(Color.BLACK);
                g2d.draw(rect);
            }
        }
    }
}