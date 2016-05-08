package Algorythms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import mainPackage.Area;
import mainPackage.Cell;
import mainPackage.Grain;

public class PentagonalRandom extends Neighbourhood{

	public PentagonalRandom(Area a) {
		super(a);
	}

	@Override
	public Grain determineGrain(Cell[][] tab, int i, int j) {
		int iii=0, jjj=0, height = a.getHeight(), width = a.getWidth(); int pomInt=0;
		neighbours = new HashMap<Grain,Integer>(); ArrayList<Grain> tmp2 = new ArrayList<Grain>(); Random rand = new Random();
		Grain pom = null;
		for (int ii=i-1;ii<i+2;ii++){
			for(int jj = j-1;jj<j+2;jj++){
				
				pomInt = rand.nextInt(4);
				if (pomInt == 0){
					if ((jj==j && ii==i) || (ii==i-1)){
						continue;
					}
				}
				else if (pomInt == 1){
					if ((jj==j && ii==i)|| (ii==i+1)){
						continue;
					}
				}
				else if (pomInt == 2){
					if ((jj==j && ii==i)|| (jj==j-1)){
						continue;
					}
				}
				else if (pomInt == 3){
					if ((jj==j && ii==i)|| (jj==j+1)){
						continue;
					}
				}
				
				
				
				iii = ii; jjj=jj;
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

				if (tab[iii][jjj].isAlive()) {
					//System.out.println("found alive neighbour ");
					pom = tab[iii][jjj].getGrain();
				}
				else{
					//System.out.println("dead neighbour ");
					continue;
				}

				
				if ( pom!= null){
					if(!neighbours.containsKey(pom)){
						neighbours.put(pom, 1);
					}
					else{
						neighbours.put(pom,neighbours.get(pom)+1);
					}
					
				}

			}
		} 
		int pom2=0; int tmp=0;
		for (Map.Entry<Grain, Integer> entry : neighbours.entrySet()){
			tmp = entry.getValue();
			if (pom2 < tmp ){
				pom2 = tmp;
				//pom = entry.getKey();
			}
		}
		for (Map.Entry<Grain, Integer> entry : neighbours.entrySet()){
			tmp = entry.getValue();
			if (pom2 == tmp ){
				tmp2.add(entry.getKey());
			}
		}
		if (tmp2.isEmpty())
			return null;
		return tmp2.get(rand.nextInt(tmp2.size())); // Randomizing grain if equal ammount of neighbours
	}

}
