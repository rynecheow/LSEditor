package rocks6205.editor.viewcomponents.dialogs;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.core.LSModel;
import rocks6205.editor.core.LSView;
import rocks6205.editor.dto.LSCanvasDataObject;
import rocks6205.editor.model.adt.LSLength;
import rocks6205.editor.model.adt.LSLengthUnitType;
import rocks6205.editor.viewcomponents.LSNumericTextField;
import rocks6205.editor.viewcomponents.LSUIProtocol;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ResourceBundle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *
 * @author Cheow Yeong Chi
 *
 * @since 2.2
 */
public final class LSUICanvasPropertiesDialog extends JDialog implements LSUIProtocol {

    /*
     * SIZE PROPERTIES
     */
    int       width;
    int       height;
    Dimension screen;
    int       x;
    int       y;

    /*
     * GUI COMPONENTS
     */
    private JPanel     backPanel;
    private JButton    cancelButton;
    private JLabel     colorModeInfoLabel;
    private JLabel     colorModeLabel;
    private JButton    confirmButton;
    private JLabel     documentNameLabel;
    private JTextField documentNameTextField;
    private JLabel     heightLabel;
    private JTextField heightTextField;
    private JComboBox  heightUnitComboBox;
    private JComboBox  presetComboBox;
    private JLabel     presetLabel;
    private JLabel     resolutionInfoLabel;
    private JLabel     resolutionLabel;
    private JComboBox  sizeComboBox;
    private JLabel     sizeLabel;
    private JLabel     widthLabel;
    private JTextField widthTextField;
    private JComboBox  widthUnitComboBox;

    /*
     * PARENT COMPONENT
     */
    LSView parentView;

    /**
     * DATA COMPONENT
     */
    LSCanvasDataObject canvasData;

    /**
     * Constructs a canvas properties dialog with parent component.
     * @param parent Parent component.
     */
    public LSUICanvasPropertiesDialog(LSView parent) {
        super(parent, "Welcome Screen", true);
        this.height = 340;
        this.width  = 480;
        parentView  = parent;
        initialise();
        customise();
        bindHandlers();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void initialise() {
        setUpProperties();
        backPanel             = new JPanel();
        documentNameLabel     = new JLabel();
        documentNameTextField = new JTextField();
        presetLabel           = new JLabel();
        presetComboBox        = new JComboBox();
        sizeLabel             = new JLabel();
        sizeComboBox          = new JComboBox();
        widthLabel            = new JLabel();
        heightLabel           = new JLabel();
        widthTextField        = new LSNumericTextField();
        heightTextField       = new LSNumericTextField();
        widthUnitComboBox     = new JComboBox();
        heightUnitComboBox    = new JComboBox();
        resolutionLabel       = new JLabel();
        resolutionInfoLabel   = new JLabel();
        colorModeLabel        = new JLabel();
        colorModeInfoLabel    = new JLabel();
        confirmButton         = new JButton();
        cancelButton          = new JButton();
    }

    /**
     * Set up dialog size properties.
     */
    private void setUpProperties() {
        screen = Toolkit.getDefaultToolkit().getScreenSize();
        x      = (screen.width - width) / 2;
        y      = (screen.height - height) / 2;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void customise() {
        layoutView();
        layoutChildComponents();
        getContentPane().add(backPanel);
        backPanel.setBounds(0, 0, width, height);
        setBounds(x, y, width, height);
        pack();
    }

    /**
     * Layout view.
     */
    private void layoutView() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setModal(true);
        setUndecorated(true);
        setPreferredSize(new Dimension(width, height));
        setSize(new Dimension(width, height));
        getContentPane().setLayout(null);
    }

    /**
     * Layout child components.
     */
    private void layoutChildComponents() {
        backPanel.setBackground(new Color(30, 30, 30));
        backPanel.setMaximumSize(new Dimension(width, height));
        backPanel.setMinimumSize(new Dimension(width, height));
        backPanel.setPreferredSize(new Dimension(width, height));
        backPanel.setLayout(null);
        documentNameLabel.setBackground(new Color(255, 255, 255));
        documentNameLabel.setForeground(new Color(255, 255, 255));
        documentNameLabel.setText("Document Name:");
        backPanel.add(documentNameLabel);
        documentNameLabel.setBounds(49, 37, 109, 16);
        documentNameTextField.setBackground(backPanel.getBackground());
        documentNameTextField.setBorder(null);
        documentNameTextField.setForeground(new Color(255, 255, 255));
        backPanel.add(documentNameTextField);
        documentNameTextField.setBounds(176, 31, 202, 28);
        presetLabel.setForeground(new Color(255, 255, 255));
        presetLabel.setText("Preset:");
        backPanel.add(presetLabel);
        presetLabel.setBounds(49, 69, 42, 16);
        presetComboBox.setModel(new DefaultComboBoxModel(new String[] { "Default Editor Size", "Web" }));
        backPanel.add(presetComboBox);
        presetComboBox.setBounds(176, 65, 202, 27);
        sizeLabel.setForeground(new Color(255, 255, 255));
        sizeLabel.setText("Size:");
        backPanel.add(sizeLabel);
        sizeLabel.setBounds(49, 102, 29, 16);
        sizeComboBox.setModel(new DefaultComboBoxModel(new String[] { "800 x 600", "1024 x 768", "1152 x 864" }));
        backPanel.add(sizeComboBox);
        sizeComboBox.setBounds(176, 98, 202, 27);
        widthLabel.setForeground(new Color(255, 255, 255));
        widthLabel.setText("Width:");
        backPanel.add(widthLabel);
        widthLabel.setBounds(49, 137, 40, 16);
        heightLabel.setForeground(new Color(255, 255, 255));
        heightLabel.setText("Height:");
        backPanel.add(heightLabel);
        heightLabel.setBounds(49, 172, 46, 16);
        backPanel.add(widthTextField);
        widthTextField.setBounds(176, 131, 100, 28);
        backPanel.add(heightTextField);
        heightTextField.setBounds(176, 166, 100, 28);
        widthUnitComboBox.setModel(new DefaultComboBoxModel(new String[] { "px", "in", "cm", "mm", "pt" }));
        backPanel.add(widthUnitComboBox);
        widthUnitComboBox.setBounds(288, 133, 90, 27);
        heightUnitComboBox.setModel(new DefaultComboBoxModel(new String[] { "px", "in", "cm", "mm", "pt" }));
        backPanel.add(heightUnitComboBox);
        heightUnitComboBox.setBounds(288, 168, 90, 27);
        resolutionLabel.setBackground(new Color(255, 255, 255));
        resolutionLabel.setForeground(new Color(255, 255, 255));
        resolutionLabel.setText("Resolution:");
        backPanel.add(resolutionLabel);
        resolutionLabel.setBounds(50, 208, 71, 16);
        resolutionInfoLabel.setForeground(new Color(255, 255, 255));
        resolutionInfoLabel.setText("72 Pixel/Inch");
        backPanel.add(resolutionInfoLabel);
        resolutionInfoLabel.setBounds(176, 208, 84, 16);
        colorModeLabel.setBackground(new Color(255, 255, 255));
        colorModeLabel.setForeground(new Color(255, 255, 255));
        colorModeLabel.setText("Color Mode:");
        backPanel.add(colorModeLabel);
        colorModeLabel.setBounds(50, 242, 76, 16);
        colorModeInfoLabel.setForeground(new Color(255, 255, 255));
        colorModeInfoLabel.setText("RGB Color - 8 bit");
        backPanel.add(colorModeInfoLabel);
        colorModeInfoLabel.setBounds(176, 242, 107, 16);
        confirmButton.setText("OK");
        backPanel.add(confirmButton);
        confirmButton.setBounds(362, 303, 96, 29);
        cancelButton.setText("Cancel");
        backPanel.add(cancelButton);
        cancelButton.setBounds(362, 270, 96, 29);
    }

    /**
     * Display dialog with certain fields disabled.
     */
    public void displayLimited() {
        documentNameTextField.setText("  " + canvasData.getTitle());
        presetComboBox.setEnabled(false);
        sizeComboBox.setEnabled(false);
        documentNameTextField.setEditable(false);
        updateHeight(canvasData.getHeight());
        updateWidth(canvasData.getWidth());
        setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bindHandlers() {
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCanvasData(generateCanvasDTO());
                updateModel();
                dispose();
            }
            private void updateModel() {
                LSModel model = parentView.getController().getModel();

                model.setCanvasDTO(canvasData);
                parentView.getController().setModel(model);
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        widthUnitComboBox.addItemListener(new ItemListener() {
            float val;
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch (widthUnitComboBox.getSelectedIndex()) {
                case 0 :
                    val = canvasData.getWidth().getValue(LSLengthUnitType.PX);

                    break;

                case 1 :
                    val = canvasData.getWidth().getValue(LSLengthUnitType.IN);

                    break;

                case 2 :
                    val = canvasData.getWidth().getValue(LSLengthUnitType.CM);

                    break;

                case 3 :
                    val = canvasData.getWidth().getValue(LSLengthUnitType.MM);

                    break;

                case 4 :
                    val = canvasData.getWidth().getValue(LSLengthUnitType.PT);

                    break;

                default :
                    throw new AssertionError("Invalid symbol for conversion");
                }

                widthTextField.setText(String.format("%.1f", val));
            }
        });
    }

    private void updateHeight(LSLength length) {
        String unit = length.getUnitType().getUnitSymbol();
        int    comboBoxIndex;
        float  value = length.getValue();

        heightTextField.setText(String.format("%d", (int) value));

        switch (unit) {
        case "px" :
            comboBoxIndex = 0;

            break;

        case "in" :
            comboBoxIndex = 1;

            break;

        case "cm" :
            comboBoxIndex = 2;

            break;

        case "mm" :
            comboBoxIndex = 3;

            break;

        case "pt" :
            comboBoxIndex = 4;

            break;

        default :
            throw new AssertionError("Invalid symbol");
        }

        heightUnitComboBox.setSelectedIndex(comboBoxIndex);
    }

    private void updateWidth(LSLength length) {
        String unit = length.getUnitType().getUnitSymbol();
        int    comboBoxIndex;
        float  value = length.getValue();

        widthTextField.setText(String.format("%d", (int) value));

        switch (unit) {
        case "px" :
            comboBoxIndex = 0;

            break;

        case "in" :
            comboBoxIndex = 1;

            break;

        case "cm" :
            comboBoxIndex = 2;

            break;

        case "mm" :
            comboBoxIndex = 3;

            break;

        case "pt" :
            comboBoxIndex = 4;

            break;

        default :
            throw new AssertionError("Invalid symbol");
        }

        widthUnitComboBox.setSelectedIndex(comboBoxIndex);
    }

    /**
     *
     */
    public void updateCanvasData(LSCanvasDataObject dto) {
        canvasData = dto;
    }

    public LSCanvasDataObject generateCanvasDTO() {
        return new LSCanvasDataObject(generateEffectiveWidth(), generateEffectiveHeight(),
                                      documentNameTextField.getText());
    }

    private LSLength generateEffectiveHeight() {
        float            val = Float.parseFloat(heightTextField.getText());
        LSLengthUnitType unitType;

        switch (heightUnitComboBox.getSelectedIndex()) {
        case 0 :
            unitType = LSLengthUnitType.PX;

            break;

        case 1 :
            unitType = LSLengthUnitType.IN;

            break;

        case 2 :
            unitType = LSLengthUnitType.CM;

            break;

        case 3 :
            unitType = LSLengthUnitType.MM;

            break;

        case 4 :
            unitType = LSLengthUnitType.PT;

            break;

        default :
            throw new AssertionError("Invalid INDEX");
        }

        return new LSLength(unitType, val);
    }

    private LSLength generateEffectiveWidth() {
        float            val = Float.parseFloat(widthTextField.getText());
        LSLengthUnitType unitType;

        switch (widthUnitComboBox.getSelectedIndex()) {
        case 0 :
            unitType = LSLengthUnitType.PX;

            break;

        case 1 :
            unitType = LSLengthUnitType.IN;

            break;

        case 2 :
            unitType = LSLengthUnitType.CM;

            break;

        case 3 :
            unitType = LSLengthUnitType.MM;

            break;

        case 4 :
            unitType = LSLengthUnitType.PT;

            break;

        default :
            throw new AssertionError("Invalid INDEX");
        }

        return new LSLength(unitType, val);
    }

   public void reloadString(ResourceBundle b) {
      documentNameLabel.setText(b.getString("dpd.docname.text"));
      heightLabel.setText(b.getString("dpd.height.text"));
      widthLabel.setText(b.getString("dpd.width.text"));
      presetLabel.setText(b.getString("dpd.preset.text"));
      sizeLabel.setText(b.getString("dpd.size.text"));
      resolutionLabel.setText(b.getString("dpd.resolution.title"));
      resolutionInfoLabel.setText(b.getString("dpd.resolution.desc"));
      colorModeLabel.setText(b.getString("dpd.colormode.title"));
      colorModeInfoLabel.setText(b.getString("dpd.colormode.desc"));
      confirmButton.setText(b.getString("dpd.confirmbutton.text"));
      cancelButton.setText(b.getString("dpd.cancelbutton.text"));
      
      repaint();
   }
   
   
}