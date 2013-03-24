/**
 * 
 * Class: Main
 * Description: Execute main function
 * 
 * @author: Cheow Yeong Chi
 * @date: 14/03/2013
 * 
 */
package rocks6205.svg.engine;

import java.io.File;

import javax.swing.JFrame;

public class Main {

	public static void main(String []rcks){

		String fileURI;

		SVGModel m = new SVGModel();
		SVGView v = new SVGView();
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
