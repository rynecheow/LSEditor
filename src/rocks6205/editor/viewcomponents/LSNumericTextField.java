package rocks6205.editor.viewcomponents;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.core.LSEditor;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

//~--- JDK imports ------------------------------------------------------------

/**
 * Text field that accepts only integer value.
 *
 * @author Cheow Yeong Chi
 * @since 2.5
 */
public class LSNumericTextField extends JTextField {
    public LSNumericTextField() {
        super();
    }

    /**
     * {@inheritDoc}
     *
     * @return new instance of <code>LSNumericDocument</code>.
     */
    @Override
    protected Document createDefaultModel() {
        return new LSNumericDocument();
    }

    private class LSNumericDocument extends PlainDocument {
        @SuppressWarnings("ResultOfMethodCallIgnored")
        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            if (str == null) {
                return;
            }

            char[] chars = str.toCharArray();
            boolean isInteger = true;

            for (char aChar : chars) {
                try {
                    Integer.parseInt(String.valueOf(aChar));
                } catch (NumberFormatException exc) {
                    isInteger = false;
                    LSEditor.logger.warning(exc.getMessage());

                    break;
                }
            }

            if (isInteger) {
                super.insertString(offs, new String(chars), a);
            }
        }
    }
}