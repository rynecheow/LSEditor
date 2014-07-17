package rocks6205.editor.model.adt;

//~--- JDK imports ------------------------------------------------------------

import java.awt.*;

/**
 * The <code>LSPainting</code> class is used to parse <code>paint</code> attribute in
 * SVG documents and convert to respective type and color to be drawn on canvas.<p>
 * Refer to: <a href="http://www.w3.org/TR/SVG/painting.html#SpecifyingPaint">Scalable Vector Graphics
 * (SVG) 1.1 - Specifying paint</a>
 *
 * @author Cheow Yeong Chi
 * @since 1.0
 */
public class LSPainting {

    /*
     * PROPERTIES
     */

    /**
     * Paint color of current instance
     */
    LSColor paintColor;

    /**
     * Paint type of current instance.
     */
    LSPaintingType paintType;

    /**
     * Set current <code>LSPainting</code> instance to type <code>COLOR</code>.
     *
     * @param c Paint color
     * @see #setPaint(rocks6205.editor.model.adt.LSColor, rocks6205.editor.model.adt.LSPaintingType)
     */
    public LSPainting(Color c) {
        setPaint(LSColor.createFromColor(c), LSPaintingType.COLOR);
    }

    /**
     * Set current <code>LSPainting</code> instance to type <code>COLOR</code>.
     *
     * @param c Paint color
     * @see #setPaint(rocks6205.editor.model.adt.LSColor, rocks6205.editor.model.adt.LSPaintingType)
     */
    public LSPainting(LSColor c) {
        setPaint(c, LSPaintingType.COLOR);
    }

    /*
     * CONSTRUCTORS
     */

    /**
     * Set current <code>LSPainting</code> instance to type <code>type</code>.
     *
     * @param type Paint type
     * @see #setPaintType(rocks6205.editor.model.adt.LSPaintingType)
     */
    public LSPainting(LSPaintingType type) {
        setPaintType(type);
    }

    /*
     * MUTATOR
     */

    /**
     * @param c Paint color
     */
    public final void setPaintColor(LSColor c) {
        if (c != null) {
            this.paintColor = c;
        }
    }

    /**
     * Set current <code>LSPainting</code> instance to <code>type</code>, if paint color is not of type
     * <code>NONE</code> then set the paint color to default transparent.
     *
     * @param type Paint type
     */
    public final void setPaintType(LSPaintingType type) {
        if (type != null) {
            this.paintType = type;
        }

        if (type == LSPaintingType.NONE) {
            this.paintColor = new LSColor(0, 0, 0, 0);
        }
    }

    /*
     * ACCESSOR
     */

    /**
     * @return Paint color
     */
    public LSColor getPaintColor() {
        return this.paintColor;
    }

    /**
     * @return Paint type
     */
    public LSPaintingType getPaintType() {
        return this.paintType;
    }

    /*
     * METHODS
     */

    /**
     * Parsing paint attribute into types and color that matches the defined types that matches.
     *
     * @param paintAttributeString Attribute string that is read directly from SVG file
     * @return <code>LSPainting</code> object
     */
    public static LSPainting parse(String paintAttributeString) {
        paintAttributeString = paintAttributeString.trim();

        if (paintAttributeString.equalsIgnoreCase(LSPaintingType.NONE.name())) {
            return new LSPainting(LSPaintingType.NONE);
        } else if (paintAttributeString.equalsIgnoreCase(LSPaintingType.CURRENTCOLOR.name())) {
            return new LSPainting(LSPaintingType.CURRENTCOLOR);
        } else {
            LSColor color = LSColor.parse(paintAttributeString);

            if (color != null) {
                return new LSPainting(color);
            }
        }

        return null;
    }

    /**
     * Set current <code>LSPainting</code> instance to color <code>c</code> and type <code>type</code>
     *
     * @param c    Paint color
     * @param type Paint type
     * @see #setPaintType(rocks6205.editor.model.adt.LSPaintingType)
     * @see #setPaintColor(rocks6205.editor.model.adt.LSColor)
     */
    public final void setPaint(LSColor c, LSPaintingType type) {
        setPaintType(type);

        if (type == LSPaintingType.COLOR) {
            setPaintColor(c);
        }
    }

    /**
     * Variant. Setting the color of paint.
     *
     * @param c Paint color
     * @see #setPaint(rocks6205.editor.model.adt.LSColor, rocks6205.editor.model.adt.LSPaintingType)
     */
    public final void setPaint(LSColor c) {
        setPaint(c, LSPaintingType.COLOR);
    }

    /**
     * {@inheritDoc}
     *
     * @return String of color in RGB format, none or empty.
     */
    @Override
    public String toString() {
        switch (paintType) {
            case NONE:
                return "none";

            case COLOR:
                return "rgb(" + paintColor.getRed() + "," + paintColor.getGreen() + "," + paintColor.getBlue() + ")";

            default:
                return "";
        }
    }
}