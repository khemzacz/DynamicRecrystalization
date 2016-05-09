package mainPackage;
import GUI.MainWindow;

public class AppCore {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Area field = new Area(173,173);
		
		MainWindow window = new MainWindow(field);
		
		
		
		field.injectTestingStructures();
		//field.printCells();
		field.step();
		//field.printCells();
		
		window.setVisible(true);
	}

}
