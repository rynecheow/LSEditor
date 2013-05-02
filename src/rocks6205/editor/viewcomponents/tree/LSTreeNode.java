/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rocks6205.editor.viewcomponents.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import javax.swing.tree.TreeNode;

/**
 * 
 * @author Cheow Yeong Chi
 */
public class LSTreeNode implements TreeNode{
/**
     * The title will be displayed in the tree
     */
    private String title;
    
    /*
     * Type of this node, which is used by a renderer to set appropriate icon
     * for the node
     */
    private int type;
 
    private ArrayList<TreeNode> children;
    private TreeNode parent;
    
    // Constants for types of node
    public static final int NODE_SVG        = 0;
    public static final int NODE_GROUP     = 1;
    public static final int NODE_LINE      = 2;
    public static final int NODE_RECT     = 3;
    public static final int NODE_CIRCLE       = 4;
    
    public LSTreeNode(String title, int type) {
        this.children = new ArrayList<>();
        this.title = title;
        this.type = type;
    }
    
    public void addChild(TreeNode child) {
        children.add(child);
    }
    
    public void setParent(TreeNode parent) {
        this.parent = parent;
    }
    
    @Override
    public Enumeration<TreeNode> children() {
        return Collections.enumeration(children);
    }
 
    @Override
    public boolean getAllowsChildren() {
        return true;
    }
 
    @Override
    public TreeNode getChildAt(int childIndex) {
        return children.get(childIndex);
    }
 
    @Override
    public int getChildCount() {
        return children.size();
    }
 
    @Override
    public int getIndex(TreeNode node) {
        return children.indexOf(node);
    }
 
    @Override
    public TreeNode getParent() {
        return this.parent;
    }
 
    @Override
    public boolean isLeaf() {
        return (children.isEmpty());
    }
 
    public void setTitle(String title) {
        this.title = title;
    }
 
    public String getTitle() {
        return title;
    }
    
    /**
     * The node object should override this method to provide a text that will
     * be displayed for the node in the tree.
     */
    @Override
    public String toString() {
        return title;
    }
 
    public int getType() {
        return type;
    }   
}
