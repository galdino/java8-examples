package com.galdino.functional.interfaces;

public class ExampleFuncInterfaces {

	public static void main(String[] args) {
		Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
		Integer converted = converter.convert("123");
		System.out.println(converted);
		
		//Built-in Functional Interfaces
		
		//Predicates
		//Predicates are boolean-valued functions of one argument. 
		//The interface contains various default methods for composing predicates to complex logical terms (and, or, negate)
		Predicate<String> predicate = (s) -> s.length() > 0;
		Predicate<Object> nonNull = Objects::nonNull;
		Predicate<String> isEmpty = String::isEmpty;
		
		
		System.out.println("predicate ---> " + predicate.test("test")); 
		System.out.println("predicate ---> " + predicate.negate().test("test"));
		System.out.println("nonNull ---> " + nonNull.test(new Integer(2)));
		System.out.println("nonNull ---> " + nonNull.test(null));
		System.out.println("isEmpty ---> " + isEmpty.test(""));
		System.out.println("isEmpty ---> " + isEmpty.test("test"));
		
		//Functions
		//Functions accept one argument and produce a result.
		//Default methods can be used to chain multiple functions together (compose, andThen).
		Function<String, Integer> toInteger = Integer::valueOf;
		Function<String, String> backToString = toInteger.andThen(String::valueOf);
		
		System.out.println("toInteger ---> " + toInteger.apply("123"));
		System.out.println("backToString ---> " + backToString.apply("123"));
		
		//Suppliers
		//Suppliers produce a result of a given generic type. Unlike Functions, Suppliers don't accept arguments.
		Supplier<Person> personSupplier = Person::new;
		
		System.out.println("personSupplier ---> " + personSupplier.get());
		
		//Consumers
		//Consumers represents operations to be performed on a single input argument.
		Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.firstName);
		greeter.accept(new Person("Luke", "Skywalker"));
		
		//Comparators
		//Comparators are well known from older versions of Java. Java 8 adds various default methods to the interface.
		Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);
		
		Person p1 = new Person("John", "Doe");
		Person p2 = new Person("Alice", "Wonderland");
		
		System.out.println("comparator.compare ---> " + comparator.compare(p1, p2));
		System.out.println("comparator.compare ---> " + comparator.reversed().compare(p1, p2));
		
		//Optionals
		//Optionals are not functional interfaces, instead it's a nifty utility to prevent NullPointerException
		Person person = new Person();
		//person.setFirstName("John");
		Optional<String> optional = Optional.ofNullable(person.getFirstName());
		
		System.out.println("optional.isPresent() ---> " + optional.isPresent());
		System.out.println("optional.orElse() ---> " + optional.orElse("fallback"));
		//System.out.println("optional.get() ---> " + optional.get());
		optional.ifPresent((s) -> System.out.println("optional.ifPresent() ---> " + s.charAt(0)));
	}

}
