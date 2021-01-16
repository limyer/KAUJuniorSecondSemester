package SubFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.imageio.ImageIO;
import javax.swing.JDialog;

import javax.swing.JFileChooser.*;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import Frame.DesktopPane;
import Frame.MainFrame;
import Frame.MainTest;
import SubPanel.DrawPanel;
import figureType.Img;
import SubFrame.CenterPanel;
import information.Information;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;



public class MenuBar extends JMenuBar{
   
   JLabel label = new JLabel("Hello");


   
   private JMenu fileMenu;
   private JMenuItem open,save,exit,newOne;
   private JMenuItem inform;
   private JMenu colorMenu;
   private JMenuItem colorChooser;
   private JLabel img = new JLabel();
   
   public MenuBar()
   {
      this.setBackground(Color.WHITE);
      
      fileMenu = new JMenu("File");
      fileMenu.setMnemonic('F');
      
      ImageIcon openfile = new ImageIcon("resource/openfile.png");
      open = new JMenuItem("Open",openfile);
      open.setMnemonic('O');
      open.addActionListener(new ActionListener(){

         @Override
         public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            
            JFileChooser jfilechooser = new JFileChooser();
            jfilechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);         
            jfilechooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("JPG & PNG Images", "jpg", "png"));
            int judge = jfilechooser.showOpenDialog(null);
            
            switch(judge)
            {
            case JFileChooser.APPROVE_OPTION : 
               
            	Information.fPath = jfilechooser.getSelectedFile().getPath();
                Information.setCurrentMode(Information.MODE_DRAW_IMAGE);
               
                break;
            case JFileChooser.CANCEL_OPTION : 
               
               break;
            }
            
         
            
         }
      });
      
      ImageIcon savefile = new ImageIcon("resource/savefile.png");
      save = new JMenuItem("Save",savefile);
      save.setMnemonic('S');
      save.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            
               JFileChooser jfilechooser = new JFileChooser();
               int judge = jfilechooser.showSaveDialog(null);
            
               switch(judge)
               {
                  case JFileChooser.APPROVE_OPTION : 
                     String path =jfilechooser.getSelectedFile().getPath();
                     BufferedImage img = new BufferedImage(Information.getCurrentJPanel().getWidth(), Information.getCurrentJPanel().getHeight(), BufferedImage.TYPE_INT_RGB);
                     Graphics2D g2d = img.createGraphics();
                     Information.getCurrentJPanel().printAll(g2d);
                     g2d.dispose();
                     try {
                         ImageIO.write(img, "png", new File(path+".png"));
                     } catch (IOException ex) {
                         ex.printStackTrace();
                     }
                     
                     break;
                  case JFileChooser.CANCEL_OPTION : 
                     return;   
               }
         }
            
         
      });
      
      
      ImageIcon exitfile = new ImageIcon("resource/exit.png");
      exit = new JMenuItem("Exit",exitfile); 
      exit.setMnemonic('E');
      exit.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            System.exit(0);
         }
         
      });
      
      ImageIcon newDocument = new ImageIcon("resource/newDocument.png");
      newOne = new JMenuItem("New", newDocument);
      newOne.setMnemonic('N');
      newOne.addActionListener(new ActionListener( ) {
         @Override
         public void actionPerformed(ActionEvent arg0) {
            DrawPanel current = Information.getCurrentJPanel();
            if(current!=null)
            {   
               current.clearFigure();
            }
            else
            {
               JOptionPane.showMessageDialog(null,"Error to find current Jpanel","ERROR",JOptionPane.ERROR_MESSAGE);
            }
         }
         
         
      });

      fileMenu.add(newOne);
      fileMenu.add(open);
      fileMenu.add(save);
      fileMenu.add(exit);
      
      this.add(fileMenu);
      
      
      colorMenu = new JMenu("Color");
      colorMenu.setMnemonic('C');
      
      colorChooser = new JMenuItem("colorChooser");
      
      colorChooser.addActionListener(new ActionListener(){
         
         @Override
         public void actionPerformed(ActionEvent e){
            String cmd = e.getActionCommand();

             

             if (cmd.equals("colorChooser")) { 

                Color selectedColor = JColorChooser.showDialog(null, "Color", Color.BLACK); 

                if (selectedColor != null)
                 Information.setCurrentColor(selectedColor.getRed(), selectedColor.getGreen(), selectedColor.getBlue()); 
             }
      
         }
      });
      colorMenu.add(colorChooser);
      this.add(colorMenu);
      
   }

}