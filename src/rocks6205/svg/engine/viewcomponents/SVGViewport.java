package rocks6205.svg.engine.viewcomponents;

import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

import rocks6205.svg.engine.SVGView;
import rocks6205.svgFamily.SVGCanvasProperties;
import rocks6205.svgFamily.SVGImageCanvas;

public class SVGViewport extends JPanel {

	private static final long serialVersionUID = -7920677728155693552L;
	private SVGImageCanvas canvas;

	public SVGViewport(SVGView view) {
		int resolution = Toolkit.getDefaultToolkit().getScreenResolution();
		SVGCanvasProperties.setOutputResolution(resolution);
		SVGCanvasProperties.setFontSize((this.getFont().getSize2D()));
	}

	public void setCanvas(SVGImageCanvas canvas) {
		this.canvas = canvas;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(canvas, 0, 0, null);
	}
}
