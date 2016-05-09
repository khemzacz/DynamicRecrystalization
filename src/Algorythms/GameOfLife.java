package Algorythms;

import mainPackage.Area;
import mainPackage.Cell;

public class GameOfLife extends MyAlgorythm {
	private int height;
	private int width;
	public GameOfLife(Area a) {
		super(a);
		height=a.getHeight();
		width=a.getWidth();
	}
	
	public boolean step(){
		height=a.getHeight(); width=a.getWidth();
		Cell[][] prev = a.getCellularCopy();
		int ii = 0; int jj = 0; int iii = 0; int jjj = 0;
		int neighbours = 0;
		
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height;j++) //pêtla po komórkach
			{
				
				neighbours = 0; 
				ii = (i - 1);
				for (; ii < (i + 2); ii++) {
					iii = ii;
					jj = (j - 1);
					for (; jj < (j + 2); jj++) {
						jjj = jj; 
						if (ii == i) {
							if (jj == j) {
								continue;
							}
						}
							
						if(a.isPeriodic()){
							if (jj > height - 1) jjj = 0;
							if (ii > width - 1) iii = 0;
							if (jj < 0) jjj = (height - 1);
							if (ii < 0) iii = (width - 1);
							
						}
						else if (a.isZeros()){
							if (jj > height - 1) continue;
							if (ii > width - 1) continue;
							if (jj < 0) continue;
							if (ii < 0) continue;							
						}
						
						if (prev[iii][jjj].isAlive()) {
							neighbours++;
						}
					}
				}


				if ( ( (neighbours == 3 || neighbours == 2) && (prev[i][j].isAlive()) ) 
				|| ( (neighbours == 3) && (!prev[i][j].isAlive()) ) ){
					a.getCellAt(i, j).on();
				}
				else {
					a.getCellAt(i, j).off();
				}
				
			}
		return false;
		
	}

	@Override
	public void setNeighbourhood(Neighbourhood n) {
		// TODO Auto-generated method stub
		
	}

}
