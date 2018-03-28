package art_concurrent_book.base;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * Created by yaojie.hou on 2017/5/27.
 *
 * Java程序天生就是多线程程序，执行main()方法的是一个名为main的线程
 * 使用JMX查看一个普通的Java程序包含哪些线程
 */
public class MultiThread {
	public static void main(String[] args) {
		//获取Java线程管理MXBean
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		//不需要获取同步的monitor和synchronizer信息，仅获取线程和线程堆栈信息
		ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
		//遍历线程信息，仅打印线程ID和线程名称信息
		for (ThreadInfo threadInfo : threadInfos) {
			System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
			 /*
			 [6] Monitor Ctrl-Break
			 [5] Attach Listener
			 [4] Signal Dispatcher		分发处理发送给JVM信号的线程
			 [3] Finalizer				调用对象finalize方法的线程
			 [2] Reference Handler		清除Reference的线程
			 [1] main					用户程序入口
			 */
		}
	}
}
