package kishan;

import java.sql.*;
import java.util.Scanner;

public class BankDB extends ExceptionHandling{
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "");
	}

	static Timestamp currentDate() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
	}

	public static int executeQuery(String query, Object[] obj) throws SQLException {
		Connection con = BankDB.getConnection();
		if(obj==null) {
			Statement stmt = con.createStatement();
			return stmt.executeUpdate(query);
		}
		PreparedStatement ps = con.prepareStatement(query);
		for (int i = 0; i < obj.length; i++) {
			if (obj[i] instanceof String) {
				ps.setString(i + 1, obj[i].toString());
			} else if (obj[i] instanceof Integer) {
				ps.setInt(i + 1, obj[i].hashCode());
			} else if (obj[i] instanceof Double) {
				ps.setDouble(i + 1, (Double) obj[i]);
			} else if (obj[i] instanceof Timestamp) {
				ps.setTimestamp(i + 1, (Timestamp) obj[i]);
			}
		}
		return ps.executeUpdate();
	}

	public static ResultSet getRecords(String query) throws SQLException {
		Connection con = BankDB.getConnection();
		Statement stmt = con.createStatement();
		return stmt.executeQuery(query);

	}

	
}
