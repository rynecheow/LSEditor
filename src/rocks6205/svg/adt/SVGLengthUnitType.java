package rocks6205.svg.adt;

import rocks6205.svgFamily.SVGCanvasProperties;

public enum SVGLengthUnitType {
	UNKNOWN(null), NUMBER(null), EMS("em"), EXS("ex"), PX("px"), CM("cm"), MM(
			"mm"), IN("in"), PT("pt"), PC("pc");

	private final String symbol;

	SVGLengthUnitType(String symbol) {
		this.symbol = symbol;
	}

	public String getUnitSymbol() {
		return symbol;
	}
/**
 * mm is set as default unit
 * In CSS definition, 1pt is 1/72 inch, and 'ex' is the x-height of the font
 * 
 * 
 * @return
 */
	public double getUnitScalingFactor() {
		double mm = 1; 
		double cm = 10 * mm;
		double in = 25.4 * mm;
		double pt = in / 72; 
		double pc = 12 * pt;
		double px = 0.75 * pt;
		double em = SVGCanvasProperties.getFontSize() * px;
		double ex = 0.5 * em;

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

	public static SVGLengthUnitType getType(String symbol) {
		for (SVGLengthUnitType type : values()) {
			if (symbol.equalsIgnoreCase(type.getUnitSymbol())) {
				return type;
			}
		}

		return SVGLengthUnitType.NUMBER;
	}
}
