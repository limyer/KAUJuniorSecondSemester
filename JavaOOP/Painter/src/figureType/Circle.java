package figureType;

import java.awt.BasicStroke;
import java.awt.Graphics2D;

import javax.swing.JOptionPane;

import information.Information;

public class Circle extends RectangleType{
	
	private int strokeSize=1;
	
	public Circle(int startX, int startY,int width,int height)
	{
		super(startX,startY,width,height);
		strokeSize=Information.getLineSize();
	}
	
	@Override
	public void drawFigure(Graphics2D g) {
		g.setStroke(new BasicStroke(strokeSize,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		g.setColor(figureColor);
		if(getFillMode() == Information.MODE_FILL) {
			g.fillOval(X,Y,width,height);
		}
		else if(getFillMode() == Information.MODE_DRAW) {
			g.drawOval(X,Y,width,height);
		}
		minx = X;
		miny = Y;
		maxx = X + width;
		maxy = Y + height;
	}


	@Override
	 public Figure clone() { 
		Circle cir = new Circle(0,0,0,0);
		cir.startX=startX;
		cir.startY=startY;
		cir.width=width;
		cir.height=height;
		cir.figureColor=figureColor;
		cir.X=X;
		cir.Y=Y;
		cir.fillMode = fillMode;
			
		return cir;
	}
	
}
