package SubPanel;

import SubFrame.MenuBar;
import figureType.Circle;
import figureType.Eraser;
import figureType.Figure;
import figureType.Img;
import figureType.Line;
import figureType.Pen;
import figureType.Rectangle;
import figureType.Text;
import figureType.Triangle;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;
import java.util.Stack;
import java.util.Vector;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Frame.MainFrame;
import information.Information;

public class DrawPanel extends JPanel implements MouseMotionListener, MouseListener, Serializable, KeyListener {
	
	static int LinePotX=0;
	static int LinePotY=0;
	private String filepath = null;
	private Vector<Figure> figureSet = new Vector<Figure>();
	private Stack<Vector<Figure>> figureStack = new Stack<Vector<Figure>>();
	
	private int dragStartX,dragStartY;
	
	public DrawPanel()
	{
		Information.setCurrentpanel(this);					
		this.setBackground(new Color(255,255,255));			
		setVisible(true);									
		this.setFocusable(true);
		this.addMouseMotionListener(this);					
		this.addMouseListener(this);

		addKeyListener(this);
	}
	
	
	@Override
	public void paintComponent(Graphics g)
	{	
		Graphics2D g2 = (Graphics2D)g;
		super.paintComponent(g2);
	
		for(Figure current:figureSet)						
		{
			current.drawFigure(g2);	
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		MainFrame.getInstance().setBottomLabel("X : "+e.getX()+" Y : "+e.getY());	
		
		if(Information.getCurrentMode()==Information.MODE_MOVE)						
		{	
			if(( Information.getCurrentFigure()!=null) && !(Information.getCurrentFigure() instanceof Eraser))					//현재 피규어가 있으면
			{
				int moveX=e.getX()-dragStartX;										
				int moveY=e.getY()-dragStartY;
				Information.getCurrentFigure().moveTo(moveX, moveY);				
				dragStartX=e.getX();												
				dragStartY=e.getY();
				repaint();
			}
			
			
		}
		else if(Information.getCurrentMode()==Information.MODE_PAINT)
		{
			
		}
		
		
		else if(Information.getCurrentMode()==Information.MODE_RESIZE)
		{
			if(Information.getCurrentFigure()!=null)
			{
				
				Information.getCurrentFigure().calcFigure(e.getX(), e.getY());
				repaint();
			}
		}
		else if(Information.getCurrentMode()==Information.MODE_DELETE) 
		{
			
		}
		
		else
		{
			Figure current = figureSet.lastElement();								
			
			drawCurrentFigureFunc(e, current);
		}
		
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {											
		MainFrame.getInstance().setBottomLabel("X : "+e.getX()+" Y : "+e.getY());	
	}


	@Override
	public void mouseClicked(MouseEvent e) {												
		Information.setCurrentpanel(this);										
		if(Information.getCurrentMode()==Information.MODE_MOVE)
		{	
			if(Information.getCurrentFigure()!=null)
			{	
				dragStartX=e.getX();
				dragStartY=e.getY();
			}			
		}
		else if(Information.getCurrentMode()==Information.MODE_PAINT)
		{
			
			if(Information.getCurrentFigure()!=null)
			{	
				Information.getCurrentFigure().setColor(Information.getCurrentColor());
				repaint();
			}
		}
		else if(Information.getCurrentMode() == Information.MODE_ERASE) {
			if(e.isControlDown() == true) {
				popStackTrace();
			}
		}
		else if(Information.getCurrentMode()==Information.MODE_DELETE) {
			for(int i = figureSet.size()-1; i >= 0 ; i--) {
				if((e.getX() >= figureSet.get(i).minx) &&
						(e.getY() >= figureSet.get(i).miny) &&
						(e.getX() <= figureSet.get(i).maxx) &&
						(e.getY() <= figureSet.get(i).maxy)) {
					Information.setCurrentFigure(figureSet.get(i));
					deleteFigure(i);
					break;
				}
				else if((i == 0) && !((e.getX() >= figureSet.get(i).minx) &&
						(e.getY() >= figureSet.get(i).miny) &&
						(e.getX() <= figureSet.get(i).maxx) &&
						(e.getY() <= figureSet.get(i).maxy))) {
					Information.setCurrentFigure(null);
				}
			}
		}
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		MainFrame.getInstance().setBottomLabel("Out of Frame ");
	}


	@Override
	public void mousePressed(MouseEvent e) {
		Information.setCurrentpanel(this);
	
		if(Information.getCurrentMode()==Information.MODE_MOVE)
		{	
			for(int i = figureSet.size()-1; i >= 0 ; i--) {		
				if((e.getX() >= figureSet.get(i).minx) &&
						(e.getY() >= figureSet.get(i).miny) &&
						(e.getX() <= figureSet.get(i).maxx) &&
						(e.getY() <= figureSet.get(i).maxy)) {
					Information.setCurrentFigure(figureSet.get(i));
					break;
				}
				else if((i ==0) && !((e.getX() >= figureSet.get(i).minx) &&
						(e.getY() >= figureSet.get(i).miny) &&
						(e.getX() <= figureSet.get(i).maxx) &&
						(e.getY() <= figureSet.get(i).maxy))) {
					Information.setCurrentFigure(null);
				}
			}
			
			if(Information.getCurrentFigure()!=null)
			{	
				if(e.isControlDown() == true && !(Information.getCurrentFigure() instanceof Pen) && !(Information.getCurrentFigure() instanceof Eraser)) {
					figureSet.addElement(Information.getCurrentFigure().clone());
					Information.setCurrentFigure(figureSet.lastElement());
				}
				dragStartX=e.getX();
				dragStartY=e.getY();	
			}	
		}
		else if(Information.getCurrentMode()==Information.MODE_PAINT)
		{
			for(int i = figureSet.size()-1; i >= 0 ; i--) {
				if((e.getX() >= figureSet.get(i).minx) &&
						(e.getY() >= figureSet.get(i).miny) &&
						(e.getX() <= figureSet.get(i).maxx) &&
						(e.getY() <= figureSet.get(i).maxy)) {
					Information.setCurrentFigure(figureSet.get(i));
					break;
				}
				else if((i ==0) && !((e.getX() >= figureSet.get(i).minx) &&
						(e.getY() >= figureSet.get(i).miny) &&
						(e.getX() <= figureSet.get(i).maxx) &&
						(e.getY() <= figureSet.get(i).maxy))) {
					Information.setCurrentFigure(null);
				}
			}
			if(Information.getCurrentFigure()!=null)
			{	
				Information.getCurrentFigure().setColor(Information.getCurrentColor());
				repaint();
			}
			
		}
		else if(Information.getCurrentMode()==Information.MODE_RESIZE) {
			for(int i = figureSet.size()-1; i >= 0 ; i--) {
				if((e.getX() >= figureSet.get(i).minx) &&
						(e.getY() >= figureSet.get(i).miny) &&
						(e.getX() <= figureSet.get(i).maxx) &&
						(e.getY() <= figureSet.get(i).maxy)) {
					Information.setCurrentFigure(figureSet.get(i));
					break;
				}
				else if((i == 0) && !((e.getX() >= figureSet.get(i).minx) &&
						(e.getY() >= figureSet.get(i).miny) &&
						(e.getX() <= figureSet.get(i).maxx) &&
						(e.getY() <= figureSet.get(i).maxy))) {
					Information.setCurrentFigure(null);
				}
			}
		}
		else
		{
			drawFigureFunc(e);
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		figureStack.push((Vector<Figure>)figureSet.clone());

		if(Information.getCurrentMode()==Information.MODE_DRAW_IMAGE) {
			Information.setCurrentMode(Information.MODE_PEN);
		}
	}
	
	
	
	private void drawFigureFunc(MouseEvent e)
	{
		int mode=Information.getCurrentMode();
		switch(mode)
		{
		case Information.MODE_DRAW_IMAGE:
			Img img = new Img(Information.fPath,e.getX(), e.getY());
			figureSet.addElement(img);
			break;
			
		case Information.MODE_PEN: 
			Pen pens = new Pen(e.getX(),e.getY());
			figureSet.addElement(pens);
			break;
		
		case Information.MODE_DRAW_REC: 
			Rectangle rec = new Rectangle(e.getX(),e.getY(),0,0);
			figureSet.addElement(rec);
			break;
		case Information.MODE_DRAW_CIRCLE:
			Circle circle = new Circle(e.getX(),e.getY(),0,0);
			figureSet.addElement(circle);
			break;
		case Information.MODE_DRAW_LINE:
			Line line = new Line(e.getX(),e.getY(),e.getX(),e.getY());
			figureSet.addElement(line);
			break;
		case Information.MODE_DRAW_TRIANGLE:
			Triangle triangle = new Triangle(e.getX(),e.getY());
			figureSet.addElement(triangle);
			break;
		case Information.MODE_ERASE:
			Eraser eraser = new Eraser(e.getX(),e.getY());
			figureSet.addElement(eraser);
			break;
		case Information.MODE_TEXT:
			String textData=null;
			if(textData==null || textData.equals("")) textData=JOptionPane.showInputDialog(null,"텍스트를 입력해 주세요",JOptionPane.OK_OPTION);
			if(textData==null || textData.equals("")) return;
			
			Text text = new Text(e.getX(),e.getY(),textData);
			figureSet.addElement(text);
			break;
			
		default : return;
		}
		repaint();
	}
	

	public void drawCurrentFigureFunc(MouseEvent e, Figure temp)
	{
		temp.calcFigure(e.getX(), e.getY());
		repaint();
	}


	public void changeVector(Vector<Figure> vector)
	{
		figureSet=vector;
	}
	public Vector<Figure> getVector()
	{
		return figureSet;
	}
	
	public void addVector(Vector<Figure> addData)
	{
		figureSet.addAll(addData);
		
		
		figureStack.push((Vector<Figure>)figureSet.clone());	
		repaint();
	}
			
	public void addVector(Figure addData)
	{
		figureSet.add((Figure) addData.clone());
		
		
		figureStack.push((Vector<Figure>)figureSet.clone());	
		repaint();
	}
	
	public void  clearFigure()
	{
		figureSet.clear();
		figureStack.clear();
		
		repaint();
	}
	
	public void deleteFigure(int idx)
	{
		figureStack.push((Vector<Figure>)figureSet.clone());
		figureSet.remove(idx);
	
		repaint();
	}
	
	public void popStackTrace()
	{
		if(figureStack.isEmpty())
		{
			
			figureSet.clear();
			repaint();
			
			JOptionPane.showMessageDialog(null,"Error : Cant' find More Action","ERROR",JOptionPane.ERROR_MESSAGE);

		}
		else
		{
			
			if(figureSet.equals(figureStack.peek())) figureStack.pop();
			if(!figureStack.empty())
			{
				figureSet=(Vector<Figure>)figureStack.peek().clone();
				figureStack.pop();
			}
			else
			{
				figureSet.clear();
			}
			
			
			repaint();
		}
		
	}
	

	@Override
	public void keyTyped(KeyEvent e) {
	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	
	
	
}
