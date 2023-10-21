import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PatternExample {
    public static void main (String args[]) {
        // first we compile a pattern to which we will match
        // that is our pattern object
        Pattern p = Pattern.compile("^th*ree$");
        // now we get a matcher object from the pattern object
        // we give the String for which we will cehck match
        Matcher m = p.matcher("three");
        // now invoke matches method to get the match result
        System.out.println(m.matches());
        // we can do it differently by invoking static method of Pattern
        System.out.println(Pattern.matches("^th*ree$", "three "));
    }
}