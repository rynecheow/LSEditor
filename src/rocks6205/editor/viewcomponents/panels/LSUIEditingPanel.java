package rocks6205.editor.viewcomponents.panels;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.model.adt.LSLength;
import rocks6205.editor.model.adt.LSLengthUnitType;
import rocks6205.editor.model.adt.LSPainting;
import rocks6205.editor.model.elements.LSShapeCircle;
import rocks6205.editor.model.elements.LSGenericElement;
import rocks6205.editor.model.elements.LSShapeLine;
import rocks6205.editor.model.elements.LSShapeRect;

import rocks6205.editor.core.LSView;
import rocks6205.editor.core.LSViewController;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.OverlayLayout;

/**
 * Editor panel that acts as a central view for user to edit, select, add
 * components and perform actions to the canvas.
 *
 * @author Toh Huey Jing
 *
 * @since 2.0
 *
 */
public final class LSUIEditingPanel extends JPanel {
    private LSViewController      controller;
    private LSView                parent;
    private LSGenericElement            activeElement, newElement;
    private LSScribblePanel              scribbleArea;
    private LSCanvasViewport             viewArea;
    private LSPainting                  fill, stroke;
    private LSLength                strokeWidth;
    private Rectangle                    selectionRect, activeRect, previousActiveRect;
    private Point2D.Float                startPoint;
    private ArrayList<Rectangle2D.Float> resizeHandlers;
    private Rectangle2D.Float            activeResizeHandler;
    private EditModeScheme               editingMode;
    private SVGEditorDrawMouseAdaptor    drawListener;
    private boolean                      isDragged;

    /**
     * Default constructor.
     */
    public LSUIEditingPanel(LSView view) {
        super();
        parent     = view;
        controller = view.getController();

        LayoutManager overlay = new OverlayLayout(this);

        setLayout(overlay);
        scribbleArea = new LSScribblePanel();
        scribbleArea.setOpaque(false);
        add(scribbleArea);
        viewArea = new LSCanvasViewport(view);
        add(viewArea);
        setAutoscrolls(true);
        drawListener = new SVGEditorDrawMouseAdaptor();
        scribbleArea.addMouseListener(drawListener);
        scribbleArea.addMouseMotionListener(drawListener);
    }

    /**
     *
     */
    public enum EditModeScheme {
        MODE_PAN("pan"), MODE_SELECT("select"), MODE_MOVE("move"), MODE_RESIZE("resize"), DRAW_RECTANGLE("drawRect"),
        DRAW_CIRCLE("drawCircle"), DRAW_LINE("drawLine");

        private String string;

        /*
         * CONSTRUCTOR
         */

        /**
         *
         * @param string
         */
        EditModeScheme(String string) {
            this.string = string;
        }
    }

    public void setFill(LSPainting fill) {
        if (activeElement != null) {
            controller.setFillForElement(fill, activeElement);
        }

        this.fill = fill;
    }

    public void setStroke(LSPainting stroke) {
        if (activeElement != null) {
            controller.setStrokeForElement(stroke, activeElement);
        }

        this.stroke = stroke;
    }

    public void setStrokeWidth(LSLength strokeWidth) {
        if (activeElement != null) {
            controller.setStrokeWidthForElement(strokeWidth, activeElement);
        }

        this.strokeWidth = strokeWidth;
    }

    public void switchModeTo(EditModeScheme mode) {
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
        System.out.printf("Current mode: %s \n", mode.name());
    }

    public String getEditMode() {
        return editingMode.name();
    }

    public void setSelections(ArrayList<LSGenericElement> selections) {
        ArrayList<Rectangle2D.Float> selectionRects = new ArrayList<>(selections.size());
        Rectangle2D.Float            bounds;
        float                        zoomScale = parent.getZoomScale();

        for (LSGenericElement e : selections) {
            bounds        = e.getBounds();
            bounds.x      *= zoomScale;
            bounds.y      *= zoomScale;
            bounds.width  *= zoomScale;
            bounds.height *= zoomScale;
            selectionRects.add(bounds);
        }

        scribbleArea.setSelectionRectangles(selectionRects);

        if (selections.size() == 1) {
            LSGenericElement e      = selections.get(0);
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

            if (e instanceof LSShapeRect) {
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
            } else if (e instanceof LSShapeCircle) {
                resizeHandlers = new ArrayList<>(1);
                resizeHandlers.add(0, new Rectangle2D.Float(bounds.x + bounds.width - 5, bounds.y + bounds.height - 5,
                        10, 10));
            } else if (e instanceof LSShapeLine) {
                LSShapeLine line   = (LSShapeLine) e;
                Point2D        point1 = new Point2D.Float(line.getX1().getValue(LSLengthUnitType.PX),
                                            line.getY1().getValue(LSLengthUnitType.PX));
                Point2D point2 = new Point2D.Float(line.getX2().getValue(LSLengthUnitType.PX),
                                                   line.getY2().getValue(LSLengthUnitType.PX));

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
        if (xPos < 0) {
            xPos = 0;
        }

        if (xPos > componentWidth) {
            xPos = componentWidth - 1;
        }

        if (yPos < 0) {
            yPos = 0;
        }

        if (yPos > componentHeight) {
            yPos = componentHeight - 1;
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

    public LSView getParentView() {
        return parent;
    }
    /**
     *
     * Handles event for drawing 3 different shapes on canvas
     * <p>
     *
     * @author: Sugar CheeSheen Chan
     *
     * @since 2.1
     *
     */
    public class SVGEditorDrawMouseAdaptor extends MouseAdapter {

        /**
         * {@inheritDoc}<p>
         *
         */
        @Override
        public void mousePressed(MouseEvent event) {
            scribbleArea.setEnabled(true);

            //
            Point         cursorPoint = event.getPoint();
            Point2D.Float scaledPoint = new Point2D.Float(cursorPoint.x / parent.getZoomScale(),
                                            cursorPoint.y / parent.getZoomScale());

            startPoint = scaledPoint;

            if (editingMode == EditModeScheme.MODE_SELECT) {
                activeResizeHandler = null;

                if (resizeHandlers != null) {
                    for (Iterator<Rectangle2D.Float> it = resizeHandlers.iterator(); it.hasNext(); ) {
                        Rectangle2D.Float resizeHandler = it.next();

                        if (resizeHandler.contains(cursorPoint)) {
                            activeResizeHandler = resizeHandler;

                            switch (resizeHandlers.indexOf(resizeHandler)) {
                            case 0 :
                                if (activeElement instanceof LSShapeRect) {
                                    setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
                                } else if (activeElement instanceof LSShapeCircle) {
                                    setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
                                } else {
                                    setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                                }

                                break;

                            case 1 :
                                if (activeElement instanceof LSShapeRect) {
                                    setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
                                } else {
                                    setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                                }

                                break;

                            case 2 :
                                setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));

                                break;

                            case 3 :
                                setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));

                                break;

                            case 4 :
                                setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));

                                break;

                            case 5 :
                                setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));

                                break;

                            case 6 :
                                setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));

                                break;

                            case 7 :
                                setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));

                                break;

                            default :
                                throw new AssertionError("Non-existant resize handler");
                            }

                            break;
                        }
                    }
                }

                if (activeResizeHandler != null) {
                    editingMode = EditModeScheme.MODE_RESIZE;
                } else {
                    if (controller.isPointSelected(scaledPoint)) {
                        editingMode = EditModeScheme.MODE_MOVE;
                        setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                    } else if (!event.isControlDown()) {
                        controller.clearSelection();
                    }
                }
            } else {
                LSLength x = new LSLength(LSLengthUnitType.PX, scaledPoint.x);
                LSLength y = new LSLength(LSLengthUnitType.PX, scaledPoint.y);

                switch (editingMode) {
                case DRAW_RECTANGLE :
                    LSShapeRect svgRect = new LSShapeRect();

                    svgRect.setX(x);
                    svgRect.setY(y);
                    newElement = svgRect;

                    break;

                case DRAW_CIRCLE :
                    LSShapeCircle svgCircle = new LSShapeCircle();

                    svgCircle.setCx(x);
                    svgCircle.setCy(y);
                    newElement = svgCircle;

                    break;

                case DRAW_LINE :
                    LSShapeLine svgLine = new LSShapeLine();

                    svgLine.setX1(x);
                    svgLine.setY1(y);
                    svgLine.setX2(x);
                    svgLine.setY2(y);
                    newElement = svgLine;

                    break;

                case MODE_PAN :
                    scribbleArea.setEnabled(false);

                    break;

                case MODE_SELECT :
                    break;

                case MODE_MOVE :
                    break;

                case MODE_RESIZE :
                    break;

                default :
                    throw new AssertionError("Invalid edit mode");
                }

                if (editingMode != EditModeScheme.MODE_PAN) {
                    newElement.setFill(fill);
                    newElement.setStroke(stroke);
                    newElement.setStrokeWidth(strokeWidth);
                    controller.addElement(newElement);
                }
            }

            updateSelectionRect(cursorPoint);
            requestFocusInWindow();
        }

        /**
         * {@inheritDoc}<p>
         *
         */
        @Override
        public void mouseReleased(MouseEvent event) {
            float              zoom      = parent.getZoomScale();
            Rectangle2D.Double realRect  = null;
            Point2D.Double     realPoint = null;

            if (editingMode == EditModeScheme.MODE_SELECT) {
                if (isDragged) {
                    updateSelectionRect(event.getPoint());
                    realRect = new Rectangle2D.Double(activeRect.x /= zoom, activeRect.y /= zoom,
                                                      activeRect.width /= zoom, activeRect.height /= zoom);
                } else {
                    realPoint = new Point2D.Double(event.getX() / zoom, event.getY() / zoom);
                }
            }

            scribbleArea.setSelectionBox(null);
            activeRect = null;

            if (editingMode == EditModeScheme.MODE_SELECT) {
                if (isDragged) {
                    controller.addToSelection(realRect);
                } else {
                    controller.addToSelection(realPoint);
                }
            } else if ((editingMode == EditModeScheme.MODE_MOVE) || (editingMode == EditModeScheme.MODE_RESIZE)) {
                editingMode = EditModeScheme.MODE_SELECT;
                setCursor(Cursor.getDefaultCursor());
            }

            isDragged          = false;
            selectionRect      = null;
            previousActiveRect = null;
        }

        /**
     * {@inheritDoc}<p>
     *
     */
        @Override
        public void mouseDragged(MouseEvent event) {
            Point         cursorPoint = event.getPoint();
            float         zoom        = parent.getZoomScale();
            Point2D.Float endPoint    = new Point2D.Float(cursorPoint.x / zoom, cursorPoint.y / zoom);

            isDragged = true;

            if (editingMode == EditModeScheme.MODE_SELECT) {
                updateSelectionRect(cursorPoint);
                scribbleArea.setSelectionBox(activeRect);
                scribbleArea.repaint();
            } else {
                float dx = endPoint.x - startPoint.x;
                float dy = endPoint.y - startPoint.y;

                switch (editingMode) {
                case MODE_MOVE :
                    controller.moveSelectedElement(dx, dy);
                    startPoint = endPoint;

                    break;

                case MODE_RESIZE :
                    if (activeElement instanceof LSShapeRect) {
                        LSShapeRect rect = (LSShapeRect) activeElement;

                        switch (resizeHandlers.indexOf(activeResizeHandler)) {
                        case 0 :
                            controller.resizeRect(rect, -dx, -dy);
                            controller.moveSelectedElement(dx, dy);

                            break;

                        case 1 :
                            controller.resizeRect(rect, 0, -dy);
                            controller.moveSelectedElement(0, dy);

                            break;

                        case 2 :
                            controller.resizeRect(rect, dx, -dy);
                            controller.moveSelectedElement(0, dy);

                            break;

                        case 3 :
                            controller.resizeRect(rect, -dx, 0);
                            controller.moveSelectedElement(dx, 0);

                            break;

                        case 4 :
                            controller.resizeRect(rect, dx, 0);

                            break;

                        case 5 :
                            controller.resizeRect(rect, -dx, dy);
                            controller.moveSelectedElement(dx, 0);

                            break;

                        case 6 :
                            controller.resizeRect(rect, 0, dy);

                            break;

                        case 7 :
                            controller.resizeRect(rect, dx, dy);

                            break;

                        default :
                            throw new AssertionError("Non-existant resize handler");
                        }
                    } else if (activeElement instanceof LSShapeCircle) {
                        LSShapeCircle circle      = (LSShapeCircle) activeElement;
                        Point2D.Float    centerPoint = new Point2D.Float(circle.getCx().getValue(LSLengthUnitType.PX),
                                                           circle.getCy().getValue(LSLengthUnitType.PX));
                        float dr = (float) (endPoint.distance(centerPoint) - startPoint.distance(centerPoint));

                        switch (resizeHandlers.indexOf(activeResizeHandler)) {
                        case 0 :
                            controller.resizeCircle(circle, dr);

                            break;

                        default :
                            throw new AssertionError("Non-existant resize handler");
                        }
                    } else if (activeElement instanceof LSShapeLine) {
                        LSShapeLine line = (LSShapeLine) activeElement;

                        switch (resizeHandlers.indexOf(activeResizeHandler)) {
                        case 0 :
                            controller.resizeLine(line, 1, dx, dy);

                            break;

                        case 1 :
                            controller.resizeLine(line, 2, dx, dy);

                            break;

                        default :
                            throw new AssertionError("Non-existant resize handler");
                        }
                    }

                    startPoint = endPoint;

                    break;

                case DRAW_RECTANGLE :
                    LSShapeRect rect = (LSShapeRect) newElement;

                    updateSelectionRect(cursorPoint);
                    rect.setX(new LSLength(LSLengthUnitType.PX, activeRect.x / zoom));
                    rect.setY(new LSLength(LSLengthUnitType.PX, activeRect.y / zoom));
                    rect.setWidth(new LSLength(LSLengthUnitType.PX, activeRect.width / zoom));
                    rect.setHeight(new LSLength(LSLengthUnitType.PX, activeRect.height / zoom));

                    break;

                case DRAW_CIRCLE :
                    LSShapeCircle circle = (LSShapeCircle) newElement;

                    circle.setRadius(new LSLength(LSLengthUnitType.PX, (float) endPoint.distance(startPoint)));

                    break;

                case DRAW_LINE :
                    LSShapeLine line = (LSShapeLine) newElement;

                    line.setX2(new LSLength(LSLengthUnitType.PX, endPoint.x));
                    line.setY2(new LSLength(LSLengthUnitType.PX, endPoint.y));

                    break;

                case MODE_PAN :
                    break;

                case MODE_SELECT :
                    break;

                default :
                    throw new AssertionError("Invalid edit mode");
                }

                controller.updateViews();
            }

            scrollRectToVisible(new Rectangle(cursorPoint, new Dimension(1, 1)));
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
