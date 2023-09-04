package kishan;

import java.util.*;
import java.sql.*;

public class StaffUtil {
	static Object[] getData() throws ExceptionHandling{
		Object[] obj = new Object[2];
		Staff s = new Staff();
		Scanner input = new Scanner(System.in);
		try {
			System.out.println("Enter the Staff Name : ");
			obj[0] = input.next();
			System.out.println("Enter the Staff Id : ");
			obj[1] = input.nextInt();
			s.setName(obj[0].toString());
			s.setId(obj[1].hashCode());
		
		}catch(InputMismatchException itmm) {
			System.out.println(itmm);
	
		}
		return obj;
	}

	static boolean checkAccount(int n) throws SQLException, IncorrecAccNumberException {
		String query = "select id from staff where id=" + n + "";
		ResultSet rs = BankDB.getRecords(query);
		while (rs.next()) {
			return true;
		}
		throw new IncorrecAccNumberException("Account Not Exist....");
	}

	static int createAccount() throws SQLException, ExceptionHandling {
		Object[] obj = getData();
		String query = "insert into staff values(?,?)";
		return BankDB.executeQuery(query, obj);
	}

	static Object[][] viewOneAccount() throws SQLException {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the Customer ID ");
		int id = input.nextInt();
		String query = "select * from customer where AccNo=" + id + "";
		ResultSet rs = BankDB.getRecords(query);
		int row = getRowSize(rs);
		Object[][] objVOA = assignMethod(rs, row);
		return objVOA;
	}

	static Object[][] viewAllAccount() throws SQLException {
		String query = "select * from customer;";
		ResultSet rs = BankDB.getRecords(query);
		int row = getRowSize(rs);
		Object[][] objVAA = assignMethod(rs, row);
		return objVAA;

	}

	private static int getRowSize(ResultSet rs) throws SQLException {
		int rowSize = 0;
		ResultSetMetaData metadata = rs.getMetaData();
		String tblName = metadata.getTableName(1);
		String countQuery = "SELECT COUNT(*) FROM " + tblName;
		ResultSet rs1 = BankDB.getRecords(countQuery);
		if (rs1.next()) {
			return rowSize = rs1.getInt(1);
		}
		return 0;
	}

	private static Object[][] assignMethod(ResultSet rs, int row) throws SQLException {
		ResultSetMetaData metadata = rs.getMetaData();
		int columnlength = metadata.getColumnCount();
		Object[][] objam = new Object[row][columnlength];
		for (int i = 0; i < objam.length && rs.next(); i++) {
			for (int j = 0; j < objam[0].length; j++) {
				int columnType = metadata.getColumnType(j + 1);
				if (columnType == Types.INTEGER) {
					objam[i][j] = rs.getInt(j + 1);
				} else if (columnType == Types.VARCHAR) {
					objam[i][j] = rs.getString(j + 1);
				} else if (columnType == Types.DOUBLE) {
					objam[i][j] = rs.getDouble(j + 1);
				} else if (columnType == Types.TIMESTAMP) {
					objam[i][j] = rs.getTimestamp(j + 1);
				}
			}
			System.out.println();
		}
		return objam;
	}

	static int deleteAccount() throws SQLException {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the Customer ID ");
		int id = input.nextInt();
		String query="delete from customer where AccNo="+id+"";
		return BankDB.executeQuery(query,null);
	}

}
