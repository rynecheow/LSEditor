package rocks6205.svgFamily;

//~--- JDK imports ------------------------------------------------------------

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 *
 * The <code>SVGImageCanvas</code> is a subtype of <code>BufferedImage</code>
 * where zooming is allowed and preset with <code>width</code>, <code>height</code>
 * and color profile.
 *
 * @author Cheow Yeong Chi
 * @since 1.3
 *
 */
public class SVGImageCanvas extends BufferedImage {

    /**
     * Default minimum zoom scale
     */
    public static final int DEFAULT_MIN_ZOOM_LEVEL = -20;

    /**
     * Default maximum zoom scale
     */
    public static final int DEFAULT_MAX_ZOOM_LEVEL = 10;

    /**
     * Default zoom multiplication factor
     */
    public static final double DEFAULT_ZOOM_MULTIPLICATION_FACTOR = 1.0f;

    /**
     * Zoom scale of current instance
     */
    private static double zoomScale = DEFAULT_ZOOM_MULTIPLICATION_FACTOR;

    /*
     * CONSTRUCTOR
     */

    /**
     * Constructs an <code>SVGImageCanvas</code> object with defined
     * <code>height</code> and <code>width</code>.
     * @param width
     * @param height
     */
    public SVGImageCanvas(int width, int height) {
        super(width, height, BufferedImage.TYPE_4BYTE_ABGR_PRE);
    }

    /**
     * Get a blank canvas with predefined <code>height</code> and <code>width</code>, and
     * defined rectangular <code>bounds</code>.
     *
     * @param bounds Bounds of current component.
     * @return Blank canvas
     */
    public static SVGImageCanvas getBlankCanvas(Rectangle2D.Float bounds) {
        boolean shouldGetCanvas = (bounds != null) && ((bounds.width > 0) && (bounds.height > 0));

        if (shouldGetCanvas) {
            return new SVGImageCanvas((int) (SVGImageCanvas.getZoomScale() * Math.ceil(bounds.width)),
                                      (int) (SVGImageCanvas.getZoomScale() * Math.ceil(bounds.height)));
        }

        return null;
    }

    /**
     * @return Zoom scale of current instance
     */
    public static double getZoomScale() {
        return zoomScale;
    }

    /**
     * @param zoomScale Zoom scale of current instance
     */
    public static void setZoomScale(double zoomScale) {
        SVGImageCanvas.zoomScale = zoomScale;
    }
}