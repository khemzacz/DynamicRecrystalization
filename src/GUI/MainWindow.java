package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Canvas;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import mainPackage.Area;
import mainPackage.Cell;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Button;
import javax.swing.JTable;
import javax.swing.RepaintManager;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JScrollBar;

public class MainWindow extends JFrame {
	private boolean running;
	private JPanel contentPane;
	private CellsViewModel cellGrid;
	private JPanel panel;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private Area a; private JComboBox bcScroll;
	private JComboBox algorythmBox;
	private JButton btnRealtimesimulation;
	private JPanel rightSidePanel;
	private JScrollBar scrollBar;
	private JLabel randomGrainsNumber;
	/**
	 * Create the frame.
	 */
	public MainWindow(Area a) {
		running = false;
		setSize(new Dimension(900, 601));
		setMaximumSize(new Dimension(1880, 1060));
		setMinimumSize(new Dimension(500, 500));
		this.a =a ;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 908, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cellGrid = new CellsViewModel(a);
		cellGrid.setDoubleBuffered(false);
		cellGrid.setBounds(198, 10, 520, 520);
		contentPane.add(cellGrid);
		cellGrid.setPreferredSize(new Dimension(500, 500));
		cellGrid.setLayout(new GridBagLayout());
		panel = new JPanel();
		panel.setLocation(8, 10);
		panel.setPreferredSize(new Dimension(875, 25));
		panel.setSize(new Dimension(185, 312));
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnNewButton = new JButton("NextStep");
		btnNewButton.setBounds(0, 0, 156, 23);
		btnNewButton.setVerticalAlignment(SwingConstants.TOP);
		panel.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Next15Steps");
		btnNewButton_1.setBounds(0, 25, 156, 23);
		btnNewButton_1.setVerticalAlignment(SwingConstants.TOP);
		btnNewButton_1.addMouseListener(new MouseAdapter(){
			
            @Override
            public void mousePressed(MouseEvent e) {
            	cellGrid.gameOfLife(15);
            }
			
		});
		panel.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("SpawnGliderGun");
		btnNewButton_2.setBounds(0, 49, 156, 23);
		btnNewButton_2.addMouseListener(new MouseAdapter(){
			
            @Override
            public void mousePressed(MouseEvent e) {
            	cellGrid.spawnGliderGun();
            }
			
		});
		panel.add(btnNewButton_2);
		
		btnRealtimesimulation = new JButton("RealTime(toggle)");
		btnRealtimesimulation.setBounds(0, 73, 156, 23);
		btnRealtimesimulation.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
            	Runnable r1 = (Runnable)cellGrid; 
    			Thread t1 = new Thread(r1);
            	if(running){
            		cellGrid.onOff();
            		btnNewButton.setEnabled(true);
            		btnNewButton_1.setEnabled(true);
            		btnNewButton_2.setEnabled(true);
            		btnNewButton_3.setEnabled(true);
            		bcScroll.setEnabled(true);
            		cellGrid.addCellListeners();
            	} else{
            		cellGrid.removeListenersFromCells();
            		btnNewButton.setEnabled(false);
            		btnNewButton_1.setEnabled(false);
            		btnNewButton_2.setEnabled(false);
            		btnNewButton_3.setEnabled(false);
            		bcScroll.setEnabled(false);
            		cellGrid.onOff();
            		t1.start();
            		
            	}
            	running = !running;
            }
			
		});
		panel.add(btnRealtimesimulation);
		
		btnNewButton_3 = new JButton("ClearAll");
		btnNewButton_3.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
            	cellGrid.clearTheArea();
            }
			
		}); 
		btnNewButton_3.setBounds(0, 98, 156, 23);
		panel.add(btnNewButton_3);
		
		ArrayList<String> pom1 = new ArrayList<String>();
		pom1.add("Periodic");
		pom1.add("Zeros");
		String[] listData = new String [pom1.size()];
		listData = pom1.toArray(listData);
		//bc.setBounds(700, 0, 77, 23);
		bcScroll = new JComboBox(listData);
		bcScroll.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String choice = (String)bcScroll.getSelectedItem();
				if (choice.equals("Periodic")){
					cellGrid.setPeriodicBC();
				}
				else if (choice.equals("Zeros")){
					cellGrid.setZeroBC();
				}
			}
			
			
		});
		bcScroll.setBounds(0, 125, 156, 23);
		panel.add(bcScroll);
		ArrayList<String> pom2 = new ArrayList<String>();
		pom2.add("GameOfLife");
		pom2.add("NaiveGrainGrowth");
		String[] algorythms = new String [pom2.size()];
		listData = pom2.toArray(algorythms);
		algorythmBox = new JComboBox(listData);
		algorythmBox.setBounds(0, 150, 156, 22);
		algorythmBox.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String choice = (String)bcScroll.getSelectedItem();
				if (choice.equals("GameOfLife")){
					cellGrid.setPeriodicBC();
				}
				else if (choice.equals("NaiveGrainGrowth")){
					cellGrid.setZeroBC();
				}
			}
			
			
		});
		panel.add(algorythmBox);
		
		scrollBar = new JScrollBar();
		scrollBar.setOrientation(JScrollBar.HORIZONTAL);
		scrollBar.setMinimum(2);
		scrollBar.setValue(3);
		scrollBar.setBounds(0, 214, 185, 34);
		scrollBar.addAdjustmentListener(new AdjustmentListener(){
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				int type=e.getAdjustmentType();
				switch (type){
					case AdjustmentEvent.UNIT_INCREMENT:
						randomGrainsNumber.setText(Integer.toString(scrollBar.getValue()));
					case AdjustmentEvent.UNIT_DECREMENT:
						randomGrainsNumber.setText(Integer.toString(scrollBar.getValue()));
				}
				
			}
		});
		panel.add(scrollBar);
		
		JLabel lblNewLabel = new JLabel("<html>Number of randomly generater grains</html>");
		lblNewLabel.setBounds(0, 174, 124, 34);
		panel.add(lblNewLabel);
		
		randomGrainsNumber = new JLabel("");
		randomGrainsNumber.setBounds(130, 185, 43, 16);
		panel.add(randomGrainsNumber);
		btnNewButton.addMouseListener(new MouseAdapter(){
			
            @Override
            public void mousePressed(MouseEvent e) {
            	cellGrid.nextStep();
            }
			
		});
		
		rightSidePanel = new JPanel();
		rightSidePanel.setLocation(730, 10);
		rightSidePanel.setSize(new Dimension(148, 520));
		rightSidePanel.setPreferredSize(new Dimension(100, 500));
		contentPane.add(rightSidePanel);
		rightSidePanel.setLayout(null);
		
	}
	
	
	public void naiveAlgorythm(){
		for (Component cmp :contentPane.getComponents()){
			if(cmp.equals(algorythmBox)) continue;
			cmp.setVisible(false);
		}
		
	}
}
