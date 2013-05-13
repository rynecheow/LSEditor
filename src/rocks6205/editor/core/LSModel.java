package rocks6205.editor.core;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.dto.LSDocumentDataObject;
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
    private LSSVGContainer       SVGElement;
    private LSDocumentDataObject canvasDTO;
    private String               title;

    /**
     * Default constructor.
     */
    public LSModel() {
        canvasDTO = new LSDocumentDataObject();
    }

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
     * @param svgElement SVG Element to be drawn.
     *
     * @see #updateCanvasDTO()
     */
    public final void setSVGElement(LSSVGContainer svgElement) {
        SVGElement = svgElement;
        updateCanvasDTO();
    }

    /**
     * @param data Data object
     *
     * @see #updateCanvasDTO()
     */
    public final void setCanvasDTO(LSDocumentDataObject data) {
        canvasDTO = data;
        updateModelFromDTO();
    }

    /**
     * @param t Title of data object
     */
    public void setTitle(String t) {
        title = t;
    }

    /**
     * @return Title of data object
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return Data object
     */
    public LSDocumentDataObject getCanvasDTO() {
        return canvasDTO;
    }

    /**
     * Updates the data object
     */
    public void updateCanvasDTO() {
        if ((SVGElement != null) && (canvasDTO != null)) {
            canvasDTO.setHeight(SVGElement.getHeight());
            canvasDTO.setWidth(SVGElement.getWidth());
            canvasDTO.setTitle(title);
        }
    }

    private void updateModelFromDTO() {
        if ((SVGElement != null) && (canvasDTO != null)) {
            SVGElement.setHeight(canvasDTO.getHeight());
            SVGElement.setWidth(canvasDTO.getWidth());
            title = canvasDTO.getTitle();
        }
    }
}