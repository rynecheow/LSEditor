/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rocks6205.editor.viewcomponents;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import rocks6205.editor.core.LSEditor;

/**
 * 
 * @author Cheow Yeong Chi
 */
public class LSNumericTextField extends JTextField {

   public LSNumericTextField() {
      super();
   }

   @Override
   protected Document createDefaultModel() {
      return new LSNumericDocument();
   }

   private class LSNumericDocument extends PlainDocument {

      @Override
      public void insertString(int offs, String str, AttributeSet a)
              throws BadLocationException {

         if (str == null) {
            return;
         }

         char[] chars = str.toCharArray();
         boolean ok = true;

         for (int i = 0; i < chars.length; i++) {

            try {
               Integer.parseInt(String.valueOf(chars[i]));
            } catch (NumberFormatException exc) {
               ok = false;
               LSEditor.logger.warning(exc.getMessage());
               break;
            }


         }

         if (ok) {
            super.insertString(offs, new String(chars), a);
         }

      }
   }
}