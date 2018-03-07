package com.marshall.design_patterns.singleton;

/**
 * Created by yaojie.hou on 2017/12/7.
 */
public enum Singleton_config {

	CONFIG;

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		System.out.println("set url");
		this.url = url;
	}
}
