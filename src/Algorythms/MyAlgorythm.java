package Algorythms;

import mainPackage.Area;

public abstract class MyAlgorythm {
	protected Area a;
	public MyAlgorythm(Area a){
		this.a=a;
	}
	
	public boolean step(){
		return true;
	}
	public void steps(int n){
		for(int i =0;i<n;i++){
			step();
		}
		
	}
}
