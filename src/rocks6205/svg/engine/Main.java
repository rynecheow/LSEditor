package rocks6205.svg.engine;

//~--- JDK imports ------------------------------------------------------------

import java.io.File;

import javax.swing.JFrame;

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

        m.addObserver(v);
        v.setModel(m);
        v.setController(c);
        v.setVisible(true);

        if (rcks.length > 0) {
            fileURI = rcks[0];
            c.fileLoad(new File(fileURI));
        }

        v.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
}
