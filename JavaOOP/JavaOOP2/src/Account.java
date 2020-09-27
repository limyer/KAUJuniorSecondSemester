//Acount.java

public class Account{
	private double balance; // 잔고 저장 인스턴스 변수
	
	// 생성자
	public Account(double initialBalance) {
		// 처음 잔고가 0.0보다 크도록 설정
		// 작으면 디폴트
		if (initialBalance > 0.0)
			balance = initialBalance;
	}
	
	// 계좌에 돈 입금
	public String credit(double amount) {
		if (amount < 0.0) {
			return "Invalid amount\n";
		}
		else {
			balance = balance + amount;
			return "";
		}
	}
	
	// 계좌에 돈 출금
	public String debit(double amount) {
		if (balance - amount < 0.0) {
			return "Debit amount exceeded account balance.\n";
		}
		else {
			balance = balance - amount;
			return "";
		}
	}
	
	// 잔고 반환
	public double getBalance() {
		return balance;
	}
	
	// 잔고 설정
	public void setBalance(double amount) {
		balance = amount;
	}
	
	
	
}