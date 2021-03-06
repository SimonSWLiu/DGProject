package com.study.jdbc.sample;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class JdbcUtilTest {

	@Test
	public void test() {

		
		class dbThread extends Thread{
			
			public void run() {
				String sql = "select * from menu_top_tags";

				Connection conn = null;
				Statement st = null;
				ResultSet rs = null;
				ResultSetMetaData rsmd = null;

				try {
					conn = JdbcUtil.getConnection();
					st = conn.createStatement();
					rs = st.executeQuery(sql);
					rsmd = rs.getMetaData();
					int columns = rsmd.getColumnCount();
					// 显示列,表格的表头
					for (int i = 1; i <= columns; i++) {
						System.out.print(rsmd.getColumnName(i));
						System.out.print("\t\t");
					}
					System.out.println();

					while (rs.next()) {
						for (int i = 1; i <= columns; i++) {
							System.out.print(rs.getString(i));
							System.out.print("\t\t");
						}
						System.out.println();
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					JdbcUtil.release(conn, st, rs);
					rsmd = null;
				}
			}
		};
		

	}
}