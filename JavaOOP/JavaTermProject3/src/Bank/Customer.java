package Bank;

public class Customer{
	private int customerNumber; //고객 번호
	private String customerName; //고객 이름
    private CheckingAccount checking; //입출금 계좌
    private SavingsAccount savings; //정기예금 계좌
    
    //생성자
    public Customer (){
    	this.customerNumber = 0;
    	this.customerName = null;
    	this.checking = null;
    	this.savings = null;
    }
    
    public Customer (int num, String name, boolean hasSavings ){//hasSaving, 정기예금 계좌 소지 여부
    	this.customerNumber = num;
    	this.customerName = name;
        this.checking = new CheckingAccount();
        if (hasSavings) {
        	this.savings = new SavingsAccount();
        }
        else {
        	this.savings = null;
        }
    }
    
    //고객번호 반환
    public int getCustomerNumber() {
    	return this.customerNumber;
    }
    
    //고객이름 반환
    public String getCustomerName() {
    	return this.customerName;
    }
    
    //계좌 개수 반환
    public int howManyAccount() {
    	if (this.savings != null) { //정기예금 계좌가 있는지 확인
    		return 2; //있다면 2개 반환
    	}
    	else {
    		return 1; //없으면 1개 반환
    	}
    }
    
    // 예금이 있을 경우 연도만큼 이율 적용
    public void setSavingsInterest(int years) {
    	if (this.savings != null) {
        	this.savings.applyInterest(years);
    	}
    }
    
    //입출금 계좌 반환
    public Account getChecking() {
    	return this.checking;
    }
    
    //정기예금 계좌 반환
    public Account getSavings() {
    	return this.savings;
    }
    
    //입출금 계좌 설정
    public void setCheckingBalance(int amount) {
    	this.checking.setBalance(amount);
    }
    
    //정기예금 계좌 설정
    public void setSavingsBalance(int amount) {
    	if (this.savings != null) {
        	this.savings.setBalance(amount);
    	}
    }
}
