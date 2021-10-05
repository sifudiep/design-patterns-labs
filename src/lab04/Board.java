package lab04;

public class Board {
	Board(int _columns, int _rows) {
		if (_columns > 5 || _rows > 5) throw new Error("Column and Row length of Board cannot exceed 5.");

		columnsLength = _columns;
		rowsLength = _rows;
		board = new char[_columns][_rows];
	}

	private int columnsLength;
	private int rowsLength;
	private char[][] board;

	public char getCell(int column, int row) {
		return board[column][row];
	}

	public int getColumnsLength() {
		return columnsLength;
	}

	public int getRowsLength() {
		return rowsLength;
	}

	public void setBoardCell(int column, int row, char symbol) {
		board[column][row] = symbol;
	}
}
