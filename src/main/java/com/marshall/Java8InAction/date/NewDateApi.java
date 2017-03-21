package com.marshall.Java8InAction.date;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

/**
 * Created by yaojie.hou on 2017/3/21.
 *
 * 新的日期和时间api
 */
public class NewDateApi {
	public static void main(String[] args) {
		LocalDate date = LocalDate.of(2017, 03, 21);
		System.out.println(date.getMonth());

		System.out.println(LocalDate.now());

		// ChronoField枚举类读取LocalDate的值
		int year = date.get(ChronoField.YEAR);
		int month = date.get(ChronoField.MONTH_OF_YEAR);
		int day = date.get(ChronoField.DAY_OF_MONTH);
		System.out.println(year + "-" + month + "-" + day);

		LocalTime time = LocalTime.of(11, 25, 23);
		System.out.println(time);

		// 解体时间字符串
		LocalDate parseDate = LocalDate.parse("2017-03-21");
		LocalTime parseTime = LocalTime.parse("11:37:03");
		System.out.println(parseDate + " " + parseTime);

		// 合并日期时间
		LocalDateTime dateTime = LocalDateTime.of(2017, Month.MARCH, 21, 11, 58, 38);
		System.out.println(dateTime);
		System.out.println(LocalDateTime.of(date, time));

		System.out.println(Instant.ofEpochSecond(4, 1000000000));

		LocalTime time1 = LocalTime.of(11, 25, 23);
		LocalTime time2 = LocalTime.of(12, 25, 23);
		System.out.println(Duration.between(time1, time2).getSeconds());

		// 修改LocalDate的属性
		System.out.println(date.withYear(2020));
		System.out.println(date.with(ChronoField.YEAR, 2018));
		System.out.println(date.plusMonths(11));
		System.out.println(date.plus(11, ChronoUnit.MONTHS));
	}
}
