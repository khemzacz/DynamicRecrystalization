package mainPackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import Algorythms.MyAlgorythm;
import Algorythms.Neighbourhood;

public class MonteCarlo extends MyAlgorythm {
	private int width; private int height;
	private HashMap<Grain, Integer> neighbours; 
	public MonteCarlo(Area a) {
		super(a);
		width = a.getWidth(); height=a.getHeight();
	}

	@Override
	public boolean step() {
		int iii=0, jjj=0;
		neighbours = new HashMap<Grain,Integer>(); ArrayList<Grain> tmp2 = new ArrayList<Grain>(); Random rand = new Random();
		Grain pom=null;Grain center=null; int e=0; int newE=0; double p;
		for (int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				center = a.getCellAt(i, j).getGrain();
				for (int ii=i-1;ii<i+2;ii++){
					for(int jj = j-1;jj<j+2;jj++){
						if (jj==j && ii==i){
							continue;
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
						pom = a.getCellAt(iii, jjj).getGrain();
						
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
				//******Hashmap of neighbours, and their size as (key-neighbourType, value-occuranceOfNeighbour)
				tmp2 = new ArrayList<Grain> (neighbours.keySet());
				e=8;
				for (Grain grain : neighbours.keySet()){
					if (center==grain){
						e-=neighbours.get(grain);
					}
				}

				
				Grain tested = tmp2.get(rand.nextInt(tmp2.size()));
				newE=(8-neighbours.get(tested));
						
				if((newE-e) <=0){
					a.getCellAt(i, j).setGrain(tested);
				}/*
				for (Grain inner : neighbours.keySet()){
					newE=8;
					for (Grain outer : neighbours.keySet()){
						if (inner==outer){
							newE-=neighbours.get(inner);
							if(newE<=e){ 
								a.getCellAt(i, j).setGrain(outer);
							}
						}
					}
				}*/
				tmp2 = new ArrayList<Grain>();
				neighbours = new HashMap<Grain,Integer>();
			}
		}
		
		
		
		return false;
	}

	@Override
	public void setNeighbourhood(Neighbourhood n) {
		this.ngbh= n;
		
	}



}
