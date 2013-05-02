
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package rocks6205.editor.model.elements;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Cheow Yeong Chi
 */
public abstract class SVGShapeElement extends SVGGenericElement {
    @Override
    public abstract Rectangle2D.Float getBounds();

    @Override
    public abstract void drawShape(Graphics2D g);
}