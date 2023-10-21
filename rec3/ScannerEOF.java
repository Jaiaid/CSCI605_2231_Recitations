import java.util.Scanner;

public class ScannerEOF {
    public static void main ( String args[] ) {
        Scanner sc = new Scanner(System.in);

        // will throw exception if input mismatch
        if (sc.hasNext()) {
            String str = sc.nextLine();
            System.out.println(str);
        }
        System.out.println(sc.nextLine());

        sc.close();
    }
}