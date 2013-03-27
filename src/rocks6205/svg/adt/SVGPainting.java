package rocks6205.svg.adt;

/**
 * The <code>SVGPainting</code> class is used to parse <code>paint</code> attribute in
 * SVG documents and convert to respective type and color to be drawn on canvas.<p>
 * Refer to: <a href="http://www.w3.org/TR/SVG/painting.html#SpecifyingPaint">Scalable Vector Graphics
 * (SVG) 1.1 - Specifying paint</a>
 * @author Cheow Yeong Chi
 *
 * @since 1.0
 *
 */
public class SVGPainting {

    /*
     * PROPERTIES
     */

    /**
     * Paint color of current instance
     */
    SVGColorScheme paintColor;

    /**
     * Paint type of current instance
     */
    SVGPaintingType paintType;

    /**
     * Set current <code>SVGPainting</code> instance to type <code>COLOR</code>.
     *
     * @param c Paint color
     */
    public SVGPainting(SVGColorScheme c) {
        setPaint(c, SVGPaintingType.COLOR);
    }

    /*
     * CONSTRUCTORS
     */

    /**
     * Set current <code>SVGPainting</code> instance to type <code>type</code>.
     *
     * @param type Paint type
     */
    public SVGPainting(SVGPaintingType type) {
        setPaintType(type);
    }

    /*
     * MUTATOR
     */

    /**
     * @param c Paint color
     */
    public final void setPaintColor(SVGColorScheme c) {
        if (c != null) {
            this.paintColor = c;
        }
    }

    /**
     * Set current <code>SVGPainting</code> instance to <code>type</code>, if paint color is not of type
     * <code>NONE</code> then set the paint color to default transparent.
     *
     * @param type Paint type
     */
    public final void setPaintType(SVGPaintingType type) {
        if (type != null) {
            this.paintType = type;
        }

        if (type == SVGPaintingType.NONE) {
            this.paintColor = new SVGColorScheme(0, 0, 0, 0);
        }
    }

    /*
     * ACCESSOR
     */

    /**
     * @return Paint color
     */
    public SVGColorScheme getPaintColor() {
        return this.paintColor;
    }

    /**
     * @return Paint type
     */
    public SVGPaintingType getPaintType() {
        return this.paintType;
    }

    /*
     * METHODS
     */

    /**
     * Parsing paint attribute into types and color that matches the defined types that matches.
     *
     * @param paintAttributeString Attribute string that is read directly from SVG file
     * @return <code>SVGPainting</code> object
     */
    public static SVGPainting parse(String paintAttributeString) {
        paintAttributeString = paintAttributeString.trim();

        if (paintAttributeString.equalsIgnoreCase(SVGPaintingType.NONE.name())) {
            return new SVGPainting(SVGPaintingType.NONE);
        } else if (paintAttributeString.equalsIgnoreCase(SVGPaintingType.CURRENTCOLOR.name())) {
            return new SVGPainting(SVGPaintingType.CURRENTCOLOR);
        } else {
            SVGColorScheme color = SVGColorScheme.parse(paintAttributeString);

            if (color != null) {
                return new SVGPainting(color);
            }
        }

        return null;
    }

    /**
     * Set current <code>SVGPainting</code> instance to color <code>c</code> and type <code>type</code>
     * @param c Paint color
     * @param type Paint type
     */
    public final void setPaint(SVGColorScheme c, SVGPaintingType type) {
        setPaintType(type);

        if (type == SVGPaintingType.COLOR) {
            setPaintColor(c);
        }
    }

    /**
     * Variant. Setting the color of paint.
     *
     * @param c Paint color
     */
    public final void setPaint(SVGColorScheme c) {
        setPaint(c, SVGPaintingType.COLOR);
    }
}
