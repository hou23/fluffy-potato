package com.marshall.jcip.ch03;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yaojie.hou on 2018/3/7.
 */
public class Secrets {

	public static Set<Secret> knownSecrets;

	public void initialize() {
		knownSecrets = new HashSet<Secret>();
	}

}

class Secret {
}