/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rocks6205.system.parser;

import javax.swing.text.Element;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;

/**
 * 
 * @author Cheow Yeong Chi
 */
class SVGViewFactory implements ViewFactory {

   public SVGViewFactory() {
      
   }

   @Override
   public View create(Element elem) {
      return new LSSVGPlainView(elem);
   }

}
