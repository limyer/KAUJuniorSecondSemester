// 2018125084 �ӿ��� 1�� ���α׷��� ����
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ShapesTest{
	public static void main(String[] args) {


		Shapes panel = new Shapes();

		JFrame application = new JFrame();
		
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		application.add(panel);
		application.setTitle("Drawing Figures (�ӿ���)");
		application.setSize(600, 400);
		application.setVisible(true);
		
		while(true) {
			String input = JOptionPane.showInputDialog("Enter the number of figures to draw (1-40)");
			int choice = Integer.parseInt(input);
			if (choice > 40) {
				JOptionPane.showMessageDialog(null, "Can't draw more than 40");
				continue;
			}
			panel.setChoice(choice);
			panel.repaint();
		}
	}
}