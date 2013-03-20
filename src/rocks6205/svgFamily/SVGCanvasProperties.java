/**
 * 
 * Class: SVGCanvasProperties
 * Description: Describes the environment where the canvas will run on, focusing on output resolution and text size.
 * 
 * @author: Cheow Yeong Chi
 * @date: 11/03/2013
 * 
 */
package rocks6205.svgFamily;

public class SVGCanvasProperties {
	/*
	 * PROPERTIES
	 */
	private static float TEXT_SIZE = 10;
	private static int DOTS_PER_INCH = 72;

	/*
	 * CONSTRUCTOR
	 */
	private SVGCanvasProperties() {
	}
	/*
	 * ACCESSORS
	 */
	public static int getOutputResolution() {
		return DOTS_PER_INCH;
	}
	
	public static float getFontSize() {
		return TEXT_SIZE;
	}
	/*
	 * MUTATORS
	 */
	public static void setOutputResolution(int dpi) {
		DOTS_PER_INCH = dpi;
	}

	public static void setFontSize(float fontSize) {
		TEXT_SIZE = fontSize;
	}
}