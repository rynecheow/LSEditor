package rocks6205.svg.adt;

public class SVGLengthUnit {
	public static final String INT = "[+-]?[0-9]+";
	public static final String NUM = INT + "([Ee]" + INT
			+ ")?|[+-]?[0-9]*\\.[0-9]+([Ee]" + INT + ")?";
	
	private float value;
	private SVGLengthUnitType unitType;
	public SVGLengthUnit() {
		
	}
}
