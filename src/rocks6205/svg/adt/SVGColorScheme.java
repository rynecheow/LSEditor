package rocks6205.svg.adt;

import java.awt.Color;

public class SVGColorScheme extends Color {
	/*PRIMITIVE DATA STRUCTURE FORMAT*/
	//	8-bit integer regex
	public static final String _8_BIT_UINT = "([01]?[0-9]?[0-9]|2[0-4][0-9]|25[0-5])";	
	
	// 	percentage regex
	// 	e.g. 99%, 18.4%,  100%
	public static final String PERCENTAGE_UNUM = "([01]?[0-9]?[0-9](.[0-9]+)?)%";		
	
	//	8-bit hex integer regex
	public static final String _8_BIT_HEX_UINT = "([0-9A-Fa-f]{2})";
	
	//	8-bit hex integer regex
	public static final String _4_BIT_HEX_UINT = "([0-9A-Fa-f])";	
	
	/*COLOR DATA STRUCTURE FORMAT*/
	//	Standard color format
	//	e.g. RGB(120,83,22)
	public static final String RGB_COLOR_UINT = "^RGB\\(\\s*"
			+ SVGColorScheme._8_BIT_UINT + "\\s*,\\s*"
			+ SVGColorScheme._8_BIT_UINT + "\\s*,\\s*"
			+ SVGColorScheme._8_BIT_UINT + "\\s*\\)$";
	
	//	Standard color format with percentile format
	//	e.g. RGB(70%,62%,3%)
	public static final String RGB_PERCENTILE = "^RGB\\(\\s*"
			+ SVGColorScheme.PERCENTAGE_UNUM + "\\s*,\\s*"
			+ SVGColorScheme.PERCENTAGE_UNUM + "\\s*,\\s*"
			+ SVGColorScheme.PERCENTAGE_UNUM + "\\s*\\)$";
	
	//	Hexadecimal format
	// 	e.g. #123abc , #fff 
	public static final String HEXA_COLOR = "#" + "("
			+ SVGColorScheme._8_BIT_HEX_UINT + "{3}|"
			+ SVGColorScheme._4_BIT_HEX_UINT + "{3})";
	
	private static final long serialVersionUID = 5839823140108294927L;

	public SVGColorScheme(int red, int green, int blue) {
		super(red, green, blue);
		// TODO Auto-generated constructor stub
	}
}