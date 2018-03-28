package java_concurrency_in_practice.ch03;

import net.jcip.annotations.Immutable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

/**
 * 构造于底层可变对象上的不可变类
 * @author yaojie.hou
 * @date 2018/3/14
 */
@Immutable
public final class ThreeStooges {

	/**
	 * 除了构造器外没有提供修改变量值的方法, 包括setter方法
	 */
	private final Set<String> stooges = new HashSet<String>();

	ThreeStooges() {
		stooges.add("Moe");
		stooges.add("Larry");
		stooges.add("Curly");
	}

	public boolean isStooge(String name) {
		return stooges.contains(name);
	}

	public String getStoogeNames() {
		List<String> stooges = new Vector<String>();
		stooges.add("Moe");
		stooges.add("Larry");
		stooges.add("Curly");
		return stooges.toString();
	}

}

class Test {
	public static void main(String[] args) {
		ThreeStooges threeStooges = new ThreeStooges();
	}
}
