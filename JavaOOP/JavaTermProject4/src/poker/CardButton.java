package poker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class CardButton extends JButton implements ActionListener{
	private Card buttonCard;
	private PlayerPanel parentPanel;
	
	public CardButton(PokerGUI gui, Card card, PlayerPanel panel) {
		this.parentPanel = panel;
		setVerticalAlignment(SwingConstants.CENTER);
		setHorizontalAlignment(SwingConstants.LEFT);
		setBorderPainted(false);
		setBackground(GamePanel.GamePanelColor);
		this.buttonCard = card;
		setIcon(CardImages.getBack());
		addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (!buttonCard.getIsReveal()) {
			setIcon(CardImages.getCard(buttonCard));
			buttonCard.setIsReveal(true);
			parentPanel.checkAllReveal();
		}
	}

}
