package Model;

/**
*
* 
*/

public class MiniMax {
	
	private static char player1;
	private static char player2;
	
	public MiniMax(Player p1, Player p2) {
		player1 = p1.getSymbol().toString().charAt(0);
		player2 = p2.getSymbol().toString().charAt(0);
	}

	private int miniMax(char[][] board, int depth, boolean isMaximizing) {
		
		int score = evaluate(board); 
		
		// if Maximizer has won the game
		if (score == 10) return score - depth; 

		// if Minimizer has won the game 
		if (score == -10) return score + depth; 

		// it's a tie if there're no more moves
		if (hasEmptyCells(board) == false) return 0; 
		
		// recursive case
		if (isMaximizing) {
			
			//initial low value
			int maxVal = -1000;
						
			for (int row = 0; row < 3; row++) { 
				for (int col = 0; col < 3; col++) {
					
					if (board[row][col]== ' ') {
						
						// make one move 
						board[row][col] = player1; 
						
						// invoke MiniMax recursively 
						int eval = miniMax(board, depth + 1, false); 
						
						// undo the move 
						board[row][col] = ' '; 
						
                        if (eval > maxVal) {
                            maxVal = eval;
                        }
					}
				}
			}
			return maxVal;
						
		} else {
			
			//initial high value
			int minVal = 1000;
			
			for (int row = 0; row < 3; row++) { 
				for (int col = 0; col < 3; col++) {
					
					if (board[row][col]== ' ') {
						
						// make one move 
						board[row][col] = player2; 

						// invoke MiniMax recursively 
						int eval = miniMax(board, depth + 1, true); 

						// undo the move 
						board[row][col] = ' '; 
						
                        if (eval < minVal) {
                        	minVal = eval;
                        }						
					}
				}
			}
		return minVal;
		
		}
	}
	
	private int evaluate(char[][] board) 
	{ 
		// checking for Rows for X or O victory
		for (int row = 0; row < 3; row++) 
		{ 
			if (board[row][0] == board[row][1] && 
				board[row][1] == board[row][2]) 
			{ 
				if (board[row][0] == player1) return 10; 
				else if (board[row][0] == player2) return -10; 
			} 
		} 

		// checking for Columns for X or O victory
		for (int col = 0; col < 3; col++) 
		{ 
			if (board[0][col] == board[1][col] && 
				board[1][col] == board[2][col]) 
			{ 
				if (board[0][col] == player1) return 10; 
				else if (board[0][col] == player2) return -10; 
			} 
		} 

		// checking for Diagonals for X or O victory
		if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) 
		{ 
			if (board[0][0] == player1) return 10; 
			else if (board[0][0] == player2) return -10; 
		} 
		
		// checking for Diagonals for X or O victory
		if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) 
		{ 
			if (board[0][2] == player1) return 10; 
			else if (board[0][2] == player2) return -10; 
		} 

		// else if none of them have won then return 0 
		return 0; 
	} 
	    
    public int[] findBestMove(char[][] board, boolean isMaximizing) {
        int bestScore = isMaximizing ? -100 : 100;        
        char currentPlayer = isMaximizing ? player1 : player2;
        int[] bestMove = new int[2];

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
            	
                if (board[row][col] == ' ') {
                	
                	// mark this move
                    board[row][col] = currentPlayer;
                    
                    // invoke MiniMax to get value of this move
                    int score = miniMax(board, 0, (!isMaximizing));
                    
                    // undo the move
                    board[row][col] = ' '; // Undo the move

                    // save the best move
                    if (isMaximizing && (score > bestScore) || (!isMaximizing && score < bestScore)){
                        bestScore = score;
                        bestMove[0] = row;
                        bestMove[1] = col;
                    }
                }
            }
        }
        
        return bestMove;
    }

	
	public static boolean hasEmptyCells(char[][] board) {
		
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				
				if (board[row][col] == ' ') return true;
			}
		}

		return false;
	}

}

