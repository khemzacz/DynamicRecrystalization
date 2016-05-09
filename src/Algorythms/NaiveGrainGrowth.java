package Algorythms;

import java.util.HashMap;

import mainPackage.Area;
import mainPackage.Cell;

public class NaiveGrainGrowth extends MyAlgorythm {
	private int height=0; private int width=0;
	
	public NaiveGrainGrowth(Area a) {
		super(a);
		height=a.getHeight(); width=a.getWidth();
	}

	public boolean step(){
		height=a.getHeight(); width=a.getWidth();
		boolean last = true; 
		Cell[][] prev = a.getCellularCopy();
		//int ii = 0; int jj = 0; int iii = 0; int jjj = 0;
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width;j++) //pêtla po komórkach
			{
				if(prev[i][j].isAlive())
					continue; 
				Cell tmp = a.getCellAt(i, j);
				tmp.setGrain(ngbh.determineGrain(prev, i, j));
				last = false;
				if(tmp.getGrain() != null){
					tmp.on();
				}
				
			}
		return last;
	}
	
	public void setNeighbourhood(Neighbourhood ngbh){
		this.ngbh=ngbh;
	}

}
