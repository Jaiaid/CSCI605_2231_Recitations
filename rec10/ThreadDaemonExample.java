import java.io.FileWriter;
import java.io.IOException;

/**
 * This class defines a daemon thread object
 * Also contains the main method
 */
public class ThreadDaemonExample extends Thread {
    ThreadDaemonExample (String threadName) {
        super(threadName);
    }

    /**
     * This thread execution code  write
     * 1-9(inculsive) over at least 10s
     *
     * What is the purpose of daemon thread?
     * =>maybe some low priority observation which you do not really care about
     *   you want JVM not to wait for them
     */
    public void run() {
        
        try {
            for (int i = 0;i < 10;i++) {
                System.out.println("Thread " + getName() + ": alive, writing to stdout");
                System.out.flush();
                sleep(1000);
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * main method of the program
     * based on cmd line argument it either spawn a daemon or non-daemon thread
     * 
     * java ThreadDaemon [-d|-nd]
     * -d means daemon thread
     * -nd means non daemon thread
     * 
     * @param args array of cmd line argument
     */
    public static void main(String args[]) {
        // create the thread object
        // we are not starting it
        ThreadDaemonExample threadObject = new ThreadDaemonExample("daemon thread");
        // make it daemon based on passed cmd line arguments
        // we have to do it before starting it
        // if else block can be avoided as we are passing true or false
        if (args.length > 0 && args[0].equals("-d")) {
            threadObject.setDaemon(true);
        }
        else {
            threadObject.setDaemon(false);
        }

        // start the thread
        threadObject.start();

        /*
         * Let's wait before exiting
         * 
         * If thread is daemon then with main thread exiting JVM will exit
         * That will cause daemon thread exit and no output will show up
         */
        try{
            // approximately at least 5s wait
            sleep(5000);
        }
        catch (InterruptedException e) {
            
        }
        System.out.println("we may have started the daemon based on what cmd line you have passed");
        System.out.println("main thread will exit now\n");
        System.out.println("if the other thread is non daeamon JVM will continue");
        System.out.println("we will see the program is not closed for at least 10 more seconds");
        
        System.out.println("If it is daemon we will see the JVM is closed");
        System.out.println("and the daemon thread exits without showing all output");
    }
}
