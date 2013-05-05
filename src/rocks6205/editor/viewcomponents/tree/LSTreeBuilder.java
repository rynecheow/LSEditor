package rocks6205.editor.viewcomponents.tree;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.model.elements.LSGenericContainer;
import rocks6205.editor.model.elements.LSGenericElement;
import rocks6205.editor.model.elements.LSGroupContainer;
import rocks6205.editor.model.elements.LSSVGContainer;

//~--- JDK imports ------------------------------------------------------------

import java.util.Iterator;

/**
 * Builds the tree by recursively check into any container elements and append to tree.
 * 
 * @author Cheow Yeong Chi
 * 
 * @since 2.4
 */
public class LSTreeBuilder {
   /**
    * Builder method.
    * @param svgElement Element to be constructed as a tree.
    * @return constructed root <code>LSTreeNode</code>
    */
    public static LSTreeNode build(LSSVGContainer svgElement) {
        LSTreeNode svgNode = new LSTreeNode("SVG Container Element", LSTreeNode.NODE_SVG);

        recurseIterateNode(svgElement, svgNode);

        return svgNode;
    }
    
    /**
     * Recurse into container elements if they have any descendants and append to tree.
     * 
     * @param container Container element 
     * @param containerNode Container node
     */
    private static void recurseIterateNode(LSGenericContainer container, LSTreeNode containerNode) {
        for (Iterator<LSGenericElement> it = container.getDescendants().iterator(); it.hasNext(); ) {
            LSGenericElement e = it.next();

            if (e instanceof LSGroupContainer) {
                LSGroupContainer g         = (LSGroupContainer) e;
                LSTreeNode       groupNode = buildGroupNode();

                containerNode.addChild(groupNode);
                recurseIterateNode(g, groupNode);
            } else {
                containerNode.addChild(buildSingularNode(e));
            }
        }
    }
    
    /**
     * Build a leaf node from any type of <code>LSShapeCircle</code>, <code>LSShapeLine</code>
     * or <code>LSShapeRect</code>.
     * @param el Element to process as node
     * @return Singular node with type defined
     */
    private static LSTreeNode buildSingularNode(LSGenericElement el) {
        String title;
        int    type;

        switch (el.getElementType()) {
        case "SVGCircleElement" :
            title = "Circle Element";
            type  = LSTreeNode.NODE_CIRCLE;

            break;

        case "SVGLineElement" :
            title = "Line Element";
            type  = LSTreeNode.NODE_LINE;

            break;

        case "SVGRectElement" :
            title = "Rectangle Element";
            type  = LSTreeNode.NODE_RECT;

            break;

        default :
            throw new AssertionError("Invalid type of tree node");
        }

        return new LSTreeNode(title, type);
    }

    /**
     * Build a leaf node from any type of <code>LSGroupContainer</code>
     * @return A new group node.
     */
    private static LSTreeNode buildGroupNode() {
        return new LSTreeNode("Group Element", LSTreeNode.NODE_GROUP);
    }
}