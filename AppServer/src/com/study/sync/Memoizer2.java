package com.study.sync;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Memoizer2<A, V> implements Computable<A, V> {
	//use ConcurrentHashMap replace HashMap, but this is not threadsafe that exist a bug which run a duplicate compute.	
	private final Map<A, V> cache = new ConcurrentHashMap<A, V>();  
	private final Computable<A, V> c;

	public Memoizer2(Computable<A, V> c) {

		this.c = c;
	}

	public V compute(final A arg) throws InterruptedException {
		V result = cache.get(arg);
		if(result == null) {
			result = c.compute(arg);
			cache.put(arg, result);
		}
		return result;
	}


}