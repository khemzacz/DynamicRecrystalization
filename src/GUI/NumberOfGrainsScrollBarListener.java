package GUI;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class NumberOfGrainsScrollBarListener implements AdjustmentListener{
	MainWindow w;
	public NumberOfGrainsScrollBarListener(MainWindow w){
		super();
		this.w=w;
	}
	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		w.randomGrainsNumber.setText(Integer.toString(w.numberOfGrainsScrollBar.getValue()));
	}
}
