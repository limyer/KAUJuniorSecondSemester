package Bank;
import java.util.Scanner;

public class ATM {
		
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		ATMSystem sys=new ATMSystem();
		Customer customer;
		
		boolean loopCheck=true;
		int inputNumber;
		int choice;

		System.out.println("============ATM 가동==============");
		
		System.out.println("안녕하세요! 고객번호를 입력해주세요");
		
		do {
			inputNumber=input.nextInt();
			if(!sys.findCustomer(inputNumber)) {
				System.out.println("고객번호를 다시 입력해주세요");
			}
			else {
				break;
			}
		} while(true);
		
		customer = sys.getSelectedCustomer();
		
		System.out.printf("반갑습니다! %s 고객님!\n", customer.getCustomerName());
		System.out.printf("고객님은 현재 %d개의 계좌가 있습니다.", customer.howManyAccount());
		
		if (customer.howManyAccount() == 2) {
			System.out.println("어떤 계좌를 선택하시겠습니까?\n1. 입출금 2. 예금");
			do {
				inputNumber=input.nextInt();
				if(inputNumber != 1 && inputNumber != 2) {
					System.out.println("다시 입력해주세요");
				}
				else {
					if(inputNumber == 1) {
						System.out.println("입출금 계좌를 선택하셨습니다.");
						sys.setWhichAccount(1);
					}
					else {
						System.out.println("예금 계좌를 선택하셨습니다.");
						sys.setWhichAccount(2);
					}
					break;
				}
			} while(true);
		}
		else {
			sys.setWhichAccount(1);
		}
		
		
		while(loopCheck) {
			System.out.println("원하시는 작업을 선택해주세요.");
			System.out.println("1.조회   2.입금   3.출금   4.ATM 조회   5.종료");
			choice=input.nextInt();
			switch (choice) {
			case 1:
				sys.Show();
				break;
			case 2:
				sys.Deposit();
				break;
			case 3:
				sys.Withdrawl();
				break;
			case 4: sys.ShowATM();	break;
			case 5:	
				System.out.println("ATM을 종료합니다");
				sys.writeTransactionRecord();
				loopCheck=false;	
				break;
			default: System.out.println("잘못된 입력입니다.\n");
			}
		}
		input.close();
	}

}
