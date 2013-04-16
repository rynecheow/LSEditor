package rocks6205.svg.util;

/**
 * @author Komalah
 *
 */
public class SVGParserUtils {
    public static Float parseFloatAttribute(final String pString) {
        if (pString == null) {
            return null;
        } else {
            try {
                if (pString.endsWith("px")) {
                    return Float.parseFloat(pString.substring(0, pString.length() - 2));
                } else {
                    return Float.parseFloat(pString);
                }
            } catch (final NumberFormatException nfe) {
                return null;
            }
        }
    }
}