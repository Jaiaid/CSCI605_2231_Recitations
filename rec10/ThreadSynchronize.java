import java.util.Vector;

public class ThreadSynchronize extends Thread {
    // an id number to identify the thread
    int id = 0;
    static String lockObject = new String();
    /**
     * Constructor
     * 
     * @param id to init id field
     */
    ThreadSynchronize(int id) {
        this.id = id;
    }

    public synchronized void run() {
        for (int i = 0;i < 20;i++)
        {
            System.out.println(i);
        }
    }

    public static synchronized void syncMethod() {
        // System.out.println("Thread " + id + " execution completed");
    }
    /**
     * main method
     * 
     * @param args cmd line arguments
     */
    public static void main(String args[]) {
        // creating two thread object
        ThreadSynchronize t1 = new ThreadSynchronize(45);
        ThreadSynchronize t2 = new ThreadSynchronize(78);

        // start them
        t1.start();
        t2.start();

        System.out.println("main thread is finishing");
    }    
}
