import java.util.ArrayList;
import java.util.List;

/**
 * Think Stream as a feature to declare (Note, not define)
 * a set of operation on a collection of data
 * 
 * These operations can be parallely executed/sequential
 * If you have done SQL then think this
 * SQL syntax is roughly
 * 
 * select -> filter based on condition -> sort maybe -> present a part of it
 * DISCLAIMER: this is not exact SQL but I am trying to draw some connection 
 * to help reader in imagining the concept
 * 
 * In this example we will do processing on a dataset of Personal info and try
 * to implement this SQL query like processing
 * 
 * SELECT id, name
 * FROM PersonList
 * WHERE salary < 10000
 */
public class StreamJavaExample {
    /**
     * data instance, think of it like it will be a row in the table 
     * 
     * static, as we will use it to create new instance only from a 
     * static method
     */
    static class Person {
        public int id;
        public String name;
        public int salary;

        public Person(int id, String name, int salary) {
            this.id = id;
            this.name = name;
            this.salary = salary;
        }
    }
    static ArrayList<Person> PersonList;

    public static void main(String args[]) {
        // first lets create a collection
        PersonList = new ArrayList<>();
        PersonList.add(new Person(0, "null", 5000));
        PersonList.add(new Person(1, "null", 15000));
        PersonList.add(new Person(2, "null", 1000));
        PersonList.add(new Person(3, "null", 2000));
        PersonList.add(new Person(4, "null", 25000));
        PersonList.add(new Person(5, "null", 11000));

        // get the stream first
        List<Integer> result = PersonList.stream()
        // first apply filtering or WHERE <condition>
        // how to provide the condition functionility
        // lambda expression comes in help here
        // to be precise you need to provide Predicate functional interface
        // https://docs.oracle.com/javase/8/docs/api/java/util/function/Predicate.html
        // Predicate provides following logic methods
        // - and
        // - isEqual (check the doc how equality is checked)
        // - negate
        // - or
        // - test
        // apart from test all are default implementation provided
        // you will be providing test method's implementation through lambda
        .filter((p)-> p.salary < 10000)
        // map can be used to apply custom operations
        .map((p) -> { System.out.println(p.id);return p.id;})
        // now to collect the processed data
        //
        // =====================================================================
        // ALSO VERY IMPORTANT, CALLING COLLECT WILL ACTUALLY START THE PIPELINE
        // TRY TO DEFINE PIPELINE WITHOUT COLLECTING METHOD YOU WILL NOT HAVE EXECUTION
        // =====================================================================
        //
        // what is meant by collect
        // => think of it as cmd that start execute the pipeline,
        // summarizing the processed data, maybe apply some aggregating operation
        // what aggregating operations are available
        // https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html
        // what is this class?
        // => this generic class contains many static aggregating operation, 
        // we have to call one
        // here we are just converting it to a list
        .toList();
        System.out.println(result);
    }
}
