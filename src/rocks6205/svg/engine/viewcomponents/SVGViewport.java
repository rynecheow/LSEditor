package rocks6205.svg.engine.viewcomponents;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.svg.engine.SVGView;

import rocks6205.svgFamily.SVGCanvasProperties;
import rocks6205.svgFamily.SVGImageCanvas;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

/**
 * A viewport panel that renders SVG and contain the image canvas that the graphics
 * are to be drawn on.
 *
 * @author Cheow Yeong Chi
 *
 * @since 1.3
 */
public class SVGViewport extends JPanel {
    private static final long serialVersionUID = -7920677728155693552L;

    /**
     * Parent component
     */
    private SVGView parentView;

    /*
     * PROPERTIES
     */

    /**
     * Image canvas
     */
    private SVGImageCanvas canvas;

    /**
     * Constructs an <code>SVGViewport</code> instance with parent
     * component <code>view</code>, subsequently sets the resolution and
     * font size of the canvas.
     *
     * @param view
     */
    public SVGViewport(SVGView view) {
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
    public void setCanvas(SVGImageCanvas canvas) {
        this.canvas = canvas;
    }

    /**
     * <p>Paint components on canvas</p>.
     * {@inheritDoc}
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(canvas, 0, 0, null);
    }

    /**
     * Handles zoom in and out by resizing canvas.<p>
     * {@inheritDoc}
     */
    public Dimension getPreferredSize() {
        if (canvas != null) {
            int w = (int) (SVGImageCanvas.getZoomScale() * canvas.getWidth()),
                h = (int) (SVGImageCanvas.getZoomScale() * canvas.getHeight());

            return new Dimension(w, h);
        }

        return super.getPreferredSize();
    }

    public SVGView getParentView() {
        return parentView;
    }

    public void setParentView(SVGView parentView) {
        if (parentView != null) {
            this.parentView = parentView;
        }
    }
}