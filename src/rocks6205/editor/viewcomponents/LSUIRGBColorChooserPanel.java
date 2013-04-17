package rocks6205.editor.viewcomponents;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.mvc.SVGEditorView;

import rocks6205.svg.adt.SVGColorScheme;

import rocks6205.system.properties.SVGEditorTheme;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/**
 * Color Chooser for SVGEditor
 *
 * @author Sugar CheeSheen Chan
 *
 * @since 2.2
 *
 */
public final class LSUIRGBColorChooserPanel extends JPanel implements LSUIProtocol {
    private static final long serialVersionUID = 4003671910607353797L;

    /*
     * PARENT COMPONENT
     */
    private SVGEditorView      parent;
    private SVGColorScheme     color;
    private JPanel             blueIndicator;
    private JSlider            blueSlider;
    private JSpinner           blueSpinner;
    private JPanel             finalColorIndicator;
    private JPanel             greenIndicator;
    private JSlider            greenSlider;
    private JSpinner           greenSpinner;
    private JPanel             redIndicator;
    private JSlider            redSlider;
    private JSpinner           redSpinner;
    private GridBagConstraints gbConstraint;
    private SpinnerModel       redSpinnerModel;
    private SpinnerModel       greenSpinnerModel;
    private SpinnerModel       blueSpinnerModel;
    private JLabel             redLabel;
    private JLabel             greenLabel;
    private JLabel             blueLabel;

    public LSUIRGBColorChooserPanel(SVGEditorView view) {
        super();
        parent = view;
        initialise();
        customise();
    }

    @Override
    public void initialise() {
        redSpinnerModel     = new SpinnerNumberModel(0, 0, 255, 1);
        greenSpinnerModel   = new SpinnerNumberModel(0, 0, 255, 1);
        blueSpinnerModel    = new SpinnerNumberModel(0, 0, 255, 1);
        finalColorIndicator = new JPanel();
        redSlider           = new JSlider();
        greenSlider         = new JSlider();
        blueSlider          = new JSlider();
        redSpinner          = new JSpinner(redSpinnerModel);
        greenSpinner        = new JSpinner(greenSpinnerModel);
        blueSpinner         = new JSpinner(blueSpinnerModel);
        redLabel            = new JLabel();
        greenLabel          = new JLabel();
        blueLabel           = new JLabel();
    }

    @Override
    public void customise() {
        layoutView();
        layoutComponents();
    }

    public SVGEditorView getParentView() {
        return parent;
    }

    public void setColor(SVGColorScheme color) {
        if (color != null) {
            this.color = color;
        }
    }

    public SVGColorScheme getColor() {
        return color;
    }

    private void layoutView() {
        setBackground(SVGEditorTheme.MASTER_DEFAULT_BACKGROUND_COLOR);
        setMaximumSize(new Dimension(335, 114));
        setMinimumSize(new Dimension(335, 114));
        setMainLayout();
    }

    private void layoutComponents() {
        redSlider.setMajorTickSpacing(32);
        redSlider.setMaximum(255);
        redSlider.setMinorTickSpacing(1);
        redSlider.setValue(0);
        greenSlider.setMajorTickSpacing(32);
        greenSlider.setMaximum(255);
        greenSlider.setMinorTickSpacing(1);
        greenSlider.setValue(0);
        blueSlider.setMajorTickSpacing(32);
        blueSlider.setMaximum(255);
        blueSlider.setMinorTickSpacing(1);
        blueSlider.setValue(0);
        redLabel.setText("R:");
        redLabel.setForeground(new Color(255, 255, 255));
        greenLabel.setText("G:");
        greenLabel.setForeground(new Color(255, 255, 255));
        blueLabel.setText("B:");
        blueLabel.setForeground(new Color(255, 255, 255));
        finalColorIndicator.setBackground(new Color(0, 222, 0));
        finalColorIndicator.setBorder(BorderFactory.createLineBorder(Color.black));
        setFinalColorIndicatorLayout();
    }

    private void setMainLayout() {
        GroupLayout layout = new GroupLayout(this);

        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
                GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
                        GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(
                            0, 0, Short.MAX_VALUE).addComponent(
                            blueSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGroup(
                                GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(
                                    18, Short.MAX_VALUE).addGroup(
                                    layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
                                        GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addComponent(
                                            finalColorIndicator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addPreferredGap(
                                                LayoutStyle.ComponentPlacement.RELATED).addComponent(
                                                    greenSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addComponent(
                                                        redSlider, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))).addPreferredGap(
                                                            LayoutStyle.ComponentPlacement.RELATED).addGroup(
                                                                layout.createParallelGroup(
                                                                    GroupLayout.Alignment.LEADING).addComponent(
                                                                        redLabel).addComponent(greenLabel).addComponent(
                                                                            blueLabel, GroupLayout.Alignment.TRAILING)).addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.RELATED).addGroup(
                                                                                    layout.createParallelGroup(
                                                                                        GroupLayout.Alignment.LEADING).addComponent(
                                                                                            redSpinner, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE).addComponent(
                                                                                                blueSpinner, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE).addComponent(
                                                                                                    greenSpinner, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)).addContainerGap()));
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addGap(7, 7, 7).addGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(
                        redSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE).addGroup(
                            layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(
                                redSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE).addComponent(redLabel))).addGap(7, 7, 7).addGroup(
                                    layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
                                        layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(
                                            greenSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE).addGroup(
                                                    layout.createParallelGroup(
                                                        GroupLayout.Alignment.BASELINE).addComponent(
                                                            blueSpinner, GroupLayout.PREFERRED_SIZE,
                                                                GroupLayout.DEFAULT_SIZE,
                                                                    GroupLayout.PREFERRED_SIZE).addComponent(
                                                                        greenLabel))).addComponent(
                                                                            finalColorIndicator,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                    GroupLayout.DEFAULT_SIZE,
                                                                                        GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                                                                                            LayoutStyle.ComponentPlacement.RELATED).addGroup(
                                                                                                layout.createParallelGroup(
                                                                                                    GroupLayout.Alignment.TRAILING).addGroup(
                                                                                                        layout.createParallelGroup(
                                                                                                            GroupLayout.Alignment.BASELINE).addComponent(
                                                                                                                greenSpinner,
                                                                                                                    GroupLayout.PREFERRED_SIZE,
                                                                                                                        GroupLayout.DEFAULT_SIZE,
                                                                                                                            GroupLayout.PREFERRED_SIZE).addComponent(
                                                                                                                                blueLabel)).addComponent(
                                                                                                                                    blueSlider,
                                                                                                                                        GroupLayout.PREFERRED_SIZE,
                                                                                                                                            GroupLayout.DEFAULT_SIZE,
                                                                                                                                                GroupLayout.PREFERRED_SIZE)).addContainerGap(
                                                                                                                                                    GroupLayout.DEFAULT_SIZE,
                                                                                                                                                        Short.MAX_VALUE)));
    }

    private void setFinalColorIndicatorLayout() {
        GroupLayout finalColorIndicatorLayout = new GroupLayout(finalColorIndicator);

        finalColorIndicator.setLayout(finalColorIndicatorLayout);
        finalColorIndicatorLayout.setHorizontalGroup(
            finalColorIndicatorLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(
                0, 30, Short.MAX_VALUE));
        finalColorIndicatorLayout.setVerticalGroup(
            finalColorIndicatorLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(
                0, 30, Short.MAX_VALUE));
    }
}