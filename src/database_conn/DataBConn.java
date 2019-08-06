package database_conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class DataBConn {
	private static String connURL = "jdbc:mysql://localhost/bazaxml";
	private static String userName = "root";
	private static String pass = "";

	private static Connection conn = null;

	public DataBConn() {
		super();

	}

	public static ResultSet getDataFromSQL(String sql) {
		ResultSet set = null;
		if (open()) {
			try {
				Statement s = conn.createStatement();
				set = s.executeQuery(sql);
				// close();
			} catch (SQLException e) {
				return null;
			}
		}
		return set;
	}

	public static int returnSQLNumber(String sql, String columnName) {
		int number = -1;
		if (open()) {
			try {
				Statement s = conn.createStatement();
				ResultSet set = s.executeQuery(sql);
				while (set.next()) {
					number = Integer.parseInt(set.getString(columnName));
				}
				close();
				return number;

			} catch (SQLException e) {
				return -1;
			}
		}
		return number;
	}

	public static String deleteDataFromTable(String table) {
		String sql = "DELETE FROM " + table;
		if (open()) {
			try {
				Statement s = conn.createStatement();
				s.executeUpdate(sql);
				close();
				return "true";
			} catch (SQLException e) {
				return e.getMessage();
			}
		}
		return "Error connecting to database";

	}

	public static String insertRecord(String sql) {
		/// if (open()) {
		try {
			Statement s = conn.createStatement();
			s.executeUpdate(sql);
			/// close();
			return "true";
		} catch (SQLException e) {
			return e.getMessage();
			/// }
		}
		// return "Error connecting to the database";
	}

	public static boolean open() {
		try {
			conn = DriverManager.getConnection(connURL, userName, pass);
		} catch (Exception e) {
			// System.out.println(e.getMessage());
			return false;
		}
		if (conn != null) {
			return true;
		}

		return false;
	}

	public static boolean close() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				return false;
			}
		}
		return true;
	}

}
