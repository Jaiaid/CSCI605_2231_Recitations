/**
 * To show the requirement of synchronized block
 * in calling wait, notify, notifyAll
 * 
 * The IllegalMonitorStateException is thrown when you try to call notify or wait
 * on an object for which you are not in synchronized block
 */
public class IllegalMonitorStateSimple extends Thread {
    private Object lock;
    
    public IllegalMonitorStateSimple(String name) {
        super(name);
        this.lock = new Object();
    }

    public void run() {
        // will throw a IllegalMonitorStateException
        // because we are not entering into synchronized block
        // notice although they all are trying to sync on instance object
        // the object does not need to be shared reference or class variable
        try {
            if (this.getName().equals("1")) {
                this.lock.wait();
            }
            else if (this.getName().equals("2")) {
                this.lock.notify();
            }
            else {
                this.lock.notifyAll();
            }
        }
        catch (IllegalMonitorStateException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String args[]) {
        // let's start thread 1, 2 and 3
        new IllegalMonitorStateSimple("1").start();
        new IllegalMonitorStateSimple("2").start();
        new IllegalMonitorStateSimple("3").start();
    }
}
