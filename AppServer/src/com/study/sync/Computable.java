package com.study.sync;

public interface Computable<A, V> {

	V compute(A arg) throws InterruptedException;

}