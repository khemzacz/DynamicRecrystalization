package mainPackage;

import java.awt.Color;
import java.util.ArrayList;
import java.util.UUID;

public class Grain {
	private static ArrayList<Grain> grains = new ArrayList<Grain>();
	private Color grainColor;
	private UUID uniqueID;
	private int cellsAmount;
	public Grain(){
		grains.add(this);
		uniqueID = UUID.randomUUID();
		cellsAmount=0;
	}
	/*public int getIdGrain() {
		return idGrain;
	}
	public void setIdGrain(int idGrain) {
		this.idGrain = idGrain;
	}*/
	public Color getGrainColor() {
		return grainColor;
	}
	public void setGrainColor(Color grainColor) {
		this.grainColor = grainColor;
	}
	public void deleteGrain(){
		grains.remove(this);
	}
	public int getGrainsSize() {
		return grains.size();
	}
	public UUID getIdGrain() {
		return uniqueID;
	}
	public void setIdGrain(UUID idGrain) {
		this.uniqueID = idGrain;
	}
	public void addCell(){ cellsAmount++;}
	public void removeCell(){
		if(cellsAmount<=0){
			deleteGrain();
		}
		cellsAmount--;
		
	}
	public int getCellAmount(){ return cellsAmount;}
}
