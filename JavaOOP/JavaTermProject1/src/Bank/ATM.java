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

		System.out.println("============ATM ����==============");
		
		System.out.println("�ȳ��ϼ���! ����ȣ�� �Է����ּ���");
		
		do {
			inputNumber=input.nextInt();
			if(!sys.findCustomer(inputNumber)) {
				System.out.println("����ȣ�� �ٽ� �Է����ּ���");
			}
			else {
				break;
			}
		} while(true);
		
		customer = sys.getSelectedCustomer();
		
		System.out.printf("�ݰ����ϴ�! %s ����!\n", customer.getCustomerName());
		System.out.printf("������ ���� %d���� ���°� �ֽ��ϴ�.", customer.howManyAccount());
		
		if (customer.howManyAccount() == 2) {
			System.out.println("� ���¸� �����Ͻðڽ��ϱ�?\n1. ����� 2. ����");
			do {
				inputNumber=input.nextInt();
				if(inputNumber != 1 && inputNumber != 2) {
					System.out.println("�ٽ� �Է����ּ���");
				}
				else {
					if(inputNumber == 1) {
						System.out.println("����� ���¸� �����ϼ̽��ϴ�.");
						sys.setWhichAccount(1);
					}
					else {
						System.out.println("���� ���¸� �����ϼ̽��ϴ�.");
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
			System.out.println("���Ͻô� �۾��� �������ּ���.");
			System.out.println("1.��ȸ   2.�Ա�   3.���   4.ATM ��ȸ   5.����");
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
				System.out.println("ATM�� �����մϴ�");
				sys.writeTransactionRecord();
				loopCheck=false;	
				break;
			default: System.out.println("�߸��� �Է��Դϴ�.\n");
			}
		}
		input.close();
	}

}
