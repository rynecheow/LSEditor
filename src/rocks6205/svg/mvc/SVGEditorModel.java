package rocks6205.svg.mvc;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.svg.elements.SVGSVGElement;
import rocks6205.svg.properties.SVGImageCanvas;


//~--- JDK imports ------------------------------------------------------------

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
     * Canvas to be drawn on.
     */
    private SVGImageCanvas canvas;

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

    /**
     * @return Canvas to be drawn on.
     */
    public SVGImageCanvas getCanvas() {
        return canvas;
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

    /**
     * @param canvas Canvas to be drawn on.
     */
    public void setCanvas(SVGImageCanvas canvas) {
        this.canvas = canvas;
    }

    /*
     * METHOD
     */

    /**
     * Renders SVG file by notifying Observer
     */
    public void render() {
        setCanvas(SVGElement.draw());
        setChanged();
        notifyObservers(this.canvas);
    }
}