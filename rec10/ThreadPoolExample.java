import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Please note, threadpoolexecutor is possibly not part of syllabus
 * 
 * Creating thread is expensive
 * What if we have steady stream of cmd which consists of tasks
 * which can be concurrent
 * Should we create thread each time a new task arrives?
 * 
 * ThreadPoolExecutor gives a solution (https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ThreadPoolExecutor.html)
 * It maintains a pool of created thread
 * new task arrives and it allocates that to a free thread
 * by default it creates non-daemon thread which can cause following problem
 * https://stackoverflow.com/questions/20057497/program-does-not-terminate-immediately-when-all-executorservice-tasks-are-done
 * 
 * A task is modeled by run method got by implementing Runnable
 * extending Thread will also work because 
 * Thread class implements Runnable (https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html)
 */

/**
 * Task type 1
 */
class ThreadType1 extends Thread {
    ThreadType1(String name) {
        super(name);
    }

    public void run() {
        System.out.println("Hello from thread " + getName() + " of type ThreadType1");
    }
}

/**
 * Task type 2
 */
class ThreadType2 extends Thread {
    ThreadType2(String name) {
        super(name);
    }

    public void run() {
        System.out.println("Hello from thread " + getName() + " of type ThreadType2");
    }
}

/**
 * Task type 2
 */
class ThreadType3 implements Runnable {
    String name;
    ThreadType3(String name) {
        // not extending Thread so no super
        this.name = name;
    }

    public void run() {
        System.out.println("Hello from thread " + name + " of type ThreadType3");
    }
}

public class ThreadPoolExample {
    String taskgroupName;
    ArrayBlockingQueue<Runnable> taskqueue;
    ThreadPoolExecutor workersGroup;

    ThreadPoolExample(String name, int maxconcurrenttask) {
        // notice the ThreadPoolExecutor constructor
        // it takes a Queue of Runnable
        // this runnable's run method will have the task code
        // for detail of the argument list
        // https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ThreadPoolExecutor.html#ThreadPoolExecutor-int-int-long-java.util.concurrent.TimeUnit-java.util.concurrent.BlockingQueue-
        this.taskgroupName = name;
        // init taskqueue
        taskqueue = new ArrayBlockingQueue<Runnable>(maxconcurrenttask);
        // DON'T FORGET THREADPOOLEXECUTOR needs shutdown for proper closing of program
        // Check this https://stackoverflow.com/a/20057584
        workersGroup = new ThreadPoolExecutor(
            maxconcurrenttask, maxconcurrenttask,10,
            TimeUnit.MINUTES, taskqueue);
    }

    /**
     * important to shutdown threadpool
     * as default ThreadFactory of ThreadPoolExecutor creates non-daemon thread
     * therefore program will not close until they are closed
     */
    void cleanup() {
        workersGroup.shutdown();
    }

    public String getName() {
        return taskgroupName;
    }

    public ThreadPoolExecutor getWorkersGroup() {
        return workersGroup;
    }

    public static void main(String args[]) {
        // creating a thread pool to execute tasks concurrently
        // assuming maximum concurrent task is 6
        // this count is design choice
        ThreadPoolExample workers = new ThreadPoolExample("CSCI605_students_tasks", 6);

        for (int i = 0;i <6;i++) {
            if (i%3 == 0) {
                workers.getWorkersGroup().execute(new ThreadType3("t1"));
            }
            else if (i%2 == 0) {
                workers.getWorkersGroup().execute(new ThreadType2("t2"));
            }
            else {
                workers.getWorkersGroup().execute(new ThreadType1("t3"));
            }
        }
        // important to do cleanup
        workers.cleanup();
    }
}
