/**
 * ScannerExample.java
 * 
 * @version 1
 */

import java.util.Scanner;

/**
 * utility class containing main program to show
 * Scanner class usage
 * How to give EoF on terminal
 * 
 * @author JM
 */
public class ScannerExample {
    public static void main ( String[] args ) {
        // defining a scanner object to read from terminal
        Scanner sc = new Scanner(System.in);

        // this while loop will wait until EoF is got
        // scanner is line buffered 
        // so it will hold on returning until enter is pressed 
        // while ( sc.hasNext() ) {
        //     System.out.println(
        //         "There are token in stream and enter is pressed");
        //     // read the whole line
        //     // nextLine is line buffered 
        //     // so until you donot press newline it will not return
        //     String line = sc.nextLine();
        //     System.out.println(line);
        // }
        // System.out.println("got EoF, exiting nextLine loop");
        // sc.close();

        // this while loop will wait until EoF is got
        // scanner is line buffered 
        // so it will hold on returning until enter is pressed 
        while ( sc.hasNext() ) {
            System.out.println(
                "There are token in stream and enter is pressed");
            // read the next integer
            // nextInt is line buffered 
            // so until you donot press newline it will not return
            // you can provide radix of the integer also
            int num = sc.nextInt();
            System.out.println(num);
        }
        System.out.println("got EoF, exiting nextInt loop");

        // closing the sc
        sc.close();
    }
}