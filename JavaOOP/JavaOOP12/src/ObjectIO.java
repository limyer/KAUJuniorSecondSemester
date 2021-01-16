import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ObjectIO{
	private ObjectOutputStream output;
	private Formatter displayWriter;
	private ObjectInputStream input;
	private Scanner scanner;
	
	public ObjectIO() {
		try {
			scanner = new Scanner(new File("Lab12data.txt"));
		}
		catch (FileNotFoundException fe){
			System.err.println("File Not Found");
		}
	}
	
	public void startWriter() {
		try {
			output = new ObjectOutputStream(
					new FileOutputStream("test2.acc"));
			System.out.println("Writer Started");
		}
		catch(IOException ie) {
			System.err.println("Error opening file");
		}
	}
	
	public void startReader() {
		try {
			input = new ObjectInputStream(
					new FileInputStream("test2.acc"));
		}
		catch(IOException ie) {
			System.err.println("Error opening file");
		}
	}

	public void readRecords() {
		String combined = new String("");
		List<String> allString = new ArrayList<String>();
		

		try {
			displayWriter = new Formatter("ObjectDisplayed.txt");
		}
		catch(SecurityException se) {
			System.err.println("You do not have permission to this file");
			System.exit(1);
		}
		catch(FileNotFoundException fe) {
			System.err.println("Error Opening or creating file");
			System.exit(1);
		}

		AccountRecord record;
		try {
			while (true) {
				record = (AccountRecord) input.readObject();
				
				combined = String.format("Account: %-10d First Name: %-12s Last Name: %-12s Balance: %10.2f\n",
						record.getAccount(), record.getFirstName(), record.getLastName(), record.getBalance());
				
				System.out.print(combined);	
				allString.add(combined);
				
			}
		}
		catch (EOFException ee){
			return;
		}		
		catch (ClassNotFoundException ce){
			System.err.println("Unable to create object");
		}		
		catch (IOException ie){
			System.err.println("Error reading file");
		}
		catch (IllegalFormatException fe){
			System.err.println("File improperly formed.");
			System.exit(1);
		}
		for(String a : allString) {
			displayWriter.format("%s", a);
		}
		

		if (displayWriter != null) 
			displayWriter.close();
	}
	
	
	public void addRecords() {
		AccountRecord record;
		int acc = 0;
		String first;
		String last;
		double bal;
		
		int line = 0;
		while(line < 5) {
				try {
					acc = scanner.nextInt();
					first =scanner.next();
					last = scanner.next();
					bal = scanner.nextDouble();
					if (acc > 0) {
						record = new AccountRecord(acc, first, last, bal);
						output.writeObject(record);
					}
					else {
						System.out.println("Account number mubst be greater than 0");
					}
				}
				catch(IOException ie) {
					System.err.println("Error writing to file");
					return;
				}
				catch(NoSuchElementException nse) {
					System.err.println("Invalid Input.");
				}
				line++;
		}
		scanner.close();
		
	}
	
	
	public void closeReader() {
		try {
			if (input != null) {
				input.close();
			}
		}
		catch(IOException ie) {
			System.err.println("Error closing file.");
			System.exit(1);
		}
	}
	
	public void closeWriter() {
		try {
			if (output != null) {
				output.close();
				System.out.println("Writer Closed");
			}
		}
		catch(IOException ie) {
			System.err.println("Error closing file.");
			System.exit(1);
		}
	}
	
}