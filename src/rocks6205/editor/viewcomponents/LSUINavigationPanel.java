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
       LSTreeNode node = LSTreeBuilder.build();
       LSElementNavigationTreeModel model = new LSElementNavigationTreeModel(node);
       navigationTree = new JTree(model);
       navigationTree.setCellRenderer(new NodeRenderer());
       navigationTree.addTreeSelectionListener(this);
       
    }

    @Override
    public void customise() {
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
   
}