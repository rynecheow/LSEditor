package rocks6205.editor.model;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

public class LSTreeModel extends DefaultTreeModel {

   public LSTreeModel(TreeNode root) {
      super(root);
   }

   public LSTreeModel(TreeNode root, boolean asksAllowsChildren) {
      super(root, asksAllowsChildren);

   }
}
