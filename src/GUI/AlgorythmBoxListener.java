package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

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
			w.btnRealtimesimulation.setEnabled(true);
			w.cellGrid.getCellsFromArea();
			w.cellGrid.setGameOfLife();
			w.btnSpawnGliderGun.setEnabled(true);
		}
		else if (choice.equals("NaiveGrainGrowth")){
			w.generateGrainsBtn.setEnabled(true);
			w.initializeCells();
			w.btnRealtimesimulation.setEnabled(true);
			w.cellGrid.getCellsFromArea();
			w.cellGrid.setNaiveGrainGrowth();
			w.btnSpawnGliderGun.setEnabled(false);
		}
		else if (choice.equals("DetectEdges")){
			
			//w.initializeCells();
			if (!w.cellGrid.isGrownGrains()){
				JOptionPane.showMessageDialog(w, "You can't detected edges without input.");
				return;
			}
			w.generateGrainsBtn.setEnabled(false);
			w.btnRealtimesimulation.setEnabled(false);
			w.cellGrid.setDetectEdges();
			w.btnSpawnGliderGun.setEnabled(false);
			
		}
		else if (choice.equals("StaticRecrystalization")){
			
			//w.initializeCells();
			//if (!w.cellGrid.isGrownGrains()){
			//	JOptionPane.showMessageDialog(w, "You can't recrystalize without input.");
			//	return;
			//}
			w.generateGrainsBtn.setEnabled(false);
			w.btnRealtimesimulation.setEnabled(false);
			w.cellGrid.setStaticRecrystalization();
			w.btnSpawnGliderGun.setEnabled(false);
			
		}
	}
}
