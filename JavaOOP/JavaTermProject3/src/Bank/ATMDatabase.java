package Bank;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class ATMDatabase {
	private static ATMDatabase database = null; // ATM 데이터베이스는 하나만 존재
	
	// 초기정보용 상수
	private final int[] pinNumberList = {2345, 5432, 3451}; // 고객정보는 일단 하드코딩
	private final String[] customerNameList = {"임예랑", "조세희", "박한솔"};
	private final boolean[] accountTypeList = {true, false, false}; //정기예금 계좌 소유 여부
	private final int[] checkingBalanceList = {30, 50, 40}; // 입출금계좌
	private final int[] savingsBalanceList = {20, 0, 0}; // 없으면 그냥 0으로 설정
	private final int managerPIN = 9999; // 매니저 핀
	
	// 데이터베이스 자료
	private List<Customer> customerList = new ArrayList<>(); //고객 ArrayList
	private List<String> transaction = new ArrayList<>(); //트랜잭션 저장 ArrayList
	private boolean isManagerOnline = false; // 매니저 온라인 여부
	private int customerIndex = -1; //고객 번호 변수		//1.임예랑 2.조세희 3.박한솔
	

	
	// 생성자
	public ATMDatabase(){
		for (int i = 0; i < pinNumberList.length; i++) {
			// 상수값에 따라 초기 정보 설정
			Customer customer = new Customer(pinNumberList[i], customerNameList[i], accountTypeList[i]);
			customer.setCheckingBalance(checkingBalanceList[i]);
			customer.setSavingsBalance(savingsBalanceList[i]);
			customerList.add(customer);
		}
	}
	
	// 데이터베이스를 내보내는 함수
	public static ATMDatabase getInstance() {
		if(database == null) {
			database = new ATMDatabase();
		}
		return database;
	}
	
	//고객번호 확인
	public boolean findCustomer(int inputNumber) {
		// 매니저일 경우
		if (inputNumber == managerPIN) {
			isManagerOnline = true;
		    System.out.println("매니저님 환영합니다.");
			return true;
		}
		
		// 고객일 경우
		isManagerOnline = false;
	    for(int i=0; i< pinNumberList.length; i++) {
	         if(pinNumberList[i] == inputNumber) {
	        	 customerIndex = i;
	        	 return true;
	         }
	    }
	    // 못찾았을 경우
	    customerIndex = -1;
	    System.out.println("해당 고객번호를 찾지 못했습니다");
	    return false;
	}
	
	// 현재 인덱스로 고객 반환
	public Customer getSelectedCustomer() {
		if(customerIndex == -1) {
			return null;
		}
		else {
			return customerList.get(customerIndex);
		}
	}
	
	// 현재 트랜잭션 출력
	public void printTransaction() {
		for(String i: transaction) {
			System.out.println(i);
		}	
	}
	public List<String> getTransaction() {
		return transaction;
	}
	
	// 트랜잭션 리셋
	public void resetTransaction() {
		transaction.clear();
	}
	
	// 연도 설정 (고객이 예금을 가지고 있다면 이율 적용)
	public void yearsPassed(int years) {
		for (Customer customer : customerList) {
			customer.setSavingsInterest(years);
		}
	}
	
	// 매니저 온라인인지 여부 반환
	public boolean getIsManagerOnline() {
		return this.isManagerOnline;
	}
	
	// 매니저 온라인 설정
	public void setIsManagerOnline(boolean option) {
		this.isManagerOnline = option;
	}
	
	// 트랜잭션에 레코드 삽입
	public void addTransaction(String record) {
		this.transaction.add(record);
	}
}
