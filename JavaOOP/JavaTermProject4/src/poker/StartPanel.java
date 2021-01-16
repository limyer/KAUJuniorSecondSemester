package poker;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.imageio.ImageIO;




public class StartPanel extends JPanel{
	Image title;
	Image background;	
	PokerGUI parentGUI;
	
	public StartPanel(int num, PokerGUI gui){
		parentGUI = gui;
		setSize(parentGUI.getWidth(),parentGUI.getHeight());
        try {    		
        	Path relPath = Paths.get("");
        	String path = relPath.toAbsolutePath().toString();
    		title = ImageIO.read(new File(path + "\\src\\images\\title.png"));
    		background = ImageIO.read(new File(path + "\\src\\images\\back.png"));

        }
        catch(IOException e) {
        	JOptionPane.showMessageDialog(null, "Error while loading images", "Error", JOptionPane.ERROR_MESSAGE);
        }

        setLayout(null);
        TextPanel text = new TextPanel(num);
        text.setBounds(250,500,500,300);
        add(text);

        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("San Serif", Font.BOLD, 40));
        startButton.setBounds(350, 820, 300, 120);
        startButton.addActionListener(new ActionListener(){ //익명클래스로 리스너 작성
			public void actionPerformed(ActionEvent e){
				List<String> namesArray = text.getPlayerNames();
				Boolean emptyName = false;
				for (int index = 0; index < namesArray.size(); index++) {
					   if (namesArray.get(index).isEmpty()) {
					        emptyName = true;
					   }
				}
				if(emptyName) {
					JOptionPane.showMessageDialog(null, "Please type player name!", "Can't Start the game", JOptionPane.WARNING_MESSAGE);
				}
				else {
					gui.getSystem().setPlayerNames(namesArray);
					parentGUI.startGamePanel();
				}
				
			}
		});
        add(startButton);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(title,0,0,null);
		g.drawImage(background,0,400,null);
	}
}