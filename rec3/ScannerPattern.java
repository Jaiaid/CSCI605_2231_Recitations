import java.util.Scanner;
import java.util.regex.Pattern;

public class ScannerPattern {
    public static void main ( String args[] ) {
        Scanner sc = new Scanner(System.in);

        // will throw exception if input mismatch
        String str = sc.next(Pattern.compile("^th*ree"));
        System.out.println(str);
    }
}