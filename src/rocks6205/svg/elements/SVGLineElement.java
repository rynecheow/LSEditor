/**
 * 
 * Class: SVGLineElement
 * Description: 
 * 
 * @author: Sugar CheeSheen Chan
 * @version: 1.0
 * @date: 14/03/2013
 * 
 */

package rocks6205.svg.elements;

public class SVGLineElement extends SVGGraphicsElement {
	
	// Instance fields
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	
	/**
	 * 
	 * @param x1 contains value for field x1
	 * @param y1 contains value for field y1
	 * @param x2 contains value for field x2
	 * @param y2 contains value for field y2
	 * @param stroke contains value for field stroke
	 * @param strokeWidth contains value for field strokeWidth
	 */
	public SVGLineElement( int x1 , int y1 , int x2 , int y2 , 
			String stroke , String strokeWidth ) {
		super( stroke , strokeWidth );
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	/**
	 * Mutator for field x1
	 * 
	 * @param x1 contains value for field x1
	 */
	public void setX( int x1 ) {
		this.x1 = x1;
	}

	/**
	 * Mutator for field y1
	 * 
	 * @param y1 contains value for field y1
	 */
	public void setY( int y1 ) {
		this.y1 = y1;
	}

	/**
	 * Mutator for field x2
	 * 
	 * @param x2 contains value for field x2
	 */
	public void setWidth( int x2 ) {
		this.x2 = x2;
	}
	
	/**
	 * Mutator for field y2
	 * 
	 * @param y2 contains value for field y2
	 */
	public void setY2( int y2 ) {
		this.y2 = y2;
	}
	
	/**
	 * Accessor for field x1
	 * 
	 * @return Value of x1
	 */
	public int getX1() {
		return this.x1;
	}
	
	/**
	 * Accessor for field y1
	 * 
	 * @return Value of y1
	 */
	public int getY1() {
		return this.y1;
	}
	
	/**
	 * Accessor for field x2
	 * 
	 * @return Value of x2
	 */
	public int getX2() {
		return this.x2;
	}
	
	/**
	 * Accessor for field y2
	 * 
	 * @return Value of y2
	 */
	public int getY2() {
		return this.y2;
	}
}
