package lab04;

public class Controller {
    Controller() {
        ruleEngine = new RuleEngine(2,3,5,5, this);
        view = new View(this, true);

        view.updateBoardTextForAllPlayers("Player 1's turn!");
    }

    private View view;
    private RuleEngine ruleEngine;

    public void updateBoardTextForAllPlayers(String message) {
        view.updateBoardTextForAllPlayers(message);
    }

    public void updateBoardButtonForAllPlayers(int column, int row, char symbol) {
        view.updateBoardButtonForAllPlayers(column, row, symbol);
    }

    public void updateBoardTextForOnePlayer(String message, int playerIndex) {
        view.updateBoardTextForOnePlayer(message, playerIndex);
    }

    public void gameIsOver() {
        view.gameIsOver();
    }

    public int getAmountOfPlayers() {
        return ruleEngine.players.length;
    }

    public String getPlayerName(int index) {
        return ruleEngine.getPlayerName(index);
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

    public void makeMove(int column, int row, int playerIndex, boolean multiplePlayers) {
        ruleEngine.makeMove(column, row, playerIndex, multiplePlayers);
    }

    public boolean checkWinner() {
        return ruleEngine.checkPlayerHasWon();
    }
}
