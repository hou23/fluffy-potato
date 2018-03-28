package java_concurrency_in_practice.ch03;

/**
 * Created by yaojie.hou on 2018/3/7.
 *
 * 允许内部可变的数据逸出(错误)
 */
public class UnsafeStates {

	private String[] states = new String[]{
			"AK", "AL" /*...*/
	};

	public String[] getStates() {
		return states;
	}

}
