import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ShapesTest{
	public static void main(String[] args) {
		Shapes panel = new Shapes();

		JFrame application = new JFrame();
		
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		application.add(panel);
		application.setTitle("Drawing Figures (11Á¶)");
		application.setSize(300, 300);
		application.setVisible(true);
		
		while(true) {
			String input = JOptionPane.showInputDialog(""
					+ "Type C and any number of shape you want to change (eg. C1)\n"
					+ "Type H or R to hide and resore\n"
					+ "Type anything else to end\n");
			char first = input.charAt(0);
			if (first == 'C') {
				String color = JOptionPane.showInputDialog("" 
			+ "Type the color you want to change (eg. red, black, green, yellow, blue)\n");
				int number = input.charAt(1) - '0' - 1;
				panel.setColor(color, number);
			}
			else if (input.equalsIgnoreCase("H")) {
				panel.setVisible(false);
			}
			else if (input.equalsIgnoreCase("R")) {
				panel.setVisible(true);
			}
			else {
				System.exit(0);
				break;
			}
			panel.repaint();
		}
	}
}