package Bank.System;
import Bank.ATMDatabase;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowTransactionGUI extends JFrame {

    private ATMSystem atmSystem;
    private ATMDatabase database=ATMDatabase.getInstance(); // 데이터베이스
    List<String> transactions = database.getTransaction();
    JTextArea transactionarea;
    JButton okButton = new JButton("ok");

    public ShowTransactionGUI(ATMSystem atmSystem){
        this.atmSystem = atmSystem;
        setSize(500,500);
        setTitle("ATM");
        JPanel transactionpanel = new JPanel();
        transactionpanel.add(transactionarea = new JTextArea(20,45));
        //printTransaction(transactions);

        for(int i =0; i<transactions.size();i++) {
            transactionarea.append(transactions.get(i) + "\n");
        }
        transactionpanel.add(new JScrollPane(transactionarea));
        transactionpanel.add(okButton);
        add(transactionpanel);
        okButton.addActionListener(new OkButtonListener());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    class OkButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event) {
            dispose();
        }
    }
}
