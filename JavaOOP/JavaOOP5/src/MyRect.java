import java.awt.Color;
import java.awt.Graphics;

public class MyRect extends MyShape{
	private int x;
	private int y;
	private int width;
	private int height;
	
	public MyRect(int x, int y, int width, int height, Color color){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		myColor = color;
	}
	
	public void draw(Graphics g){
		g.setColor(myColor);
		g.drawRect(x,y,width,height);
	}
	
	
}