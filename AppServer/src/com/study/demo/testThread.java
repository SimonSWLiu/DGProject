package com.study.demo;


public class testThread {

    public static void main(String[] args) {

        Thread t = new Thread() {
        	public void run() {
        		
        		pong();
        	}
        };
        t.setPriority(10);
        
        Thread t2 = new Thread() {
        	public void run() {
        		
        		pung();
        	}
        };
        
        t.setPriority(10);
        t2.setPriority(4);
        
        t.start();
        t2.start();
        
        System.out.println("ping"); //default Priority is 5
    }

    static void pong(){
    	
    	System.out.println("pong");
    	
    }
    
    static void pung(){
    	
    	System.out.println("pung");
    	
    }
}
