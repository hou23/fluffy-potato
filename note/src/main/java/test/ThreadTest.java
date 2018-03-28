package test;

import java.util.concurrent.TimeUnit;

/**
 * Created by marshall.houz on 2017/2/19.
 *
 * This class is for learning thread,
 * the relevant data on the website
 * http://ifeve.com/java-concurrency-thread-directory/
 */
public class ThreadTest {

    /**
     * 4.how to create and run java threads
     * open 10 threads, print their names
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            new Thread("" + i) {
                public void run() {
                    System.out.println("Thread: " + getName() + "running");
                }
            }.start();
        }
    }
}
