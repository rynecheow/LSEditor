package rocks6205.svgFamily;

public class SVGCanvasProperties {
	private static int RES = 72;
	private static double GLOBAL_TEXT_SIZE = 10;

	private SVGCanvasProperties() {
	}

	public static int getResolution() {
		return RES;
	}

	public static void setResolution(int res) {
		RES = res;
	}

	public static double getFontSize() {
		return GLOBAL_TEXT_SIZE;
	}

	public static void setFontSize(double fontSize) {
		GLOBAL_TEXT_SIZE = fontSize;
	}
}