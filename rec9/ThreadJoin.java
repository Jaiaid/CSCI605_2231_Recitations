/**
 * Following class gives example of how we can wait 
 * until some threads are finished
 * 
 * We can do it through calling join on that thread
 * The thread creation codes and thread codes are 
 * similar to ThreadCreationExample.java
 */
public class ThreadJoin extends Thread {
    // an id number to identify the thread
    int id = 0;
    /**
     * Constructor
     * 
     * @param id to init id field
     */
    ThreadJoin(int id) {
        this.id = id;
    }

    /**
     * Overridden run method inherited from Thread
     * 
     * run method contains the code which will be executed in that thread
     * here we will run a loop for 200 iteration 
     * at each iteration the thread id with iteration number will be printed
     * 
     * Finally, at the finishing an execution completed message will be printed 
     */
    public void run() {
        for (int i = 0;i < 200;i++)
        {
            System.out.println("Thread " + this.id + ":" + i);
        }
        System.out.println("Thread " + id + " execution completed");
    }
    /**
     * main method
     * 
     * @param args cmd line arguments
     */
    public static void main(String args[]) {
        // creating two thread object
        ThreadJoin t1 = new ThreadJoin(45);
        ThreadJoin t2 = new ThreadJoin(78);

        // start them
        t1.start();
        t2.start();

        // join throws a checked exception called InterruptedException
        try{
            // https://docs.oracle.com/javase/tutorial/essential/concurrency/join.html
            // we are calling t1's join method from main thread
            // this means main thread will not start executing until t1 is finished
            // this is guaranteed unless error/exception
            t1.join();
            // following line won't be executed until t1 is done
            System.out.println("joined with t1");
            // notice we are not joining with t2
            // that does not mean main thread will always finish before t2
            // it still may finish after t2 but it is unpredictable (JVM, OS scheduler wish)
        }
        catch(InterruptedException e) {
            System.out.println(e);
        }

        System.out.println("main thread is finishing");
    }
}
