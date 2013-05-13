
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package rocks6205.editor.model.elements;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 * Superclass that handles shape object.
 * @author Cheow Yeong Chi
 */
public abstract class LSGenericShape extends LSGenericElement {
    
    public static final int SHAPE_CIRCLE = 1;
    public static final int SHAPE_RECT = 2;
    public static final int SHAPE_LINE = 3;
    
    @Override
    public abstract Rectangle2D.Float getBounds();

    @Override
    public abstract void drawShape(Graphics2D g);
    
    /**
     * Gets shape type.
     * @return Shape type.
     */
    public abstract int getShapeType();
}