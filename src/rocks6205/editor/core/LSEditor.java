package rocks6205.editor.core;

//~--- non-JDK imports --------------------------------------------------------

import com.memetix.mst.language.Language;

import rocks6205.system.properties.OSValidator;
import rocks6205.system.properties.Translator;

//~--- JDK imports ------------------------------------------------------------

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import rocks6205.editor.viewcomponents.LSUISplashScreen;

/**
 * Execute main function.
 *
 * @author Cheow Yeong Chi
 * @author Sugar Chee-Sheen Chan
 * 
 * @since 1.0
 *
 */
public class LSEditor {
    public static final Logger   logger = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());
    public static ResourceBundle titleBundle;
    public static ClassLoader languageLoader;
    
    public static void main(String[] rcks) throws Exception {
       Locale locale = new Locale("ru", "MO");
       File file = new File("lang/" + "LSEditor_" + locale
                             + ".properties");    /* Locale.FRANCE */

        logger.info(String.format("Your default locale is %s \n", Locale.getDefault().toString()));
        
        if (!file.exists()) {
           titleBundle = ResourceBundle.getBundle("LSEditor", Locale.US, languageLoader);
           LSUISplashScreen splash = new LSUISplashScreen(10000);
            splash.showSplash();
            Translator.translate(new File("lang/" + "LSEditor_" + Locale.US
                   + ".properties"), file,
                    Language.fromString(locale.getLanguage()));    /* Locale.FRANCE.getLanguage() */
        }
         
        String languageBundlePath = "lang" + File.separator;
        URL[] urls = {new File(languageBundlePath).toURI().toURL()};  
        languageLoader = new URLClassLoader(urls);
        
        titleBundle = ResourceBundle.getBundle("LSEditor", locale, languageLoader);    /* Locale.FRANCE */

        String message = String.format("The current active OS is " + OSValidator.getOS() + ".\n");

        logger.info(message);

      if (!OSValidator.isMac()) {
          JOptionPane.showMessageDialog(
              new JFrame(),
              message + "The current application only provide support for Mac OS X. The application will exit now.",
              "Operating system not supported", JOptionPane.WARNING_MESSAGE);
          System.exit(0);
      }
        setUpLookAndFeel();

      
        LSViewController c = new LSViewController();

        c.createBlankDocument();

        LSView v = new LSView(c);

        c.addView(v);
        v.setVisible(true);
        v.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        if (rcks.length > 0) {
            try {
                c.fileLoad(new File(rcks[0]));
            } catch (IOException e) {
                LSEditor.logger.warning(e.getMessage());
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
                LSEditor.logger.warning(ex.getMessage());
            }
        } else {
            try {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                        LSEditor.logger.info(UIManager.getLookAndFeel().getName());

                        break;
                    }
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                     | UnsupportedLookAndFeelException e) {
                LSEditor.logger.warning(e.getMessage());
            }
        }
    }
}