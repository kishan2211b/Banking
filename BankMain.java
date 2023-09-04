package kishan;

import java.util.*;
import java.sql.*;

public class BankMain {

	static int n;
	static int ch, row;

	private static void printObj(Object[][] objVA) {
		for (int i = 0; i < objVA.length; i++) {
			for (int j = 0; j < objVA[0].length; j++) {
				if (objVA[i][j] instanceof String) {
					System.out.print(objVA[i][j] + "\t\t");
				} else if (objVA[i][j] instanceof Integer) {
					System.out.print(objVA[i][j] + "\t\t");
				} else if (objVA[i][j] instanceof Double) {
					System.out.print(objVA[i][j] + "\t\t");
				} else if (objVA[i][j] instanceof Timestamp) {
					System.out.print(objVA[i][j] + "\t");
				}

			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws Throwable {

		while (true) {
			try {
				System.out.println("1.Staff\n2.User");
				Scanner input = new Scanner(System.in);
				System.out.println("Enter the choice : ");
				ch = input.nextInt();
				switch (ch) {
				case 1:
					System.out.println("-------------------------");
					System.out.println("\t\tStaff");
					System.out.println("1.Signup\n2.login");
					System.out.println("Enter the choice : ");
					int ch = input.nextInt();
					switch (ch) {
					case 1:
						System.out.println("\t\tSignup");
						System.out.println("1. Creating Account.....");
						int row = StaffUtil.createAccount();
						if (row > 0) {
							System.out.println("Account Created...");
						}
						break;
					case 2:
						AuditFields ObjAF = new AuditFields();
						System.out.println("\t\t Login");
						System.out.println("Enter the Staff Id : ");
						n = input.nextInt();
						ObjAF.setCreatedBy(n);
						if (StaffUtil.checkAccount(n)) {
							while (true) {
								System.out.println(
										"1.Create Acc for Benificiery\n2.View ONe Account\n3.view all account\n4.Delete Account\n5.logout");
								System.out.println("Enter the choice : ");
								ch = input.nextInt();
								switch (ch) {
								case 1:
									System.out.println("-----Creating Account for Benificiery-----");
									Object[] obj = CustomerUtil.getData();
									System.out.println(
											"Do you want to deposit more than Minimum balance ? \n1.yes\n2.no");
									ch = input.nextInt();
									if (ch == 1) {
										System.out.println("Enter the amount : ");
										int bal = input.nextInt();
										Customer obj1 = new Customer(bal);
									} else {
										Customer obj1 = new Customer();
									}
									row = CustomerUtil.createAccount(obj, n);
									if (row > 0) {
										System.out.println("Account Created...");
									}
									break;
								case 2:
									System.out.println("-----View One Account-----");
									Object[][] objVOA = StaffUtil.viewOneAccount();
									System.out.println(
											"AccHolName \t Account No \t Balance \t Created By \t Created Date");
									printObj(objVOA);
									break;
								case 3:
									System.out.println("-----View All Account-----");
									Object[][] objVAA = StaffUtil.viewAllAccount();
									System.out.println(
											"AccHolName \t Account No \t Balance \t Created By \t Created Date");
									printObj(objVAA);
									break;
								case 4:
									System.out.println("-----Delete Account-----");
									int del = StaffUtil.deleteAccount();
									if (del > 0) {
										System.out.println("Account Deleted....");
									}
									break;
								case 5:
									return;
								}
							}
						} else {
							System.out.println("Account Not exitst");
						}
					default:
						System.out.println("Invalid choice ..");
						break;
					}
					break;
				case 2:
					System.out.println("-------------------------");
					System.out.println("\t\tCustomer");
					System.out.println("Enter the customer Id : ");
					n = input.nextInt();
					if (CustomerUtil.checkAccout(n)) {
						while (true) {
							System.out
									.println("1.Balance Enquiry\n2.Deposit\n3.Withdraw\n4.logout\nEnter the Choice :");
							ch = input.nextInt();
							switch (ch) {
							case 1:
								System.out.println("-----Balance Enquiry-----");
								double bal = CustomerUtil.balanceEnquiry(n);
								System.out.println("The Balance of " + n + " is " + bal);
								break;
							case 2:
								System.out.println("-----Depositing Amount-----");
								int dep = CustomerUtil.depositAmount(n);
								if (dep > 0) {
									System.out.println("Amount Deposited....");
								}
								break;
							case 3:
								System.out.println("-----Withdraw Amount-----");
								int wid = CustomerUtil.withdrawAmount(n);
								if (wid > 0) {
									System.out.println("Amount Withdrawn....");
								}
								break;
							case 4:
								return;
							}
						}
					} else {
						System.out.println("Account Not exitst");
					}
				}
			} catch (Exception e) {
				GlobalExceptionHandler.handleException(e);
			}finally {
				System.out.println("Logged OUt");
			}
		}
	}

}
