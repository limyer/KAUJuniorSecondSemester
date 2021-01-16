package poker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;


public class MenuBarGUI extends JMenuBar{
    private final int[] memberNum = {1, 2, 3, 4, 5};
    private JRadioButtonMenuItem[] numberOfMembers; // 게임 참여인원 조정 버튼
    private ButtonGroup memberButtonGroup;
    private int num = 4;
    private PokerGUI parentGUI;
    
	public MenuBarGUI(PokerGUI GUI) {
		this.parentGUI = GUI;

        // file 메뉴 내 메뉴에 대한 설정
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');
        // about
        JMenuItem aboutItem = new JMenuItem("About....");
        aboutItem.setMnemonic('A');
        fileMenu.add(aboutItem);
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Poker made by Java Class in 11teams",
                        "About",JOptionPane.PLAIN_MESSAGE);
            }
        });
        // exit game
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic('X');
        fileMenu.add(exitItem);
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(fileMenu);// menubar에 filemenu 추가

        // setting 안에 member 수 설정 버튼 만들기
        JMenu settingMenu = new JMenu("Game");
        settingMenu.setMnemonic('G');
        
        // new Game
        JMenuItem newGame = new JMenuItem("New Game Start");
        newGame.setMnemonic('N');
        settingMenu.add(newGame);
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int check = JOptionPane.showConfirmDialog(null, "게임을 새로 시작하시겠습니까 ?", "new game", JOptionPane.YES_NO_OPTION);
                if (check == JOptionPane.YES_OPTION) {
                	parentGUI.startNewGame();
                }
            }
        });


        // memebr수 버튼 구성
        String[] numbers = {"1","2","3","4","5"}; //MAX = 5
        JMenu membersMenu = new JMenu("Set Player Number");
        membersMenu.setMnemonic('S');
        // radiobutton으로 만들어 하나만 고를수 있도록 설정정
        numberOfMembers = new JRadioButtonMenuItem[memberNum.length];
        memberButtonGroup = new ButtonGroup();
        ItemHandler itemHandler = new ItemHandler();
        for(int i = 0; i< numbers.length; i++){
            numberOfMembers[i] = new JRadioButtonMenuItem(numbers[i]);
            membersMenu.add(numberOfMembers[i]);
            memberButtonGroup.add(numberOfMembers[i]);
            numberOfMembers[i].addActionListener(itemHandler);
        }
        numberOfMembers[parentGUI.getNumberOfPlayers()-1].setSelected(true);
        settingMenu.add(membersMenu);
        settingMenu.addSeparator();

        add(settingMenu);
	}
	
    // member수 버튼 입력에 대한 이벤트 생성
    private class ItemHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
        	parentGUI.setNumberOfPlayers(Integer.parseInt(event.getActionCommand()));
        }
    }
}