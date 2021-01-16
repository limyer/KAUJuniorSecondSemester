import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JPanel;

public class PaintPanel extends JPanel{
	private int pointCount = 0;
	private int drawWidth = 3;
	private Color paintColor = Color.BLACK;
	
	private Point[] points = new Point[10000];
	private Color[] pointColors = new Color[10000];
	private int[] widths = new int[10000];
	
	public PaintPanel() {
		addMouseMotionListener(
				new MouseMotionAdapter() {
					public void mouseDragged(MouseEvent event) {
						if (pointCount < points.length) {
							points[pointCount] = event.getPoint();
							pointColors[pointCount] = paintColor;
							widths[pointCount] = drawWidth;
							pointCount++;
							repaint();
						}
					}
				}
				);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (int i=0; i < pointCount; i++) {
			g.setColor(pointColors[i]);
			g.fillOval(points[i].x, points[i].y, widths[i], widths[i]);
		}
	}
	
	public void changeColor(Color color) {
		paintColor = color;
	}
	
	public void changeDrawWidth(int width) {
		drawWidth = width;
	}
}