package lab04;

public class Controller {
    Controller() {
        ruleEngine = new RuleEngine(2,3,5,5);
        view = new View(this);
    }

    private View view;
    private RuleEngine ruleEngine;

    public String getNameOfCurrentPlayer() {
        return ruleEngine.players[ruleEngine.getCurrentPlayerIndex()].getName();
    }

    public String getSymbolOfCurrentPlayer() {
        return String.valueOf(ruleEngine.players[ruleEngine.getCurrentPlayerIndex()].getSymbol());
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

    public boolean makeMove(int column, int row) {
        return ruleEngine.makeMove(column, row);
    }

    public boolean checkWinner() {
        return ruleEngine.checkPlayerHasWon();
    }
}
