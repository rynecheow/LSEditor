package rocks6205.editor.viewcomponents.tree;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.model.elements.LSGenericContainer;
import rocks6205.editor.model.elements.LSGroup;
import rocks6205.editor.model.elements.LSGenericElement;
import rocks6205.editor.model.elements.LSSVGElement;

//~--- JDK imports ------------------------------------------------------------

import java.util.Iterator;

public class LSTreeBuilder {
//   private static LSTreeNode svgNode  = new LSTreeNode("SVG Container Element", LSTreeNode.NODE_SVG);
    public static LSTreeNode build(LSSVGElement svgElement) {
        LSTreeNode svgNode  = new LSTreeNode("SVG Container Element", LSTreeNode.NODE_SVG);
        iterateNode(svgElement, svgNode);
        return svgNode;
    }

    private static void iterateNode(LSGenericContainer container, LSTreeNode containerNode) {
        for (Iterator<LSGenericElement> it = container.getDescendants().iterator(); it.hasNext(); ) {
            LSGenericElement e = it.next();

            if (e instanceof LSGroup) {
                LSGroup g         = (LSGroup) e;
                LSTreeNode  groupNode = buildGroupNode();

                containerNode.addChild(groupNode);
                iterateNode(g, groupNode);
            } else {
                containerNode.addChild(buildSingularNode(e));
            }
        }
    }

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

    private static LSTreeNode buildGroupNode() {
        return new LSTreeNode("Group Element", LSTreeNode.NODE_GROUP);
    }
}