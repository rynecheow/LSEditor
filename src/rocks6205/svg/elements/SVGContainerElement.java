/**
 * 
 * Class: SVGContainerElement
 * Description: 
 * 
 * @author: Cheow Yeong Chi
 * @version: 1.0
 * @date: 14/03/2013
 * 
 */

package rocks6205.svg.elements;

import java.util.Vector;


public abstract class SVGContainerElement extends SVGGenericElement{
	private Vector<SVGGenericElement> descendants;

	public SVGContainerElement() {
		descendants = new Vector<SVGGenericElement>();
	}

	public Vector<SVGGenericElement> getDescendants() {
		return descendants;
	}

	public SVGGenericElement getDescendant(int index) {
		return descendants.get(index);
	}

	public void addDescendant(SVGGenericElement e) {
		descendants.add(e);
		e.setAncestorElement(this);
	}

	public void insertDescendant(SVGGenericElement e,int atIndex) {

	}

	public void replaceDescendant(SVGGenericElement e,int atIndex) {

	}

	public void replaceDescendant(SVGGenericElement e, SVGGenericElement eNew) {
	}

	public void removeDescendant(int index) {

	}

	public void removeDescendant(SVGGenericElement e) {

	}
}
