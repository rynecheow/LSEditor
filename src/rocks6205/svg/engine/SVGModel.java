/**
 * 
 * Class: SVGModel
 * Description: 
 * 
 * @author: Cheow Yeong Chi
 * @version: 1.0
 * @date: 14/03/2013
 * 
 */

package rocks6205.svg.engine;

import java.util.Observable;

import rocks6205.svg.elements.SVGSVGElement;
import rocks6205.svgFamily.SVGImageCanvas;

public class SVGModel extends Observable{

	private SVGSVGElement SVGElement;
	private SVGImageCanvas imageCanvas;

	public SVGModel() {
		// TODO Auto-generated constructor stub
	}


	/*
	 * ACCESSOR
	 */
	public SVGSVGElement getSVGElement() {
		return SVGElement;
	}

	public SVGImageCanvas getImageCanvas() {
		return imageCanvas;
	}
	/*
	 * MUTATORS
	 */
	public void setImageCanvas(SVGImageCanvas imageCanvas) {
		this.imageCanvas = imageCanvas;
	}

	public void setSVGElement(SVGSVGElement sVGElement) {
		SVGElement = sVGElement;
	}
	/*
	 * OVERRIDING
	 */
	public void addObserver(SVGView v) {

	}
}
