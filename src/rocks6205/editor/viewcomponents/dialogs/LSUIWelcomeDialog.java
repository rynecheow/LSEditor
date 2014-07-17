package rocks6205.editor.viewcomponents.dialogs;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.actions.LSAbstractAction.OpenFileAction;
import rocks6205.editor.core.LSEditor;
import rocks6205.editor.core.LSView;
import rocks6205.editor.viewcomponents.LSUIProtocol;

import javax.swing.*;
import java.awt.*;

//~--- JDK imports ------------------------------------------------------------

/**
 * Welcome screen when user first use the application.
 *
 * @author Cheow Yeong Chi
 * @since 2.2
 */
public final class LSUIWelcomeDialog extends JDialog implements LSUIProtocol {

    /*
     * SIZE PROPERTIES
     */
    private int width;
    private int height;
    private Dimension screen;
    private int x;
    private int y;

    /*
     * GUI COMPONENTS
     */
    private JPanel drawPanel;

    /*
     * PARENT COMPONENT
     */
    private LSView parentView;


    /**
     * Constructs a welcome dialog with parent component.
     *
     * @param parent Parent component.
     */
    public LSUIWelcomeDialog(LSView parent) {
        super(parent, "Welcome Screen", true);
        this.height = 450;
        this.width = 600;
        parentView = parent;
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
        drawPanel = drawNewWelcomePanel();
        new OpenFileAction(LSEditor.titleBundle, parentView);
    }

    /**
     * Set up dialog size properties.
     */
    private void setUpProperties() {
        screen = Toolkit.getDefaultToolkit().getScreenSize();
        x = (screen.width - width) / 2;
        y = (screen.height - height) / 2;
    }

    /**
     * Creates a custom JPanel for welcome screen.
     *
     * @return custom drawn <code>JPanel</code>.
     */
    private JPanel drawNewWelcomePanel() {
        return new JPanel() {

            /**
             * {@inheritDoc}
             */
            @Override
            protected void paintComponent(Graphics g) {
            }
        };
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void customise() {
        layoutView();
        layoutDrawPanel();
        getContentPane().add(drawPanel);
        drawPanel.setBounds(0, 0, width, height);
        pack();
        setBounds(x, y, width, height);
    }

    /**
     * Layout view.
     */
    private void layoutView() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setMaximumSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setPreferredSize(new Dimension(width, height));
        getContentPane().setLayout(null);
    }

    /**
     * Layout <code>drawPanel</code>
     */
    private void layoutDrawPanel() {
        drawPanel.setMaximumSize(new Dimension(width, height));
        drawPanel.setPreferredSize(new Dimension(width, height));

        GroupLayout drawPanelLayout = new GroupLayout(drawPanel);

        drawPanel.setLayout(drawPanelLayout);
        drawPanelLayout.setHorizontalGroup(drawPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0,
                width, Short.MAX_VALUE));
        drawPanelLayout.setVerticalGroup(drawPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0,
                height, Short.MAX_VALUE));
    }

    public void display() {
        setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bindHandlers() {
    }
}