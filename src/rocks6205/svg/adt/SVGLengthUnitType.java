/**
 * 
 * Class: SVGLengthUnitType
 * Description: Enumerates length unit type symbols recognized by data type <length> in SVG as defined in 4.5.11 of CSS2 BDT
 * Refer to: <a href="http://www.w3.org/TR/SVG/types.html#InterfaceSVGLength"/>
 * 
 * @author: Cheow Yeong Chi
 * @date: 16/03/2013
 * 
 */

package rocks6205.svg.adt;

import rocks6205.svgFamily.SVGCanvasProperties;

public enum SVGLengthUnitType {
	UNKNOWN(null), 
	NUMBER(null), 
	EMS("em"), 
	EXS("ex"), 
	PX("px"), 
	CM("cm"), 
	MM("mm"), 
	IN("in"), 
	PT("pt"), 
	PC("pc");

	private final String symbol;

	SVGLengthUnitType(String symbol) {
		this.symbol = symbol;
	}

	public String getUnitSymbol() {
		return symbol;
	}

	/**
	 * In CSS definition,
	 * 
	 * The 'ex' unit is defined by the font�s �x-height�. 
	 * The x-height is so called because it is often equal to the height of the lowercase "x". 
	 * However, an �ex� is defined even for fonts that don�t contain an "x".
	 * 
	 * 1 'em' is equal to the current font size. 
	 * n 'em' means n times the size of the current font. 
	 * 
	 * 1 'pt' is the same as 1/72 'in'
	 * 1 'pc' is the same as 12 'pt'
	 * 
	 * @return Scaling factor that the length unit symbol defined
	 */
	public float getUnitScalingFactor() {
		//'mm' is set as default unit as defined in 4.5.11
		float mm = 1.0f; 
		float cm = 10.0f * mm;
		float in = 25.4f * mm;
		float pt = in / 72.0f; 
		float pc = 12.0f * pt;
		float px = 0.75f * pt;
		float em = SVGCanvasProperties.getFontSize() * px;
		float ex = 0.5f * em;

		switch (this) {
		case MM:
			return mm;
		case CM:
			return cm;
		case IN:
			return in;
		case PT:
			return pt;
		case PC:
			return pc;
		case EMS:
			return em;
		case EXS:
			return ex;
		case NUMBER:
		case PX:
			return px;
		default:
			return 0;
		}
	}

	/*
	 * METHODS
	 */

	/**
	 * Match the length unit type of the symbol with the defined symbols
	 * @param symbol
	 * @return	Returns the respective type if matches, returns 'NUMBER' if it doesn't
	 */
	public static SVGLengthUnitType getType(String symbol) {
		if (symbol == null) {
			throw new NullPointerException("Symbol String cannot be null");
		}

		if (symbol.isEmpty()) {
			return SVGLengthUnitType.NUMBER;
		}

		for (SVGLengthUnitType type : values()) {
			if (symbol.equalsIgnoreCase(type.getUnitSymbol())) {
				return type;
			}
		}
		return null;
	}

	public static String[] getUnitSymbols() {
		SVGLengthUnitType[] types = values();
		int typeCount = types.length;
		String[] symbols = new String[typeCount];

		for (int i = 0; i < typeCount; i++) {
			symbols[i] = types[i].getUnitSymbol();
		}

		return symbols;
	}

}
