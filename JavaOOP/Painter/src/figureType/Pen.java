package figureType;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.util.ArrayList;

import figureType.PenType.PenTypeTracePoint;
import information.*;

public class Pen extends PenType{
	

	private int strokeSize=1;
	
	public Pen(int firstX, int firstY)
	{
		
		super(firstX,firstY);
		strokeSize=Information.getLineSize();
	}

	@Override
	public void drawFigure(Graphics2D g) {
		// TODO Auto-generated method stub
		
		g.setStroke(new BasicStroke(strokeSize,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		g.setColor(figureColor);
		for(PenTypeTracePoint current : points)
		{
			g.drawLine(current.prePoints.x, current.prePoints.y, current.LastPoints.x, current.LastPoints.y);
		}
		
	}

	@Override
	 public Figure clone() { 
			Pen pen = new Pen(0,0);			
			pen.previousX=previousX;
			pen.previousY=previousY;
			pen.points=(ArrayList<PenTypeTracePoint>)points.clone();
			return pen;
	    }
	
}

