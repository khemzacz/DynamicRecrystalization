package GUI;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class RtsSpeedBarListener implements AdjustmentListener {
	private MainWindow w;
	public RtsSpeedBarListener(MainWindow w){
		this.w=w;
	}
	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		int val = e.getValue();
		switch (val){
		case 1:
			w.lblRtsSpeedLabel.setText("Full throttle");
			w.cellGrid.setSpeed(0);
			break;
		case 2:
			w.lblRtsSpeedLabel.setText("Ultra fast");
			w.cellGrid.setSpeed(16);			
			break;
		case 3:
			w.lblRtsSpeedLabel.setText("Very fast");
			w.cellGrid.setSpeed(33);
			break;
		case 4:
			w.lblRtsSpeedLabel.setText("Fast");
			w.cellGrid.setSpeed(75);
			break;
		case 5:
			w.lblRtsSpeedLabel.setText("Medium");
			w.cellGrid.setSpeed(150);
			break;
		case 6:
			w.lblRtsSpeedLabel.setText("Slow");
			w.cellGrid.setSpeed(250);
			break;
		case 7:
			w.lblRtsSpeedLabel.setText("Very slow");
			w.cellGrid.setSpeed(500);
			break;
		case 8:
			w.lblRtsSpeedLabel.setText("Ultra slow");
			w.cellGrid.setSpeed(1000);
			break;
		case 9:
			w.lblRtsSpeedLabel.setText("Slideshow");
			w.cellGrid.setSpeed(4000);
			break;	
		default:
			break;
		}
	}

}
