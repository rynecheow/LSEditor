package rocks6205.editor.viewcomponents;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.bridge.actions.LSAction.DrawCircleAction;
import rocks6205.editor.bridge.actions.LSAction.DrawLineAction;
import rocks6205.editor.bridge.actions.LSAction.DrawRectAction;
import rocks6205.editor.bridge.actions.LSAction.GroupAction;
import rocks6205.editor.bridge.actions.LSAction.PanModeAction;
import rocks6205.editor.bridge.actions.LSAction.SelectModeAction;
import rocks6205.editor.bridge.actions.LSAction.UngroupAction;
import rocks6205.editor.mvc.SVGEditorView;

import rocks6205.system.properties.LSSVGEditorGUITheme;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.JToolBar;
import javax.swing.SwingConstants;

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
    private LSUIToggleButton selectButton;      // group 1
    private LSUIToggleButton drawRectButton;
    private LSUIToggleButton drawCircButton;
    private LSUIToggleButton drawLineButton;    // group 2
    private LSUIButton       groupButton;
    private LSUIButton       ungroupButton;     // group 3

    /*
     * ACTION COMPONENTS
     */
    private DrawLineAction   drawLineAction;
    private DrawRectAction   drawRectAction;
    private DrawCircleAction drawCircleAction;
    private PanModeAction    panModeAction;
    private SelectModeAction selectModeAction;
    private GroupAction      groupAction;
    private UngroupAction    ungroupAction;

    public LSUISideToolbar(String name, SVGEditorView parent) {
        super(name);
        setParentView(parent);
        initialise();
        customise();
    }

    @Override
    public void initialise() {
        panButton        = LSUIToggleButton.create();
        selectButton     = LSUIToggleButton.create();
        drawRectButton   = LSUIToggleButton.create();
        drawCircButton   = LSUIToggleButton.create();
        drawLineButton   = LSUIToggleButton.create();
        groupButton      = LSUIButton.create();
        ungroupButton    = LSUIButton.create();
        panModeAction    = new PanModeAction(parentView);
        selectModeAction = new SelectModeAction(parentView);
        drawRectAction   = new DrawRectAction(parentView);
        drawCircleAction = new DrawCircleAction(parentView);
        drawLineAction   = new DrawLineAction(parentView);
        groupAction      = new GroupAction(parentView);
        ungroupAction    = new UngroupAction(parentView);
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
     * Setup
     * actions
     * for
     * button.
     */
    private void setActionForButtons() {
        panButton.setAction(panModeAction);
        selectButton.setAction(selectModeAction);
        drawRectButton.setAction(drawRectAction);
        drawCircButton.setAction(drawCircleAction);
        drawLineButton.setAction(drawLineAction);
    }

    /**
     * Setup
     * icons
     * for
     * button.
     */
    private void setIconsForButtons() {
        String panIconPath       = "pan.png";
        String selectIconPath    = "select.png";
        String rectangleIconPath = "rectangle.png";
        String circleIconPath    = "circle.png";
        String lineIconPath      = "line.png";
        String groupIconPath     = "group.png";
        String ungroupIconPath   = "ungroup.png";

        panButton.setIcon(panIconPath);
        selectButton.setIcon(selectIconPath);
        drawRectButton.setIcon(rectangleIconPath);
        drawCircButton.setIcon(circleIconPath);
        drawLineButton.setIcon(lineIconPath);
        groupButton.setIcon(groupIconPath);
        ungroupButton.setIcon(ungroupIconPath);
    }

    private void layoutView() {
        addSeparator();
        add(panButton);
        add(selectButton);
        addSeparator();
        add(drawRectButton);
        add(drawCircButton);
        add(drawLineButton);
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