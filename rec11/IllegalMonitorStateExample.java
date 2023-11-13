public class IllegalMonitorStateExample extends Thread {
    static Object lock = new Object();
    
    IllegalMonitorStateExample(String name) {
        super(name);
        // System.out.printf("changing lock %s\n", getName());
        // System.out.flush();
        lock = new Object();
        // System.out.printf("changing lock done %s\n", getName());
        // System.out.flush();
    }

    public void run() {
        // System.out.printf("trying to enter synchronized %s\n", getName());
        // System.out.flush();
        if (getName().equals("t1")) {
            try {
                // adding this sleep 
                // to increse the likelihood that
                sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        synchronized( lock ) {
            // System.out.printf("entered, notifying otheres %s\n", getName());
            // System.out.flush();
            
            lock.notify();
            try {
                if (getName().equals("t2")) {
                    try {
                        // adding this sleep 
                        // to increse the likelihood that
                        sleep(200);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                // System.out.printf("waiting to get notification %s\n", getName());
                // System.out.flush();
                lock.wait();
                // System.out.printf("woke up by notification %s\n", getName());
                // System.out.flush();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("exception thrown in " + getName());
            }
        }
    }

    public static void main(String args[]) {
        // ThreadSynchronizeLockChange t1 = new ThreadSynchronizeLockChange("t1").start();
        // ThreadSynchronizeLockChange t2 = new ThreadSynchronizeLockChange("t2").start();
        new IllegalMonitorStateExample("t1").start();
        try {
            sleep(200);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        new IllegalMonitorStateExample("t2").start();

        // t1.start();
        // t2.start();
    }
}
