package rocks6205.editor.viewcomponents;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.core.LSEditor;

import javax.swing.*;
import java.awt.*;

//~--- JDK imports ------------------------------------------------------------

/**
 * Splash screen.
 *
 * @author Cheow Yeong Chi
 * @since 2.1
 */
public final class LSUISplashScreen extends JWindow {
    private int duration;

    /**
     * Constructs a splash screen for a duration of <code>d</code> milliseconds
     *
     * @param d Duration of splash screen will show.
     */
    public LSUISplashScreen(int d) {
        duration = d;
    }

    /**
     * Display splash screen.
     */
    public void showSplash() {
        JPanel content = (JPanel) getContentPane();

        content.setBackground(Color.white);
        setUpProperties();

        // Build the splash screen
        JLabel label = new JLabel(new ImageIcon("imageicon/LSSplashScreen.png"));
        JLabel copyrt = new JLabel(LSEditor.titleBundle.getString("translate.text"), JLabel.CENTER);

        copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 12));
        content.add(label, BorderLayout.CENTER);
        content.add(copyrt, BorderLayout.SOUTH);

        Color oraRed = new Color(156, 20, 20, 255);

        content.setBorder(BorderFactory.createLineBorder(oraRed, 10));

        // Display it
        setVisible(true);

        // Wait a little while, maybe while loading resources
        try {
            Thread.sleep(duration);
        } catch (Exception e) {
            LSEditor.logger.warning(e.getMessage());
        }

        setVisible(false);
        dispose();
    }

    /**
     * Set up splash screen size properties.<br>
     * Set the window's bounds, centering the window.
     */
    private void setUpProperties() {
        int width = 450;
        int height = 115;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;

        setBounds(x, y, width, height);
    }
}