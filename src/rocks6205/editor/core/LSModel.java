package rocks6205.editor.core;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.dto.LSCanvasDataObject;
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
    private LSCanvasDataObject canvasDTO;
    private String title;
    
    /**
     * Default constructor.
     */
    public LSModel() {
       canvasDTO = new LSCanvasDataObject();
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
     */
    public void setSVGElement(LSSVGContainer svgElement) {
        SVGElement = svgElement;
    }
    
    public void setCanvasDTO(LSCanvasDataObject data){
       canvasDTO = data;
    }
    
    public void setTitle(String t){
       title = t;
    }
    
    public String getTitle(){
       return title;
    }
    
    public LSCanvasDataObject getCanvasDTO(){
       return canvasDTO;
    }
    
    public void updateCanvasDTO(){
       if(SVGElement!=null){
            canvasDTO.setHeight(SVGElement.getHeight());
            canvasDTO.setWidth(SVGElement.getWidth());
            canvasDTO.setTitle(title);
       }
    }
}