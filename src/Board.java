import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Create a JFrame to hold the game board.
 *
 * Created by durner01 on 12/2/2016.
 */

public class Board {

    private static JButton spaces[];
    private static int turn = 0;

    Board(String title, int width, int height) {
        JFrame jf = new JFrame(title);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(width, height));
        spaces = new JButton[width * height];
        for (int i = 0; i < width * height; i++) {
            spaces[i] = new TTTButton();
            jp.add(spaces[i]);
        }
        jf.add(jp);

        jf.setSize(width * 100, height * 100);
        jf.setVisible(true);
    }

    private static class TTTButton extends JButton implements ActionListener {

        public TTTButton() {
            super();
            setForeground(Color.BLACK);
            setOpaque(true);
            setFont(new Font("Helvetica", Font.PLAIN, 60));
            setText(" ");
            addActionListener(this);
        }

        // Notice button clicks to record moves.
        public void actionPerformed(ActionEvent e) {
            if (turn % 2 == 0 && getText().equals(" ")) {
                setText("X");
            } else if (turn % 2 == 1 && getText().equals(" ")) {
                setText("O");
            }
            if (checkForWin()) {
                playAgain();
            } else {
                turn++;
                if (turn >= spaces.length) {
                    //  Game over, played to draw.
                    playAgain();
                }
            }
        }
    }

    private static int[][] wins = new int[][]{
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // horizontal wins
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // vertical wins
            {0, 4, 8}, {2, 4, 6}             // diagonal wins
    };

    private static boolean checkForWin() {

        boolean haveWin = false;

        for (int i = 0; i < wins.length; i++) {
            if (!spaces[wins[i][0]].getText().equals(" ")
                    && spaces[wins[i][0]].getText().equals(spaces[wins[i][1]].getText())
                    && spaces[wins[i][1]].getText().equals(spaces[wins[i][2]].getText())
                    ) {
                haveWin = true;
                showWin(i);
            }
        }
        return haveWin;
    }

    private static void showWin(int win) {
        System.out.println("Winner: pattern " + win);
        for (int i : wins[win]) {
            spaces[i].setForeground(Color.RED);
        }
    }

    private static void playAgain() {
        int newGame = JOptionPane.showConfirmDialog(null, "Play again?", "Game Over", JOptionPane.YES_NO_OPTION);
        if (newGame == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            System.exit(0);
        }
    }

    public static void resetGame() {
        for (JButton s : spaces) {
            s.setText(" ");
            s.setForeground(Color.BLACK);
        }
        turn = 0;
    }
}