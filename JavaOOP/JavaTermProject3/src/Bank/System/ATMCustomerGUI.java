package Bank.System;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 입출금 관련 GUI pannel 인데 frame으로 선언해서 추가하는거도 나쁘지 않을꺼같아요/
public class ATMCustomerGUI extends JFrame{
    JTextField fifityNumTextField = new JTextField();
    JTextField tenNumTextField = new JTextField();
    JButton okButton = new JButton("ok");
    //ATMSystem ATMSystem;
    private int fifity;
    private int ten;

    private ATMSystem atmSystem;
    public ATMCustomerGUI(ATMSystem atmSystem){
        //this.ATMSystem = atmSystem;

        this.atmSystem = atmSystem;
        setSize(600,300);

        JPanel basepanel = new JPanel(new GridLayout(4,2));
        basepanel.add(new JLabel("오만원권: "));
        basepanel.add(fifityNumTextField);
        basepanel.add(new JLabel("만원권: "));
        basepanel.add(tenNumTextField);
        basepanel.add(new JPanel());
        basepanel.add(new JPanel());

        JPanel buttonGruop = new JPanel(new GridLayout());
        basepanel.add(new JLabel("보이스 피싱 신고는 112         "));
        basepanel.add(okButton);
//        basepanel.add(buttonGruop);
        add(basepanel);
        okButton.addActionListener(new OkButtonListener());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    class OkButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent event) {
            String five = fifityNumTextField.getText();
            fifity = Integer.parseInt(five);
            String one = tenNumTextField.getText();
            ten = Integer.parseInt(one);
//            ATMSystem atmSystem = ATMSystem.getInstance();
            // 입출금 구분 해서 실행하기
            if(atmSystem.getCheckAction() == 1){
                atmSystem.deposit(fifity, ten);
            }else if(atmSystem.getCheckAction() == 2){
                atmSystem.wihtdrawl(fifity,ten);
            }
            dispose();
        }
    }
}
