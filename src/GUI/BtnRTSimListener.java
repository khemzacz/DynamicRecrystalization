package GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BtnRTSimListener extends MouseAdapter{
	private MainWindow w;
	public BtnRTSimListener(MainWindow w){
		super();
		this.w=w;
	}
    @Override
    public void mousePressed(MouseEvent e) {
    	Runnable r1 = (Runnable)w.cellGrid; 
		Thread t1 = new Thread(r1);
    	if(w.running){
    		w.cellGrid.onOff();
    		w.btnNextStep.setEnabled(true);
    		w.btnNext15.setEnabled(true);
    		w.btnSpawnGliderGun.setEnabled(true);
    		w.btnClearAll.setEnabled(true);
    		w.bcScroll.setEnabled(true);
    		w.cellGrid.addCellListeners();
    	} else{
    		w.cellGrid.removeListenersFromCells();
    		w.btnNextStep.setEnabled(false);
    		w.btnNext15.setEnabled(false);
    		w.btnSpawnGliderGun.setEnabled(false);
    		w.btnClearAll.setEnabled(false);
    		w.bcScroll.setEnabled(false);
    		w.cellGrid.onOff();
    		t1.start();
    		
    	}
    	w.running = !w.running;
    }
	
}