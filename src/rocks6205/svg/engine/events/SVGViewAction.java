package rocks6205.svg.engine.events;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

public abstract class SVGViewAction extends AbstractAction {

	private static final long serialVersionUID = 9085407834848872724L;
	/*
	 * CONSTRUCTOR
	 */
	public SVGViewAction(String actionName, String tooltipText,
			Integer mnemonic, KeyStroke keyStroke) {
		putValue(Action.NAME,actionName);
		putValue(SHORT_DESCRIPTION, tooltipText);
		putValue(MNEMONIC_KEY, mnemonic);
		putValue(ACCELERATOR_KEY, keyStroke);
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}

}
