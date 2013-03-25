/**
 * 
 * Class: SVGViewport
 * Description: 
 * 
 * @author: Cheow Yeong Chi
 * @date: 20/03/2013
 * 
 */
package rocks6205.svg.engine.viewcomponents;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;

import rocks6205.svg.engine.SVGView;
import rocks6205.svg.engine.events.SVGViewPanMouseEvent;
import rocks6205.svgFamily.SVGCanvasProperties;
import rocks6205.svgFamily.SVGImageCanvas;

public class SVGViewport extends JPanel {

	private static final long serialVersionUID = -7920677728155693552L;
	private SVGImageCanvas canvas;

	private SVGViewPanMouseEvent panListener;
	private boolean init = false;
	public SVGViewport(SVGView view) {
		panListener = new SVGViewPanMouseEvent(this);
		addListener(panListener);
		SVGCanvasProperties.setOutputResolution(Toolkit.getDefaultToolkit().getScreenResolution());
		SVGCanvasProperties.setFontSize((this.getFont().getSize2D()));
	}

	private void addListener(SVGViewPanMouseEvent mouseHandler) {
		addMouseListener(mouseHandler);
		addMouseMotionListener(mouseHandler);
		addMouseWheelListener(mouseHandler);
	}

	public void setCanvas(SVGImageCanvas canvas) {
		this.canvas = canvas;
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D graphics2D = (Graphics2D) g;
		if (init ) {
			// Initialize the viewport by moving the origin to the center of the window,
			// and inverting the y-axis to point upwards.
			init = false;
			Dimension d = getSize();
			int xc = d.width / 2;
			int yc = d.height / 2;
			graphics2D.translate(xc, yc);
			graphics2D.scale(1, -1);
			// Save the viewport to be updated by the ZoomAndPanListener
			panListener.setCoordinateTransform(graphics2D.getTransform());
		} else {
			// Restore the viewport after it was updated by the ZoomAndPanListener
			graphics2D.setTransform(panListener.getCoordinateTransform());
		}

		super.paintComponent(graphics2D);
		graphics2D.drawImage(canvas, 0, 0, null);
	}
}
