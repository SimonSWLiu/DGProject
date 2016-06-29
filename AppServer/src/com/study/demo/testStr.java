package com.study.demo;

import java.util.concurrent.TimeUnit;


public class testStr {

    public static void main(String[] args) {

        String a = new String("aaa");
        StringBuffer b = new StringBuffer("bbb");
        int c = 10;

        operator(a,b,c);
        
        System.out.println(a + "," + b + "," + c);
        
        System.out.println(TimeUnit.SECONDS);
    }

    static void operator(String a, StringBuffer b, int c){
    	
    	a = a.concat("xxx");
    	b = b.append("yyy");
    	c += 10;
    	System.out.println(a + "," + b + "," + c);
    	
    }
}
