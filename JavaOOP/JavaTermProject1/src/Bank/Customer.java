package Bank;

public class Customer{
	private int customerNumber;
	private String customerName;
    private CheckingAccount checking;
    private SavingsAccount savings;
    
    public Customer (){
    	this.customerNumber = 0;
    	this.customerName = null;
    	this.checking = null;
    	this.savings = null;
    }
    
    public Customer (int num, String name, boolean hasSavings ){
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
    
    public int getCustomerNumber() {
    	return this.customerNumber;
    }
    
    public String getCustomerName() {
    	return this.customerName;
    }
    
    public int howManyAccount() {
    	if (this.savings != null) {
    		return 2;
    	}
    	else {
    		return 1;
    	}
    }
    
    public Account getChecking() {
    	return this.checking;
    }
    
    public Account getSavings() {
    	return this.savings;
    }
    
    public void setCheckingBalance(int amount) {
    	this.checking.setBalance(amount);
    }
    
    public void setSavingsBalance(int amount) {
    	if (this.savings != null) {
        	this.savings.setBalance(amount);
    	}
    }
}
