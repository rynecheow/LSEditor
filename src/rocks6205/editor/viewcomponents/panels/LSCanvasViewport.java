package rocks6205.editor.viewcomponents.panels;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.core.LSView;

import rocks6205.system.properties.LSCanvasProperties;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Color;
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
    private LSView parentView;

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
    public LSCanvasViewport(LSView view) {
        super();
        setParentView(view);
        LSCanvasProperties.setOutputResolution(Toolkit.getDefaultToolkit().getScreenResolution());
        LSCanvasProperties.setFontSize((this.getFont().getSize2D()));
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

    public LSView getParentView() {
        return parentView;
    }

    public final void setParentView(LSView parentView) {
        if (parentView != null) {
            this.parentView = parentView;
        }
    }
}