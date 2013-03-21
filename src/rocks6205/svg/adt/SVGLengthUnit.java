/**
 * 
 * Class: SVGLengthUnit
 * Description: Parsing <length> in SVG documents and convert to floating points with respective type to be presented on Java
 * 
 * @author: Cheow Yeong Chi
 * @date: 16/03/2013
 * 
 */

package rocks6205.svg.adt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SVGLengthUnit{
	/*
	 * PROPERTIES
	 */
	private float value;
	private SVGLengthUnitType unitType;

	/*
	 * CONSTRUCTORS
	 */
	public SVGLengthUnit() {
		value = Float.NaN;
		unitType = SVGLengthUnitType.UNKNOWN;
	}

	public SVGLengthUnit(float val){
		this(SVGLengthUnitType.NUMBER,val);
	}

	public SVGLengthUnit(SVGLengthUnitType type, float val){
		if (Float.isNaN(value)) {
			throw new IllegalArgumentException("Length value cannot be NaN");
		}
		value = val;

		if (type == null) {
			throw new IllegalArgumentException("Length type cannot be null");
		}
		if (type == SVGLengthUnitType.UNKNOWN) {
			throw new IllegalArgumentException("Length type cannot be UNKNOWN");
		}
		unitType = type;
	}

	/*
	 * ACCESSORS
	 */
	public SVGLengthUnitType getUnitType(){
		return this.unitType;
	}

	public float getValue(){
		return this.value;
	}

	public float getValue(SVGLengthUnitType target){
		if (target == null) {
			throw new NullPointerException("Target type must not be null");
		}

		if (Float.isNaN(value) || unitType == target) {
			return value;
		}

		return value * this.unitType.getUnitScalingFactor() / target.getUnitScalingFactor();
	}

	/*
	 * MUTATORS
	 */
	public final void setUnitType(SVGLengthUnitType type){
		this.unitType = type;
	}

	public final void setValue(float val){
		this.value = val;
	}

	/*
	 * METHODS
	 */

	/**
	 * Parsing length attribute by extracting symbols and matches with defined unit types.
	 * 
	 * @param lengthAttributeValue	
	 * @return	Length unit type in floating point and respective type.
	 */
	public static SVGLengthUnit parse(String lengthAttributeValue){
		if (lengthAttributeValue == null) {
			return null;
		}
		lengthAttributeValue = lengthAttributeValue.trim();
		//initialise variables
		SVGLengthUnit length = null;

		Pattern pattern = null, unitPattern = null;
		Matcher matcher = null;
		String numberStr = "";
		String unitStr = "", unitSym = "";
		int u = 0;

		//search
		for (String symbol : SVGLengthUnitType.getUnitSymbols()) {
			if (symbol != null && !symbol.isEmpty()) {
				if (u != 0) {
					symbol = "|" + symbol;
				}
				unitSym += symbol;
				u++;
			}
		}

		unitPattern = Pattern.compile("(" + unitSym + ")$");
		matcher = unitPattern.matcher(lengthAttributeValue);

		if (matcher.find()){
			unitStr = matcher.group();
			lengthAttributeValue = lengthAttributeValue.replace(unitStr, "");
		}

		pattern = Pattern.compile(SVGPrimitive.NUM);
		matcher = pattern.matcher(lengthAttributeValue);
		if (matcher.matches())	numberStr = matcher.group();
		if (!numberStr.isEmpty()){
			length = new SVGLengthUnit(SVGLengthUnitType.getType(unitStr),Float.parseFloat(numberStr));
		}
		return length;
	}
}
