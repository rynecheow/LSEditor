package rocks6205.svg.editor.controllers;

import java.io.File;
import java.io.IOException;

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

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import rocks6205.svg.adt.SVGLengthUnit;
import rocks6205.svg.adt.SVGPainting;
import rocks6205.svg.elements.SVGCircleElement;
import rocks6205.svg.elements.SVGContainerElement;
import rocks6205.svg.elements.SVGGElement;
import rocks6205.svg.elements.SVGGenericElement;
import rocks6205.svg.elements.SVGLineElement;
import rocks6205.svg.elements.SVGRectElement;
import rocks6205.svg.elements.SVGSVGElement;
import rocks6205.svg.mvc.SVGEditorModel;
import rocks6205.svg.parser.XMLParser;
import rocks6205.svg.properties.SVGImageCanvas;

public interface SVGEditorFileController {

    
    /**
     * Loads file from two ways:<p>
     * 1) Read directly from <code>JFileChooser</code>.<br>
     * 2) File from path string passed by command line argument.<p>
     *
     * File is then parsed into a <code>Document</code> object and rendered.
     *
     * @param file
     * @return If file is successfully loaded
     */
    public boolean fileLoad(File file);

    /**
     * Saves current file
     * @return True if file is successfully saved, false otherwise.
     * @throws IOException
     */
    public boolean saveFile() throws IOException;
    
    /**
     * 
     * @param file
     * @return
     * @throws IOException
     */
    public boolean saveFile(File file) throws IOException;
    
    /**
     * 
     */
    public void closeFile();
    
    public void endFileModification();
    
}
