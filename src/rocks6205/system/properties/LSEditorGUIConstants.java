package rocks6205.system.properties;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Color;

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
public interface LSEditorGUIConstants {

    /**
     * Default master color for background
     */
    public static final Color MASTER_DEFAULT_BACKGROUND_COLOR = new Color(0X141212);

    /**
     * Default master font color
     */
    public static final Color MASTER_DEFAULT_FONT_COLOR = new Color(0xdce8e7);

    /**
     * Default master border color
     */
    public static final Color  MASTER_DEFAULT_BORDER_COLOR = new Color(0x211e1e);
    public static final Border MASTER_DEFAULT_PANEL_BORDER = BorderFactory.createLineBorder(MASTER_DEFAULT_BORDER_COLOR, 1);

    public static final String DEFAULT_PATH_TO_TOOLBAR_ICONS = "resources/toolbar-icons/";
    
    public static final String DEFAULT_PATH_TO_TREE_ICONS = "resources/tree-icons/";
    
    public static final String DEFAULT_PATH_TO_CUSTOM_FONTS = "resources/fonts/";
}