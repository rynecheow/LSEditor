package rocks6205.editor.viewcomponents.tree;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.model.elements.SVGContainerElement;
import rocks6205.editor.model.elements.SVGGElement;
import rocks6205.editor.model.elements.SVGGenericElement;
import rocks6205.editor.model.elements.SVGSVGElement;

//~--- JDK imports ------------------------------------------------------------

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.tree.TreeNode;

public class LSTreeBuilder {
    private static LSTreeNode            svgNode  = new LSTreeNode("SVG Container Element", LSTreeNode.NODE_SVG);
    private static ArrayList<LSTreeNode> nodeList = new ArrayList<>();

    public static LSTreeNode build() {
        LSTreeNode groupNode  = new LSTreeNode("Group Element", LSTreeNode.NODE_GROUP);
        LSTreeNode lineNode   = new LSTreeNode("Line Element", LSTreeNode.NODE_LINE);
        LSTreeNode circleNode = new LSTreeNode("Circle Element", LSTreeNode.NODE_CIRCLE);
        LSTreeNode rectNode   = new LSTreeNode("Rectangle Element", LSTreeNode.NODE_RECT);

        groupNode.addChild(lineNode);
        groupNode.addChild(circleNode);
        svgNode.addChild(groupNode);
        svgNode.addChild(rectNode);

        return svgNode;
    }

    public static TreeNode build(SVGSVGElement svgElement) {
        iterateNode(svgElement, svgNode);

        return svgNode;
    }

    public static void iterateNode(SVGContainerElement container, LSTreeNode containerNode) {
        for (Iterator<SVGGenericElement> it = container.getDescendants().iterator(); it.hasNext(); ) {
            SVGGenericElement e = it.next();

            if (e instanceof SVGGElement) {
                SVGGElement g         = (SVGGElement) e;
                LSTreeNode  groupNode = buildGroupNode(g);

                containerNode.addChild(groupNode);
                iterateNode(g, groupNode);
            } else {
                containerNode.addChild(buildSingularNode(e));
            }
        }
    }

    public static LSTreeNode buildSingularNode(SVGGenericElement el) {
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

    public static LSTreeNode buildGroupNode(SVGGElement el) {
        return new LSTreeNode("Group Element", LSTreeNode.NODE_GROUP);
    }
}