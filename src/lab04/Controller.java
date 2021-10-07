package lab04;

public class Controller {
    Controller() {
        ruleEngine = new RuleEngine(4,3,5,5, this);
        view = new View(this, true);

        view.updateBoardText("Player 1's turn!");
    }

    private View view;
    private RuleEngine ruleEngine;

    public void updateBoardText(String message) {
        view.updateBoardText(message);
    }

    public void updateBoardButtons(int column, int row, char symbol) {
        view.updateBoardButton(column, row, symbol);
    }

    public void gameIsOver() {
        view.gameIsOver();
    }

    public int getAmountOfPlayers() {
        return ruleEngine.players.length;
    }

    public char getBoardCell(int column, int row) {
        return ruleEngine.board.getCell(column, row);
    }

    public int getBoardColumnLength() {
        return ruleEngine.board.getColumnsLength();
    }

    public int getBoardRowLength() {
        return ruleEngine.board.getRowsLength();
    }

    public void makeMove(int column, int row) {
        ruleEngine.makeMove(column, row);
    }

    public boolean checkWinner() {
        return ruleEngine.checkPlayerHasWon();
    }
}
