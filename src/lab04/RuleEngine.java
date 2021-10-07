package lab04;

public class RuleEngine {
	RuleEngine(int _amountOfPlayers, int _numberInARowToWin, int _boardColumns, int _boardRows, Controller _controller) {
		if (_amountOfPlayers > maxPlayers) throw new Error("Players exceeded " + maxPlayers);

		controller = _controller;

		currentPlayerIndex = 0;

		players = new Player[_amountOfPlayers];
		for (int i = 0; i < _amountOfPlayers; i++) {
			// 49 = '1' in ASCII
			int charNumber = 49 + i;
			players[i] = new Player("Player " + (i + 1), (char) charNumber);
		} 
		numberInARowToWin = _numberInARowToWin;
		if (_boardColumns >= numberInARowToWin || _boardRows >= numberInARowToWin) {
			// Construct Board with _boardWidth as dimensions, new Board(BoardWidth);
			board = new Board(_boardColumns, _boardRows);
		} else {
			throw new Error("ERROR : boardColumns and boardRows is smaller than numberInARowToWin.");
		}
	}
	private Controller controller;
	private int maxPlayers = 5;
	public Player[] players;
	public Board board;
	private int currentPlayerIndex;
	private int numberInARowToWin;

	private void nextTurn() {
		if (currentPlayerIndex + 1 == players.length) {
			currentPlayerIndex = 0;
		} else {
			currentPlayerIndex += 1;
		}
	}

	public String getPlayerName(int index) {
		return players[index].getName();
	}

	public void makeMove(int column, int row, int playerIndex, boolean multiplePlayers) {
		if (checkMoveIsLegal(column, row)) {
			if (playerIndex != currentPlayerIndex && multiplePlayers) {
				controller.updateBoardTextForOnePlayer("Not your turn! " + players[currentPlayerIndex].getName() + "'s turn!", playerIndex);
				return;
			}

			board.setBoardCell(column, row, players[currentPlayerIndex].getSymbol());
			controller.updateBoardButtonForAllPlayers(column, row, players[currentPlayerIndex].getSymbol());

			if (checkPlayerHasWon()) {
				controller.updateBoardTextForAllPlayers(players[currentPlayerIndex].getName() + " has won the game!");
				controller.gameIsOver();
			} else {
				nextTurn();
				controller.updateBoardTextForAllPlayers(players[currentPlayerIndex].getName() + "'s turn");
			}
		} else {
			controller.updateBoardTextForAllPlayers("Move is illegal! " + players[currentPlayerIndex].getName() + "'s turn!");
		}
	}

	public boolean checkMoveIsLegal(int column, int row) {
		// Cannot check if char is null, convert to int and check default value 0 instead.
		if ((int)board.getCell(column, row) == 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkPlayerHasWon() {
		// Check Vertical
		for (int playerIndex = 0; playerIndex < players.length; playerIndex++) {
			for (int col = 0; col < board.getColumnsLength(); col++) {
				int inARow = 0;
				for (int row = 0; row < board.getRowsLength(); row++) {
					if (board.getCell(col, row) == players[playerIndex].getSymbol()) {
						inARow += 1;
						if (inARow == numberInARowToWin) return true;
					} else {
						inARow = 0;
					}
				}
			}
		}

		// Check Horizontal
		for (int playerIndex = 0; playerIndex < players.length; playerIndex++) {
			for (int row = 0; row < board.getRowsLength(); row++) {
				int inARow = 0;
				for (int col = 0; col < board.getColumnsLength(); col++) {
					if (board.getCell(col, row) == players[playerIndex].getSymbol()) {
						inARow += 1;
						if (inARow == numberInARowToWin) return true;
					} else {
						inARow = 0;
					}
				}
			}
		}

		// Check Diagonal
		for (int playerIndex = 0; playerIndex < players.length; playerIndex++) {
			for (int row = 0; row < board.getRowsLength(); row++) {
				for (int col = 0; col < board.getColumnsLength(); col++) {
					if (board.getCell(col, row) == players[playerIndex].getSymbol()) {
						if (checkDiagonal(0, col, row, players[playerIndex].getSymbol(), true) || checkDiagonal(0, col, row, players[playerIndex].getSymbol(), false)) {
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	public boolean checkDiagonal(int inARow, int column, int row, char symbol, boolean topLeftToBottomRight) {
		if (inARow == numberInARowToWin) return true;

		if (column < 0 || column >= board.getColumnsLength() || row < 0 || row >= board.getRowsLength()) return false;

		if (board.getCell(column, row) == symbol) {
			return checkDiagonal(inARow + 1, topLeftToBottomRight ? column + 1 : column -1, row - 1, symbol, topLeftToBottomRight);
		} else {
			return false;
		}
	}
}
