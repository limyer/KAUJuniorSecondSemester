// 2018125084 �ӿ��� 3���� �ǽ� ���� 2

package Exercise;

import javax.swing.JFrame;

public class FigureExercise{
	public static void main(String[] args) {
		DrawPanel panel = new DrawPanel();
		JFrame application = new JFrame();
		
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		application.add(panel);
		application.setSize(250, 250);
		application.setVisible(true);
		
	}
}