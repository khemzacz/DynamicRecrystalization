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
		break;
		case 2:
			w.lblCaSizeLabel.setText("Small");		
		break;
		case 3:
			w.lblCaSizeLabel.setText("Medium");		
		break;
		case 4:
			w.lblCaSizeLabel.setText("Large");		
		break;
		case 5:
			w.lblCaSizeLabel.setText("Very Large");		
		break;
		default:
		
		
		}
	}

}
