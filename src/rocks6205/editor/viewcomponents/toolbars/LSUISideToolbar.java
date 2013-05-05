package rocks6205.editor.viewcomponents.toolbars;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.actions.LSAbstractAction.DeleteAction;
import rocks6205.editor.actions.LSAbstractAction.DrawCircleAction;
import rocks6205.editor.actions.LSAbstractAction.DrawLineAction;
import rocks6205.editor.actions.LSAbstractAction.DrawRectAction;
import rocks6205.editor.actions.LSAbstractAction.GroupAction;
import rocks6205.editor.actions.LSAbstractAction.PanModeAction;
import rocks6205.editor.actions.LSAbstractAction.SelectModeAction;
import rocks6205.editor.actions.LSAbstractAction.UngroupAction;
import rocks6205.editor.core.LSView;
import rocks6205.editor.viewcomponents.LSUIButton;
import rocks6205.editor.viewcomponents.LSUIProtocol;
import rocks6205.editor.viewcomponents.LSUIToggleButton;

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
    private LSView parentView;

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
    private LSUIButton       deleteButton;

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
    private DeleteAction     deleteAction;

    public LSUISideToolbar(String name, LSView parent) {
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
        deleteButton     = LSUIButton.create();
        panModeAction    = new PanModeAction(parentView);
        selectModeAction = new SelectModeAction(parentView);
        drawRectAction   = new DrawRectAction(parentView);
        drawCircleAction = new DrawCircleAction(parentView);
        drawLineAction   = new DrawLineAction(parentView);
        groupAction      = new GroupAction(parentView);
        ungroupAction    = new UngroupAction(parentView);
        deleteAction     = new DeleteAction(parentView);
    }

    @Override
    public void customise() {
        layoutView();
        bindHandlers();
        setIconsForButtons();
        setFloatable(false);
        setRollover(true);
        setOrientation(SwingConstants.VERTICAL);
        setBorder(LSSVGEditorGUITheme.MASTER_DEFAULT_PANEL_BORDER);
    }

    private void setParentView(LSView parent) {
        parentView = parent;
    }

    /**
     * Setup
     * actions
     * for
     * button.
     */
    private void setActionForButtons() {
        selectButton.setAction(selectModeAction);
        panButton.setAction(panModeAction);
        drawRectButton.setAction(drawRectAction);
        drawCircButton.setAction(drawCircleAction);
        drawLineButton.setAction(drawLineAction);
        groupButton.setAction(groupAction);
        ungroupButton.setAction(ungroupAction);
        deleteButton.setAction(deleteAction);
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
        String deleteIconPath    = "delete.png";

        panButton.setIcon(panIconPath);
        selectButton.setIcon(selectIconPath);
        drawRectButton.setIcon(rectangleIconPath);
        drawCircButton.setIcon(circleIconPath);
        drawLineButton.setIcon(lineIconPath);
        groupButton.setIcon(groupIconPath);
        ungroupButton.setIcon(ungroupIconPath);
        deleteButton.setIcon(deleteIconPath);
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
        add(deleteButton);
    }

    public void updateActionStatusFromView(boolean status) {
        groupButton.setEnabled(status);
        ungroupButton.setEnabled(status);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bindHandlers() {
        setActionForButtons();
    }
}