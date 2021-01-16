package figureType;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Arrays;

import information.Information;

public class Triangle extends Figure{
	
	private int[] xPoints ={0,0,0};
	private int[] yPoints ={0,0,0};
	private int strokeSize=1;
	
	
	public Triangle(int startX, int startY)
	{
		super();
		this.xPoints[0]=this.xPoints[1]=this.xPoints[2]=startX;
		this.yPoints[0]=this.yPoints[2]=this.yPoints[2]=startY;
		strokeSize=Information.getLineSize();
	}
	
	
	
	
	@Override
	public void setFigureSize(int xPoint, int yPoint) {
		
		xPoints[2]=xPoint;
		yPoints[2]=yPoint;
		
		xPoints[1] = xPoints[2]-(xPoint-xPoints[0])*2;
		yPoints[1]=yPoint;
		

	}

	@Override
	public int getSize() {
		
		int width = Math.abs(xPoints[2]-xPoints[1]);
		int height = Math.abs(yPoints[0]-yPoints[1]);
		
		return width*height/2;
	}

	@Override
	public void drawFigure(Graphics2D g) {
		g.setStroke(new BasicStroke(strokeSize,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		g.setColor(figureColor);
		if(getFillMode() == Information.MODE_FILL) {
			g.fillPolygon(xPoints, yPoints, 3);
		}
		else if(getFillMode() == Information.MODE_DRAW) {
			g.drawPolygon(xPoints, yPoints, 3);
		}		
		 this.minx = Math.min(Math.min(xPoints[0], xPoints[1]), xPoints[2]);
         this.miny = Math.min(Math.min(yPoints[0], yPoints[1]), yPoints[2]);
         this.maxx = Math.max(Math.max(xPoints[0], xPoints[1]), xPoints[2]);
         this.maxy = Math.max(Math.max(yPoints[0], yPoints[1]), yPoints[2]);
	}

	@Override
	public void calcFigure(int currentX, int currentY) {
		// TODO Auto-generated method stub
		setFigureSize(currentX,currentY);
	}
	
	public void moveTo(int curX,int curY)
	{
		xPoints[0]+=curX;
		xPoints[1]+=curX;
		xPoints[2]+=curX;
		
		yPoints[0]+=curY;
		yPoints[1]+=curY;
		yPoints[2]+=curY;		
		
	}
	
	@Override
	 public Figure clone() { 
			Triangle tri = new Triangle(0,0);
			tri.xPoints=xPoints.clone();
			tri.yPoints=yPoints.clone();
			tri.fillMode = fillMode;
			return tri;
	    }
	
	
	
}
