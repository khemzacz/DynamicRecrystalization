package Algorythms;

import java.awt.Color;

import mainPackage.Area;
import mainPackage.Cell;
import mainPackage.Grain;

public class EdgeGrainDetection extends MyAlgorythm {

	public EdgeGrainDetection(Area a) {
		super(a);
	}

	@Override
	public void setNeighbourhood(Neighbourhood n) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean step() {
		Grain edgeDetected = new Grain();
		edgeDetected.setGrainColor(new Color(0,0,0));
		int height = a.getHeight(); int width = a.getWidth(); int iii=0; int jjj=0;
		Cell tab[][] = a.getCellularCopy();
		for (int i=0;i < height;i++){
			for (int j =0;j<width;j++){
				
				for (int ii=i-1;ii<i+2;ii++){ // moore loop
					for(int jj = j-1;jj<j+2;jj++){
						if (jj==j && ii==i){
							continue;
						}
						iii = ii; jjj=jj; // check BC
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
						
						if (tab[iii][jjj].getGrain() != ( tab [i][j].getGrain())){
							a.getCellAt(i, j).setGrain(edgeDetected);
							a.getCellAt(i, j).setEdge(true);
						}
						
						
						
					}
				}
				
			}
		}
		return false;
	}

}
