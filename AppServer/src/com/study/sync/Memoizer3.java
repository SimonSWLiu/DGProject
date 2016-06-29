package com.study.sync;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import org.apache.http.annotation.GuardedBy;

public class Memoizer3<A, V> implements Computable<A, V> {

	@GuardedBy("this")
	private final Map<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>(); // replace
																					// concurrent
																					// type
	private final Computable<A, V> c;

	public Memoizer3(Computable<A, V> c) {

		this.c = c;
	}

	public V compute(final A arg) throws InterruptedException {
		Future<V> f = cache.get(arg);
		if (f == null) {
			Callable<V> eval = new Callable<V>() {
				public V call() throws InterruptedException {
					return c.compute(arg);
				}
			};
			FutureTask<V> ft = new FutureTask<V>(eval);
			f = ft;
			cache.put(arg, f); // here is nonatomic
			ft.run(); // call c.compute here
		}
		try {
			return f.get();
		} catch (ExecutionException e) {
			throw Exception.launderThrowable(e.getCause());
		}
	}

}