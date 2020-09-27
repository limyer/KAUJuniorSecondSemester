package Bank;

import java.util.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ATMSystem {
	private ArrayList<Customer> customerList = new ArrayList<Customer>();
	private int[] customerNumberList = {12345, 23456, 34567};
	private int customerIndex = -1;
	private int whichAccount = -1;
	
	private int totalAmount;
	private int	tenThousandBills=1000;
	private int fiftyThousandBills=200;
	
	public boolean IsMinus=true;
	
	private ArrayList<String> transaction = new ArrayList<String>();
	private String record;
	private int index=1;

	public ATMSystem(){
		// 고객정보는 일단 하드코딩
		Customer customer1 = new Customer(customerNumberList[0], "임예랑", true);
		customer1.setCheckingBalance(30);
		customer1.setSavingsBalance(20);

		Customer customer2 = new Customer(customerNumberList[1], "조세희", false);
		customer2.setCheckingBalance(50);

		Customer customer3 = new Customer(customerNumberList[2], "박한솔", false);
		customer3.setCheckingBalance(40);
		
		customerList.add(customer1);
		customerList.add(customer2);
		customerList.add(customer3);
		
		totalAmount = 10000 * tenThousandBills + 50000 * fiftyThousandBills;
		
	}
	
	public boolean findCustomer(int inputNumber) {
	    for(int i=0; i<customerNumberList.length; i++) {
	         if(customerNumberList[i] == inputNumber) {
	        	 customerIndex = i;
	        	 return true;
	         }        
	    }
	    System.out.println("해당 고객번호를 찾지 못했습니다");
	    return false;
	}
	
	public Customer getSelectedCustomer() {
		if(customerIndex == -1) {
			return null;
		}
		else {
			return customerList.get(customerIndex);
		}
	}
	
	public void setWhichAccount(int option){
		this.whichAccount = option;
	}

	public void ShowATM() {
		String showString = String.format("현재 ATM 잔액: %d 원\n만원권: %d 장\n오만원권: %d 장\n"
				,totalAmount,tenThousandBills,fiftyThousandBills);
		
		record=String.format("%d. ATM조회, %s", index++, showString);
		transaction.add(record);
		
		System.out.print(showString);
	}
	
	public void Show() {
		String accountType = (this.whichAccount == 1) ? "입출금" : "예금";
		Customer customer = customerList.get(customerIndex);
		Account account = (this.whichAccount == 1) ? customer.getChecking() : customer.getSavings();
		
		String showString = String.format("현재 %s 계좌 잔액: %d 원\n",
				accountType, account.getBalance() * 10000) ;
		
		record=String.format("%d. 잔고조회, %s", index++, showString);
		transaction.add(record);
		
		System.out.print(showString);
	}

	public void Deposit() {
		Customer customer = customerList.get(customerIndex);
		Account account = (this.whichAccount == 1) ? customer.getChecking() : customer.getSavings();
		Scanner input=new Scanner(System.in);
		int tenNum=0;
		int fiftyNum=0;
		int amount = 0;
		
		System.out.println("입금을 요청하셨습니다. 입금할 권종의 장수를 각각 입력해주세요.");
		System.out.print("1만원권(장):");
		tenNum=input.nextInt();
		System.out.print("5만원권(장):");
		fiftyNum=input.nextInt();
		amount = tenNum * 10000 + fiftyNum * 50000;
		System.out.printf("총 %d 원 맞습니까? (Y / N)\n", amount);
		char choiceInput = input.next().charAt(0);
		if (choiceInput == 'Y') {
			account.credit(amount/10000);
			this.tenThousandBills += tenNum;
			this.fiftyThousandBills += fiftyNum;
			
			System.out.println("입금이 완료되었습니다.");
			
			record=String.format("%d. %d 원 입금", index++, amount);
			transaction.add(record);
			return;
		}
		System.out.println("거래가 취소되었습니다.");
	}
	
	public void Withdrawl() {
		Customer customer = customerList.get(customerIndex);
		Account account = (this.whichAccount == 1) ? customer.getChecking() : customer.getSavings();
		Scanner input=new Scanner(System.in);
		int tenNum=0;
		int fiftyNum=0;
		int amount = 0;
		
		System.out.println("출금을 요청하셨습니다. 출금할 권종의 장수를 각각 입력해주세요.");
		System.out.print("1만원권(장):");
		tenNum=input.nextInt();
		System.out.print("5만원권(장):");
		fiftyNum=input.nextInt();
		amount = tenNum * 10000 + fiftyNum * 50000;
		System.out.printf("총 %d 원 맞습니까? (Y / N)\n", amount);
		char choiceInput = input.next().charAt(0);
		if (choiceInput == 'Y') {
			if (account.debit(amount/10000)) {
				if (this.tenThousandBills > tenNum
						&& this.fiftyThousandBills > fiftyNum) {
					this.tenThousandBills -= tenNum;
					this.fiftyThousandBills -= fiftyNum;
					
					System.out.println("출금이 완료되었습니다.");

					record=String.format("%d. %d 원 출금", index++, amount);
					transaction.add(record);
					return;
				}
				else {
					System.out.println("원하시는 권종이 부족합니다.");
				}
			}
		}
		System.out.println("거래가 취소되었습니다.");
	}	
	
	public void writeTransactionRecord(){
        PrintWriter pw;
		try {
			pw = new PrintWriter("ATMTransactionRecord.txt");
		} catch (FileNotFoundException e) {
			return;
		}
		for(String i: transaction) {
			pw.println(i);
		}	
        pw.close();
	}
}
