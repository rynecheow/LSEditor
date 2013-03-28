package rocks6205.svgFamily;

/**
 *
 * Describes the environment where the canvas will run on, focusing on output resolution and text size.
 *
 * @author Cheow Yeong Chi
 * @since 1.3
 *
 */
public class SVGCanvasProperties {

    /*
     * PROPERTIES
     */

    /**
     * Text size
     */
    private static float TEXT_SIZE = 10;

    /**
     * Resolution in DPI
     */
    private static int DOTS_PER_INCH = 72;

    /*
     * CONSTRUCTOR
     */

    /**
     * Default constructor
     */
    private SVGCanvasProperties() {}

    /*
     * ACCESSORS
     */

    /**
     * @return Resolution in DPI
     */
    public static int getOutputResolution() {
        return DOTS_PER_INCH;
    }

    /**
     * @return Text size
     */
    public static float getFontSize() {
        return TEXT_SIZE;
    }

    /*
     * MUTATORS
     */

    /**
     * @param dpi Resolution in DPI
     */
    public static void setOutputResolution(int dpi) {
        DOTS_PER_INCH = dpi;
    }

    /**
     * @param fontSize Text size
     */
    public static void setFontSize(float textSize) {
        TEXT_SIZE = textSize;
    }
}