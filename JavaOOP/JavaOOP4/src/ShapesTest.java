// 2018125084 �ӿ��� 4���� �ǽ� ����
import javax.swing.JFrame;

public class ShapesTest{
	public static void main(String[] args) {


		Shapes panel = new Shapes();
		JFrame application = new JFrame();
		
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		application.add(panel);
		application.setSize(250, 250);
		application.setVisible(true);
		
	}
}