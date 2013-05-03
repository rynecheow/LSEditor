package rocks6205.editor.mvc;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.model.elements.LSSVGElement;

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
    private LSSVGElement SVGElement;

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
    public LSSVGElement getSVGElement() {
        return SVGElement;
    }

    /*
     * MUTATORS
     */

    /**
     *
     * @param svgElement SVG Element to be drawn.
     */
    public void setSVGElement(LSSVGElement svgElement) {
        SVGElement = svgElement;
    }
}