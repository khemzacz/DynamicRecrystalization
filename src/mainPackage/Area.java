package mainPackage;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import Algorythms.GameOfLife;
import Algorythms.MyAlgorythm;
import Algorythms.NaiveGrainGrowth;

public class Area {
	private Cell[][] tab;
	private int height=0;



	private int width=0;
	private boolean periodic=true;
	private boolean zeros=false;
	private MyAlgorythm algorythm;
	private int numberOfGrains=3;
	
	
	public Area(int width, int height){
		this.height=height; this.width=width;
		tab = new Cell[height][width];
		for (int i=0;i<height;i++){
			for (int j=0;j<width;j++){
				this.tab[i][j] = new Cell(i,j);
								
			}			
		}
		this.algorythm=new GameOfLife(this);
	}
	
	
	public void setCell(int i, int j)
	{
		tab[i][j].on();
	}
	
	public Area getCopy(){
		Area tmp = new Area(width,height);
		for (int i=0;i<height;i++)
			for (int j=0;j<width;j++){
				if (this.tab[i][j].isAlive())
					tmp.setCell(i, j);				
			}
		return tmp;
	}
	
	public ArrayList<Cell> getCellsAsArrayList(){
		ArrayList<Cell> cells = new ArrayList<Cell>();
		for (int i=0;i<height;i++)
			for (int j=0;j<width;j++){
				cells.add(new Cell(tab[i][j]));
			}
		return cells;
	}
	
	public ArrayList<Cell> getReferencesToCells(){
		ArrayList<Cell> cells = new ArrayList<Cell>();
		for (int i=0;i<height;i++)
			for (int j=0;j<width;j++){
				cells.add(tab[i][j]);
			}
		return cells;
	}
	
	public Cell[][] getCellularCopy(){
		Cell[][] pom = new Cell[height][width];
		for (int i=0;i<height;i++){
			for (int j=0;j<width;j++){
				pom[i][j] = new Cell(i,j); // Here i lose grains from algorythm
				if (this.tab[i][j].isAlive())
					pom[i][j].on();
					pom[i][j].setGrain(this.tab[i][j].getGrain());
			}			
		}
		return pom;
	}
	
	public void printCells(){
		for (int i=0;i<height;i++){
			System.out.println();
			for (int j=0;j<width;j++){
				if (this.tab[i][j].isAlive())
					System.out.print("1 ");
				else
					System.out.print("0 ");				
			}			
		}		
		System.out.println();
	}

	public void injectTestingStructures(){
		if (width < 10 || height < 10){
			System.out.println( "Given table is too small to perform tests(height, and width have to be >=10)" );
			return;
		}
		tab[2][2].on(); tab[2][3].on(); tab[2][4].on(); // blinker
		tab[8][1].on(); tab[8][2].on(); tab[7][3].on(); tab[6][0].on(); tab[5][1].on(); tab[5][2].on(); // frog

		tab[0][7].on(); tab[1][6].on(); tab[1][8].on(); tab[2][7].on(); //shamrock
		tab[6][6].on(); tab[6][7].on(); tab[7][6].on(); tab[7][7].on(); // square 2x2
	}
	
	public void gameOfLife(int iterations){
		for (int i = 0; i < iterations; i++)
			algorythm.step();
	}
	
	public boolean step(){
		return algorythm.step();
	}
	
//	public void gameOfLifeStep(){
//		Cell[][] prev = getCellularCopy();
//		int ii = 0; int jj = 0; int iii = 0; int jjj = 0;
//		int neighbours = 0;
//		
//		for (int i = 0; i < width; i++)
//			for (int j = 0; j < height;j++) //pêtla po komórkach
//			{
//				
//				neighbours = 0; 
//				ii = (i - 1);
//				for (; ii < (i + 2); ii++) {
//					iii = ii;
//					jj = (j - 1);
//					for (; jj < (j + 2); jj++) {
//						jjj = jj; 
//						if (ii == i) {
//							if (jj == j) {
//								continue;
//							}
//						}
//							
//						if(periodic){
//							if (jj > height - 1) jjj = 0;
//							if (ii > width - 1) iii = 0;
//							if (jj < 0) jjj = (height - 1);
//							if (ii < 0) iii = (width - 1);
//							
//						}
//						else if (zeros){
//							if (jj > height - 1) continue;
//							if (ii > width - 1) continue;
//							if (jj < 0) continue;
//							if (ii < 0) continue;							
//						}
//						
//						if (prev[iii][jjj].isAlive()) {
//							neighbours++;
//						}
//					}
//				}
//
//
//				if ( ( (neighbours == 3 || neighbours == 2) && (prev[i][j].isAlive()) ) 
//				|| ( (neighbours == 3) && (!prev[i][j].isAlive()) ) ){
//					tab[i][j].on();
//				}
//				else {
//					tab[i][j].off();
//				}
//				
//			}
//		
//	}
	
	public void insertGliderGun(int i,int j){
		
		if ( ( (i+9 ) > (height-1) ) || ( (j+36) > (width-1) ) ) {System.out.println("Not enough space to spawn GliderGun"); return;}
		tab[i+4][j+1].on();
		tab[i+4][j+2].on();
		tab[i+5][j+1].on();
		tab[i+5][j+2].on();
		// /\ Pierwszy Kwadrat
		tab[i+4][j+11].on();
		tab[i+5][j+11].on();
		tab[i+6][j+11].on();
		tab[i+3][j+12].on();
		tab[i+7][j+12].on();
		tab[i+2][j+13].on();
		tab[i+8][j+13].on();
		tab[i+2][j+14].on();
		tab[i+8][j+14].on();
		tab[i+5][j+15].on();
		tab[i+3][j+16].on();
		tab[i+7][j+16].on();
		tab[i+4][j+17].on();
		tab[i+5][j+17].on();
		tab[i+6][j+17].on();
		tab[i+5][j+18].on();
		tab[i+2][j+21].on();
		tab[i+3][j+21].on();
		tab[i+4][j+21].on();
		tab[i+2][j+22].on();
		tab[i+3][j+22].on();
		tab[i+4][j+22].on();
		tab[i+1][j+23].on();
		tab[i+5][j+23].on();
		tab[i][j+25].on();
		tab[i+1][j+25].on();
		tab[i+5][j+25].on();
		tab[i+6][j+25].on();
		tab[i+2][j+35].on();
		tab[i+3][j+35].on();
		tab[i+2][j+36].on();
		tab[i+3][j+36].on();
		
		
	}


	public void setPeriodicBC() {
		periodic=true;
		zeros=false;
		
	}


	public void setZeroBC() {
		zeros=true;
		periodic=false;
		
	}
	
	public int getWidth(){
		return this.width;		
	}


	public int getHeight() {
		return this.height;		
	}


	public Cell getCellAt(int i, int j) {
		return tab[i][j];
	}


	public boolean isPeriodic() {
		return periodic;
	}


	public boolean isZeros() {
		return zeros;
	}


	public int getNumberOfGrains() {
		return numberOfGrains;
	}


	public void setNumberOfGrains(int numberOfGrains) {
		this.numberOfGrains = numberOfGrains;
	}


	public void setNaiveGrainGrowth() {
		algorythm = new NaiveGrainGrowth(this);
		
	}
	
	public void generateRandomGrains(int n){
		ArrayList<Point> generated = new ArrayList<Point> ();
		int j=0, k=0;
		Point result;
		Random rand = new Random();
		Cell cell;
		Grain grain;
		for(int i =0;i<n;i++)
		{
			j = rand.nextInt(height-1);
			k = rand.nextInt(width-1);
			result = checkPoint(generated, new Point(j,k),rand);
			generated.add(result);
			cell = getCellAt(j, k);
			grain = new Grain();
			grain.setIdGrain(i);
			grain.setGrainColor(new Color(rand.nextInt(210)+35,rand.nextInt(210)+35,rand.nextInt(210)+35));
			cell.setGrain(grain);
			cell.on();
		}		
	}
		

	
	public Point checkPoint(ArrayList<Point> generated ,Point p, Random rand){
		int j=0, k = 0 ;
		for (Point point: generated){
			if (point.getX()==p.getX() && point.getY()==p.getY()){
				j = rand.nextInt(height-1);
				k = rand.nextInt(width-1);
				return checkPoint(generated, new Point(j,k),rand);
			}
			
			
		}		
		return p;
	}


	public void generateEvenlySpreadGrains(int x) {
		double sqr = Math.sqrt(x);
		int a = (int) Math.floor(sqr);
		int c= (int)(x-Math.pow(a, 2));
		if (c != 0){
			int h_vect = (int)(173/(a+1));
			int h_start = h_vect/2;
			int v_vect = (173/(a));
			int v_start = v_vect/2;
			Cell cell; Grain grain;
			Random rand = new Random();
			for (int i=0;i<a;i++)
				for (int j=0;j<a;j++){
					int pom = h_start+h_vect*i;
					int pom1 = v_start+j*v_vect;
					cell = getCellAt(pom,pom1);
					grain = new Grain();
					grain.setIdGrain(i*(a)+j); //chyba OK
					grain.setGrainColor(new Color(rand.nextInt(210)+35,rand.nextInt(210)+35,rand.nextInt(210)+35));
					cell.setGrain(grain);
					cell.on();
				}
			v_vect = (173/(c));
			v_start = v_vect/2;
			for (int j=0;j<c;j++){
				cell = getCellAt(h_start+h_vect*a,v_start+j*v_vect);
				grain = new Grain();
				grain.setIdGrain(a*a+j); //chyba OK
				grain.setGrainColor(new Color(rand.nextInt(210)+35,rand.nextInt(210)+35,rand.nextInt(210)+35));
				cell.setGrain(grain);
				cell.on();
			}
		} else {
			int h_vect = (int)(173/(a));
			int h_start = h_vect/2;
			int v_vect = (173/(a));
			int v_start = v_vect/2;
			Cell cell; Grain grain;
			Random rand = new Random();
			for (int i=0;i<a;i++)
				for (int j=0;j<a;j++){
					int pom = h_start+h_vect*i;
					int pom1 = v_start+j*v_vect;
					cell = getCellAt(pom,pom1);
					grain = new Grain();
					grain.setIdGrain(i*(a)+j); //chyba OK
					grain.setGrainColor(new Color(rand.nextInt(210)+35,rand.nextInt(210)+35,rand.nextInt(210)+35));
					cell.setGrain(grain);
					cell.on();
				}
			
		}
	}
	
	public void setHeight(int height) {
		this.height = height;
	}


	public void setWidth(int width) {
		this.width = width;
	}
	public void initializeCells(){
		tab = new Cell[height][width];
		for (int i=0;i<height;i++){
			for (int j=0;j<width;j++){
				this.tab[i][j] = new Cell(i,j);
								
			}			
		}
	}


	public void setGameOfLife() {
		algorythm=new GameOfLife(this);
		
	}
	
	

	
	
	public void generateRandomGrainsWithRadius(int r) {
		Random rand = new Random();
		int k=rand.nextInt(height), l=rand.nextInt(width);
		Cell cell;// = getCellAt(k,l);
		Grain grain; int id=0;
		//grain = new Grain(); 
		//grain.setIdGrain(id);
		//grain.setGrainColor(new Color(rand.nextInt(210)+35,rand.nextInt(210)+35,rand.nextInt(210)+35));
		//cell.setGrain(grain);
		//cell.setInRadius(true);
		//cell.on();
		ArrayList<Cell> cells = new ArrayList<Cell>();
		cells = getReferencesToCells();
		//cells.remove(cell);
		int pom=0;
		if (zeros){
			while(cells.size()>0){
				pom=cells.size();
				cell = cells.get(rand.nextInt(pom));
				grain = new Grain();
				grain.setIdGrain(id);
				grain.setGrainColor(new Color(rand.nextInt(210)+35,rand.nextInt(210)+35,rand.nextInt(210)+35));
				cell.setGrain(grain);
				cell.setInRadius(true);
				cell.on();
				setInRadiusZeros(cell.getI(),cell.getJ(),r,cells);
			}
		}		
		if (periodic){
			while(cells.size()>0){
				pom=cells.size();
				cell = cells.get(rand.nextInt(pom));
				grain = new Grain();
				grain.setIdGrain(id);
				grain.setGrainColor(new Color(rand.nextInt(210)+35,rand.nextInt(210)+35,rand.nextInt(210)+35));
				cell.setGrain(grain);
				cell.setInRadius(true);
				cell.on();
				setInRadiusPeriodic(cell.getI(),cell.getJ(),r,cells);
			}
		}

		
		
		
	}
	
	private void setInRadiusPeriodic(int k, int l, int r, ArrayList<Cell> cells) {
		Cell cell;
		for(int i=k-r;i<=k+r;i++){
			for(int j=l-r;j<=l+r;j++){
				if((Math.pow(i-k, 2) + Math.pow(j-l, 2)) <=  Math.pow(r,2)){
					cell = getCellAt(Math.floorMod(i, height),Math.floorMod(j, width));
					cell.setInRadius(true);
					cells.remove(cell);
				}
			}
		}
		
	}


	public void setInRadiusZeros(int k, int l, int r,ArrayList<Cell> cells){
		Cell cell;
		for(int i=k-r;i<=k+r;i++){
			for(int j=l-r;j<=l+r;j++){
				if((Math.pow(i-k, 2) + Math.pow(j-l, 2)) <=  Math.pow(r,2)){
					if(i<0 || i>=width || j<0 || j>= width)
						continue;
					cell = getCellAt(i,j);
					cell.setInRadius(true);
					cells.remove(cell);
				}
			}
		}
		
	}
}

