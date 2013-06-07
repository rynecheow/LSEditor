package rocks6205.editor.viewcomponents.tree;

//~--- JDK imports ------------------------------------------------------------

import java.util.ArrayList;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 * Tree model that configures the structure of the navigation tree.
 *
 * @author Cheow Yeong Chi
 *
 * @since 2.4
 */
public class LSElementNavigationTreeModel implements TreeModel {
    private TreeNode                     rootNode;
    private ArrayList<TreeModelListener> listeners;

    /*
     * CONSTRUCTOR
     */

    /**
     * Default constructor.
     */
    public LSElementNavigationTreeModel() {
        this.listeners = new ArrayList<>();
    }

    /**
     * Constructor that takes in a node and set is as the root node of the tree.
     * @param rootNode
     */
    public LSElementNavigationTreeModel(TreeNode rootNode) {
        this.rootNode  = rootNode;
        this.listeners = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTreeModelListener(TreeModelListener l) {
        listeners.add(l);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getChild(Object parent, int index) {
        TreeNode parentNode = (TreeNode) parent;

        return parentNode.getChildAt(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getChildCount(Object parent) {
        TreeNode parentNode = (TreeNode) parent;

        return parentNode.getChildCount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getIndexOfChild(Object parent, Object child) {
        TreeNode parentNode = (TreeNode) parent;
        TreeNode childNode  = (TreeNode) child;

        return parentNode.getIndex(childNode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getRoot() {
        return rootNode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLeaf(Object node) {
        TreeNode treeNode = (TreeNode) node;

        return treeNode.isLeaf();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        listeners.remove(l);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {}
}