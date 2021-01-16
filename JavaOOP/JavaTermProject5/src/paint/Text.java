package paint;


import java.awt.Font;
import java.awt.Point;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;

import javax.swing.JOptionPane;


public class Text extends Shape implements IShape {
	
	private Font f;
	private FontRenderContext frc;
	
	public Text() {
        super();
		text = JOptionPane.showInputDialog("Type Text");
		f = new Font("Helvetica", 1, 60);
		frc  = GUI.getInstance().canvas.getFontMetrics(f).getFontRenderContext();
    }
	
	public Text(String t) {
        super();
		text = t;
		f = new Font("Helvetica", 1, 60);
		frc  = GUI.getInstance().canvas.getFontMetrics(f).getFontRenderContext();
    }

    @Override
    public java.awt.Shape getShape() throws NullPointerException{
    	try {
        	GlyphVector v = f.createGlyphVector(frc, text);
        	return v.getOutline(topLeftCorner.x, bottomRightCorner.y);
    	}
    	catch(NullPointerException e) {
    		return null;
    	}
    }

    @Override
    public Shape clone() {
        Shape temp = new Text(text);
        temp.topLeftCorner = (Point) this.topLeftCorner.clone();
        temp.bottomRightCorner = (Point) this.bottomRightCorner.clone();
        temp.borderColor = this.borderColor;
        temp.fillColor = this.fillColor;
        temp.selected = false;
        return temp;
    }
	


	

}
