package rocks6205.svg.editor.viewcomponents;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.svg.adt.SVGLengthUnit;
import rocks6205.svg.adt.SVGLengthUnitType;
import rocks6205.svg.adt.SVGPainting;
import rocks6205.svg.elements.SVGCircleElement;
import rocks6205.svg.elements.SVGGenericElement;
import rocks6205.svg.elements.SVGLineElement;
import rocks6205.svg.elements.SVGRectElement;
import rocks6205.svg.mvc.SVGEditorView;
import rocks6205.svg.mvc.SVGEditorViewController;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import java.util.ArrayList;

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
    private static final long            serialVersionUID = 523971636492120974L;
    private SVGEditorViewController      controller;
    private SVGEditorView                parent;
    private SVGGenericElement            activeElement, newElement;
    private SVGEditorScribblePanel       scribbleArea;
    private SVGEditorCanvasViewport      viewArea;
    private SVGPainting                  fill, stroke;
    private SVGLengthUnit                strokeWidth;
    private Rectangle                    selectionRect, activeRect, previousActiveRect;
    private Point2D.Float                startPoint;
    private ArrayList<Rectangle2D.Float> resizeHandlers;
    private Rectangle2D.Float            activeResizeHandler;
    private EditModeScheme               editingMode;

    /**
     *
     */
    private enum EditModeScheme {
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
        add(scribbleArea);
        viewArea = new SVGEditorCanvasViewport(view);
        add(viewArea);
        setAutoscrolls(true);
    }

    public void setFill(SVGPainting fill) {
        if (activeElement != null) {
            controller.setElementFill(activeElement, fill);
        }

        this.fill = fill;
    }

    public void setStroke(SVGPainting stroke) {
        if (activeElement != null) {
            controller.setElementStroke(activeElement, stroke);
        }

        this.stroke = stroke;
    }

    public void setStrokeWidth(SVGLengthUnit strokeWidth) {
        if (activeElement != null) {
            controller.setElementStrokeWidth(activeElement, strokeWidth);
        }

        this.strokeWidth = strokeWidth;
    }

    void switchModeTo(EditModeScheme mode) {
        if (mode == null) {
            throw new IllegalArgumentException("Edit mode not nullable");
        }

        this.editingMode = mode;

        if (mode == EditModeScheme.MODE_SELECT) {
            if (newElement != null) {
                controller.addToSelection(newElement);
            }

            setCursor(Cursor.getDefaultCursor());
        } else {
            setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        }

        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    }

    public void setSelections(ArrayList<SVGGenericElement> selections) {
        ArrayList<Rectangle2D.Float> selectionRects = new ArrayList<>(selections.size());
        Rectangle2D.Float            bounds;
        float                        zoomScale = parent.getZoomScale();

        for (SVGGenericElement e : selections) {
            bounds        = e.getBounds();
            bounds.x      *= zoomScale;
            bounds.y      *= zoomScale;
            bounds.width  *= zoomScale;
            bounds.height *= zoomScale;
            selectionRects.add(bounds);
        }

        scribbleArea.setSelectionRectangles(selectionRects);

        if (selections.size() == 1) {
            SVGGenericElement e      = selections.get(0);
            int               active = -1;

            activeElement = e;
            bounds        = e.getBounds();
            bounds.x      *= zoomScale;
            bounds.y      *= zoomScale;
            bounds.width  *= zoomScale;
            bounds.height *= zoomScale;

            if ((resizeHandlers != null) && (activeResizeHandler != null)) {
                active = resizeHandlers.indexOf(activeResizeHandler);
            }

            if (e instanceof SVGRectElement) {
                resizeHandlers = new ArrayList<>(4);
                resizeHandlers.add(0, new Rectangle2D.Float(bounds.x - 5, bounds.y - 5, 10, 10));
                resizeHandlers.add(1, new Rectangle2D.Float(bounds.x + bounds.width / 2 - 5, bounds.y - 5, 10, 10));
                resizeHandlers.add(2, new Rectangle2D.Float(bounds.x + bounds.width - 5, bounds.y - 5, 10, 10));
                resizeHandlers.add(3, new Rectangle2D.Float(bounds.x - 5, bounds.y + bounds.height / 2 - 5, 10, 10));
                resizeHandlers.add(4, new Rectangle2D.Float(bounds.x + bounds.width - 5,
                        bounds.y + bounds.height / 2 - 5, 10, 10));
                resizeHandlers.add(5, new Rectangle2D.Float(bounds.x - 5, bounds.y + bounds.height - 5, 10, 10));
                resizeHandlers.add(6, new Rectangle2D.Float(bounds.x + bounds.width / 2 - 5,
                        bounds.y + bounds.height - 5, 10, 10));
                resizeHandlers.add(7, new Rectangle2D.Float(bounds.x + bounds.width - 5, bounds.y + bounds.height - 5,
                        10, 10));
            } else if (e instanceof SVGCircleElement) {
                resizeHandlers = new ArrayList<>(1);
                resizeHandlers.add(0, new Rectangle2D.Float(bounds.x + bounds.width - 5, bounds.y + bounds.height - 5,
                        10, 10));
            } else if (e instanceof SVGLineElement) {
                SVGLineElement line   = (SVGLineElement) e;
                Point2D        point1 = new Point2D.Float(line.getX1().getValue(SVGLengthUnitType.PX),
                                            line.getY1().getValue(SVGLengthUnitType.PX));
                Point2D point2 = new Point2D.Float(line.getX2().getValue(SVGLengthUnitType.PX),
                                                   line.getY2().getValue(SVGLengthUnitType.PX));

                e.getTransform().transform(point1, point1);
                e.getTransform().transform(point2, point2);
                resizeHandlers = new ArrayList<>(2);
                resizeHandlers.add(0, new Rectangle2D.Float((float) point1.getX() - 5, (float) point1.getY() - 5, 10,
                        10));
                resizeHandlers.add(1, new Rectangle2D.Float((float) point2.getX() - 5, (float) point2.getY() - 5, 10,
                        10));
            }

            if (active != -1) {
                activeResizeHandler = resizeHandlers.get(active);
            }
        } else {
            resizeHandlers = null;
            activeElement  = null;
        }

        scribbleArea.setResizeRectangles(resizeHandlers);
    }

    public void paintCanvas(BufferedImage canvas) {
        viewArea.setPreferredSize(new Dimension(canvas.getWidth(), canvas.getHeight()));
        viewArea.setCanvas(canvas);
        viewArea.repaint();
        setPreferredSize(viewArea.getPreferredSize());
        revalidate();
    }

    public void drawOverlay() {
        scribbleArea.repaint();
    }

    private void updateSelectionRect(Point point) {
        int xPos            = point.x;
        int yPos            = point.y;
        int width           = 0;
        int height          = 0;
        int componentWidth  = getWidth();
        int componentHeight = getHeight();

        // Clamp coordinates to within the component
        if (xPos >= 0) {
            if (xPos > componentWidth) {
                xPos = componentWidth - 1;
            } else {
                xPos = 0;
            }
        }

        if (yPos >= 0) {
            if (yPos > componentHeight) {
                yPos = componentHeight - 1;
            } else {
                yPos = 0;
            }
        }

        if (selectionRect != null) {
            width  = xPos - selectionRect.x;
            height = yPos - selectionRect.y;
            xPos   = selectionRect.x;
            yPos   = selectionRect.y;
            selectionRect.setBounds(xPos, yPos, width, height);

            // Invert rectangles with negative width and height
            if (width < 0) {
                width = -width;
                xPos  -= width;
            }

            if (height < 0) {
                height = -height;
                yPos   -= height;
            }
        } else {
            selectionRect = new Rectangle(xPos, yPos, width, height);
        }

        if (activeRect != null) {
            if (previousActiveRect == null) {
                previousActiveRect = new Rectangle(activeRect);
            } else {
                previousActiveRect.setBounds(activeRect);
            }
        } else {
            activeRect = new Rectangle();
        }

        activeRect.setBounds(xPos, yPos, width, height);
    }
    
    
}