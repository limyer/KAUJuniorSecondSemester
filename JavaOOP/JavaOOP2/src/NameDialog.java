// NameDialog.java

import javax.swing.JOptionPane;
import javax.swing.Icon;

public class NameDialog{
	public static void main(String[] args) {
		String name =
				JOptionPane.showInputDialog("What is your name?");
		
		String message = 
				String.format("Welcome, %s, to Java Programming!", name);
		
		JOptionPane.showMessageDialog(null, message);
		JOptionPane.showMessageDialog(null, message, "Hello", JOptionPane.PLAIN_MESSAGE, null);
);
	}
}
