package rocks6205.svg.adt;

//~--- JDK imports ------------------------------------------------------------

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The <code>SVGLengthUnit</code> class is used to parse <code>length</code> attribute in
 * SVG documents and convert to floating points with respective type to be presented
 * on Java.
 * <p>
 * <code>SVGLengthUnit</code> is used for <code>coordinates</code>, <code>stroke-width</code>,
 * <code>width</code>, <code>height</code>, etc.
 *
 * @author Cheow Yeong Chi
 *
 * @since 1.0
 */
public class SVGLengthUnit {

    /*
     * PROPERTIES
     */

    /**
     * Length unit value
     */
    private float value;

    /**
     * Length unit type
     */
    private SVGLengthUnitType unitType;

    /*
     * CONSTRUCTORS
     */

    /**
     * Default constructor.<p>
     * Set current <code>SVGLengthUnit</code> instance to type <code>UNKNOWN</code>,
     * and gives a float value of 0.
     */
    public SVGLengthUnit() {
        setSVGLengthUnit(SVGLengthUnitType.UNKNOWN, 0.0f);
    }

    /**
     * Set current <code>SVGLengthUnit</code> instance to type <code>NUMBER</code>,
     * and gives a <code>float</code> value of <code>val</code>.
     *
     * @param val Value of length unit in <code>float</code>
     */
    public SVGLengthUnit(float val) {
        setSVGLengthUnit(val);
    }

    /**
     * Set current <code>SVGLengthUnit</code> instance to type <code>type</code>,
     * and gives a float value of <code>val</code>.
     *
     * @param type Type of unit as defined in {@link SVGLengthUnitType}
     * @param val Value of length unit in <code>float</code>
     */
    public SVGLengthUnit(SVGLengthUnitType type, float val) {
        setSVGLengthUnit(type, val);
    }

    /*
     * ACCESSORS
     */

    /**
     * @return Length unit type
     */
    public SVGLengthUnitType getUnitType() {
        return this.unitType;
    }

    /**
     * @return Length unit value
     */
    public float getValue() {
        return getValue("");
    }

    /**
     * Get the value of length unit by specific unit symbols.
     * @param sym Unit symbol string
     * @return Length unit value with respect to symbol string
     */
    public float getValue(String sym) {
        SVGLengthUnitType target = SVGLengthUnitType.getType(sym);

        if (this.unitType == target) {
            return this.value;
        } else if (this.unitType != SVGLengthUnitType.UNKNOWN) {
            return value * this.unitType.getUnitScalingFactor() / target.getUnitScalingFactor();
        }

        return 0;
    }

    /*
     * MUTATORS
     */

    /**
     * @param type Length unit type
     */
    public final void setUnitType(SVGLengthUnitType type) {
        this.unitType = type;
    }

    public final void setValue(float val) {
        this.value = val;
    }

    /*
     * METHODS
     */

    /**
     * Parsing length attribute by extracting symbols and matches with defined unit types.
     *
     * @param lengthAttributeValue      Attribute string that is read directly from SVG file
     * @return  <code>SVGLengthUnit</code> object
     */
    public static SVGLengthUnit parse(String lengthAttributeValue) {
        lengthAttributeValue = lengthAttributeValue.trim();

        // initialise variables
        SVGLengthUnit length      = null;
        Pattern       pattern     = null,
                      unitPattern = null;
        Matcher       matcher     = null;
        String        numberStr   = "";
        String        unitStr     = "",
                      unitSym     = "";
        String        symbol      = "";
        int           u           = 0;

        // search
        for (SVGLengthUnitType type : SVGLengthUnitType.values()) {
            symbol = type.getUnitSymbol();

            if ((type != SVGLengthUnitType.UNKNOWN) && (type != SVGLengthUnitType.NUMBER)) {
                if (u != 0) {
                    symbol = "|" + symbol;
                }

                unitSym += symbol;
                u++;
            }
        }

        unitPattern = Pattern.compile("(" + unitSym + ")$");
        matcher     = unitPattern.matcher(lengthAttributeValue);

        if (matcher.find()) {
            unitStr              = matcher.group();
            lengthAttributeValue = lengthAttributeValue.replace(unitStr, "");
        }

        pattern = Pattern.compile(SVGPrimitive.NUM);
        matcher = pattern.matcher(lengthAttributeValue);

        if (matcher.matches()) {
            numberStr = matcher.group();
        }

        if (!numberStr.isEmpty()) {
            length = new SVGLengthUnit(SVGLengthUnitType.getType(unitStr), Float.parseFloat(numberStr));
        }

        return length;
    }

    /**
     * Setting the type and value of the length unit object.
     *
     * @param type      Length unit type
     * @param val       Length unit value
     */
    public final void setSVGLengthUnit(SVGLengthUnitType type, float val) {
        if (type != null) {
            setUnitType(type);
        }

        setValue(val);
    }

    /**
     * Variant. Default unit type set to 'NUMBER'.
     *
     * @param val       Length unit value
     */
    public final void setSVGLengthUnit(float val) {
        setSVGLengthUnit(SVGLengthUnitType.NUMBER, val);
    }
}