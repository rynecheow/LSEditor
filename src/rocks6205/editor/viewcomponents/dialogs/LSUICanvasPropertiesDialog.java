package rocks6205.editor.viewcomponents.dialogs;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.core.LSView;
import rocks6205.editor.viewcomponents.LSUIProtocol;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

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

    public LSUICanvasPropertiesDialog(LSView parent) {
        super(parent, "Welcome Screen", true);
        this.height = 340;
        this.width  = 480;
        parentView  = parent;
        initialise();
        customise();
        bindHandlers();
    }

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
        widthTextField        = new JTextField();
        heightTextField       = new JTextField();
        widthUnitComboBox     = new JComboBox();
        heightUnitComboBox    = new JComboBox();
        resolutionLabel       = new JLabel();
        resolutionInfoLabel   = new JLabel();
        colorModeLabel        = new JLabel();
        colorModeInfoLabel    = new JLabel();
        confirmButton         = new JButton();
        cancelButton          = new JButton();
    }

    private void setUpProperties() {
        screen = Toolkit.getDefaultToolkit().getScreenSize();
        x      = (screen.width - width) / 2;
        y      = (screen.height - height) / 2;
    }

    @Override
    public void customise() {
        layoutView();
        layoutChildComponents();
        getContentPane().add(backPanel);
        backPanel.setBounds(0, 0, width, height);
        pack();
        setBounds(x, y, width, height);
    }

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
        documentNameTextField.setText("Untitled");
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
        widthUnitComboBox.setModel(new DefaultComboBoxModel(new String[] { "Pixels", "Inches", "Centimeter",
                "Millimeter", "Points" }));
        backPanel.add(widthUnitComboBox);
        widthUnitComboBox.setBounds(288, 133, 90, 27);
        heightUnitComboBox.setModel(new DefaultComboBoxModel(new String[] { "Pixels", "Inches", "Centimeter",
                "Millimeter", "Points" }));
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

    public void display() {
        setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bindHandlers() {}
}