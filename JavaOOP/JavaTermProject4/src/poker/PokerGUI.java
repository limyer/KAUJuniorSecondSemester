package poker;


import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.awt.event.ItemEvent;
import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class PokerGUI extends JFrame{
	private static final int width = 1020;
	private static final int height = 1060;
	private JPanel currentPanel;
	private String currentPanelStr;
    private int numberOfPlayers = 5;
    private PokerSystem sys;

    public PokerGUI(PokerSystem s){
        super("Poker Games");
    	this.sys = s;
        setSize(width,height);
        setTitle("Poker");
        MenuBarGUI menuBar = new MenuBarGUI(this);
        setJMenuBar(menuBar);
        StartPanel init = new StartPanel(numberOfPlayers, this);
        currentPanel = init;
        currentPanelStr = "StartPanel";
        add(init);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public void startNewGame() {
    	remove(currentPanel);
    	setCurrentPanel(new StartPanel(this.numberOfPlayers, this), "StartPanel");
    	this.sys.resetPlayers();
    	reset();
    }
    
    public void startGamePanel(){
    	remove(currentPanel);
    	sys.setEachPlayerCards();
    	setCurrentPanel(new GamePanel(this), "GamePanel");
    	reset();
    }
    
    public void enableWinnerButton() {
    	if(currentPanelStr.equalsIgnoreCase("GamePanel")){
    		ControlButtonPanel gp = (ControlButtonPanel)currentPanel.getComponent(1);
        	gp.getComponent(1).setEnabled(true);
    	}
    }
    
    public void reset() {
    	pack();
        setSize(width,height);
        setTitle("Poker");
        setVisible(true);
    }

    public JPanel getCurrentPanel() {
    	return currentPanel;
    }
    
    public int getNumberOfPlayers() {
    	return this.numberOfPlayers;
    }
    
    public PokerSystem getSystem() {
    	return this.sys;
    }
    
    public void setNumberOfPlayers(int num) {
    	this.numberOfPlayers = num;
    }
    
    public void setCurrentPanel(JPanel newPanel, String newPanelName) {
    	this.currentPanel = newPanel;
    	this.currentPanelStr = newPanelName;
    	add(newPanel);
    }
}
