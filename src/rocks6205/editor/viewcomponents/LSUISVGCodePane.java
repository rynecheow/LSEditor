package rocks6205.editor.viewcomponents;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.core.LSEditor;

import rocks6205.system.properties.LSEditorGUIConstants;
import rocks6205.system.toolkit.LSDOMViewerToolkit;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;

import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.net.URLDecoder;

import javax.swing.JTextPane;

/**
 *
 * @author Cheow Yeong Chi
 *
 * @since 2.5
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
     * Set custom font of size 15<code>pt</code>.
     * @param filename File name including extension of the true type font.
     */
    public void setCustomFont(String filename) {
        setFont(loadCustomFont(filename));
    }

    private Font loadCustomFont(String filename) {
        Font   font   = null;
        String path   = LSEditorGUIConstants.DEFAULT_PATH_TO_CUSTOM_FONTS + filename;
        URL    imgURL = LSUISVGCodePane.class.getResource(path);

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(URLDecoder.decode(imgURL.getFile(), "UTF-8")));
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
}