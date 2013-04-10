package rocks6205.editor.viewcomponents;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.mvc.SVGEditorView;
import rocks6205.system.properties.SVGCanvasProperties;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * A viewport panel that renders SVG and contain the image canvas that the graphics
 * are to be drawn on.
 *
 * @author Cheow Yeong Chi
 *
 * @since 1.3
 */
public class LSCanvasViewport extends JPanel {
    private static final long serialVersionUID = -7920677728155693552L;

    /**
     * Parent component
     */
    private SVGEditorView parentView;

    /*
     * PROPERTIES
     */

    /**
     * Image canvas
     */
    private BufferedImage canvas;

    /**
     * Constructs an <code>SVGViewport</code> instance with parent
     * component <code>view</code>, subsequently sets the resolution and
     * font size of the canvas.
     *
     * @param view
     */
    public LSCanvasViewport(SVGEditorView view) {
        super();
        setParentView(view);
        SVGCanvasProperties.setOutputResolution(Toolkit.getDefaultToolkit().getScreenResolution());
        SVGCanvasProperties.setFontSize((this.getFont().getSize2D()));

        /*
         * Unfinished implementation
         * addMouseWheelListener(new SVGViewMouseWheelZoomAdaptor());
         */
    }

    /**
     * @param canvas Image canvas
     */
    public void setCanvas(BufferedImage canvas) {
        this.canvas = canvas;
    }

    /**
     * <p>Paint components on canvas</p>.
     * {@inheritDoc}
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (canvas != null) {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            g.drawImage(canvas, 0, 0, null);
        }
    }

    /**
     * Handles zoom in and out by resizing canvas.<p>
     * {@inheritDoc}
     */
//    public Dimension getPreferredSize() {
//        if (canvas != null) {
//            int w = (int) (SVGImageCanvas.getZoomScale() * canvas.getWidth()),
//                h = (int) (SVGImageCanvas.getZoomScale() * canvas.getHeight());
//
//            return new Dimension(w, h);
//        }
//
//        return super.getPreferredSize();
//    }
//
    public SVGEditorView getParentView() {
        return parentView;
    }

    public final void setParentView(SVGEditorView parentView) {
        if (parentView != null) {
            this.parentView = parentView;
        }
    }
}