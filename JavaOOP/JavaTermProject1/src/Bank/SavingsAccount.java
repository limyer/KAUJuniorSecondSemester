package Bank;

public class SavingsAccount extends Account{

	public SavingsAccount(){
		balance=0;
	}
	
	public SavingsAccount(int initialBalance){
		balance=initialBalance;
	}
	
	public boolean debit(int amount){
		System.out.println("예금 계좌에서는 출금할 수 없습니다.");
		return false;
	}
}