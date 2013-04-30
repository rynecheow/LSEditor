package rocks6205.editor.viewcomponents;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;

/**
 * 
 * @author Cheow Yeong Chi
 */
public class LSElementsNavigationTree extends JTree{

   
   
   
   private LSTreeCellRenderer renderer;
   public static LSElementsNavigationTree createTree(TreeNode root) {
      return new LSElementsNavigationTree(root);
   }
   
   private LSElementsNavigationTree(TreeNode root){
      super(root);
      renderer = new LSTreeCellRenderer();
   }
   
   private class LSTreeCellRenderer extends DefaultTreeCellRenderer{
//      private static final ImageIcon svgContainerIcon;
//      private ImageIcon groupContainerIcon;
//      private ImageIcon circleIcon;
//      private ImageIcon lineIcon;
//      private ImageIcon rectIcon;
//      
//      @Override
//    public Component getTreeCellRendererComponent(JTree tree, Object value,
//        boolean sel, boolean exp, boolean leaf, int row, boolean hasFocus) {
//        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
//        String s = node.getUserObject().toString();
//        if ("sports".equals(s)) {
//            setOpenIcon(open);
//            setClosedIcon(closed);
//        } else {
//            setOpenIcon(getDefaultOpenIcon());
//            setClosedIcon(getDefaultClosedIcon());
//        }
//        super.getTreeCellRendererComponent(
//            tree, value, sel, exp, leaf, row, hasFocus);
//        return this;
//    }
   }
}
