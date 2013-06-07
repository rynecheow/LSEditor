package rocks6205.editor.viewcomponents.tree;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.model.elements.LSGenericContainer;
import rocks6205.editor.model.elements.LSGenericElement;
import rocks6205.editor.model.elements.LSGenericShape;
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
     *
     * @see #recurseIterateNode(rocks6205.editor.model.elements.LSGenericContainer, rocks6205.editor.viewcomponents.tree.LSTreeNode)
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
     *
     * @see #buildShapeNode(rocks6205.editor.model.elements.LSGenericShape)
     * @see #buildGroupNode()
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
                if (e instanceof LSGenericShape) {
                    LSGenericShape el = (LSGenericShape) e;

                    containerNode.addChild(buildShapeNode(el));
                }
            }
        }
    }

    private static LSTreeNode buildShapeNode(LSGenericShape el) {
        String title;
        int    type;

        switch (el.getShapeType()) {
        case LSGenericShape.SHAPE_CIRCLE :
            title = "Circle Element";
            type  = LSTreeNode.NODE_CIRCLE;

            break;

        case LSGenericShape.SHAPE_LINE :
            title = "Line Element";
            type  = LSTreeNode.NODE_LINE;

            break;

        case LSGenericShape.SHAPE_RECT :
            title = "Rectangle Element";
            type  = LSTreeNode.NODE_RECT;

            break;

        default :
            throw new AssertionError("Invalid type of tree node");
        }

        return new LSTreeNode(title, type);
    }

    private static LSTreeNode buildGroupNode() {
        return new LSTreeNode("Group Element", LSTreeNode.NODE_GROUP);
    }
}