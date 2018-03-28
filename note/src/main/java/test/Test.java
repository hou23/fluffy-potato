package test;

/**
 * Created by yaojie.hou on 2018/3/14.
 */
public class Test {

	public static void main(String[] args) {
		boolean w = false;
		boolean d = false;
		boolean i = d || (w = true);
		System.out.println(w);
	}
}
