package GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class btnSpawnGliderGunListener extends MouseAdapter {
	private MainWindow w;
	public btnSpawnGliderGunListener(MainWindow w){
		this.w=w;
	}
    @Override
    public void mousePressed(MouseEvent e) {
    	w.cellGrid.spawnGliderGun();
    }
}
