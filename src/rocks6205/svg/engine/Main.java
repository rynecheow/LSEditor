package rocks6205.svg.engine;

import java.io.File;

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
	}

}
