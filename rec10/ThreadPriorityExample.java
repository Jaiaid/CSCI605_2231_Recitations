/**
 * Experiment with Thread.setPriority instance method
 * In my system I did not see much difference
 * 
 * check the given link commented before setPriority call
 */
public class ThreadPriorityExample extends Thread {
    // an id number to identify the thread
    int id = 0;
    /**
     * Constructor
     * 
     * @param id to init id field
     */
    ThreadPriorityExample(String name, int id) {
        super(name);
        this.id = id;
    }

    /**
     * Overridden run method inherited from Thread
     * 
     * run method contains the code which will be executed in that thread
     * here we will run a loop for 1000 iteration 
     * at each iteration the thread name with iteration number will be printed
     * 
     * Finally, at the finishing an execution completed message will be printed 
     */
    public void run() {
        for (int i = 0;i < 1000;i++)
        {
            System.out.println("Thread " + getName() + ":" + i);
        }
        System.out.println("Thread " + getName() + " execution completed");
    }
    /**
     * main method
     * 
     * @param args cmd line arguments
     */
    public static void main(String args[]) {
        // creating two thread object
        ThreadPriorityExample t1 = new ThreadPriorityExample("t1", 45);
        ThreadPriorityExample t2 = new ThreadPriorityExample("t2", 78);

        // print to check what are the priority value range
        System.out.printf("Max min priority are %d %d\n", MIN_PRIORITY, MAX_PRIORITY);
        
        // what will be the effect for this large variation in priority
        // interestingly in my system there is not observable change, check following
        // https://stackoverflow.com/questions/12038592/java-thread-priority-has-no-effect
        t1.setPriority(MAX_PRIORITY);
        t2.setPriority(MIN_PRIORITY);
        // start them
        t1.start();
        t2.start();

        // join throws a checked exception called InterruptedException
        try{
            t1.join();
            System.out.println("Joined with " + t1.getName());
            t2.join();
        }
        catch(InterruptedException e) {
            System.out.println(e);
        }

        System.out.println("main thread is finishing");
    }
}
