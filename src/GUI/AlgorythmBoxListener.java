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
			w.cellGrid.setGameOfLife();
			w.generateGrainsBtn.setEnabled(false);
		}
		else if (choice.equals("NaiveGrainGrowth")){
			w.cellGrid.setNaiveGrainGrowth();
			w.generateGrainsBtn.setEnabled(true);
		}
	}
}
