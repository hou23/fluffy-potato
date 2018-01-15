package com.marshall.Spring.pattern;

/**
 * Created by yaojie.hou on 2017/9/7.
 *
 * 建设者模式
 */
public class WriterBuilder {
	private Writer writer;

	public WriterBuilder writer(Writer writer) {
		this.writer = writer;
		return this;
	}

	public WriterBuilder setName(String name) {
		this.writer.setName(name);
		return this;
	}

	public WriterBuilder setCity(String city) {
		this.writer.setCity(city);
		return this;
	}

	public Writer getWriter() {
		return this.writer;
	}
}
