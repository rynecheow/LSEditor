package rocks6205.editor.viewcomponents;

//~--- non-JDK imports --------------------------------------------------------
import java.awt.BorderLayout;
import rocks6205.editor.mvc.SVGEditorView;

import rocks6205.system.properties.LSSVGEditorGUITheme;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeNode;
import rocks6205.editor.model.elements.SVGSVGElement;
import rocks6205.editor.viewcomponents.tree.LSElementNavigationTreeModel;
import rocks6205.editor.viewcomponents.tree.LSTreeBuilder;
import rocks6205.editor.viewcomponents.tree.LSTreeNode;
import rocks6205.editor.viewcomponents.tree.NodeRenderer;

/**
 *
 * @author Cheow Yeong Chi
 */
public final class LSUINavigationPanel extends JPanel implements LSUIProtocol, TreeSelectionListener {

    /**
     * PARENT COMPONENT
     */
    private SVGEditorView parentView;

    /*
     *  GUI Components
     */
    private JTree navigationTree;
    
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
       updateTree();
    }

    @Override
    public void customise() {
       navigationTree.setCellRenderer(new NodeRenderer());
       navigationTree.addTreeSelectionListener(this);
       setBorder(LSSVGEditorGUITheme.MASTER_DEFAULT_PANEL_BORDER);
       setLayout(new BorderLayout());
       add(new JScrollPane(navigationTree), BorderLayout.CENTER);
    }

   @Override
   public void valueChanged(TreeSelectionEvent e) {
      Object node = navigationTree.getLastSelectedPathComponent();
       if (node == null) {
           return;
       }
        
       System.out.println("You have selected: " + node);
    }

   public void updateTree() {
      navigationTree = new JTree(buildModel());
      navigationTree.repaint();
   }
   
   public LSElementNavigationTreeModel buildModel(){
       return new LSElementNavigationTreeModel(buildNode());
      
   }

   private TreeNode buildNode() {
      return LSTreeBuilder.build(parentView.getModel().getSVGElement());
   }
}