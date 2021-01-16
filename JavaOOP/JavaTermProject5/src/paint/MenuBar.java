package paint;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;


import java.awt.*;


public class MenuBar extends JMenuBar{
   

   private JMenu fileMenu;
   private JMenuItem load,saveJson,exit,newOne;
   private ImageIcon img;
   
   public MenuBar()
   {
      this.setBackground(Color.WHITE);
      
      fileMenu = new JMenu("File");
      fileMenu.setMnemonic('F');
      
      img = new ImageIcon(this.getClass().getResource("/img/load.png"));
      load = new JMenuItem("Open",img);
      load.setMnemonic('O');

      img = new ImageIcon(this.getClass().getResource("/img/save.png"));
      saveJson = new JMenuItem("Save",img);
      saveJson.setMnemonic('S');

      

      img = new ImageIcon(this.getClass().getResource("/img/exit.png"));
      exit = new JMenuItem("Exit",img); 
      exit.setMnemonic('E');
      exit.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e) {
            System.exit(0);
         }
         
      });

      img = new ImageIcon(this.getClass().getResource("/img/new.png"));
      newOne = new JMenuItem("New", img);
      newOne.setMnemonic('N');
      newOne.addActionListener(new ActionListener( ) {
         @Override
         public void actionPerformed(ActionEvent arg0) {
        	 GUI.restart();
        	 GUI.getInstance().run();
         }
         
         
      });
      
      FileChooser action = new FileChooser();
      FileChooser.Open openAction = action.new Open();
      FileChooser.Save saveAction = action.new Save();
      
      saveJson.addActionListener(saveAction);
      load.addActionListener(openAction);





      fileMenu.add(newOne);
      fileMenu.add(saveJson);
      fileMenu.add(load);
      fileMenu.add(exit);
      
      this.add(fileMenu);
      
      
   }

}