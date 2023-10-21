import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A class implementing iterartor
 * 
 * 
 * We don't need to implement all methods of Iterator interface
 * Thanks to default method
 * https://docs.oracle.com/javase/tutorial/java/IandI/defaultmethods.html
 */
public class IteratorExample implements Iterator<String> {
    private ArrayList<String> list;
    private int counter;

    IteratorExample(String sentence) {
        this.counter = 0;
        String array[] = sentence.split(" ");
        
        this.list = new ArrayList<String>(array.length);
        for(String s: array) {
            this.list.add(s);
        } 
    }

    public boolean hasNext() {
        return counter < this.list.size();
    }

    public String next() {
        return this.list.get(counter++);
    }

    public void remove() {
        this.list.remove(counter);
    }

    public void replace(int idx, String string) {
        this.list.set(idx, string);
    }

    public static void main(String[] ara) {
        IteratorExample example =
        new IteratorExample("A brown quick fox jumps over the lazy dog");

        // notice I am call remove before calling next still not throwing IllegalStateException
        // it will throw error for another reason thoough
        // this is one reason why you should not call remove before next
        // you can implement you iterable in such way that it is controlled
        // example.remove();
        // System.out.println(example.next());
        while (example.hasNext()) {
            // example.remove();
            // example.replace(0, "hello");
            System.out.println(example.next());
        }
    }    
}