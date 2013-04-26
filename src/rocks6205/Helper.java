package rocks6205;

/**
 *
 * @author Cheow Yeong Chi
 */
public class Helper {
    public static void println(String msg) {
        System.out.println(msg);
    }

    public static void printErr(String msg) {
        System.err.println(msg);
    }

    public static void printf(String format, Object... args) {
        System.out.printf(format, args);
    }
}