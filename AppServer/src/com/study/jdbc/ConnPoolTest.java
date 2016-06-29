package com.study.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;

public class ConnPoolTest {

	public static void main(String[] args) throws Exception {
		final ConnPool connPool = new ConnPool("com.mysql.jdbc.Driver",
				"jdbc:mysql://localhost:3306/onemenudb", "root", "root");

		connPool.createPool();

		class connThread extends Thread {

			public void run() {
				Connection conn;
				try {
					conn = connPool.getConnection();
					// System.out.println(Thread.currentThread().getName() +
					// " using --> " + conn);
					Random r = new Random();
					Thread.sleep(r.nextInt(10000));
					connPool.returnConnection(conn);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		;

		for (int i = 0; i < 100; i++) {
			Thread t = new connThread();
			t.start();
		}

		// connPool.closeConnectionPool();

	}

}