package mainPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JPanel;
import javax.swing.RepaintManager;

public class Cell {
	boolean alive;
	private int i=0; private int j=0;
	int grain;
	
	public Cell(int i, int j){
		alive=false;
		grain=0;
		this.i=i; this.j=j;
	}
	
	public Cell(Cell cell) {
		this.i=cell.i;
		this.j=cell.j;
		this.alive = cell.alive;
		this.grain=cell.grain;
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
