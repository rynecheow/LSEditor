/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rocks6205.editor.viewcomponents;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.TreeNode;

/**
 * 
 * @author Cheow Yeong Chi
 */
public class LSElementsNavigationTree extends JTree{

   
   private ImageIcon icon;
   
   public static LSElementsNavigationTree createTree(TreeNode root) {
      return new LSElementsNavigationTree(root);
   }
   
   private LSElementsNavigationTree(TreeNode root){
      super(root);
   }
}
