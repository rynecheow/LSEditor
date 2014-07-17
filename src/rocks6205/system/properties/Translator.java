package rocks6205.system.properties;

//~--- non-JDK imports --------------------------------------------------------

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;
import org.apache.commons.lang3.StringEscapeUtils;
import rocks6205.editor.core.LSEditor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Logger;

//~--- JDK imports ------------------------------------------------------------

/**
 * @author Sugar CheeSheen Chan
 */
public class Translator {
    public static final Logger logger = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());

    public static void translate(File inputFile, File outputFile, Language language) throws Exception {
        Translate.setClientId("RCKS_SEPT");
        Translate.setClientSecret("vtpcr7K3Xz2sqlW4BPbHiTeCEI8DWbQnztU2JbWSU1Q");

        try (
                BufferedWriter writer = new BufferedWriter(new PrintWriter(outputFile, "UTF-8"))) {
            try {
                LSEditor.logger.warning(inputFile.getAbsolutePath());
                Scanner scanner = new Scanner(inputFile);

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();

                    if (line.contains("=")) {
                        String[] token = line.split("=");
                        LSEditor.logger.warning(token[1].trim());
                        String translatedText = Translate.execute(token[1].trim(), language);
                        translatedText = StringEscapeUtils.escapeJava(translatedText);
                        LSEditor.logger.info(String.format("%s \n", translatedText));

                        writer.write(token[0].trim() + " = " + translatedText + "\n");
                    }
                }
            } catch (Exception ex) {
                LSEditor.logger.warning(ex.toString());
            }
        }
    }

    public static boolean translate(String text, Language to) {
        try {
            Translate.setClientId("RCKS_SEPT");
            Translate.setClientSecret("vtpcr7K3Xz2sqlW4BPbHiTeCEI8DWbQnztU2JbWSU1Q");
            Translate.execute(text, Language.ENGLISH, to);
        } catch (Exception e) {
            LSEditor.logger.warning(e.getLocalizedMessage());
        }

        return false;
    }

}