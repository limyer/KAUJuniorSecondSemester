//Acount.java

public class Account{
	private double balance; // �ܰ� ���� �ν��Ͻ� ����
	
	// ������
	public Account(double initialBalance) {
		// ó�� �ܰ� 0.0���� ũ���� ����
		// ������ ����Ʈ
		if (initialBalance > 0.0)
			balance = initialBalance;
	}
	
	// ���¿� �� �Ա�
	public String credit(double amount) {
		if (amount < 0.0) {
			return "Invalid amount\n";
		}
		else {
			balance = balance + amount;
			return "";
		}
	}
	
	// ���¿� �� ���
	public String debit(double amount) {
		if (balance - amount < 0.0) {
			return "Debit amount exceeded account balance.\n";
		}
		else {
			balance = balance - amount;
			return "";
		}
	}
	
	// �ܰ� ��ȯ
	public double getBalance() {
		return balance;
	}
	
	// �ܰ� ����
	public void setBalance(double amount) {
		balance = amount;
	}
	
	
	
}