// 2018125084 ÀÓ¿¹¶û
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import java.util.Random;

public class Shapes extends JPanel {
	private Random randomNumbers = new Random();
	private MyShape[] shapes; 
	private int[] counts;
	private int countIndex;

	
	public Shapes() {
		setBackground(Color.WHITE);
		shapes = new MyShape[255];
		counts = new int[255];
		countIndex = 0;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (MyShape shape: shapes) {
			if (shape != null) {
				shape.draw(g);
			}
		}
	}
	
	public void setChoice(int newChoice) {
		int reCount = counts[newChoice - 1];
		int totalCountBefore = 0;
		int number = 0;
		
		for (int count: counts) {
			number++;
			if (number == newChoice) {
				break;
			}
			totalCountBefore += count;
		}
		
		for (int count = 0; count < reCount; count++) {
			shapes[totalCountBefore++].reColor(Color.RED);
		}
	}
	
	public void setChoice(String newChoice) {
		int drawCount = 6 + randomNumbers.nextInt(15);
		int startPoint = sum(counts);
		
		counts[countIndex] = drawCount;
		countIndex++;
		
		for (int count = startPoint; count < startPoint + drawCount; count++) {
			int x1 = randomNumbers.nextInt(300);
			int y1 = randomNumbers.nextInt(300);
			int x2 = randomNumbers.nextInt(300);
			int y2 = randomNumbers.nextInt(300);
			
			if (newChoice.equalsIgnoreCase("L")) {
				shapes[count] = new MyLine(x1,y1,x2,y2, Color.BLACK);
			}
			else if (newChoice.equalsIgnoreCase("C")) {
				shapes[count] = new MyCircle(x1,y1,x2,y2, Color.BLACK);
			}
			else if (newChoice.equalsIgnoreCase("R")) {
				shapes[count] = new MyRect(x1,y1,x2,y2, Color.BLACK);
			}
		}
	}
	
	  public static int sum(int[] array) {
		  int sum = 0;
		  for (int i = 0; i < array.length; i++)
		      sum += array[i];
		  return sum;
	  }
 }


