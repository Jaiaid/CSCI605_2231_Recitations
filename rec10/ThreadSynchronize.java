public class ThreadSynchronize extends Thread {
    // an id number to identify the thread
    int id = 0;
    static String lockObject = new String();
    /**
     * Constructor
     * 
     * @param id to init id field
     */
    ThreadSynchronize(String name, int id) {
        super(name);
        this.id = id;
    }

    /**
     * Although we have made the run method synchronized it does not mean
     * only one thread will run at a time
     * 
     * Because this synchronization is based on locking the thread objetct instance
     * like following
     * synchronized (this) {}
     * 
     * "this" will be different for all individual threads
     * For further reading, https://docs.oracle.com/javase/tutorial/essential/concurrency/locksync.html 
     */
    public synchronized void run() {
        for (int i = 0;i < 20;i++)
        {
            // getName() is inherited from Thread
            System.out.println("Thread named " + getName() + " at iteration:" + i);
        }
        System.out.println("Thread named " + getName() + " execution completed");
    }

    /**
     * This is static method therefore it will be synchronized based on
     * locking Class object which is same for all caller
     * like following
     * synchronized (ThreadSynchronize) {}
     * 
     * Therefore, only one thread can execute this method at a time
     */
    public static synchronized void syncMethod() {
        for (int i = 0;i < 20;i++)
        {
            // can not print thread name as that is instance attribute
            System.out.println(i);
        }
    }
    /**
     * main method
     * 
     * @param args cmd line arguments
     */
    public static void main(String args[]) {
        // creating two thread object
        ThreadSynchronize t1 = new ThreadSynchronize("t1", 45);
        ThreadSynchronize t2 = new ThreadSynchronize("t2", 78);

        // start them
        t1.start();
        t2.start();

        System.out.println("main thread is finishing");
    }    
}
