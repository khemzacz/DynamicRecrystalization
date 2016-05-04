package GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BtnNextStepListener extends MouseAdapter {
	private MainWindow w;
	public BtnNextStepListener(MainWindow w){
		this.w=w;
	}
    @Override
    public void mousePressed(MouseEvent e) {
    	w.cellGrid.nextStep();
    }
}
