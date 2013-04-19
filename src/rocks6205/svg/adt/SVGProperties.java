
/**
 *
 */
package rocks6205.svg.adt;

//~--- non-JDK imports --------------------------------------------------------

import org.xml.sax.Attributes;

import rocks6205.svg.util.SAXHelper;
import rocks6205.svg.util.SVGParserUtils;

/**
 * @author Komalah
 * This is the class where to identify the SVG properties before doing the grouping..
 * And I think we may call the core methods for SVG image properties and override the methods from this class to other functional
 * classes.
 */
public class SVGProperties {

    private final SVGStyleSet mSVGStyleSet;
    private final Attributes mAttributes;
    private final SVGProperties mParentSVGProperties;

    public SVGProperties(final Attributes pAttributes, final SVGProperties pParentSVGProperties) {
            this.mAttributes = pAttributes;
            this.mParentSVGProperties = pParentSVGProperties;
            final String styleAttr = SAXHelper.getStringAttribute(pAttributes, "style");
            if (styleAttr != null) {
                    this.mSVGStyleSet = new SVGStyleSet(styleAttr);
            } else {
                    this.mSVGStyleSet = null;
            }
    }

  
    public String getStringProperty(final String pPropertyName) {
            return getStringProperty(pPropertyName, true);
    }

    public String getStringProperty(final String pPropertyName, final boolean pAllowParentSVGProperties) {
            String s = null;
            if (this.mSVGStyleSet != null) {
                    s = this.mSVGStyleSet.getStyle(pPropertyName);
            }
            if (s == null) {
                    s = SAXHelper.getStringAttribute(this.mAttributes, pPropertyName);
            }
            if(s == null && pAllowParentSVGProperties) {
                    if(this.mParentSVGProperties == null) {
                            return null;
                    } else {
                            return this.mParentSVGProperties.getStringProperty(pPropertyName);
                    }
            } else {
                    return s;
            }
    }

    public String getStringProperty(final String pPropertyName, final String pDefaultValue) {
            final String s = this.getStringProperty(pPropertyName);
            if (s == null) {
                    return pDefaultValue;
            } else {
                    return s;
            }
    }

    public Float getFloatProperty(final String pPropertyName) {
            return SVGParserUtils.parseFloatAttribute(this.getStringProperty(pPropertyName));
    }

    public Float getFloatProperty(final String pPropertyName, final float pDefaultValue) {
            final Float f = this.getFloatProperty(pPropertyName);
            if (f == null) {
                    return pDefaultValue;
            } else {
                    return f;
            }
    }

    public String getStringAttribute(final String pAttributeName) {
            return SAXHelper.getStringAttribute(this.mAttributes, pAttributeName);
    }

    public String getStringAttribute(final String pAttributeName, final String pDefaultValue) {
            return SAXHelper.getStringAttribute(this.mAttributes, pAttributeName, pDefaultValue);
    }

    public Float getFloatAttribute(final String pAttributeName) {
            return SAXHelper.getFloatAttribute(this.mAttributes, pAttributeName);
    }

    public float getFloatAttribute(final String pAttributeName, final float pDefaultValue) {
            return SAXHelper.getFloatAttribute(this.mAttributes, pAttributeName, pDefaultValue);
    }

}
