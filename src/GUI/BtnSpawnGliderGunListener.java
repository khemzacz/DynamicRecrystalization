package GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BtnSpawnGliderGunListener extends MouseAdapter {
	private MainWindow w;
	public BtnSpawnGliderGunListener(MainWindow w){
		super();
		this.w=w;
	}
    @Override
    public void mousePressed(MouseEvent e) {
    	w.cellGrid.spawnGliderGun();
    }
}
