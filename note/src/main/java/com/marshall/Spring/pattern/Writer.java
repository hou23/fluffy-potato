package com.marshall.Spring.pattern;

import lombok.Data;

/**
 * Created by yaojie.hou on 2017/8/29.
 */
@Data
public class Writer {
	private String name;
	private String city;

	public Writer() {
	}

	public Writer(String name) {
		this.name = name;
	}

	// 工厂方法
	public static Writer createWriter(String ingredient) {
		if (ingredient.equals("china")) {
			return new Writer("cn");
		} else {
			return new Writer("foreign");
		}
	}
}
