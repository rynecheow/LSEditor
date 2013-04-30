package rocks6205.editor.viewcomponents;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.mvc.SVGEditorView;

import rocks6205.system.properties.LSSVGEditorGUITheme;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Cheow Yeong Chi
 */
public final class LSUINavigationPanel extends JPanel implements LSUIProtocol {

    /**
     * PARENT COMPONENT
     */
    private SVGEditorView parentView;

    /*
     *  GUI Components
     */
    private LSElementsNavigationTree navigationTree;
    private DefaultMutableTreeNode root;
    
    /*
     * PROPERTIES
     */
    private boolean isRootNull;
    /**
     * 
     * @param parent 
     */
    public LSUINavigationPanel(SVGEditorView parent) {
        super();
        parentView = parent;
        initialise();
        customise();
    }

    @Override
    public void initialise() {
//       navigationTree = LSElementsNavigationTree.createTree(null);
//       isRootNull = parentView.getModel().getSVGElement()==null;
//       if(!isRootNull){
//          root = new DefaultMutableTreeNode("SVG Root Container");
//       }
    }

    @Override
    public void customise() {
        setBorder(LSSVGEditorGUITheme.MASTER_DEFAULT_PANEL_BORDER);
    }
    
    
    
    
   

   public class LSTreeNode extends DefaultMutableTreeNode {

    public LSTreeNode() {
	// TODO Auto-generated constructor stub
    }

    public LSTreeNode(Object userObject) {
	super(userObject);
	// TODO Auto-generated constructor stub
    }

    public LSTreeNode(Object userObject, boolean allowsChildren) {
	super(userObject, allowsChildren);
	// TODO Auto-generated constructor stub
    }

    
}

}