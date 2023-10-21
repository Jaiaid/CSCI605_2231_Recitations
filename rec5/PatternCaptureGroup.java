import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PatternCaptureGroup {
    public static void main( String args[] ) {
        Pattern p = Pattern.compile("\\d(?:1t1|1+1)");
        Matcher matcher = p.matcher("21t1");
        if (matcher.matches()) {
            System.out.println(matcher.group(1));
            System.out.printf("matched substring from index %d-%d\n", matcher.start(1), matcher.end(1));
        }
    }
}