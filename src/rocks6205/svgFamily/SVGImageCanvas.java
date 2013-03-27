/**
 * 
 * Class: SVGImageCanvas
 * Description: 
 * 
 * @author: Cheow Yeong Chi
 * @date: 11/03/2013
 * 
 */

package rocks6205.svgFamily;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class SVGImageCanvas extends BufferedImage {

    public static final int DEFAULT_MIN_ZOOM_LEVEL = -20;
    public static final int DEFAULT_MAX_ZOOM_LEVEL = 10;
    public static final double DEFAULT_ZOOM_MULTIPLICATION_FACTOR = 1.0f;

    private static double zoomScale = DEFAULT_ZOOM_MULTIPLICATION_FACTOR;

    public SVGImageCanvas(int width, int height) {
	super(width, height, BufferedImage.TYPE_4BYTE_ABGR_PRE);
    }

    public static SVGImageCanvas getBlankCanvas(Rectangle2D.Float bounds) {
	boolean shouldGetCanvas = (bounds != null)  && (bounds.width > 0 && bounds.height > 0);

	if (shouldGetCanvas) {
	    return new SVGImageCanvas(
		    (int) (SVGImageCanvas.getZoomScale() * Math.ceil(bounds.width)),
		    (int) (SVGImageCanvas.getZoomScale() * Math.ceil(bounds.height)));
	}
	return null;

    }

    public static double getZoomScale() {
	return zoomScale;
    }

    public static void setZoomScale(double zoomScale) {
	SVGImageCanvas.zoomScale = zoomScale;
    }
}
