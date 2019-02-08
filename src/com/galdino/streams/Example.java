package com.galdino.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		.forEach((s) -> System.out.println("stream().filter() ---> " + s));
		
		//Sorted
		//Sorted is an intermediate operation which returns a sorted view of the stream.
		stringCollection
		.stream()
		.sorted()
		.filter((s) -> s.startsWith("a"))
		.forEach((s) -> System.out.println("stream().sorted().filter() --> " + s));
		//The ordering of stringCollection is untouched
		System.out.println(stringCollection);
		
		//Map
		//The intermediate operation map converts each element into another object via the given function.
		stringCollection
		.stream()
		.map(String::toUpperCase)
		.sorted((a, b) -> a.compareTo(b))
		.forEach((s) -> System.out.print("stream().map() --> " + s + ", "));
		//The ordering of stringCollection is untouched
		System.out.println();
		System.out.println(stringCollection);
		
		//Match
		//Various matching operations can be used to check whether a certain predicate matches the stream.
		boolean anyStartsWithA = stringCollection
		.stream()
		.anyMatch((s) -> s.startsWith("a"));
		System.out.println("stream().anyMatch() |anyStartsWithA| --> " + anyStartsWithA);
		
		boolean allStartsWithA = stringCollection
		.stream()
		.allMatch((s) -> s.startsWith("a"));
		System.out.println("stream().allMatch() |allStartsWithA| --> " + allStartsWithA);
		
		boolean noneStartsWithZ = stringCollection
		.stream()
		.noneMatch((s) -> s.startsWith("z"));
		System.out.println("stream().noneMatch() |noneStartsWithZ| --> " + noneStartsWithZ);
		
		//Count
		//Count is a terminal operation returning the number of elements in the stream as a long.
		long startsWithBCount = stringCollection
		.stream()
		.filter((s) -> s.startsWith("b"))
		.count();
		System.out.println("stream().filter().count() |startsWithBCount| --> " + startsWithBCount);
		
		//Reduce
		//This terminal operation performs a reduction on the elements of the stream with the given function. 
		//The result is an Optional holding the reduced value.
		Optional<String> reduced = stringCollection
		.stream()
		.sorted()
		.reduce((s1, s2) -> s1 + "#" + s2);
		reduced.ifPresent((s) -> System.out.println("stream().sorted().reduce() --> " + s));

	}

}
