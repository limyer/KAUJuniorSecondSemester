package Bank;


//정기예금 계좌
public class SavingsAccount extends Account{
	private final int maturity = 10; // 만기는 개설일로부터 10년
	private final double interestRate = 0.0215; // 연 2.15% 복리이자
	private boolean isMatured = false;

	public SavingsAccount(){
		balance=0;
	}
	
	public SavingsAccount(int initialBalance){
		balance=initialBalance;
	}
	
	//예금 계좌에서는 만기 전까지 출금 불가능
	public boolean debit(int amount){
		int startYear = ATMSystem.startYear;
		int currentYear = ATMSystem.currentYear;
		// 만기가 넘었을 시
		if ((currentYear - startYear) >= maturity) {
			System.out.println("만기가 지나 출금이 가능하지만 출금 후 이자는 적용되지 않습니다.");
			this.isMatured = true;
			return super.debit(amount);
		}
		else {
			System.out.println("예금 계좌에서는 출금할 수 없습니다.");
			return false;
		}
	}
	
	// 이율 적용
	public void applyInterest(int years) {
		if (!isMatured) {
			System.out.println("이율이 적용되었습니다.");
			double previousBalance = balance * 10000;
			double afterBalance = previousBalance * Math.pow((1 + interestRate), (double)years);
			System.out.println("이전 금액: " + previousBalance);
			balance = (int)Math.ceil(afterBalance/10000);
			System.out.println("현재 금액: " + afterBalance);
		}
	}
}