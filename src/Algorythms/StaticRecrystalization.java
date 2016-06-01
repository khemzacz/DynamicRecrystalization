package Algorythms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import mainPackage.Area;
import mainPackage.Cell;

public class StaticRecrystalization extends MyAlgorythm {
	private boolean firstStep; private double GlobalDislocationDensity; private double A=86710969050178.5; private double B=9.41268203527779;
	private double ro; private double deltaT = 0.001; private double time=0; private File file; private FileWriter writer;
	private double prevRo; private double deltaRo; private double roOfCell; private Cell[][] prev;
	public StaticRecrystalization(Area a) {
		super(a);
		this.firstStep = true;
	}

	@Override
	public boolean step() {
		if (firstStep){
			detectEdges();// marks if edge of grain
			try {
			    file = new File("GlobalDislocationDensity.txt");
				writer = new FileWriter(file);
				
			} catch (IOException e) {
				System.out.println("Failed creating a file");
			}
			
			
			firstStep=false;
		} else{
			//*********Aka dislocation cannon*********//
			double width = a.getWidth(); double height = a.getHeight(); Random rand = new Random();
			ro = calculateGlobalDislocationDensity(time); 
			// Ro to file
			try {
				writer.write("\ntime: "+time+"\t"+"ro: "+ro);
				writer.flush();
			} catch (IOException e) {
			}
			time = time+deltaT;
			deltaRo = prevRo-ro;
			roOfCell = deltaRo/(height*width);
			prev = a.getCellularCopy();
			ArrayList<Cell> cellsOnTheEdge = new ArrayList<Cell>();
			for(int i=0;i<width;i++){
				for(int j=0;j<height;j++){
					Cell cell = a.getCellAt(i, j);
					if (cell.isEdge()){
						cell.addDislocationDensity(roOfCell*0.8);
						ro-=roOfCell*0.8;
						cellsOnTheEdge.add(cell);
					}
					else {
						cell.addDislocationDensity(roOfCell*0.2);
						ro-=roOfCell*0.2;
					}
				}	
			}
			double remainingRoOfEdgeCell = ro/(cellsOnTheEdge.size());	
			int pom=0;
			for (Cell cell : cellsOnTheEdge){
				pom = rand.nextInt(1);
				if (pom==1){
					cell.addDislocationDensity(remainingRoOfEdgeCell);
				}
			}
			//******RecrystalizationBelow*******
			double criticalDislocationDensity=4215840142323.42/(height*width);
			for(int i=0;i<width;i++){
				for(int j=0;j<height;j++){
					Cell cell = a.getCellAt(i, j);
					
				}	
			}
			

		}

			

			
		
		
		
		return false;
	}

	private double calculateGlobalDislocationDensity(double t) {
		return A/B + (1 - A/B)  * Math.exp(-B*t);
		
	}

	@Override
	public void setNeighbourhood(Neighbourhood n) {
		// TODO Auto-generated method stub

	}

	public void detectEdges(){
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
							a.getCellAt(i, j).setEdge(true);
						}
					}
				}
			}
		}
		this.firstStep=true;
	}
}
