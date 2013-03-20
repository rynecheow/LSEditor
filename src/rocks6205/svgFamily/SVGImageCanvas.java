package rocks6205.svgFamily;

import java.awt.image.BufferedImage;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;

public class SVGImageCanvas extends BufferedImage {
	private static double zoomScale = 1.0f;

	public SVGImageCanvas(int width, int height) {
		super(width, height, BufferedImage.TYPE_4BYTE_ABGR_PRE);
	}

	public static SVGImageCanvas getBlankCanvas(Float bounds) {
		boolean shouldGetCanvas = bounds != null;
		shouldGetCanvas &= (bounds.width > 0 && bounds.height > 0);

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

	public static void setZoomFactor(double zoomScale) {
		SVGImageCanvas.zoomScale = zoomScale;
	}
}
