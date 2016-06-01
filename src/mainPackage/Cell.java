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
	private boolean inRadius;
	private boolean edge;
	private double roOfCell;
	private boolean recrystalized;

	public Cell(int i, int j){
		this.alive=false;
		this.grain=null;
		this.edge = false;
		this.i=i; this.j=j;
		this.roOfCell=0.0;
		this.setRecrystalized(false);
	}
	
	public Cell(Cell cell) {
		this.i=cell.i;
		this.j=cell.j;
		this.alive = cell.alive; this.edge = false;
		this.grain=cell.copyGrain();
		this.roOfCell=cell.roOfCell;
		this.recrystalized=cell.recrystalized;
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
	  
	public Grain getGrain(){
		return grain;
	}
	
	public void nullifyGrain(){
		if(grain==null)
			return;
		grain.deleteGrain(); // removes from static ArrayList<Grain> grains
		grain=null;
	}

	public void setGrain(Grain grain2) {
		grain=grain2;
	}

	public boolean isInRadius() {
		return inRadius;
	}

	public void setInRadius(boolean inRadius) {
		this.inRadius = inRadius;
	}

	public void setEdge(boolean b) {
		this.edge=b;
	}
	
	public boolean isEdge() {
		return edge;
	}

	public void addDislocationDensity(double d) {
		roOfCell+=d;
		
	}

	public double getRoOfCell() {
		return roOfCell;
	}

	public boolean isRecrystalized() {
		return recrystalized;
	}

	public void setRecrystalized(boolean recrystalized) {
		this.recrystalized = recrystalized;
	}

}
