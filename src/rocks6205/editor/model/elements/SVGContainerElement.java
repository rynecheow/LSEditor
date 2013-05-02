package rocks6205.editor.model.elements;

//~--- JDK imports ------------------------------------------------------------

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The <code>SVGContainerElement</code> class is an abstract class that defines a
 * container for other SVG container or non-container elements and store
 * descendants of the container element
 *
 * @author Cheow Yeong Chi
 *
 * @since 1.1
 *
 */
public abstract class SVGContainerElement extends SVGGenericElement {

    /**
     * List of SVG element object descendants
     */
    private ArrayList<SVGGenericElement> descendants;

    /**
     * Default constructor
     */
    public SVGContainerElement() {
        descendants = new ArrayList<>();
    }

    /*
     * ACCESSOR
     */

    /**
     * @return List of SVG element object descendants
     */
    public ArrayList<SVGGenericElement> getDescendants() {
        return descendants;
    }

    /**
     * Get descendant at a specific <code>index</code>
     *
     * @param index Descendant index
     * @return Descendant object at <code>index</code>
     */
    public SVGGenericElement getDescendant(int index) {
        return descendants.get(index);
    }

    public boolean hasDescendant() {
        return !descendants.isEmpty();
    }

    public int getDescendantCount() {
        return descendants.size();
    }

    public int indexOf(SVGGenericElement elem) {
        return descendants.indexOf(elem);
    }

    /**
     * Add descendant to current element and sets itself as the parent element
     *
     * @param e <code>SVGGenericElement</code> object
     */
    public void addDescendant(SVGGenericElement e) {
        if (e != null) {
            descendants.add(e);
            e.setAncestorElement(this);
        }
    }

    /**
     * Insert descendant element <code>e</code>at specific <code>index</code>
     * @param e Element to be inserted
     * @param atIndex Index for <code>e</code> to be inserted
     */
    public void insertDescendant(SVGGenericElement e, int atIndex) {
        descendants.add(atIndex, e);
        e.setAncestorElement(this);
    }

    /**
     * Replace descendant element <code>e</code>at specific <code>index</code>
     * @param e Element to be replaced
     * @param atIndex Index for <code>e</code> to be replaced
     */
    public void replaceDescendant(SVGGenericElement e, int atIndex) {}

    /**
     * Replace descendant element <code>e</code> with new element <code>eNew</code>
     * @param e Element to be replaced
     * @param eNew New element to replace current element
     */
    public void replaceDescendant(SVGGenericElement e, SVGGenericElement eNew) {}

    /**
     * Remove descendant element at specific <code>index</code>
     * @param index Index for element to be removed
     */
    public void removeDescendant(int index) {
        descendants.remove(index);
    }

    /**
     * Remove descendant element that matches <code>e</code>
     * @param e Element to be removed
     */
    public void removeDescendant(SVGGenericElement e) {
        descendants.remove(e);
    }

    public void listDescendants(SVGContainerElement container, int indentNumber) {
        int level = indentNumber;

        for (Iterator<SVGGenericElement> it = container.descendants.iterator(); it.hasNext(); ) {
            SVGGenericElement e = it.next();

            if (e instanceof SVGGElement) {
                SVGGElement g = (SVGGElement) e;

                System.out.println(getIndent(level) + g.getElementType());
                listDescendants(g, ++level);
                level--;
            } else {
                System.out.println(getIndent(level) + e.getElementType());
            }
        }
    }

    public String getIndent(int indentNumber) {
        String indent = "";

        while (indentNumber > 0) {
            indent += " ";
            indentNumber--;
        }

        return indent;
    }
}