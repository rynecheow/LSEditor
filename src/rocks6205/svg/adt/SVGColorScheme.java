package rocks6205.svg.adt;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Parsing <code>color</code> tags in SVG documents and convert to standard RGB Color scheme readable by Java. <p>
 * Currently supported conversions formats are rgb() an
 * 
 * @author: Cheow Yeong Chi
 * @date: 15/03/2013
 * 
 */
public class SVGColorScheme extends Color {

    private static final long serialVersionUID = 5839823140108294927L;

    /*
     * COLOR DATA STRUCTURE FORMAT
     */
    /**
     *  Regular expression for standard color format<br>
     *  e.g. RGB(120,83,22)<br>
     *  e.g. RGB(70%,62%,3%)
     */
    public static final String RGB_COLOR = "rgb\\((" + SVGPrimitive._8_BIT_UINT + ","
	    + SVGPrimitive._8_BIT_UINT + "," + SVGPrimitive._8_BIT_UINT + "|" + SVGPrimitive.PERCENTAGE_UNUM
	    + "," + SVGPrimitive.PERCENTAGE_UNUM + "," + SVGPrimitive.PERCENTAGE_UNUM + ")\\)";

    //	
    // 	
    /**
     *  Regular expression for hexadecimal color format<br>
     *  e.g. #123abc , #fff
     */
    public static final String HEXA_COLOR = "#" + "(" + SVGPrimitive._4_BIT_HEX_UINT + "{3}){1,2}";


    /**
     * Enumerates color keyword names recognized by data type <code>color</code> in SVG as specified in 4.4<p>
     * Refer to : <a href="http://www.w3.org/TR/SVG/types.html#ColorKeywords">Scalable Vector Graphics (SVG) 1.1 - Color Keywords</a>
     * 
     * @author Sugar CheeSheen Chan
     * @date 13/03/2013
     * 
     * @since 1.0
     */
    private static enum SVGColorKeyword {

	// List of color keywords
	ALICEBLUE( 240, 248, 255 ),
	ANTIQUEWHITE( 250, 235, 215 ),
	AQUA(  0, 255, 255 ),
	AQUAMARINE( 127, 255, 212 ),
	AZURE( 240, 255, 255 ),
	BEIGE( 245, 245, 220 ),
	BISQUE(255, 228, 196),
	BLACK( 0, 0, 0 ),
	BLANCHEDALMOND( 255, 235, 205 ),
	BLUE( 0, 0, 255 ),
	BLUEVIOLET( 138, 43, 226 ),
	BROWN( 165, 42, 42 ),
	BURLYWOOD( 222, 184, 135 ),
	CADETBLUE( 95, 158, 160 ),
	CHARTREUSE( 127, 255, 0 ),
	CHOCOLATE( 210, 105, 30 ),
	CORAL( 255, 127, 80 ),
	CORNFLOWERBLUE( 100, 149, 237 ),
	CORNSILK( 255, 248, 220 ),
	CIRMSON( 220, 20, 60 ),
	CYAN( 0, 255, 255 ),
	DAKRBLUE( 0, 0, 139 ),
	DARKCYAN( 0, 139, 139 ),
	DAKRGOLDENROD( 184, 134, 11 ),
	DARKGRAY( 169, 169, 169 ),
	DARKGREEN( 0, 100, 0 ),
	DARKGREY( 169, 169, 169 ),
	DARKKHAKI( 189, 183, 107 ),
	DARKMAGENTA( 139, 0, 139 ),
	DARKOLIVEGREEN( 85, 107, 47 ),
	DARKORANGE( 255, 140, 0 ),
	DARKORCHID( 153, 50, 204 ),
	DARKRED( 139, 0, 0 ),
	DARKSALMON( 233, 150, 122 ),
	DARKSEAGREEN( 143, 188, 143 ),
	DARKSLATEBLUE( 72, 61, 139 ),
	DAKRSLATEGRAY( 47, 79, 79 ),
	DARKSLATEGREY( 47, 79, 79 ),
	DARKTURQUOSE( 0, 206, 209 ),
	DARKVIOLET( 148, 0, 211 ),
	DEEPPINK(255, 20, 147 ),
	DEEPSKYBLUE( 0, 191, 255 ),
	DIMGRAY(105, 105, 105 ),
	DIMGREY(105, 105, 105 ),
	DODGERBLUE( 30, 144, 255 ),
	FIREBRICK( 178, 34, 34 ),
	FLORALWHITE( 255, 250, 240 ),
	FORESTGREEN( 34, 139, 34 ),
	FUCHSIA( 255, 0, 255 ),
	GAINSBORO( 220, 220, 220 ),
	GHOSTWHITE( 248, 248, 255 ),
	GOLD(255, 215, 0 ),
	GOLDENROD( 218, 165, 32 ),
	GRAY( 128, 128, 128 ),
	GREY( 128, 128, 128 ),
	GREEN( 0, 128, 0 ),
	GREENYELLOW( 173, 255, 47 ),
	HONEYDEW( 240, 255, 240 ),
	HOTPINK( 255, 105, 180 ),
	INDIANRED( 205, 92, 92 ),
	INDIGO( 75, 0, 130 ),
	IVORY( 255, 255, 240 ),
	KHAKI( 240, 230, 140 ),
	LAVENDER( 230, 230, 250 ),
	LAVENDERBLUSH( 255, 240, 245 ),
	LAWNGREEN( 124, 252, 0 ),
	LEMONCHIFFON( 255, 250, 205 ),
	LIGHTBLUE( 173, 216, 230 ),
	LIGHTCORAL( 240, 128, 128 ),
	LIGHTCYAN( 224, 255, 255 ),
	LIGHTGOLDENYELLOW( 250, 250, 210 ),
	LIGHTGRAY( 211, 211, 211 ),
	LIGHTGREEN( 144, 238, 144 ),
	LIGHTGREY( 211, 211, 211 ),
	LIGHTPINK( 255, 182, 193 ),
	LIGHTSALMON( 255, 160, 122 ),
	LIGHTSEAGREEN( 32, 178, 170 ),
	LIGHSKYBLUE( 135, 206, 250 ),
	LIGHTSLATEGRAY( 119, 136, 153 ),
	LIGHTSLATEGREY( 119, 136, 153 ),
	LIGHTSTEELBLUE( 176, 196, 222 ),
	LIGHTYELLOW( 255, 255, 224 ),
	LIME( 0, 255, 0 ),
	LIMEGREEN( 50, 205, 50),
	LINEN( 250, 240, 230 ),
	MAGENTA( 255, 0, 255 ),
	MAROON( 128, 0, 0 ),
	MEDIUMAQUAMARINE( 102, 205, 170 ),
	MEDIUMBLUE( 0, 0, 205),
	MEDIUMORCHID( 186, 85, 211 ),
	MEDIUMPURPLE( 147, 112, 219 ),
	MEDIUMSEAGREEN( 60, 179, 113 ),
	MEDIUMSLATEBLUE( 123, 104, 238 ),
	MEDIUMSPRINGGREEN( 0, 250, 154 ),
	MEDIUMTURQUOISE( 72, 209, 204 ),
	MEDIUMVIOLETERED( 199, 21, 133 ),
	MIDNIGHTBLUE( 25, 25, 112 ),
	MINTCREAM( 245, 255, 250 ),
	MISTYROSE( 255, 228, 225 ),
	MOCCASIN( 255, 228, 181 ),
	NAVAJOWHITE( 255, 222, 173 ),
	NAVY( 0, 0, 128 ),
	OLDLACE( 253, 245, 230 ),
	OLIVE(128, 128, 0 ),
	OLIVEDRAB( 107, 142, 35 ),
	ORANGE( 255, 165, 0 ),
	ORANGERED( 255, 69, 0 ),
	ORCHID( 218, 112, 214 ),
	PALEGOLDENROD( 238, 232, 170 ),
	PALEGREEN( 152, 251, 152 ),
	PALETURQUOISE( 175, 238, 238 ),
	PALEVIOLETERED( 219, 112, 147 ),
	PAPAYAWHIP( 255, 239, 213 ),
	PEACHPUFF( 255, 218, 185 ),
	PERU( 205, 133, 63 ),
	PINK( 255, 192, 203 ),
	PLUM(221, 160, 221 ),
	POWDERBLUE( 176, 224, 230 ),
	PURPLE( 128, 0, 128 ),
	RED( 255, 0, 0 ),
	ROSYBROWN( 188, 143, 143 ),
	ROYALBLUE( 65, 105, 225 ),
	SADDLEBROWN( 139, 69, 19 ),
	SALMON(250, 128, 114),
	SANDYBROWN( 244, 164, 96 ),
	SEAGREEN( 46, 139, 87 ),
	SEASHELL( 255, 245, 238 ),
	SIENNA( 160, 82, 45 ),
	SILVER( 192, 192, 192 ),
	SKYBLUE( 135, 206, 235 ),
	SLATEBLUE( 106, 90, 205 ),
	SLATEGRAY( 112, 128, 144 ),
	SLATEGREY( 112, 128, 144 ),
	SNOW( 255, 250, 250 ),
	SPRINGGREEN( 0, 255, 127 ),
	STEELBLUE( 70, 130, 180 ),
	TAN( 210, 180, 140 ),
	TEAL( 0, 128, 128 ),
	THISTLE( 216, 191, 216 ),
	TOMATO( 255, 99, 71 ),
	TURQUOISE( 64, 224, 208 ),
	VIOLET( 238, 130, 238 ),
	WHEAT( 245, 222, 179 ),
	WHITE( 255, 255, 255 ),
	WHITESMOKE( 245, 245, 245 ),
	YELLOW( 255, 255, 0 ),
	YELLOWGREEN( 154, 205, 50 );

	/*
	 * PROPERTIES
	 */
	private final int red;
	private final int green;
	private final int blue;

	/*
	 * CONSTRUCTOR
	 */
	/**
	 * Creates <code>SVGColorKeyword</code> from specified value of <code>r</code> , <code>g</code>, and <code>b</code> from the color keywords
	 * 
	 * @param r Red integer value 
	 * @param g Green integer value
	 * @param b Blue integer value 
	 */
	SVGColorKeyword( int r , int g , int b ) {
	    this.red = r;
	    this.green = g;
	    this.blue = b;
	}

	/*
	 * METHODS
	 */

	/**
	 * Returns a <code>SVGColorScheme</code> from a color keyword as listed in {@link SVGColorKeyword} enumerator provided
	 * 
	 * @return <code>SVGColorScheme</code> value in respective specified value of <code>r</code> , <code>g</code>, and <code>b</code>
	 */
	public SVGColorScheme getSVGColorScheme(){
	    return new SVGColorScheme(red, green, blue);
	}
    }

    /*
     * CONSTRUCTOR
     */
    /**
     * Creates <code>SVGColorScheme</code> from specified value of <code>red</code> , <code>green</code>, 
     * and <code>blue</code> values
     * 
     * @param red Red integer value 
     * @param green Green integer value
     * @param blue Blue integer value 
     */
    public SVGColorScheme(int red, int green, int blue) {
	super(red, green, blue);
    }

    /**
     * Creates <code>SVGColorScheme</code> from specified value of <code>red</code> , <code>green</code>, 
     * <code>blue</code> and <code>alpha</code> values
     * 
     * @param red Red integer value 
     * @param green Green integer value
     * @param blue Blue integer value 
     * @param alpha Alpha integer value
     */
    public SVGColorScheme(int red, int green, int blue, int alpha) {
	super(red,green,blue,alpha);
    }

    /*
     * METHODS
     */
    /**
     * Parsing color attribute to convert to Java-compatible color type where matches formats.
     * 
     * 
     * @param colorAttributeString	Attribute string that is read directly from SVG file
     * @return <code>SVGColorScheme</code> in RGB format
     */
    public static SVGColorScheme parse(String colorAttributeString){
	colorAttributeString = colorAttributeString.trim();
	SVGColorScheme color = getColorFromKeyword(colorAttributeString);
	if (color != null) {
	    return color;
	}

	Matcher hexMatcher = Pattern.compile(HEXA_COLOR,Pattern.CASE_INSENSITIVE).matcher(colorAttributeString);
	if (hexMatcher.matches()) {
	    return decodeHex(colorAttributeString);
	}

	Matcher rgbMatcher = Pattern.compile(RGB_COLOR,Pattern.CASE_INSENSITIVE).matcher(colorAttributeString);
	if (rgbMatcher.matches()) {
	    int[] rgb = new int[3];
	    String string = rgbMatcher.group(1).trim();
	    double multiplier = 1;

	    if (string.contains("%")) {
		string = string.replace("%", "");
		multiplier = 2.55;
	    }

	    String[] parts = string.split("\\s*,\\s*");

	    for (int u = 0; u < 3; u++) {
		rgb[u] = (int) (Integer.parseInt(parts[u]) * multiplier);
		rgb[u] = normaliseRGB(rgb[u]);
	    }

	    return new SVGColorScheme(rgb[0], rgb[1], rgb[2]);
	}

	return null;
    }

    /**
     * Sets the value for a color component to be in the range of 
     * valid color values (0-255).
     * If raw color value is greater than 255 (maximum), function returns 255.
     * If raw color value is less than 0 (minimum), function returns 0.
     * 
     * 
     * @param value Raw value from a color component
     * @return value Value after applying range
     */
    private static int normaliseRGB(int value) {
	if(value>255) return 255;
	if(value<0) return 0;
	return value;
    }

    /**
     * Convert color keywords to Java-compatible color type where matches formats.
     * 
     * @param colorAttributeString	Attribute string that is read directly from SVG file
     * @return <code>SVGColorScheme</code> in RGB format
     */
    public static SVGColorScheme getColorFromKeyword(String colorAttributeString) {
	colorAttributeString = colorAttributeString.toUpperCase();
	for (SVGColorKeyword keyword : SVGColorKeyword.values()) {
	    if (keyword.name().equals(colorAttributeString)) {
		return keyword.getSVGColorScheme();
	    }
	}
	return null;
    }

    /**
     * Decodes hexadecimal string, and extend if the string is in only 3 hexadecimal digit format 
     * 
     * @param hexColorString	Attribute string that is read directly from SVG file
     * @return <code>SVGColorScheme</code> in RGB format
     */
    private static SVGColorScheme decodeHex(String hexColorString) {
	try {
	    Color color = Color.decode(extendHex(hexColorString));
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
	Matcher shortHexaStringMatcher = Pattern.compile(SVGPrimitive._4_BIT_HEX_UINT + "{3}").matcher(
		hexColorString.substring(1));
	if (shortHexaStringMatcher.matches()) {
	    String shortHex = shortHexaStringMatcher.group(), prefix = "#";
	    char c;

	    for (int i = 0; i < 3; i++) {
		c = shortHex.charAt(i);
		prefix += "" + c + c;
	    }

	    return prefix;
	}
	return hexColorString;
    }
}