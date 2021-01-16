package Bank;

//계좌
public class Account {
	protected int balance;//잔고 변수
	
	//생성자
	public Account(){
		balance=0;
	}
	
	public Account(int initialBalance){
		balance=initialBalance;
	}
	
	//계좌에 입금
	public void credit(int amount) {
		balance = amount+balance;
	}
	
	//계좌에 출금
	public boolean debit(int amount){
		if(amount>balance) {//잔고 부족
			System.out.println("계좌 잔고보다 더 많이 출금할 수 없습니다.");
			return false;
		}
		else {
			balance=balance-amount;
			return true;
		}
	}
	
	//잔고 반환
	public int getBalance(){ return balance;}
	
	//잔고 설정
	public void setBalance(int amount){ this.balance = amount;}
}
