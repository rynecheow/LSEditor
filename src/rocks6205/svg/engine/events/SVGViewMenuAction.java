/**
 * 
 * Class: SVGViewMenuAction
 * Description: 
 * 
 * @author: Cheow Yeong Chi
 * @date: 14/03/2013
 * 
 */

package rocks6205.svg.engine.events;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;

import rocks6205.svg.engine.SVGView;

public abstract class SVGViewMenuAction extends AbstractAction {

	private static final long serialVersionUID = 9085407834848872724L;
	/*
	 * CONSTRUCTOR
	 */
	private SVGView parent;

	public SVGViewMenuAction(String actionName, String tooltipText,
			Integer mnemonic, KeyStroke keyStroke, SVGView parent) {
		putValue(Action.NAME,actionName);
		putValue(SHORT_DESCRIPTION, tooltipText);
		putValue(MNEMONIC_KEY, mnemonic);
		putValue(ACCELERATOR_KEY, keyStroke);
		this.parent = parent;
	}

	public static class OpenFileAction extends SVGViewMenuAction {
		private static final long serialVersionUID = -7823707833188816535L;

		public OpenFileAction(SVGView parent) {
			super("Open", "Open a file", KeyEvent.VK_O, KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK), parent);
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			JFileChooser fc = new JFileChooser();
			if (fc.showOpenDialog(super.parent) == JFileChooser.APPROVE_OPTION) {
				super.parent.getController().fileLoad(fc.getSelectedFile());

			}
		}
	}

	public static class ExitProgramAction extends SVGViewMenuAction {
		private static final long serialVersionUID = 6507259946341784118L;

		public ExitProgramAction(SVGView parent) {
			super("Exit", null, KeyEvent.VK_X, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK), parent);
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			System.exit(0);
		}
	}

	public static class ZoomOutAction extends SVGViewMenuAction {
		private static final long serialVersionUID = -6578149781110081473L;

		public ZoomOutAction(SVGView parent) {
			super("Zoom Out", null, KeyEvent.VK_O, KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, ActionEvent.CTRL_MASK), parent);
			setEnabled(false);
		}

		@Override
		public void actionPerformed(ActionEvent event) {

		}
	}

	public static class ZoomInAction extends SVGViewMenuAction {

		private static final long serialVersionUID = 1180439021996674018L;

		public ZoomInAction(SVGView parent) {
			super("Zoom In", null, KeyEvent.VK_I, KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, ActionEvent.CTRL_MASK), parent);
		}

		@Override
		public void actionPerformed(ActionEvent event) {

		}
	}
}