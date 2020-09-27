// 2018125084 임예랑 4주차 실습 문제
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