package rocks6205.editor.core;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.actions.LSPanMouseAdapter;
import rocks6205.editor.actions.LSTransferHandler;
import rocks6205.editor.model.elements.LSGenericElement;
import rocks6205.editor.viewcomponents.LSUIProtocol;
import rocks6205.editor.viewcomponents.dialogs.LSUICanvasPropertiesDialog;
import rocks6205.editor.viewcomponents.dialogs.LSUIWelcomeDialog;
import rocks6205.editor.viewcomponents.panels.LSUIEditingPanel;
import rocks6205.editor.viewcomponents.panels.LSUIMiscPanel;
import rocks6205.editor.viewcomponents.panels.LSUINavigationPanel;
import rocks6205.editor.viewcomponents.panels.LSUIStatusPanel;
import rocks6205.editor.viewcomponents.toolbars.LSUIMenubar;
import rocks6205.editor.viewcomponents.toolbars.LSUISideToolbar;
import rocks6205.editor.viewcomponents.toolbars.LSUITopToolbar;
import rocks6205.system.properties.LSCanvasProperties;
import rocks6205.system.properties.LSEditorGUIConstants;
import rocks6205.system.properties.OSValidator;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

//~--- JDK imports ------------------------------------------------------------

/**
 * A class defining how the main user interface should look like.
 * <p>
 *
 * @author Toh Huey Jing
 * @author Cheow Yeong Chi
 * @since 1.2
 */
public final class LSView extends JFrame implements LSUIProtocol {
    private static final long serialVersionUID = 6764861773639452353L;
    private static boolean codeViewFlag;

    /*
     * STATIC INITIALISER
     */
    static {
        codeViewFlag = (true);
    }

    /*
     * GUI COMPONENTS
     */
    private Container c = getContentPane();

    /**
     * Controller object
     */
    private LSViewController controller;

    private Dimension frameDim;

    /*
     * PROPERTIES
     */
    private float zoomScale;
    private boolean isZoomChanged;
    private File displayedFile;
    private String documentTitle;

    /*
     * GUI COMPONENTS
     */
    private JScrollPane scrollPane;
    private JViewport viewport;
    private LSUIEditingPanel editPanel;
    private LSUIMenubar menuBar;
    private LSUITopToolbar topBar;
    private LSUISideToolbar sideBar;
    private LSUIStatusPanel statusPanel;
    private LSUINavigationPanel navPanel;
    private LSUIMiscPanel miscPanel;
    private LSUICanvasPropertiesDialog docPropDlg;

    /*
     * ACTION COMPONENTS
     */
    private LSTransferHandler tfHandler;

    /*
     * CONSTRUCTOR
     */

    /**
     * Construct an <code>SVGView</code> instance with components initialised
     * and properly customised.
     *
     * @see #setController(rocks6205.editor.core.LSViewController)
     * @see #initialise()
     * @see #customise()
     */
    public LSView(LSViewController c) {
        super();
        setController(c);
        initialise();
        customise();

//      showWelcomeScreen();
    }

    /**
     * {@inheritDoc}
     *
     * @see #setUpProperties()
     * @see #initialiseComponents()
     * @see #initialiseHandlers()
     */
    @Override
    public void initialise() {
        setUpProperties();
        isZoomChanged = false;
        zoomScale = 1.0f;
        initialiseComponents();
        initialiseHandlers();
    }

    /**
     * Setting up size of the main view.
     */
    private void setUpProperties() {
        int margin = OSValidator.isMac()
                ? -21
                : 0;
        int width = 1180;
        int height = 830 + margin;
        frameDim = new Dimension(width, height);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        setLocation(x, y);
        LSCanvasProperties.setOutputResolution(Toolkit.getDefaultToolkit().getScreenResolution());
        setResizable(false);
    }

    /**
     * Initialises child components.
     */
    private void initialiseComponents() {
        menuBar = new LSUIMenubar(this);
        topBar = new LSUITopToolbar("Editing Tools", this);
        sideBar = new LSUISideToolbar("Selection Tools", this);
        statusPanel = new LSUIStatusPanel(this);
        navPanel = new LSUINavigationPanel(this);
        miscPanel = new LSUIMiscPanel(this);
        editPanel = new LSUIEditingPanel(this);
        scrollPane = new JScrollPane(editPanel);
        viewport = scrollPane.getViewport();
        docPropDlg = new LSUICanvasPropertiesDialog(this);
    }

    /**
     * Initialises action handlers.
     */
    private void initialiseHandlers() {
        tfHandler = new LSTransferHandler(this);
    }

    /**
     * {@inheritDoc}
     *
     * @see #layoutChildComponents()
     * @see #setUpEditingPanel()
     * @see #layoutFrame()
     * @see #setClosingEvent()
     * @see #bindHandlers()
     * @see #updateTitle()
     */
    @Override
    public void customise() {
        layoutChildComponents();
        setUpEditingPanel();
        layoutFrame();
        setClosingEvent();
        bindHandlers();
        updateTitle();
    }

    /**
     * Layout child components in main view.
     */
    private void layoutChildComponents() {
        scrollPane.setViewportView(editPanel);
        topBar.setBounds(0, 0, 1180, 35);
        sideBar.setBounds(0, 35, 35, 760);
        statusPanel.setBounds(35, 35, 920, 20);
        navPanel.setBounds(955, 35, 225, 752);
        miscPanel.setBounds(35, 555 + 81, 920, 151);
        scrollPane.setBounds(35, 55, 920, 582);
        topBar.setBackground(LSEditorGUIConstants.MASTER_DEFAULT_BACKGROUND_COLOR);
        sideBar.setBackground(LSEditorGUIConstants.MASTER_DEFAULT_BACKGROUND_COLOR);
        navPanel.setBackground(LSEditorGUIConstants.MASTER_DEFAULT_BACKGROUND_COLOR);
        miscPanel.setBackground(LSEditorGUIConstants.MASTER_DEFAULT_BACKGROUND_COLOR);
    }

    /**
     * Sets up the SVG Editing Panel.
     */
    private void setUpEditingPanel() {
        BufferedImage image = controller.renderImage(zoomScale);

        editPanel.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        editPanel.switchModeTo(LSUIEditingPanel.EditModeScheme.MODE_SELECT);
        editPanel.setFill(LSGenericElement.SVG_FILL_DEFAULT);
        editPanel.setStroke(LSGenericElement.SVG_STROKE_DEFAULT);
        editPanel.setStrokeWidth(LSGenericElement.SVG_STROKE_WIDTH_DEFAULT);
        scrollPane.setViewportView(editPanel);
    }

    /**
     * Layouts main view.
     */
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
        updateTitle();
        pack();
    }

    /**
     * Prompt exit confirmation while user clicks on 'x' button on the window.
     */
    private void setClosingEvent() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent winEvt) {
                int closeCf = JOptionPane.showConfirmDialog(null, "Exit SVG Editor?", "Confirm exit",
                        JOptionPane.YES_NO_OPTION);

                if (closeCf == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bindHandlers() {
        setTransferHandler(tfHandler);
    }

    /*
     * METHOD
     */
    public void update() {
        ArrayList<LSGenericElement> selections = new ArrayList<>(controller.getSelections());
        boolean isAnySelected = !selections.isEmpty();
        File currentFile = controller.getCurrentFile();
        boolean isFileChanged = (currentFile != displayedFile);
        boolean needRepaint = controller.isDocumentModified() || isZoomChanged || isFileChanged;

        sideBar.updateActionStatusFromView(isAnySelected);
        menuBar.updateActionStatusFromView(isAnySelected);
        editPanel.setSelections(selections);
        editPanel.drawOverlay();

        if (isZoomChanged) {
            isZoomChanged = false;
        }

        if (isFileChanged) {
            displayedFile = currentFile;
        }

        updateTitle();

        if (needRepaint) {
            editPanel.paintCanvas(controller.renderImage(zoomScale));
            navPanel.updateTree();
            miscPanel.updateCode(controller.getDocumentString());
        }
    }

    /**
     * Shows the welcome screen as main view comes to load.
     */
    private void showWelcomeScreen() {
        new LSUIWelcomeDialog(this).display();
    }

    /**
     * Prompts user to save before exit or changes file
     *
     * @return Flag that if saving is needed
     */
    public boolean promptSaveIfNeeded() {
        boolean modified = controller.isDocumentModified();

        if (!modified) {
            return true;
        }

        Object[] messageArguments = {documentTitle};
        MessageFormat formatter = new MessageFormat("Save modifications?");
        String message = formatter.format(messageArguments);
        String title = formatter.format(messageArguments);
        int option = JOptionPane.showOptionDialog(this, message, title,
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, null,
                null);

        switch (option) {
            case JOptionPane.NO_OPTION:
                return true;

            case JOptionPane.YES_OPTION:
                if (saveFile()) {
                    return true;
                }
        }

        return false;
    }

    /**
     * Saves current file.
     *
     * @return <code>true</code> if save successful, <code>false</code> otherwise.
     * @see #fileSaveAs()
     */
    public boolean saveFile() {
        boolean saved;

        try {
            saved = controller.fileSave();
        } catch (IOException e) {
            saved = fileSaveAs();
        }

        return saved;
    }

    /**
     * Saves file as some other file.
     *
     * @return <code>true</code> if save successful, <code>false</code> otherwise.
     */
    public boolean fileSaveAs() {
        boolean saved = false;
        JFileChooser fileChoooser = new JFileChooser();

        fileChoooser.setMultiSelectionEnabled(false);
        fileChoooser.setAcceptAllFileFilterUsed(false);

        FileNameExtensionFilter extFilter = new FileNameExtensionFilter("Scalable Vector Graphics (*.svg)", "svg");

        fileChoooser.setFileFilter(extFilter);
        fileChoooser.setSelectedFile(getDisplayedFile());

        if (fileChoooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                saved = controller.fileSave(fileChoooser.getSelectedFile());
            } catch (IOException e) {
                LSEditor.logger.warning(e.getMessage());
            }
        }

        return saved;
    }

    /**
     * Updates status of the status label.
     *
     * @param status
     */
    public void updateStatus(String status) {
        statusPanel.updateStatus(status);
    }

    /**
     * Updates title of the current frame as the name of the file.
     */
    private void updateTitle() {
        if (displayedFile != null) {
            documentTitle = displayedFile.getName();

            statusPanel.updateStatus(documentTitle);
            repaint();
        }
    }

    /**
     * Instructs controller to open file.
     *
     * @param f File to be opened.
     */
    public void openFile(File f) {
        try {
            controller.fileLoad(f);
        } catch (IOException ex) {
            LSEditor.logger.warning(ex.getMessage());
        }
    }

    public void registerPanListenerToScrollPane(LSPanMouseAdapter adapter) {
        viewport.addMouseListener(adapter);
        viewport.addMouseMotionListener(adapter);
    }

    public void deregisterPanListenerToScrollPane(LSPanMouseAdapter adapter) {
        viewport.removeMouseListener(adapter);
        viewport.removeMouseMotionListener(adapter);
    }

    /**
     * Toggles code preview.
     */
    public void toggleCodeView() {
        menuBar.toggleCodeMenuTitle(codeViewFlag);

        if (codeViewFlag) {
            miscPanel.disableTextAreaInView();
        } else {
            miscPanel.enableTextAreaInView();
        }

        codeViewFlag = !codeViewFlag;
    }

    /**
     * Show document properties dialog
     */
    public void showDocumentPropertiesDialog() {
        docPropDlg.updateCanvasData(controller.getModel().getCanvasDTO());
        docPropDlg.displayLimited();
        controller.resizeDocument(controller.getModel().getCanvasDTO().getWidth(),
                controller.getModel().getCanvasDTO().getHeight());
    }

    public void reloadBundle() {
        ResourceBundle bundle = LSEditor.titleBundle;

        topBar.reloadString(bundle);
        sideBar.reloadString(bundle);
        menuBar.reloadString(bundle);
        docPropDlg.reloadString(bundle);
        navPanel.reloadString(bundle);
    }

    /*
     * ACCESSORS
     */

    /**
     * @return Controller object
     */
    public LSViewController getController() {
        return controller;
    }

    /**
     * @return Zoom scale
     */
    public float getZoomScale() {
        return zoomScale;
    }

    /**
     * @return whether is the zoom changed
     */
    public boolean isZoomChanged() {
        return isZoomChanged;
    }

    /**
     * @return Current displayed file
     */
    public File getDisplayedFile() {
        return displayedFile;
    }

    /**
     * @return Current editing panel
     */
    public LSUIEditingPanel getEditPanel() {
        return editPanel;
    }

    /*
     * MUTATORS
     */

    /**
     * @param controller Controller object
     */
    public void setController(LSViewController controller) {
        this.controller = controller;
    }

    /**
     * @param zoom zoom
     */
    public void changeZoom(float zoom) {
        isZoomChanged = true;
        this.zoomScale = zoom;
    }

    /**
     * Resets zoom scale to 1.0.
     */
    public void resetZoom() {
        isZoomChanged = false;
        this.zoomScale = 1.00f;
    }

    /**
     * @param d File to be displayed
     */
    public void setDisplayedFile(File d) {
        displayedFile = d;
    }

    /**
     * @param mode Editing mode to change to
     */
    public void changeMode(LSUIEditingPanel.EditModeScheme mode) {
        editPanel.switchModeTo(mode);
    }

    /**
     * @param p Editing panel modified.
     */
    public void setEditPanel(LSUIEditingPanel p) {
        editPanel = p;
    }
}