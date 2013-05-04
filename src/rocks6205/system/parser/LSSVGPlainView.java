/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rocks6205.system.parser;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.PlainDocument;
import javax.swing.text.PlainView;
import javax.swing.text.Segment;
import javax.swing.text.Utilities;
/**
 * 
 * @author kees
 * @author Cheow Yeong Chi
 */
public final class LSSVGPlainView extends PlainView {

    private final static LinkedHashMap<Pattern, Color> syntaxHighlighter;
    private final static Color HIGHLIGHT_COLOR_START_END_TAG = new Color(0x64d3e7);
    private final static Color HIGHLIGHT_COLOR_ATTRIBUTE_VALUE = new Color(0xae81ff);
    private final static Color HIGHLIGHT_COLOR_ATTRIBUTE_TAG = new Color(0xe9286a);
    private final static Color HIGHLIGHT_COLOR_COMMENT = new Color(0x535454);
    private final static Color HIGHLIGHT_COLOR_CDATA = new Color(0x535454);
    
    private final static String SYNTAX_START_TAG = "(</?(\\?)*[a-z]*)\\s?>?";
    private final static String SYNTAX_END_TAG = "((/*)|(\\?*))>";
    private final static String SYNTAX_ATTRIBUTE_TAG = "\\s(\\p{L}+(?:(-|:)\\n?\\p{L}+)*)(\\w*)\\=";
    private final static String SYNTAX_ATTRIBUTE_VALUE = "\\w*\\=(\"[^\"]*\")";
    private final static String SYNTAX_COMMENT = "(<!--.*-->)";
    private final static String SYNTAX_CDATA = "(\\<!\\[CDATA\\[).*";
    private final static String SYNTAX_CDATA_END = ".*(]]>)";
    
    
    
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
    
    public LSSVGPlainView(Element e) {
        super(e);
        getDocument().putProperty(PlainDocument.tabSizeAttribute, 3);
    }
    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected int drawUnselectedText(Graphics g, int xCoord, int yCoord, int beginIndex,
            int endIndex) throws BadLocationException {
       ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Document doc = getDocument();
        String text = doc.getText(beginIndex, endIndex - beginIndex);
        TreeMap<Integer, Integer> startMap = new TreeMap<>();
        TreeMap<Integer, Color> colorMap = new TreeMap<>();
        Segment segment = getLineBuffer();
        
       for (Iterator<Map.Entry<Pattern, Color>> it = syntaxHighlighter.entrySet().iterator(); it.hasNext();) {
          Map.Entry<Pattern, Color> entry = it.next();
          Matcher matcher = entry.getKey().matcher(text);
          while (matcher.find()) {
              startMap.put(matcher.start(1), matcher.end());
              colorMap.put(matcher.start(1), entry.getValue());
          }
       }
 
        // TODO: check the map for overlapping parts
         
       int u = 0;
       for (Iterator<Entry<Integer, Integer>> it = startMap.entrySet().iterator(); it.hasNext();) {
          Entry<Integer, Integer> entry = it.next();
          int start = entry.getKey();
          int end = entry.getValue();
          if (u < start) {
              g.setColor(Color.black);
              doc.getText(beginIndex + u, start - u, segment);
              xCoord = Utilities.drawTabbedText(segment, xCoord, yCoord, g, this, u);
          }
          g.setColor(colorMap.get(start));
          u = end;
          doc.getText(beginIndex + start, u - start, segment);
          xCoord = Utilities.drawTabbedText(segment, xCoord, yCoord, g, this, start);
       }
 
        // Paint possible remaining text black
        if (u < text.length()) {
            g.setColor(Color.black);
            doc.getText(beginIndex + u, text.length() - u, segment);
            xCoord = Utilities.drawTabbedText(segment, xCoord, yCoord, g, this, u);
        }
 
        return xCoord;
    }
}
