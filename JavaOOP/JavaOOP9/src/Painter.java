import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class Painter{
	
	private static final String[] colorNames = { "Black", "Red", "Green", "Blue", "Yellow", "White"};
	private static final Color[] colors = { Color.BLACK, Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.WHITE};
	
	public static void main(String[] args) {
		JFrame application = new JFrame("Paintbrush");
		
		PaintPanel paintPanel = new PaintPanel();
		
		JList colorList = new JList(colorNames);
		colorList.setVisibleRowCount(6);
		colorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		colorList.addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						paintPanel.changeColor(colors[colorList.getSelectedIndex()]);
					}
				}
				);
		
		JRadioButton widthThreeButton = new JRadioButton("3", true);
		JRadioButton widthFiveButton = new JRadioButton("5", false);
		JRadioButton widthEightButton = new JRadioButton("8", false);
		JRadioButton widthTwelveButton = new JRadioButton("12", false);
		JRadioButton widthSixteenButton = new JRadioButton("16", false);
		JRadioButton widthTwentyButton = new JRadioButton("20", false);

		
		widthThreeButton.addItemListener(
				new ItemListener() {
					public void itemStateChanged(ItemEvent event) {
						paintPanel.changeDrawWidth(3);
					}
				});
		widthFiveButton.addItemListener(
				new ItemListener() {
					public void itemStateChanged(ItemEvent event) {
						paintPanel.changeDrawWidth(5);
					}
				});
		widthEightButton.addItemListener(
				new ItemListener() {
					public void itemStateChanged(ItemEvent event) {
						paintPanel.changeDrawWidth(8);
					}
				});
		widthTwelveButton.addItemListener(
				new ItemListener() {
					public void itemStateChanged(ItemEvent event) {
						paintPanel.changeDrawWidth(12);
					}
				});
		widthSixteenButton.addItemListener(
				new ItemListener() {
					public void itemStateChanged(ItemEvent event) {
						paintPanel.changeDrawWidth(16);
					}
				});
		widthTwentyButton.addItemListener(
				new ItemListener() {
					public void itemStateChanged(ItemEvent event) {
						paintPanel.changeDrawWidth(20);
					}
				});
		


		
		
		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(widthThreeButton);
		radioGroup.add(widthFiveButton);
		radioGroup.add(widthEightButton);
		radioGroup.add(widthTwelveButton);
		radioGroup.add(widthSixteenButton);
		radioGroup.add(widthTwentyButton);
		
		JPanel radioPanel = new JPanel();
		radioPanel.add(widthThreeButton);
		radioPanel.add(widthFiveButton);
		radioPanel.add(widthEightButton);
		radioPanel.add(widthTwelveButton);
		radioPanel.add(widthSixteenButton);
		radioPanel.add(widthTwentyButton);
		
		application.add(paintPanel, BorderLayout.CENTER);
		application.add(colorList, BorderLayout.NORTH);
		application.add(radioPanel, BorderLayout.SOUTH);
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.setSize(600, 800);
		application.setVisible(true);

	}
}