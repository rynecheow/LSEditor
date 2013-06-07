package rocks6205.editor.dto;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.model.adt.LSLength;

/**
 * Data transfer object of the document which consist of width, height and the
 * title of the document.
 *
 * @author Cheow Yeong Chi
 *
 * @since 2.4
 */
public class LSDocumentDataObject {
    private LSLength width;
    private LSLength height;
    private String   title;

    /**
     * Default constructor.
     */
    public LSDocumentDataObject() {
        width  = new LSLength(0);
        height = new LSLength(0);
        title  = "";
    }

    /**
     * Constructs a <code>LSDocumentDataObject</code> instance with data initialised.
     * @param w Width of document
     * @param h Height of document
     * @param t Title of document
     */
    public LSDocumentDataObject(LSLength w, LSLength h, String t) {
        width  = w;
        height = h;
        title  = t;
    }

    /*
     * ACCESSORS
     */

    /**
     * @return Width of current document
     */
    public LSLength getWidth() {
        return width;
    }

    /**
     * @return Height of current document
     */
    public LSLength getHeight() {
        return height;
    }

    /**
     * @return Title of current document
     */
    public String getTitle() {
        return title;
    }

    /*
     * MUTATORS
     */

    /**
     * @param width Width of current document
     */
    public void setWidth(LSLength width) {
        this.width = width;
    }

    /**
     * @param height Height of current document
     */
    public void setHeight(LSLength height) {
        this.height = height;
    }

    /**
     * @param title Title of current document
     */
    public void setTitle(String title) {
        this.title = title;
    }
}