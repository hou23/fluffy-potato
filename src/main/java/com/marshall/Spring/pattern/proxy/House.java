package com.marshall.Spring.pattern.proxy;

/**
 * Created by yaojie.hou on 2017/9/7.
 */
public class House implements Construction{

	private boolean permitted;

	@Override
	public void construct() {
		System.out.println("I'm constructing a house");
	}

	@Override
	public void givePermission() {
		System.out.println("Permission is given to construct a simple house");
		this.permitted = true;
	}

	@Override
	public boolean isPermitted() {
		return this.permitted;
	}
}
