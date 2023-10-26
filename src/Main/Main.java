package Main;

import Controller.BoardController;
import Model.BoardModel;
import View.SwingView;

public class Main {

	public static void main(String[] args) {
		
		// initialise MVC
        BoardModel model = new BoardModel();
        SwingView view = new SwingView();
        BoardController controller = new BoardController(model, view);
        
        // create two-way communication
        view.setController(controller); 
        
        // start the game
        controller.runTicTacToeGame();  
	 }	
}
