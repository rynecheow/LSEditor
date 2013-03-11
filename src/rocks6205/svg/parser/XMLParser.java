package rocks6205.svg.parser;
import javax.xml.parsers.*;

import java.io.IOException;
import java.io.StringReader;

import org.w3c.dom.Document;
import org.xml.sax.*;

public class XMLParser {

	private XMLParser() {
		// TODO Auto-generated constructor stub
	}

	public static Document parseXml(InputSource XMLInputSource){
		Document doc = null;		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			// Prevent loading external DTD
			builder.setEntityResolver(new EntityResolver() {
				public InputSource resolveEntity(String publicId,
						String systemId) throws SAXException, IOException {
					return new InputSource(new StringReader(""));
				}
			});

			doc = builder.parse(XMLInputSource);
		} catch (IOException ioe) {
		} catch (SAXException sae) {
		} catch (ParserConfigurationException pce) {
		} catch (NullPointerException npe) {}

		return doc;
	}
}
