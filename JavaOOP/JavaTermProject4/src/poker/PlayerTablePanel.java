package poker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

public class PlayerTablePanel extends JPanel{
	private PokerGUI parentGUI;
    private List<PlayerPanel> playerPanelList = new ArrayList<PlayerPanel>();
    private List<Player> players;
	
	public PlayerTablePanel(PokerGUI gui) {
		this.parentGUI = gui;
		setBackground(GamePanel.GamePanelColor);
		players = gui.getSystem().getPlayers();
        for(int i=0; i<players.size(); i++) {
        	PlayerPanel pp = new PlayerPanel(gui, players.get(i));
        	pp.setBounds(0,(int)(600*(double)i/players.size()),getWidth(),600);
        	playerPanelList.add(pp); 
            add(pp);
        }
	}
	
	public void revealAll() {
    	Iterator<PlayerPanel> ppitr = this.playerPanelList.iterator();
    	
    	while(ppitr.hasNext()) {
    		PlayerPanel pp = ppitr.next();
    		pp.actionPerformed(null);
    	}
	}
	

}