// 2018125084 ÀÓ¿¹¶û
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import java.util.Random;

public class Shapes extends JPanel {
	private Random randomNumbers = new Random();
	private MyShape[] shapes; 

	public Shapes() {
		setBackground(Color.WHITE);
		shapes = new MyShape[10];
		
		int timesDraw = randomNumbers.nextInt(6) + 5;
		
		for(int i = 0; i < timesDraw; i++) {
			int x1 = randomNumbers.nextInt(250);
			int y1 = randomNumbers.nextInt(250);
			int x2 = randomNumbers.nextInt(250);
			int y2 = randomNumbers.nextInt(250);
			int shapeKind = randomNumbers.nextInt(3);
			switch(shapeKind) {
			case 0:
				shapes[i] = new MyLine(x1,y1,x2,y2, Color.BLACK);
				break;
			case 1:
				shapes[i] = new MyOval(x1,y1,x2,y2, Color.BLACK);
				break;
			case 2:
				shapes[i] = new MyRect(x1,y1,x2,y2, Color.BLACK);
				break;
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (MyShape shape: shapes) {
			if (shape != null) {
				shape.draw(g);
			}
		}
	}
	
	public void setColor(String newColor, int choiceNumber) {
		newColor = newColor.toUpperCase();
		switch(newColor) {
		case "RED":
			shapes[choiceNumber].reColor(Color.RED);
			break;
		case "BLUE":
			shapes[choiceNumber].reColor(Color.BLUE);
			break;
		case "YELLOW":
			shapes[choiceNumber].reColor(Color.YELLOW);
			break;
		case "GREEN":
			shapes[choiceNumber].reColor(Color.GREEN);
			break;
		case "BLACK":
			shapes[choiceNumber].reColor(Color.BLACK);
			break;
		}

	}
 }


