package lab04;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;

public class View extends JFrame {
    View(Controller _controller) {
        controller = _controller;
        initGUI(true);
    }

    private Controller controller;
    private boolean gameOver;

    private void initGUI(boolean oneForEachPlayer) {
        gameOver = false;
        for (int i = 0; i < (oneForEachPlayer ? controller.getAmountOfPlayers() : 1); i++) {
            JFrame frame = new JFrame("ImprovedTicTacToeVersion-" + i);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel myButtonPanel = new JPanel();

            final String emptyCellText = String.valueOf(controller.getBoardCell(0,0));

            int rows = controller.getBoardRowLength();
            int cols = controller.getBoardColumnLength();

            myButtonPanel.setLayout(new GridLayout(rows,cols));

            JPanel myTextPanel = new JPanel();
            myTextPanel.setLayout(new GridLayout(1,1));
            myTextPanel.setPreferredSize(new Dimension(150,50));
            JLabel myLabel = new JLabel(controller.getNameOfCurrentPlayer() + "'s turn", SwingConstants.CENTER);
            myTextPanel.add(myLabel);

            JPanel myMainPanel = new JPanel();
            myMainPanel.setLayout(new BoxLayout(myMainPanel, BoxLayout.Y_AXIS));
            myMainPanel.add(myButtonPanel);
            myMainPanel.add(myTextPanel);

            frame.getContentPane().add(myMainPanel);

            JButton buttons[][] = new JButton[cols][rows];
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    final int _r = r;
                    final int _c = c;
                    JButton button = buttons[c][r] = new JButton(" ");
                    button.setPreferredSize(new Dimension(50, 50));
                    button.setText(emptyCellText);
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (oneForEachPlayer) {
                                boolean wasOutOfSync = false;
                                for (int row = 0; row < controller.getBoardRowLength(); row++) {
                                    for (int col = 0; col < controller.getBoardColumnLength(); col++) {
                                        if ((int)controller.getBoardCell(col,row) == 0) {
                                            continue;
                                        }

                                        if (controller.getBoardCell(col, row) != buttons[col][row].getText().charAt(0)) {
                                            buttons[col][row].setText(String.valueOf(controller.getBoardCell(col, row)));
                                            myLabel.setText(controller.getNameOfCurrentPlayer() + "'s turn");
                                            wasOutOfSync = true;
                                        }
                                    }
                                }

                                if (wasOutOfSync) return;
                            }

                            if (gameOver) return;
                            String formerPlayerSymbol = controller.getSymbolOfCurrentPlayer();
                            String formerPlayerName = controller.getNameOfCurrentPlayer();
                            if (controller.makeMove(_c, _r)) {
                                buttons[_c][_r].setText(formerPlayerSymbol);
                                if (controller.checkWinner()) {
                                    myLabel.setText(formerPlayerName + " has won!");
                                    gameOver = true;
                                } else {
                                    myLabel.setText(controller.getNameOfCurrentPlayer() + "'s turn");
                                }
                            }
                        }
                    });
                    myButtonPanel.add(button);
                }
            }

            frame.pack();
            frame.setVisible(true);
        }
    }
}
