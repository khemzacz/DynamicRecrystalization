package mainPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JPanel;
import javax.swing.RepaintManager;

public class Cell {
	private boolean alive;
	private int i=0; private int j=0;
	private Grain grain;
	
	public Cell(int i, int j){
		alive=false;
		grain=null;
		this.i=i; this.j=j;
	}
	
	public Cell(Cell cell) {
		this.i=cell.i;
		this.j=cell.j;
		this.alive = cell.alive;
		this.grain=cell.copyGrain();
	}

	private Grain copyGrain() {
		Grain tmp = new Grain();
		tmp.setGrainColor(this.grain.getGrainColor());
		tmp.setIdGrain(this.grain.getIdGrain());
		return tmp;
	}

	public boolean isAlive(){
		if(alive)
			return true;
		else
			return false;
	}
	
	public void on(){
		alive=true;
		//setBackground(Color.red);
	}
	public void off(){
		alive=false;
		//setBackground(null);
	}
	
	public int getI(){
		return i;
	}
	
	public int getJ(){
		return j;
	}
	
	public void switchState(){
		if(this.alive){
			alive = false;
			//this.setBackground(null);
		}
			
		else{
			alive=true;
			//this.setBackground(Color.red);
		}
			
	}
	  

}
