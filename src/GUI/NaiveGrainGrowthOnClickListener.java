package GUI;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import mainPackage.Cell;
import mainPackage.Grain;

public class NaiveGrainGrowthOnClickListener implements MouseListener{
	MainWindow w;
	public NaiveGrainGrowthOnClickListener(MainWindow w){
		super();
		this.w=w;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x=e.getX();
		int y=e.getY();
		//System.out.println(x+" "+y);
		int dim = w.cellGrid.getCellSize();
		int i=(int) Math.floor((x-1)/dim); // -1 because there is a 1 px border on view
		int j=(int) Math.floor((y-1)/dim);
		Cell cell = w.cellGrid.a.getCellAt(j, i);
		if (cell.isAlive()){
			cell.nullifyGrain();
			cell.off();
		}
		else{
			Grain grain = new Grain();
			Random rand = new Random();
			//grain.setIdGrain(grain.getGrainsSize());
			grain.setGrainColor(new Color(rand.nextInt(210)+35,rand.nextInt(210)+35,rand.nextInt(210)+35));
			cell.setGrain(grain);
			cell.on();
			
		}
		w.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
