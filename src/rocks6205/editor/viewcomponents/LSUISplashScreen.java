package rocks6205.editor.viewcomponents;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.core.LSEditor;

//~--- JDK imports ------------------------------------------------------------

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

/**
 * Splash screen.
 *
 * @author Cheow Yeong Chi
 *
 * @since 2.1
 */
public final class LSUISplashScreen extends JWindow {
    private int duration;

    public LSUISplashScreen(int d) {
        duration = d;
    }

    public void showSplash() {
        JPanel content = (JPanel) getContentPane();

        content.setBackground(Color.white);

        // Set the window's bounds, centering the window
        int       width  = 450;
        int       height = 115;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int       x      = (screen.width - width) / 2;
        int       y      = (screen.height - height) / 2;

        setBounds(x, y, width, height);

        // Build the splash screen
        JLabel label  = new JLabel(new ImageIcon("imageicon/LSSplashScreen.png"));
        JLabel copyrt = new JLabel("Copyright ROCKS6205, LSEditor, v2.1(build x). Open-source.", JLabel.CENTER);

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
}