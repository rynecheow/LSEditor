package rocks6205.svg.mvc;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.svg.adt.SVGLengthUnitType;
import rocks6205.svg.elements.SVGSVGElement;
import rocks6205.svg.properties.SVGImageCanvas;


//~--- JDK imports ------------------------------------------------------------

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Observable;

/**
 * SVG Viewer model which consist of the image canvas and the current SVG
 * fragment/object.
 *
 * @author: Cheow Yeong Chi
 *
 * @since 1.0
 *
 */
public class SVGEditorModel extends Observable {

    /**
     * SVG Element to be drawn.
     */
    private SVGSVGElement SVGElement;

    /**
     * Default constructor.
     */
    public SVGEditorModel() {}

    /*
     * ACCESSOR
     */

    /**
     * @return SVG Element to be drawn.
     */
    public SVGSVGElement getSVGElement() {
        return SVGElement;
    }

    /*
     * MUTATORS
     */

    /**
     *
     * @param svgElement SVG Element to be drawn.
     */
    public void setSVGElement(SVGSVGElement svgElement) {
        SVGElement = svgElement;
    }


    /*
     * METHOD
     */

    public BufferedImage renderImage(float zoomScale) {
	int width = (int) (SVGElement.getWidth().getValue(SVGLengthUnitType.PX) * zoomScale);
	int height = (int) (SVGElement.getHeight().getValue(SVGLengthUnitType.PX) * zoomScale);

	BufferedImage image = new BufferedImage(width, height,
			BufferedImage.TYPE_4BYTE_ABGR);
	Graphics2D g = image.createGraphics();

	g.scale(zoomScale, zoomScale);
	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);
	SVGElement.draw(g);

	g.dispose();

	return image;
    }
    
    
    
}