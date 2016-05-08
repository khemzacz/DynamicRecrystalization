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
	boolean running;
	private JPanel contentPane;
	CellsViewModel cellGrid;
	private JPanel panel;
	JButton btnNextStep;
	JButton btnNext15;
	JButton btnSpawnGliderGun;
	JButton btnClearAll;
	JLabel lblCaSizeLabel;
	JComboBox ngbhTypeBox ;
	private Area a; JComboBox bcScroll; 
	JComboBox algorythmBox; JComboBox genTypeBox;
	JLabel grainLabel;
	private JButton btnRealtimesimulation;
	private JPanel rightSidePanel;
	JScrollBar numberOfGrainsScrollBar;
	JLabel randomGrainsNumber;
	JButton generateGrainsBtn;
	private JLabel lblChooseRtsSpeedLabel;
	JScrollBar rtsSpeedBar;
	JLabel lblRtsSpeedLabel; JScrollBar caSizeBar;
	/**
	 * Create the frame.
	 */
	public MainWindow(Area a) {
		setResizable(false);
		running = false;
		setSize(new Dimension(900, 625));
		setMaximumSize(new Dimension(1880, 1060));
		setMinimumSize(new Dimension(500, 500));
		this.a =a ;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 908, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cellGrid = new CellsViewModel(a,this);
		cellGrid.setDoubleBuffered(false);
		cellGrid.setBounds(198, 10, 519, 519);
		contentPane.add(cellGrid);
		cellGrid.setPreferredSize(new Dimension(500, 500));
		cellGrid.setLayout(new GridBagLayout());
		panel = new JPanel();
		panel.setLocation(8, 10);
		panel.setPreferredSize(new Dimension(875, 25));
		panel.setSize(new Dimension(185, 520));
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnNextStep = new JButton("NextStep"); btnNextStep.setBounds(0, 0, 156, 23); btnNextStep.setVerticalAlignment(SwingConstants.TOP); 
		panel.add(btnNextStep);
		
		btnNext15 = new JButton("Next15Steps");	btnNext15.setBounds(0, 25, 156, 23); btnNext15.setVerticalAlignment(SwingConstants.TOP); btnNext15.addMouseListener(new BtnNext15MouseListener(this));
		panel.add(btnNext15);
		
		btnSpawnGliderGun = new JButton("SpawnGliderGun"); btnSpawnGliderGun.setBounds(0, 49, 156, 23); btnSpawnGliderGun.addMouseListener(new BtnSpawnGliderGunListener(this));
		panel.add(btnSpawnGliderGun);
		
		btnRealtimesimulation = new JButton("RealTime(toggle)"); btnRealtimesimulation.setBounds(0, 73, 156, 23); btnRealtimesimulation.addMouseListener(new BtnRTSimListener(this));
		panel.add(btnRealtimesimulation);
		
		btnClearAll = new JButton("ClearAll"); btnClearAll.addMouseListener(new BtnClearAllListener(this));	btnClearAll.setBounds(0, 98, 156, 23);
		panel.add(btnClearAll);
		
		ArrayList<String> pom1 = new ArrayList<String>();
		pom1.add("Periodic");
		pom1.add("Zeros");
		String[] listData = new String [pom1.size()];
		listData = pom1.toArray(listData);
		//bc.setBounds(700, 0, 77, 23);
		bcScroll = new JComboBox(listData); bcScroll.addActionListener(new BoundryConditionScrollListener(this)); bcScroll.setBounds(0, 125, 156, 23);
		panel.add(bcScroll);
		
		ArrayList<String> pom2 = new ArrayList<String>();
		pom2.add("GameOfLife");	pom2.add("NaiveGrainGrowth");
		String[] algorythms = new String [pom2.size()];
		listData = pom2.toArray(algorythms);
		algorythmBox = new JComboBox(listData);	algorythmBox.setBounds(0, 150, 156, 22); algorythmBox.addActionListener(new AlgorythmBoxListener(this));
		panel.add(algorythmBox);
		
		numberOfGrainsScrollBar = new JScrollBar();	numberOfGrainsScrollBar.setOrientation(JScrollBar.HORIZONTAL); numberOfGrainsScrollBar.setMinimum(2);
		numberOfGrainsScrollBar.setValue(3); numberOfGrainsScrollBar.setBounds(0, 214, 185, 23); numberOfGrainsScrollBar.addAdjustmentListener(new NumberOfGrainsScrollBarListener(this));
		panel.add(numberOfGrainsScrollBar);
		
		grainLabel = new JLabel("<html>Number of randomly generated grains</html>");
		grainLabel.setBounds(0, 174, 124, 34);
		panel.add(grainLabel);
		
		randomGrainsNumber = new JLabel("3"); randomGrainsNumber.setBounds(130, 185, 43, 16);
		panel.add(randomGrainsNumber);
		
		generateGrainsBtn = new JButton("generateGrains"); generateGrainsBtn.setBounds(0, 283, 156, 25);
		generateGrainsBtn.setEnabled(false); generateGrainsBtn.addMouseListener(new BtnGenerateGrainsListener(this));
		panel.add(generateGrainsBtn);
		
		
		ArrayList<String> pom3 = new ArrayList<String>();
		pom3.add("random"); pom3.add("evenlySpread"); pom3.add("randomRadius"); pom3.add("onClick");
		String [] genOpt = new String[pom3.size()];
		listData=pom3.toArray(genOpt);
		genTypeBox = new JComboBox(listData); genTypeBox.setBounds(0, 258, 156, 22); 
		genTypeBox.addActionListener(new GenTypeBoxListener(this));
		panel.add(genTypeBox);
		
		JLabel lblTypeOfGeneration = new JLabel("Type Of Generation:");
		lblTypeOfGeneration.setBounds(0, 240, 142, 16);
		panel.add(lblTypeOfGeneration);
		
		JLabel lblNeighbourhoodType = new JLabel("Type of neighbourhood:");
		lblNeighbourhoodType.setBounds(0, 319, 156, 14);
		panel.add(lblNeighbourhoodType);
		
		ArrayList<String> pom4 = new ArrayList<String>();
		pom4.add("Moore"); pom4.add("von neumann"); pom4.add("Hexagonal left"); pom4.add("Hexagonal right"); pom4.add("Hexagonal random"); pom4.add("Pentagonal random");
		String [] ngbhOpt = new String[pom4.size()];
		listData = pom4.toArray(ngbhOpt);
		ngbhTypeBox = new JComboBox(listData); ngbhTypeBox.setBounds(0, 334, 156, 20);
		ngbhTypeBox.addActionListener(new NgbhTypeBoxListener(this));
		
		panel.add(ngbhTypeBox);
		
		btnNextStep.addMouseListener(new BtnNextStepListener(this));
		
		rightSidePanel = new JPanel(); rightSidePanel.setLocation(730, 10);	rightSidePanel.setSize(new Dimension(162, 520));
		rightSidePanel.setPreferredSize(new Dimension(100, 500));
		contentPane.add(rightSidePanel);
		rightSidePanel.setLayout(null);
		
		caSizeBar = new JScrollBar(); caSizeBar.setValue(3);	caSizeBar.setMaximum(15); caSizeBar.setMinimum(1);
		caSizeBar.setOrientation(JScrollBar.HORIZONTAL); caSizeBar.setBounds(0, 21, 162, 22); caSizeBar.addAdjustmentListener(new CaSizeBarListener(this));
		rightSidePanel.add(caSizeBar);
		
		JLabel lbCaChooseSizeLabel = new JLabel("Choose size of CA:");
		lbCaChooseSizeLabel.setBounds(0, 0, 138, 14);
		rightSidePanel.add(lbCaChooseSizeLabel);
		
		lblCaSizeLabel = new JLabel("Medium");
		lblCaSizeLabel.setBounds(0, 43, 162, 14);
		rightSidePanel.add(lblCaSizeLabel);
		
		lblChooseRtsSpeedLabel = new JLabel("<html>Choose real time simulation speed:</html>");
		lblChooseRtsSpeedLabel.setBounds(0, 81, 138, 31);
		rightSidePanel.add(lblChooseRtsSpeedLabel);
		
		rtsSpeedBar = new JScrollBar();	
		rtsSpeedBar.setValue(5);rtsSpeedBar.setMaximum(19);	rtsSpeedBar.setMinimum(1);
		rtsSpeedBar.setOrientation(JScrollBar.HORIZONTAL); rtsSpeedBar.setBounds(0, 110, 162, 22);
		rtsSpeedBar.addAdjustmentListener(new RtsSpeedBarListener(this));
		rightSidePanel.add(rtsSpeedBar);
		
		lblRtsSpeedLabel = new JLabel("Medium");
		lblRtsSpeedLabel.setBounds(0, 132, 162, 14);
		rightSidePanel.add(lblRtsSpeedLabel);
		
	}
	
	
	public void naiveAlgorythm(){
		for (Component cmp :contentPane.getComponents()){
			if(cmp.equals(algorythmBox)) continue;
			cmp.setVisible(false);
		}
		
	}


	public Object getAlgorythmBox() {
		return algorythmBox;
	}


	public Object getCellGrid() {
		return cellGrid;
	}


	public void initializeCells() {
		a.initializeCells();
		
	}
	
	
}
