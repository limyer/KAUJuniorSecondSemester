package poker;

import java.awt.Color;

import javax.swing.JPanel;





public class GamePanel extends JPanel{
	public static Color GamePanelColor = new Color(0,141,98);
	private PokerGUI parentGUI;
	
	public GamePanel(PokerGUI gui) {
		this.parentGUI = gui;
		setSize(parentGUI.getWidth(),parentGUI.getHeight());
		setBackground(GamePanel.GamePanelColor);
		setLayout(null);

		PlayerTablePanel table = new PlayerTablePanel(gui);
		table.setBounds(150,0,700,800);
		ControlButtonPanel buttons = new ControlButtonPanel(gui, table);
		add(table);
		buttons.setBounds(150,800,700,200);
		add(buttons);
		
	}

}