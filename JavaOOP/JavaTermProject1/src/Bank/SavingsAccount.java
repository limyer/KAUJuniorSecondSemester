package Bank;

public class SavingsAccount extends Account{

	public SavingsAccount(){
		balance=0;
	}
	
	public SavingsAccount(int initialBalance){
		balance=initialBalance;
	}
	
	public boolean debit(int amount){
		System.out.println("���� ���¿����� ����� �� �����ϴ�.");
		return false;
	}
}