

import java.awt.Graphics;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.Random;

public class Shapes extends JPanel {
	public Shapes() {

	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Random rand = new Random();
	    int height = getHeight();
	    int width = getWidth();
	    int x1 = (width/2) - 25;
	    int x2 = (width/2) + 25;
	    int y1 = (height/2) - 25;
	    int y2 = (height/2) + 25;
	    boolean loopCheck = true;

		while(loopCheck) {
			String input = JOptionPane.showInputDialog(""
					+ "Type 1 to draw a line\n"
					+ "Type 2 to draw a triangle\n"
					+ "Type 3 to draw a square\n"
					+ "Type 4 to draw a circle\n"
					+ "Type 5 to end");
			int choice = Integer.parseInt(input);
			switch(choice) {
			case 1:
			    int lineStartCoord = rand.nextInt(2);
			    if (lineStartCoord == 0) {
				    int x1Range = rand.nextInt(51) + x1;
				    int x2Range = rand.nextInt(51) + x1;
					g.drawLine(x1Range, y1, x2Range, y2);
			    }
			    else if (lineStartCoord == 1)  {
				    int y1Range = rand.nextInt(51) + y1;
				    int y2Range = rand.nextInt(51) + y1;
					g.drawLine(x1, y1Range, x2, y2Range);
			    }
				break;
			case 2:
			    int triStartCoord = rand.nextInt(4);
			    int triX1 = 0, triX2 = 0 , triX3 = 0, 
			    		triY1 = 0, triY2 = 0, triY3 = 0;
			    if (triStartCoord == 0) {
					triX1 = rand.nextInt(51) + x1;
					triY1 = y1;
					triX2 = x1;
					triY2 = rand.nextInt(51) + y1;
					triX3 = x2;
					triY3 = rand.nextInt(51) + y1;
			    }
			    else if (triStartCoord == 1) {
					triX1 = rand.nextInt(51) + x1;
					triY1 = y1;
					triX2 = x1;
					triY2 = rand.nextInt(51) + y1;
					triX3 = rand.nextInt(51) + x1;
					triY3 = y2;
			    }
			    else if (triStartCoord == 2) {
					triX1 = rand.nextInt(51) + x1;
					triY1 = y1;
					triX2 = x2;
					triY2 = rand.nextInt(51) + y1;
					triX3 = rand.nextInt(51) + x1;
					triY3 = y2;
			    }
			    else if (triStartCoord == 3) {
					triX1 = x1;
					triY1 = rand.nextInt(51) + y1;
					triX2 = x2;
					triY2 = rand.nextInt(51) + y1;
					triX3 = rand.nextInt(51) + x1;
					triY3 = y2;
			    }
				int triX[] = {triX1, triX2, triX3};
				int triY[] = {triY1, triY2, triY3};
				g.drawPolygon(triX, triY, 3);
				break;
			case 3:
				int sqrX[] = {width/2, x2, width/2, x1};
				int sqrY[] = {y1, height/2, y2, height/2};
				g.drawPolygon(sqrX, sqrY, 4);
				break;
			case 4:
				g.drawOval(x1, y1, 50, 50);
				break;
			case 5:
				loopCheck = false;
				return;
			default:
				loopCheck = false;
				return;
			}
		}
	}
 }


