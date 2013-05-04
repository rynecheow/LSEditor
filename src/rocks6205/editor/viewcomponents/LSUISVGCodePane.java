package rocks6205.editor.viewcomponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import javax.swing.JTextPane;
import rocks6205.editor.core.LSEditor;
import rocks6205.system.parser.LSDOMViewerToolkit;

/**
 * 
 * @author Cheow Yeong Chi
 */
public class LSUISVGCodePane extends JTextPane{

   
   public LSUISVGCodePane(){
      setEditable(false);
      setBackground(new Color(0x211e1e));
      setBorder(null);
      setEditorKitForContentType("text/xml", new LSDOMViewerToolkit());
      setContentType("text/xml");
      setFont(loadFont());
   }

   private Font loadFont() {
      Font font = null;
      try {
         font = Font.createFont(Font.TRUETYPE_FONT, new File("resources/font/Inconsolata.otf"));
      } catch (FontFormatException | IOException ex) {
         LSEditor.logger.warning(ex.getMessage());
      }
      if(font!=null){
         font = font.deriveFont(Font.PLAIN,15);
         String info = "Current font for code displaying: " + font.getFontName();
         LSEditor.logger.info(info);
      }
      return font;
   }
   
}
