/*
 * HelloWorld.java
 *
 * Version:
 *  1
 *
 * Revisions:
 *
 */

/**
 * HelloWorld.java.
 * It contains a utility class containing only main method
 * Example code to show output
 *
 * @author jm5071
 */

public class DisplayNumber {
    /**
     * main method.
     *
     * @param args cmd line arguments (ignored)
     */
    public static void main( String[] args ) {
        int x = 0;
        // we can use System.out.println
        // java will autoconvert number to string
        System.out.println( x * 2 );
        // for better control of format string
        // System.out.printf is useful
        System.out.printf( "%d", x );
        // add newline
        System.out.println(  );
        // for number printing with space
        // 4 space example, right justified
        System.out.printf( "%4d %4d %4d %4d %4d\n", 10000, 1000, 100, 10, x);
        // left justified
        System.out.printf( "%-4d %-4d %-4d %-4d\n", 1000, 100, 10, x);
        return;
    }
}
