package test.thread;

/**
 * Created by yaojie.hou on 2017/2/27.
 * Java同步块
 * 实例方法同步，同步在拥有该方法的实例对象上，当拥有多个实例时，同步不影响。
 * 静态方法同步，同步在拥有该方法的类上，当多个实例，同步锁会生效。
 * 方法中的同步块，主要看synchronized（）中的参数是本类还是别的类
 */
public class InstanceMethodSynchronized {
    //实例方法同步
    /*int count = 0;

    public synchronized void add(int value) {
        this.count += value;
        System.out.println(count);
    }*/

    //静态方法同步
    static int count = 0;

    public static synchronized void add(int value) {
        count += value;
        System.out.println(count);
    }

}

class MyRunnable implements Runnable {

    InstanceMethodSynchronized ims = null;

    public MyRunnable(InstanceMethodSynchronized ims) {
        this.ims = ims;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            ims.add(1);
        }
    }
}

class test{
    public static void main(String[] args) {
        //实例方法同步
        /*InstanceMethodSynchronized ims1 = new InstanceMethodSynchronized();
        Thread thread1 = new Thread(new MyRunnable(ims1));
        Thread thread2 = new Thread(new MyRunnable(ims1));
        thread1.start();
        thread2.start();*/

        //静态方法同步
        InstanceMethodSynchronized ims1 = new InstanceMethodSynchronized();
        InstanceMethodSynchronized ims2 = new InstanceMethodSynchronized();
        Thread thread1 = new Thread(new MyRunnable(ims1));
        Thread thread2 = new Thread(new MyRunnable(ims2));
        thread1.start();
        thread2.start();
    }
}
