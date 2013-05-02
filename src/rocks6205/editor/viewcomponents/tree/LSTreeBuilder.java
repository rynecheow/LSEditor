package rocks6205.editor.viewcomponents.tree;

public class LSTreeBuilder {
    public static LSTreeNode build() {
        LSTreeNode svgNode = new LSTreeNode("SVG Container Element", LSTreeNode.NODE_SVG);
        
        LSTreeNode groupNode = new LSTreeNode("Group Element", LSTreeNode.NODE_GROUP);
        
        LSTreeNode lineNode = new LSTreeNode("Line Element", LSTreeNode.NODE_LINE);
        LSTreeNode circleNode = new LSTreeNode("Circle Element", LSTreeNode.NODE_CIRCLE);
        LSTreeNode rectNode = new LSTreeNode("Rectangle Element", LSTreeNode.NODE_RECT);
        groupNode.addChild(lineNode);
        groupNode.addChild(circleNode);
        svgNode.addChild(groupNode);
        svgNode.addChild(rectNode);
          
     
        return svgNode;
    }
}