// AccountTest.java

import java.util.Scanner;

public class AccountTest{
	public static void main(String[] args) {
		Account account1 = new Account(50.00);
		Account account2 = new Account(0.0);
		
		System.out.printf("account1 balance: $%.2f\n", account1.getBalance());
		System.out.printf("account2 balance: $%.2f\n\n", account2.getBalance());
		
		
		Scanner input = new Scanner(System.in);
		double debitAmount;
		
		System.out.print("Enter debit amount for account1: ");
		debitAmount = input.nextDouble();
		
		System.out.printf("\nsubtracting %.2f to account1 balance\n\n", debitAmount);
		System.out.printf("%s", account1.debit(debitAmount));
		
		System.out.printf("account1 balance: $%.2f\n", account1.getBalance());
		System.out.printf("account2 balance: $%.2f\n\n", account2.getBalance());
		
		System.out.printf("Enter deposit amount for account2: ");
		debitAmount = input.nextDouble();
		
		System.out.printf("\nsubtracting %.2f to account2 balance\n\n", debitAmount);
		System.out.printf("%s", account2.debit(debitAmount));
		
		System.out.printf("account1 balance: $%.2f\n", account1.getBalance());
		System.out.printf("account2 balance: $%.2f\n\n", account2.getBalance());
		
	}
}