package Bank;

//입출금 계좌
public class CheckingAccount extends Account{
	public CheckingAccount(){
		balance=0;
	}
	
	public CheckingAccount(int initialBalance){
		balance=initialBalance;
	}
}