package figureType;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;

import information.Information;

public abstract class Figure implements Serializable ,Cloneable{
	
	protected Color figureColor;
	protected int fillMode=0;
	public int minx;
	public int miny;
	public int maxx;
	public int maxy;

	public Figure()
	{
		figureColor=Information.getCurrentColor();
		setFillMode(Information.getCurrentFillMode());
	}
	
	
	public void setColor(Color color)
	{
		this.figureColor=color;
	}
	
	public Color getColor()
	{
		return figureColor;
	}
	
	public void setFillMode(int fillMode)
	{
		this.fillMode = fillMode;
	}
	
	public int getFillMode()
	{
		return fillMode;
	}
	
	public abstract void moveTo(int curX,int curY);
	public abstract void setFigureSize(int width, int height);
	public abstract int getSize();
	public abstract void drawFigure(Graphics2D g);
	public abstract void calcFigure(int currentX, int currentY);
	public abstract Figure clone();
	
}
