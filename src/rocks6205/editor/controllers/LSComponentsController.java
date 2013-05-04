package rocks6205.editor.controllers;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.model.adt.LSLength;
import rocks6205.editor.model.adt.LSPainting;
import rocks6205.editor.model.elements.LSGenericElement;
import rocks6205.editor.model.elements.LSShapeCircle;
import rocks6205.editor.model.elements.LSShapeLine;
import rocks6205.editor.model.elements.LSShapeRect;

/**
 * Controller that modifies elements, to set presentation properties such as <code>fill</code>, <code>strokeWidth</code>, 
 * and <code>strokeWidth</code>, and resizing of shapes.
 * 
 * @author Cheow Yeong Chi
 * 
 * @version 2.1
 */
public interface LSComponentsController {

    /**
     * Add element <code>e</code> as a descendant of the current model.
     * @param e Element to be added
     */
    public void addElement(LSGenericElement e);

    /**
     * Set element <code>e</code>'s fill as <code>fill</code>.
     * @param e Target element
     * @param fill <code>LSPainting</code> fill object
     */
    public void setFillForElement(LSPainting fill, LSGenericElement e);

    /**
     * Set element <code>e</code>'s stroke as <code>stroke</code>.
     * @param e Target element
     * @param stroke <code>LSPainting</code> stroke object
     */
    public void setStrokeForElement(LSPainting stroke, LSGenericElement e);

    /**
     * Set element <code>e</code>'s stroke width as <code>strokeWidth</code>.
     * @param e Target element
     * @param strokeWidth <code>LSLength</code> object
     */
    public void setStrokeWidthForElement(LSLength strokeWidth, LSGenericElement e);

    /**
     * Resizes rectangle element with <code>changeWidth</code> and <code>changeHeight</code>.
     *
     * @param rect Target rectangle element
     * @param changeWidth Changed width
     * @param changeHeight Changed height
     */
    public void resizeRect(LSShapeRect rect, float changeWidth, float changeHeight);

    /**
     * Resizes circle element with <code>changedRadius</code>.
     * @param circle Target circle element
     * @param changedRadius Changed radius
     */
    public void resizeCircle(LSShapeCircle circle, float changedRadius);

    /**
     * Resizes line element with <code>changeX</code> and <code>changeY</code>.
     *
     * @param line Target line element
     * @param endpoint Endpoint number
     * @param changeX Changed X value of endpoint
     * @param changeY Changed Y value of endpoint
     */
    public void resizeLine(LSShapeLine line, int endpoint, float changeX, float changeY);
}