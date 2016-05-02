package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoundryConditionScrollListener implements ActionListener{
	private MainWindow w;
	
	public BoundryConditionScrollListener(MainWindow w){
		this.w=w;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String choice = (String)w.bcScroll.getSelectedItem();
		if (choice.equals("Periodic")){
			w.cellGrid.setPeriodicBC();
		}
		else if (choice.equals("Zeros")){
			w.cellGrid.setZeroBC();
		}
	}
}
