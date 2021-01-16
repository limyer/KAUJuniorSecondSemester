package poker;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;





public class ControlButtonPanel extends JPanel{
	private JButton revealAllButton;
	private JButton findWinnerButton;
	
	public ControlButtonPanel(PokerGUI gui, PlayerTablePanel panel) {
		setBackground(GamePanel.GamePanelColor);
		

	    revealAllButton = new JButton("Reveal All");
	    findWinnerButton = new JButton("Find Winner");

	    revealAllButton.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
				panel.revealAll();
				findWinnerButton.setEnabled(true);
			}
		});
	    

	    findWinnerButton.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
				gui.getSystem().sortPlayers();
				gui.getSystem().resetAllPlayerCardStates();
		    	gui.remove(gui.getCurrentPanel());
		    	GamePanel newGamePanel = new GamePanel(gui);
		    	gui.setCurrentPanel(newGamePanel, "GamePanel");
		    	gui.reset();
		    	PlayerTablePanel newTablePanel = (PlayerTablePanel)newGamePanel.getComponent(0);
		    	newTablePanel.revealAll();
		    	String winnerName = gui.getSystem().getFirstPlayerName();
		    	JOptionPane.showMessageDialog(null, "The Winner is " + winnerName + "!");
		    	
                int check = JOptionPane.showConfirmDialog(null, "게임을 새로 시작하시겠습니까 ?", "new game", JOptionPane.YES_NO_OPTION);
                if (check == JOptionPane.YES_OPTION) {
                	gui.startNewGame();
                }
			}
		});
	    
	    
	    findWinnerButton.setEnabled(false);
		add(revealAllButton);
		add(findWinnerButton);
	}
	
}