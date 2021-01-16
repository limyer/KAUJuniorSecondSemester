package paint;


import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GUI implements ActionListener, ChangeListener {

    private static final int RESIZE = 1;

    private static final int MOVE = 2;

    private static final int MOVE_GROUP = 3;

    private static final int RESIZE_GROUP = 4;

    private static final int NONE = -1;

    private JPanel card;

    private JFrame frame;

    private JTabbedPane tabbedPane;

    protected Canvas canvas;

    private JButton borderColor;

    private JButton fillColor;

    private boolean ctrlOn = false;

    protected JButton undo;

    protected JButton redo;

    private JButton delete;

    private JButton selectAll;

    private Image img;

    private JColorChooser colorChooser;

    private Object colorSource;

    private Color border;

    private Color fill;

    protected ArrayList<Shape> shapes;
    
    protected ArrayList<Shape> copiedShapes;

    private Map<Object, Class<?>> buttonsMap;
    protected Map<String, Class<?>> classMap;

    protected Shape newShape;

    protected Shape selectedShape;
    
    protected Shape copiedShape;

    protected Select select;
    

    private Point dragPos;

    private int dragFlag;

    private boolean addChange;

    private static GUI instance = null;

    public static GUI getInstance() {
        if (instance == null) {
            instance = new GUI();
        }
        return instance;
    }
    
    public static void restart() {
        if (instance != null) {
        	instance.frame.dispose();
            instance = new GUI();
        }
    }

    private GUI() {

        tabbedPane = new JTabbedPane();
        canvas = new DrawingArea();

        dragFlag = NONE;
        shapes = new ArrayList<>();
        colorChooser = new JColorChooser();
        border = Color.BLACK;

        card = new JPanel();
        buttonsMap = new HashMap<>();
        classMap = new HashMap<>();

        borderColor = new JButton("");
        fillColor = new JButton("");
        redo = new JButton("");
        undo = new JButton("");
        delete = new JButton("");
        selectAll = new JButton("");



        borderColor.addActionListener(this);
        borderColor.setBackground(Color.lightGray);
        borderColor.setBorder(null);
        img = new ImageIcon(this.getClass().getResource("/img/border.png")).getImage();
        borderColor.setIcon(new ImageIcon(img));
        borderColor.setToolTipText("Border Color");
        card.add(borderColor);

        fillColor.addActionListener(this);
        fillColor.setBackground(Color.lightGray);
        fillColor.setBorder(null);
        img = new ImageIcon(this.getClass().getResource("/img/fill.png")).getImage();
        fillColor.setIcon(new ImageIcon(img));
        fillColor.setToolTipText("Fill Color");
        card.add(fillColor);
        
        selectAll.addActionListener(this);
        img = new ImageIcon(this.getClass().getResource("/img/select.png")).getImage();
        selectAll.setIcon(new ImageIcon(img));
        selectAll.setBackground(Color.lightGray);
        selectAll.setToolTipText("Select");
        selectAll.setBorder(null);
        card.add(selectAll);

        delete.addActionListener(this);
        delete.setToolTipText("Delete");
        delete.setBorder(null);
        img = new ImageIcon(this.getClass().getResource("/img/delete.png")).getImage();
        delete.setIcon(new ImageIcon(img));
        delete.setBackground(Color.lightGray);
        card.add(delete);

        undo.addActionListener(this);
        img = new ImageIcon(this.getClass().getResource("/img/undo.png")).getImage();
        undo.setIcon(new ImageIcon(img));
        undo.setBackground(Color.lightGray);
        undo.setToolTipText("Undo");
        undo.setBorder(null);
        undo.setEnabled(false);
        card.add(undo);
        
        redo.addActionListener(this);
        img = new ImageIcon(this.getClass().getResource("/img/redo.png")).getImage();
        redo.setIcon(new ImageIcon(img));
        redo.setBackground(Color.lightGray);
        redo.setBorder(null);
        redo.setToolTipText("Redo");
        redo.setEnabled(false);
        card.add(redo);
        
        card.setBackground(Color.LIGHT_GRAY);

        colorChooser.getSelectionModel().addChangeListener(this);
        colorChooser.setPreviewPanel(new JPanel());
        
        tabbedPane.addTab("Draw", card);

        img = new ImageIcon(this.getClass().getResource("/img/draw.png")).getImage();
        tabbedPane.setBackground(Color.LIGHT_GRAY);
        tabbedPane.addTab("", new ImageIcon(img), card);


        importNewShape("Rectangle");
        importNewShape("LineSegment");
        importNewShape("Ellipse");
        importNewShape("Triangle");
        importNewShape("Text");

        canvas.setBackground(Color.WHITE);
        canvas.setSize(800, 800);
        canvas.setVisible(true);

    }

    private void importNewShape(String name) {
        ClassLoader classLoader = GUI.class.getClassLoader();
        Class<?> shapeClass;
        try {
            shapeClass = classLoader.loadClass("paint." + name);
            importNewShape(shapeClass);
        } catch (ClassNotFoundException e) {
        }
    }

    private boolean shapeExists(String name) {
        for (Object b : buttonsMap.keySet()) {
            JButton temp = (JButton) b;
            if (temp.getToolTipText().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void importNewShape(Class<?> cls) {
        String name;

        try {
            name = cls.getName().substring(cls.getName().lastIndexOf(".") + 1, cls.getName().length());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Invalid Class file!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (shapeExists(name)) {
            JOptionPane.showMessageDialog(frame, "Shape Already Exists!", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JButton newShapeButton = new JButton();
        try {
            Image img = new ImageIcon(this.getClass().getResource("/img/" + name + ".png")).getImage();
            newShapeButton.setIcon(new ImageIcon(img));
            newShapeButton.setBackground(Color.lightGray);
            newShapeButton.setBorder(null);
            newShapeButton.setToolTipText(name);
        } catch (Exception e) {
            newShapeButton = new JButton(name);
            newShapeButton.setToolTipText(name);
        }
        newShapeButton.addActionListener(this);
        buttonsMap.put(newShapeButton, cls);
        classMap.put(name, cls);
        card.add(newShapeButton);
        card.revalidate();
    }

    public void run() {
        frame = new JFrame("Painter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        MenuBar menubar = new MenuBar();
        frame.setJMenuBar(menubar);

        Container pane = frame.getContentPane();
        pane.add(tabbedPane, BorderLayout.NORTH);
        pane.add(canvas, BorderLayout.AFTER_LAST_LINE);
        
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setVisible(true);
    }

    public void deselectShapes() {
        for (int i = 0; i < shapes.size(); i++) {
            shapes.get(i).selected = false;
        }
        select = null;
        selectedShape = null;
        dragFlag = NONE;
    }

    @Override
    public final void actionPerformed(final ActionEvent e) {

        newShape = null;
        if (select != null && !select.done) {
        	select = null;
        }
        if (buttonsMap.containsKey(e.getSource())) {
            deselectShapes();
            try {
            	
                newShape = (Shape) buttonsMap.get(e.getSource()).newInstance();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error Creating Shape!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == borderColor || e.getSource() == fillColor) {
            Window window = SwingUtilities.windowForComponent(borderColor);
            colorSource = e.getSource();
            JDialog dialog = new JDialog(window);
            dialog.setLocation(250, 200);
            dialog.add(colorChooser);
            dialog.pack();
            dialog.setVisible(true);
        } else if (e.getSource() == undo) {
            shapes.clear();
            deselectShapes();
            shapes = ShapesManager.getInstance().undo();
            canvas.repaint();
        } else if (e.getSource() == redo) {
            shapes.clear();
            deselectShapes();
            shapes = ShapesManager.getInstance().redo();
            canvas.repaint();
        } else if (e.getSource() == delete && select != null) {

            for (int i = 0; i < shapes.size(); i++) {
                if (shapes.get(i).selected) {
                    shapes.remove(i);
                    i--;
                }

            }
            ShapesManager.getInstance().addToHistory();
            dragFlag = NONE;
            select = null;
            canvas.repaint();
        } else if (e.getSource() == delete && selectedShape != null) {
            shapes.remove(selectedShape);
            dragFlag = NONE;
            ShapesManager.getInstance().addToHistory();
            canvas.repaint();
        } else if (e.getSource() == selectAll) {
            deselectShapes();
            select = new Select();
        }
    }
    


    private class DrawingArea extends Canvas implements MouseListener, MouseMotionListener, KeyListener {

        private static final long serialVersionUID = 1L;
        private Point curPt;


        public DrawingArea() {
            addMouseMotionListener(this);
            addMouseListener(this);
            addKeyListener(this);
        }


        public void paint(Graphics g) {
            for (int i = 0; i < shapes.size(); i++) {
                shapes.get(i).draw(g);
            }
            if (newShape != null) {
                newShape.draw(g);
            }
            if (select != null) {
            	select.draw(g);
            }
        }


        @Override
        public void mouseDragged(MouseEvent event) {

            curPt = event.getPoint();

            if (select != null) {
                if (!select.done) {
                    select.setBottomRightCorner(curPt);
                    canvas.repaint();
                    return;
                } else if (dragFlag == MOVE_GROUP) {
                    select.translate(curPt.x - dragPos.x, curPt.y - dragPos.y);
                    dragPos = curPt;
                    canvas.repaint();
                    addChange = true;
                    return;
                } else if (dragFlag == RESIZE_GROUP) {
                    select.resizeShapes(dragPos, curPt);
                    canvas.repaint();
                    addChange = true;
                    return;
                }
            }
            if (selectedShape != null && dragFlag != NONE) {
                if (dragFlag == MOVE) {
                    selectedShape.translate(curPt.x - dragPos.x, curPt.y - dragPos.y);
                    dragPos = curPt;

                } else if (dragFlag == RESIZE) {
                    selectedShape.resize(dragPos, curPt);
                }
                addChange = true;
                canvas.repaint();
            }
        }


        @Override
        public void mouseMoved(MouseEvent event) {
            curPt = event.getPoint();
            if (newShape != null) {
                newShape.setBottomRightCorner(curPt);
                canvas.repaint();
            }
        }


        @Override
        public void mouseClicked(MouseEvent event) {
        }


        @Override
        public void mouseEntered(MouseEvent event) {
        }


        @Override
        public void mouseExited(MouseEvent event) {
        }


        @Override
        public void mousePressed(MouseEvent event) {

            curPt = event.getPoint();
            dragFlag = NONE;
            dragPos = curPt;

            if (select != null) {
                if (select.getTopLeftCorner() == null) {
                    select.setTopLeftCorner(curPt);
                } else if (select.done && !select.inBounds(curPt)) {
                    deselectShapes();
                } else if (select.done) {
                    if (select.getShape().contains(curPt)) {
                        dragFlag = MOVE_GROUP;
                        dragPos = curPt;
                    } else {
                        dragFlag = RESIZE_GROUP;
                        dragPos = select.getBottomRightCorner();
                    }
                }
                canvas.repaint();
                return;
            }
            if (selectedShape != null) {
                if (!selectedShape.inBounds(curPt)) {
                    selectedShape.selected = false;
                    selectedShape = null;
                } else {
                    dragFlag = MOVE;
                }
            }

            if (newShape != null) {

                if (newShape.getTopLeftCorner() == null) {
                    newShape.setTopLeftCorner(curPt);
                } else {
                    newShape.setBottomRightCorner(curPt);
                    shapes.add(newShape);
                    ShapesManager.getInstance().addToHistory();
                    newShape = null;
                }
            } else if (selectedShape != null && selectedShape.getAnchorForResize(curPt) != null) {
                dragFlag = RESIZE;
                dragPos = selectedShape.getAnchorForResize(curPt);
            } else {
                dragPos = curPt;
                for (int i = shapes.size() - 1; i >= 0; i--) {
                    if (shapes.get(i).intersect(curPt)) {
                        if (selectedShape != null) {
                            selectedShape.selected = false;
                        }

                        shapes.get(i).selected = true;
                        selectedShape = shapes.get(i);
                        dragFlag = MOVE;
                        break;
                    }
                }
            }
            canvas.repaint();
        }


        @Override
        public void mouseReleased(MouseEvent event) {
            if (dragFlag != NONE && addChange) {
                ShapesManager.getInstance().addToHistory();
                addChange = false;
            }
            dragFlag = NONE;

            if (select != null && !select.done) {

                Rectangle2D newArea = select.getArea(shapes);
                if (newArea == null) {
                    select = null;
                }
                canvas.repaint();
            }
        }
        
        // handle press of any key
        public void keyPressed( KeyEvent event ){
        	if(event.getKeyCode() == KeyEvent.VK_CONTROL) 
        		ctrlOn = true;
        	if(event.getKeyCode() == KeyEvent.VK_C && ctrlOn) {
        		if (selectedShape != null) {
            		copiedShape = selectedShape.clone();
        		}
        		else if(select != null && select.done) {
        			copiedShapes = select.getSelectedShapes();
        		}
        		deselectShapes();
        		JOptionPane.showMessageDialog(null,"Copied");
        		ctrlOn = false;
        	}
        	if(event.getKeyCode() == KeyEvent.VK_V && ctrlOn) {
        		if (copiedShape != null) {                    
        			copiedShape.translate(5, 5);
                    shapes.add(copiedShape);
                    ShapesManager.getInstance().addToHistory();
                    copiedShape = null;

    				canvas.repaint();
        		}
        		else if(copiedShapes != null) {
        			Iterator<Shape> iter = copiedShapes.iterator();
        			while(iter.hasNext()) {
        				Shape copied = iter.next().clone();
        				copied.translate(5, 5);
        				shapes.add(copied);
        			}
                    ShapesManager.getInstance().addToHistory();
                    copiedShapes = null;

    				canvas.repaint();
        		}
        		JOptionPane.showMessageDialog(null,"Pasted");
        		ctrlOn = false;
        	}
        } // end method keyPressed

        // handle release of any key
        public void keyReleased( KeyEvent event ){

        } // end method keyReleased

        // handle press of an action key
        public void keyTyped( KeyEvent event ){
        } // end method keyTyped


    } // end DrawingArea


    @Override
    public void stateChanged(ChangeEvent e) {

        if (colorSource == borderColor) {
            border = colorChooser.getColor();
        }
        if (colorSource == fillColor) {
            fill = colorChooser.getColor();
        }
        if (selectedShape != null && colorSource == borderColor) {
            border = colorChooser.getColor();
            selectedShape.setBorderColor(border);
            ShapesManager.getInstance().addToHistory();
            canvas.repaint();

        }
        if (selectedShape != null && colorSource == fillColor) {
            fill = colorChooser.getColor();
            selectedShape.setFillColor(fill);
            ShapesManager.getInstance().addToHistory();
            canvas.repaint();

        }
        if (newShape != null) {
            newShape.setBorderColor(border);
            newShape.setFillColor(fill);
        }
    }
}
