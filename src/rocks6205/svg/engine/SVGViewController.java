/**
 * 
 * Class: SVGViewController
 * Description: 
 * 
 * @author: Cheow Yeong Chi
 * @date: 14/03/2013
 * 
 */

package rocks6205.svg.engine;
import java.io.File;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import rocks6205.svg.elements.SVGSVGElement;
import rocks6205.svgParser.XMLParser;

public class SVGViewController{
	/*
	 * PROPERTIES
	 */
	private SVGModel model;
	private SVGView view;

	/*
	 * CONSTRUCTOR
	 */
	public SVGViewController(SVGModel model, SVGView view) {
		setModel(model);
		setView(view);
	}

	/*
	 * ACCESSORS
	 */
	public SVGView getView() {
		return view;
	}

	public SVGModel getModel() {
		return model;
	}

	/*
	 * MUTATORS
	 */
	public void setModel(SVGModel model) {
		this.model = model;
	}


	public void setView(SVGView view) {
		this.view = view;
	}


	/*
	 * METHODS
	 */

	public void fileLoad(File file) {
		if (file != null) {
			String path = file.toURI().toString();
			Document doc = null;

			try{
				doc = XMLParser.parseXml(new InputSource(path));
			}catch(NullPointerException npe){
				npe.printStackTrace();
			}

			if (doc != null) {
				SVGSVGElement svg_e = SVGSVGElement.parseDocument(doc);
				model.setSVGElement(svg_e);
				model.render();
			}
		}
	}
}
