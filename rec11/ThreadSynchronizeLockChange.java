public class ThreadSynchronizeLockChange extends Thread {
    static Object lock = new Object();
    
    ThreadSynchronizeLockChange(String name) {
        super(name);
        System.out.printf("changing lock %s\n", getName());
        System.out.flush();
        lock = new Object();
        System.out.printf("changing lock done %s\n", getName());
        System.out.flush();
    }

    public void run() {
        System.out.printf("trying to enter synchronized %s\n", getName());
        System.out.flush();
        synchronized( lock ) {
            System.out.printf("entered, notifying otheres %s\n", getName());
            System.out.flush();
            lock.notify();
            try {
                System.out.printf("waiting to get notification %s\n", getName());
                System.out.flush();
                lock.wait();
                System.out.printf("woke up by notification %s\n", getName());
                System.out.flush();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        // ThreadSynchronizeLockChange t1 = new ThreadSynchronizeLockChange("t1").start();
        // ThreadSynchronizeLockChange t2 = new ThreadSynchronizeLockChange("t2").start();
        new ThreadSynchronizeLockChange("t1").start();
        new ThreadSynchronizeLockChange("t2").start();

        // t1.start();
        // t2.start();
    }
}
