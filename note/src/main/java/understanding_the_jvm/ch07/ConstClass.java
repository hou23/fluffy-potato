package understanding_the_jvm.ch07;

/**
 * @author yaojie.hou
 * @create 2018/6/19
 */
public class ConstClass {

	static {
		System.out.println("ConstClass init!");
	}

	public static final String HELLOWORLD = "hello world";
}
