package Model;

public interface BoardOperations {
		
	public char[][] getBoard();
	
	public char[][] copyBoard();

	public boolean isValidMove(int row, int col);

	public void placeMark(char mark, int row, int col);	
	
	public boolean checkWinner(char playerMark);

	public boolean hasEmptyCells();
}

