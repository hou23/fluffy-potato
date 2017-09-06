package com.marshall.Spring.pattern;

/**
 * Created by yaojie.hou on 2017/9/6.
 *
 * 演示建设者模式
 */
public class Builder {
	public static void main(String[] args) {
		// 建设者模式
		Writer.WriterBuilder builder = new Writer.WriterBuilder();
		Writer writer = builder.writer(new Writer()).setName("L").setCity("Detroit").getWriter();
		System.out.println(writer);

		// 工厂模式
		System.out.println(Writer.createWriter("L"));
	}
}
