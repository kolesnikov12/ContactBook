package com.guzichenko.validators;

public class Validate {

	private Validate() {
		throw new IllegalStateException();
	}

	public static void validateAge(int age) throws Exception {
		if (age > 150) {
			throw new Exception("Too old.");
		}
	}

}