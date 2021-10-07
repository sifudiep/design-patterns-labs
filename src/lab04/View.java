package lab04;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;

public class View extends JFrame {
    View(Controller _controller, boolean _oneGUIForEachPlayer) {
        controller = _controller;
        columns = controller.getBoardColumnLength();
        rows = controller.getBoardRowLength();
        oneGUIForEachPlayer = _oneGUIForEachPlayer;
        if (oneGUIForEachPlayer) {
            messageLabels = new JLabel[controller.getAmountOfPlayers()];
            buttons = new JButton[controller.getAmountOfPlayers()][columns][rows];
        }
        else {
            messageLabels = new JLabel[1];
            buttons = new JButton[1][columns][rows];
        }

        initGUI();
    }

    private Controller controller;
    private int rows;
    private int columns;
    private boolean gameOver = false;
    private JLabel messageLabels[];
    private JButton buttons[][][];
    private boolean oneGUIForEachPlayer;

    public void gameIsOver() {
        gameOver = true;
    }

    public void updateBoardText(String message) {
        for (int i = 0; i < messageLabels.length; i++) {
            messageLabels[i].setText(message);
        }
    }

    public void updateBoardTextForOnePlayer(String message, int playerIndex) {
        messageLabels[playerIndex].setText(message);
    }
    
    public void updateBoardButton(int column, int row, char symbol) {
        for (int i = 0; i < (oneGUIForEachPlayer ? controller.getAmountOfPlayers() : 1); i++) {
            buttons[i][column][row].setText(String.valueOf(symbol));
        }
    }

    private void initGUI() {
        for (int i = 0; i < (oneGUIForEachPlayer ? controller.getAmountOfPlayers() : 1); i++) {
            JFrame frame = new JFrame(controller.getPlayerName(i));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel myButtonPanel = new JPanel();

            final String emptyCellText = String.valueOf(controller.getBoardCell(0,0));

            myButtonPanel.setLayout(new GridLayout(rows, columns));

            JPanel myTextPanel = new JPanel();
            myTextPanel.setLayout(new GridLayout(1,1));
            myTextPanel.setPreferredSize(new Dimension(150,50));
            messageLabels[i] = new JLabel("", SwingConstants.CENTER);
            myTextPanel.add(messageLabels[i]);

            JPanel myMainPanel = new JPanel();
            myMainPanel.setLayout(new BoxLayout(myMainPanel, BoxLayout.Y_AXIS));
            myMainPanel.add(myButtonPanel);
            myMainPanel.add(myTextPanel);

            frame.getContentPane().add(myMainPanel);

            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < columns; c++) {
                    final int _r = r;
                    final int _c = c;
                    final int playerIndex = i;
                    JButton button = buttons[i][c][r] = new JButton(" ");
                    button.setPreferredSize(new Dimension(50, 50));
                    button.setText(emptyCellText);
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (gameOver) return;
                            controller.makeMove(_c, _r, playerIndex, oneGUIForEachPlayer);
                        }
                    });
                    myButtonPanel.add(button);
                }
            }
            System.out.println("i: " + i);
            frame.pack();
            frame.setVisible(true);
        }
    }
}
