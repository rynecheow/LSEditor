package rocks6205.editor.mvc;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.viewcomponents.LSUIEditingPanel;
import rocks6205.editor.viewcomponents.LSUIMenubar;
import rocks6205.editor.viewcomponents.LSUIMiscPanel;
import rocks6205.editor.viewcomponents.LSUINavigationPanel;
import rocks6205.editor.viewcomponents.LSUIProtocol;
import rocks6205.editor.viewcomponents.LSUISideToolbar;
import rocks6205.editor.viewcomponents.LSUIStatusPanel;
import rocks6205.editor.viewcomponents.LSUITopToolbar;
import rocks6205.editor.viewcomponents.LSUIWelcomeDialog;

import rocks6205.svg.elements.SVGGenericElement;

import rocks6205.system.properties.LSSVGEditorGUITheme;
import rocks6205.system.properties.OSValidator;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.text.MessageFormat;

import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * A class defining how the main user interface should look like.
 * <p>
 *
 * @author Toh Huey Jing
 * @author Cheow Yeong Chi
 *
 * @since 1.2
 */
public final class SVGEditorView extends JFrame implements LSUIProtocol {
    private static final long serialVersionUID = 6764861773639452353L;

    /*
     * GUI COMPONENTS
     */
    private Container c = getContentPane();

    /*
     * SIZE PROPERTIES
     */
    private int       width;
    private int       height;
    private Dimension screen;
    private int       x;
    private int       y;
    private int       margin;
    private Dimension frameDim;

    /*
     * PROPERTIES
     */
    private float   zoomScale;
    private boolean isZoomChanged;
    private File    displayedFile;
    private String  documentTitle;

    /**
     * Model object
     */
    private SVGEditorModel model;

    /**
     * Controller object
     */
    private SVGEditorViewController controller;

    /*
     * GUI COMPONENTS
     */
    private JScrollPane         scrollPane;
    private LSUIEditingPanel    editPanel;
    private LSUIMenubar         menuBar;
    private LSUITopToolbar      topBar;
    private LSUISideToolbar     sideBar;
    private LSUIStatusPanel     statusPanel;
    private LSUINavigationPanel navPanel;
    private LSUIMiscPanel       miscPanel;

    /*
     * ACTION COMPONENTS
     */

    /**
     * Handle pan events.
     */

    /*
     * CONSTRUCTOR
     */

    /**
     * Construct an <code>SVGView</code> instance with components initialised
     * and properly customised.
     */
    public SVGEditorView(SVGEditorViewController c) {
        super();
        setController(c);
        initialise();
        customise();

//      showWelcomeScreen();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialise() {
        setUpProperties();
        isZoomChanged = false;
        zoomScale     = 1.0f;
        menuBar       = new LSUIMenubar(this);
        topBar        = new LSUITopToolbar("Editing Tools", this);
        sideBar       = new LSUISideToolbar("Selection Tools", this);
        statusPanel   = new LSUIStatusPanel(this);
        navPanel      = new LSUINavigationPanel(this);
        miscPanel     = new LSUIMiscPanel(this);
        scrollPane    = new JScrollPane();
        editPanel     = new LSUIEditingPanel(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void customise() {
        layoutChildComponents();
        setUpEditingPanel();
        layoutFrame();
        setClosingEvent();
    }

    /**
     * Prompt exit confirmation while user clicks on 'x' button on the window
     */
    private void setClosingEvent() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent winEvt) {
                int closeCf = JOptionPane.showConfirmDialog(null, "Exit SVG Editor?", "Confirm exit",
                                  JOptionPane.WARNING_MESSAGE);

                if (closeCf == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    /*
     * ACCESSORS
     */

    /**
     * @return Model object
     */
    public SVGEditorModel getModel() {
        return model;
    }

    /**
     * @return Controller object
     */
    public SVGEditorViewController getController() {
        return controller;
    }

    /*
     * MUTATORS
     */

    /**
     * @param controller Controller object
     */
    public void setController(SVGEditorViewController controller) {
        this.controller = controller;
    }

    /**
     * @param model Model object
     */
    public void setModel(SVGEditorModel model) {
        this.model = model;
    }

    public void update() {
        ArrayList<SVGGenericElement> selections         = new ArrayList<>(controller.getSelections());
        boolean                      isAnySelected      = !selections.isEmpty();
        File                         currentFile        = controller.getCurrentFile();
        boolean                      isFileChanged      = (currentFile != displayedFile);
        boolean                      needRepaint        = controller.isDocumentModified() || isZoomChanged || isFileChanged;

        sideBar.updateActionStatusFromView(isAnySelected);
        menuBar.updateActionStatusFromView(isAnySelected);
        editPanel.setSelections(selections);
        editPanel.drawOverlay();

        if (selections.size() == 1) {
            SVGGenericElement elem = selections.get(0);

//          fillChooserButton.setPaint(elem.getResultantFill());
//          fillChooserButton.needRepaint();
//          strokeChooserButton.setPaint(elem.getResultantStroke());
//          strokeChooserButton.needRepaint();
//          strokeWidthInputPanel.setLength(elem.getResultantStrokeWidth());
        }

        if (isZoomChanged) {
            isZoomChanged = false;
        }

        if (isFileChanged) {
            displayedFile = currentFile;
        }

        if (needRepaint) {
            editPanel.paintCanvas(controller.renderImage(zoomScale));
        }
    }

    public float getZoomScale() {
        return zoomScale;
    }

    public void changeZoom(float zoom) {
        isZoomChanged  = true;
        this.zoomScale = zoom;
    }

    public void resetZoom() {
        isZoomChanged  = false;
        this.zoomScale = 1.00f;
    }

    public boolean isZoomChanged() {
        return isZoomChanged;
    }

    public void setDisplayedFile(File d) {
        displayedFile = d;
    }

    public File getDisplayedFile() {
        return displayedFile;
    }

    private void showWelcomeScreen() {
        new LSUIWelcomeDialog(this).display();
    }

    public void layoutFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(frameDim);
        setMinimumSize(frameDim);
        setLayout(null);
        c.add(topBar);
        c.add(sideBar);
        c.add(statusPanel);
        c.add(navPanel);
        c.add(miscPanel);
        c.add(scrollPane);
        setJMenuBar(menuBar);
        pack();
    }

    public void layoutChildComponents() {
        scrollPane.setViewportView(editPanel);
        topBar.setBounds(0, 0, 1180, 35);
        sideBar.setBounds(0, 35, 35, 760);
        statusPanel.setBounds(35, 35, 920, 20);
        navPanel.setBounds(955, 35, 225, 752);
        miscPanel.setBounds(35, 555 + 81, 920, 151);
        scrollPane.setBounds(35, 55, 920, 582);
        topBar.setBackground(LSSVGEditorGUITheme.MASTER_DEFAULT_BACKGROUND_COLOR);
        sideBar.setBackground(LSSVGEditorGUITheme.MASTER_DEFAULT_BACKGROUND_COLOR);
        navPanel.setBackground(LSSVGEditorGUITheme.MASTER_DEFAULT_BACKGROUND_COLOR);
        miscPanel.setBackground(LSSVGEditorGUITheme.MASTER_DEFAULT_BACKGROUND_COLOR);
    }

    private void setUpProperties() {
        margin   = OSValidator.isMac()
                   ? -21
                   : 0;
        width    = 1180;
        height   = 830 + margin;
        frameDim = new Dimension(width, height);
        screen   = Toolkit.getDefaultToolkit().getScreenSize();
        x        = (screen.width - width) / 2;
        y        = (screen.height - height) / 2;
        this.setLocation(x, y);
    }

    /**
     *
     * @return
     */
    public boolean promptSaveIfNeeded() {
        boolean modified = controller.isDocumentModified();

        if (!modified) {
            return true;
        }

        Object[]      messageArguments = { documentTitle };
        MessageFormat formatter        = new MessageFormat("");
        String        message          = formatter.format(messageArguments);
        String        title            = formatter.format(messageArguments);
        int           option           = JOptionPane.showOptionDialog(this, message, title,
                                             JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, null,
                                             null);

        switch (option) {
        case JOptionPane.NO_OPTION :
            return true;

        case JOptionPane.YES_OPTION :
            if (saveFile()) {
                return true;
            }
        }

        return false;
    }

    public boolean saveFile() {
        boolean saved;

        try {
            saved = controller.fileSave();
        } catch (IOException e) {
            saved = fileSaveAs();
        }

        return false;
    }

    public boolean fileSaveAs() {
        boolean      saved        = false;
        JFileChooser fileChoooser = new JFileChooser();

        fileChoooser.setMultiSelectionEnabled(false);
        fileChoooser.setAcceptAllFileFilterUsed(false);

        FileNameExtensionFilter extFilter = new FileNameExtensionFilter("Scalable Vector Graphics (*.svg)", "svg");

        fileChoooser.setFileFilter(extFilter);
        fileChoooser.setSelectedFile(getDisplayedFile());

        if (fileChoooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                saved = controller.fileSave();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

        return saved;
    }

    public void changeMode(LSUIEditingPanel.EditModeScheme mode) {
        editPanel.switchModeTo(mode);
    }

    private void setUpEditingPanel() {
        BufferedImage image = controller.renderImage(zoomScale);

        editPanel.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        editPanel.switchModeTo(LSUIEditingPanel.EditModeScheme.MODE_SELECT);
        editPanel.setFill(SVGGenericElement.SVG_FILL_DEFAULT);
        editPanel.setStroke(SVGGenericElement.SVG_STROKE_DEFAULT);
        editPanel.setStrokeWidth(SVGGenericElement.SVG_STROKE_WIDTH_DEFAULT);
        scrollPane.setViewportView(editPanel);
    }
}