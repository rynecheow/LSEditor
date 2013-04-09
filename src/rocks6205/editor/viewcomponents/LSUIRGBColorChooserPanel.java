package rocks6205.editor.viewcomponents;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.mvc.SVGEditorView;

import rocks6205.svg.adt.SVGColorScheme;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
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
public class LSUIRGBColorChooserPanel extends JPanel {
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

    public LSUIRGBColorChooserPanel(SVGEditorView view) {
        super();
        parent = view;
        initialise();
        customise();
    }

    private void initialise() {
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
        redIndicator        = new JPanel();
        greenIndicator      = new JPanel();
        blueIndicator       = new JPanel();
    }

    private void customise() {
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

    private void layoutComponents() {
        setMaximumSize(new Dimension(385, 135));
        setMinimumSize(new Dimension(385, 135));
        setLayout(new GridBagLayout());
        
        gbConstraint            = new GridBagConstraints();
        gbConstraint.gridx      = 1;
        gbConstraint.gridy      = 0;
        gbConstraint.gridheight = 3;
        gbConstraint.ipadx      = 154;
        gbConstraint.anchor     = GridBagConstraints.NORTHWEST;
        gbConstraint.insets     = new Insets(6, 6, 0, 0);
        add(redSlider, gbConstraint);
        
        gbConstraint            = new GridBagConstraints();
        gbConstraint.gridx      = 1;
        gbConstraint.gridy      = 3;
        gbConstraint.gridheight = 3;
        gbConstraint.ipadx      = 154;
        gbConstraint.anchor     = GridBagConstraints.NORTHWEST;
        gbConstraint.insets     = new Insets(6, 6, 0, 0);
        add(greenSlider, gbConstraint);
        
        gbConstraint            = new GridBagConstraints();
        gbConstraint.gridx      = 1;
        gbConstraint.gridy      = 6;
        gbConstraint.gridheight = 10;
        gbConstraint.ipadx      = 154;
        gbConstraint.anchor     = GridBagConstraints.NORTHWEST;
        gbConstraint.insets     = new Insets(6, 6, 7, 0);
        add(blueSlider, gbConstraint);
        
        gbConstraint            = new GridBagConstraints();
        gbConstraint.gridx      = 3;
        gbConstraint.gridy      = 0;
        gbConstraint.gridheight = 2;
        gbConstraint.ipadx      = 21;
        gbConstraint.anchor     = GridBagConstraints.NORTHWEST;
        gbConstraint.insets     = new Insets(6, 12, 0, 12);
        add(redSpinner, gbConstraint);
        
        gbConstraint            = new GridBagConstraints();
        gbConstraint.gridx      = 3;
        gbConstraint.gridy      = 3;
        gbConstraint.gridheight = 2;
        gbConstraint.ipadx      = 21;
        gbConstraint.anchor     = GridBagConstraints.NORTHWEST;
        gbConstraint.insets     = new Insets(6, 12, 0, 12);
        add(greenSpinner, gbConstraint);
        
        gbConstraint            = new GridBagConstraints();
        gbConstraint.gridx      = 3;
        gbConstraint.gridy      = 6;
        gbConstraint.gridheight = 9;
        gbConstraint.ipadx      = 21;
        gbConstraint.anchor     = GridBagConstraints.NORTHWEST;
        gbConstraint.insets     = new Insets(6, 12, 0, 12);
        add(blueSpinner, gbConstraint);
        
        redIndicator.setBackground(new Color(255, 51, 51));
        gbConstraint        = new GridBagConstraints();
        gbConstraint.gridx  = 2;
        gbConstraint.gridy  = 0;
        gbConstraint.ipadx  = 25;
        gbConstraint.ipady  = 25;
        gbConstraint.anchor = GridBagConstraints.NORTHWEST;
        gbConstraint.insets = new Insets(6, 6, 0, 0);
        add(redIndicator, gbConstraint);
        
        greenIndicator.setBackground(new Color(102, 255, 102));
        greenIndicator.setPreferredSize(new Dimension(25, 25));
        gbConstraint        = new GridBagConstraints();
        gbConstraint.gridx  = 2;
        gbConstraint.gridy  = 3;
        gbConstraint.ipadx  = 25;
        gbConstraint.ipady  = 25;
        gbConstraint.anchor = GridBagConstraints.NORTHWEST;
        gbConstraint.insets = new Insets(6, 6, 0, 0);
        add(greenIndicator, gbConstraint);
        
        blueIndicator.setBackground(new Color(51, 0, 255));
        blueIndicator.setPreferredSize(new Dimension(25, 25));
        gbConstraint            = new GridBagConstraints();
        gbConstraint.gridx      = 2;
        gbConstraint.gridy      = 6;
        gbConstraint.gridheight = 8;
        gbConstraint.ipadx      = 25;
        gbConstraint.ipady      = 25;
        gbConstraint.anchor     = GridBagConstraints.NORTHWEST;
        gbConstraint.insets     = new Insets(6, 6, 0, 0);
        add(blueIndicator, gbConstraint);
        
        finalColorIndicator.setBackground(new Color(0, 102, 51));
        gbConstraint            = new GridBagConstraints();
        gbConstraint.gridx      = 0;
        gbConstraint.gridy      = 0;
        gbConstraint.gridheight = 7;
        gbConstraint.ipadx      = 50;
        gbConstraint.ipady      = 50;
        gbConstraint.anchor     = GridBagConstraints.NORTHWEST;
        gbConstraint.insets     = new Insets(30, 12, 0, 0);
        add(finalColorIndicator, gbConstraint);
    }
}