// 2018125084 임예랑
// 2020-2 알고리즘 해석 및 설계 이인복 교수님
// 201121 12주차 프로그래밍 과제 2
// FailureCheck.java

import java.util.Scanner;

public class FailureCheck {
	int[] F;
	
	private FailureCheck(){
		Scanner input = new Scanner(System.in);
		String inputString = "";
		int length = 0;
		
		while(true) {
			inputString = input.nextLine();
			length = inputString.length();
			if(!inputString.isEmpty() && length < 100) {
				break;
			}
			else if (inputString.isEmpty()){
				System.out.println("Input String cannot be empty");
			}
			else if (length > 100) {
				System.out.println("Input String cannot be more than 100 characters");
			}
		}
		
		F = new int[length];
		
		failure(length, inputString, F);
		
		for(int i = 0; i < length; i++) {
			if (i != (length-1)) System.out.printf("%d ", F[i]);
			else System.out.print(F[i]);
		}
		
		input.close();
	}
	
	void failure(int n, String P, int[] F) {
	    int begin = 1, m = 0;

	    while(begin + m < n) {
	        if(P.charAt(begin+m) == P.charAt(m)) {
	            m++;
	            F[begin + m - 1] = m;
	        }
	        else {
	            if(m == 0)
	                begin++;
	            else {
	                begin += (m - F[m - 1]);
	                m = F[m - 1];
	            }
	        }
	    }
	}

	
	public static void main(String args[]) {
		FailureCheck failcheck = new FailureCheck();

	}
	
}