package kishan;

import java.util.*;
import java.sql.*;

public class CustomerUtil {
	Customer objCus = new Customer();
	AuditFields ObjAF = new AuditFields();

	static Object[] getData() {
		Object[] obj = new Object[5];
		Customer c = new Customer();
		AuditFields ObjAF = new AuditFields();
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the Account Holder Name : ");
		obj[0] = input.next();
		System.out.println("Enter the Account Number : ");
		obj[1] = input.nextInt();
		c.setAccHolName(obj[0].toString());
		c.setAccNo(obj[1].hashCode());
		obj[2] = c.getBalance();
		obj[3] = ObjAF.getCreatedBy();
		System.out.println(ObjAF.getCreatedBy());
		obj[4] = BankDB.currentDate();
		return obj;
	}

	static int createAccount(Object[] obj, int n) throws SQLException {
		String query = "insert into customer values(?,?,?,?,?)";
		return BankDB.executeQuery(query, obj);
	}

	static boolean checkAccout(int n) throws SQLException, IncorrecAccNumberException {
		String query = "select AccNo from customer where AccNo=" + n + "";
		ResultSet rs = BankDB.getRecords(query);
		while (rs.next()) {
			return true;
		}
		throw new IncorrecAccNumberException("Account Not Exist....");
	}

	static double balanceEnquiry(int n) throws SQLException {
		String query = "select balance from customer where AccNo=" + n + "";
		Scanner input = new Scanner(System.in);
		ResultSet rs = BankDB.getRecords(query);
		if (rs.next())
			return rs.getDouble(1);
		return 0;

	}

	static int depositAmount(int n) throws SQLException {
		Scanner input = new Scanner(System.in);
		System.out.println("enter the Deposit Amout : ");
		double depAmount = input.nextDouble();
		String query = "update customer set Balance=Balance+" + depAmount + "where AccNo=" + n;
		return BankDB.executeQuery(query,null);
	}

	static int withdrawAmount(int n) throws SQLException,ExceptionHandling, CustomException, InsufficientBalanceException {
			String query = "select balance from customer where AccNo=" + n + "";
			ResultSet rs = BankDB.getRecords(query);
			Scanner input = new Scanner(System.in);
			System.out.println("enter the withdraw Amount : ");
			double widAmt = input.nextDouble();
			if (rs.next()) {
				double bal = rs.getDouble(1);
				try {
					if (widAmt < bal) {
						String query1 = "update customer set Balance=Balance-" + widAmt + "where AccNo=" + n;
						return BankDB.executeQuery(query1,null);
					}
					else {
						throw new InsufficientBalanceException("Your Withdraw Amount Is Higher Than Your Balance....");
					}
				}catch(InsufficientBalanceException ibe) {
					 throw ibe;
				}
			}
			return 0;
		}
	
}
