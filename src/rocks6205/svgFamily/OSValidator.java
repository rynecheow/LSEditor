package rocks6205.svgFamily;

/**
 * Checks the current operating system that the program is currently
 * run on.
 *
 * @author Cheow Yeong Chi
 *
 * @since 1.6
 */
public class OSValidator {
    private static String OS = System.getProperty("os.name").toLowerCase();

    private OSValidator() {}

    public static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    public static boolean isMac() {
        return (OS.indexOf("mac") >= 0);
    }
}