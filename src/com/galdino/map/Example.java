package com.galdino.map;

import java.util.HashMap;
import java.util.Map;

public class Example {

	public static void main(String[] args) {
		Map<Integer,String> map = new HashMap<>();
		for(int i = 0; i < 10; i++) {
			map.putIfAbsent(i, "val" + i);
		}
		
		map.forEach((id, val) -> System.out.println("map.forEach() --> " + val));
		System.out.println();
		
		map.computeIfPresent(3, (num, val) -> val + val);
		System.out.println("map.computeIfPresent() --> " + map.get(3));
		
		map.computeIfPresent(9, (num, val) -> null);
		System.out.println("map.computeIfPresent() --> " + map.get(9));
		
		map.computeIfAbsent(23, num -> "val" + num);
		System.out.println("map.computeIfAbsent() --> " + map.get(23));
		
		map.computeIfAbsent(4, num -> "bam");
		System.out.println("map.computeIfAbsent() --> " + map.get(4));
		
		map.remove(4, "val");
		System.out.println("map.remove() --> " + map.get(4));
		map.remove(4, "val4");
		System.out.println("map.remove() --> " + map.get(4));
		
		System.out.println(map.getOrDefault(42, "not found"));
		
		map.merge(5, "concat", (value, newValue) -> value.concat(newValue));
		System.out.println("map.merge() --> " + map.get(5));
		
	}

}
