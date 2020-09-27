package Exercise;

import java.awt.Graphics;
import javax.swing.JPanel;

public class DrawPanel extends JPanel {
   public void paintComponent(Graphics g) {
       super.paintComponent(g);

       int height = getHeight();
       int x1 = 0, y1 = 0, 
           x2 = 0, y2 = height; 
      

       while (y1 < y2) { 
           g.drawLine(x1, y1, x2, y2); 
           y1 += 15;              
           x2 += 15;            
       	}
   	}
 }


