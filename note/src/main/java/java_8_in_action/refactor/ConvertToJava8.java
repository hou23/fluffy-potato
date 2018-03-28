package java_8_in_action.refactor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by yaojie.hou on 2017/4/13.
 *
 * 利用Lambda表达式、方法引用以及Stream改善程序代码的可读性
 */
public class ConvertToJava8 {
	public static void main(String[] args) throws IOException {

		// 匿名类
		int a = 2;
		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				int a = 2;
				System.out.println(a);
			}
		};
		// Lambda
		Runnable r2 = () -> {
			//int a = 2;
			System.out.println(a);
		};

		//
		String oneLine = processFile(BufferedReader::readLine);

		Validator numericValidator = new Validator((String s) -> s.matches("[a-z]+"));
		boolean b1 = numericValidator.validate("aaa");
		Validator lowerCaseValidator = new Validator((String s) -> s.matches("\\d+"));
		boolean b2 = lowerCaseValidator.validate("bbb");
	}

	public static String processFile(BufferedReaderProcessor p) throws IOException {

		try(BufferedReader br = new BufferedReader(new FileReader(""))) {
			return p.process(br);
		}

	}
}

interface BufferedReaderProcessor{
	String process(BufferedReader br) throws IOException;
}

