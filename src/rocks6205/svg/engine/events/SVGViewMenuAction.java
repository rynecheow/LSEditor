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
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import rocks6205.svg.engine.SVGView;
import rocks6205.svgFamily.SVGImageCanvas;

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
			fc.setMultiSelectionEnabled(false);
			fc.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter extFilter = new FileNameExtensionFilter("SVG Documents", "svg");
			fc.setFileFilter(extFilter);
			if (fc.showOpenDialog(super.parent) == JFileChooser.APPROVE_OPTION) {
				super.parent.getController().fileLoad(fc.getSelectedFile());

			}
		}
	}

	public static class ExitAction extends SVGViewMenuAction {
		private static final long serialVersionUID = 6507259946341784118L;

		public ExitAction(SVGView parent) {
			super("Exit", null, KeyEvent.VK_X, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK), parent);
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			int closeCf = JOptionPane.showConfirmDialog(null, "Exit SVG Editor?", "Confirm exit", JOptionPane.WARNING_MESSAGE);
			if (closeCf == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}
	public static class ZoomInViewAction extends SVGViewMenuAction {

		private static final long serialVersionUID = 1180439021996674018L;

		private ZoomOutViewAction zoomOutPartnerAction;

		public ZoomInViewAction(SVGView parent) {
			this(parent, null);
		}

		public ZoomInViewAction(SVGView parent, ZoomOutViewAction zoomOutPartnerAction) {
			super("Zoom In", null, KeyEvent.VK_I, KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, ActionEvent.CTRL_MASK), parent);
			this.zoomOutPartnerAction = zoomOutPartnerAction;
		}

		public void setZoomOutPartnerAction(ZoomOutViewAction zoomOutPartnerAction){
			this.zoomOutPartnerAction= zoomOutPartnerAction;
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			SVGImageCanvas.setZoomScale(SVGImageCanvas.getZoomScale() + 1);
			super.parent.getModel().render();
			zoomOutPartnerAction.setEnabled(true);
		}
	}

	public static class ZoomOutViewAction extends SVGViewMenuAction {
		private static final long serialVersionUID = -6578149781110081473L;

		public ZoomOutViewAction(SVGView parent) {
			super("Zoom Out", null, KeyEvent.VK_O, KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, ActionEvent.CTRL_MASK), parent);
			setEnabled(false);

		}

		@Override
		public void actionPerformed(ActionEvent event) {
			SVGImageCanvas.setZoomScale(SVGImageCanvas.getZoomScale() - 1);
			super.parent.getModel().render();
			if(SVGImageCanvas.getZoomScale() < 2){
				setEnabled(false);
			}
		}
	}


}