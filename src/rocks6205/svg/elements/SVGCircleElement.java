/**
 * 
 * Class: SVGCircleElement
 * Description: 
 * 
 * @author: Sugar CheeSheen Chan
 * @version: 1.0
 * @date: 15/03/2013
 * 
 */

package rocks6205.svg.elements;

public class SVGCircleElement extends SVGGraphicsElement {
	
	// Instance fields
	private int cx;
	private int cy;
	private int radius;
	private String fill;
	
	/**
	 * 
	 * @param cx contains value for field cx
	 * @param cy contains value for field cy
	 * @param radius contains value for field radius
	 * @param fill contains value for field fill
	 * @param stroke contains value for field stroke
	 * @param strokeWidth contains value for field strokeWidth
	 */
	public SVGCircleElement( int cx , int cy , int radius , 
			String fill , String stroke , String strokeWidth ) {
		super( stroke , strokeWidth );
		this.cx = cx;
		this.cy = cy;
		this.radius = radius;
		this.fill = fill;
	}
	
	/**
	 * Mutator for field cx
	 * 
	 * @param cx contains value for field cx
	 */
	public void setCx( int cx ) {
		this.cx = cx;
	}

	/**
	 * Mutator for field cy
	 * 
	 * @param cy contains value for field cy
	 */
	public void setCy( int cy ) {
		this.cy = cy;
	}

	/**
	 * Mutator for field radius
	 * 
	 * @param radius contains value for field radius
	 */
	public void setRadius( int radius ) {
		this.radius = radius;
	}
	
	/**
	 * Mutator for field fill
	 * 
	 * @param fill contains value for field fill
	 */
	public void setFill( String fill ) {
		this.fill = fill;
	}

	/**
	 * Accessor for field cx
	 * 
	 * @return Value of cx
	 */
	public int getCx() {
		return this.cx;
	}
	
	/**
	 * Accessor for field cy
	 * 
	 * @return Value of cy
	 */
	public int getCy() {
		return this.cy;
	}
	
	/**
	 * Accessor for field radius
	 * 
	 * @return Value of radius
	 */
	public int getRadius() {
		return this.radius;
	}
	
	/**
	 * Accessor for field fill
	 * 
	 * @return Value of fill
	 */
	public String getFill() {
		return this.fill;
	}
}
