// 2018125084 임예랑

import java.util.Scanner;
import java.util.InputMismatchException;

public class DieHard {
	private DieHard(){
		Scanner input = new Scanner(System.in);
		int input1 = 0;
		int input2 = 0;
		String inputChar = "";
		
		while(true) {
			try {
				System.out.print("분자를 입력하세요:");
				input1 = input.nextInt();
				System.out.print("분모를 입력하세요:");
				input2 = input.nextInt();
				
				float result = DieHard.divide(input1, input2);
				System.out.printf("결과: %.3f\n", result);
				
				System.out.print("종료할까요? (Y/N)");
				inputChar = input.next();
				if (inputChar.equalsIgnoreCase("Y")) {
					break;
				}
			}
			catch(InputMismatchException ime) {
				System.err.printf("\nException: %s", ime);
				System.out.println("Must enter integers\n");
				input.nextLine();
			}
			catch(ArithmeticException ae) {
				System.err.printf("\nException: %s\n Invalid Denominator", ae);
			}

		}
		
		
		input.close();
	}
	
	private static float divide(int a, int b) throws ArithmeticException {
		{
			return (float)(a/b);
		}
	}

	
	public static void main(String args[]) {
		DieHard diehard = new DieHard();

	}
	
}