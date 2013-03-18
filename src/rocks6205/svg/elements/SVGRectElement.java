/**
 * 
 * Class: SVGRectElement
 * Description: 
 * 
 * @author: Sugar CheeSheen Chan
 * @version: 1.0
 * @date: 15/03/2013
 * 
 */

package rocks6205.svg.elements;

public class SVGRectElement extends SVGGraphicsElement {
	
	// Instance fields
	private int x;
	private int y;
	private int width;
	private int height;
	private String fill;
	
	/**
	 * 
	 * @param x contains value for field x
	 * @param y contains value for field y
	 * @param width contains value for field width
	 * @param height contains value for field height
	 * @param fill contains value for field fill
	 * @param stroke contains value for field stroke
	 * @param strokeWidth contains value for field strokeWidth
	 */
	public SVGRectElement( int x , int y , int width , int height ,
			String fill , String stroke , String strokeWidth ) {
		super( stroke , strokeWidth );
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.fill = fill;
	}
	
	/**
	 * Mutator for field x
	 * 
	 * @param x contains value for field x
	 */
	public void setX( int x ) {
		this.x = x;
	}

	/**
	 * Mutator for field y
	 * 
	 * @param y contains value for field y
	 */
	public void setY( int y ) {
		this.y = y;
	}

	/**
	 * Mutator for field width
	 * 
	 * @param width contains value for field width
	 */
	public void setWidth( int width ) {
		this.width = width;
	}
	
	/**
	 * Mutator for field height
	 * 
	 * @param height contains value for field height
	 */
	public void setHeight( int height ) {
		this.height = height;
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
	 * Accessor for field x
	 * 
	 * @return Value of x
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * Accessor for field y
	 * 
	 * @return Value of y
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * Accessor for field width
	 * 
	 * @return Value of width
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * Accessor for field height
	 * 
	 * @return Value of height
	 */
	public int getHeight() {
		return this.height;
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
