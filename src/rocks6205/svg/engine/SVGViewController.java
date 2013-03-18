/**
 * 
 * Class: SVGViewController
 * Description: 
 * 
 * @author: Cheow Yeong Chi
 * @version: 1.0
 * @date: 14/03/2013
 * 
 */

package rocks6205.svg.engine;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class SVGViewController implements MouseMotionListener, MouseListener {
	/*
	 * PROPERTIES
	 */
	private SVGModel model;
	private SVGView view;

	/*
	 * CONSTRUCTOR
	 */
	public SVGViewController(SVGModel model, SVGView view) {
		setModel(model);
		setView(view);
	}

	/*
	 * ACCESSORS
	 */
	public SVGView getView() {
		return view;
	}

	public SVGModel getModel() {
		return model;
	}

	/*
	 * MUTATORS
	 */
	public void setModel(SVGModel model) {
		this.model = model;
	}


	public void setView(SVGView view) {
		this.view = view;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
