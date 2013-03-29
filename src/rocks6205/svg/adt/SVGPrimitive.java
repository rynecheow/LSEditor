package rocks6205.svg.adt;

/**
 * Primitive regular expression use for building patterns to match attributes in the SVG
 * documents.<p>
 * Includes Regular Expression Atomic Unit (REGEXAU) of some attributes.
 *
 * @author Cheow Yeong Chi
 *
 * @since 1.1
 */
public interface SVGPrimitive {

    /*
     * PROPERTIES
     */

    /**
     * Integer type REGEXAU
     */
    public static final String INT = "[+-]?[0-9]+";

    /**
     * Number type REGEXAU
     */
    public static final String NUM = "(" + INT + "([Ee]" + INT + ")?|[+-]?[0-9]*\\.[0-9]+([Ee]" + INT + ")?)";

    /*
     * TRANSFORM PROPERTIES
     */

    /**
     * Comma and whitespace
     */
    public static final String COMMA_WSP = "\\s*(,|\\s)\\s*";

    /**
     * Translate string regular expression
     */
    public static final String TRANSLATE = "translate\\s*\\(\\s*(" + NUM + ")(" + COMMA_WSP + "(" + NUM + "))?\\s*\\)";

    /*
     * COLOR REGEXAU
     */

    /**
     * 4 bit hexadecimal unsigned integer REGEXAU
     */
    public static final String _4_BIT_HEX_UINT = "[0-9A-Fa-f]";

    /**
     * 8 bit unsigned integer
     */
    public static final String _8_BIT_UINT = "\\s*-?\\d+\\s*";

    /**
     * Percentage unsigned number
     */
    public static final String PERCENTAGE_UNUM = "\\s*-?\\d+%\\s*";
}