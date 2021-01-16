package figureType;

import java.awt.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Img extends Figure {
	
	protected int width,height;
	protected int startX,startY;//말그대로 도형에 무슨짓을 하기전에 일단 초기값을 백업?하는 용도라 보면됨
	protected int X,Y;
	String path;
	Image img = null;
	
	
	public Img(String path, int startX, int startY) {
		super();
		this.path = path;
		try {
			img = ImageIO.read(new File(path));
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
		this.startX = startX;
		this.startY = startY;
	}
	
	public void setFigureSize(int width, int height) {
		// TODO Auto-generated method stub
		this.width=width;
		this.height=height;
	
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return width*height;
	}
	
	public void drawFigure(Graphics2D g) {
		g.drawImage(img, this.startX, this.startY, width, height, null);
		minx = startX;
		miny = startY;
		maxx = minx + width;
		maxy = miny + height;
	}
	
	public void calcFigure(int currentX, int currentY) {

		int wantWidth = currentX-startX;
		int wantHeight = currentY-startY;
		
		setFigureSize(Math.abs(wantWidth),Math.abs(wantHeight));
		
		if(wantWidth<0 && wantHeight <0)
		{
				X=startX+wantWidth;
				Y=startY+wantHeight;
		}
		else if(wantWidth<0 && wantHeight >=0)
		{
			
			X=startX+wantWidth;
		}
		else if(wantWidth>=0 && wantHeight <0)
		{
			Y=startY+wantHeight;
		
		}
		else if(wantWidth>=0 && wantHeight >=0)
		{
			X=startX; 
			Y=startY;
		}
	}
	
	public void moveTo(int curX,int curY)
	{
		startX+=curX;
		startY+=curY;
	}
	
	public Figure clone() { 
		Img img = new Img(this.path, this.startX, this.startY);
		img.startX=startX;
		img.startY=startY;
		img.width=width;
		img.height=height;
		img.figureColor=figureColor;
		img.X=X;
		img.Y=Y;

		return img;
	}
}
