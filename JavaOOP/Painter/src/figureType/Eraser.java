package figureType;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import figureType.PenType.PenTypeTracePoint;
import information.*;

public class Eraser extends PenType{



	public Eraser(int firstX, int firstY)
	{
		
		super(firstX,firstY);
	}

	@Override
	public void drawFigure(Graphics2D g) {
		
		g.setStroke(new BasicStroke(35,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		g.setColor(new Color(255,255,255));
		
		for(PenTypeTracePoint current : points)
		{
			g.drawLine(current.prePoints.x, current.prePoints.y, current.LastPoints.x, current.LastPoints.y);
		}
		
	}

	@Override
	 public Figure clone() { 
			Eraser era = new Eraser(0,0);			
			era.previousX=previousX;
			era.previousY=previousY;
			era.points=(ArrayList<PenTypeTracePoint>)points.clone();
			return era;
	    }

}
