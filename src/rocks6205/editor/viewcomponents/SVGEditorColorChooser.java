package rocks6205.editor.viewcomponents;

import java.awt.*;
import javax.swing.*;

import rocks6205.svg.adt.*;

/**
 * Color Chooser for SVGEditor
 *
 * @author Sugar CheeSheen Chan
 *
 * @since 1.0
 *
 */

public class SVGEditorColorChooser extends JFrame {

    private static final long serialVersionUID = 4003671910607353797L;
    private SVGColorScheme color;
    
    public Color selectColor() {
	return color;
    }
}
