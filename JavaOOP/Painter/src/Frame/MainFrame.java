package Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import SubFrame.UnderBar;
import SubFrame.CenterPanel;
import SubFrame.LeftPanel;
import SubFrame.MenuBar;
import SubFrame.TopPanel;
import information.Information;

public class MainFrame extends JFrame {
	
	private LeftPanel leftPanel;
	private UnderBar bottomLabel;
	private TopPanel topPanel;
	private MenuBar menuBar;
	public static MainFrame instance;   //private을 public으로 잠깐 바꿈
	
	
	public static MainFrame getInstance()
	{
		if(instance==null)
			instance = new MainFrame(Information.PROGRAM_NAME);
		return instance;
		
	}
	public MainFrame(String name)
	{
	
		super(name);
		
		leftPanel = new LeftPanel();
		bottomLabel = new UnderBar("Start");
		topPanel = new TopPanel();
			
		this.add(leftPanel, BorderLayout.WEST);
		this.add(bottomLabel, BorderLayout.SOUTH);
		this.add(topPanel,BorderLayout.NORTH);
		
		
		menuBar = new MenuBar();
		this.setJMenuBar(menuBar);
		this.add(DesktopPane.getInstance());

	}
	
	
	
	public void addDrawFrame(String name){
		CenterPanel newFrame = new CenterPanel();
		newFrame.setLocation(0,0);
		newFrame.setSize(1000,800);
		DesktopPane.getInstance().addDrawFrameToSet(name, newFrame);
		DesktopPane.getInstance().add(newFrame);
	}
	

	public void setBottomLabel(String text)
	{
		Color color =Information.getCurrentColor();
		String mode = Information.getCurrentModeToString();
		if(Information.getCurrentMode() == Information.MODE_MOVE) {
			bottomLabel.setText("현재 모드 : "+ mode+"   현재 좌표 : "+ text +"    R : "+ color.getRed()+" G : "+ color.getGreen()+" B : "+ color.getBlue() + "        *ctrl키를 누르고 드래그하면 객체가 복사됩니다*");
		}
		else if(Information.getCurrentMode() == Information.MODE_ERASE) {
			bottomLabel.setText("현재 모드 : "+ mode+"   현재 좌표 : "+ text +"    R : "+ color.getRed()+" G : "+ color.getGreen()+" B : "+ color.getBlue() + "        *ctrl키를 누르고 클릭하면 이전 상태를 복구합니다*");
		}
		else {
			bottomLabel.setText("현재 모드 : "+ mode+"   현재 좌표 : "+ text +"    R : "+ color.getRed()+" G : "+ color.getGreen()+" B : "+ color.getBlue());
		}
	}
	
	public void setBottomLabel(Color color)
	{
		String mode = Information.getCurrentModeToString();
		bottomLabel.setText("현재 모드 : "+ mode+"   현재 좌표 : Out of frame"+ "    R : "+ color.getRed()+" G : "+ color.getGreen()+" B : "+ color.getBlue());
	}

	
}
