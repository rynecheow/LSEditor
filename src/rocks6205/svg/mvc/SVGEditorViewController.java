package rocks6205.svg.mvc;

//~--- non-JDK imports --------------------------------------------------------

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.xml.sax.InputSource;

import rocks6205.svg.adt.SVGLengthUnit;
import rocks6205.svg.adt.SVGLengthUnitType;
import rocks6205.svg.adt.SVGPainting;
import rocks6205.svg.editor.controllers.SVGEditorComponentsController;
import rocks6205.svg.editor.controllers.SVGEditorFileController;
import rocks6205.svg.editor.controllers.SVGEditorSelectionsController;
import rocks6205.svg.elements.SVGCircleElement;
import rocks6205.svg.elements.SVGContainerElement;
import rocks6205.svg.elements.SVGGElement;
import rocks6205.svg.elements.SVGGenericElement;
import rocks6205.svg.elements.SVGLineElement;
import rocks6205.svg.elements.SVGRectElement;
import rocks6205.svg.elements.SVGSVGElement;
import rocks6205.svg.parser.XMLParser;

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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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

            SVGContainerElement ancestor = e1.getAncestorElement();

            return ancestor.indexOf(e1) - ancestor.indexOf(e2);
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

            SVGContainerElement ancestor = e1.getAncestorElement();

            return ancestor.indexOf(e2) - ancestor.indexOf(e1);
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
     * @author Cheow Yeong Chi
     */
    @Override
    public void addElement(SVGGenericElement e) {
        model.getSVGElement().addDescendant(e);
        touchDocument();

    }

    /**
     * {@inheritDoc}
     *
     * @author Cheow Yeong Chi
     */
    public void setElementFill(SVGGenericElement e, SVGPainting fill) {
        e.setFill(fill);
        touchDocument();
    }

    /**
     * {@inheritDoc}
     *
     * @author  Cheow Yeong Chi
     */
    public void setElementStroke(SVGGenericElement e, SVGPainting stroke) {
        e.setStroke(stroke);
        touchDocument();
    }

    /**
     * {@inheritDoc}
     *
     * @author Cheow Yeong Chi
     */
    public void setElementStrokeWidth(SVGGenericElement e, SVGLengthUnit strokeWidth) {
        e.setStrokeWidth(strokeWidth);
        touchDocument();
    }

    /**
     * {@inheritDoc}
     * 
     * @author Cheow Yeong Chi
     */
    public void resizeRect(SVGRectElement rect, float changeWidth, float changeHeight) {
        if (Float.isNaN(changeWidth)) {
            throw new IllegalArgumentException("Change in width cannot be NaN");
        }

        if (Float.isNaN(changeHeight)) {
            throw new IllegalArgumentException("Change in height cannot be NaN");
        }

        SVGLengthUnit w = new SVGLengthUnit(rect.getWidth().getValue(SVGLengthUnitType.PX) + changeWidth);
        SVGLengthUnit h = new SVGLengthUnit(rect.getHeight().getValue(SVGLengthUnitType.PX) + changeHeight);

        rect.setWidth(SVGLengthUnit.convert(w, rect.getWidth().getUnitType()));
        rect.setHeight(SVGLengthUnit.convert(h, rect.getHeight().getUnitType()));
        touchDocument();
    }

    /**
     * {@inheritDoc}
     * 
     * @author Cheow Yeong Chi
     */
    public void resizeCircle(SVGCircleElement circle, float changedRadius) {
        if (Float.isNaN(changedRadius)) {
            throw new IllegalArgumentException("Change in radius cannot be NaN");
        }

        SVGLengthUnit r = new SVGLengthUnit(circle.getRadius().getValue(SVGLengthUnitType.PX) + changedRadius);

        circle.setRadius(SVGLengthUnit.convert(r, circle.getRadius().getUnitType()));
        touchDocument();
    }

    /**
     * {@inheritDoc}
     * 
     * @author Cheow Yeong Chi
     */
    public void resizeLine(SVGLineElement line, int endpoint, float changeX, float changeY) {
        switch (endpoint) {
        case 1 :
            SVGLengthUnit x1 = new SVGLengthUnit(line.getX1().getValue(SVGLengthUnitType.PX) + changeX);
            SVGLengthUnit y1 = new SVGLengthUnit(line.getY1().getValue(SVGLengthUnitType.PX) + changeY);

            line.setX1(SVGLengthUnit.convert(x1, line.getX1().getUnitType()));
            line.setY1(SVGLengthUnit.convert(y1, line.getY1().getUnitType()));

            break;

        case 2 :
            SVGLengthUnit x2 = new SVGLengthUnit(line.getX2().getValue(SVGLengthUnitType.PX) + changeX);
            SVGLengthUnit y2 = new SVGLengthUnit(line.getY2().getValue(SVGLengthUnitType.PX) + changeY);

            line.setX2(SVGLengthUnit.convert(x2, line.getX2().getUnitType()));
            line.setY2(SVGLengthUnit.convert(y2, line.getY2().getUnitType()));

            break;

        default :
            throw new IllegalArgumentException("Invalid endpoint number");
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @author Cheow Yeong Chi
     */
    public boolean fileLoad(File file) throws IOException{
        if ((file != null) && file.getName().endsWith(".svg")) {
            Document doc = XMLParser.parseXml(new InputSource(file.toURI().toString()));

            if (doc != null) {
                SVGSVGElement svg_e = SVGSVGElement.parseDocument(doc);
                if (svg_e != null) {
                    model.setSVGElement(svg_e);
                    currentFile        = file;
                    isDocumentModified = false;
                    updateViews();
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @author 
     */
    public boolean saveFile() throws IOException {
	return saveFile(currentFile);
    }

    /**
     * {@inheritDoc}
     * 
     * @author 
     */
    public boolean saveFile(File file) throws IOException {
	if (file != null) {
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

                factory.setNamespaceAware(true);

                DocumentBuilder     builder  = factory.newDocumentBuilder();
                Document            doc      = builder.newDocument();
                Node                ancestor = doc;
                Element             e        = null;
                Attr                attr;
                SVGGenericElement   svg_e = model.getSVGElement();
                SVGContainerElement svgAncestor;

                while (svg_e != null) {
                    if (svg_e instanceof SVGSVGElement) {
                        SVGSVGElement root = (SVGSVGElement) svg_e;

                        e = doc.createElementNS(SVGDefaultNamespace, "svg");
                        ancestor.appendChild(e);
                        attr = doc.createAttributeNS(null, "width");
                        attr.setValue(root.getWidth().toString());
                        e.setAttributeNodeNS(attr);
                        attr = doc.createAttributeNS(null, "height");
                        attr.setValue(root.getHeight().toString());
                        e.setAttributeNodeNS(attr);

                        if (root.hasDescendant()) {
                            ancestor  = e;
                            svg_e     = root.getDescendant(0);
                            svgAncestor = root;

                            continue;
                        }
                    } else {
                        Attr        fillAttr        = null;
                        Attr        strokeAttr      = null;
                        Attr        strokeWidthAttr = null;
                        Attr        transformAttr   = null;
                        SVGPainting fill            = svg_e.getFill();

                        if (fill != null) {
                            fillAttr = doc.createAttributeNS(null, "fill");
                            fillAttr.setValue(fill.toString());
                        }

                        SVGPainting stroke = svg_e.getStroke();

                        if (stroke != null) {
                            strokeAttr = doc.createAttributeNS(null, "stroke");
                            strokeAttr.setValue(stroke.toString());
                        }

                        SVGLengthUnit strokeWidth = svg_e.getStrokeWidth();

                        if (strokeWidth != null) {
                            strokeWidthAttr = doc.createAttributeNS(null, "stroke-width");
                            strokeWidthAttr.setValue(strokeWidth.toString());
                        }

                        SVGLengthUnit translateX = svg_e.getTranslateX();
                        SVGLengthUnit translateY = svg_e.getTranslateY();

                        if (translateX != null) {
                            transformAttr = doc.createAttributeNS(null, "transform");

                            String value = "translate(" + translateX.getValue();

                            if (translateY != null) {
                                value += "," + translateY.getValue();
                            }

                            value += ")";
                            transformAttr.setValue(value);
                        }

                        if (svg_e instanceof SVGGElement) {
                            SVGGElement group = (SVGGElement) svg_e;

                            e = doc.createElementNS(SVGDefaultNamespace, "g");

                            if (fillAttr != null) {
                                e.setAttributeNode(fillAttr);
                            }

                            if (strokeAttr != null) {
                                e.setAttributeNode(strokeAttr);
                            }

                            if (strokeWidthAttr != null) {
                                e.setAttributeNode(strokeWidthAttr);
                            }

                            if (transformAttr != null) {
                                e.setAttributeNode(transformAttr);
                            }

                            ancestor.appendChild(e);

                            if (group.hasDescendant()) {
                                ancestor  = e;
                                svg_e     = group.getDescendant(0);
                                svgAncestor = group;

                                continue;
                            }
                        } else if (svg_e instanceof SVGRectElement) {
                            SVGRectElement rect = (SVGRectElement) svg_e;

                            e    = doc.createElementNS(SVGDefaultNamespace, "rect");
                            attr = doc.createAttributeNS(null, "x");
                            attr.setValue(rect.getX().toString());
                            e.setAttributeNodeNS(attr);
                            attr = doc.createAttributeNS(null, "y");
                            attr.setValue(rect.getY().toString());
                            e.setAttributeNodeNS(attr);
                            attr = doc.createAttributeNS(null, "width");
                            attr.setValue(rect.getWidth().toString());
                            e.setAttributeNodeNS(attr);
                            attr = doc.createAttributeNS(null, "height");
                            attr.setValue(rect.getHeight().toString());
                            e.setAttributeNodeNS(attr);
                            ancestor.appendChild(e);
                        } else if (svg_e instanceof SVGCircleElement) {
                            SVGCircleElement circle = (SVGCircleElement) svg_e;

                            e    = doc.createElementNS(SVGDefaultNamespace, "circle");
                            attr = doc.createAttributeNS(null, "cx");
                            attr.setValue(circle.getCx().toString());
                            e.setAttributeNodeNS(attr);
                            attr = doc.createAttributeNS(null, "cy");
                            attr.setValue(circle.getCy().toString());
                            e.setAttributeNodeNS(attr);
                            attr = doc.createAttributeNS(null, "r");
                            attr.setValue(circle.getRadius().toString());
                            e.setAttributeNodeNS(attr);
                            ancestor.appendChild(e);
                        } else if (svg_e instanceof SVGLineElement) {
                            SVGLineElement line = (SVGLineElement) svg_e;

                            e    = doc.createElementNS(SVGDefaultNamespace, "line");
                            attr = doc.createAttributeNS(null, "x1");
                            attr.setValue(line.getX1().toString());
                            e.setAttributeNodeNS(attr);
                            attr = doc.createAttributeNS(null, "y1");
                            attr.setValue(line.getY1().toString());
                            e.setAttributeNodeNS(attr);
                            attr = doc.createAttributeNS(null, "x2");
                            attr.setValue(line.getX2().toString());
                            e.setAttributeNodeNS(attr);
                            attr = doc.createAttributeNS(null, "y2");
                            attr.setValue(line.getY2().toString());
                            e.setAttributeNodeNS(attr);
                            ancestor.appendChild(e);
                        }

                        if (fillAttr != null) {
                            e.setAttributeNode(fillAttr);
                        }

                        if (strokeAttr != null) {
                            e.setAttributeNode(strokeAttr);
                        }

                        if (strokeWidthAttr != null) {
                            e.setAttributeNode(strokeWidthAttr);
                        }

                        if (transformAttr != null) {
                            e.setAttributeNode(transformAttr);
                        }
                    }

                    svgAncestor = svg_e.getAncestorElement();
                    svg_e     = svg_e.getNextSiblingElement();

                    while ((svg_e == null) && (svgAncestor != null)) {
                        ancestor  = ancestor.getParentNode();
                        svg_e     = svgAncestor.getNextSiblingElement();
                        svgAncestor = svgAncestor.getAncestorElement();
                    }
                }

                TransformerFactory tFact       = TransformerFactory.newInstance();
                Transformer        transformer = tFact.newTransformer();

                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

                DOMSource    source = new DOMSource(doc);
                StreamResult result = new StreamResult(file);

                transformer.transform(source, result);
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (TransformerConfigurationException e) {
                e.printStackTrace();
            } catch (TransformerException e) {
                Throwable exception = e.getException();

                if ((exception != null) && (exception instanceof IOException)) {
                    throw(IOException) exception;
                }

                e.printStackTrace();
            }

            isDocumentModified = false;

            return true;
        }

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