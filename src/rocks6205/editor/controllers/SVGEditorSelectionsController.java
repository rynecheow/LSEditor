package rocks6205.editor.controllers;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.svg.elements.SVGGenericElement;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import java.util.LinkedHashSet;

public interface SVGEditorSelectionsController {

    /**
     * Checks elements in selections and returns true if point is in bound of
     * any element.
     * @param point
     * @return If point is selected
     */
    public boolean isPointSelected(Point2D point);

    /**
     * Return a set of elements that is of current selections
     * @return Hash set of elements
     */
    public LinkedHashSet<SVGGenericElement> getSelections();

    /**
     * Adds element to selection set.
     * @param e SVG elements
     */
    public void addToSelection(SVGGenericElement e);

    /**
     * Add any element in the selected point to selection set.
     * @param point Current selection point
     */
    public void addToSelection(Point2D point);

    /**
     * Add any element in the selected rectangular area to selection set.
     * @param rect Rectangular selection area
     */
    public void addToSelection(Rectangle2D rect);

    /**
     * Remove any element in the selected point to selection set.
     * @param point
     */
    public void removeFromSelection(Point point);

    /**
     * Remove any element in the selected point to selection set.
     * @param rect Rectangular selection area
     */
    public void removeFromSelection(Rectangle rect);

    /**
     * Select everything by adding the everything into selection set.
     */
    public void selectAll();

    /**
     * Clears selection.
     */
    public void clearSelection();

    /**
     * Delete the selected element from selection and clears selection.
     */
    public void deleteSelectedElement();

    /**
     * Translates element by <code>tx</code> units horizontally, and <code>ty</code> units vertically.
     *
     * @param tx Unit horizontal translation
     * @param ty Unit vertical translation
     */
    public void moveSelectedElement(float tx, float ty);
    
    /**
     * Groups all selected elements under <code>SVGGElement</code>.
     */
    public void group();

    /**
     * Ungroups all <code>SVGGElement</code> into individual elements.
     */
    public void ungroup();

    
}