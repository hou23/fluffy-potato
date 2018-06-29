package understanding_the_jvm.ch07;

/**
 * @author yaojie.hou
 * @create 2018/6/19
 *
 * 演示引用类不会触发目标类的初始化, 称为被动引用
 */
public class NotInitialization {

	public static void main(String[] args) {

		// 对于静态字段, 只有直接定义这个字段的类才会被初始化
		//System.out.println(SubClass.value);

		// 通过数组定义来引用类, 不会触发类的初始化
		//SuperClass[] sca = new SuperClass[10];

		// 常量在编译阶段会存入调用类的常量池中, 本质上没有直接引用到定义常量的类
		System.out.println(ConstClass.HELLOWORLD);
	}
}
