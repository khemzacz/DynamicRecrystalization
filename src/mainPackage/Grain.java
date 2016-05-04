package mainPackage;

import java.awt.Color;
import java.util.ArrayList;

public class Grain {
	static ArrayList<Grain> grains = new ArrayList<Grain>();
	private int idGrain;
	private Color grainColor;
	public Grain(){
		grains.add(this);
	}
	public int getIdGrain() {
		return idGrain;
	}
	public void setIdGrain(int idGrain) {
		this.idGrain = idGrain;
	}
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
}
