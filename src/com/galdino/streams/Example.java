package com.galdino.streams;

import java.util.ArrayList;
import java.util.List;

public class Example {

	public static void main(String[] args) {
		
		List<String> stringCollection = new ArrayList<>();
		stringCollection.add("ddd2");
		stringCollection.add("aaa2");
		stringCollection.add("bbb1");
		stringCollection.add("aaa1");
		stringCollection.add("bbb3");
		stringCollection.add("ccc");
		stringCollection.add("bbb2");
		stringCollection.add("ddd1");
		
		//Filter
		//Filter accepts a predicate to filter all elements of the stream.
		stringCollection
		.stream()
		.filter((s) -> s.startsWith("a"))
		.forEach((s) -> System.out.println("stream.filter() ---> " + s));
		
		//Sorted
		//Sorted is an intermediate operation which returns a sorted view of the stream.
		stringCollection
		.stream()
		.sorted()
		.filter((s) -> s.startsWith("a"))
		.forEach((s) -> System.out.println("stream.sorted.filter() --> " + s));
		//The ordering of stringCollection is untouched
		System.out.println(stringCollection);

	}

}
