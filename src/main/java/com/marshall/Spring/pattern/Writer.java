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
	public static Writer createWriter(String name) {
		return new Writer(name);
	}

	/* 建设者模式
	   writer类的构造器，提供链接方法的调用*/
	static class WriterBuilder {
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
}
