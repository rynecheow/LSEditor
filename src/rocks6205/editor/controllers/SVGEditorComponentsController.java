package rocks6205.editor.controllers;

import rocks6205.editor.model.adt.SVGLengthUnit;
import rocks6205.editor.model.adt.SVGPainting;
import rocks6205.editor.model.elements.SVGCircleElement;
import rocks6205.editor.model.elements.SVGGenericElement;
import rocks6205.editor.model.elements.SVGLineElement;
import rocks6205.editor.model.elements.SVGRectElement;

public interface SVGEditorComponentsController {

    /**
     * Add element <code>e</code> as a descendant of the current model.
     * @param e Element to be added
     */
    public void addElement(SVGGenericElement e);
    
    /**
     * Set element <code>e</code>'s fill as <code>fill</code>.
     * @param e Target element
     * @param fill <code>SVGPainting</code> fill object
     */
    public void setFillForElement(SVGPainting fill, SVGGenericElement e);

    /**
     * Set element <code>e</code>'s stroke as <code>stroke</code>.
     * @param e Target element
     * @param stroke <code>SVGPainting</code> stroke object
     */
    public void setStrokeForElement(SVGPainting stroke, SVGGenericElement e);

    /**
     * Set element <code>e</code>'s stroke width as <code>strokeWidth</code>.
     * @param e Target element
     * @param strokeWidth <code>SVGLengthUnit</code> object
     */
    public void setStrokeWidthForElement(SVGLengthUnit strokeWidth, SVGGenericElement e);

    /**
     * Resizes rectangle element with <code>changeWidth</code> and <code>changeHeight</code>.
     * 
     * @param rect Target rectangle element
     * @param changeWidth Changed width
     * @param changeHeight Changed height
     */
    public void resizeRect(SVGRectElement rect, float changeWidth, float changeHeight);

    /**
     * Resizes circle element with <code>changedRadius</code>.
     * @param circle Target circle element
     * @param changedRadius Changed radius
     */
    public void resizeCircle(SVGCircleElement circle, float changedRadius);

    /**
     * Resizes line element with <code>changeX</code> and <code>changeY</code>.
     * 
     * @param line Target line element
     * @param endpoint Endpoint number
     * @param changeX Changed X value of endpoint
     * @param changeY Changed Y value of endpoint
     */
    public void resizeLine(SVGLineElement line, int endpoint, float changeX, float changeY);
}
