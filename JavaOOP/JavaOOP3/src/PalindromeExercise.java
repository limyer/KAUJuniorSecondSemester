// 2018125084 임예랑 3주차 실습 문제 1

import java.util.Scanner;

class PalindromeExercise{  
 public static void main(String args[]){  
	 Scanner input = new Scanner(System.in);
	 int remainder,sum = 0,number, inputNumber = 0, temp;    
	 
	 System.out.println("Welcome to Palindrom Tester");
	 while(true) {
		 System.out.println("Enter a number: ");  
		 inputNumber = input.nextInt();
		 if (inputNumber == -1) {
			   System.out.println("Bye!");
			   return;
		 }
		 temp = inputNumber;
		 number = inputNumber;
		 while(number > 0){    
			 remainder = number % 10;  
			 sum = (sum * 10) + remainder;    
			 number = number/10;    
		 }    
	  if(temp == sum)    {
		   System.out.println("Yes, it is a palindrome.");
	  }
	  else   {
		   System.out.println("Not a palindrome");    
	  }
	  sum = 0;
	 }
 }
}  