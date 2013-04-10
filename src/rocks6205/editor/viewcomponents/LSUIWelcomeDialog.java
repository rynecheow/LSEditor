package rocks6205.editor.viewcomponents;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.events.LSAction.OpenFileAction;
import rocks6205.editor.mvc.SVGEditorView;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 * 
 * @author Cheow Yeong Chi
 * 
 * @since 2.2
 */
public final class LSUIWelcomeDialog extends JDialog implements LSUIProtocol {

    /*
     * SIZE PROPERTIES
     */
    int       width  = 600;
    int       height = 450;
    Dimension screen;
    int       x;
    int       y;

    /*
     * GUI COMPONENTS
     */
    private JPanel drawPanel;

    /*
     * PARENT COMPONENT
     */
    SVGEditorView parentView;

    /*
     * ACTION COMPONENTS
     */
    OpenFileAction openFileAction;

    public LSUIWelcomeDialog(SVGEditorView parent) {
        super(parent, "Welcome Screen", true);
        parentView = parent;
        initialise();
        customise();
        bindEventActions();
    }

    @Override
    public void initialise() {
        setUpProperties();
        drawPanel      = drawNewWelcomePanel();
        openFileAction = new OpenFileAction(parentView);
    }

    private void setUpProperties() {
        screen = Toolkit.getDefaultToolkit().getScreenSize();
        x      = (screen.width - width) / 2;
        y      = (screen.height - height) / 2;
    }

    private JPanel drawNewWelcomePanel() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {}
        };
    }

    @Override
    public void customise() {
        layoutView();
        layoutDrawPanel();
        getContentPane().add(drawPanel);
        drawPanel.setBounds(x, y, width, height);
        pack();
    }

    private void layoutView() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setMaximumSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setPreferredSize(new Dimension(width, height));
        getContentPane().setLayout(null);
    }

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

    private void bindEventActions() {}
}