/**
 * 
 */
package rocks6205.svg.adt;

/**
 * @author Komalah
 * This class is under modification..
 *
 */
public class SVGGroup {
    
    private final SVGProperties mSVGProperties;
    private final boolean mHasTransform;

 
    // Constructors


    public SVGGroup(final SVGProperties pSVGProperties, final boolean pHasTransform) {
            this.mSVGProperties = pSVGProperties;
            this.mHasTransform = pHasTransform;
    }


    // Getter & Setter
   

    public boolean hasTransform() {
            return this.mHasTransform;
    }

    public SVGProperties getSVGProperties() {
            return this.mSVGProperties;
    }

}
