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
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import mainPackage.Cell;
import mainPackage.Area;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.RepaintManager;

public class CellsViewModel extends JPanel implements ComponentListener, MouseListener, Runnable {
	private JPanel self; private MainWindow w;
	private int lineThickness; private int size;
	private int cellSize;
	private int distance; private int speed;
	private ArrayList<Cell> cells; private Lock l;
	Area a;
	private boolean shutdown; private boolean extShutdown; private boolean grownGrains;
	
	public boolean isGrownGrains() {
		return grownGrains;
	}

	public CellsViewModel(Area a, MainWindow w){
		shutdown=true; extShutdown = true; grownGrains= false;
		this.w=w; size=3;
		self = this;
		cells = new ArrayList<Cell>();
		//panels = new ArrayList<JPanel>();
		this.speed = 66;
		lineThickness=1;
		cellSize = 8;
		distance = lineThickness+cellSize;
		this.a =a ;
		getCellsFromArea();
		l = new ReentrantLock();
	}
	
	public void getCellsFromArea(){
		cells=a.getReferencesToCells();		//copying references to original cells from Area, i know it's an awful practice
	}
	
	@Override
	public void run() {
		while(!(shutdown || extShutdown)){
			shutdown = a.step();
			grownGrains = shutdown; // dataReady - if Grains are Grown we can use recrystalization
			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			repaint();
			
		}
		w.cellGrid.shutdown = true;
		w.running = false;
		w.btnNextStep.setEnabled(true);
		w.btnNext15.setEnabled(true);
		w.btnSpawnGliderGun.setEnabled(true);
		w.btnClearAll.setEnabled(true);
		w.bcScroll.setEnabled(true);
		w.algorythmBox.setEnabled(true);
		w.numberOfGrainsScrollBar.setEnabled(true);
		w.genTypeBox.setEnabled(true);
		w.generateGrainsBtn.setEnabled(true);
		w.ngbhTypeBox.setEnabled(true);
		w.rtsSpeedBar.setEnabled(true);
		w.caSizeBar.setEnabled(true);
		w.cellGrid.addCellListeners();
	}
	


	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		
	}

	@Override
	public void componentResized(ComponentEvent arg0) {
		
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		
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
		g.setColor(Color.black);
		g.drawRect(0, 0, size*width+1, size*height+1);
		for (int i=0;i<height;i++){
			for (int j = 0;j<width;j++){
				cell = a.getCellAt(i,j);
				if(cell.getGrain()!= null)
					g.setColor(cell.getGrain().getGrainColor());
				else g.setColor(Color.RED);
				if(cell.isAlive())
					g.fillRect(cell.getJ()+j*(size-1)+1,cell.getI()+i*(size-1)+1 , size,size);
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
		grownGrains = false;
		for (Cell cell:cells)
		{
			cell.nullifyGrain();
			cell.off();
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
		clearTheArea();
		a.setGameOfLife();
		repaint();
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
		a.generateEvenlySpreadGrains(value);
	}

	public void generateRandomGrainsWithRadius(int value) {
		clearTheArea();
		a.generateRandomGrainsWithRadius(value);
		
	}
	


	public void setSpeed(int i) {
		this.speed=i;
		
	}

	public int getCellSize() {
		return size;
	}

	public void setCaSize(int s) {
		size = s;
	}

	public boolean isShutdown() {
		return shutdown;
	}

	public boolean isExtShutDown() {
		return extShutdown;
	}

	public void setExtShutDown(boolean extShutDown) {
		this.extShutdown = extShutDown;
	}

	public void setDetectEdges() {
		a.setDetectEdges();
		
	}
	
}
