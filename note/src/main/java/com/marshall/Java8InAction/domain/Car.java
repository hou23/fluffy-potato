package com.marshall.Java8InAction.domain;

import java.util.Optional;

/**
 * Created by yaojie.hou on 2017/4/18.
 */
public class Car {
	private Optional<Insurance> insurance;
	public Optional<Insurance> getInsurance() {
		return insurance;
	}
}
