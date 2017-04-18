package com.marshall.Java8InAction.refactor;

import com.marshall.Java8InAction.domain.Car;
import com.marshall.Java8InAction.domain.Insurance;
import com.marshall.Java8InAction.domain.Person;

import java.util.Optional;

/**
 * Created by yaojie.hou on 2017/4/18.
 */
public class OptionalDemo {
	public static void main(String[] args) {
		Insurance insurance = new Insurance();
		//创建Optional对象
		Optional<Insurance> optInsurance = Optional.ofNullable(insurance);
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


}
