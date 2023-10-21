/*
 * StringExamples.java
 *
 * Version:
 *  1
 *
 * Revision:
 *  1
 */

/**
 * Utility class StringExample2 to show String object creation.
 */
public class StringExample2 {
    /**
     * program entry point main method.
     *
     * @param args
     */
    public static void main( String[] args ) {
        // creating a string object from constant
        String s1 = "abc";
        // print s1
        System.out.println(s1);
        // print s1 length
        System.out.println(s1.length());
        // replace character
        s1.replace('b', 'd');
        System.out.println(s1);

        String s2;
        s2 = s1.replace(""+s1.charAt(s1.length() - 1), ""+s1.charAt(0));
        System.out.println(s2);
        System.out.println(s1);
        System.out.println(s2);
        return;
    }
}
