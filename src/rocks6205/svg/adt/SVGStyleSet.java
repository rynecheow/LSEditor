
/**
 *
 */
package rocks6205.svg.adt;

//~--- JDK imports ------------------------------------------------------------

import java.util.HashMap;

/**
 * @author Komalah
 *
 */
public class SVGStyleSet {

    private final HashMap<String, String> mStyleMap = new HashMap<String, String>();

    
    public SVGStyleSet(final String pString) {
            final String[] styles = pString.split(";");
            for (final String s : styles) {
                    final String[] style = s.split(":");
                    if (style.length == 2) {
                            this.mStyleMap.put(style[0], style[1]);
                    }
            }
    }

    //Getter and setter

    public String getStyle(final String pStyleName) {
            return this.mStyleMap.get(pStyleName);
    }
}
