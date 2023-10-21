/**
 * FileReadingExample.java
 * 
 * @version 1
 */

import java.util.Scanner;
import java.io.*;

public class FileReadingExample {
    static Scanner sc;
    
    public static void main ( String args[] ) {
        // as we do not know what will be the input source
        // we are not defining Scanner in declaration
        // let's read the file name from cmd line
        if (args.length > 0) {
            System.out.println("Got this file to read " + args[1]);
            try {
                sc = new Scanner(new File(args[0]));
            }
            catch ( Exception e ) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }
        else {
            sc = new Scanner(System.in);
        }
        // if we have come upto this point 
        // it means inputstream open is successful
        while ( sc.hasNext() ) {
            System.out.println( sc.nextLine() );
        }
        // close the scanner
        sc.close();

        return;
    }
}
