/**
 * 
 * Class: SVGColorScheme
 * Description: Parsing <color> in SVG documents and convert to standard RGB Color scheme readable by Java
 * 
 * @author: Cheow Yeong Chi
 * @date: 15/03/2013
 * 
 */

package rocks6205.svg.adt;

import java.awt.Color;
import java.util.regex.*;

public class SVGColorScheme extends Color {
	/*
	 * PRIMITIVE DATA STRUCTURE FORMAT
	 */
	//	8-bit integer regex
	public static final String _8_BIT_UINT = "([01]?[0-9]?[0-9]|2[0-4][0-9]|25[0-5])";	//<color> as declared in 4.2

	// 	percentage regex
	// 	e.g. 99%, 18.4%,  100%
	public static final String PERCENTAGE_UNUM = "([01]?[0-9]?[0-9](.[0-9]+)?)%";	//<color> as declared in 4.2

	//	8-bit hex integer regex
	public static final String _8_BIT_HEX_UINT = "([0-9A-Fa-f]{2})"; //<color> as declared in 4.2
	//	8-bit hex integer regex
	public static final String _4_BIT_HEX_UINT = "([0-9A-Fa-f])";	//<color> as declared in 4.2

	/*
	 * COLOR DATA STRUCTURE FORMAT
	 */
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

	/*
	 * CONSTRUCTOR
	 */
	public SVGColorScheme(int red, int green, int blue) {
		super(red, green, blue);
	}

	/*
	 * METHODS
	 */
	/**
	 * Parsing color attribute to convert to Java-compatible color type where matches formats.
	 * 
	 * @param colorAttributeString	Attribute string that is read directly from SVG file
	 * @return Color in RGB format which Java reads
	 */
	public static SVGColorScheme parse(String colorAttributeString){
		colorAttributeString = colorAttributeString.trim();

		//set matcher to match specific type
		Matcher hexMatcher = Pattern.compile(HEXA_COLOR,
				Pattern.CASE_INSENSITIVE).matcher(colorAttributeString);
		Matcher rgbUintMatcher = Pattern.compile(RGB_COLOR_UINT,
				Pattern.CASE_INSENSITIVE).matcher(colorAttributeString);
		Matcher rgbPercentileMatcher = Pattern.compile(RGB_PERCENTILE,
				Pattern.CASE_INSENSITIVE).matcher(colorAttributeString);

		if (rgbUintMatcher.matches()) {
			int[] rgbColor = new int[3];
			String segment;

			for (int u = 0; u < 3; u++) {
				segment = rgbUintMatcher.group(u + 1);
				rgbColor[u] = Integer.parseInt(segment);
			}
			return new SVGColorScheme(rgbColor[0], rgbColor[1], rgbColor[2]);
		} else if (rgbPercentileMatcher.matches()) {
			int[] rgbColor = new int[3];
			String segment;

			for (int u = 0; u < 3; u++) {
				segment = rgbPercentileMatcher.group(1 + 2 * u);
				rgbColor[u] = (int) (Double.parseDouble(segment) / 100 * 255);
			}

			return new SVGColorScheme(rgbColor[0], rgbColor[1], rgbColor[2]);
		} else if (hexMatcher.matches()) {
			return decodeHex(colorAttributeString);
		} else if (getColorFromKeyword(colorAttributeString) != null) {
			return getColorFromKeyword(colorAttributeString);
		}
		return null;
	}

	/**
	 * Converts color keywords into RGB Format which Java reads.
	 * 
	 * @param colorAttributeString	Attribute string that is read directly from SVG file
	 * @return Color in RGB format which Java reads
	 */
	public static SVGColorScheme getColorFromKeyword(
			String colorAttributeString) {
		colorAttributeString = colorAttributeString.toUpperCase();
		for (SVGColorKeyword keyword : SVGColorKeyword.values()) {
			if (keyword.name().equals(colorAttributeString)) {
				return keyword.getSVGColorScheme();
			}
		}
		return null;
	}

	/**
	 * Decode hexadecimal color format
	 * 
	 * @param colorAttributeString	Attribute string that is read directly from SVG file
	 * @return Color in RGB format which Java reads
	 */
	private static SVGColorScheme decodeHex(String hexColorString) {
		try {
			boolean isShortHexaString = Pattern.compile(
					"([A-Fa-f0-9])([A-Fa-f0-9])([A-Fa-f0-9])").matcher(
							hexColorString.substring(1)).matches();
			//extend if needed
			if(isShortHexaString){
				hexColorString = extendHex(hexColorString);
			}

			Color color = Color.decode(hexColorString);

			return new SVGColorScheme(color.getRed(), color.getGreen(),
					color.getBlue());
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
			return null;
		}

	}
	/**
	 * Convert hexadecimal strings that are in 3 digit format into 6-digit format for Color
	 * class to decode
	 * 
	 * @param hexColorString	Short hexadecimal string
	 * @return	Extended hexadecimal string 
	 */
	private static String extendHex(String hexColorString) {
		String extendedHexGroup = "", shortHexInt;
		Matcher shortHexaStringMatcher = Pattern.compile(
				"([A-Fa-f0-9])([A-Fa-f0-9])([A-Fa-f0-9])").matcher(
						hexColorString.substring(1));
		for (int u = 0; u < 3; u++) {
			shortHexInt = shortHexaStringMatcher.group(u + 1);
			//extend by appending
			extendedHexGroup += shortHexInt + shortHexInt;
		}
		//replace short hex with extended hex
		hexColorString = hexColorString.replace(shortHexaStringMatcher.group(), extendedHexGroup);
		return hexColorString;
	}


}