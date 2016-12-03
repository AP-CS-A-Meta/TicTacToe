import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
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
    //private static int[][][] wins;

    Board(String title, int width, int height) {
        JFrame jf = new JFrame(title);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(width, height));
        //jp.setBorder(BorderFactory.createLineBorder(Color.blue, 3));
        spaces = new JButton[width * height];
        for (int i = 0; i < width * height; i++) {
            spaces[i] = new TTTButton();
            jp.add(spaces[i]);
        }
        jf.add(jp);

        jf.setSize(width * 100, height * 100);
        jf.setVisible(true);

        //initWins(width, height);
    }

    private static class TTTButton extends JButton implements ActionListener {

        boolean win = false;    // Not yet.

        public TTTButton() {
            super();
            setBackground(Color.ORANGE);
            setOpaque(true);
            //setBorderPainted(false);
            //setBorder(null);
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
                // XXX - Do something sensible.
            } else {
                turn++;
            }
        }
    }

    private static boolean checkForWin() {

        int[][] wins = new int[][] {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // horizontal wins
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // vertical wins
                {0, 4, 8}, {2, 4, 6}			 // diagonal wins
        };
        boolean haveWin = false;

        for (int i = 0; i < wins.length; i++) {
            if ( !spaces[wins[i][0]].getText().equals(" ")
                    && spaces[wins[i][0]].getText().equals( spaces[wins[i][1]].getText() )
                    && spaces[wins[i][1]].getText().equals( spaces[wins[i][2]].getText() )
                    ) {
                haveWin = true;
                showWin(i);
            }
        }
        return haveWin;
    }

    private static void showWin(int win) {
        System.out.println("Winner: pattern " + win);
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
        }
        turn = 0;
    }

    /*private static void initWins(int cols, int rows) {
        *//*
         * Winning patterns are horizontal rows, vertical columns, and the two diagonals.
         *//*
        int patterns = cols + rows + 2;
        //wins = new int[patterns][cols][rows];
        // Columns
        int winIn = 0;
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = 0; k < rows; k++) {
                    if (j == winIn) {
                        wins[i][j][k] = 1;
                    } else {
                        wins[i][j][k] = 0;
                    }
                }
            }
            winIn++;
        }
        // Rows
        winIn = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                for (int k = 0; k < cols; k++) {
                    if (k == winIn) {
                        wins[i + cols][j][k] = 1;
                    } else {
                        wins[i + cols][j][k] = 0;
                    }
                }
            }
            winIn++;
        }
        // Diagonal - upper left to lower right. Assumes square board.
        int i = cols + rows;
        for (int j = 0; j < rows; j++) {
            for (int k = 0; k < cols; k++) {
                if (j == k) {
                    wins[i][j][k] = 1;
                } else {
                    wins[i][j][k] = 0;
                }
            }
        }
        // Diagonal - upper right to lower left. Assumes square board.
        i++;
        for (int j = 0; j < rows; j++) {
            for (int k = 0; k < cols; k++) {
                if (j + k == rows - 1) {
                    wins[i][j][k] = 1;
                } else {
                    wins[i][j][k] = 0;
                }
            }
        }
        System.out.println();
    }
    */
}
