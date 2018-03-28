package java_concurrency_in_practice.ch03;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yaojie.hou on 2018/3/7.
 *
 * 发布对象, 将对象的引用存储到公共静态域中(正确)
 */
public class Secrets {

	public static Set<Secret> knownSecrets;

	public void initialize() {
		knownSecrets = new HashSet<Secret>();
	}

}

class Secret {
}