package figureType;

import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JOptionPane;

import information.Information;

public class Text extends Figure{
	

	private String text;
	private int startX,startY;
	private int textSize=10;
	private int fontStyle = Font.PLAIN;
	private int textLength;
	
	public Text(int StartX, int StartY, String str)
	{
		super();
		textSize=Information.getTextSize();
		fontStyle=Information.gettextStyle();
		this.startX=StartX;
		this.startY=StartY;
		text=str;
		textLength = text.length();
	}
	

	@Override
	public void setFigureSize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getSize() {
		
		return 0;
	}

	@Override
	public void drawFigure(Graphics2D g) {
		
		g.setColor(figureColor);
		
		g.setFont(new Font(null,fontStyle,textSize));
		g.drawString(text, startX, startY);
		minx = startX;
		miny = startY - textSize;
		maxx = minx + textSize*textLength;
		maxy = startY;
	}

	@Override
	public void calcFigure(int currentX, int currentY) {
		
		if(Information.getCurrentMode()==Information.MODE_RESIZE)
		{	
			int deltax=Math.abs(startX-currentX);
			int deltay=Math.abs(startX-currentX);
			textSize=(deltax+deltay/100);
			
		}
		
		
	}
	public void moveTo(int curX,int curY)
	{
		startX+=curX;
		startY+=curY;
	}

	@Override
	 public Figure clone() { 
			Text tex = new Text(0,0,this.text);
			
			tex.text=text;
			tex.startX=startX;
			tex.startY=startY;
			tex.textSize=textSize;
			tex.fontStyle=fontStyle;
			return tex;
	    }


}
