package rocks6205.editor.core;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.model.elements.LSSVGContainer;

/**
 * SVG Viewer model which consist the current SVG parent fragment/object.
 *
 * @author: Cheow Yeong Chi
 *
 * @since 1.0
 *
 */
public class LSModel {

    /**
     * SVG Element to be drawn.
     */
    private LSSVGContainer SVGElement;

    /**
     * Default constructor.
     */
    public LSModel() {}

    /*
     * ACCESSOR
     */

    /**
     * @return SVG Element to be drawn.
     */
    public LSSVGContainer getSVGElement() {
        return SVGElement;
    }

    /*
     * MUTATORS
     */

    /**
     *
     * @param svgElement SVG Element to be drawn.
     */
    public void setSVGElement(LSSVGContainer svgElement) {
        SVGElement = svgElement;
    }
}