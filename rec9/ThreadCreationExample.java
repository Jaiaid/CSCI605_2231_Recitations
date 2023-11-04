/**
 * Following class gives example of creating Thread Object
 * We can do it through two way
 * 1. extending Thread object
 * 2. Implement Runnable interface and 
 * create Thread object from that Runnable
 * 
 * Thread object itself implements Runnable
 */
public class ThreadCreationExample extends Thread {
    // an id number to identify the thread
    int id = 0;
    /**
     * Constructor
     * 
     * @param id to init id field
     */
    ThreadCreationExample(int id) {
        // Thread(String name) constructor
        // initiate name field of Thread object
        // https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html
        super(String.valueOf(id));
        this.id = id;
    }

    /**
     * Overridden run method inherited from Thread
     * 
     * run method contains the code which will be executed in that thread
     * here we will run a loop for 20 iteration 
     * at each iteration the thread name with iteration number will be printed
     * 
     * Finally, at the finishing an execution completed message will be printed 
     */
    public void run() {
        for (int i = 0;i < 20;i++)
        {
            // getName() is inherited from Thread
            System.out.println("Thread named " + getName() + " at iteration:" + i);
        }
        System.out.println("Thread named " + getName() + " execution completed");
    }
    /**
     * main method
     * 
     * @param args cmd line arguments
     */
    public static void main(String args[]) {
        // createing two thread  object
        ThreadCreationExample t1 = new ThreadCreationExample(45);
        ThreadCreationExample t2 = new ThreadCreationExample(78);

        // we start a thread object by calling it's start method
        // start method is inherited by extending thread
        // by calling this method t1 and t2 are moved to Runnable state
        // check https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/java/lang/Thread.State.html
        t1.start();
        t2.start();

        // main function is executed in main thread
        // as nothing more to do main function will exit and this main thread
        // this is independent of the threads (object t1 and t2) it has started
        System.out.println("main thread is finishing");
    }
}