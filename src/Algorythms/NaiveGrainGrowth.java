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
			for (int j = 0; j < width;j++) //p�tla po kom�rkach
			{
				if(prev[i][j].isAlive()) // If current cell has grown skip iteration
					continue; 
				Cell tmp = a.getCellAt(i, j);
				tmp.setGrain(ngbh.determineGrain(prev, i, j));
				last = false; // last iteration will never get here thus we will return true later on.
				if(tmp.getGrain() != null){
					tmp.on();
				}
				
			}
		if(last==true){
			a.setGrownGrains(true);
		}
		return last;
	}
	
	public void setNeighbourhood(Neighbourhood ngbh){
		this.ngbh=ngbh;
	}

}
