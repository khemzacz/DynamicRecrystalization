package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlgorythmBoxListener implements ActionListener {
	private MainWindow w;
	public AlgorythmBoxListener(MainWindow w){
		this.w=w;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String choice = (String)w.algorythmBox.getSelectedItem();
		if (choice.equals("GameOfLife")){
			w.generateGrainsBtn.setEnabled(false);
			w.cellGrid.setWidth(50);
			w.cellGrid.setHeight(50);
			w.initializeCells();
			w.cellGrid.getCellsFromArea();
			w.cellGrid.setGameOfLife();
		}
		else if (choice.equals("NaiveGrainGrowth")){
			w.generateGrainsBtn.setEnabled(true);
			w.cellGrid.setWidth(173);
			w.cellGrid.setHeight(173);
			w.initializeCells();
			w.cellGrid.getCellsFromArea();
			w.cellGrid.setNaiveGrainGrowth();
		}
	}
}
