package com.galdino.functional.interfaces;

public class ExampleFuncInterfaces {

	public static void main(String[] args) {
		Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
		Integer converted = converter.convert("123");
		System.out.println(converted);
	}

}
