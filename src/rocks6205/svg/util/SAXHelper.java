package rocks6205.svg.util;

//~--- non-JDK imports --------------------------------------------------------

import org.xml.sax.Attributes;

/**
 * @author Komalah
 *
 */
public class SAXHelper {
    public static String getStringAttribute(final Attributes pAttributes, final String pAttributeName) {
        final int attributeCount = pAttributes.getLength();

        for (int i = 0; i < attributeCount; i++) {
            if (pAttributes.getLocalName(i).equals(pAttributeName)) {
                return pAttributes.getValue(i);
            }
        }

        return null;
    }

    public static String getStringAttribute(final Attributes pAttributes, final String pAttributeName,
            final String pDefaultValue) {
        final String s = SAXHelper.getStringAttribute(pAttributes, pAttributeName);

        if (s == null) {
            return pDefaultValue;
        } else {
            return s;
        }
    }

    public static Float getFloatAttribute(final Attributes pAttributes, final String pAttributeName) {
        return SVGParserUtils.parseFloatAttribute(SAXHelper.getStringAttribute(pAttributes, pAttributeName));
    }

    public static float getFloatAttribute(final Attributes pAttributes, final String pAttributeName,
            final float pDefaultValue) {
        final Float f = SAXHelper.getFloatAttribute(pAttributes, pAttributeName);

        if (f == null) {
            return pDefaultValue;
        } else {
            return f;
        }
    }
}