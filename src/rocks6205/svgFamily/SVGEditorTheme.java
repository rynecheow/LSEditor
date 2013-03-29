package rocks6205.svgFamily;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Color;

public class SVGEditorTheme {

    /**
     * Default master color
     */
    private static Color defaultMasterColor = new Color(0x0A3040);

    /**
     * Default constructor
     */
    public SVGEditorTheme(Color defaultMasterColor) {
        setDefaultMasterColor(defaultMasterColor);
    }

    /**
     * @return Default master color
     */
    public static Color getDefaultMasterColor() {
        return defaultMasterColor;
    }

    /**
     * @param defaultMasterColor Default master color
     */
    public static void setDefaultMasterColor(Color defaultMasterColor) {
        SVGEditorTheme.defaultMasterColor = defaultMasterColor;
    }
}