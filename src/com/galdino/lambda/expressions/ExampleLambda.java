package com.galdino.lambda.expressions;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ExampleLambda {

	public static void main(String[] args) {
		List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
		
		Collections.sort(names, new Comparator<String>() {
			@Override
			public int compare(String a, String b) {
				return a.compareTo(b);
			}
		});		
		System.out.println(names);
		
		names = Arrays.asList("mike", "xenia", "anna", "peter");		
		Collections.sort(names, (String a, String b) -> {
			return a.compareTo(b);
		});		
		System.out.println(names);
		
		names = Arrays.asList("anna", "xenia", "mike", "peter");		
		Collections.sort(names, (String a, String b) -> a.compareTo(b));
		System.out.println(names);
		
		names = Arrays.asList("peter", "anna", "xenia", "mike");
		Collections.sort(names, (a, b) -> a.compareTo(b));
		System.out.println(names);
		
	}

}
