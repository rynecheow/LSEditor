package rocks6205.system.properties;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sugar CheeSheen Chan
 */
public class Translator {
    public static final Logger   logger      =
        Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());
    public static File makeNewPropertiesFile( Locale locale ) {
        File file = new File( "build/classes/rocks6205/system/properties/" + "LSEditor_" + 
                Locale.getDefault() + ".properties" );
        
        try {
            file.createNewFile();
        }catch( Exception ex ) {
            return null;
        }
        return file;
    }
    
    public static void translate( File inputFile , File outputFile , Language language ) throws Exception {
        Translate.setClientId("RCKS_SEPT");
        Translate.setClientSecret("vtpcr7K3Xz2sqlW4BPbHiTeCEI8DWbQnztU2JbWSU1Q");
        BufferedWriter writer = new BufferedWriter(new PrintWriter( outputFile , "UTF-8" ));
        try {
            Scanner scanner = new Scanner(inputFile);
            
            while ( scanner.hasNextLine() ) {
                String line = scanner.nextLine();
                
                if(line.indexOf("=") != -1 ) {
                    String[] token = line.split( "=" );
                    String translatedText = Translate.execute( token[ 1 ].trim() , language );
                    logger.info(translatedText + "\n");
                    writer.write( token[ 0 ].trim() + " = " + translatedText + "\n"  );
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Translator.class.getName()).log(Level.SEVERE, null, ex);
        }
        writer.close();
    }
    
    public static boolean translate( String text , Language to ) {
        try{
            Translate.setClientId("RCKS_SEPT");
            Translate.setClientSecret("vtpcr7K3Xz2sqlW4BPbHiTeCEI8DWbQnztU2JbWSU1Q");

            String translatedText = Translate.execute( text , Language.ENGLISH, to );
        }
        catch(Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return false;
    }
}
