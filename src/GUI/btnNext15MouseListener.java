package GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class btnNext15MouseListener extends MouseAdapter{
	private MainWindow w;
	public btnNext15MouseListener(MainWindow w){
		this.w=w;
	}
    @Override
    public void mousePressed(MouseEvent e) {
    	w.cellGrid.gameOfLife(15);
    }
}
