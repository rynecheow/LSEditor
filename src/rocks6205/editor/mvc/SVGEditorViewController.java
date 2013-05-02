package rocks6205.editor.mvc;

//~--- non-JDK imports --------------------------------------------------------

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.xml.sax.InputSource;

import rocks6205.editor.model.adt.SVGLengthUnit;
import rocks6205.editor.model.adt.SVGLengthUnitType;
import rocks6205.editor.model.adt.SVGPainting;
import rocks6205.editor.controllers.SVGEditorComponentsController;
import rocks6205.editor.controllers.SVGEditorFileController;
import rocks6205.editor.controllers.SVGEditorSelectionsController;
import rocks6205.editor.model.elements.SVGCircleElement;
import rocks6205.editor.model.elements.SVGContainerElement;
import rocks6205.editor.model.elements.SVGGElement;
import rocks6205.editor.model.elements.SVGGenericElement;
import rocks6205.editor.model.elements.SVGLineElement;
import rocks6205.editor.model.elements.SVGRectElement;
import rocks6205.editor.model.elements.SVGSVGElement;
import rocks6205.system.parser.XMLParser;

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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
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
    private File activeFile;

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
        views              = new LinkedHashSet<>(1);
        selections         = new LinkedHashSet<>();
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
        return this.activeFile;
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

    public void modifyDocument() {
        isDocumentModified = true;
    }

    public void unmodifyDocument() {
        isDocumentModified = false;
    }

    public void touchDocument() {
        if (!isDocumentModified) {
            modificationStart_t = System.currentTimeMillis();
            modifyDocument();
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
        model = new SVGEditorModel();
//        model.setSVGElement(new SVGSVGElement(SVGLengthUnit.parse("916px"), SVGLengthUnit.parse("578px")));
        model.setSVGElement(new SVGSVGElement(SVGLengthUnit.parse("1000px"), SVGLengthUnit.parse("1000px")));
        activeFile = NEW_DOCUMENT;
        LSSVGEditor.logger.info("New document created with height 1000px and width 1000px. \n");
        unmodifyDocument();
        updateViews();
    }

    public BufferedImage renderImage(double scale) {
        int           width  = (int) (model.getSVGElement().getWidth().getValue(SVGLengthUnitType.PX) * scale);
        int           height = (int) (model.getSVGElement().getHeight().getValue(SVGLengthUnitType.PX) * scale);
        
        BufferedImage image  = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        
        Graphics2D    g      = image.createGraphics();

        g.scale(scale, scale);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        model.getSVGElement().drawShape(g);
        g.dispose();
//        LSSVGEditor.logger.info(String.format("Graphic rendered: Scale=\t"+scale+ " Width=\t"+width+" Height=\t"+height+"\n"));
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
        return new LinkedHashSet<>(views);
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
        LSSVGEditor.logger.info(String.format("Element of type "+e.getElementType()+" is added to the root element.\n"));
        touchDocument();
    }

    /**
     * {@inheritDoc}
     *
     * @author Cheow Yeong Chi
     */
    @Override
    public void setFillForElement(SVGPainting fill, SVGGenericElement e) {
        e.setFill(fill);
        touchDocument();
    }

    /**
     * {@inheritDoc}
     *
     * @author  Cheow Yeong Chi
     */
    @Override
    public void setStrokeForElement(SVGPainting stroke, SVGGenericElement e) {
        e.setStroke(stroke);
        touchDocument();
    }

    /**
     * {@inheritDoc}
     *
     * @author Cheow Yeong Chi
     */
    @Override
    public void setStrokeWidthForElement(SVGLengthUnit strokeWidth, SVGGenericElement e) {
        e.setStrokeWidth(strokeWidth);
        touchDocument();
    }

    /**
     * {@inheritDoc}
     *
     * @author Cheow Yeong Chi
     */
    @Override
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
//        String logInfo = "Rectangle is resized to " + w + rect.getWidth().getUnitType().getUnitSymbol() + " x " + h + rect.getWidth().getUnitType().getUnitSymbol();
//        LSSVGEditor.logger.info(logInfo);
        touchDocument();
    }

    /**
     * {@inheritDoc}
     *
     * @author Cheow Yeong Chi
     */
    @Override
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
    @Override
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
    @Override
    public boolean fileLoad(File file) throws IOException {
        if ((file != null) && file.getName().endsWith(".svg")) {
            Document doc = XMLParser.parseXml(new InputSource(file.toURI().toString()));

            if (doc != null) {
                SVGSVGElement svg_e = SVGSVGElement.parseDocument(doc);

                if (svg_e != null) {
                    model.setSVGElement(svg_e);
                    activeFile        = file;
                    isDocumentModified = false;
                    LSSVGEditor.logger.info(String.format("File named %s is successfully loaded", file.getName()));
                    getDescendantList();
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
    @Override
    public boolean fileSave() throws IOException {
        return fileSave(activeFile);
    }

    /**
     * {@inheritDoc}
     *
     * @author
     */
       @Override
    public boolean fileSave(File file) throws IOException {
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
            } catch (ParserConfigurationException | TransformerConfigurationException e) {
                System.err.println(e.getMessage());
            } catch (TransformerException e) {
                Throwable exception = e.getException();

                if ((exception != null) && (exception instanceof IOException)) {
                    throw(IOException) exception;
                }
            }

            unmodifyDocument();
            return true;
        }
        return false;
    }
       
    /**
     * {@inheritDoc}
     *
     * @author Cheow Yeong Chi
     */
    @Override
    public void fileClose() {
        endFileModification();
    }

    /**
     * {@inheritDoc}
     *
     * @author Cheow Yeong Chi
     */
    @Override
    public void endFileModification() {
        model.setSVGElement(null);
        selections.clear();
        activeFile = null;
        unmodifyDocument();
    }

    /**
     * SVGEditorFileController  
     */

    /**
     * {@inheritDoc}
     *
     * @author Cheow Yeong Chi
     */
    @Override
    public boolean isPointSelected(Point2D point) {
        for (SVGGenericElement e : selections) {
            if (e.getBounds().contains(point)) {
                return true;
            }
        }

        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @author Cheow Yeong Chi
     */
    @Override
    public LinkedHashSet<SVGGenericElement> getSelections() {
        return new LinkedHashSet<>(selections);
    }

    /**
     * {@inheritDoc}
     *
     * @author Cheow Yeong Chi
     */
    @Override
    public void addToSelection(SVGGenericElement e) {
        if (e != null) {
            selections.add(e);
        }

        updateViews();
    }

    /**
     * {@inheritDoc}
     *
     * @author Cheow Yeong Chi
     */
    @Override
    public void addToSelection(Point2D point) {
        ArrayList<SVGGenericElement> elements = model.getSVGElement().getDescendants();
        SVGGenericElement            e;

        for (int u = elements.size() - 1; u >= 0; u--) {
            e = elements.get(u);

            if (e.getBounds().contains(point)) {
                selections.add(e);

                break;
            }
        }

        updateViews();
    }

    /**
     * {@inheritDoc}
     *
     * @author Cheow Yeong Chi
     */
    @Override
    public void addToSelection(Rectangle2D rect) {
        for (SVGGenericElement e : model.getSVGElement().getDescendants()) {
            if (e.getBounds().intersects(rect)) {
                selections.add(e);
            }
        }

        updateViews();
    }

    /**
     * {@inheritDoc}
     *
     * @author Cheow Yeong Chi
     */
    @Override
    public void removeFromSelection(Point point) {
        ArrayList<SVGGenericElement> elements = model.getSVGElement().getDescendants();
        SVGGenericElement            e;

        for (int u = elements.size() - 1; u >= 0; u--) {
            e = elements.get(u);

            if (e.getBounds().contains(point)) {
                selections.remove(e);

                break;
            }
        }

        updateViews();
    }

    /**
     * {@inheritDoc}
     *
     * @author Cheow Yeong Chi
     */
    @Override
    public void removeFromSelection(Rectangle rect) {
        for (SVGGenericElement elem : model.getSVGElement().getDescendants()) {
            if (rect.contains(elem.getBounds())) {
                selections.remove(elem);
            }
        }
        updateViews();
    }

    /**
     * {@inheritDoc}
     *
     * @author Cheow Yeong Chi
     */
    @Override
    public void selectAll() {
        selections = new LinkedHashSet<>(model.getSVGElement().getDescendants());
        updateViews();
    }

    /**
     * {@inheritDoc}
     *
     * @author Cheow Yeong Chi
     */
    @Override
    public void clearSelection() {
        selections.clear();
        updateViews();
    }

    /**
     * {@inheritDoc}
     *
     * @author Cheow Yeong Chi
     */
    @Override
    public void deleteSelectedElement() {
        if (selections.isEmpty()) {
            return;
        }

        for (SVGGenericElement e : selections) {
            model.getSVGElement().removeDescendant(e);
        }

        selections.clear();
        touchDocument();
    }

    /**
     * {@inheritDoc}
     *
     * @author Cheow Yeong Chi
     */
    @Override
    public void moveSelectedElement(float tx, float ty) {
        SVGLengthUnit x;
        SVGLengthUnit y;

        for (SVGGenericElement e : selections) {
            if (e instanceof SVGRectElement) {
                SVGRectElement rect = (SVGRectElement) e;

                x = new SVGLengthUnit(rect.getX().getValue(SVGLengthUnitType.NUMBER) + tx);
                y = new SVGLengthUnit(rect.getY().getValue(SVGLengthUnitType.NUMBER) + ty);
                rect.setX(SVGLengthUnit.convert(x, rect.getX().getUnitType()));
                rect.setY(SVGLengthUnit.convert(y, rect.getY().getUnitType()));
            } else if (e instanceof SVGCircleElement) {
                SVGCircleElement circle = (SVGCircleElement) e;

                x = new SVGLengthUnit(circle.getCx().getValue(SVGLengthUnitType.NUMBER) + tx);
                y = new SVGLengthUnit(circle.getCy().getValue(SVGLengthUnitType.NUMBER) + ty);
                circle.setCx(SVGLengthUnit.convert(x, circle.getCx().getUnitType()));
                circle.setCy(SVGLengthUnit.convert(y, circle.getCy().getUnitType()));
            } else if (e instanceof SVGLineElement) {
                SVGLineElement line = (SVGLineElement) e;

                x = new SVGLengthUnit(line.getX1().getValue(SVGLengthUnitType.NUMBER) + tx);
                y = new SVGLengthUnit(line.getY1().getValue(SVGLengthUnitType.NUMBER) + ty);
                line.setX1(SVGLengthUnit.convert(x, line.getX1().getUnitType()));
                line.setY1(SVGLengthUnit.convert(y, line.getY1().getUnitType()));
                x = new SVGLengthUnit(line.getX2().getValue(SVGLengthUnitType.NUMBER) + tx);
                y = new SVGLengthUnit(line.getY2().getValue(SVGLengthUnitType.NUMBER) + ty);
                line.setX2(SVGLengthUnit.convert(x, line.getX2().getUnitType()));
                line.setY2(SVGLengthUnit.convert(y, line.getY2().getUnitType()));
            } else if (e instanceof SVGGElement) {
                x = e.getTranslateX();
                y = e.getTranslateY();

                if (x == null) {
                    x = new SVGLengthUnit(tx);
                } else {
                    x = new SVGLengthUnit(x.getValue() + tx);
                }

                if (y == null) {
                    y = new SVGLengthUnit(ty);
                } else {
                    y = new SVGLengthUnit(y.getValue() + ty);
                }

                e.setTranslateX(x);
                e.setTranslateY(y);
            }
        }

        touchDocument();
    }
    
    /**
     * {@inheritDoc}
     *
     * @author Komalah Nair
     */
    @Override
    public void group() {
        ArrayList<SVGGenericElement> selectionsList = new ArrayList<>(selections);

        if (selectionsList.size() < 2) {
            return;
        }

        Collections.sort(selectionsList, SVG_ELEMENT_ORDER);

        int         lastPos = model.getSVGElement().indexOf(selectionsList.get(selectionsList.size() - 1));
        SVGGElement group   = new SVGGElement();

        model.getSVGElement().insertDescendant(group, lastPos);

        for (SVGGenericElement e : selectionsList) {
            group.addDescendant(e);
            model.getSVGElement().removeDescendant(e);
            selections.remove(e);
        }

        selections.add(group);
        
    }

    /**
     * {@inheritDoc}
     *
     * @author Komalah Nair
     */
    @Override
    public void ungroup() {
        int                          position;
        ArrayList<SVGGenericElement> addList    = new ArrayList<>();
        ArrayList<SVGGenericElement> removeList = new ArrayList<>();

        for (SVGGenericElement e : selections) {
            if (e instanceof SVGGElement) {
                SVGGElement group = (SVGGElement) e;

                if (group.getDescendantCount() == 0) {
                    continue;
                }

                position = model.getSVGElement().indexOf(group);

                for (SVGGenericElement descendant : group.ungroup()) {
                    model.getSVGElement().insertDescendant(descendant, position++);
                    addList.add(descendant);
                }

                model.getSVGElement().removeDescendant(position);
                removeList.add(e);
            }
        }

        if (removeList.isEmpty()) {
            return;
        }

        for (SVGGenericElement e : addList) {
            selections.add(e);
        }

        for (SVGGenericElement e : removeList) {
            selections.remove(e);
        }

        touchDocument();
    }
    
    public void getDescendantList(){
       model.getSVGElement().listDescendants();
    }
}