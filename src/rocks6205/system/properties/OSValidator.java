package rocks6205.system.properties;

/**
 * Checks the current operating system that the program is currently
 * run on.
 *
 * @author Cheow Yeong Chi
 *
 * @since 1.6
 */
public class OSValidator {

    /**
     * Operating System Name
     */
    private static String OS = System.getProperty("os.name").toLowerCase();

    private OSValidator() {}

    /**
     * @return if the current operating system is Windows
     */
    public static boolean isWindows() {
        System.out.println(OS);

        return (OS.indexOf("win") >= 0);
    }

    /**
     * @return if the current operating system is Mac
     */
    public static boolean isMac() {
        System.out.println(OS);

        return (OS.indexOf("mac") >= 0);
    }
}