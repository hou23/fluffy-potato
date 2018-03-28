package java_concurrency_in_practice.ch03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by yaojie.hou on 2018/3/13.
 *
 * 使用ThreadLocal确保线程封闭性
 */
public class ConnectionDispenser {

	static String DB_URL = "jdbc:mysql://localhost/mydatabase";

	private ThreadLocal<Connection> connectionHolder
			= new ThreadLocal<Connection>() {
		@Override
		public Connection initialValue() {
			try {
				return DriverManager.getConnection(DB_URL);
			} catch (SQLException e) {
				throw new RuntimeException("Unable to acquire Connection, e");
			}
		}
	};

	public Connection getConnection() {
		return connectionHolder.get();
	}

}
