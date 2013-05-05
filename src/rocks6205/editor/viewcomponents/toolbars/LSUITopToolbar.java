package rocks6205.editor.viewcomponents.toolbars;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.actions.LSAbstractAction.DocumentPropertiesAction;
import rocks6205.editor.actions.LSAbstractAction.NewDocumentAction;
import rocks6205.editor.actions.LSAbstractAction.OpenFileAction;
import rocks6205.editor.actions.LSAbstractAction.SaveFileAction;
import rocks6205.editor.actions.LSAbstractAction.ZoomInViewAction;
import rocks6205.editor.actions.LSAbstractAction.ZoomOutViewAction;
import rocks6205.editor.core.LSView;
import rocks6205.editor.model.elements.LSGenericElement;
import rocks6205.editor.viewcomponents.LSUIButton;
import rocks6205.editor.viewcomponents.LSUIColorButton;
import rocks6205.editor.viewcomponents.LSUIIconLabel;
import rocks6205.editor.viewcomponents.LSUIProtocol;
import rocks6205.editor.viewcomponents.panels.LSUIEditingPanel;

import rocks6205.system.properties.LSEditorGUIConstants;

//~--- JDK imports ------------------------------------------------------------

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Cheow Yeong Chi
 *
 * @since 2.2
 */
public final class LSUITopToolbar extends JToolBar implements LSUIProtocol {

    /*
     * PARENT COMPONENT
     */
    private LSView parentView;

    /*
     * GUI COMPONENTS
     */
    private LSUIButton      newButton;
    private LSUIButton      openButton;
    private LSUIButton      saveButton;
    private LSUIButton      zoomInButton;
    private LSUIButton      zoomOutButton;
    private LSUIButton      docPropButton;
    private LSUIIconLabel   fillLabel;
    private LSUIIconLabel   strokeLabel;
    private LSUIColorButton fillButton;
    private LSUIColorButton strokeButton;
    private JCheckBox       fillCheckBox;
    private JCheckBox       strokeCheckBox;

    /*
     * ACTION COMPONENTS
     */
    private NewDocumentAction        newAct;
    private OpenFileAction           openAct;
    private SaveFileAction           saveAct;
    private ZoomInViewAction         zoomInAction;
    private ZoomOutViewAction        zoomOutAction;
    private DocumentPropertiesAction docPropAct;

    public LSUITopToolbar(String name, LSView parent) {
        super(name);
        setParentView(parent);
        initialise();
        customise();
    }

    @Override
    public void initialise() {
        newButton      = LSUIButton.create();
        openButton     = LSUIButton.create();
        saveButton     = LSUIButton.create();
        zoomInButton   = LSUIButton.create();
        zoomOutButton  = LSUIButton.create();
        docPropButton  = LSUIButton.create();
        fillLabel      = new LSUIIconLabel();
        strokeLabel    = new LSUIIconLabel();
        fillButton     = LSUIColorButton.create(LSGenericElement.SVG_FILL_DEFAULT);
        strokeButton   = LSUIColorButton.create(LSGenericElement.SVG_STROKE_DEFAULT);
        fillCheckBox   = new JCheckBox();
        strokeCheckBox = new JCheckBox();
        newAct         = new NewDocumentAction(parentView);
        openAct        = new OpenFileAction(parentView);
        saveAct        = new SaveFileAction(parentView);
        zoomInAction   = new ZoomInViewAction(parentView);
        zoomOutAction  = new ZoomOutViewAction(parentView);
        docPropAct     = new DocumentPropertiesAction(parentView);
    }

    @Override
    public void customise() {
        layoutChildComponents();
        bindHandlers();
        setIconsForButtons();
        layoutView();
    }

    private void layoutView() {
        setBorder(LSEditorGUIConstants.MASTER_DEFAULT_PANEL_BORDER);
        addSeparator();
        add(newButton);
        add(openButton);
        add(saveButton);
        addSeparator();
        add(zoomInButton);
        add(zoomOutButton);
        addSeparator();
        add(docPropButton);
        addSeparator();
        add(fillCheckBox);
        add(fillLabel);
        add(fillButton);
        addSeparator();
        add(strokeCheckBox);
        add(strokeLabel);
        add(strokeButton);
        addSeparator();
        setFloatable(false);
        setRollover(true);
    }

    private void layoutChildComponents() {
        fillCheckBox.setSelected(true);
        strokeCheckBox.setSelected(false);
    }

    private void setItemListenerForCheckBox() {
        fillCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                boolean selected = e.getStateChange() == ItemEvent.SELECTED;

                fillButton.setEnabled(selected);

                if (selected) {
                    fillButton.setPainting(LSGenericElement.SVG_FILL_DEFAULT);
                } else {
                    fillButton.setPaintingNone();
                }
            }
        });
        strokeCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                boolean selected = e.getStateChange() == ItemEvent.SELECTED;

                strokeButton.setEnabled(selected);

                if (selected) {
                    strokeButton.setPaintingNone();
                } else {
                    strokeButton.setPainting(LSGenericElement.SVG_STROKE_DEFAULT);
                }
            }
        });
    }

    /**
     * Setup actions for button.
     */
    private void setActionForButtons() {
        newButton.setAction(newAct);
        openButton.setAction(openAct);
        saveButton.setAction(saveAct);
        zoomInButton.setAction(zoomInAction);
        zoomOutButton.setAction(zoomOutAction);
        zoomInAction.setZoomOutPartnerAction(zoomOutAction);
        docPropButton.setAction(docPropAct);
    }

    /**
     * Setup icons for button.
     */
    private void setIconsForButtons() {
        String newFileIconPath  = "newfile.png";
        String openFileIconPath = "openfolder.png";
        String saveFileIconPath = "save.png";
        String zoomInIconPath   = "zoomin.png";
        String zoomOutIconPath  = "zoomout.png";
        String fillIconPath     = "fillIcon.png";
        String strokeIconPath   = "strokeIcon.png";
        String docPropIconPath  = "docprop.png";

        newButton.setIcon(newFileIconPath);
        openButton.setIcon(openFileIconPath);
        saveButton.setIcon(saveFileIconPath);
        zoomInButton.setIcon(zoomInIconPath);
        zoomOutButton.setIcon(zoomOutIconPath);
        fillLabel.setIcon(fillIconPath);
        strokeLabel.setIcon(strokeIconPath);
        docPropButton.setIcon(docPropIconPath);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bindHandlers() {
        setActionForButtons();
        setItemListenerForCheckBox();

        SVGPresentationChangeListener changeList = new SVGPresentationChangeListener();

        fillButton.addPresentationChangeListener(changeList);
        strokeButton.addPresentationChangeListener(changeList);
    }

    private void setParentView(LSView parent) {
        parentView = parent;
    }

    private class SVGPresentationChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            Object           source    = e.getSource();
            LSUIEditingPanel editPanel = parentView.getEditPanel();

            if (source == fillButton) {
                editPanel.setFill(fillButton.getPainting());
            } else if (source == strokeButton) {
                editPanel.setStroke(strokeButton.getPainting());
            }

//          } else if (source == strokeWidthInputPanel) {
//                  editPanel.setStrokeWidth(strokeWidthInputPanel.getLength());
//          }
            parentView.setEditPanel(editPanel);
        }
    }
}