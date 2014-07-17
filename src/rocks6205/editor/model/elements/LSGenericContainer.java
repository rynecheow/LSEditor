package rocks6205.editor.model.elements;

//~--- JDK imports ------------------------------------------------------------

import java.util.ArrayList;

/**
 * The <code>LSGenericContainer</code> class is an abstract class that defines a
 * container for other SVG container or non-container elements and store
 * descendants of the container element
 *
 * @author Cheow Yeong Chi
 * @since 1.1
 */
public abstract class LSGenericContainer extends LSGenericElement {
    private ArrayList<LSGenericElement> descendants;

    /**
     * Default constructor
     */
    public LSGenericContainer() {
        descendants = new ArrayList<>();
    }

    /*
     * ACCESSOR
     */

    /**
     * @return List of SVG element object descendants
     */
    public ArrayList<LSGenericElement> getDescendants() {
        return descendants;
    }

    /**
     * Get descendant at a specific <code>index</code>
     *
     * @param index Descendant index
     * @return Descendant object at <code>index</code>
     */
    public LSGenericElement getDescendant(int index) {
        return descendants.get(index);
    }

    /**
     * Check if the descendant list of the document is empty.
     *
     * @return <code>true</code> if descendant list is not empty, <code>false
     * </code> otherwise.
     */
    public boolean hasDescendant() {
        return !descendants.isEmpty();
    }

    /**
     * Get the number of descendants in the container.
     *
     * @return Number of descendants
     */
    public int getDescendantCount() {
        return descendants.size();
    }

    /**
     * Check the index of the current element in the descendant list of the
     * container.
     *
     * @param elem Element
     * @return index of current element
     */
    public int indexOf(LSGenericElement elem) {
        return descendants.indexOf(elem);
    }

    /**
     * Add descendant to current element and sets itself as the parent element
     *
     * @param e <code>LSGenericElement</code> object
     */
    public void addDescendant(LSGenericElement e) {
        if (e != null) {
            descendants.add(e);
            e.setAncestorElement(this);
        }
    }

    /**
     * Insert descendant element <code>e</code>at specific <code>index</code>
     *
     * @param e       Element to be inserted
     * @param atIndex Index for <code>e</code> to be inserted
     */
    public void insertDescendant(LSGenericElement e, int atIndex) {
        descendants.add(atIndex, e);
        e.setAncestorElement(this);
    }

    /**
     * Replace descendant element <code>e</code>at specific <code>index</code>
     *
     * @param e       Element to be replaced
     * @param atIndex Index for <code>e</code> to be replaced
     */
    public void replaceDescendant(LSGenericElement e, int atIndex) {
    }

    /**
     * Replace descendant element <code>e</code> with new element <code>eNew</code>
     *
     * @param e    Element to be replaced
     * @param eNew New element to replace current element
     */
    public void replaceDescendant(LSGenericElement e, LSGenericElement eNew) {
    }

    /**
     * Remove descendant element at specific <code>index</code>
     *
     * @param index Index for element to be removed
     */
    public void removeDescendant(int index) {
        descendants.remove(index);
    }

    /**
     * Remove descendant element that matches <code>e</code>
     *
     * @param e Element to be removed
     */
    public void removeDescendant(LSGenericElement e) {
        descendants.remove(e);
    }
}