package Bank;

public class Account {
	protected int balance;
	
	public Account(){
		balance=0;
	}
	
	public Account(int initialBalance){
		balance=initialBalance;
	}
	
	public void credit(int amount) {
		balance = amount+balance;
	}
	
	public boolean debit(int amount){
		if(amount>balance) {
			System.out.println("계좌 잔고보다 더 많이 출금할 수 없습니다.");
			return false;
		}
		else {
			balance=balance-amount;
			return true;
		}
	}
	
	public int getBalance(){ return balance;}

	public void setBalance(int amount){ this.balance = amount;}
}
