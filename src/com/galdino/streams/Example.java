package com.galdino.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.galdino.functional.interfaces.Person;

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
		
		//Primitive Streams
		//Special kinds of streams for working with the primitive data types int, long and double.
		//And primitive streams support the additional terminal aggregate operations sum() and average().
		IntStream.range(1, 4)
			.forEach((s) -> System.out.println("IntStream.range() --> " + s));
		
		Arrays.stream(new int[] {1,2,3})
			.map(n -> 2 * n + 1)
			.average()
			.ifPresent((s) -> System.out.println("Arrays.stream().average() --> " + s));
		
		Stream.of("a1","a2","a3")
			.map((s) -> s.substring(1))
			.mapToInt((s) -> Integer.parseInt(s))
			.max()
			.ifPresent((s) -> System.out.println("Stream.of().map().mapToInt().max() --> " + s));
	
		IntStream.range(1, 4)
			.mapToObj((s) -> "a" + s)
			.forEach((s) -> System.out.println("IntStream.range().mapToObj() --> " + s));
		
		Stream.of(1.0, 2.0, 3.0)
			.mapToInt((s) -> new Double(s).intValue())
			.mapToObj((s) -> "a" + s)
			.forEach((s) -> System.out.println("Stream.of().mapToInt().mapToObj() --> " + s));
		
		Stream.of("d2","a2","b1","b3","c")
			.filter((s) -> {
				System.out.println("filter: " + s);
				return true;
			})
			.forEach((s) -> System.out.println("forEach: " + s));
		
		//Advanced Operations
		//Complex operations collect, flatMap and reduce.
		List<Person> persons = Arrays.asList(new Person("Max", 18),
					  new Person("Peter", 23),
					  new Person("Pamela", 23),
					  new Person("David", 12));
		
		List<Person> filtered = persons
			.stream()
			.filter((s) -> s.getFirstName().startsWith("P"))
			.collect(Collectors.toList());		
		System.out.println("stream().filter().collect() --> " + filtered);
		
		Map<Integer, List<Person>> personsByAge = persons
			.stream()
			.collect(Collectors.groupingBy((s) -> s.getAge()));
		personsByAge.forEach((age, p) -> System.out.format("age %s: %s\n", age, p));
		
		Double averageAge = persons
			.stream()
			.collect(Collectors.averagingInt((s) -> s.getAge()));
		System.out.println("stream().collect(Collectors.averagingInt()) --> " + averageAge);
		
		IntSummaryStatistics ageSummary = persons
		.stream()
		.collect(Collectors.summarizingInt((s) -> s.getAge()));
		System.out.println("stream().collect(Collectors.summarizingInt()) --> " + ageSummary);
		
		//The join collector accepts a delimiter as well as an optional prefix and suffix.
		String phrase = persons
		.stream()
		.filter((s) -> s.getAge() >= 18)
		.map((s)-> s.getFirstName())
		.collect(Collectors.joining(" and ", "In Germany ", " are of legal age."));
		System.out.println("stream().collect(Collectors.joining()) --> " + phrase);
		
		//In order to transform the stream elements into a map, we have to specify how both the keys and the values should be mapped.
		Map<Integer, String> map = persons
		.stream()
		.collect(Collectors.toMap(
				(s) -> s.getAge(), 
				(s) -> s.getFirstName(),
				(name1, name2) -> name1 + ";" + name2));
		System.out.println("stream().collect(Collectors.toMap()) --> " + map);
		
		//Creating a new collector via Collector.of()
		Collector<Person, StringJoiner, String> personNameCollector = Collector.of(() -> new StringJoiner(" | "), //supplier
					 (j, s) -> j.add(s.getFirstName().toUpperCase()), //accumulator 
					 (j1, j2) -> j1.merge(j2), //combiner 
					 StringJoiner::toString);
		
		
		String names = persons
		.stream()
		.collect(personNameCollector);
		System.out.println("stream().collect(Collector.of()) --> " + names);
		
		//FlatMap transforms each element of the stream into a stream of other objects.
		List<Foo> foos = new ArrayList<>();
		
		IntStream.
			range(1, 4)
			.forEach((i) -> foos.add(new Foo("Foo" + i)));
		
		foos.forEach((f) ->
				IntStream.
				range(1, 4).
				forEach((i) -> f.bars.add(new Bar("Bar" + i + " <- " + f.name))));
		
		foos.stream()
			.flatMap((f) -> f.bars.stream())
			.forEach((b) -> System.out.println(b.name));
		
		//The reduction operation combines all elements of the stream into a single result.
		//BinaryOperator accumulator function
		persons
			.stream()
			.reduce((p1, p2) -> p1.getAge() > p2.getAge() ? p1 : p2)
			.ifPresent((p) -> System.out.println("stream().reduce(BinaryOperator) --> " +   p.getFirstName()));
		
		//identity value and BinaryOperator accumulator function
		Person result = persons
			.stream()
			.reduce(new Person("", 0), (p1, p2) -> {
				p1.setAge(p1.getAge() + p2.getAge());
				p1.setFirstName(p1.getFirstName() + p2.getFirstName());
				return p1;
			});
		
		System.out.println("stream().reduce(identity, BinaryOperator) --> "+ "name: " + result.getFirstName() + " age: " + result.getAge());
		
		//identity value, BinaryOperator accumulator function and combiner function of type BinaryOperator
		Integer ageSum = persons
			.stream()
			.reduce(0, (sum, p) -> sum += p.getAge(), (sum1, sum2) -> sum1 + sum2);
		
		System.out.println("stream().reduce(identity, BinaryOperator, combiner) --> ageSum: " + ageSum);
		
		
	}

}
