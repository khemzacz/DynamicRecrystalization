package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Algorythms.HexagonalLeft;
import Algorythms.HexagonalRandom;
import Algorythms.HexagonalRight;
import Algorythms.Moore;
import Algorythms.PentagonalRandom;
import Algorythms.VonNeumann;

public class NgbhTypeBoxListener implements ActionListener {
	private MainWindow w;

	public NgbhTypeBoxListener(MainWindow w){
		this.w=w;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String xd = (String)w.ngbhTypeBox.getSelectedItem();
		switch (xd){
		case "Moore":
			w.cellGrid.a.setNeighbourhood(new Moore(w.cellGrid.a));
			break;
		case "von neumann":	
			w.cellGrid.a.setNeighbourhood(new VonNeumann(w.cellGrid.a));
			break;
			
		case "Hexagonal left":
			w.cellGrid.a.setNeighbourhood(new HexagonalLeft(w.cellGrid.a));
			break;
			
		case "Hexagonal right":
			w.cellGrid.a.setNeighbourhood(new HexagonalRight(w.cellGrid.a));
			break;
			
		case "Hexagonal random":
			w.cellGrid.a.setNeighbourhood(new HexagonalRandom(w.cellGrid.a));
			break;
			
		case "Pentagonal random":
			w.cellGrid.a.setNeighbourhood(new PentagonalRandom(w.cellGrid.a));		
			break;
		default:
			break;		
		
		}
	}
}
