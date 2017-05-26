package com.marshall.Java8InAction.date;

import java.time.*;
import java.time.chrono.Chronology;
import java.time.chrono.JapaneseDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

/**
 * Created by yaojie.hou on 2017/3/27.
 *
 * 用格式器处理日期和时间对象
 */
public class DateTimeFormat {
	public static void main(String[] args) {
		// 将LocalDate对象格式化为String
		LocalDate date1 = LocalDate.of(2017, 3, 27);
		String format1 = date1.format(DateTimeFormatter.BASIC_ISO_DATE);
		String format2 = date1.format(DateTimeFormatter.ISO_LOCAL_DATE);
		System.out.println(format1 + ", " + format2);

		// 将String解析为LocalDate对象
		LocalDate date2 = LocalDate.parse("2017-03-27", DateTimeFormatter.ISO_LOCAL_DATE);
		System.out.println(date2);
		//DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
		//String parse = LocalDate.parse("2017-03", DateTimeFormatter.ISO_LOCAL_DATE).toString();
		//System.out.println(parse);
		// 使用自定义的格式创建DateTimeFormatter
		DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
		String format3 = date1.format(dateTimeFormatter1);
		System.out.println(format3);

		// 创建一个本地化的DateTimeFormatter
		DateTimeFormatter itaDateTimeFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);
		System.out.println(date1.format(itaDateTimeFormatter));

		// 构造一个DateTimeFormatter
		DateTimeFormatter italianFormatter = new DateTimeFormatterBuilder()
				.appendText(ChronoField.DAY_OF_MONTH)
				.appendLiteral(". ")
				.appendText(ChronoField.MONTH_OF_YEAR)
				.appendLiteral(" ")
				.appendText(ChronoField.YEAR)
				.parseCaseInsensitive()
				.toFormatter(Locale.ITALIAN);

		DateTimeFormatter myFormatter = DateTimeFormatter.ofPattern("yyyy年MM月", Locale.CHINA);
		LocalDate parse = LocalDate.parse("2017-03-27", myFormatter);
		System.out.println(parse);

		ZoneId romeZone = ZoneId.of("Europe/Rome");
		// 为时间点添加时区信息
		LocalDate date3 = LocalDate.of(2014, Month.MARCH, 18);
		System.out.println(date3.atStartOfDay(romeZone));
		Instant now = Instant.now();
		System.out.println(now.atZone(romeZone));

		// 将LocalDateTime转为Instant
		LocalDateTime dateTime = LocalDateTime.of(2017, Month.MARCH, 27, 14, 12);
		LocalDateTime timeFormatInstant = LocalDateTime.ofInstant(now, romeZone);
		System.out.println(timeFormatInstant);

		// 使用别的日历系统，建议使用LocalDate，Chronology只有在需要将时间本地化时使用
		JapaneseDate japaneseDate = JapaneseDate.from(date1);
		System.out.println(japaneseDate);
		Chronology japaneseChronology = Chronology.ofLocale(Locale.JAPAN);
		System.out.println(japaneseChronology.dateNow());
	}
}
