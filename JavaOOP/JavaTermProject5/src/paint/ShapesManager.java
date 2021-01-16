package paint;


import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;



public class ShapesManager {


    private ArrayList<ArrayList<Shape>> history;


    int historyIndex;


    private static ShapesManager instance = null;


    private ShapesManager() {
        history = new ArrayList<>();
        history.add(new ArrayList<Shape>());
        historyIndex = 0;
    }


    public static ShapesManager getInstance() {
        if (instance == null) {
            instance = new ShapesManager();
        }
        return instance;
    }


    public ArrayList<Shape> undo() {
        if (historyIndex == 0) {
            return null;
        }
        GUI.getInstance().undo.setEnabled(historyIndex - 1 == 0 ? false : true);
        GUI.getInstance().redo.setEnabled(true);
        ArrayList<Shape> temp = new ArrayList<>();
        for (int i = 0; i < history.get(historyIndex - 1).size(); i++) {
            temp.add(history.get(historyIndex - 1).get(i).clone());
        }
        historyIndex--;

        return new ArrayList<Shape>(temp);
    }


    public ArrayList<Shape> redo() {
        if (historyIndex == history.size() - 1) {
            return null;
        }
        GUI.getInstance().undo.setEnabled(true);
        GUI.getInstance().redo.setEnabled(historyIndex + 1 == history.size() - 1 ? false : true);
        ArrayList<Shape> temp = new ArrayList<>();
        for (int i = 0; i < history.get(historyIndex + 1).size(); i++) {
            temp.add(history.get(historyIndex + 1).get(i).clone());
        }
        historyIndex++;
        return new ArrayList<Shape>(temp);
    }


    public void addToHistory() {
        while (historyIndex != history.size() - 1) {
            history.remove(history.size() - 1);
        }
        ArrayList<Shape> temp = new ArrayList<>();
        for (int i = 0; i < GUI.getInstance().shapes.size(); i++) {
            temp.add(GUI.getInstance().shapes.get(i).clone());
        }

        history.add(temp);
        historyIndex++;
        GUI.getInstance().undo.setEnabled(true);
        GUI.getInstance().redo.setEnabled(false);
    }

    public void saveJson(File file) {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write("{\"shapes\":[" + System.getProperty("line.separator"));
            for (int i = 0; i < GUI.getInstance().shapes.size(); i++) {

                writer.write("{\"ClassName\":" + "\"" + GUI.getInstance().shapes.get(i).getClass().toString() + "\",");
                writer.write("\"topLeftx\":" + "\" " + GUI.getInstance().shapes.get(i).topLeftCorner.x + "\",");
                writer.write("\"topLefty\":" + "\" " + GUI.getInstance().shapes.get(i).topLeftCorner.y + "\",");
                writer.write("\"bottomRightx\":" + "\" " + GUI.getInstance().shapes.get(i).bottomRightCorner.x + "\",");
                writer.write("\"bottomrighty\":" + "\" " + GUI.getInstance().shapes.get(i).bottomRightCorner.y + "\",");
                writer.write("\"borderColor\":" + "\" " + GUI.getInstance().shapes.get(i).borderColor + "\",");
                writer.write("\"fillColor\":" + "\" " + GUI.getInstance().shapes.get(i).fillColor + "\",");
                writer.write("\"text\":" + "\" " + GUI.getInstance().shapes.get(i).text + "\"}");
                if (i != GUI.getInstance().shapes.size() - 1) {
                    writer.write(",");
                }
                writer.write(System.getProperty("line.separator"));
            }

            writer.write("]}");
            writer.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }

    }


    public void loadJson(File file) {

        String[] arr = new String[8];
        try {
            File readFile = file;
            Scanner in = new Scanner(readFile);
            GUI.getInstance().shapes.clear();
            GUI.getInstance().undo.setEnabled(false);
            GUI.getInstance().redo.setEnabled(false);
            GUI.getInstance().newShape = GUI.getInstance().selectedShape = GUI.getInstance().select = null;

            String s = in.nextLine();
            while (in.hasNext()) {
                s = in.nextLine();

                if (s.equals("]}"))
                    break;

                String data = "";
                int cnt = 0;
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == ' ') {
                        i++;
                        while (s.charAt(i) != '"') {
                            data += s.charAt(i);
                            i++;
                        }
                        arr[cnt] = data;
                        cnt++;
                        data = "";
                    }
                }

                Shape newShape;
                String className = "";
                try {
                    className = arr[0].substring(arr[0].indexOf("paint") + 6, arr[0].length());
                    if (className.equalsIgnoreCase("Text")) {
                        String text = arr[7];
                        newShape = (Shape) new Text(text);
                    }
                    else {
                        newShape = (Shape) GUI.getInstance().classMap.get(className).newInstance();
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Missing Shape class file: " + className + ".class\n"
                            + "Please import the shape and try again", "Error", JOptionPane.ERROR_MESSAGE);
                    GUI.getInstance().shapes.clear();
                    return;
                }

                newShape.topLeftCorner = new Point();
                newShape.bottomRightCorner = new Point();
                newShape.topLeftCorner.x = Integer.parseInt(arr[1]);
                newShape.topLeftCorner.y = Integer.parseInt(arr[2]);
                newShape.bottomRightCorner.x = Integer.parseInt(arr[3]);
                newShape.bottomRightCorner.y = Integer.parseInt(arr[4]);
                Scanner sc = new Scanner(arr[5]);
                sc.useDelimiter("\\D+");
                newShape.borderColor = new Color(sc.nextInt(), sc.nextInt(), sc.nextInt());
                if (!arr[6].equals("null")) {
                    sc = new Scanner(arr[6]);
                    sc.useDelimiter("\\D+");
                    newShape.fillColor = new Color(sc.nextInt(), sc.nextInt(), sc.nextInt());
                } else {
                    newShape.fillColor = null;
                }

                GUI.getInstance().shapes.add(newShape);

            }
            in.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
            GUI.getInstance().shapes.clear();

        }

        addToHistory();
        GUI.getInstance().canvas.repaint();

    }
}
