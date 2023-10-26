package Model;

public class BoardModel implements BoardOperations {
	
	private char[][] board = new char[3][3];

	public BoardModel() {
		initializeBoard();
	}
	
	private void initializeBoard() {
		// Initialise an new board
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				
				this.board[row][col] = ' ';
				
				System.out.println(board[row][col]);
			}
		}
	}
	
	/**
	 * Method should check if the location row/col on the board
	 * is a valid move. Can x or o be placed on a cell or 
	 * is it already taken
	 * @param x
	 * @param o
	 * @return
	 */
	public boolean isValidMove(int row, int col) {
		return board[row][col] == ' '
;	}
	
	/**
	 * Used after checking if move is valid. Places x or o on the board.
	 * Maybe should be modified to return a copy of the board which 
	 * includes the additional move.
	 * @param symbol
	 * @param row
	 * @param col
	 * @return
	 */
	public void placeMark(char mark, int row, int col) {
		board[row][col] = mark;
	}
	
	/**
	 * Method checks if there is a winner on the board
	 * 3x or 3o.
	 * @param board
	 * @return boolean
	 */
	public boolean checkWinner(char playerMark) {
		
        // Check rows
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == playerMark && board[row][1] == playerMark && board[row][2] == playerMark) {
                return true;
            }
        }
        // Check columns
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == playerMark && board[1][col] == playerMark && board[2][col] == playerMark) {
                return true;
            }
        }
        // Check diagonals
        if ((board[0][0] == playerMark && board[1][1] == playerMark && board[2][2] == playerMark) ||
            (board[0][2] == playerMark && board[1][1] == playerMark && board[2][0] == playerMark)) {
            return true;
        }
        return false;
    }
		
	/**
	 * Board makes a copy of a current board. This is important
	 * because the board is going to be passed as reference and every
	 * next change will change the original board. Copies will have to 
	 * be made to preserve a specific board state in the algorithm
	 * @return char[row][column]
	 */
	public char[][] copyBoard() {
		char[][] copyBoard = board;
		return copyBoard;
	}
	
	/**
	 * Can be used to keep ongoing count of taken cells for
	 * quick reference. Every time a mark is placed number of
	 * empty cells go down from 9 to 0. When 0 it should be 
	 * a base case an a winner or draw should be checked
	 * @return
	 */
	public boolean hasEmptyCells() {
		
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				
				if (board[row][col] == ' ') return true;
			}
		}
		return false;
	}
	
	/**
	 * Send board values to the console.
	 */
	public char[][] getBoard() {
		return board;
	}
}
