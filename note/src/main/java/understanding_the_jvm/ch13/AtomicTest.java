package understanding_the_jvm.ch13;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by marshall.houz on 2018/4/17.
 *
 * 演示使用CAS操作来避免阻塞同步
 */
public class AtomicTest {

    public static final AtomicInteger race = new AtomicInteger(0);

    public static void increase() {
        race.incrementAndGet();
    }

    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_COUNT];

        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    increase();
                }
            });
            threads[i].start();
        }

        while (Thread.activeCount() > 1) {
            Thread.yield();
        }

        System.out.println(race);
    }
}
