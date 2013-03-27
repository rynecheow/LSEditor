/**
 * 
 * Class: SVGPrimitive
 * Description: Standard data types
 * @author: Cheow Yeong Chi
 * @date: 16/03/2013
 * 
 */

package rocks6205.svg.adt;

public interface SVGPrimitive {

    /*
     * PROPERTIES
     */
    //<integer> as specified in 4.2
    public static final String INT = "[+-]?[0-9]+";
    //<number> as specified in 4.2
    public static final String NUM = "(" + INT + "([Ee]" + INT
	    + ")?|[+-]?[0-9]*\\.[0-9]+([Ee]" + INT + ")?)";
    public static final String COMMA_WSP = "\\s*(,|\\s)\\s*";
    public static final String TRANSLATE = "translate\\s*\\(\\s*(" + NUM
	    + ")(" + COMMA_WSP + "(" + NUM + "))?\\s*\\)";


    public static final String _4_BIT_HEX_UINT = "[0-9A-Fa-f]";
    public static final String _8_BIT_UINT = "\\s*-?\\d+\\s*";
    public static final String PERCENTAGE_UNUM = "\\s*-?\\d+%\\s*";
}
