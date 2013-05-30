package rocks6205.editor.actions;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.core.LSEditor;
import rocks6205.editor.core.LSView;
import rocks6205.editor.core.LSViewController;
import rocks6205.editor.viewcomponents.panels.LSUIEditingPanel;

import rocks6205.system.properties.LSCanvasProperties;
import rocks6205.system.properties.OSValidator;

//~--- JDK imports ------------------------------------------------------------

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import java.io.File;
import java.io.IOException;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import static javax.swing.Action.ACCELERATOR_KEY;
import static javax.swing.Action.MNEMONIC_KEY;
import static javax.swing.Action.SELECTED_KEY;
import static javax.swing.Action.SHORT_DESCRIPTION;

/**
 * The <code>LSAbstractAction</code> is an abstract class which create <code>Action</code>
 * instances. Instances can carry out similar handling of event is enabled without redefining
 * the <code>actionPerformed()</code> function in different classes.
 *
 * @author Cheow Yeong Chi
 *
 * @since 1.1
 *
 */
public abstract class LSAbstractAction extends AbstractAction {

    /**
     * Parent component (Usually an <code>LSView</code> object.)
     */
    protected LSView           v;
    protected LSViewController vc;

    /*
     * CONSTRUCTOR
     */

    /**
     * Construct a <code>SVGViewMenuAction</code> type object with <code>tooltipText</code>,
     * <code>mnemonic</code> key, <code>keyStroke</code> and v component
     * <code>v</code>.
     *
     * @param tooltipText Tool tip text while mouse over action component
     * @param mnemonic Mnemonic key that allows users to choose a control by pressing a single key
     * @param keyStroke Key action on the keyboard
     * @param v Parent component
     */
    public LSAbstractAction(String tooltipText, Integer mnemonic, KeyStroke keyStroke, LSView parent) {
        putValue(SHORT_DESCRIPTION, tooltipText);
        putValue(MNEMONIC_KEY, mnemonic);
        putValue(ACCELERATOR_KEY, keyStroke);
        this.v  = parent;
        this.vc = parent.getController();
    }

    /**
     * Construct a <code>SVGViewMenuAction</code> type object with action <code>name</code>,
     * <code>tooltipText</code>, <code>mnemonic</code> key, <code>keyStroke</code> and v component
     * <code>v</code>.
     *
     * @param name Name of action component
     * @param tooltipText Tool tip text while mouse over action component
     * @param mnemonic Mnemonic key that allows users to choose a control by pressing a single key
     * @param keyStroke Key action on the keyboard
     * @param v Parent component
     */
    public LSAbstractAction(String name, String tooltipText, Integer mnemonic, KeyStroke keyStroke, LSView parent) {
        putValue(Action.NAME, name);
        putValue(SHORT_DESCRIPTION, tooltipText);
        putValue(MNEMONIC_KEY, mnemonic);
        putValue(ACCELERATOR_KEY, keyStroke);
        this.v  = parent;
        this.vc = parent.getController();
    }

    /**
     * Masks for Mac OS X to change <code>CTRL</code> masks to <code>COMMAND</code> masks
     * @return
     */
    private static int getKeyEventMask() {
        if (OSValidator.isMac()) {
            return ActionEvent.META_MASK;
        }

        return ActionEvent.CTRL_MASK;
    }
    
    /**
     * Sets action name.
     * @param name 
     */
    public void setText(String name){
       putValue(Action.NAME, name);
    }
    
    /**
     * The <code>DeleteAction</code> is a class which create an <code>Action</code>
     * instance. This action handles event by removing elements from the model.
     *
     * @author Cheow Yeong Chi
     *
     * @since 2.2
     *
     */
    public static class DeleteAction extends LSAbstractAction {

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>DeleteAction</code> instance with v component
         * <code>v</code> and no action name.
         * @param v Parent component
         */
        public DeleteAction(ResourceBundle b,LSView parent) {
            super(b.getString("action.delete.title"), KeyEvent.VK_E, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), parent);
        }

        /**
         * Construct a <code>DeleteAction</code> instance with v component
         * <code>v</code> and action name.
         * @param v Parent component
         * @param actionName Name of action component
         */
        public DeleteAction(LSView parent, String actionName) {
            super(actionName, "Delete", KeyEvent.VK_E, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), parent);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            vc.deleteSelectedElement();
        }
    }


    /**
     * The <code>DeselectAllAction</code> is a class which create an <code>Action</code>
     * instance. This action handles event by removing every element on view from selection
     * set.
     *
     * @author Cheow Yeong Chi
     *
     * @since 2.2
     *
     */
    public static class DeselectAllAction extends LSAbstractAction {

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>DeselectAllAction</code> instance with v component
         * <code>v</code> and no action name.
         * @param v Parent component
         */
        public DeselectAllAction(ResourceBundle b,LSView parent) {
            super(b.getString("action.deselall.title"), KeyEvent.VK_D,
                  KeyStroke.getKeyStroke(KeyEvent.VK_A, getKeyEventMask() + InputEvent.SHIFT_DOWN_MASK), parent);
        }

        /**
         * Construct a <code>DeselectAllAction</code> instance with v component
         * <code>v</code> and action name.
         * @param v Parent component
         * @param actionName Name of action component
         */
        public DeselectAllAction(LSView parent, String actionName) {
            super(actionName, "Deselect All", KeyEvent.VK_D,
                  KeyStroke.getKeyStroke(KeyEvent.VK_A, getKeyEventMask() + InputEvent.SHIFT_DOWN_MASK), parent);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            vc.clearSelection();
        }
    }


    /**
     * The <code>DocumentPropertiesAction</code> is a class which create an <code>Action</code>
     * instance. This action handles event by calling the document properties dialog.
     *
     * @author Cheow Yeong Chi
     *
     * @since 2.4
     *
     */
    public static class DocumentPropertiesAction extends LSAbstractAction {

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>DocumentPropertiesAction</code> instance with v component
         * <code>v</code> and no action name.
         * @param v Parent component
         */
        public DocumentPropertiesAction(ResourceBundle b,LSView parent) {
            super(b.getString("action.docprop.title"), KeyEvent.VK_P,
                  KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.ALT_DOWN_MASK), parent);
        }

        /**
         * Construct a <code>DocumentPropertiesAction</code> instance with v component
         * <code>v</code> and action name.
         * @param v Parent component
         * @param actionName Name of action component
         */
        public DocumentPropertiesAction(LSView parent, String actionName) {
            super(actionName, "Document Properties..", KeyEvent.VK_P,
                  KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.ALT_DOWN_MASK), parent);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            v.showDocumentPropertiesDialog();
        }
    }

    /**
     * The <code>DrawCircleAction</code> is a class which create an <code>Action</code>
     * instance. This action handles event by setting the color used for painting shapes
     *
     *
     * @author Sugar CheeSheen Chan
     *
     * @since 2.0
     *
     */
    public static class DrawCircleAction extends LSAbstractAction {

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>DrawCircleAction</code> instance with v component
         * <code>v</code> and no action name. <p>Disabled by default.
         * @param v Parent component
         */
        public DrawCircleAction(ResourceBundle b,LSView parent) {
            super(b.getString("action.drawCircle.title"), KeyEvent.VK_C, KeyStroke.getKeyStroke(KeyEvent.VK_C, getKeyEventMask()), parent);
        }

        /**
         * Construct a <code>FillAction</code> instance with v component
         * <code>v</code>.
         * @param v Parent component
         * @param actionName Name of action component
         */
        public DrawCircleAction(LSView parent, String actionName) {
            super(actionName, "Draw Circle", KeyEvent.VK_C, KeyStroke.getKeyStroke(KeyEvent.VK_C, getKeyEventMask()),
                  parent);
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            putValue(SELECTED_KEY, Boolean.TRUE);
            v.changeMode(LSUIEditingPanel.EditModeScheme.DRAW_CIRCLE);
            vc.clearSelection();
        }
    }


    /**
     * The <code>DrawLineAction</code> is a class which create an <code>Action</code>
     * instance. This action handles event by setting the color used for painting shapes
     *
     *
     * @author Sugar CheeSheen Chan
     *
     * @since 2.0
     *
     */
    public static class DrawLineAction extends LSAbstractAction {

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>DrawLineAction</code> instance with v component
         * <code>v</code> and no action name. <p>Disabled by default.
         * @param v Parent component
         */
        public DrawLineAction(ResourceBundle b,LSView parent) {
            super(b.getString("action.drawLine.title"), KeyEvent.VK_L, KeyStroke.getKeyStroke(KeyEvent.VK_L, getKeyEventMask()), parent);
        }

        /**
         * Construct a <code>FillAction</code> instance with v component
         * <code>v</code>.
         * @param v Parent component
         * @param actionName Name of action component
         */
        public DrawLineAction(LSView parent, String actionName) {
            super(actionName, "Draw Line", KeyEvent.VK_L, KeyStroke.getKeyStroke(KeyEvent.VK_L, getKeyEventMask()),
                  parent);
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            putValue(SELECTED_KEY, Boolean.TRUE);
            v.changeMode(LSUIEditingPanel.EditModeScheme.DRAW_LINE);
            vc.clearSelection();
        }
    }


    /**
     * The <code>DrawRectAction</code> is a class which create an <code>Action</code>
     * instance. This action handles event by setting the color used for painting shapes
     *
     *
     * @author Sugar CheeSheen Chan
     *
     * @since 2.0
     *
     */
    public static class DrawRectAction extends LSAbstractAction {

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>DrawLineAction</code> instance with v component
         * <code>v</code> and no action name. <p>Disabled by default.
         * @param v Parent component
         */
        public DrawRectAction(ResourceBundle b,LSView parent) {
            super(b.getString("action.drawRect.title"), KeyEvent.VK_R, KeyStroke.getKeyStroke(KeyEvent.VK_R, getKeyEventMask()), parent);
        }

        /**
         * Construct a <code>FillAction</code> instance with v component
         * <code>v</code>.
         * @param v Parent component
         * @param actionName Name of action component
         */
        public DrawRectAction(LSView parent, String actionName) {
            super(actionName, "Draw Rect", KeyEvent.VK_R, KeyStroke.getKeyStroke(KeyEvent.VK_R, getKeyEventMask()),
                  parent);
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            putValue(SELECTED_KEY, Boolean.TRUE);
            v.changeMode(LSUIEditingPanel.EditModeScheme.DRAW_RECTANGLE);
            vc.clearSelection();
        }
    }


    /**
     * The <code>ExitAction</code> is a class which create an <code>Action</code>
     * instance. This action handles event by prompting user to confirm exiting the program
     * by calling a instance of <code>JOptionPane</code>.
     *
     * @author Cheow Yeong Chi
     *
     * @since 1.1
     *
     */
    public static class ExitAction extends LSAbstractAction {

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>ExitAction</code> instance with v component
         * <code>v</code> and no action name.
         * @param v Parent component
         */
        public ExitAction(ResourceBundle b,LSView parent) {
            super(b.getString("action.exit.title"), KeyEvent.VK_X, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK), parent);
        }

        /**
         * Construct a <code>ExitAction</code> instance with v component
         * <code>v</code>.
         * @param v Parent component
         * @param actionName Name of action component
         */
        public ExitAction(LSView parent, String actionName) {
            super(actionName, "Exit Program", KeyEvent.VK_X,
                  KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK), parent);
        }

        /**
         * <p>Calls a <code>JOptionPane</code> dialog and prompts user to confirm program exit.</p>
         * {@inheritDoc}
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            int closeCf = JOptionPane.showConfirmDialog(null, "Exit SVG Editor?", "Confirm exit",
                              JOptionPane.WARNING_MESSAGE);

            if (closeCf == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }


    /**
     * The <code>GroupAction</code> is a class which create an <code>Action</code>
     * instance. This action handles event by grouping elements in selection.
     *
     * @author Cheow Yeong Chi
     *
     * @since 2.2
     */
    public static class GroupAction extends LSAbstractAction {

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>GroupAction</code> instance with v component
         * <code>v</code> and no action name.
         * @param v Parent component
         */
        public GroupAction(ResourceBundle b,LSView parent) {
            super(b.getString("action.group.title"), KeyEvent.VK_G, KeyStroke.getKeyStroke(KeyEvent.VK_G, getKeyEventMask()), parent);
        }

        /**
         * Construct a <code>GroupAction</code> instance with v component
         * <code>v</code> and action name.
         * @param v Parent component
         * @param actionName Name of action component
         */
        public GroupAction(LSView parent, String actionName) {
            super(actionName, "Group", KeyEvent.VK_G, KeyStroke.getKeyStroke(KeyEvent.VK_G, getKeyEventMask()), parent);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            vc.group();
        }
    }

    /**
     * The <code>NewDocumentAction</code> is a class which create an <code>Action</code>
     * instance. This action handles event by creating a new document according to user
     * settings.
     * @author Cheow Yeong Chi
     *
     * @since 2.2
     */
    public static class NewDocumentAction extends LSAbstractAction {

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>NewDocumentAction</code> instance with v component
         * <code>v</code> and no action name.
         * @param v Parent component
         */
        public NewDocumentAction(ResourceBundle b , LSView parent) {
            super(b.getString("action.new.title"), KeyEvent.VK_N, KeyStroke.getKeyStroke(KeyEvent.VK_N, getKeyEventMask()), parent);
        }

        /**
         * Construct a <code>NewDocumentAction</code> instance with v component
         * <code>v</code> and action name.
         * @param v Parent component
         * @param actionName Name of action component
         */
        public NewDocumentAction(LSView parent, String actionName) {
            super(actionName, "New..", KeyEvent.VK_N, KeyStroke.getKeyStroke(KeyEvent.VK_N, getKeyEventMask()), parent);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (v.promptSaveIfNeeded()) {
                v.setDisplayedFile(null);
                vc.createBlankDocument();
            }
        }
    }


    /**
     * The <code>OpenFileAction</code> is a class which create an <code>Action</code>
     * instance. This action handles event by prompting user to select an SVG document
     * from the local drive by calling an instance of <code>JFileChooser</code>.
     *
     * @author Cheow Yeong Chi
     *
     * @since 1.1
     *
     */
    public static class OpenFileAction extends LSAbstractAction {

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>OpenFileAction</code> instance with v component
         * <code>v</code> and no action name.
         *
         * @param v Parent component
         */
        public OpenFileAction(ResourceBundle b,LSView parent) {
            super(b.getString("action.open.title"), KeyEvent.VK_O, KeyStroke.getKeyStroke(KeyEvent.VK_O, getKeyEventMask()), parent);
        }

        /**
         * Construct a <code>OpenFileAction</code> instance with v component
         * <code>v</code>.
         * @param v Parent component
         * @param actionName Name of action component
         */
        public OpenFileAction(LSView parent, String actionName) {
            super(actionName, "Open a file", KeyEvent.VK_O, KeyStroke.getKeyStroke(KeyEvent.VK_O, getKeyEventMask()),
                  parent);
        }

        /**
         * <p>Calls a <code>JFileChooser</code> dialog and prompts user to select an SVG file.</p>
         * {@inheritDoc}
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            JFileChooser fileChoooser = new JFileChooser();

            fileChoooser.setMultiSelectionEnabled(false);
            fileChoooser.setAcceptAllFileFilterUsed(false);

            FileNameExtensionFilter extFilter = new FileNameExtensionFilter("Scalable Vector Graphics (*.svg)", "svg");

            fileChoooser.setFileFilter(extFilter);

            if (fileChoooser.showOpenDialog(super.v) == JFileChooser.APPROVE_OPTION) {
                openFile(fileChoooser.getSelectedFile());
            }
        }

        private boolean openFile(File file) {
            boolean opened = false;

            try {
                opened = vc.fileLoad(file);
            } catch (IOException e) {
                LSEditor.logger.warning(e.getMessage());
            }

            return opened;
        }
    }


    /**
     * The <code>PanModeAction</code> is a class which create an <code>Action</code>
     * instance. This action handles event changing the current mode to selection mode.
     *
     * @author Cheow Yeong Chi
     *
     * @since 2.2
     *
     */
    public static class PanModeAction extends LSAbstractAction {

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>PanModeAction</code> instance with v component
         * <code>v</code> and no action name.
         * @param v Parent component
         */
        public PanModeAction(ResourceBundle b,LSView parent) {
            super(b.getString("action.pan.title"), KeyEvent.VK_P, null, parent);
        }

        /**
         * Construct a <code>PanModeAction</code> instance with v component
         * <code>v</code> and action name.
         * @param v Parent component
         * @param actionName Name of action component
         */
        public PanModeAction(LSView parent, String actionName) {
            super(actionName, "Pan Tool", KeyEvent.VK_P, null, parent);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            v.changeMode(LSUIEditingPanel.EditModeScheme.MODE_PAN);
        }
    }


    /**
     * The <code>SaveFileAction</code> is a class which create an <code>Action</code>
     * instance. This action handles event by saving the current file as it is on the
     * user view.
     *
     * @author Cheow Yeong Chi
     *
     * @since 2.2
     *
     */
    public static class SaveFileAction extends LSAbstractAction {

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>SaveFileAction</code> instance with v component
         * <code>v</code> and no action name.
         * @param v Parent component
         */
        public SaveFileAction(ResourceBundle b,LSView parent) {
            super(b.getString("action.save.title"), KeyEvent.VK_S, KeyStroke.getKeyStroke(KeyEvent.VK_S, getKeyEventMask()), parent);
        }

        /**
         * Construct a <code>SaveFileAction</code> instance with v component
         * <code>v</code> and action name.
         * @param v Parent component
         * @param actionName Name of action component
         */
        public SaveFileAction(LSView parent, String actionName) {
            super(actionName, "Save", KeyEvent.VK_S, KeyStroke.getKeyStroke(KeyEvent.VK_S, getKeyEventMask()), parent);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            v.saveFile();
        }
    }


    /**
     * The <code>SaveFileAsAction</code> is a class which create an <code>Action</code>
     * instance. This action handles event by saving the file as it is on the
     * user view as some other file other than the currently editing file.
     *
     * @author Cheow Yeong Chi
     *
     * @since 2.2
     *
     */
    public static class SaveFileAsAction extends LSAbstractAction {

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>SaveFileAsAction</code> instance with v component
         * <code>v</code> and no action name.
         * @param v Parent component
         */
        public SaveFileAsAction(ResourceBundle b,LSView parent) {
            super(b.getString("action.saveas.title"), KeyEvent.VK_A,
                  KeyStroke.getKeyStroke(KeyEvent.VK_S, getKeyEventMask() + InputEvent.SHIFT_DOWN_MASK), parent);
        }

        /**
         * Construct a <code>SaveFileAsAction</code> instance with v component
         * <code>v</code> and action name.
         * @param v Parent component
         * @param actionName Name of action component
         */
        public SaveFileAsAction(LSView parent, String actionName) {
            super(actionName, "Save As", KeyEvent.VK_A,
                  KeyStroke.getKeyStroke(KeyEvent.VK_S, getKeyEventMask() + InputEvent.SHIFT_DOWN_MASK), parent);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            v.fileSaveAs();
        }
    }


    /**
     * The <code>SelectAllAction</code> is a class which create an <code>Action</code>
     * instance. This action handles event by adding every element on view into selection
     * set.
     *
     * @author Cheow Yeong Chi
     *
     * @since 2.2
     *
     */
    public static class SelectAllAction extends LSAbstractAction {

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>SelectAllAction</code> instance with v component
         * <code>v</code> and no action name.
         * @param v Parent component
         */
        public SelectAllAction(ResourceBundle b,LSView parent) {
            super(b.getString("action.selall.title"), KeyEvent.VK_A, KeyStroke.getKeyStroke(KeyEvent.VK_A, getKeyEventMask()), parent);
        }

        /**
         * Construct a <code>SelectAllAction</code> instance with v component
         * <code>v</code> and action name.
         * @param v Parent component
         * @param actionName Name of action component
         */
        public SelectAllAction(LSView parent, String actionName) {
            super(actionName, "Select All", KeyEvent.VK_A, KeyStroke.getKeyStroke(KeyEvent.VK_A, getKeyEventMask()),
                  parent);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            vc.selectAll();
        }
    }


    /**
     * The <code>SelectModeAction</code> is a class which create an <code>Action</code>
     * instance. This action handles event changing the current mode to selection mode.
     *
     * @author Cheow Yeong Chi
     *
     * @since 2.2
     *
     */
    public static class SelectModeAction extends LSAbstractAction {

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>SelectModeAction</code> instance with v component
         * <code>v</code> and no action name.
         * @param v Parent component
         */
        public SelectModeAction(ResourceBundle b,LSView parent) {
            super(b.getString("action.selection.title"), KeyEvent.VK_S, null, parent);
        }

        /**
         * Construct a <code>SelectModeAction</code> instance with v component
         * <code>v</code> and action name.
         * @param v Parent component
         * @param actionName Name of action component
         */
        public SelectModeAction(LSView parent, String actionName) {
            super(actionName, "Selection Tool", KeyEvent.VK_S, null, parent);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            v.changeMode(LSUIEditingPanel.EditModeScheme.MODE_SELECT);
        }
    }

    /**
     * The <code>ToggleCodeViewAction</code> is a class which create an <code>Action</code>
     * instance. This action handles event by adding every element on view into selection
     * set.
     *
     * @author Cheow Yeong Chi
     *
     * @since 2.2
     *
     */
    public static class ToggleCodeViewAction extends LSAbstractAction {

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>ToggleCodeViewAction</code> instance with v component
         * <code>v</code> and no action name.
         * @param v Parent component
         */
        public ToggleCodeViewAction(ResourceBundle b,LSView parent) {
            super(b.getString("action.closecodeview.title"), KeyEvent.VK_D, KeyStroke.getKeyStroke(KeyEvent.VK_D, getKeyEventMask()),
                  parent);
        }

        /**
         * Construct a <code>SelectAllAction</code> instance with v component
         * <code>v</code> and action name.
         * @param v Parent component
         * @param actionName Name of action component
         */
        public ToggleCodeViewAction(LSView parent, String actionName) {
            super(actionName, "Hide code view area", KeyEvent.VK_D, KeyStroke.getKeyStroke(KeyEvent.VK_D, getKeyEventMask()),
                  parent);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            v.toggleCodeView();
        }
    }


    /**
     * The <code>UngroupAction</code> is a class which create an <code>Action</code>
     * instance. This action handles event by grouping elements in selection.
     *
     * @author Cheow Yeong Chi
     *
     * @since 2.2
     */
    public static class UngroupAction extends LSAbstractAction {

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>UngroupAction</code> instance with v component
         * <code>v</code> and no action name.
         * @param v Parent component
         */
        public UngroupAction(ResourceBundle b,LSView parent) {
            super(b.getString("action.ungroup.title"), KeyEvent.VK_U,
                  KeyStroke.getKeyStroke(KeyEvent.VK_G, getKeyEventMask() + InputEvent.SHIFT_DOWN_MASK), parent);
        }

        /**
         * Construct a <code>UngroupAction</code> instance with v component
         * <code>v</code> and action name.
         * @param v Parent component
         * @param actionName Name of action component
         */
        public UngroupAction(LSView parent, String actionName) {
            super(actionName, "Ungroup", KeyEvent.VK_U,
                  KeyStroke.getKeyStroke(KeyEvent.VK_G, getKeyEventMask() + InputEvent.SHIFT_DOWN_MASK), parent);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            vc.ungroup();
        }
    }


    /**
     * The <code>ZoomInViewAction</code> is a class which create an <code>Action</code>
     * instance. This action handles event by setting the zoom scale of the canvas view to be
     * <code>100%</code> larger.
     *
     * @author Cheow Yeong Chi
     *
     * @since 1.1
     *
     */
    public static class ZoomInViewAction extends LSAbstractAction {

        /*
         * PROPERTIES
         */

        /**
         * Partner action component that perform zoom out action.
         */
        private ZoomOutViewAction zoomOutPartnerAction;

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>ZoomOutViewAction</code> instance with v component
         * <code>v</code> and no action name.
         * @param v Parent component
         */
        public ZoomInViewAction(ResourceBundle b,LSView parent) {
            super(b.getString("action.zoomin.title"), KeyEvent.VK_I, KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, getKeyEventMask()), parent);
        }

        /**
         * Construct a <code>ZoomOutViewAction</code> instance with v component
         * <code>v</code> and action name.
         * @param v Parent component
         * @param actionName Name of action component
         */
        public ZoomInViewAction(LSView parent, String actionName) {
            super(actionName, "Zoom In", KeyEvent.VK_I, KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, getKeyEventMask()),
                  parent);
        }

        /**
         *
         * @param zoomOutPartnerAction Partner action component that perform zoom out action.
         */
        public void setZoomOutPartnerAction(ZoomOutViewAction zoomOutPartnerAction) {
            this.zoomOutPartnerAction = zoomOutPartnerAction;
            this.zoomOutPartnerAction.setZoomInPartnerAction(this);
        }

        /**
         * <p>Increase the <code>zoomScale</code> by 100% and sets the partner
         * to be enabled.</p>
         * {@inheritDoc}
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            float zoom = v.getZoomScale();

            if (zoom < LSCanvasProperties.DEFAULT_MAX_ZOOM_LEVEL) {
                zoom *= 2;

                if (zoom >= LSCanvasProperties.DEFAULT_MAX_ZOOM_LEVEL) {
                    setEnabled(false);
                }

                zoomOutPartnerAction.setEnabled(true);
                v.changeZoom(zoom);
                v.update();
            }
        }
    }


    /**
     * The <code>ZoomOutViewAction</code> is a class which create an <code>Action</code>
     * instance. This action handles event by setting the zoom scale of the canvas view to be
     * <code>100%</code> smaller.
     *
     * @author Cheow Yeong Chi
     *
     * @since 1.1
     *
     */
    public static class ZoomOutViewAction extends LSAbstractAction {

        /**
         * Partner action component that perform zoom out action.
         */
        private ZoomInViewAction zoomInPartnerAction;

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>ZoomOutViewAction</code> instance with v component
         * <code>v</code> and no action name. <p>Disabled by default.
         * @param v Parent component
         */
        public ZoomOutViewAction(ResourceBundle b,LSView parent) {
            super(b.getString("action.zoomout.title"), KeyEvent.VK_O, KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, getKeyEventMask()), parent);
        }

        /**
         * Construct a <code>ZoomOutViewAction</code> instance with v component
         * <code>v</code> and action name. <p>Disabled by default.
         * @param v Parent component
         * @param actionName Name of action component
         */
        public ZoomOutViewAction(LSView parent, String actionName) {
            super(actionName, "Zoom Out", KeyEvent.VK_O, KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, getKeyEventMask()),
                  parent);
        }

        /**
         *
         * @param zoomOutPartnerAction Partner action component that perform zoom out action.
         */
        public void setZoomInPartnerAction(ZoomInViewAction zoomInPartnerAction) {
            this.zoomInPartnerAction = zoomInPartnerAction;
        }

        /**
         * <p>Decrease the <code>zoomScale</code> by 100% and sets the partner
         * to be enabled.</p>
         * {@inheritDoc}
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            float zoom = v.getZoomScale();

            if (zoom > LSCanvasProperties.DEFAULT_MIN_ZOOM_LEVEL) {
                zoom /= 2;

                if (zoom <= LSCanvasProperties.DEFAULT_MIN_ZOOM_LEVEL) {
                    setEnabled(false);
                }

                zoomInPartnerAction.setEnabled(true);
                v.changeZoom(zoom);
                v.update();
            }
        }
    }

    /*
     * LANGUAGE TOGGLES
     */
    
    /**
     * The <code>ChineseLanguageToggleAction</code> is a class which create an <code>Action</code>
     * instance. This action handles event by setting the environment to be in Chinese language.
     *
     * @author Cheow Yeong Chi
     *
     * @since 2.6
     *
     */
    public static class ChineseLanguageToggleAction extends LSAbstractAction {

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>ChineseLanguageToggleAction</code> instance with v component
         * <code>v</code> and no action name.
         * @param v Parent component
         */
        public ChineseLanguageToggleAction(ResourceBundle b,LSView parent) {
            super(b.getString("action.chineseLanguage.name"), KeyEvent.VK_C, null, parent);
        }

        /**
         * Construct a <code>ChineseLanguageToggleAction</code> instance with v component
         * <code>v</code> and action name. <p>
         * @param v Parent component
         * @param actionName Name of action component
         */
        public ChineseLanguageToggleAction(LSView parent, String actionName) {
            super(actionName, "Chinese", KeyEvent.VK_C, null, parent);
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            putValue(SELECTED_KEY, Boolean.TRUE);
            LSEditor.titleBundle = ResourceBundle.getBundle("rocks6205.system.properties.LSEditor",
                    new Locale("zh", "CN"));
            v.reloadBundle();
        }
    }
    
    /**
     * The <code>EnglishLanguageToggleAction</code> is a class which create an <code>Action</code>
     * instance. This action handles event by setting the environment to be in English language.
     *
     * @author Cheow Yeong Chi
     *
     * @since 2.6
     *
     */
    public static class EnglishLanguageToggleAction extends LSAbstractAction {

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>EnglishLanguageToggleAction</code> instance with v component
         * <code>v</code> and no action name.
         * @param v Parent component
         */
        public EnglishLanguageToggleAction(ResourceBundle b,LSView parent) {
            super(b.getString("action.englishLanguage.name"), KeyEvent.VK_E, null, parent);
        }

        /**
         * Construct a <code>EnglishLanguageToggleAction</code> instance with v component
         * <code>v</code> and action name. <p>
         * @param v Parent component
         * @param actionName Name of action component
         */
        public EnglishLanguageToggleAction(LSView parent, String actionName) {
            super(actionName, "English", KeyEvent.VK_E, null, parent);
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            putValue(SELECTED_KEY, Boolean.TRUE);
            LSEditor.titleBundle = ResourceBundle.getBundle("rocks6205.system.properties.LSEditor",
                    new Locale("en", "US"));
            v.reloadBundle();
        }
    }


    /**
     * The <code>JapaneseLanguageToggleAction</code> is a class which create an <code>Action</code>
     * instance. This action handles event by setting the environment to be in Japanese language.
     *
     * @author Cheow Yeong Chi
     *
     * @since 2.6
     *
     */
    public static class JapaneseLanguageToggleAction extends LSAbstractAction {

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>JapaneseLanguageToggleAction</code> instance with v component
         * <code>v</code> and no action name.
         * @param v Parent component
         */
        public JapaneseLanguageToggleAction(ResourceBundle b,LSView parent) {
            super(b.getString("action.japaneseLanguage.name"), KeyEvent.VK_J, null, parent);
        }

        /**
         * Construct a <code>JapaneseLanguageToggleAction</code> instance with v component
         * <code>v</code> and action name. <p>
         * @param v Parent component
         * @param actionName Name of action component
         */
        public JapaneseLanguageToggleAction(LSView parent, String actionName) {
            super(actionName, "Japanese", KeyEvent.VK_J, null, parent);
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            putValue(SELECTED_KEY, Boolean.TRUE);
            LSEditor.titleBundle = ResourceBundle.getBundle("rocks6205.system.properties.LSEditor",
                    new Locale("ja", "JP"));
            v.reloadBundle();
        }
    }


    /**
     * The <code>MalayLanguageToggleAction</code> is a class which create an <code>Action</code>
     * instance. This action handles event by setting the environment to be in Malay language.
     *
     * @author Cheow Yeong Chi
     *
     * @since 2.6
     *
     */
    public static class MalayLanguageToggleAction extends LSAbstractAction {

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>MalayLanguageToggleAction</code> instance with v component
         * <code>v</code> and no action name.
         * @param v Parent component
         */
        public MalayLanguageToggleAction(ResourceBundle b,LSView parent) {
            super(b.getString("action.malayLanguage.name"), KeyEvent.VK_M, null, parent);
        }

        /**
         * Construct a <code>TamilLanguageToggleAction</code> instance with v component
         * <code>v</code> and action name. <p>
         * @param v Parent component
         * @param actionName Name of action component
         */
        public MalayLanguageToggleAction(LSView parent, String actionName) {
            super(actionName, "Malay", KeyEvent.VK_M, null, parent);
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            putValue(SELECTED_KEY, Boolean.TRUE);
            LSEditor.titleBundle = ResourceBundle.getBundle("rocks6205.system.properties.LSEditor",
                    new Locale("ms", "MY"));
            v.reloadBundle();
        }
    }


    /**
     * The <code>TamilLanguageToggleAction</code> is a class which create an <code>Action</code>
     * instance. This action handles event by setting the environment to be in Tamil language.
     *
     * @author Cheow Yeong Chi
     *
     * @since 2.6
     *
     */
    public static class TamilLanguageToggleAction extends LSAbstractAction {

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>TamilLanguageToggleAction</code> instance with v component
         * <code>v</code> and no action name.
         * @param v Parent component
         */
        public TamilLanguageToggleAction(LSView parent) {
            super("Tamil", KeyEvent.VK_T, null, parent);
        }

        /**
         * Construct a <code>TamilLanguageToggleAction</code> instance with v component
         * <code>v</code> and action name. <p>
         * @param v Parent component
         * @param actionName Name of action component
         */
        public TamilLanguageToggleAction(LSView parent, String actionName) {
            super(actionName, "Tamil", KeyEvent.VK_T, null, parent);
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            putValue(SELECTED_KEY, Boolean.TRUE);
            LSEditor.titleBundle = ResourceBundle.getBundle("rocks6205.system.properties.LSEditor",
                    new Locale("ta", "IN"));
            v.reloadBundle();
        }
    }
    
    /**
     * The <code>ThaiLanguageToggleAction</code> is a class which create an <code>Action</code>
     * instance. This action handles event by setting the environment to be in Thai language.
     *
     * @author Cheow Yeong Chi
     *
     * @since 2.6
     *
     */
    public static class ThaiLanguageToggleAction extends LSAbstractAction {

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>ThaiLanguageToggleAction</code> instance with v component
         * <code>v</code> and no action name.
         * @param v Parent component
         */
        public ThaiLanguageToggleAction(ResourceBundle b,LSView parent) {
            super(b.getString("action.thaiLanguage.name"), KeyEvent.VK_T, null, parent);
        }

        /**
         * Construct a <code>ThaiLanguageToggleAction</code> instance with v component
         * <code>v</code> and action name. <p>
         * @param v Parent component
         * @param actionName Name of action component
         */
        public ThaiLanguageToggleAction(LSView parent, String actionName) {
            super(actionName, "Thai", KeyEvent.VK_T, null, parent);
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            putValue(SELECTED_KEY, Boolean.TRUE);
            LSEditor.titleBundle = ResourceBundle.getBundle("rocks6205.system.properties.LSEditor",
                    new Locale("th", "TH"));
            v.reloadBundle();
        }
    }
}