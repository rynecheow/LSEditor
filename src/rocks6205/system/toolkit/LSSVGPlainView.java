package rocks6205.system.toolkit;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.text.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author kees
 * @author Cheow Yeong Chi
 * @since 2.5
 */
public final class LSSVGPlainView extends PlainView {
    private final static Color HIGHLIGHT_COLOR_START_END_TAG = new Color(0x64d3e7);
    private final static Color HIGHLIGHT_COLOR_ATTRIBUTE_VALUE = new Color(0xae81ff);
    private final static Color HIGHLIGHT_COLOR_ATTRIBUTE_TAG = new Color(0xe9286a);
    private final static Color HIGHLIGHT_COLOR_COMMENT = new Color(0x535454);
    private final static Color HIGHLIGHT_COLOR_CDATA = new Color(0x535454);
    private final static String SYNTAX_START_TAG = "(</?!?(\\?)*[a-z]*)\\s?>?";
    private final static String SYNTAX_END_TAG =
            "(<{0,1})((/*)|(\\?*))(\\w*)>{1}";
    private final static String SYNTAX_ATTRIBUTE_TAG =
            "\\s(\\p{L}+(?:(-|:)\\n?\\p{L}+)*)(\\w*)\\=";
    private final static String SYNTAX_ATTRIBUTE_VALUE = "\\w*\\=(\"[^\"]*\")";
    private final static String SYNTAX_COMMENT = "(<!--.*-->)";
    private final static String SYNTAX_CDATA = "(\\<!\\[CDATA\\[).*";
    private final static String SYNTAX_CDATA_END = ".*(]]>)";
    private final static LinkedHashMap<Pattern, Color> syntaxHighlighter;

    static {
        syntaxHighlighter = new LinkedHashMap<>();
        syntaxHighlighter.put(Pattern.compile(SYNTAX_CDATA), HIGHLIGHT_COLOR_CDATA);
        syntaxHighlighter.put(Pattern.compile(SYNTAX_CDATA_END), HIGHLIGHT_COLOR_CDATA);
        syntaxHighlighter.put(Pattern.compile(SYNTAX_START_TAG), HIGHLIGHT_COLOR_START_END_TAG);
        syntaxHighlighter.put(Pattern.compile(SYNTAX_ATTRIBUTE_TAG), HIGHLIGHT_COLOR_ATTRIBUTE_TAG);
        syntaxHighlighter.put(Pattern.compile(SYNTAX_END_TAG), HIGHLIGHT_COLOR_START_END_TAG);
        syntaxHighlighter.put(Pattern.compile(SYNTAX_ATTRIBUTE_VALUE), HIGHLIGHT_COLOR_ATTRIBUTE_VALUE);
        syntaxHighlighter.put(Pattern.compile(SYNTAX_COMMENT), HIGHLIGHT_COLOR_COMMENT);
    }

    /**
     * Constructor. Sets the tab size to three spaces.
     *
     * @param e Element to be parsed in.
     */
    public LSSVGPlainView(Element e) {
        super(e);
        getDocument().putProperty(PlainDocument.tabSizeAttribute, 3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int drawUnselectedText(Graphics g, int xCoord, int yCoord, int beginIndex, int endIndex)
            throws BadLocationException {
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Document doc = getDocument();
        String docTxt = doc.getText(beginIndex, endIndex - beginIndex);
        TreeMap<Integer, Integer> indexMap = new TreeMap<>();
        TreeMap<Integer, Color> colorMap = new TreeMap<>();
        Segment txtSegment = getLineBuffer();

        for (Entry<Pattern, Color> currentEntry : syntaxHighlighter.entrySet()) {
            Matcher matcher = currentEntry.getKey().matcher(docTxt);

            while (matcher.find()) {
                indexMap.put(matcher.start(1), matcher.end());
                colorMap.put(matcher.start(1), currentEntry.getValue());
            }
        }

        int u = 0;

        for (Entry<Integer, Integer> currentEntry : indexMap.entrySet()) {
            int startIndex = currentEntry.getKey();
            int stopIndex = currentEntry.getValue();

            if (u < startIndex) {
                g.setColor(Color.white);
                doc.getText(beginIndex + u, startIndex - u, txtSegment);
                xCoord = Utilities.drawTabbedText(txtSegment, xCoord, yCoord, g, this, u);
            }

            g.setColor(colorMap.get(startIndex));
            u = stopIndex;
            doc.getText(beginIndex + startIndex, u - startIndex, txtSegment);
            xCoord = Utilities.drawTabbedText(txtSegment, xCoord, yCoord, g, this, startIndex);
        }

        if (u < docTxt.length()) {
            g.setColor(Color.black);
            doc.getText(beginIndex + u, docTxt.length() - u, txtSegment);
            xCoord = Utilities.drawTabbedText(txtSegment, xCoord, yCoord, g, this, u);
        }

        return xCoord;
    }
}