public class ThreadWaitNotifyExample extends Thread {
    private Object lock;

    ThreadWaitNotifyExample(String name, Object lock) {
        super(name);
        this.lock = lock;
    }

    public void run() {
        synchronized(this.lock) {
            System.out.printf(
                "Thread %s got hold of synchronized codeblock\n", getName());
            System.out.printf(
                "Now Thread %s will notify others\n", getName());
            this.lock.notify();
            
            System.out.printf(
                "Now Thread %s will wait to be waked up by others\n", getName());
            try {
                this.lock.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            System.out.printf(
                "Thread %s has completed waiting now will exit\n", getName());
        }
    } 

    public static void main(String args[]) {
        String theStringObjectWhichWillLock = "Lock";
        ThreadWaitNotifyExample t1 = new ThreadWaitNotifyExample(
            "t1", theStringObjectWhichWillLock);
        ThreadWaitNotifyExample t2 = new ThreadWaitNotifyExample(
            "t2", theStringObjectWhichWillLock);
        ThreadWaitNotifyExample t3 = new ThreadWaitNotifyExample(
            "t3", theStringObjectWhichWillLock);
        
        t1.start();
        t2.start();
        t3.start();
    }
}
