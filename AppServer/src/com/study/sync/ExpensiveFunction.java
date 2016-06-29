package com.study.sync;

import java.math.BigInteger;

public class ExpensiveFunction implements Computable<String, BigInteger> {

	public BigInteger compute(String arg) {
		
		//after a long-time running 
		return new BigInteger(arg);
	}

}