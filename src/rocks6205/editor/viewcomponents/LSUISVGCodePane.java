package rocks6205.editor.viewcomponents;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.core.LSEditor;

import rocks6205.system.toolkit.LSDOMViewerToolkit;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;

import java.io.File;
import java.io.IOException;

import javax.swing.JTextPane;
import rocks6205.system.properties.LSEditorGUIConstants;

/**
 *
 * @author Cheow Yeong Chi
 */
public final class LSUISVGCodePane extends JTextPane {
    public LSUISVGCodePane() {
        setEditable(false);
        setBackground(new Color(0x211e1e));
        setBorder(null);
        setEditorKitForContentType("text/xml", new LSDOMViewerToolkit());
        setContentType("text/xml");
        setCustomFont("Inconsolata.otf");
    }
    
    /**
     * Sets the font of the code view pane.
     * 
     * @param filename File name including extension of the true type font.
     * @return Loaded font.
     */
    private Font loadCustomFont(String filename) {
        Font font = null;
        String path = LSEditorGUIConstants.DEFAULT_PATH_TO_CUSTOM_FONTS + filename;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(path));
        } catch (FontFormatException | IOException ex) {
            LSEditor.logger.warning(ex.getMessage());
        }

        if (font != null) {
            font = font.deriveFont(Font.PLAIN, 15);

            String info = "Current font for code displaying: " + font.getFontName();

            LSEditor.logger.info(info);
        }

        return font;
    }
    
    /**
     * Set custom font of size 15pt.
     * @param filename File name including extension of the true type font.
     */
    public void setCustomFont(String filename){
       setFont(loadCustomFont(filename));
    }
    
}