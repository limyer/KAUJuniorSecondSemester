package Bank;

import java.util.*;

public class ATMSystem {
	private static ATMSystem atmsystem = null; // ATM System은 하나만 존재
	private final int tenThousandBillsReset = 1000; // 1만원권 초기화 상수
	private final int fiftyThousandBillsReset = 200; // 1만원권 초기화 상수
	public static final int startYear = 2020; // 시작 연도

	Scanner input=new Scanner(System.in);
	
	private int totalAmount;//ATM 잔고 총액
	private int	tenThousandBills=tenThousandBillsReset; //만원권 개수
	private int fiftyThousandBills=fiftyThousandBillsReset; //5만원권 개수
	
	private String record; //트랜잭션
	private int recordIndex=1; //트랜잭션 목차
	Customer customer; // 현재 이용 고객
	private int whichAccount = -1; //계좌 이용 종류 변수 		//1.입출금 계좌 2.정기예금 계좌
	
	private ATMDatabase database; // 데이터베이스
	public static int currentYear = startYear; // 현재 연도
	
	//생성자, 싱글톤 패턴 적용 
	private ATMSystem(){
		this.database = ATMDatabase.getInstance(); // 하나만 존재
		
		totalAmount = 10000 * tenThousandBills + 50000 * fiftyThousandBills; //ATM 기기에 지폐 충전
		cls();
		System.out.println("============ATM 가동==============");
	}
	
	// ATM 시스템을 내보내는 함수 (없을경우 생성, 있을경우 리턴)
	public static ATMSystem getInstance() {
		if(atmsystem == null) {
			atmsystem = new ATMSystem();
		}
		return atmsystem;
	}
	
	// ATM 메인 루프
	public void atmMainLoop() {
		boolean loopCheck=true; //실행 루프
		
		while(loopCheck) {
			this.getCustomerPin(); // 고객 PIN 확인
			
			// PIN이 매니저일 경우
			if (database.getIsManagerOnline()) {
				// 트랜잭션 추가
				record=String.format("%d. 매니저 작업 개시", recordIndex++);
				database.addTransaction(record);
				// 매니저 루프 개시
				loopCheck = this.atmManagerLoop(); // 매니저만 ATM 종료 가능
			}
			// PIN이 고객일 경우
			else {
				// 고객 루프 개시
				this.atmCustomerLoop();
			}
		}
		// ATM 종료됨
		input.close();
	}
	
	// 고객 PIN 확인
	private void getCustomerPin() {
		System.out.println("안녕하세요! 고객번호를 입력해주세요");
		do {
			int inputNumber=this.input.nextInt();
			cls();
			if(!database.findCustomer(inputNumber)) {
				System.out.println("고객번호를 다시 입력해주세요");
			}
			else {
				break;
			}
		} while(true);
	}
	
	// 고객 루프
	private void atmCustomerLoop() {
		int choice; //작업 case 입력 변수
		
		this.customer = database.getSelectedCustomer(); //고객 변수에 반환된 고객정보 반환
		this.accountSelection(); // 어떤 계좌로 작업할지 설정

		// 고객 작업 시작
		while(true) {
			System.out.println("원하시는 작업을 선택해주세요.");
			System.out.println("1.조회   2.입금   3.출금   4.거래종료");
			choice=this.input.nextInt();
			cls();
			switch (choice) {
			case 1:
				Show();
				break;
			case 2:
				Deposit();
				break;
			case 3:
				Withdrawl();
				break;
			case 4:	
				System.out.println("거래를 종료합니다");
				return;
			default: System.out.println("잘못된 입력입니다.\n");
			}
		}
	}
	
	// 매니저 루프
	private boolean atmManagerLoop() {
		int choice; //작업 case 입력 변수
		
		//매니저 작업 시작
		while(true) {
			System.out.println("원하시는 작업을 선택해주세요.");
			System.out.println("1.ATM 현황 조회   2.오늘의 거래 출력   3.연도 설정   4.로그아웃   5.시스템종료");
			choice=this.input.nextInt();
			cls();
			switch (choice) {
			case 1:
				ShowATM();
				break;
			case 2:
				ShowTransaction();
				break;
			case 3:
				// 거래를 출력하고 연도를 설정함
				ShowTransaction();
				SetYear();
				// 로그아웃으로 이어짐
			case 4:	
				System.out.println("매니저 작업을 종료합니다.");
				database.setIsManagerOnline(false);
				return true;
			case 5:
				System.out.println("ATM 시스템이 종료됩니다.");
				return false; // false 반환시 ATM 메인루프 종료
			default: System.out.println("잘못된 입력입니다.\n");
			}
		}
	}
	
	
	// 계좌 선택 루프
	private void accountSelection() {
		int inputNumber; //번호 입력 변수
		String customerName = this.customer.getCustomerName();
		
		System.out.printf("반갑습니다! %s 고객님!\n", customerName);

		// 트랜잭션 추가
		record=String.format("%d. %s 고객님 이용 시작", recordIndex++, customerName);
		database.addTransaction(record);
		
		System.out.printf("고객님은 현재 %d개의 계좌가 있습니다.", this.customer.howManyAccount());
		
		if (customer.howManyAccount() == 2) { //계좌가 2개일 때 선택
			System.out.println("어떤 계좌를 선택하시겠습니까?\n1. 입출금 2. 예금");
			do {
				inputNumber=this.input.nextInt();
				cls();
				if(inputNumber != 1 && inputNumber != 2) {
					System.out.println("다시 입력해주세요");
				}
				else {
					if(inputNumber == 1) {
						System.out.println("입출금 계좌를 선택하셨습니다.");
					}
					else {
						System.out.println("예금 계좌를 선택하셨습니다.");
					}
					this.whichAccount = inputNumber;
					break;
				}
			} while(true);
		}
		// 계좌가 한개일때
		else {
			this.whichAccount = 1; // 입출금 고정
		}
		// 트랜잭션 추가
		String accountType = (this.whichAccount == 1) ? "입출금" : "예금";
		record=String.format("%d. %s 계좌 선택", recordIndex++, accountType);
		database.addTransaction(record);
		
	}
	
	//계좌 조회
	private void Show() {
		String accountType = (this.whichAccount == 1) ? "입출금" : "예금";
		Account account = (this.whichAccount == 1) ? customer.getChecking() : customer.getSavings();
		
		String showString = String.format("현재 %s 계좌 잔액: %d 원\n",
				accountType, account.getBalance() * 10000) ;
		
		record=String.format("%d. 잔고조회, %s", recordIndex++, showString);
		database.addTransaction(record);
		
		System.out.print(showString);
	}

	//입금
	private void Deposit() {
		Account account = (this.whichAccount == 1) ? customer.getChecking() : customer.getSavings();
		int tenNum=0;
		int fiftyNum=0;
		int amount = 0;
		
		System.out.println("입금을 요청하셨습니다. 입금할 권종의 장수를 각각 입력해주세요.");
		System.out.print("1만원권(장): ");
		tenNum=input.nextInt();
		System.out.print("5만원권(장): ");
		fiftyNum=input.nextInt();
		amount = tenNum * 10000 + fiftyNum * 50000;
		System.out.printf("총 %d 원 맞습니까? (Y / N)\n", amount);
		char choiceInput = input.next().charAt(0);
		cls();
		if (choiceInput == 'Y'||choiceInput=='y') {
			account.credit(amount/10000);
			this.tenThousandBills += tenNum;
			this.fiftyThousandBills += fiftyNum;
			
			System.out.println("입금이 완료되었습니다.");
			
			record=String.format("%d. %d 원 입금", recordIndex++, amount);
			database.addTransaction(record);
			return;
		}
		System.out.println("거래가 취소되었습니다.");
	}
	
	//출금
	private void Withdrawl() {
		Account account = (this.whichAccount == 1) ? customer.getChecking() : customer.getSavings();
		int tenNum=0;
		int fiftyNum=0;
		int amount = 0;
		
		System.out.println("출금을 요청하셨습니다. 출금할 권종의 장수를 각각 입력해주세요.");
		System.out.print("1만원권(장): ");
		tenNum=input.nextInt();
		System.out.print("5만원권(장): ");
		fiftyNum=input.nextInt();
		amount = tenNum * 10000 + fiftyNum * 50000;
		System.out.printf("총 %d 원 맞습니까? (Y / N)\n", amount);
		char choiceInput = input.next().charAt(0);
		cls();
		if (choiceInput == 'Y' || choiceInput=='y') {
			if (account.debit(amount/10000)) {
				if (this.tenThousandBills > tenNum
						&& this.fiftyThousandBills > fiftyNum) {
					this.tenThousandBills -= tenNum;
					this.fiftyThousandBills -= fiftyNum;
					
					System.out.println("출금이 완료되었습니다.");

					record=String.format("%d. %d 원 출금", recordIndex++, amount);
					database.addTransaction(record);
					return;
				}
				else {
					System.out.println("원하시는 권종이 부족합니다.");
				}
			}
		}
		System.out.println("거래가 취소되었습니다.");
	}	
	
	//ATM 조회
	private void ShowATM() {
		totalAmount = 10000 * tenThousandBills + 50000 * fiftyThousandBills;
		String showString = String.format("현재 ATM 잔액: %d 원\n만원권: %d 장\n오만원권: %d 장\n"
				,totalAmount,tenThousandBills,fiftyThousandBills);
		
		record=String.format("%d. ATM조회, %s", recordIndex++, showString);
		database.addTransaction(record);
		
		System.out.print(showString);
	}
	
	//하루의 ATM 이용 내역(Transaction)을 출력하고 하루가 지나는 작업(초기화)
	private void ShowTransaction() {
		System.out.println("오늘의 ATM 이용 내역입니다.");
		database.printTransaction();
		System.out.println("하루가 초기화됩니다.");
		this.tenThousandBills = this.tenThousandBillsReset;
		this.fiftyThousandBills = this.fiftyThousandBillsReset;
		this.recordIndex = 1;
		database.resetTransaction();
	}
	
	// 연도 설정
	private void SetYear() {
		System.out.println("몇 년후로 설정하시겠습니까?");
		System.out.println("현재 연도: " + currentYear + "년");
		int years=this.input.nextInt();
		cls();
		currentYear += years;
		database.yearsPassed(years);
		System.out.println(years + "년이 경과했습니다.");
		System.out.println("현재 연도: " + currentYear + "년");
	}
	
	// 화면 넘기기
	private static void cls() {
		for (int i = 0; i < 80; i++)
		      System.out.println("");
	}
}
