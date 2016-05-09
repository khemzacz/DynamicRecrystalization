package GUI;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class CaSizeBarListener implements AdjustmentListener {
	private MainWindow w;

	public CaSizeBarListener(MainWindow w){
		this.w=w;
	}
	
	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		int val = e.getValue();
		switch(val){
		case 1:
			w.lblCaSizeLabel.setText("Very small");
			w.cellGrid.setCaSize(5);
			w.cellGrid.clearTheArea();
			w.cellGrid.a.setDimensions(103,103);
			w.cellGrid.getCellsFromArea();
			w.numberOfGrainsScrollBar.setMaximum(30);
		break;
		case 2:
			w.lblCaSizeLabel.setText("Small");
			w.cellGrid.setCaSize(4);
			w.cellGrid.clearTheArea();
			w.cellGrid.a.setDimensions(129,129);
			w.cellGrid.getCellsFromArea();
			w.numberOfGrainsScrollBar.setMaximum(60);
		break;
		case 3:
			w.lblCaSizeLabel.setText("Medium");
			w.cellGrid.setCaSize(3);
			w.cellGrid.clearTheArea();
			w.cellGrid.a.setDimensions(173,173);
			w.cellGrid.getCellsFromArea();
			w.numberOfGrainsScrollBar.setMaximum(90);
		break;
		case 4:
			w.lblCaSizeLabel.setText("Large");
			w.cellGrid.setCaSize(2);
			w.cellGrid.clearTheArea();
			w.cellGrid.a.setDimensions(258,258);
			w.cellGrid.getCellsFromArea();
			w.numberOfGrainsScrollBar.setMaximum(120);
		break;
		case 5:
			w.lblCaSizeLabel.setText("Very Large");
			w.cellGrid.setCaSize(1);
			w.cellGrid.clearTheArea();
			w.cellGrid.a.setDimensions(519,519);
			w.cellGrid.getCellsFromArea();
			w.numberOfGrainsScrollBar.setMaximum(240);
		break;
		default:
		
		
		}
	}

}
