package rocks6205.editor.model.adt;

//~--- JDK imports ------------------------------------------------------------

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The <code>LSLength</code> class is used to parse <code>length</code> attribute in
 * SVG documents and convert to floating points with respective type to be presented
 * on Java.
 * <p>
 * <code>LSLength</code> is used for <code>coordinates</code>, <code>stroke-width</code>,
 * <code>width</code>, <code>height</code>, etc.
 *
 * @author Cheow Yeong Chi
 *
 * @since 1.0
 */
public class LSLength {

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
    private LSLengthUnitType unitType;

    /*
     * CONSTRUCTORS
     */

    /**
     * Default constructor.<p>
     * Set current <code>LSLength</code> instance to type <code>UNKNOWN</code>,
     * and gives a float value of 0.
     */
    public LSLength() {
        this(LSLengthUnitType.UNKNOWN, Float.NaN);
    }

    /**
     * Set current <code>LSLength</code> instance to type <code>NUMBER</code>,
     * and gives a <code>float</code> value of <code>val</code>.
     *
     * @param val Value of length unit in <code>float</code>
     */
    public LSLength(float val) {
        this(LSLengthUnitType.NUMBER, val);
    }

    /**
     * Set current <code>LSLength</code> instance to type <code>type</code>,
     * and gives a float value of <code>val</code>.
     *
     * @param type Type of unit as defined in {@link LSLengthUnitType}
     * @param val Value of length unit in <code>float</code>
     */
    public LSLength(LSLengthUnitType type, float val) {
        if (Float.isNaN(value)) {
            throw new IllegalArgumentException("Length value must not be NaN");
        }

        setValue(val);

        if (type == LSLengthUnitType.UNKNOWN) {
            throw new IllegalArgumentException("Length type must not be UNKNOWN");
        }

        if (type == null) {
            throw new IllegalArgumentException("Length type must not be null");
        }

        setUnitType(type);
    }

    /*
     * ACCESSORS
     */

    /**
     * @return Length unit type
     */
    public LSLengthUnitType getUnitType() {
        return this.unitType;
    }

    /**
     * @return Length unit value
     */
    public float getValue() {
        return this.value;
    }

    /**
     * Get the value of length unit by specific unit type.
     * @param sym Target conversion type
     * @return Length unit value with respect to the target type
     */
    public float getValue(LSLengthUnitType target) {
        if (target == null) {
            throw new NullPointerException("Target type must not be null");
        }

        if (Float.isNaN(this.value) || (this.unitType == target)) {
            return this.value;
        }

        return value * this.unitType.getUnitScalingFactor() / target.getUnitScalingFactor();
    }

    /*
     * MUTATORS
     */

    /**
     * @param type Length unit type
     */
    public final void setUnitType(LSLengthUnitType type) {
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
     * @return  <code>LSLength</code> object
     */
    public static LSLength parse(String lengthAttributeValue) {
        lengthAttributeValue = lengthAttributeValue.trim();

        if (lengthAttributeValue == null) {
            return null;
        }

        // initialise variables
        LSLength length = null;
        Pattern  pattern, unitPattern;
        Matcher  matcher;
        String   numberStr = "";
        String   unitStr   = "",
                 unitSym   = "";
        int      u         = 0;

        // search
        for (String symbol : LSLengthUnitType.getSymbols()) {
            if ((symbol != null) &&!symbol.isEmpty()) {
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

        pattern = Pattern.compile(LSSVGPrimitive.NUM);
        matcher = pattern.matcher(lengthAttributeValue);

        if (matcher.matches()) {
            numberStr = matcher.group();
        }

        if (!numberStr.isEmpty()) {
            length = new LSLength(LSLengthUnitType.getType(unitStr), Float.parseFloat(numberStr));
        }

        return length;
    }

    /**
     * Convert the existing length unit to different type
     * @param length Length unit
     * @param targetType type of length unit
     * @return Converted length unit with type set to target type
     */
    public static LSLength convert(LSLength length, LSLengthUnitType targetType) {
        float value = length.getValue(targetType);

        return new LSLength(targetType, value);
    }
    /**
     * {@inheritDoc}
     * @return value of length with the unit symbol
     */
    @Override
    public String toString() {
        return value + unitType.getUnitSymbol();
    }
}