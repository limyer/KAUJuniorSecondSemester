package figureType;

import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

import information.*;

public abstract class PenType extends Figure{

	protected ArrayList<PenTypeTracePoint> points = new ArrayList<PenTypeTracePoint>();
	
	protected int previousX,previousY;
	public int minx;
	public int maxx;
	public int miny;
	public int maxy;
	
	
	class PenTypeTracePoint implements Serializable
	{
		Point prePoints;
		Point LastPoints;
		
		PenTypeTracePoint(int x1, int y1, int x2, int y2)
		{
			prePoints = new Point(x1,y1);
			LastPoints = new Point(x2,y2);
		}
	}
	
	public PenType(int firstX, int firstY)
	{
		
		previousX=firstX;
		previousY=firstY;
		
		points.add(new PenTypeTracePoint(firstX,firstY,firstX,firstY));
		
	}
	
	
	
	@Override
	public void setFigureSize(int currentX, int currentY) {
		// TODO Auto-generated method stub
		
		points.add(new PenTypeTracePoint(previousX,previousY,currentX,currentY));
		previousX=currentX;
		previousY=currentY;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public abstract void drawFigure(Graphics2D g) ;

	@Override
	public void calcFigure(int currentX, int currentY) {
		// TODO Auto-generated method stub
		if(Information.getCurrentMode()!=Information.MODE_RESIZE)
		{
			setFigureSize(currentX,currentY);
		}
		
	}
	
	public  void moveTo(int curX,int curY)
	{
		for(PenTypeTracePoint curPoints : points)
		{
			curPoints.prePoints.x+=curX;
			curPoints.prePoints.y+=curY;
			curPoints.LastPoints.x+=curX;
			curPoints.LastPoints.y+=curY;
		}
	}

	public abstract Figure clone();
}

	

