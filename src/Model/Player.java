package Model;

public class Player implements PlayerInterface {
	
	private PlayerType playerType;
	private Symbol symbol;
	private String username;
	
	public Player(PlayerType playerType, Symbol symbol, String username) {
		this.playerType = playerType;
		this.symbol = symbol;
		this.username = username;
	}
	
	public PlayerType getPlayerType() {
		return playerType;
	}
	
	public Symbol getSymbol() {
		return symbol;
	}
	
	public String getUsername() {
		return username;
	}
}
