package rocks6205.system.properties;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Color;
import java.awt.Cursor;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 * Theme used for the editor GUI.
 * 
 * @author Toh Huey Jing
 * @author Cheow Yeong Chi
 *
 * @since 1.6
 *
 */
public interface LSSVGEditorGUITheme {

    /**
     * Default master color for background
     */
    public static Color MASTER_DEFAULT_BACKGROUND_COLOR = new Color(0X141212);
    /**
     * Default master font color
     */
    public static Color MASTER_DEFAULT_FONT_COLOR = new Color(0xdce8e7);
    /**
     * Default master border color
     */
    public static Color MASTER_DEFAULT_BORDER_COLOR = new Color(0x211e1e);
    
    public static Border MASTER_DEFAULT_PANEL_BORDER = BorderFactory.createLineBorder(MASTER_DEFAULT_BORDER_COLOR, 1);
    
//    public static Cursor EDITING_MOVE_CURSOR = new Cursor();
}