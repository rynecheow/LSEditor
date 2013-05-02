package rocks6205.editor.mvc;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.viewcomponents.LSUISplashScreen;

import rocks6205.system.properties.OSValidator;

//~--- JDK imports ------------------------------------------------------------

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Execute main function.
 *
 * @author Cheow Yeong Chi
 * @since 1.0
 *
 */
public class LSSVGEditor {
    public static final Logger logger = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());
   
    public static void main(String[] rcks) {
       logger.info(String.format("The current active OS is " + OSValidator.getOS()+".\n"));
//      LSUISplashScreen splash = new LSUISplashScreen(5000);
//
//      splash.showSplash();
        setUpLookAndFeel();

        SVGEditorViewController c = new SVGEditorViewController();

        c.createBlankDocument();

        SVGEditorView v = new SVGEditorView(c);
        c.addView(v);
        v.setVisible(true);
        v.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        if (rcks.length > 0) {
            try {
                c.fileLoad(new File(rcks[0]));
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

        c.updateViews();
    }

    /**
     * Setup look and feel for GUI.
     * @param style <code>String</code> containing the name
     */
    private static void setUpLookAndFeel() {
        if (OSValidator.isMac()) {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "My Application");

            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                     | UnsupportedLookAndFeelException ex) {
                System.err.println(ex.getMessage());
            }
        } else {
            try {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        System.out.println(UIManager.getLookAndFeel().getName());

                        break;
                    }
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                     | UnsupportedLookAndFeelException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}