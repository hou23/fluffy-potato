package java_8_in_action.date;

import java.time.*;
import java.time.temporal.*;

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

		LocalDate testLocalDate = LocalDate.of(2014, 3, 18);
		testLocalDate = testLocalDate.with(ChronoField.MONTH_OF_YEAR, 9);
		testLocalDate = testLocalDate.plusYears(2).minusDays(10);
		testLocalDate.withYear(2011);
		System.out.println(testLocalDate);

		//使用TemporalAdjuster
		LocalDate adjusterDate = LocalDate.of(2017, 3, 23);
		System.out.println(adjusterDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)));
		System.out.println(adjusterDate.with(TemporalAdjusters.lastDayOfMonth()));

		LocalDate nextWorkingDayDate = LocalDate.of(2017, 3, 23);
		System.out.println(nextWorkingDayDate.with(new nextWorkingDay()));

	}
}

/**
 * 自定义的temporalAdjuster
 */
class nextWorkingDay implements TemporalAdjuster{

	@Override
	public Temporal adjustInto(Temporal temporal) {
		DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
		int dayToAdd = 1;
		if (dow == DayOfWeek.FRIDAY) {
			// 增加3天
			dayToAdd = 3;
		} else if (dow == DayOfWeek.SATURDAY) {
			dayToAdd = 2;
		}
		return temporal.plus(dayToAdd, ChronoUnit.DAYS);
	}
}
