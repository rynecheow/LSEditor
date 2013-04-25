package rocks6205.editor.viewcomponents;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.mvc.SVGEditorView;

import rocks6205.system.properties.LSSVGEditorGUITheme;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import rocks6205.editor.events.LSAction.DrawCircleAction;
import rocks6205.editor.events.LSAction.DrawLineAction;
import rocks6205.editor.events.LSAction.DrawRectAction;
import rocks6205.editor.events.LSAction.PanModeAction;
import rocks6205.editor.events.LSAction.SelectModeAction;

/**
 *
 * @author Cheow Yeong Chi
 *
 * @since 2.2
 */
public final class LSUISideToolbar extends JToolBar implements LSUIProtocol {

    /*
     * PARENT COMPONENT
     */
    private SVGEditorView parentView;

    /*
     * GUI COMPONENTS
     */
    private LSUIToggleButton panButton;
    private LSUIToggleButton moveButton;
    private LSUIToggleButton selectButton;        // group 1
    private LSUIToggleButton insertRectButton;
    private LSUIToggleButton insertCircButton;
    private LSUIToggleButton insertLineButton;    // group 2
    private LSUIButton groupButton;
    private LSUIButton ungroupButton;       // group 3

    /*
     * ACTION COMPONENTS
     */
    private DrawLineAction drawLineAction;
    private DrawRectAction drawRectAction;
    private DrawCircleAction drawCircleAction;
    private PanModeAction panModeAction;
    private SelectModeAction selectModeAction;
    
    
    public LSUISideToolbar(String name, SVGEditorView parent) {
        super(name);
        setParentView(parent);
        initialise();
        customise();
    }

    @Override
    public void initialise() {
        panButton        = LSUIToggleButton.create();
        moveButton       = LSUIToggleButton.create();
        selectButton     = LSUIToggleButton.create();
        insertRectButton = LSUIToggleButton.create();
        insertCircButton = LSUIToggleButton.create();
        insertLineButton = LSUIToggleButton.create();
        groupButton      = LSUIButton.create();
        ungroupButton    = LSUIButton.create();
        
        drawLineAction = new DrawLineAction(parentView);
        drawRectAction = new DrawRectAction(parentView);
        drawCircleAction = new DrawCircleAction(parentView);
        panModeAction = new PanModeAction(parentView);
        selectModeAction = new SelectModeAction(parentView);
        
    }

    @Override
    public void customise() {
        layoutView();
        setActionForButtons();
        setIconsForButtons();
        setFloatable(false);
        setRollover(true);
        setOrientation(SwingConstants.VERTICAL);
        setBorder(LSSVGEditorGUITheme.MASTER_DEFAULT_PANEL_BORDER);
    }

    private void setParentView(SVGEditorView parent) {
        parentView = parent;
    }

    /**
     * Setup actions for button.
     */
    private void setActionForButtons() {
       insertLineButton.setAction(drawLineAction);
       insertCircButton.setAction(drawCircleAction);
       insertRectButton.setAction(drawRectAction);
    }

    /**
     * Setup icons for button.
     */
    private void setIconsForButtons() {
        String panIconPath       = "imageicon/pan.png";
        String moveIconPath      = "imageicon/move.png";
        String selectIconPath    = "imageicon/select.png";
        String rectangleIconPath = "imageicon/rectangle.png";
        String circleIconPath    = "imageicon/circle.png";
        String lineIconPath      = "imageicon/line.png";
        String groupIconPath     = "imageicon/group.png";
        String ungroupIconPath   = "imageicon/ungroup.png";

        panButton.setIcon(panIconPath);
        moveButton.setIcon(moveIconPath);
        selectButton.setIcon(selectIconPath);
        insertRectButton.setIcon(rectangleIconPath);
        insertCircButton.setIcon(circleIconPath);
        insertLineButton.setIcon(lineIconPath);
        groupButton.setIcon(groupIconPath);
        ungroupButton.setIcon(ungroupIconPath);
    }

    private void layoutView() {
        addSeparator();
        add(panButton);
        add(moveButton);
        add(selectButton);
        addSeparator();
        add(insertRectButton);
        add(insertCircButton);
        add(insertLineButton);
        addSeparator();
        add(groupButton);
        add(ungroupButton);
        addSeparator();
    }
    
    public void updateActionStatusFromView(boolean status) {
      groupButton.setEnabled(status);
      ungroupButton.setEnabled(status);
   }
}