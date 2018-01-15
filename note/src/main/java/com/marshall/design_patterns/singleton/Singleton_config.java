package com.marshall.design_patterns.singleton;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by yaojie.hou on 2017/12/7.
 */
@ConfigurationProperties(prefix = "singleton")
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
