// 2018125084 임예랑 1차 프로그래밍 시험
import java.awt.Graphics;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.Random;

public class Shapes extends JPanel {

	private int target = 0;
	private int currentXCoord = 10;
	private int currentYCoord = 10;
	private int customWidth = 50;
	private int customHeight = 50;
	
	public Shapes() {

	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	    int repeat = 0;


		while(repeat < target) {
			if (repeat % 3 == 0) {
				g.drawOval(currentXCoord, currentYCoord, customWidth, customHeight);
			}
			else if (repeat % 3 == 1) {
				g.drawRect(currentXCoord, currentYCoord, customWidth, customHeight);
			}
			else if (repeat % 3 == 2) {
				g.drawLine(currentXCoord, currentYCoord, currentXCoord + customWidth, currentYCoord + customHeight);
			}
			currentXCoord = currentXCoord + customWidth + 10;
			if (repeat % 8 == 7) {
				currentYCoord = currentYCoord + customHeight + 10;
				currentXCoord = 10;
			}
			repeat++;
		}
	}
	
	public void setChoice(int newChoice) {
		this.target = newChoice;
		currentXCoord = 10;
		currentYCoord = 10;
		customWidth = 50;
		customHeight = 50;
	}
 }


