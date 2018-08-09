package com.galdino.functional.interfaces;

@FunctionalInterface
public interface Converter<F, T> {
	T convert(F from);
}
