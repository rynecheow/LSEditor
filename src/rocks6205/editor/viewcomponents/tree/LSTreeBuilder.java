package rocks6205.editor.viewcomponents.tree;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.model.elements.SVGContainerElement;
import rocks6205.editor.model.elements.SVGGElement;
import rocks6205.editor.model.elements.SVGGenericElement;
import rocks6205.editor.model.elements.SVGSVGElement;

//~--- JDK imports ------------------------------------------------------------

import java.util.Iterator;

public class LSTreeBuilder {
//   private static LSTreeNode svgNode  = new LSTreeNode("SVG Container Element", LSTreeNode.NODE_SVG);
    public static LSTreeNode build(SVGSVGElement svgElement) {
        LSTreeNode svgNode  = new LSTreeNode("SVG Container Element", LSTreeNode.NODE_SVG);
        iterateNode(svgElement, svgNode);
        return svgNode;
    }

    private static void iterateNode(SVGContainerElement container, LSTreeNode containerNode) {
        for (Iterator<SVGGenericElement> it = container.getDescendants().iterator(); it.hasNext(); ) {
            SVGGenericElement e = it.next();

            if (e instanceof SVGGElement) {
                SVGGElement g         = (SVGGElement) e;
                LSTreeNode  groupNode = buildGroupNode();

                containerNode.addChild(groupNode);
                iterateNode(g, groupNode);
            } else {
                containerNode.addChild(buildSingularNode(e));
            }
        }
    }

    private static LSTreeNode buildSingularNode(SVGGenericElement el) {
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