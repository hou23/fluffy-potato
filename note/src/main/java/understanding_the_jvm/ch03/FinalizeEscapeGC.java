package understanding_the_jvm.ch03;

/**
 * Created by marshall.houz on 2018/4/23.
 *
 * 此代码演示了两点:
 * 1. 对象可以在被GC时自我拯救
 * 2. 这种自救机会只有一次, 因为一个对象的finalize()方法最多只会被调用一次
 */
public class FinalizeEscapeGC {

    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive() {
        System.out.println("yes, i am still alive :)");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();

        // 对象第一次成功拯救自己
        SAVE_HOOK = null;
        System.gc();
        // 因为finalize方法优先级很低, 所以暂停0.5s等待它
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, i am dead :(");
        }

        //下面这段代码与上面完全相同, 但是这次自救失败了
        SAVE_HOOK = null;
        System.gc();
        // 因为finalize方法优先级很低, 所以暂停0.5s等待它
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, i am dead :(");
        }
    }
}
