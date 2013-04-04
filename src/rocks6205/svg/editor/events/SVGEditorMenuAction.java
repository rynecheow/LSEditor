package rocks6205.svg.editor.events;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.svg.adt.SVGColorScheme;
import rocks6205.svg.mvc.SVGEditorView;
import rocks6205.svg.properties.OSValidator;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * The <code>SVGViewMenuAction</code> is an abstract class which create <code>Action</code>
 * instances. Instances can carry out similar handling of event is enabled without redefining
 * the <code>actionPerformed()</code> function in different classes.
 *
 * @author Cheow Yeong Chi
 *
 * @since 1.1
 *
 */
public abstract class SVGEditorMenuAction extends AbstractAction {
    private static final long serialVersionUID = 9085407834848872724L;

    /**
     * Parent component (Usually an <code>SVGView</code> object.)
     */
    private SVGEditorView parent;

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
    public SVGEditorMenuAction(String tooltipText, Integer mnemonic, KeyStroke keyStroke, SVGEditorView parent) {
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
    public SVGEditorMenuAction(String name, String tooltipText, Integer mnemonic, KeyStroke keyStroke,
                               SVGEditorView parent) {
        putValue(Action.NAME, name);
        putValue(SHORT_DESCRIPTION, tooltipText);
        putValue(MNEMONIC_KEY, mnemonic);
        putValue(ACCELERATOR_KEY, keyStroke);
        this.parent = parent;
    }

    public static int getKeyEventMask() {
        if (OSValidator.isMac()) {
            return ActionEvent.META_MASK;
        }

        return ActionEvent.CTRL_MASK;
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
    public static class ExitAction extends SVGEditorMenuAction {
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
        public void actionPerformed(ActionEvent event) {
            int closeCf = JOptionPane.showConfirmDialog(null, "Exit SVG Editor?", "Confirm exit",
                              JOptionPane.WARNING_MESSAGE);

            if (closeCf == JOptionPane.YES_OPTION) {
                System.exit(0);
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
    public static class OpenFileAction extends SVGEditorMenuAction {
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
        public void actionPerformed(ActionEvent event) {
            JFileChooser fileChoooser = new JFileChooser();

            fileChoooser.setMultiSelectionEnabled(false);
            fileChoooser.setAcceptAllFileFilterUsed(false);

            FileNameExtensionFilter extFilter = new FileNameExtensionFilter("Scalable Vector Graphics (*.svg)", "svg");

            fileChoooser.setFileFilter(extFilter);

//            if (fileChoooser.showOpenDialog(super.parent) == JFileChooser.APPROVE_OPTION) {
//                super.parent.getController().fileLoad(fileChoooser.getSelectedFile());
//            }
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
    public static class ZoomInViewAction extends SVGEditorMenuAction {
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
         * <code>parent</code> and no action name.
         * @param parent Parent component
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
        public void actionPerformed(ActionEvent event) {
//            SVGImageCanvas.setZoomScale(SVGImageCanvas.getZoomScale() + 1);
//            super.parent.getModel().render();
            zoomOutPartnerAction.setEnabled(true);
//            super.parent.getController().setZoomOut(true);

//            if (SVGImageCanvas.getZoomScale() > 5) {
//                super.parent.getController().setZoomOut(false);
//            }
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
    public static class ZoomOutViewAction extends SVGEditorMenuAction {
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
         * <code>parent</code> and no action name. <p>Disabled by default.
         * @param parent Parent component
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
        public void actionPerformed(ActionEvent event) {
//            SVGImageCanvas.setZoomScale(SVGImageCanvas.getZoomScale() - 1);
//            super.parent.getModel().render();

//            if (SVGImageCanvas.getZoomScale() < 2) {

                // setEnabled(false);
//                super.parent.getController().setZoomOut(false);
//            }
        }
    }
    
    /**
     * The <code>FillAction</code> is a class which create an <code>Action</code>
     * instance. This action handles event by setting the color used for painting shapes
     * 
     *
    * @author Sugar CheeSheen Chan
     *
     * @since 1.0
     *
     */
    public static class FillAction extends SVGEditorMenuAction {
        private static final long serialVersionUID = -6578149781110081473L;
        
        private Color color;
        private SVGColorScheme svgColorScheme;

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
            setEnabled(false);
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
        
        public void actionPerformed(ActionEvent event) {
            color = JColorChooser.showDialog( super.parent , "Choose a color", color );
            
            if( color == null ) {
        	svgColorScheme = SVGColorScheme.getColorFromKeyword("black");
            }else{
        	svgColorScheme = new SVGColorScheme( color.getRed() , 
            	    color.getGreen() , color.getBlue() );
            }
            //super.parent.getModel().getSVGElement()
            System.out.println(svgColorScheme);
        
        }
    }
    
    /**
     * The <code>DrawLineAction</code> is a class which create an <code>Action</code>
     * instance. This action handles event by setting the color used for painting shapes
     * 
     *
    * @author Sugar CheeSheen Chan
     *
     * @since 1.0
     *
     */
    public static class DrawLineAction extends SVGEditorMenuAction {
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
        
        public void actionPerformed(ActionEvent event) {
            
        
        }
    }
    
    /**
     * The <code>DrawRectAction</code> is a class which create an <code>Action</code>
     * instance. This action handles event by setting the color used for painting shapes
     * 
     *
    * @author Sugar CheeSheen Chan
     *
     * @since 1.0
     *
     */
    public static class DrawRectAction extends SVGEditorMenuAction {
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
        
        public void actionPerformed(ActionEvent event) {
            
        
        }
    }
    
    /**
     * The <code>DrawCircleAction</code> is a class which create an <code>Action</code>
     * instance. This action handles event by setting the color used for painting shapes
     * 
     *
    * @author Sugar CheeSheen Chan
     *
     * @since 1.0
     *
     */
    public static class DrawCircleAction extends SVGEditorMenuAction {
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
        
        public void actionPerformed(ActionEvent event) {
        }
    }
}