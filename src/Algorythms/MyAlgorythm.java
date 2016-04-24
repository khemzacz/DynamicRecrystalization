package Algorythms;

import mainPackage.Area;

public class MyAlgorythm {
	protected Area a;
	public MyAlgorythm(Area a){
		this.a=a;
	}
	
	public void step(){
		
	}
	public void steps(int n){
		for(int i =0;i<n;i++){
			step();
		}
		
	}
}
