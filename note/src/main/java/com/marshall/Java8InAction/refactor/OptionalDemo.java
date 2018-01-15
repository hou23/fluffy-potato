package com.marshall.Java8InAction.refactor;

import com.marshall.Java8InAction.domain.Car;
import com.marshall.Java8InAction.domain.Insurance;
import com.marshall.Java8InAction.domain.Person;
import org.junit.Test;

import java.util.Optional;
import java.util.Properties;

import static java.util.Optional.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by yaojie.hou on 2017/4/18.
 */
public class OptionalDemo {
	public static void main(String[] args) {
		Insurance insurance = new Insurance();
		//创建Optional对象
		Optional<Insurance> optInsurance = ofNullable(insurance);
		//从Optional
		Optional<String> name = optInsurance.map(Insurance::getName);


	}

	//从Person对象中获取Insurance的名字
	public String getCarInsuranceName(Optional<Person> person) {
		return person.flatMap(Person::getCar)
				.flatMap(Car::getInsurance)
				.map(Insurance::getName)
				.orElse("Unknown");
	}

	//根据传入的Person和Car对象，找出最便宜的Insurance
	public Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person, Optional<Car> car) {
		return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
	}

	//找出最便宜的Insurance的逻辑
	public Insurance findCheapestInsurance(Person person, Car car) {
		//省略逻辑代码
		return new Insurance();
	}


	@Test
	public void testMap() {
		Properties props = new Properties();
		props.setProperty("a", "5");
		props.setProperty("b", "true");
		props.setProperty("c", "-3");
		assertEquals(5, readDurationImperative(props, "a"));
		assertEquals(0, readDurationImperative(props, "b"));
		assertEquals(0, readDurationImperative(props, "c"));
		assertEquals(0, readDurationImperative(props, "d"));

		assertEquals(5, readDurationWithOptional(props, "a"));
		assertEquals(0, readDurationWithOptional(props, "b"));
		assertEquals(0, readDurationWithOptional(props, "c"));
		assertEquals(0, readDurationWithOptional(props, "d"));
	}

	private static int readDurationImperative(Properties props, String name) {
		String value = props.getProperty(name);
		if (value != null) {
			try {
				int i = Integer.parseInt(value);
				if (i > 0) {
					return i;
				}
			} catch (NumberFormatException nfe) {}
		}
		return 0;
	}

	private static int readDurationWithOptional(Properties props, String name) {
		return ofNullable(props.getProperty(name))
				.flatMap(OptionalDemo::s2i)
				.filter(i -> i > 0).orElse(0);
	}

	public static Optional<Integer> s2i(String s) {
		try {
			return of(Integer.parseInt(s));
		} catch (NumberFormatException e) {
			return empty();
		}
	}
}
