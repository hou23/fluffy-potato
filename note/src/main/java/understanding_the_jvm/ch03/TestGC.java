package understanding_the_jvm.ch03;

/**
 * @author yaojie.hou
 * @create 2018/5/7
 */
public class TestGC {

	public static void main(String[] args) {
		//testAllocation();
		//testPretenureSizeThreshold();
		//testTenuringThreshold();
		testTenuringThreshold2();
	}

	private static final int _1MB = 1024 * 1024;

	/**
	 * 对象优先在Eden分配
	 *
	 * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
	 *
	 * -Xms20M -Xmx20M 限制Java堆大小为20M, 不可扩展
	 * -Xmn10M 设置新生代内存为10M, 剩下的10M给老年代
	 * -XX:+PrintGCDetails 打印内存回收日志
	 * -XX:SurvivorRatio=8 设置新生代Eden区与一个Survivor区的空间比例是8:1
	 */
	private static void testAllocation() {
		byte[] allocation1, allocation2, allocation3, allocation4;
		allocation1 = new byte[2 * _1MB];
		allocation2 = new byte[2 * _1MB];
		allocation3 = new byte[2 * _1MB];

		/*
		* 此时Eden已被占用6M, 剩余空间不足给allocation4分配内存, 因此发生Minor GC.
		* GC期间3个2M大小的对象无法放入Survivor空间, 通过担保机制转移到老年代.
		* 4M的allocation4对象分配在Eden空间.
		* GC结束后, Eden占用4M, Survivor空间空闲, 老年代占用6M
		*/
		allocation4 = new byte[4 * _1MB];
	}

	/**
	 * 大对象直接进入老年代
	 *
	 * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
	 * -XX:PretenureSizeThreshold=3145728
	 *
	 * -XX:PretenureSizeThreshold=3145728 令大于这个值的对象直接在老年代分配. 避免在Eden和Survivor区
	 * 之间发生大量的内存复制. 此参数只对Serial和ParNew收集器有效.
	 */
	private static void testPretenureSizeThreshold() {
		byte[] allocation;
		allocation = new byte[4 * _1MB]; // 直接分配在老年代中
	}

	/**
	 * 长期存活的对象将进入老年代
	 *
	 * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1
	 * -XX:+PrintTenuringDistribution
	 *
	 * -XX:MaxTenuringThreshold=1 设置对象晋升老年代的年龄阈值
	 */
	private static void testTenuringThreshold() {
		byte[] allocation1, allocation2, allocation3;
		allocation1 = new byte[_1MB / 4];
		allocation2 = new byte[4 * _1MB];
		allocation3 = new byte[4 * _1MB];
		allocation3 = null;
		allocation3 = new byte[4 * _1MB];
	}

	/**
	 * Survivor空间中相同年龄所有对象大小的总和大于Survivor空间的一半, 年龄大于/等于该年龄的对象直接进入老年代
	 *
	 * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15
	 * -XX:+PrintTenuringDistribution
	 */
	private static void testTenuringThreshold2() {
		byte[] allocation1, allocation2, allocation3, allocation4;
		allocation1 = new byte[_1MB / 4];
		// allocation1 + allocation2大于survivor空间一半
		allocation2 = new byte[_1MB / 4];
		allocation3 = new byte[4 * _1MB];
		allocation4 = new byte[4 * _1MB];
		allocation4 = null;
		allocation4 = new byte[4 * _1MB];
	}
}
