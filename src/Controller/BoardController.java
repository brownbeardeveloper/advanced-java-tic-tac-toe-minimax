package Controller;

import java.awt.EventQueue;
import Model.BoardModel;
import Model.Computer;
import Model.Human;
import Model.MiniMax;
import Model.Player;
import Model.PlayerType;
import Model.Symbol;
import View.SwingView;

public class BoardController {
	
	private BoardModel model;
	private SwingView view;
	private MiniMax miniMax;
	private Player p1; 
	private Player p2;
	private boolean runGame;
	
	public BoardController(BoardModel model, SwingView view) {
		this.model = model;
		this.view = view;
	}
	
	/**
	 * <strong>Main method of the controller class.</strong><br>
	 * - Initialises the players for the Tic Tac Toe game.<br>
	 * - Makes the JFrame window visible to the user.<br>
	 * - Sets up the game interface elements for user interaction.<br>
	 * - Instantiates and configures the MiniMax class for game AI.
	 */
	public void runTicTacToeGame () {
		initializePlayers();
		makeJFrameVisible();
		miniMax = new MiniMax(p1, p2);
		startGameInterface();
		runGame = true;
	}
	
	/**
	 * <strong>Set up display in view</strong><br>
	 * - set prompt to this user<br>
	 * - give suggestion for the best move<br>
	 */
	private void startGameInterface() {
		view.printMessage(p1.getUsername() + ", make your move!");
		view.printAdvice(suggestBestMoveToHuman());
	}
	
	/**
	 * <strong>Handles input of Player 1 for the Tic Tac Toe game</strong>
	 * - Waits for input to make a move.<br>
	 * - Checks if the input is a valid move.<br>
	 * - Places the player's mark on the board.<br>
	 * - Checks if the player has won the game.<br>
	 * - Checks if there are still empty cells on the board.<br>
	 * - Calls AI to make its move if the game continues.
	 *
	 * @param row - index of the selected cell.
	 * @param col - index of the selected cell.
	 */
	public void getUserInput(int row, int col) {
		
		if (runGame) {
			if (model.isValidMove(row,col)) {			
				
	            char mark = p1.getSymbol().toString().charAt(0);
				model.placeMark(mark, row, col);
				
				view.printBoard(model.getBoard());
				
				if (model.checkWinner(mark) ) { // if this player win
					view.printMessage("Congratulations, " + p1.getUsername() + "! You won!");
					runGame = false;
					
				} else if (!model.hasEmptyCells()){ // if there's no empty cells 	
					view.printMessage("Draw!");	
					runGame = false;
					
				} else { // else still run the game	
					getAIMove();
				}			
			} else {
	            view.printMessage("Error: Invalid move! Please choose an empty cell.");
			}
		} else {
			
			System.out.println("Please let the developer know you want a restart button and a score counter");
		}
	}
			
	
	/**
	 * <strong>Handles moves for Player 2 in the Tic Tac Toe game</strong><br>
	 * Player 2 is computer-based and will make its moves automatically
 	 * - <br>
	 * - <br>
	 */
	private void getAIMove() {
		
		if(runGame) {
			char mark = p2.getSymbol().toString().charAt(0);
			boolean isValidMove = false;
			
			while (!isValidMove) {
																			
				int[] move = miniMax.findBestMove(model.getBoard(), false);		
	            										
				if (model.isValidMove(move[0], move[1])) {
					
					model.placeMark(mark, move[0], move[1]);
					isValidMove = true;
					view.printBoard(model.getBoard());
										
				} else {
					
	                view.printMessage("Warning! This AI is trying take over your computer!");
				}
			}
			
			if (model.checkWinner(mark)) {
				
				view.printMessage("You lose!");
				runGame = false;
				
			} else if(!model.hasEmptyCells()){
				
				view.printMessage("Draw!");
				runGame = false;
				
			} else {
				
				view.printAdvice(suggestBestMoveToHuman());
			}	
		}
	}
	
	/**
	 * <strong>Suggest the best move to this user</strong><br>
	 * - retrieves the best move from the MiniMax class<br>
	 * @return a string with the suggested move
	 */
	private String suggestBestMoveToHuman() {
		int[] hintMove = miniMax.findBestMove(model.getBoard(), true);
		return "hint - row: " + hintMove[0] + " col: " + hintMove[1];
	}

	/**
	 * <strong>Create players for the Tic Tac Toe game</strong><br>
	 * - p1 = player 1 (HUMAN)<br>
	 * - p2 = player 2 (COMPUTER)
	 */
	private void initializePlayers() {
		p1 = new Human(PlayerType.HUMAN, Symbol.X, "Player 1");
		p2 = new Computer(PlayerType.COMPUTER, Symbol.O, "Player 2");
	}
	
	/**
	 * <strong>Opens and displays this Java window</strong><br>
	 * - make the JFrame window visible to the user
	 */
	private void makeJFrameVisible(){
	    EventQueue.invokeLater(new Runnable() {
	    	
	        public void run() {
	            try {
	                view.setVisible(true);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    });	
	}
}
