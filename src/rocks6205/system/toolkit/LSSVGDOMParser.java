package rocks6205.system.toolkit;

//~--- non-JDK imports --------------------------------------------------------

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import rocks6205.editor.core.LSEditor;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

//~--- JDK imports ------------------------------------------------------------

/**
 * Parsing a single XML source into a Document ready to be read by other classes.
 *
 * @author Cheow Yeong Chi
 * @since 1.0
 */
public class LSSVGDOMParser {

    /*
     * CONSTRUCTOR
     */

    /**
     * Default constructor
     */
    private LSSVGDOMParser() {
    }

    /*
     * METHODS
     */

    /**
     * Checks and build a document from input source and aware of namespaces.
     *
     * @param XMLInputSource input source
     * @return XML Document to be further processed.
     */
    public static Document parseXml(InputSource XMLInputSource) {
        Document doc = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        factory.setNamespaceAware(true);

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Prevent loading external DTD
            builder.setEntityResolver((publicId, systemId) -> new InputSource(new StringReader("")));
            doc = builder.parse(XMLInputSource);
        } catch (IOException | SAXException | ParserConfigurationException | NullPointerException e) {
            LSEditor.logger.warning(e.getMessage());
        }

        return doc;
    }
}