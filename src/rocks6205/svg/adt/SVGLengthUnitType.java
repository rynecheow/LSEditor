package rocks6205.svg.adt;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.svg.properties.SVGCanvasProperties;

/**
 * <p>List of SVG length types</p>
 * Enumerates length unit type symbols recognized by data type <code>length</code> in SVG as defined in 4.5.11 of CSS2 BDT<p>
 * Refer to: <a href="http://www.w3.org/TR/SVG/types.html#InterfaceSVGLength">Scalable Vector Graphics
 * (SVG) 1.1 - Interface SVG Length</a>
 *
 * @author: Cheow Yeong Chi
 *
 * @since 1.0
 */
public enum SVGLengthUnitType {
    UNKNOWN(null), NUMBER(null), EMS("em"), EXS("ex"), PX("px"), CM("cm"), MM("mm"), IN("in"), PT("pt"), PC("pc");

    private final String symbol;

    SVGLengthUnitType(String symbol) {
        this.symbol = symbol;
    }

    public String getUnitSymbol() {
        return symbol;
    }

    /**
     * <p>This function gets the scaling factor according to the unit type specified. </p>
     * In CSS definition,<br>
     *
     * The 'ex' unit is defined by the font's 'x-height'. <br>
     * The x-height is so called because it is often equal to the height of the lowercase "x".
     * However, an 'ex' is defined even for fonts that don't contain an "x".<br>
     * <p>
     * 1 'em' is equal to the current font size. <br>
     * n 'em' means n times the size of the current font. <br>
     *
     * 1 'pt' is the same as 1/72 'in'<br>
     * 1 'pc' is the same as 12 'pt'<br>
     *
     * @return Scaling factor that the length unit symbol defined
     */
    public float getUnitScalingFactor() {

        // 'mm' is set as default unit as defined in 4.5.11
        float mm = 1.0f;
        float cm = 10.0f * mm;
        float in = 25.4f * mm;
        float pt = in / 72.0f;
        float pc = 12.0f * pt;
        float px = 0.75f * pt;
        float em = SVGCanvasProperties.getFontSize() * px;
        float ex = 0.5f * em;

        switch (this) {
        case MM :
            return mm;

        case CM :
            return cm;

        case IN :
            return in;

        case PT :
            return pt;

        case PC :
            return pc;

        case EMS :
            return em;

        case EXS :
            return ex;

        case NUMBER :
        case PX :
            return px;

        default :
            return 0;
        }
    }

    /*
     * METHODS
     */

    /**
     * Match the length unit type of the symbol with the defined symbols, it returns the respective
     * type if matches, returns <code>NUMBER</code> type if it doesn't
     *
     * @param symbol Unit symbol string
     * @return  <code>SVGLengthUnitType</code> symbolic constant
     */
    public static SVGLengthUnitType getType(String symbol) {
        for (SVGLengthUnitType type : values()) {
            if (symbol.equalsIgnoreCase(type.getUnitSymbol())) {
                return type;
            }
        }

        return SVGLengthUnitType.NUMBER;
    }
}