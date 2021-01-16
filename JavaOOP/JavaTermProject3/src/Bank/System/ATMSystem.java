package Bank.System;

import Bank.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;


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


	// ATMGUI frame 생성
	private JFrame ATMGUI = new JFrame();
	private int checkAction = 0;
	//생성자, 싱글톤 패턴 적용
	public ATMSystem(){
		this.database = ATMDatabase.getInstance(); // 하나만 존재

		//GUI 크기 설정
		ATMGUI.setTitle("ATM\n");
		ATMGUI.setSize(540,600);
		ATMGUI.setLayout(null);
		ATMGUI.setLocationRelativeTo(null);

		totalAmount = 10000 * tenThousandBills + 50000 * fiftyThousandBills; //ATM 기기에 지폐 충전
		//cls();
		System.out.println("============ATM 가동==============");
		atmMainLoop();
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
		this.getCustomerPin(); // 고객 PIN 확인
		if (database.getIsManagerOnline()) {
			// 트랜잭션 추가
			record=String.format("%d. 매니저 작업 개시", recordIndex++);
			database.addTransaction(record);
			// 매니저 루프 개시
			this.atmManagerLoop(); // 매니저만 ATM 종료 가능
		}
		// PIN이 고객일 경우
		else {
			// 고객 루프 개시
			this.atmCustomerLoop();
		}
	}
	
	// 고객 PIN 확인
	private void getCustomerPin() {
		String input = JOptionPane.showInputDialog(null,"안녕하세요! 고객번호를 입력해주세요","ATM",JOptionPane.QUESTION_MESSAGE);
		int inputNumber = Integer.parseInt(input);
		//System.out.println("안녕하세요! 고객번호를 입력해주세요");
		if (input != null){
			do {
				//int inputNumber=this.input.nextInt();
				if(!database.findCustomer(inputNumber)) {
					//System.out.println("고객번호를 다시 입력해주세요");
					input = JOptionPane.showInputDialog("고객번호를 다시 입력해주세요");
					inputNumber = Integer.parseInt(input);
				}else{
					break;
				}
			}while(true);
		}
		//If cancel button is pressed
	}
	// 계좌 선택 루프
	private void accountSelection() {
		int inputNumber; //번호 입력 변수
		String customerName = this.customer.getCustomerName();
		String Tittle = "반갑습니다! " + this.customer.getCustomerName() + " 고객님!";

		// 트랜잭션 추가
		record=String.format("%d. %s 고객님 이용 시작", recordIndex++, customerName);
		database.addTransaction(record);

		if (customer.howManyAccount() == 2) { //계좌가 2개일 때 선택
			String[] str = {"입출금", "예금"};
			inputNumber = JOptionPane.showOptionDialog(null,Tittle+"\n"+"고객님은 현재 2개의 계좌가 있습니다.",
					"ATM",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, str, str[1] );
			do {
				if(inputNumber == 0){
					this.whichAccount = 1;
					JOptionPane.showMessageDialog(null,"입출금 계좌를 선택하셨습니다.","ATM",JOptionPane.INFORMATION_MESSAGE);
					break;
				}else if(inputNumber ==1){
					this.whichAccount = 2;
					JOptionPane.showMessageDialog(null,"예금 계좌를 선택하셨습니다.","ATM",JOptionPane.INFORMATION_MESSAGE);
					break;
				}
			} while(true);
		}
		// 계좌가 한개일때
		else {
			JOptionPane.showMessageDialog(null,Tittle+"\n"+"현재 고객님은 입출금계좌만 가지고 계십니다.","ATM",JOptionPane.INFORMATION_MESSAGE);
			this.whichAccount = 1; // 입출금 고정
		}
		// 트랜잭션 추가
		String accountType = (this.whichAccount == 1) ? "입출금" : "예금";
		record=String.format("%d. %s 계좌 선택", recordIndex++, accountType);
		database.addTransaction(record);
	}
	// 고객 루프
	private void atmCustomerLoop() {
		this.customer = database.getSelectedCustomer(); //고객 변수에 반환된 고객정보 반환
		this.accountSelection(); // 어떤 계좌로 작업할지 설정

		// JLabel
		JLabel SelectionLabel = new JLabel("원하시는 작업을 선택해 주세요\n");

		// BUtton 생성
		JButton AccountShowButton = new JButton("조회");
		JButton AccountDepositButton = new JButton("입금");
		JButton AccountWithdrawlButton = new JButton("출금");
		JButton AccountGoodbyeButton = new JButton("종료");
		AccountShowButton.setFont(new Font("맑은고딕", Font.BOLD, 30));
		AccountDepositButton.setFont(new Font("맑은고딕", Font.BOLD, 30));
		AccountWithdrawlButton.setFont(new Font("맑은고딕", Font.BOLD, 30));
		AccountGoodbyeButton.setFont(new Font("맑은고딕", Font.BOLD, 30));

		// JLabel 추가
		SelectionLabel.setLocation(0,0);
		ATMGUI.getContentPane().add(SelectionLabel);

		//container로 버튼 추가
		JPanel ATMActionContainer = new JPanel();
		ATMActionContainer.setLayout(new GridLayout(2, 2));
		ATMActionContainer.add(AccountShowButton);
		ATMActionContainer.add(AccountDepositButton);
		ATMActionContainer.add(AccountWithdrawlButton);
		ATMActionContainer.add(AccountGoodbyeButton);
		ATMActionContainer.setLocation(10,50);
		ATMActionContainer.setSize(500,300);

		// GUI 에 button container 추가
		ATMGUI.getContentPane().add(ATMActionContainer);
		ATMGUI.getContentPane().revalidate();

		// event listener 설정
		ShowButtonListener showButtonListener = new ShowButtonListener();
		AccountShowButton.addActionListener(showButtonListener);

		DepositButtonListener depositButtonListener = new DepositButtonListener();
		AccountDepositButton.addActionListener(depositButtonListener);

		WithdrawlButtonListener withdrawlButtonListener = new WithdrawlButtonListener();
		AccountWithdrawlButton.addActionListener(withdrawlButtonListener);

		EndButtonListener endButtonListener = new EndButtonListener();
		AccountGoodbyeButton.addActionListener(endButtonListener);

		ATMGUI.setVisible(true);

	}
	
	// 매니저 루프
	private void atmManagerLoop() {
		// JLabel
		JLabel SelectionLabel = new JLabel("원하시는 작업을 선택해 주세요\n");

		// Button 추가
		JButton ShowATMButton = new JButton("ATM 현황 조회");
		JButton ShowTransactionButton = new JButton("오늘의 거래 출력");
		JButton SetYearsButton = new JButton("연도 설정");
		JButton LogoutButton = new JButton("로그아웃");
		JButton GoodByeButton = new JButton("시스템종료");
		ShowATMButton.setFont(new Font("맑은고딕", Font.BOLD, 20));
		ShowTransactionButton.setFont(new Font("맑은고딕", Font.BOLD, 20));
		SetYearsButton.setFont(new Font("맑은고딕", Font.BOLD, 20));
		LogoutButton.setFont(new Font("맑은고딕", Font.BOLD, 20));
		GoodByeButton.setFont(new Font("맑은고딕", Font.BOLD, 20));

		//container로 버튼 추가
		JPanel ManagerActionContainer = new JPanel();
		ManagerActionContainer.setLayout(new FlowLayout());
		ManagerActionContainer.add(ShowATMButton);
		ManagerActionContainer.add(ShowTransactionButton);
		ManagerActionContainer.add(SetYearsButton);
		ManagerActionContainer.add(LogoutButton);
		ManagerActionContainer.add(GoodByeButton);
		ManagerActionContainer.setLocation(10,50);
		ManagerActionContainer.setSize(500,300);

		// GUI 에 button container 추가
		ATMGUI.getContentPane().add(ManagerActionContainer);

		// JLabel 추가
		SelectionLabel.setLocation(50,10);
		ATMGUI.getContentPane().add(SelectionLabel);
		ATMGUI.getContentPane().revalidate();

		//event listener 추가
		ShowATMButtonListener showATMButtonListener = new ShowATMButtonListener();
		ShowATMButton.addActionListener(showATMButtonListener);

		ShowTransactionButtonListener showTransactionButtonListener = new ShowTransactionButtonListener();
		ShowTransactionButton.addActionListener(showTransactionButtonListener);

		SetYearsButtonListener setYearsButtonListener = new SetYearsButtonListener();
		SetYearsButton.addActionListener(setYearsButtonListener);

		LogoutButtonListener logoutButtonListener = new LogoutButtonListener();
		LogoutButton.addActionListener(logoutButtonListener);

		GoodByeButtonListener goodByeButtonListener = new GoodByeButtonListener();
		GoodByeButton.addActionListener(goodByeButtonListener);

		// managerloopTF 가 0일때 멈춤
		ATMGUI.setVisible(true);
	}


	//계좌 조회
	public void Show() {
		String accountType = (this.whichAccount == 1) ? "입출금" : "예금";
		Account account = (this.whichAccount == 1) ? customer.getChecking() : customer.getSavings();
		
		String showString = String.format("현재 %s 계좌 잔액: %d 원\n",
				accountType, account.getBalance() * 10000) ;
		
		record=String.format("%d. 잔고조회, %s", recordIndex++, showString);
		//database.addTransaction(record);
		JOptionPane.showMessageDialog(null,showString,"ATM",JOptionPane.INFORMATION_MESSAGE);
		//System.out.print(showString);
	}


	//입금
	public void Deposit() {
		checkAction = 1;
		new ATMCustomerGUI(this);
	}

	public void deposit(int fifty, int ten) {
		Account account = (this.whichAccount == 1) ? customer.getChecking() : customer.getSavings();
		int amount = ten * 10000 + fifty * 50000;
		int check = JOptionPane.showConfirmDialog(null,"총 "+amount+ "원 맞습니까?\n","ATM",JOptionPane.YES_NO_CANCEL_OPTION);
		if (check == JOptionPane.YES_OPTION) {
			account.credit(amount/10000);
			this.tenThousandBills += ten;
			this.fiftyThousandBills += fifty;
			JOptionPane.showMessageDialog(null,"입금이 완료되었습니다.","ATM",JOptionPane.INFORMATION_MESSAGE);
			//System.out.println("입금이 완료되었습니다.");
			record=String.format("%d. %d 원 입금", recordIndex++, amount);
			database.addTransaction(record);
			return;
		}
		JOptionPane.showMessageDialog(null,"거래가 취소되었습니다.","ATM",JOptionPane.INFORMATION_MESSAGE);
		//System.out.println("거래가 취소되었습니다.");
	}
	
	//출금
	public void Withdrawl() {
		checkAction = 2;
		new ATMCustomerGUI(this);
	}
	public void wihtdrawl(int fifty,int ten){
		Account account = (this.whichAccount == 1) ? customer.getChecking() : customer.getSavings();
		int amount = ten * 10000 + fifty * 50000;

		int check = JOptionPane.showConfirmDialog(null,"총 "+amount+ "원 맞습니까?\n","ATM",JOptionPane.YES_NO_OPTION);
		if (check == JOptionPane.YES_OPTION) {
			if (account.debit(amount/10000)) {
				if (this.tenThousandBills > ten
						&& this.fiftyThousandBills > fifty) {
					this.tenThousandBills -= ten;
					this.fiftyThousandBills -= fifty;

					//System.out.println("출금이 완료되었습니다.");
					JOptionPane.showMessageDialog(null,"출금이 완료되었습니다.","ATM",JOptionPane.INFORMATION_MESSAGE);
					record=String.format("%d. %d 원 출금", recordIndex++, amount);
					database.addTransaction(record);
					return;
				}
				else {
					JOptionPane.showMessageDialog(null,"원하시는 권종이 부족합니다.","ATM",JOptionPane.INFORMATION_MESSAGE);
					//System.out.println("원하시는 권종이 부족합니다.");
				}
			}
		}
		JOptionPane.showMessageDialog(null,"거래가 취소되었습니다.","ATM",JOptionPane.INFORMATION_MESSAGE);
		//		System.out.println("거래가 취소되었습니다.");
	}
	
	//ATM 조회
	public void ShowATM() {
		totalAmount = 10000 * tenThousandBills + 50000 * fiftyThousandBills;
		JOptionPane.showMessageDialog(null,
				"현재 ATM 잔액:"+totalAmount+ "원\n"+"만원권: "+tenThousandBills+"장\n"+"오만원권: "+fiftyThousandBills+"장\n","ATM",
				JOptionPane.INFORMATION_MESSAGE);

		String showString = String.format("현재 ATM 잔액: %d 원\n만원권: %d 장\n오만원권: %d 장\n"
				,totalAmount,tenThousandBills,fiftyThousandBills);
		
		record=String.format("%d. ATM조회, %s", recordIndex++, showString);
		database.addTransaction(record);
		
		System.out.print(showString);
	}
	
	//하루의 ATM 이용 내역(Transaction)을 출력하고 하루가 지나는 작업(초기화)
	public void ShowTransaction() {
		new ShowTransactionGUI(this);
		System.out.println("오늘의 ATM 이용 내역입니다.");
		database.printTransaction();
		//new ShowTransactionGUI(this);
		System.out.println("하루가 초기화됩니다.");
		this.tenThousandBills = this.tenThousandBillsReset;
		this.fiftyThousandBills = this.fiftyThousandBillsReset;
		this.recordIndex = 1;
		database.resetTransaction();
	}
	
	// 연도 설정
	public void SetYear() {
		//System.out.println("몇 년후로 설정하시겠습니까?");
		//System.out.println("현재 연도: " + currentYear + "년");
		String yearinput=JOptionPane.showInputDialog(null,"현재 연도: " + currentYear + "년\n"+"몇 년후로 설정하시겠습니까?");
		int years=Integer.parseInt(yearinput);
		currentYear += years;
		database.yearsPassed(years);
		JOptionPane.showMessageDialog(null, years + "년이 경과했습니다.\n"+"현재 연도: " + currentYear + "년");
		//System.out.println(years + "년이 경과했습니다.");
		//System.out.println("현재 연도: " + currentYear + "년");
	}

	public int getCheckAction(){
		return checkAction;
	}
	// customerGUI listener 생성
	//계좌조회 버튼 eventlistener
	class ShowButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Show();
			System.out.println("Search Button Clicked");
		}
	}
	// 입금 버튼 이벤트 리스너
	class DepositButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// ATMSystem.Deposit();
			Deposit();
			System.out.println("Deposit Button Clicked");
		}
	}
	// 출금 버튼 이벤트 리스너
	class WithdrawlButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// ATMSystem.Withdrwal();
			Withdrawl();
			System.out.println("Withdrwal Button Clicked");
		}
	}
	// user 계좌 접속 종료 버튼 이벤트 리스너
	class EndButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("end Button Clicked");
			ATMGUI.setVisible(false);
			ATMGUI.getContentPane().removeAll();
			atmMainLoop();
		}
	}

	// managerButtonListener

	// ATM 잔액 조회 이벤트 리스너
	class ShowATMButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			ShowATM();
		}
	}
	// ATM 이용내역 이벤트 리스너
	class ShowTransactionButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			ShowTransaction();
		}
	}
	// 연도 설정 이벤트 리스너
	class SetYearsButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			SetYear();
		}
	}
	// 매니저 모드 종료 이벤트 리스너
	class LogoutButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			database.setIsManagerOnline(false);
			ATMGUI.setVisible(false);
			ATMGUI.getContentPane().removeAll();
			atmMainLoop();
		}
	}
	// ATM system 종료 이벤트 리스너
	class GoodByeButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
}
