package rocks6205.svg.mvc;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.svg.adt.SVGLengthUnit;
import rocks6205.svg.adt.SVGLengthUnitType;
import rocks6205.svg.adt.SVGPainting;
import rocks6205.svg.editor.controllers.SVGEditorComponentsController;
import rocks6205.svg.editor.controllers.SVGEditorFileController;
import rocks6205.svg.editor.controllers.SVGEditorSelectionsController;
import rocks6205.svg.elements.SVGCircleElement;
import rocks6205.svg.elements.SVGContainerElement;
import rocks6205.svg.elements.SVGGenericElement;
import rocks6205.svg.elements.SVGLineElement;
import rocks6205.svg.elements.SVGRectElement;
import rocks6205.svg.elements.SVGSVGElement;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.util.Comparator;
import java.util.LinkedHashSet;

/**
 * The <code>SVGViewController</code> class defines a MVC module and plays as
 * the central control of the whole engine which lets the model and view
 * communicate effectively.
 *
 * @author: Cheow Yeong Chi
 * @since 1.2
 *
 */
public class SVGEditorViewController
        implements SVGEditorSelectionsController, SVGEditorFileController, SVGEditorComponentsController {

    /**
     * Default namespace for SVG documents.
     */
    public static final String SVGDefaultNamespace = "http://www.w3.org/2000/svg";

    /**
     * SVG element order comparator
     */
    public static final Comparator<SVGGenericElement> SVG_ELEMENT_ORDER = new Comparator<SVGGenericElement>() {
        @Override
        public int compare(SVGGenericElement e1, SVGGenericElement e2) {
            if (e1 == e2) {
                return 0;
            }

            SVGContainerElement parent = e1.getAncestorElement();

            return parent.indexOf(e1) - parent.indexOf(e2);
        }
    };

    /**
     * SVG element comparator reversed order
     */
    public static final Comparator<SVGGenericElement> SVG_ELEMENT_REVERSE_ORDER = new Comparator<SVGGenericElement>() {
        @Override
        public int compare(SVGGenericElement e1, SVGGenericElement e2) {
            if (e1 == e2) {
                return 0;
            }

            SVGContainerElement parent = e1.getAncestorElement();

            return parent.indexOf(e2) - parent.indexOf(e1);
        }
    };
    private static final File NEW_DOCUMENT = null;

    /*
     * PROPERTIES
     */

    /**
     * Model object
     */
    private SVGEditorModel model;

    /**
     * View objects
     */
    private LinkedHashSet<SVGEditorView> views;

    /**
     * Selected objects.
     */
    private LinkedHashSet<SVGGenericElement> selections;

    /**
     * Currently opened file.
     */
    private File currentFile;

    /**
     * Document modification state
     */
    private boolean isDocumentModified;

    /**
     * Modification time.
     */
    private long modificationStart_t;

    /*
     * CONSTRUCTOR
     */

    /**
     * Default constructor.
     */
    public SVGEditorViewController() {
        views              = new LinkedHashSet<SVGEditorView>(1);
        selections         = new LinkedHashSet<SVGGenericElement>();
        isDocumentModified = false;
    }

    /*
     * ACCESSORS
     */

    /**
     * @return Model object
     */
    public SVGEditorModel getModel() {
        return this.model;
    }

    public boolean isDocumentModified() {
        return this.isDocumentModified;
    }

    public File getCurrentFile() {
        return this.currentFile;
    }

    /*
     * MUTATORS
     */

    /**
     * Get time elasped since modification starts.
     * @return Time elasped in milliseconds.
     */
    public long getTimeElapsed() {
        if (isDocumentModified()) {
            return System.currentTimeMillis() - modificationStart_t;
        }

        return 0;
    }

    /**
     * @param model Model object
     */
    public void setModel(SVGEditorModel model) {
        this.model = model;
    }

    public void touchDocument() {
        if (!isDocumentModified) {
            modificationStart_t = System.currentTimeMillis();
            isDocumentModified  = true;
        }

        updateViews();
    }

    /*
     * DOCUMENT OPERATION
     */

    /**
     * Get the width of current document.
     * @return Width of current model
     */
    public SVGLengthUnit getDocumentWidth() {
        return model.getSVGElement().getWidth();
    }

    /**
     * Get the height of current document.
     * @return Height of current model
     */
    public SVGLengthUnit getDocumentHeight() {
        return model.getSVGElement().getWidth();
    }

    /**
     * Resizes the current document to specific <code>width</code> and <code>height</code>.
     *
     * @param width Canvas width
     * @param height Canvas height
     */
    public void resizeDocument(SVGLengthUnit width, SVGLengthUnit height) {
        model.getSVGElement().setWidth(width);
        model.getSVGElement().setHeight(height);
        touchDocument();
        updateViews();
    }

    public void createBlankDocument() {
        model.setSVGElement(new SVGSVGElement(SVGLengthUnit.parse("500px"), SVGLengthUnit.parse("500px")));
        currentFile        = NEW_DOCUMENT;
        isDocumentModified = false;
        updateViews();
    }

    public BufferedImage renderImage(double scale) {
        int           width  = (int) (model.getSVGElement().getWidth().getValue(SVGLengthUnitType.PX) * scale);
        int           height = (int) (model.getSVGElement().getHeight().getValue(SVGLengthUnitType.PX) * scale);
        BufferedImage image  = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D    g      = image.createGraphics();

        g.scale(scale, scale);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        model.getSVGElement().draw(g);
        g.dispose();

        return image;
    }

    /*
     * SET OPERATIONS
     */

    /**
     * Add a view into the current set of views.
     * @param view
     */
    public void addView(SVGEditorView view) {
        views.add(view);
    }

    /**
     * Removes a view into the current set of views.
     * @param view
     */
    public void removeView(SVGEditorView view) {
        views.remove(view);
    }

    /**
     * Gets the current set of views.
     * @return Set of views
     */
    public LinkedHashSet<SVGEditorView> getViews() {
        return new LinkedHashSet<SVGEditorView>(views);
    }

    /**
     * Updates views one by one.
     */
    public void updateViews() {
        for (SVGEditorView view : views) {
            view.update();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @author 
     */
    public void addElement(SVGGenericElement e) {

        // TODO Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     * 
     * @author 
     */
    public void setElementFill(SVGGenericElement e, SVGPainting fill) {

        // TODO Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     * 
     * @author 
     */
    public void setElementStroke(SVGGenericElement e, SVGPainting stroke) {

        // TODO Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     * 
     * @author 
     */
    public void setElementStrokeWidth(SVGGenericElement e, SVGLengthUnit strokeWidth) {

        // TODO Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     * 
     * @author 
     */
    public void resizeRect(SVGRectElement rect, float changeWidth, float changeHeight) {

        // TODO Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     * 
     * @author 
     */
    public void resizeCircle(SVGCircleElement circle, float changedRadius) {

        // TODO Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     * 
     * @author 
     */
    public void resizeLine(SVGLineElement line, int endpoint, float changeX, float changeY) {

        // TODO Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     * 
     * @author 
     */
    public boolean fileLoad(File file) throws IOException{

        // TODO Auto-generated method stub
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @author 
     */
    public boolean saveFile() throws IOException {

        // TODO Auto-generated method stub
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @author 
     */
    public boolean saveFile(File file) throws IOException {

        // TODO Auto-generated method stub
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @author 
     */
    public void closeFile() {

        // TODO Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     * 
     * @author 
     */
    public void endFileModification() {

        // TODO Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     * 
     * @author 
     */
    public boolean isPointSelected(Point2D point) {

        // TODO Auto-generated method stub
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @author 
     */
    public LinkedHashSet<SVGGenericElement> getSelections() {

        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @author 
     */
    public void addToSelection(SVGGenericElement e) {

        // TODO Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     * 
     * @author 
     */
    public void addToSelection(Point2D point) {

        // TODO Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     * 
     * @author 
     */
    public void addToSelection(Rectangle2D rect) {

        // TODO Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     * 
     * @author 
     */
    public void removeFromSelection(Point point) {

        // TODO Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     * 
     * @author 
     */
    public void removeFromSelection(Rectangle rect) {

        // TODO Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     * 
     * @author 
     */
    public void selectAll() {

        // TODO Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     * 
     * @author 
     */
    public void clearSelection() {

        // TODO Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     * 
     * @author 
     */
    public void group() {

        // TODO Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     * 
     * @author 
     */
    public void ungroup() {

        // TODO Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     * 
     * @author 
     */
    public void deleteSelectedElement() {

        // TODO Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     * 
     * @author 
     */
    public void moveSelectedElement(float tx, float ty) {

        // TODO Auto-generated method stub
    }
}