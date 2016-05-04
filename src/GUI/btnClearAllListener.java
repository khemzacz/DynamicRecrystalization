package GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BtnClearAllListener extends MouseAdapter{
	private MainWindow w;
	public BtnClearAllListener(MainWindow w){
		this.w=w;
	}
    @Override
    public void mousePressed(MouseEvent e) {
    	w.cellGrid.clearTheArea();
    }
	
}
