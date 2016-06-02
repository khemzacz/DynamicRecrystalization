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
import mainPackage.Grain;

public class StaticRecrystalization extends MyAlgorythm {
	private boolean firstStep; private double GlobalDislocationDensity; private double A=86710969050178.5; private double B=9.41268203527779;
	private double ro; private double deltaT = 0.001; private double time=0; private File file; private FileWriter writer;
	private double prevRo; private double deltaRo; private double roOfCell; private Cell[][] prev; private double width; private double height;
	private ArrayList<Cell> recrystalizedCells; private ArrayList<Cell> cellsOnTheEdge; private double criticalDislocationDensity; private double sumRo;
	private double sigma=0;
	public StaticRecrystalization(Area a) {
		super(a);
		this.firstStep = true;
		this.width=a.getWidth(); this.height = a.getHeight();
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width;j++) //pêtla po komórkach
			{
				a.getCellAt(i, j).setRecrystalized(false);
			}
		this.prevRo=0; this.deltaRo=0; this.sumRo=0;
	}

	@Override
	public boolean step() {
		boolean last = true;
		if (firstStep){
			criticalDislocationDensity=4215840142323.42/(height*width);
			detectEdges();// marks if edge of grain
			try {
			    file = new File("GlobalDislocationDensity.txt");
				writer = new FileWriter(file);
				writer.write("t\tRo\tsumaRo");
			} catch (IOException e) {
				System.out.println("Failed creating a file");
			}
			
			last=false;
			firstStep=false;
		} 
		else{
			//*********Aka dislocation cannon*********//
			width = a.getWidth(); height = a.getHeight(); Random rand = new Random();
			ro = calculateGlobalDislocationDensity(time); 
			
			deltaRo = ro-prevRo;
			prevRo=ro;
			roOfCell = deltaRo/(height*width);
			sigma=(1.9*0.000000000257*80000000000.0*Math.sqrt(ro));
			//prev = a.getCellularCopy();
			cellsOnTheEdge = new ArrayList<Cell>();
			for(int i=0;i<width;i++){
				for(int j=0;j<height;j++){
					Cell cell = a.getCellAt(i, j);
					if (cell.isEdge()){
						cell.addDislocationDensity(roOfCell*0.8);
						deltaRo-=roOfCell*0.8;
						cellsOnTheEdge.add(cell);
					}
					else {
						cell.addDislocationDensity(roOfCell*0.2);
						deltaRo-=roOfCell*0.2;
					}
				}	
			}
			deltaRo=deltaRo/(width*height);
			int randomCellsOnEdgeAmount = rand.nextInt(cellsOnTheEdge.size());
			double remainingRoOfEdgeCell = (sigma*deltaRo)/(randomCellsOnEdgeAmount);	
			int pom=0; int tmp=0;
			for (int i=0;i<randomCellsOnEdgeAmount;i++){
				tmp =rand.nextInt(cellsOnTheEdge.size());
				Cell cell = cellsOnTheEdge.get(tmp);
				cellsOnTheEdge.remove(tmp);
				//pom = rand.nextInt(2);
				//if (pom==1){
					cell.addDislocationDensity(remainingRoOfEdgeCell);
				//}
			}
			// Ro to file
			try {
				writer.write("\n"+time+"\t"+prevRo+"\t"+(prevRo+remainingRoOfEdgeCell*randomCellsOnEdgeAmount));
				writer.flush();
			} catch (IOException e) {
			}
			
			//******RecrystalizationBelow*******
			recrystalizedCells = new ArrayList<Cell>();
			
			for(int i=0;i<width;i++){
				for(int j=0;j<height;j++){
					Cell cell = a.getCellAt(i, j);
					if (cell.getRoOfCell()>criticalDislocationDensity && !cell.isRecrystalized()){
						
						cell.recrystalize();
					}
					if (cell.isRecrystalized()){
						recrystalizedCells.add(cell);
					}
				}	
			}
			//*********GrowthOfRecrystalizedGrainsBelow*********
			prev = a.getCellularCopy();
			//last=false;
			//int ii = 0; int jj = 0; int iii = 0; int jjj = 0;
			for (int i = 0; i < height; i++)
				for (int j = 0; j < width;j++) //pêtla po komórkach
				{
					if(prev[i][j].isRecrystalized()) // If current cell has grown skip iteration
						continue; 
					Cell tmp1 = a.getCellAt(i, j);
					Grain grain = ngbh.determineGrain(prev, i, j);
					if(grain != null){
						tmp1.setGrain(grain);
						tmp1.setRecrystalized(true);
					}
					last=false;
					
					
				}

			

		
		}
		time = time+deltaT;
		return last;

			
		
		
		
	}

	private double calculateGlobalDislocationDensity(double t) {
		return A/B + (1 - A/B)  * Math.exp(-B*t);
		
	}

	@Override
	public void setNeighbourhood(Neighbourhood n) {
		this.ngbh=n;

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
