package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class genTypeBoxListener implements ActionListener{
	private MainWindow w;
	public genTypeBoxListener(MainWindow w){
		this.w=w;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String choice = (String)w.genTypeBox.getSelectedItem();
		switch (choice){
		case "random":
	    	w.grainLabel.setText("<html>Number of randomly generated grains</html>");
	    	break;
		case "evenlySpread":
	    	w.grainLabel.setText("<html>Number of grains to spread</html>");
			break;
		case "randomRadius":
	    	w.grainLabel.setText("<html>set Radius Of Grains/html>");
			break;
		case "onClick":	
			// To-Do Set Clickable Grains
			
	    	w.cellGrid.repaint();
			
			break;
	    default:
		}
	}
}
