package rocks6205.svg.engine;

//~--- non-JDK imports --------------------------------------------------------

import org.w3c.dom.Document;

import org.xml.sax.InputSource;

import rocks6205.svg.elements.SVGSVGElement;

import rocks6205.svgFamily.SVGImageCanvas;

import rocks6205.svgParser.XMLParser;

//~--- JDK imports ------------------------------------------------------------

import java.io.File;

import javax.swing.JOptionPane;

/**
 * The <code>SVGViewController</code> class defines a MVC module and plays as
 * the central control of the whole engine which lets the model and view
 * communicate effectively.
 *
 * @author: Cheow Yeong Chi
 * @since 1.2
 *
 */
public class SVGViewController {

    /*
     * PROPERTIES
     */

    /**
     * Model object
     */
    private SVGModel model;

    /**
     * View object
     */
    private SVGView view;

    /*
     * CONSTRUCTOR
     */

    /**
     * Constructs an <code>SVGViewController</code> instances with
     * <code>model</code> and <code>view</code>.
     * @param model  Model object
     * @param view  View object
     */
    public SVGViewController(SVGModel model, SVGView view) {
        setModel(model);
        setView(view);
    }

    /*
     * ACCESSORS
     */

    /**
     * @return View object
     */
    public SVGView getView() {
        return view;
    }

    /**
     * @return Model object
     */
    public SVGModel getModel() {
        return model;
    }

    /*
     * MUTATORS
     */

    /**
     * @param model Model object
     */
    public void setModel(SVGModel model) {
        this.model = model;
    }

    /**
     * @param view View object
     */
    public void setView(SVGView view) {
        this.view = view;
    }

    /*
     * METHODS
     */

    /**
     * Loads file from two ways:<p>
     * 1) Read directly from <code>JFileChooser</code>.<br>
     * 2) File from path string passed by command line argument.<p>
     *
     * File is then parsed into a <code>Document</code> object and rendered.
     *
     * @param file
     */
    public void fileLoad(File file) {
        if (file != null) {
            String   path = file.toURI().toString();
            Document doc  = null;

            try {
                doc = XMLParser.parseXml(new InputSource(path));
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }

            SVGImageCanvas.setZoomScale(1.0);

            if (doc != null) {
                SVGSVGElement svg_e = SVGSVGElement.parseDocument(doc);

                model.setSVGElement(svg_e);
                model.render();

                // --REFURBISH REQUIRED -- //
                view.menuBar.enableZoomIn();
                view.topTool.enableZoomIn();

                // --REFURBISH REQUIRED -- //
            } else {
                JOptionPane.showMessageDialog(null, "SVG Image could not be rendered. Please select another SVG file.",
                                              "SVG File Corrupted", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void setZoomOut(boolean flag) {
        view.menuBar.setZoomOut(flag);
        view.topTool.setZoomOut(flag);
    }
}