package rocks6205.svg.mvc;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.svg.elements.SVGSVGElement;

/**
 * SVG Viewer model which consist the current SVG parent fragment/object.
 *
 * @author: Cheow Yeong Chi
 *
 * @since 1.0
 *
 */
public class SVGEditorModel {

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
}