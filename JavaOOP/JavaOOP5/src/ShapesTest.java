import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ShapesTest{
	public static void main(String[] args) {
		Shapes panel = new Shapes();

		JFrame application = new JFrame();
		
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		application.add(panel);
		application.setTitle("Drawing Figures (ÀÓ¿¹¶û)");
		application.setSize(300, 300);
		application.setVisible(true);
		
		while(true) {
			String input = JOptionPane.showInputDialog(""
					+ "Type L to draw lines\n"
					+ "Type R to draw rectangles\n"
					+ "Type C to draw circles\n"
					+ "Type any number of figures to change the color to red");
			if (input.equalsIgnoreCase("L") || input.equalsIgnoreCase("R") || input.equalsIgnoreCase("C")) {
				panel.setChoice(input);
			}
			else {
				int choice = Integer.parseInt(input);
				panel.setChoice(choice);
			}
			panel.repaint();
		}
	}
}