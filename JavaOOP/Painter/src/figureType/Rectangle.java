package figureType;

import java.awt.BasicStroke;
import java.awt.Graphics2D;

import information.Information;

public class Rectangle extends RectangleType{

	private int strokeSize=1;
	
	public Rectangle(int startX, int startY,int width,int height)
	{
		super(startX,startY,width,height);
		
		strokeSize=Information.getLineSize();
	}

	
	@Override
	public void drawFigure(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setStroke(new BasicStroke(strokeSize,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		g.setColor(figureColor);
		
		if(getFillMode() == Information.MODE_FILL) {
			g.fillRect(X,Y,width,height);
		}
		else if(getFillMode() == Information.MODE_DRAW) {
			g.drawRect(X,Y,width,height);
		}
		minx = X;
		miny = Y;
		maxx = X + width;
		maxy = Y + height;
	}

	@Override
	 public Figure clone() {
			Rectangle rec = new Rectangle(0,0,0,0);
			rec.startX=startX;
			rec.startY=startY;
			rec.width=width;
			rec.height=height;
			rec.figureColor=figureColor;
			rec.X=X;
			rec.Y=Y;
			rec.fillMode = fillMode;		

			return rec;
	    }

	
}
