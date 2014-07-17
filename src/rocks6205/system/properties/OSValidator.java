package rocks6205.system.properties;

/**
 * Checks the current operating system that the program is currently
 * run on.
 *
 * @author Cheow Yeong Chi
 * @since 1.6
 */
public class OSValidator {

    /**
     * Operating System Name
     */
    private static String OS = System.getProperty("os.name");

    private OSValidator() {
    }

    /**
     * @return if the current operating system is Windows
     */
    public static boolean isWindows() {
        return (OS.toLowerCase().contains("win"));
    }

    /**
     * @return if the current operating system is Mac
     */
    public static boolean isMac() {
        return (OS.toLowerCase().contains("mac"));
    }

    public static String getOS() {
        return OS;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
