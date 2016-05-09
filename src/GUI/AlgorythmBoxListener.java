package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlgorythmBoxListener implements ActionListener {
	private MainWindow w;
	public AlgorythmBoxListener(MainWindow w){
		super();
		this.w=w;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String choice = (String)w.algorythmBox.getSelectedItem();
		if (choice.equals("GameOfLife")){
			w.generateGrainsBtn.setEnabled(false);
			w.initializeCells();
			w.cellGrid.getCellsFromArea();
			w.cellGrid.setGameOfLife();
		}
		else if (choice.equals("NaiveGrainGrowth")){
			w.generateGrainsBtn.setEnabled(true);
			w.initializeCells();
			w.cellGrid.getCellsFromArea();
			w.cellGrid.setNaiveGrainGrowth();
		}
	}
}
