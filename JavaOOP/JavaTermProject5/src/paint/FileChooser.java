package paint;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser extends JFrame {

    private File f;

    private JTextField filename = new JTextField(), dir = new JTextField();

    public class Open implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            JFileChooser c = new JFileChooser();

            FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON files (*.json)", "json");
            c.setFileFilter(filter);
            c.setAcceptAllFileFilterUsed(false);
            ShapesManager obj = ShapesManager.getInstance();

            int rVal = c.showOpenDialog(FileChooser.this);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                filename.setText(c.getSelectedFile().getName());
                dir.setText(c.getCurrentDirectory().toString());
                String name = dir.getText() + File.separatorChar + filename.getText();

                String s = name.substring(name.lastIndexOf(".") + 1, name.length());

                if (s.equals("json")) {
                    f = new File(name);
                    if (!f.exists()) {
                        JOptionPane.showMessageDialog(null, "Error");
                        return;
                    }
                    obj.loadJson(f);
                } else {
                    JOptionPane.showMessageDialog(null, "Error");
                    return;
                }

            }
            if (rVal == JFileChooser.CANCEL_OPTION) {
                dir.setText(null);
            }
        }
    }

    class Save implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            JFileChooser c = new JFileChooser();
            // Demonstrate "Save" dialog:

            FileNameExtensionFilter filter = null;

            filter = new FileNameExtensionFilter("JSON (*.json)", "json");

            c.setFileFilter(filter);
            c.setAcceptAllFileFilterUsed(false);

            ShapesManager obj = ShapesManager.getInstance();

            int rVal = c.showSaveDialog(FileChooser.this);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                filename.setText(c.getSelectedFile().getName());
                dir.setText(c.getCurrentDirectory().toString());
                String name = dir.getText() + File.separatorChar + filename.getText();

                String s = name.substring(name.lastIndexOf(".") + 1, name.length());
                f = new File(name);
                if (!name.contains(".")) {
                    f = new File(name + ".json");
                } else if (!s.equals("json")) {
                    JOptionPane.showMessageDialog(null, "Error");
                    return;
                }
                obj.saveJson(f);
            }

            
            if (rVal == JFileChooser.CANCEL_OPTION) {
                dir.setText(null);
            }
        }
    }

    
}
