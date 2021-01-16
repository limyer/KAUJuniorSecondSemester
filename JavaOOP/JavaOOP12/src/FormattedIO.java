import java.io.File;
import java.io.FileNotFoundException;
import java.lang.SecurityException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FormattedIO{
	private Formatter formatter;
	private Scanner scanner;
	private Scanner fileScanner;
	private Formatter displayWriter;
	
	public FormattedIO() {
		try {
			scanner = new Scanner(new File("Lab12data.txt"));
		}
		catch (FileNotFoundException fe){
			System.err.println("File Not Found");
		}
	}
	
	public void startWriter() {
		try {
			formatter = new Formatter("formatted1.txt");
		}
		catch(SecurityException se) {
			System.err.println("You do not have permission to this file");
			System.exit(1);
		}
		catch(FileNotFoundException fe) {
			System.err.println("Error Opening or creating file");
			System.exit(1);
		}

		System.out.println("Writer Started");
	}
	
	public void startReader() {
		try {
			fileScanner = new Scanner(new File("formatted1.txt"));
		}
		catch (FileNotFoundException fne){
			System.err.println("Error opening file");
			System.exit(1);
		}
	}

	public void readRecords() {
		try {
			displayWriter = new Formatter("FormattedDisplayed.txt");
		}
		catch(SecurityException se) {
			System.err.println("You do not have permission to this file");
			System.exit(1);
		}
		catch(FileNotFoundException fe) {
			System.err.println("Error Opening or creating file");
			System.exit(1);
		}
		
		try {
			while (fileScanner.hasNext()) {
				int acc = fileScanner.nextInt();
				String first= fileScanner.next();
				String last = fileScanner.next();
				double bal = fileScanner.nextDouble();
				String combined = String.format("Account: %-10d First Name: %-12s Last Name: %-12s Balance: %10.2f\n",
						acc, first, last, bal);
				
				System.out.print(combined);		
				displayWriter.format("%s", combined);
			}
		}
		catch (NoSuchElementException nse){
			System.err.println("File improperly formed.");
			fileScanner.close();
			System.exit(1);
		}		
		catch (IllegalStateException ise){
			System.err.println("Error reading from file");
			System.exit(1);
		}
		
		if (displayWriter != null) {
			displayWriter.close();
		}
	}
	
	
	public void addRecords() {
		int line = 0;
		while(line < 5) {
			try {
				int acc = scanner.nextInt();
				String first =scanner.next();
				String last = scanner.next();
				double bal = scanner.nextDouble();
				if (acc > 0) {
					formatter.format("%d %s %s %.2f\n",
							acc, first, last, bal);
				}
				else {
					System.out.println("Account number must be greater than 0.");
				}
			}
			catch(FormatterClosedException fce) {
				System.err.println("Error writing to file");
				return;
			}
			catch(NoSuchElementException nse) {
				System.err.println("Invalid Input.");
				scanner.next();
			}
			line++;
		}
		scanner.close();
	}
	
	
	
	public void closeReader() {
		if (fileScanner != null) {
			fileScanner.close();
		}
	}
	
	public void closeWriter() {
		if (formatter != null) {

			System.out.println("Writer closed");
			formatter.close();
		}
	}
	
}