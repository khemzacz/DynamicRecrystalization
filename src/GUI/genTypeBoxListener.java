package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class genTypeBoxListener implements ActionListener{
	private MainWindow w;
	private NaiveGrainGrowthOnClickListener nggl;
	public genTypeBoxListener(MainWindow w){
		super();
		this.w=w;
		nggl = new NaiveGrainGrowthOnClickListener(w);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String choice = (String)w.genTypeBox.getSelectedItem();
		switch (choice){
		case "random":
			if(w.cellGrid.getMouseListeners().length==1)
				w.cellGrid.removeMouseListener(nggl);
	    	w.grainLabel.setText("<html>Number of randomly generated grains</html>");
	    	break;
		case "evenlySpread":
			if(w.cellGrid.getMouseListeners().length==1)
				w.cellGrid.removeMouseListener(nggl);
	    	w.grainLabel.setText("<html>Number of grains to spread</html>");
			break;
		case "randomRadius":
			if(w.cellGrid.getMouseListeners().length==1)
				w.cellGrid.removeMouseListener(nggl);
	    	w.grainLabel.setText("<html>set Radius Of Grains</html>");
			break;
		case "onClick":	
			if(w.cellGrid.getMouseListeners().length==0)
				w.cellGrid.addMouseListener(nggl);
			w.grainLabel.setText("<html>Activate or deactivate cells on click</html>");
	    	w.cellGrid.repaint();
			
			break;
	    default:
		}
	}
}
