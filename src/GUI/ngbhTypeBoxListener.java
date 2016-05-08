package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Algorythms.Moore;
import Algorythms.VonNeumann;

public class ngbhTypeBoxListener implements ActionListener {
	private MainWindow w;

	public ngbhTypeBoxListener(MainWindow w){
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
			
			break;
			
		case "Hexagonal right":
			
			break;
			
		case "Hexagonal random":
			
			break;
			
		case "Pentaonal random":
			
			break;
		default:
			break;		
		
		}
	}
}
