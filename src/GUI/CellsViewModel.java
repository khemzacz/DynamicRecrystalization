package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

import mainPackage.Cell;
import mainPackage.Area;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.RepaintManager;

public class CellsViewModel extends JPanel implements ComponentListener, MouseListener, Runnable {
	private JPanel self;
	private int lineThickness;
	private int cellSize;
	private int distance;
	private ArrayList<Cell> cells;
	private Area a;
	private boolean shutdown;
	
	public CellsViewModel(Area a){
		shutdown=true;
		self = this;
		cells = new ArrayList<Cell>();
		//panels = new ArrayList<JPanel>();
		lineThickness=1;
		cellSize = 8;
		distance = lineThickness+cellSize;
		this.a =a ;
		getCellsFromArea();
	}
	
	public void getCellsFromArea(){
		cells=a.getReferencesToCells();		//copying references to original cells from Area, i know it's an awful practice
	}
	
	@Override
	public void run() {
		while(!shutdown){
			a.step();
			try {
				
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			repaint();
			
		}
		
	}
	


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void paintCell(int x, int y){
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		int x=0; int y=0; int iteration=1;
		g.setColor(Color.red);
		for(Cell cell : cells){
			x = (distance*cell.getJ());
			y = (distance*cell.getI());
			iteration++;
		}
		
		g.setColor(Color.RED);
		x=0;y=0;
		int line_length = (int) (Math.sqrt(cells.size()) *distance);
		int height = a.getHeight();
		int width = a.getWidth();
		Cell cell;
		for (int i=0;i<height;i++){
			for (int j = 0;j<width;j++){
				cell = a.getCellAt(i,j);
				if(cell.getGrain()!= null)
					g.setColor(cell.getGrain().getGrainColor());
				else g.setColor(Color.RED);
				if(cell.isAlive())
					g.fillRect(cell.getJ()+j*3,cell.getI()+i*3 , 4,4);
			}

			
		}
		
		
		
		
	}

	public void nextStep() {
		a.step();
		repaint();
	}
	
	public void gameOfLife(int steps){
		a.gameOfLife(steps);
		repaint();
	}

	public void spawnGliderGun() {
		a.insertGliderGun((int)Math.sqrt(cells.size())/2, (int)Math.sqrt(cells.size())/2-15);
		repaint();
	}
	public void onOff(){
		shutdown=!shutdown;
	}
	
	
	public void removeListenersFromCells(){
		for ( Cell cell: cells){
		
		}		
	}
	public void addCellListeners(){

		
	}

	public void clearTheArea() {
		for (Cell cell:cells)
		{
			cell.off();
			cell.nullifyGrain();
		}
		repaint();
		
	}

	public void setPeriodicBC() {
		a.setPeriodicBC();
		
	}

	public void setZeroBC() {
		a.setZeroBC();
		
	}

	public void setNumberOfGrains(int grains){
		a.setNumberOfGrains(grains);
	}

	public void setGameOfLife() {
		
	}

	public void setNaiveGrainGrowth() {
		clearTheArea();
		a.setNaiveGrainGrowth();
		repaint();
	}

	public void generateRandomGrains(int value) {
		a.generateRandomGrains(value);
	}

	public void generateEvenlySpreadGrains(int value) {
		// TODO Auto-generated method stub
		
	}

	public void generateRandomGrainsWithRadius() {
		// TODO Auto-generated method stub
		
	}

	
}
