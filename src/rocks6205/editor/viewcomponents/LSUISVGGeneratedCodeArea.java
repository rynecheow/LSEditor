/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rocks6205.editor.viewcomponents;

import java.awt.Color;
import javax.swing.JTextPane;
import rocks6205.system.parser.LSDOMViewerToolkit;

/**
 * 
 * @author Cheow Yeong Chi
 */
public class LSUISVGGeneratedCodeArea extends JTextPane{

   
   public LSUISVGGeneratedCodeArea(){
      setEditable(false);
      setBackground(new Color(153, 153, 153));
      setBorder(null);
      setEditorKitForContentType("text/xml", new LSDOMViewerToolkit());
      setContentType("text/xml");
   }
}
