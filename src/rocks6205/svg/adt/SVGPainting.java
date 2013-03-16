/**
 * 
 * Class: SVGPainting
 * Description: Parsing <paint> in SVG documents and convert to floating points with respective type to be
 * 				presented on Java
 * 
 * @author: Cheow Yeong Chi
 * @version: 1.0
 * @date: 16/03/2013
 * 
 */

package rocks6205.svg.adt;

public class SVGPainting {
	/*
	 * PROPERTIES
	 */
	SVGColorScheme paintColor;
	SVGPaintingType paintType;
	
	/*
	 * CONSTRUCTORS
	 */
	public SVGPainting() {
		// TODO Auto-generated constructor stub
	}
	
	public SVGPainting(SVGColorScheme color){
		
	}
	
	/*
	 * MUTATOR
	 */
	
	public final void setPaintColor(SVGColorScheme c){
		this.paintColor = c;
	}

	/*
	 * ACCESSOR
	 */
	
	/*
	 * METHODS
	 */
}
