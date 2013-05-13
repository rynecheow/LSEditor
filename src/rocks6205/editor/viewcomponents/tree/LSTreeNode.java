package rocks6205.editor.viewcomponents.tree;

//~--- JDK imports ------------------------------------------------------------

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import javax.swing.tree.TreeNode;

/**
 * Defines the tree node of the navigation tree.
 *
 * @author Cheow Yeong Chi
 *
 * @since 2.4
 */
public class LSTreeNode implements TreeNode {

    /*
     * Constants for types of node.
     */
    public static final int NODE_SVG    = 0;
    public static final int NODE_GROUP  = 1;
    public static final int NODE_LINE   = 2;
    public static final int NODE_RECT   = 3;
    public static final int NODE_CIRCLE = 4;

    /**
     *     The title will be displayed in the tree
     */
    private String title;

    /*
     * Type of current node.
     */
    private int type;

    /**
     * Children of node.
     */
    private ArrayList<TreeNode> children;

    /**
     * Parent node of current node.
     */
    private TreeNode parent;

    /*
     * CONSTRUCTOR
     */

    /**
     * Constructs a tree node by accepting a title and a type.
     * @param title
     * @param type
     */
    public LSTreeNode(String title, int type) {
        this.children = new ArrayList<>();
        this.title    = title;
        this.type     = type;
    }

    /**
     * Append child to current node
     * @param child Child tree node.
     */
    public void addChild(TreeNode child) {
        children.add(child);
    }

    /**
     * Set parent node.
     * @param parent parent node.
     */
    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Enumeration<TreeNode> children() {
        return Collections.enumeration(children);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean getAllowsChildren() {
        return true;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public TreeNode getChildAt(int childIndex) {
        return children.get(childIndex);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public int getChildCount() {
        return children.size();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public int getIndex(TreeNode node) {
        return children.indexOf(node);
    }

    /**
     * {@inheritDoc }
     * @return parent node
     */
    @Override
    public TreeNode getParent() {
        return this.parent;
    }

    /**
     * {@inheritDoc }
     * @return If there is not children
     */
    @Override
    public boolean isLeaf() {
        return (children.isEmpty());
    }

    /**
     * @param title Title for current node
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return title of current node
     */
    public String getTitle() {
        return title;
    }

    /**
     * The node object should override this method to provide a text that will
     * be displayed for the node in the tree.
     * {@inheritDoc }
     */
    @Override
    public String toString() {
        return title;
    }

    /**
     * @return Type of current node.
     */
    public int getType() {
        return type;
    }
}