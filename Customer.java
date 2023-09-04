package kishan;

public class Customer {
	private String accHolName;
	private int accNo;
	private double balance;

	Customer() {
		balance = 1000;
		setBalance(balance);
	}

	Customer(int balance) {
		this.balance = balance;
		setBalance(balance);
	}

	public String getAccHolName() {
		return accHolName;
	}

	public void setAccHolName(String accHolName) {
		this.accHolName = accHolName;
	}

	public int getAccNo() {
		return accNo;
	}

	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}
