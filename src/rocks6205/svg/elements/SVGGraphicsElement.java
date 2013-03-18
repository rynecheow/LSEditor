/**
 * 
 * Class: SVGGraphicsElement
 * Description: 
 * 
 * @author: Cheow Yeong Chi
 * @version: 1.0
 * @date: 14/03/2013
 * 
 */

package rocks6205.svg.elements;

public class SVGGraphicsElement {
	
	// Instance fields
	private String stroke;
	private String strokeWidth;

	public SVGGraphicsElement( String stroke , String strokeWidth ) {
		this.stroke= stroke;
		this.strokeWidth = strokeWidth;
	}
	
	/**
	 * Mutator for field stroke
	 * 
	 * @param stroke contains value for field stroke
	 */
	public void setStroke( String stroke ) {
		this.stroke = stroke;
	}
	
	/**
	 * Mutator for field strokeWidth
	 * 
	 * @param strokeWidth contains value for field strokeWidth
	 */
	public void setStrokeWidth( String strokeWidth ) {
		this.strokeWidth = strokeWidth;
	}
	
	/**
	 * Accessor for field stroke
	 * 
	 * @return Value of stroke
	 */
	public String getStroke() {
		return this.stroke;
	}
	
	/**
	 * Accessor for field strokeWidth
	 * 
	 * @return Value of strokeWidth
	 */
	public String getStrokeWidth() {
		return this.strokeWidth;
	}
}
