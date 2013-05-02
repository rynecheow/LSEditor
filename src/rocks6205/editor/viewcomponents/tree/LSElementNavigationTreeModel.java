
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package rocks6205.editor.viewcomponents.tree;

//~--- JDK imports ------------------------------------------------------------

import java.util.ArrayList;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author Cheow Yeong Chi
 */
public class LSElementNavigationTreeModel implements TreeModel {
    private TreeNode                     rootNode;
    private ArrayList<TreeModelListener> listeners;

    public LSElementNavigationTreeModel() {
        this.listeners = new ArrayList<>();
    }

    public LSElementNavigationTreeModel(TreeNode rootNode) {
        this.rootNode  = rootNode;
        this.listeners = new ArrayList<>();
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
        listeners.add(l);
    }

    @Override
    public Object getChild(Object parent, int index) {
        TreeNode parentNode = (TreeNode) parent;

        return parentNode.getChildAt(index);
    }

    @Override
    public int getChildCount(Object parent) {
        TreeNode parentNode = (TreeNode) parent;

        return parentNode.getChildCount();
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        TreeNode parentNode = (TreeNode) parent;
        TreeNode childNode  = (TreeNode) child;

        return parentNode.getIndex(childNode);
    }

    @Override
    public Object getRoot() {
        return rootNode;
    }

    @Override
    public boolean isLeaf(Object node) {
        TreeNode treeNode = (TreeNode) node;

        return treeNode.isLeaf();
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        listeners.remove(l);
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {}
}