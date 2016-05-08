package Algorythms;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import mainPackage.Area;
import mainPackage.Cell;
import mainPackage.Grain;

public abstract class Neighbourhood {
	Area a;
	Map<Grain,Integer> neighbours;// = new HashMap<Grain,Integer>();
	public Neighbourhood (Area a){
		this.a = a;
	}
	public abstract Grain determineGrain(Cell[][] tab, int i, int j);
	
}
