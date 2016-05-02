package GUI;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class numberOfGrainsScrollBarListener implements AdjustmentListener{
	MainWindow w;
	public numberOfGrainsScrollBarListener(MainWindow w){
		this.w=w;
	}
	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		w.randomGrainsNumber.setText(Integer.toString(w.numberOfGrainsScrollBar.getValue()));
	}
}
