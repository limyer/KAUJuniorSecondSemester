package poker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PlayerPanel extends JPanel implements ActionListener{
	private static Font PlayerFont = new Font("San Serif", Font.BOLD, 20);
	private Card[] playerCards;
	private Player player;
	private JPanel hand;
	private JLabel playerRank;
	private PokerGUI parentGUI;
	
	public PlayerPanel(PokerGUI gui, Player player) {
		this.parentGUI = gui;
		this.player = player;
		setLayout(new BorderLayout());
		setBackground(GamePanel.GamePanelColor);

        // player 이름을 label로 표기
        JLabel playerName = new JLabel(player.getPlayerName());
        playerName.setVerticalAlignment(SwingConstants.CENTER);
        playerName.setHorizontalAlignment(SwingConstants.LEFT);
        playerName.setFont(PlayerFont);
        playerName.setForeground(Color.YELLOW);

        // PlayerPanelclass 내에 필요한 panel과 버튼
	    hand = new JPanel(new FlowLayout());
	    hand.setBackground(GamePanel.GamePanelColor);
	    // card 버튼을 추가
	    playerCards = player.getPlayerCards();
	    for(int i = 0; i < playerCards.length; i++) {
		    hand.add(new CardButton(gui, playerCards[i], this));
	    }
	    // 앞면 전환 버튼
	    JButton revealButton = new JButton("Reveal");
	    revealButton.addActionListener(this);

	    // 모든 앞면이 전환되었을 때 패의 스코어를 리턴하도록한다.
		this.player.setPlayerRank();
		// 스코어판단 후 카드 보여주기
		// 스코어result가 저장된 숫자 배열을 가져와 다시 카드화 시킴 .... 너무 스파게티로 만들어 죄송합니다...
		int[] scoreResult = player.getPlayerRank().getResult();
		String[] faces = {"0","0","\"2\"","\"3\"","\"4\"","\"5\"","\"6\"","\"7\"","\"8\"","\"9\"","\"10\"","\"J\"","\"Q\"","\"K\"","\"A\""};
	    String[] suits = {"of Clubs", "of Hearts", "of Diamonds", "of Spades"};
		String scoreString = faces[scoreResult[1]]+suits[scoreResult[2]]+ " " + this.player.getPlayerRank().toString();

		playerRank = new JLabel(scoreString);//player.getPlayerRank().toString());
        playerRank.setVerticalAlignment(SwingConstants.TOP);
        playerRank.setHorizontalAlignment(SwingConstants.CENTER);
        playerRank.setFont(PlayerFont);
        playerRank.setForeground(Color.WHITE);
        playerRank.setVisible(false);

        add(playerName, BorderLayout.WEST);
        add(hand, BorderLayout.CENTER);
        add(revealButton, BorderLayout.EAST);
        add(playerRank, BorderLayout.SOUTH);
	}
	
	public void actionPerformed(ActionEvent e){
		Component[] handCards = hand.getComponents();
		for (int i = 0; i < handCards.length; i++) {
			((CardButton) handCards[i]).actionPerformed(e);
		}
		player.setAllReveal(true);
		if (parentGUI.getSystem().checkAllPlayerReveal()) {
			parentGUI.enableWinnerButton();
		}
		showRank();
	}
	
	public void checkAllReveal() {
		for(int i=0; i<playerCards.length;i++) {
			if(!playerCards[i].getIsReveal()) {
				return;
			}
		}
		player.setAllReveal(true);		
		if (parentGUI.getSystem().checkAllPlayerReveal()) {
			parentGUI.enableWinnerButton();
		}
		showRank();

	}
	
	public void showRank() {
		if (this.player.getAllReveal()) {	    
			playerRank.setVisible(true);
		}
	}
}