package rocks6205.editor.events;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.mvc.SVGEditorView;

import rocks6205.svg.adt.SVGColorScheme;

import rocks6205.system.properties.OSValidator;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import static javax.swing.Action.ACCELERATOR_KEY;
import static javax.swing.Action.MNEMONIC_KEY;
import static javax.swing.Action.SHORT_DESCRIPTION;

/**
 * The <code>LSAction</code> is an abstract class which create <code>Action</code>
 * instances. Instances can carry out similar handling of event is enabled without redefining
 * the <code>actionPerformed()</code> function in different classes.
 *
 * @author Cheow Yeong Chi
 *
 * @since 1.1
 *
 */
public abstract class LSAction extends AbstractAction {
    private static final long serialVersionUID = 9085407834848872724L;

    /**
     * Parent component (Usually an <code>SVGView</code> object.)
     */
    protected SVGEditorView parent;

    /*
     * CONSTRUCTOR
     */

    /**
     * Construct a <code>SVGViewMenuAction</code> type object with <code>tooltipText</code>,
     * <code>mnemonic</code> key, <code>keyStroke</code> and parent component
     * <code>parent</code>.
     *
     * @param tooltipText Tool tip text while mouse over action component
     * @param mnemonic Mnemonic key that allows users to choose a control by pressing a single key
     * @param keyStroke Key action on the keyboard
     * @param parent Parent component
     */
    public LSAction(String tooltipText, Integer mnemonic, KeyStroke keyStroke, SVGEditorView parent) {
        putValue(SHORT_DESCRIPTION, tooltipText);
        putValue(MNEMONIC_KEY, mnemonic);
        putValue(ACCELERATOR_KEY, keyStroke);
        this.parent = parent;
    }

    /**
     * Construct a <code>SVGViewMenuAction</code> type object with action <code>name</code>,
     * <code>tooltipText</code>, <code>mnemonic</code> key, <code>keyStroke</code> and parent component
     * <code>parent</code>.
     *
     * @param name Name of action component
     * @param tooltipText Tool tip text while mouse over action component
     * @param mnemonic Mnemonic key that allows users to choose a control by pressing a single key
     * @param keyStroke Key action on the keyboard
     * @param parent Parent component
     */
    public LSAction(String name, String tooltipText, Integer mnemonic, KeyStroke keyStroke, SVGEditorView parent) {
        putValue(Action.NAME, name);
        putValue(SHORT_DESCRIPTION, tooltipText);
        putValue(MNEMONIC_KEY, mnemonic);
        putValue(ACCELERATOR_KEY, keyStroke);
        this.parent = parent;
    }

    private static int getKeyEventMask() {
        if (OSValidator.isMac()) {
            return ActionEvent.META_MASK;
        }

        return ActionEvent.CTRL_MASK;
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
    public static class DeleteAction extends LSAction {

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>DeleteAction</code> instance with parent component
         * <code>parent</code> and no action name.
         * @param parent Parent component
         */
        public DeleteAction(SVGEditorView parent) {
            super("Delete", KeyEvent.VK_E, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), parent);
        }

        /**
         * Construct a <code>DeleteAction</code> instance with parent component
         * <code>parent</code> and action name.
         * @param parent Parent component
         * @param actionName Name of action component
         */
        public DeleteAction(SVGEditorView parent, String actionName) {
            super(actionName, "Delete", KeyEvent.VK_E, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), parent);
        }

        @Override
        public void actionPerformed(ActionEvent e) {}
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
    public static class DeselectAllAction extends LSAction {

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>DeselectAllAction</code> instance with parent component
         * <code>parent</code> and no action name.
         * @param parent Parent component
         */
        public DeselectAllAction(SVGEditorView parent) {
            super("Deselect All", KeyEvent.VK_D,
                  KeyStroke.getKeyStroke(KeyEvent.VK_A, getKeyEventMask() + InputEvent.SHIFT_DOWN_MASK), parent);
        }

        /**
         * Construct a <code>DeselectAllAction</code> instance with parent component
         * <code>parent</code> and action name.
         * @param parent Parent component
         * @param actionName Name of action component
         */
        public DeselectAllAction(SVGEditorView parent, String actionName) {
            super(actionName, "Deselect All", KeyEvent.VK_D,
                  KeyStroke.getKeyStroke(KeyEvent.VK_A, getKeyEventMask() + InputEvent.SHIFT_DOWN_MASK), parent);
        }

        @Override
        public void actionPerformed(ActionEvent e) {}
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
    public static class DrawCircleAction extends LSAction {
        private static final long serialVersionUID = -6578149781110081473L;

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>DrawCircleAction</code> instance with parent component
         * <code>parent</code> and no action name. <p>Disabled by default.
         * @param parent Parent component
         */
        public DrawCircleAction(SVGEditorView parent) {
            super("Draw Circle", KeyEvent.VK_C, KeyStroke.getKeyStroke(KeyEvent.VK_C, getKeyEventMask()), parent);
            setEnabled(false);
        }

        /**
         * Construct a <code>FillAction</code> instance with parent component
         * <code>parent</code>.
         * @param parent Parent component
         * @param actionName Name of action component
         */
        public DrawCircleAction(SVGEditorView parent, String actionName) {
            super(actionName, "Draw Circle", KeyEvent.VK_C, KeyStroke.getKeyStroke(KeyEvent.VK_C, getKeyEventMask()),
                  parent);
        }

        @Override
        public void actionPerformed(ActionEvent event) {}
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
    public static class DrawLineAction extends LSAction {
        private static final long serialVersionUID = -6578149781110081473L;

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>DrawLineAction</code> instance with parent component
         * <code>parent</code> and no action name. <p>Disabled by default.
         * @param parent Parent component
         */
        public DrawLineAction(SVGEditorView parent) {
            super("Draw Line", KeyEvent.VK_L, KeyStroke.getKeyStroke(KeyEvent.VK_L, getKeyEventMask()), parent);
            setEnabled(false);
        }

        /**
         * Construct a <code>FillAction</code> instance with parent component
         * <code>parent</code>.
         * @param parent Parent component
         * @param actionName Name of action component
         */
        public DrawLineAction(SVGEditorView parent, String actionName) {
            super(actionName, "Draw Line", KeyEvent.VK_L, KeyStroke.getKeyStroke(KeyEvent.VK_L, getKeyEventMask()),
                  parent);
        }

        @Override
        public void actionPerformed(ActionEvent event) {}
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
    public static class DrawRectAction extends LSAction {
        private static final long serialVersionUID = -6578149781110081473L;

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>DrawLineAction</code> instance with parent component
         * <code>parent</code> and no action name. <p>Disabled by default.
         * @param parent Parent component
         */
        public DrawRectAction(SVGEditorView parent) {
            super("Draw Rect", KeyEvent.VK_R, KeyStroke.getKeyStroke(KeyEvent.VK_R, getKeyEventMask()), parent);
            setEnabled(false);
        }

        /**
         * Construct a <code>FillAction</code> instance with parent component
         * <code>parent</code>.
         * @param parent Parent component
         * @param actionName Name of action component
         */
        public DrawRectAction(SVGEditorView parent, String actionName) {
            super(actionName, "Draw Rect", KeyEvent.VK_R, KeyStroke.getKeyStroke(KeyEvent.VK_R, getKeyEventMask()),
                  parent);
        }

        @Override
        public void actionPerformed(ActionEvent event) {}
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
    public static class ExitAction extends LSAction {
        private static final long serialVersionUID = 6507259946341784118L;

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>ExitAction</code> instance with parent component
         * <code>parent</code> and no action name.
         * @param parent Parent component
         */
        public ExitAction(SVGEditorView parent) {
            super("Exit Program", KeyEvent.VK_X, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK), parent);
        }

        /**
         * Construct a <code>ExitAction</code> instance with parent component
         * <code>parent</code>.
         * @param parent Parent component
         * @param actionName Name of action component
         */
        public ExitAction(SVGEditorView parent, String actionName) {
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
     * The <code>FillAction</code> is a class which create an <code>Action</code>
     * instance. This action handles event by setting the color used for painting shapes
     *
     *
     * @author Sugar CheeSheen Chan
     *
     * @since 2.0
     *
     */
    public static class FillAction extends LSAction {
        private static final long serialVersionUID = -6578149781110081473L;
        private Color             color;
        private SVGColorScheme    svgColorScheme;

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>FillAction</code> instance with parent component
         * <code>parent</code> and no action name. <p>Disabled by default.
         * @param parent Parent component
         */
        public FillAction(SVGEditorView parent) {
            super("Fill Color", KeyEvent.VK_F, KeyStroke.getKeyStroke(KeyEvent.VK_F, getKeyEventMask()), parent);
        }

        /**
         * Construct a <code>FillAction</code> instance with parent component
         * <code>parent</code>.
         * @param parent Parent component
         * @param actionName Name of action component
         */
        public FillAction(SVGEditorView parent, String actionName) {
            super(actionName, "Fill Color", KeyEvent.VK_F, KeyStroke.getKeyStroke(KeyEvent.VK_F, getKeyEventMask()),
                  parent);
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            color = JColorChooser.showDialog(super.parent, "Choose a color", color);

            if (color == null) {
                svgColorScheme = SVGColorScheme.getColorFromKeyword("black");
            } else {
                svgColorScheme = new SVGColorScheme(color.getRed(), color.getGreen(), color.getBlue());
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
    public static class GroupAction extends LSAction {

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>GroupAction</code> instance with parent component
         * <code>parent</code> and no action name.
         * @param parent Parent component
         */
        public GroupAction(SVGEditorView parent) {
            super("Group", KeyEvent.VK_G, KeyStroke.getKeyStroke(KeyEvent.VK_G, getKeyEventMask()), parent);
        }

        /**
         * Construct a <code>GroupAction</code> instance with parent component
         * <code>parent</code> and action name.
         * @param parent Parent component
         * @param actionName Name of action component
         */
        public GroupAction(SVGEditorView parent, String actionName) {
            super(actionName, "Group", KeyEvent.VK_G, KeyStroke.getKeyStroke(KeyEvent.VK_G, getKeyEventMask()), parent);
        }

        @Override
        public void actionPerformed(ActionEvent e) {}
    }


    /**
     * The <code>NewDocumentAction</code> is a class which create an <code>Action</code>
     * instance. This action handles event by creating a new document according to user
     * settings.
     * @author Cheow Yeong Chi
     *
     * @since 2.2
     */
    public static class NewDocumentAction extends LSAction {

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>NewDocumentAction</code> instance with parent component
         * <code>parent</code> and no action name.
         * @param parent Parent component
         */
        public NewDocumentAction(SVGEditorView parent) {
            super("New..", KeyEvent.VK_N, KeyStroke.getKeyStroke(KeyEvent.VK_N, getKeyEventMask()), parent);
        }

        /**
         * Construct a <code>NewDocumentAction</code> instance with parent component
         * <code>parent</code> and action name.
         * @param parent Parent component
         * @param actionName Name of action component
         */
        public NewDocumentAction(SVGEditorView parent, String actionName) {
            super(actionName, "New..", KeyEvent.VK_N, KeyStroke.getKeyStroke(KeyEvent.VK_N, getKeyEventMask()), parent);
        }

        @Override
        public void actionPerformed(ActionEvent e) {}
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
    public static class OpenFileAction extends LSAction {
        private static final long serialVersionUID = -7823707833188816535L;

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>OpenFileAction</code> instance with parent component
         * <code>parent</code> and no action name.
         *
         * @param parent Parent component
         */
        public OpenFileAction(SVGEditorView parent) {
            super("Open a file", KeyEvent.VK_O, KeyStroke.getKeyStroke(KeyEvent.VK_O, getKeyEventMask()), parent);
        }

        /**
         * Construct a <code>OpenFileAction</code> instance with parent component
         * <code>parent</code>.
         * @param parent Parent component
         * @param actionName Name of action component
         */
        public OpenFileAction(SVGEditorView parent, String actionName) {
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

//          if (fileChoooser.showOpenDialog(super.parent) == JFileChooser.APPROVE_OPTION) {
//              super.parent.getController().fileLoad(fileChoooser.getSelectedFile());
//          }
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
    public static class SaveFileAction extends LSAction {

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>SaveFileAction</code> instance with parent component
         * <code>parent</code> and no action name.
         * @param parent Parent component
         */
        public SaveFileAction(SVGEditorView parent) {
            super("Save", KeyEvent.VK_S, KeyStroke.getKeyStroke(KeyEvent.VK_S, getKeyEventMask()), parent);
        }

        /**
         * Construct a <code>SaveFileAction</code> instance with parent component
         * <code>parent</code> and action name.
         * @param parent Parent component
         * @param actionName Name of action component
         */
        public SaveFileAction(SVGEditorView parent, String actionName) {
            super(actionName, "Save", KeyEvent.VK_S, KeyStroke.getKeyStroke(KeyEvent.VK_S, getKeyEventMask()), parent);
        }

        @Override
        public void actionPerformed(ActionEvent e) {}
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
    public static class SaveFileAsAction extends LSAction {

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>SaveFileAsAction</code> instance with parent component
         * <code>parent</code> and no action name.
         * @param parent Parent component
         */
        public SaveFileAsAction(SVGEditorView parent) {
            super("Save As", KeyEvent.VK_A,
                  KeyStroke.getKeyStroke(KeyEvent.VK_S, getKeyEventMask() + InputEvent.SHIFT_DOWN_MASK), parent);
        }

        /**
         * Construct a <code>SaveFileAsAction</code> instance with parent component
         * <code>parent</code> and action name.
         * @param parent Parent component
         * @param actionName Name of action component
         */
        public SaveFileAsAction(SVGEditorView parent, String actionName) {
            super(actionName, "Save As", KeyEvent.VK_A,
                  KeyStroke.getKeyStroke(KeyEvent.VK_S, getKeyEventMask() + InputEvent.SHIFT_DOWN_MASK), parent);
        }

        @Override
        public void actionPerformed(ActionEvent e) {}
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
    public static class SelectAllAction extends LSAction {

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>SelectAllAction</code> instance with parent component
         * <code>parent</code> and no action name.
         * @param parent Parent component
         */
        public SelectAllAction(SVGEditorView parent) {
            super("Select All", KeyEvent.VK_A, KeyStroke.getKeyStroke(KeyEvent.VK_A, getKeyEventMask()), parent);
        }

        /**
         * Construct a <code>SelectAllAction</code> instance with parent component
         * <code>parent</code> and action name.
         * @param parent Parent component
         * @param actionName Name of action component
         */
        public SelectAllAction(SVGEditorView parent, String actionName) {
            super(actionName, "Select All", KeyEvent.VK_A, KeyStroke.getKeyStroke(KeyEvent.VK_A, getKeyEventMask()),
                  parent);
        }

        @Override
        public void actionPerformed(ActionEvent e) {}
    }


    /**
     * The <code>UngroupAction</code> is a class which create an <code>Action</code>
     * instance. This action handles event by grouping elements in selection.
     *
     * @author Cheow Yeong Chi
     *
     * @since 2.2
     */
    public static class UngroupAction extends LSAction {

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>UngroupAction</code> instance with parent component
         * <code>parent</code> and no action name.
         * @param parent Parent component
         */
        public UngroupAction(SVGEditorView parent) {
            super("Ungroup", KeyEvent.VK_U,
                  KeyStroke.getKeyStroke(KeyEvent.VK_G, getKeyEventMask() + InputEvent.SHIFT_DOWN_MASK), parent);
        }

        /**
         * Construct a <code>UngroupAction</code> instance with parent component
         * <code>parent</code> and action name.
         * @param parent Parent component
         * @param actionName Name of action component
         */
        public UngroupAction(SVGEditorView parent, String actionName) {
            super(actionName, "Ungroup", KeyEvent.VK_U,
                  KeyStroke.getKeyStroke(KeyEvent.VK_G, getKeyEventMask() + InputEvent.SHIFT_DOWN_MASK), parent);
        }

        @Override
        public void actionPerformed(ActionEvent e) {}
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
    public static class ZoomInViewAction extends LSAction {
        private static final long serialVersionUID = 1180439021996674018L;

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
         * Construct a <code>ZoomOutViewAction</code> instance with parent component
         * <code>parent</code> and no action name.
         * @param parent Parent component
         */
        public ZoomInViewAction(SVGEditorView parent) {
            super("Zoom In", KeyEvent.VK_I, KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, getKeyEventMask()), parent);
            this.setEnabled(false);
        }

        /**
         * Construct a <code>ZoomOutViewAction</code> instance with parent component
         * <code>parent</code> and action name.
         * @param parent Parent component
         * @param actionName Name of action component
         */
        public ZoomInViewAction(SVGEditorView parent, String actionName) {
            super(actionName, "Zoom In", KeyEvent.VK_I, KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, getKeyEventMask()),
                  parent);
            this.setEnabled(false);
        }

        /**
         *
         * @param zoomOutPartnerAction Partner action component that perform zoom out action.
         */
        public void setZoomOutPartnerAction(ZoomOutViewAction zoomOutPartnerAction) {
            this.zoomOutPartnerAction = zoomOutPartnerAction;
        }

        /**
         * <p>Increase the <code>zoomScale</code> by 100% and sets the partner
         * to be enabled.</p>
         * {@inheritDoc}
         */
        @Override
        public void actionPerformed(ActionEvent event) {}
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
    public static class ZoomOutViewAction extends LSAction {
        private static final long serialVersionUID = -6578149781110081473L;

        /*
         * CONSTRUCTOR
         */

        /**
         * Construct a <code>ZoomOutViewAction</code> instance with parent component
         * <code>parent</code> and no action name. <p>Disabled by default.
         * @param parent Parent component
         */
        public ZoomOutViewAction(SVGEditorView parent) {
            super("Zoom Out", KeyEvent.VK_O, KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, getKeyEventMask()), parent);
            setEnabled(false);
        }

        /**
         * Construct a <code>ZoomOutViewAction</code> instance with parent component
         * <code>parent</code> and action name. <p>Disabled by default.
         * @param parent Parent component
         * @param actionName Name of action component
         */
        public ZoomOutViewAction(SVGEditorView parent, String actionName) {
            super(actionName, "Zoom Out", KeyEvent.VK_O, KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, getKeyEventMask()),
                  parent);
            setEnabled(false);
        }

        /**
         * <p>Decrease the <code>zoomScale</code> by 100% and sets the partner
         * to be enabled.</p>
         * {@inheritDoc}
         */
        @Override
        public void actionPerformed(ActionEvent event) {}
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
