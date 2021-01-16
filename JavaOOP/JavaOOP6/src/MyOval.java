import java.awt.Color;
import java.awt.Graphics;

public class MyOval extends MyShape{
	private int x;
	private int y;
	private int width;
	private int height;
	
	public MyOval(int x, int y, int width, int height, Color color){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		myColor = color;
	}
	
	public void draw(Graphics g){
		g.setColor(myColor);
		g.drawOval(x,y,width,height);
	}
}