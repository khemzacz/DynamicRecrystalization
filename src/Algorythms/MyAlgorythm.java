package Algorythms;

import mainPackage.Area;

public abstract class MyAlgorythm {
	protected Area a; protected Neighbourhood ngbh; 
	public MyAlgorythm(Area a){
		this.a=a;
		this.ngbh = new Moore(a);
	}
	
	public abstract boolean step();
	public void steps(int n){
		for(int i =0;i<n;i++){
			step();
		}
		
	}
	
	public abstract void setNeighbourhood(Neighbourhood n);
}
