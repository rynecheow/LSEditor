package rocks6205.svg.editor.controllers;

import rocks6205.svg.adt.SVGLengthUnit;
import rocks6205.svg.adt.SVGPainting;
import rocks6205.svg.elements.SVGCircleElement;
import rocks6205.svg.elements.SVGGenericElement;
import rocks6205.svg.elements.SVGLineElement;
import rocks6205.svg.elements.SVGRectElement;

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
    public void setElementFill(SVGGenericElement e, SVGPainting fill);

    /**
     * Set element <code>e</code>'s stroke as <code>stroke</code>.
     * @param e Target element
     * @param stroke <code>SVGPainting</code> stroke object
     */
    public void setElementStroke(SVGGenericElement e, SVGPainting stroke);

    /**
     * Set element <code>e</code>'s stroke width as <code>strokeWidth</code>.
     * @param e Target element
     * @param strokeWidth <code>SVGLengthUnit</code> object
     */
    public void setElementStrokeWidth(SVGGenericElement e, SVGLengthUnit strokeWidth);

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
