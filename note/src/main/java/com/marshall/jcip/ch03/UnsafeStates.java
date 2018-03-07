package com.marshall.jcip.ch03;

/**
 * Created by yaojie.hou on 2018/3/7.
 */
public class UnsafeStates {

	private String[] states = new String[]{
			"AK", "AL" /*...*/
	};

	public String[] getStates() {
		return states;
	}

}
