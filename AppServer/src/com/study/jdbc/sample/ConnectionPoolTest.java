package com.study.jdbc.sample;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolTest {     
    
    public static void main(String[] args) throws Exception {     
        final ConnectionPool connPool = new ConnectionPool("com.mysql.jdbc.Driver",     
                "jdbc:mysql://localhost:3306/onemenudb", "root", "root");     
    
        connPool.createPool();     
        
        class connThread extends Thread {

        	public void run() {
        		Connection conn;
				try {
					conn = connPool.getConnection();
	                System.out.println(Thread.currentThread().getName() + " using --> " + conn);
	                System.out.println("连接池中可用连接数 －－> " + connPool.getCurrentConnections());
	                connPool.returnConnection(conn);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
        };
        
        for (int i = 0; i < 100; i++) {
        	Thread t = new connThread();
        	t.start();
		}
        
//        connPool.closeConnectionPool();
           
    }     
         
} 