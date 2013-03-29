package rocks6205.svg.engine;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.svgFamily.OSValidator;

//~--- JDK imports ------------------------------------------------------------

import java.io.File;

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
public class Main {
    public static void main(String[] rcks) {
        String            fileURI;
        SVGModel          m = new SVGModel();
        SVGView           v = new SVGView();
        SVGViewController c = new SVGViewController(m, v);

        setUpLookAndFeel();
        m.addObserver(v);
        v.setModel(m);
        v.setController(c);
        v.setVisible(true);
        v.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        if (rcks.length > 0) {
            fileURI = rcks[0];
            c.fileLoad(new File(fileURI));
        }
    }

    /**
     * Setup look and feel for GUI.
     * @param style <code>String</code> containing the name
     */
    private static void setUpLookAndFeel() {
        if (OSValidator.isWindows()) {
            try {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        System.out.println(UIManager.getLookAndFeel().getName());

                        break;
                    }
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                     | UnsupportedLookAndFeelException e) {}
        }
    }
}
