package rocks6205.editor.model;

import java.util.LinkedHashSet;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

public class LSTreeModel extends DefaultTreeModel {

   public DefaultMutableTreeNode svgContainerRootNode;
   private LinkedHashSet<DefaultMutableTreeNode> groupNodes;
   
   public LSTreeModel(TreeNode root) {
      super(root);
      this.svgContainerRootNode = (DefaultMutableTreeNode) root;
      groupNodes = new LinkedHashSet<>();
   }

   public LSTreeModel(TreeNode root, boolean asksAllowsChildren) {
      super(root, asksAllowsChildren);
      groupNodes = new LinkedHashSet<>();
   }
   
   public boolean isLeaf(){
      return (false);
   }
   
}
