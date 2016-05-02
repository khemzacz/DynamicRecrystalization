package GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GenerateGrainsBtnListener extends MouseAdapter{
    private MainWindow w;
    public GenerateGrainsBtnListener(MainWindow w){
    	this.w=w;
    }
	
	@Override
    public void mousePressed(MouseEvent e) {
		String choice = (String)w.genTypeBox.getSelectedItem();
		switch (choice){
		case "random":
	    	w.cellGrid.clearTheArea();
	    	w.cellGrid.generateRandomGrains(w.numberOfGrainsScrollBar.getValue());
	    	w.cellGrid.repaint();
	    	break;
		case "evenlySpread":
	    	w.cellGrid.clearTheArea();
	    	w.cellGrid.generateEvenlySpreadGrains(w.numberOfGrainsScrollBar.getValue());
	    	w.cellGrid.repaint();
			break;
		case "randomRadius":
	    	w.cellGrid.clearTheArea();
	    	w.cellGrid.generateRandomGrainsWithRadius();
	    	w.cellGrid.repaint();
			
			break;
		case "onClick":	
			// To-Do Set Clickable Grains
			
	    	w.cellGrid.repaint();
			
			break;
	    default:
		}

    }
}
