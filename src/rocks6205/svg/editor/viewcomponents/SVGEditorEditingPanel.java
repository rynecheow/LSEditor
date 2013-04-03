package rocks6205.svg.editor.viewcomponents;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.svg.adt.SVGLengthUnit;
import rocks6205.svg.adt.SVGPainting;
import rocks6205.svg.elements.SVGGenericElement;
import rocks6205.svg.mvc.SVGEditorView;
import rocks6205.svg.mvc.SVGEditorViewController;

//~--- JDK imports ------------------------------------------------------------

import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.OverlayLayout;

/**
 * Editor panel that acts as a central view for user to edit, select, add
 * components and perform actions to the canvas.
 *
 * @author Cheow Yeong Chi
 *
 * @since 2.0
 *
 */
public class SVGEditorEditingPanel extends JPanel {
    private static final long          serialVersionUID = 523971636492120974L;
    private SVGEditorViewController    controller;
    private SVGEditorView              parent;
    private SVGGenericElement          activeElement, newElement;
    private SVGEditorScribblePanel     scribbleArea;
    private SVGEditorCanvasViewport    viewArea;
    private SVGPainting                fill, stroke;
    private SVGLengthUnit              strokeWidth;
    private Rectangle                  selectionRect, activeRect, previousActiveRect;
    private Point2D.Float              startPoint;
    private Vector<Rectangle2D.Double> resizeHandlers;
    private Rectangle2D.Float          activeResizeHandler;
    private CurrentEditingMode         editingMode;

    /**
     *
     */
    private enum CurrentEditingMode {
        MODE_PAN, MODE_SELECT, MODE_MOVE, MODE_RESIZE, DRAW_RECTANGLE, DRAW_CIRCLE, DRAW_LINE
    }

    /**
     * Default constructor.
     */
    public SVGEditorEditingPanel(SVGEditorView view) {
        super();
        parent     = view;
        controller = view.getController();

        LayoutManager overlay = new OverlayLayout(this);

        setLayout(overlay);
        scribbleArea = new SVGEditorScribblePanel();
        scribbleArea.setOpaque(false);
        add(scribblePanel);
        viewArea = new SVGEditorCanvasViewport();
        add(viewArea);
        setAutoscrolls(true);
    }
}