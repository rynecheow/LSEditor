/**
 * 
 * Class: SVGLengthUnit
 * Description: Parsing <length> in SVG documents and convert to floating points with respective type to be presented on Java
 * 
 * @author: Cheow Yeong Chi
 * @version: 1.0
 * @date: 16/03/2013
 * 
 */

package rocks6205.svg.adt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SVGLengthUnit {
	/*
	 * STATIC VARIABLES
	 */

	//<integer> as specified in 4.2
	public static final String INT = "[+-]?[0-9]+"; 
	//<number> as specified in 4.2
	public static final String NUM = INT + "([Ee]" + INT + ")?|[+-]?[0-9]*\\.[0-9]+([Ee]" + INT + ")?"; 

	/*
	 * PROPERTIES
	 */
	private float value;
	private SVGLengthUnitType unitType;
	public SVGLengthUnit() {
		setSVGLengthUnit(SVGLengthUnitType.UNDEFINED, 0.0f);
	}
	/*
	 * CONSTRUCTORS
	 */
	public SVGLengthUnit(float val){
		setSVGLengthUnit(val);
	}

	public SVGLengthUnit(SVGLengthUnitType type, float val){
		setSVGLengthUnit(type, val);
	}

	/*
	 * ACCESSORS
	 */
	public SVGLengthUnitType getUnitType(){
		return this.unitType;
	}

	public float getValue(){
		return getValue("");
	}

	public float getValue(String sym){
		SVGLengthUnitType target = SVGLengthUnitType.getType(sym);

		if (this.unitType == target) {
			return this.value;
		} else if (this.unitType != SVGLengthUnitType.UNDEFINED) {
			return value * this.unitType.getUnitScalingFactor() / target.getUnitScalingFactor();
		}

		return 0;
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
		lengthAttributeValue = lengthAttributeValue.trim();
		//initialise variables
		SVGLengthUnit length = null;

		Pattern pattern = null, unitPattern = null;
		Matcher matcher = null;
		String numberStr = "";
		String unitStr = "", unitSym = "";
		String symbol = "";
		int u = 0;

		//search
		for (SVGLengthUnitType type: SVGLengthUnitType.values()) {
			symbol = type.getUnitSymbol();
			if (type != SVGLengthUnitType.UNDEFINED && type != SVGLengthUnitType.NUMBER) {
				if (u != 0) 	{symbol = "|" + symbol;}
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

		pattern = Pattern.compile(SVGLengthUnit.NUM);
		matcher = pattern.matcher(lengthAttributeValue);
		if (matcher.matches())	{numberStr = matcher.group();}
		if (!numberStr.isEmpty()){
			length = new SVGLengthUnit(SVGLengthUnitType.getType(unitStr),Float.parseFloat(numberStr));
		}
		return length;
	}

	/**
	 * Setting the type and value of the length unit object.
	 * 
	 * @param type	Type of length unit
	 * @param val	Value of length unit
	 */
	public final void setSVGLengthUnit(SVGLengthUnitType type, float val){
		if(type!=null)	setUnitType(type);
		setValue(val);
	}

	/**
	 * Variant. Default unit type set to 'NUMBER'.
	 * 
	 * @param val	Value of length unit
	 */
	public final void setSVGLengthUnit(float val){
		setSVGLengthUnit(SVGLengthUnitType.NUMBER, val);		
	}

}
