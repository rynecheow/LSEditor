package rocks6205.editor.core;

//~--- non-JDK imports --------------------------------------------------------

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.xml.sax.InputSource;

import rocks6205.editor.controllers.LSComponentsController;
import rocks6205.editor.controllers.LSFileController;
import rocks6205.editor.controllers.LSSelectionsController;
import rocks6205.editor.model.adt.LSLength;
import rocks6205.editor.model.adt.LSLengthUnitType;
import rocks6205.editor.model.adt.LSPainting;
import rocks6205.editor.model.elements.LSGenericContainer;
import rocks6205.editor.model.elements.LSGenericElement;
import rocks6205.editor.model.elements.LSGroupContainer;
import rocks6205.editor.model.elements.LSSVGContainer;
import rocks6205.editor.model.elements.LSShapeCircle;
import rocks6205.editor.model.elements.LSShapeLine;
import rocks6205.editor.model.elements.LSShapeRect;

import rocks6205.system.toolkit.LSSVGDOMParser;

import static rocks6205.editor.controllers.LSFileController.NEW_DOCUMENT;

//~--- JDK imports ------------------------------------------------------------

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import java.util.ArrayList;
import java.util.Collections;
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
public class LSViewController implements LSSelectionsController, LSFileController, LSComponentsController {
    /**
     * Default namespace for SVG documents.
     */
    public static final String SVGDefaultNamespace;

    /**
     * SVG element order comparator
     */
    public static final Comparator<LSGenericElement> SVG_ELEMENT_ORDER;

    /**
     * SVG element comparator reversed order
     */
    public static final Comparator<LSGenericElement> SVG_ELEMENT_REVERSE_ORDER;

    /*
     * STATIC INITIALISER
     */
    static {
        SVG_ELEMENT_REVERSE_ORDER = new Comparator<LSGenericElement>() {
            @Override
            public int compare(LSGenericElement e1, LSGenericElement e2) {
                if (e1 == e2) {
                    return 0;
                }

                LSGenericContainer ancestor = e1.getAncestorElement();

                return ancestor.indexOf(e2) - ancestor.indexOf(e1);
            }
        };
        SVG_ELEMENT_ORDER = new Comparator<LSGenericElement>() {
            @Override
            public int compare(LSGenericElement e1, LSGenericElement e2) {
                if (e1 == e2) {
                    return 0;
                }

                LSGenericContainer ancestor = e1.getAncestorElement();

                return ancestor.indexOf(e1) - ancestor.indexOf(e2);
            }
        };
        SVGDefaultNamespace = "http://www.w3.org/2000/svg";
    }

    /*
     * PROPERTIES
     */

    /**
     * Model object
     */
    private LSModel model;

    /**
     * View objects
     */
    private LinkedHashSet<LSView> views;

    /**
     * Selected objects.
     */
    private LinkedHashSet<LSGenericElement> selections;

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

    /**
     * String of current document preserving linebreaks and indentations.
     */
    private String docString;

    /*
     * CONSTRUCTOR
     */

    /**
     * Default constructor.
     */
    public LSViewController() {
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
    public LSModel getModel() {
        return this.model;
    }

    /**
     * @return Document modification state
     */
    public boolean isDocumentModified() {
        return isDocumentModified;
    }

    /**
     * @return Currently opened file
     */
    public File getCurrentFile() {
        return this.activeFile;
    }

    /**
     * @return String of current document preserving linebreaks and indentations.
     */
    public final String getDocumentString() {
        return docString;
    }

    /*
     * MUTATORS
     */

    /**
     * Get time elasped since modification starts.
     *
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
    public void setModel(LSModel model) {
        this.model = model;
    }

    /**
     * Marks document as modified.
     */
    public void modifyDocument() {
        isDocumentModified = true;
    }

    /**
     * Marks document as not modified.
     */
    public void unmodifyDocument() {
        isDocumentModified = false;
    }

    /**
     * Starts modification if not, updates necessary items.
     */
    public void touchDocument() {
        if (!isDocumentModified) {
            modificationStart_t = System.currentTimeMillis();
            modifyDocument();
        }

        updateDocumentString();
        updateViews();
    }

    public void setDocumentString(String d) {
        docString = d;
    }

    /**
     * Updates document string for code view.
     */
    public void updateDocumentString() {
        setDocumentString(parseDocumentAsFormattedString(generateUpdatedDocument()));
    }

    private Document generateUpdatedDocument() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            factory.setNamespaceAware(true);

            DocumentBuilder    builder  = factory.newDocumentBuilder();
            Document           doc      = builder.newDocument();
            Node               ancestor = doc;
            Element            e        = null;
            Attr               attr;
            LSGenericElement   svg_e = model.getSVGElement();
            LSGenericContainer svgAncestor;

            while (svg_e != null) {
                if (svg_e instanceof LSSVGContainer) {
                    LSSVGContainer root = (LSSVGContainer) svg_e;

                    e = doc.createElementNS(SVGDefaultNamespace, "svg");
                    ancestor.appendChild(e);
                    attr = doc.createAttributeNS(null, "width");
                    attr.setValue(root.getWidth().toString());
                    e.setAttributeNodeNS(attr);
                    attr = doc.createAttributeNS(null, "height");
                    attr.setValue(root.getHeight().toString());
                    e.setAttributeNodeNS(attr);

                    if (root.hasDescendant()) {
                        ancestor    = e;
                        svg_e       = root.getDescendant(0);
                        svgAncestor = root;

                        continue;
                    }
                } else {
                    Attr       fillAttr        = null;
                    Attr       strokeAttr      = null;
                    Attr       strokeWidthAttr = null;
                    Attr       transformAttr   = null;
                    LSPainting fill            = svg_e.getFill();

                    if (fill != null) {
                        fillAttr = doc.createAttributeNS(null, "fill");
                        fillAttr.setValue(fill.toString());
                    }

                    LSPainting stroke = svg_e.getStroke();

                    if (stroke != null) {
                        strokeAttr = doc.createAttributeNS(null, "stroke");
                        strokeAttr.setValue(stroke.toString());
                    }

                    LSLength strokeWidth = svg_e.getStrokeWidth();

                    if (strokeWidth != null) {
                        strokeWidthAttr = doc.createAttributeNS(null, "stroke-width");
                        strokeWidthAttr.setValue(strokeWidth.toString());
                    }

                    LSLength translateX = svg_e.getTranslateX();
                    LSLength translateY = svg_e.getTranslateY();

                    if (translateX != null) {
                        transformAttr = doc.createAttributeNS(null, "transform");

                        String value = "translate(" + translateX.getValue();

                        if (translateY != null) {
                            value += "," + translateY.getValue();
                        }

                        value += ")";
                        transformAttr.setValue(value);
                    }

                    if (svg_e instanceof LSGroupContainer) {
                        LSGroupContainer group = (LSGroupContainer) svg_e;

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
                            ancestor    = e;
                            svg_e       = group.getDescendant(0);
                            svgAncestor = group;

                            continue;
                        }
                    } else if (svg_e instanceof LSShapeRect) {
                        LSShapeRect rect = (LSShapeRect) svg_e;

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
                    } else if (svg_e instanceof LSShapeCircle) {
                        LSShapeCircle circle = (LSShapeCircle) svg_e;

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
                    } else if (svg_e instanceof LSShapeLine) {
                        LSShapeLine line = (LSShapeLine) svg_e;

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

                    if (e != null) {
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
                }

                svgAncestor = svg_e.getAncestorElement();
                svg_e       = svg_e.getNextSiblingElement();

                while ((svg_e == null) && (svgAncestor != null)) {
                    ancestor    = ancestor.getParentNode();
                    svg_e       = svgAncestor.getNextSiblingElement();
                    svgAncestor = svgAncestor.getAncestorElement();
                }
            }

            return doc;
        } catch (ParserConfigurationException ex) {
            LSEditor.logger.warning(ex.getMessage());

            return null;
        }
    }
    
    /*
     * DOCUMENT OPERATION
     */

    /**
     * Get the width of current document.
     * @return Width of current model
     */
    public LSLength getDocumentWidth() {
        return model.getSVGElement().getWidth();
    }

    /**
     * Get the height of current document.
     * @return Height of current model
     */
    public LSLength getDocumentHeight() {
        return model.getSVGElement().getWidth();
    }

    /**
     * Resizes the current document to specific <code>width</code> and <code>height</code>.
     *
     * @param width Canvas width
     * @param height Canvas height
     */
    public void resizeDocument(LSLength width, LSLength height) {
        model.getSVGElement().setWidth(width);
        model.getSVGElement().setHeight(height);
        touchDocument();
        updateViews();
    }

    public void createBlankDocument() {
        model = new LSModel();
//        model.setSVGElement(new LSSVGContainer(LSLength.parse("916px"), LSLength.parse("578px")));
        model.setSVGElement(new LSSVGContainer(LSLength.parse("1000px"), LSLength.parse("1000px")));
        model.setTitle(NEW_DOCUMENT.getName());
        model.updateCanvasDTO();
        activeFile = NEW_DOCUMENT;
        LSEditor.logger.info(String.format("\"New document created with height %fpx and width %fpx. \\n\"", model.getSVGElement().getWidth().getValue(),model.getSVGElement().getHeight().getValue()));
        unmodifyDocument();
        updateDocumentString();
        updateViews();
    }

    /**
     * Renders graphics from SVG element with scale.
     *
     * @param scale A float value that changes according to view and controls
     * @return An image rendered from SVG element with anti-aliasing.
     */
    public BufferedImage renderImage(float scale) {
        int           width  = (int) (model.getSVGElement().getWidth().getValue(LSLengthUnitType.PX) * scale);
        int           height = (int) (model.getSVGElement().getHeight().getValue(LSLengthUnitType.PX) * scale);
        BufferedImage image  = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D    g2d    = image.createGraphics();

        g2d.scale(scale, scale);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        model.getSVGElement().drawShape(g2d);
        g2d.dispose();

        return image;
    }

    /*
     * SET OPERATIONS
     */

    /**
     * Add a view into the current set of views.
     * @param view
     */
    public void addView(LSView view) {
        views.add(view);
    }

    /**
     * Removes a view into the current set of views.
     * @param view
     */
    public void removeView(LSView view) {
        views.remove(view);
    }

    /**
     * Gets the current set of views.
     * @return Set of views
     */
    public LinkedHashSet<LSView> getViews() {
        return new LinkedHashSet<>(views);
    }

    /**
     * Updates views one by one.
     */
    public void updateViews() {
        for (LSView view : views) {
            view.update();
        }
    }

    /**
     * {@inheritDoc}
     *
     * @author Cheow Yeong Chi
     */
    @Override
    public void addElement(LSGenericElement e) {
        model.getSVGElement().addDescendant(e);
        LSEditor.logger.info(String.format("Element of type " + e.getElementType()
                                           + " is added to the root element.\n"));
        touchDocument();
    }

    /**
     * {@inheritDoc}
     *
     * @author Cheow Yeong Chi
     */
    @Override
    public void setFillForElement(LSPainting fill, LSGenericElement e) {
        e.setFill(fill);
        touchDocument();
    }

    /**
     * {@inheritDoc}
     *
     * @author  Cheow Yeong Chi
     */
    @Override
    public void setStrokeForElement(LSPainting stroke, LSGenericElement e) {
        e.setStroke(stroke);
        touchDocument();
    }

    /**
     * {@inheritDoc}
     *
     * @author Cheow Yeong Chi
     */
    @Override
    public void setStrokeWidthForElement(LSLength strokeWidth, LSGenericElement e) {
        e.setStrokeWidth(strokeWidth);
        touchDocument();
    }

    /**
     * {@inheritDoc}
     *
     * @author Cheow Yeong Chi
     */
    @Override
    public void resizeRect(LSShapeRect rect, float changeWidth, float changeHeight) {
        if (Float.isNaN(changeWidth)) {
            throw new IllegalArgumentException("Change in width cannot be NaN");
        }

        if (Float.isNaN(changeHeight)) {
            throw new IllegalArgumentException("Change in height cannot be NaN");
        }

        LSLength w = new LSLength(rect.getWidth().getValue(LSLengthUnitType.PX) + changeWidth);
        LSLength h = new LSLength(rect.getHeight().getValue(LSLengthUnitType.PX) + changeHeight);

        rect.setWidth(LSLength.convert(w, rect.getWidth().getUnitType()));
        rect.setHeight(LSLength.convert(h, rect.getHeight().getUnitType()));
        touchDocument();
    }

    /**
     * {@inheritDoc}
     *
     * @author Cheow Yeong Chi
     */
    @Override
    public void resizeCircle(LSShapeCircle circle, float changedRadius) {
        if (Float.isNaN(changedRadius)) {
            throw new IllegalArgumentException("Change in radius cannot be NaN");
        }

        LSLength r = new LSLength(circle.getRadius().getValue(LSLengthUnitType.PX) + changedRadius);

        circle.setRadius(LSLength.convert(r, circle.getRadius().getUnitType()));
        touchDocument();
    }

    /**
     * {@inheritDoc}
     *
     * @author Cheow Yeong Chi
     */
    @Override
    public void resizeLine(LSShapeLine line, int endpoint, float changeX, float changeY) {
        switch (endpoint) {
        case 1 :
            LSLength x1 = new LSLength(line.getX1().getValue(LSLengthUnitType.PX) + changeX);
            LSLength y1 = new LSLength(line.getY1().getValue(LSLengthUnitType.PX) + changeY);

            line.setX1(LSLength.convert(x1, line.getX1().getUnitType()));
            line.setY1(LSLength.convert(y1, line.getY1().getUnitType()));

            break;

        case 2 :
            LSLength x2 = new LSLength(line.getX2().getValue(LSLengthUnitType.PX) + changeX);
            LSLength y2 = new LSLength(line.getY2().getValue(LSLengthUnitType.PX) + changeY);

            line.setX2(LSLength.convert(x2, line.getX2().getUnitType()));
            line.setY2(LSLength.convert(y2, line.getY2().getUnitType()));

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
            Document doc = LSSVGDOMParser.parseXml(new InputSource(file.toURI().toString()));

            if (doc != null) {
                setDocumentString(parseDocumentAsFormattedString(doc));

                LSSVGContainer svg_e = LSSVGContainer.parseDocument(doc);

                if (svg_e != null) {
                    model.setSVGElement(svg_e);
                    model.setTitle(file.getName());
                    activeFile         = file;
                    isDocumentModified = false;
                    LSEditor.logger.info(String.format("File named %s is successfully loaded\n", file.getName()));
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
                Document           doc         = this.generateUpdatedDocument();
                TransformerFactory tFact       = TransformerFactory.newInstance();
                Transformer        transformer = tFact.newTransformer();

                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

                DOMSource    source = new DOMSource(doc);
                StreamResult result = new StreamResult(file);

                transformer.transform(source, result);
            } catch (TransformerConfigurationException e) {
                LSEditor.logger.warning(e.getMessage());
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
     * LSFileController
     */

    /**
     * {@inheritDoc}
     *
     * @author Cheow Yeong Chi
     */
    @Override
    public boolean isPointSelected(Point2D point) {
        for (LSGenericElement e : selections) {
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
    public LinkedHashSet<LSGenericElement> getSelections() {
        return new LinkedHashSet<>(selections);
    }

    /**
     * {@inheritDoc}
     *
     * @author Cheow Yeong Chi
     */
    @Override
    public void addToSelection(LSGenericElement e) {
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
        ArrayList<LSGenericElement> elements = model.getSVGElement().getDescendants();
        LSGenericElement            e;

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
        for (LSGenericElement e : model.getSVGElement().getDescendants()) {
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
        ArrayList<LSGenericElement> elements = model.getSVGElement().getDescendants();
        LSGenericElement            e;

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
        for (LSGenericElement elem : model.getSVGElement().getDescendants()) {
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

        for (LSGenericElement e : selections) {
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
        LSLength x;
        LSLength y;

        for (LSGenericElement e : selections) {
            if (e instanceof LSShapeRect) {
                LSShapeRect rect = (LSShapeRect) e;

                x = new LSLength(rect.getX().getValue(LSLengthUnitType.NUMBER) + tx);
                y = new LSLength(rect.getY().getValue(LSLengthUnitType.NUMBER) + ty);
                rect.setX(LSLength.convert(x, rect.getX().getUnitType()));
                rect.setY(LSLength.convert(y, rect.getY().getUnitType()));
            } else if (e instanceof LSShapeCircle) {
                LSShapeCircle circle = (LSShapeCircle) e;

                x = new LSLength(circle.getCx().getValue(LSLengthUnitType.NUMBER) + tx);
                y = new LSLength(circle.getCy().getValue(LSLengthUnitType.NUMBER) + ty);
                circle.setCx(LSLength.convert(x, circle.getCx().getUnitType()));
                circle.setCy(LSLength.convert(y, circle.getCy().getUnitType()));
            } else if (e instanceof LSShapeLine) {
                LSShapeLine line = (LSShapeLine) e;

                x = new LSLength(line.getX1().getValue(LSLengthUnitType.NUMBER) + tx);
                y = new LSLength(line.getY1().getValue(LSLengthUnitType.NUMBER) + ty);
                line.setX1(LSLength.convert(x, line.getX1().getUnitType()));
                line.setY1(LSLength.convert(y, line.getY1().getUnitType()));
                x = new LSLength(line.getX2().getValue(LSLengthUnitType.NUMBER) + tx);
                y = new LSLength(line.getY2().getValue(LSLengthUnitType.NUMBER) + ty);
                line.setX2(LSLength.convert(x, line.getX2().getUnitType()));
                line.setY2(LSLength.convert(y, line.getY2().getUnitType()));
            } else if (e instanceof LSGroupContainer) {
                x = e.getTranslateX();
                y = e.getTranslateY();

                if (x == null) {
                    x = new LSLength(tx);
                } else {
                    x = new LSLength(x.getValue() + tx);
                }

                if (y == null) {
                    y = new LSLength(ty);
                } else {
                    y = new LSLength(y.getValue() + ty);
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
        ArrayList<LSGenericElement> selectionsList = new ArrayList<>(selections);

        if (selectionsList.size() < 2) {
            return;
        }

        Collections.sort(selectionsList, SVG_ELEMENT_ORDER);

        LSSVGContainer   svgEl   = model.getSVGElement();
        int              lastPos = svgEl.indexOf(selectionsList.get(selectionsList.size() - 1));
        LSGroupContainer group   = new LSGroupContainer();

        svgEl.insertDescendant(group, lastPos);

        for (LSGenericElement e : selectionsList) {
            group.addDescendant(e);
            svgEl.removeDescendant(e);
            selections.remove(e);
        }

        model.setSVGElement(svgEl);
        selections.add(group);
        touchDocument();
    }

    /**
     * {@inheritDoc}
     *
     * @author Komalah Nair
     */
    @Override
    public void ungroup() {
        int                         position;
        ArrayList<LSGenericElement> addList    = new ArrayList<>();
        ArrayList<LSGenericElement> removeList = new ArrayList<>();

        for (LSGenericElement e : selections) {
            if (e instanceof LSGroupContainer) {
                LSGroupContainer group = (LSGroupContainer) e;

                if (group.getDescendantCount() == 0) {
                    continue;
                }

                LSSVGContainer svgEl = model.getSVGElement();

                position = svgEl.indexOf(group);

                for (LSGenericElement descendant : group.ungroup()) {
                    svgEl.insertDescendant(descendant, position++);
                    addList.add(descendant);
                }

                svgEl.removeDescendant(position);
                model.setSVGElement(svgEl);
                removeList.add(e);
            }
        }

        if (removeList.isEmpty()) {
            return;
        }

        for (LSGenericElement e : addList) {
            selections.add(e);
        }

        for (LSGenericElement e : removeList) {
            selections.remove(e);
        }

        touchDocument();
    }

    private String parseDocumentAsFormattedString(Document d) {
        try {
            OutputFormat format = new OutputFormat(d);

            format.setLineWidth(65);
            format.setIndenting(true);
            format.setIndent(2);

            Writer        out        = new StringWriter();
            XMLSerializer serializer = new XMLSerializer(out, format);

            serializer.serialize(d);

            return out.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}