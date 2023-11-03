import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class ThreadType1 extends Thread {
    ThreadType1(ThreadGroup group, String name) {
        super(group, name);
    }

    public void run() {
        System.out.println("Hello from thread " + getName() + " of type ThreadType1");
    }
}

class ThreadType2 extends Thread {
    ThreadType2(ThreadGroup group, String name) {
        super(group, name);
    }

    public void run() {
        System.out.println("Hello from thread " + getName() + " of type ThreadType2");
    }
}

class ThreadType3 extends Thread {
    ThreadType3(ThreadGroup group, String name) {
        super(group, name);
    }

    public void run() {
        System.out.println("Hello from thread " + getName() + " of type ThreadType3");
    }
}

public class ThreadPoolExample {
    ThreadPoolExecutor workersGroup;

    ThreadPoolExample(String name, int count) {
        workersGroup = new ThreadPoolExecutor(count, count, 10, TimeUnit.MINUTES, );
    }

    public ThreadGroup getWorkersGroup() {
        return workersGroup;
    }

    public void startWork() {
        for(int i = 0;i < threadCollection.length; i++) {
            threadCollection[i].start();
        }
    }

    boolean addThread(Thread threadObject) {
        if (workerCount == threadCollection.length) {
            return false;
        } 
        threadCollection[workerCount] = threadObject;
        workerCount++;
        return true;
    }

    public static void main(String args[]) {
        ThreadGroupExample workers = new ThreadGroupExample("CSCI605_students", 6);

        for (int i = 0;i <6;i++) {
            if (i%3 == 0) {
                workers.addThread(new ThreadType3(workers.getWorkersGroup(), String.valueOf(i)));
            }
            else if (i%2 == 0) {
                workers.addThread(new ThreadType2(workers.getWorkersGroup(), String.valueOf(i)));
            }
            else {
                workers.addThread(new ThreadType1(workers.getWorkersGroup(), String.valueOf(i)));
            }
        }

        workers.startWork();
        workers.getWorkersGroup().list();
    }
}
