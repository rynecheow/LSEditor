package rocks6205.editor.viewcomponents.tree;

import java.awt.Component;
import java.awt.Image;
 
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 * This class is implemented to customize the display of a node.
 * 
 * @author Cheow Yeong Chi
 */
public class NodeRenderer extends DefaultTreeCellRenderer {
   private ImageIcon iconSVG = createIconWithImageName("SVG-LOGO");
   private ImageIcon iconGroup = createIconWithImageName("G-LOGO");
   private ImageIcon iconLine = createIconWithImageName("Rect-Logo");
   private ImageIcon iconCircle = createIconWithImageName("Circle-Logo");
   private ImageIcon iconRect = createIconWithImageName("Line-Logo");
    
   /**
    * 
    * @param tree
    * @param value
    * @param sel
    * @param expanded
    * @param leaf
    * @param row
    * @param hasFocus
    * @return 
    */
   @Override
   public Component getTreeCellRendererComponent(
           JTree tree,
           Object value,
           boolean sel,
           boolean expanded,
           boolean leaf,
           int row,
           boolean hasFocus) {
       super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
       
       LSTreeNode node = (LSTreeNode) value;
       
       switch (node.getType()) {
           case LSTreeNode.NODE_SVG:
               setIcon(iconSVG);
               break;
           case LSTreeNode.NODE_GROUP:
               setIcon(iconGroup);
               break;
           case LSTreeNode.NODE_LINE:
               setIcon(iconLine);
               break;
           case LSTreeNode.NODE_CIRCLE:
               setIcon(iconCircle);
               break;
           case LSTreeNode.NODE_RECT:
               setIcon(iconRect);
               break;
       }
       
       return this;
   }

   private ImageIcon createIconWithImageName(String name) {
      String string = "resources/tree-logo/" + name + ".png";
      System.out.println(string);
      ImageIcon icon = new ImageIcon(string);
      icon = new ImageIcon(icon.getImage().getScaledInstance(24, 20, Image.SCALE_SMOOTH));
      return icon;
   }
}