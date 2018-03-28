package java_8_in_action.domain;

import java.util.Optional;

/**
 * Created by yaojie.hou on 2017/4/18.
 */
public class Person {
	private Optional<Car> car;
	public Optional<Car> getCar() {
		return car;
	}
}
