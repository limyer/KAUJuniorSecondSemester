package poker;


import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;





public class TextPanel extends JPanel{
	private List<String> playerNames = new ArrayList<String>();
	
	public TextPanel(int playerNum){
		setSize(1000,600);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
        for(int i = 0; i < playerNum; i++) {
        	JLabel typeNameLabel = new JLabel("Type Player" + (i+1) + " Name:");
        	typeNameLabel.setFont(new Font("San Serif", Font.BOLD,20));
        	add(typeNameLabel);
        	JTextField tf = new JTextField(20);
        	tf.setFont(new Font("San Serif", Font.BOLD, 17));
        	tf.setHorizontalAlignment(JTextField.CENTER);
        	int index = i;
        	playerNames.add("");
        	KeyListener listener = new KeyListener() {
        		public void keyTyped(KeyEvent e) {
        			playerNames.set(index, tf.getText());
        		}
        		
        		public void keyReleased(KeyEvent e) {
        			playerNames.set(index, tf.getText());
        		}
        		
        		public void keyPressed(KeyEvent e) {
        			
        		}
        	};
        	tf.addKeyListener(listener);
        	add(tf);
        }
	}
	
	public List<String> getPlayerNames(){
		return this.playerNames;
	}
}