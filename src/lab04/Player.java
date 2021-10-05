package lab04;

public class Player {
	Player(String _name, char _symbol) {
		name = _name;
		symbol = _symbol;
	}
	
	private String name;
	private char symbol;
	
	public String getName() {
		return name;
	}
	
	public char getSymbol() {
		return symbol;
	}
	
}
