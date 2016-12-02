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

    private JButton spaces[];
    private static int turn = 0;

    Board(String title, int width, int height) {
        JFrame jf = new JFrame(title);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(width, height));
        jp.setBorder(BorderFactory.createLineBorder(Color.blue, 3));
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
        boolean win = false;    // Not yet.

        public TTTButton() {
            super();
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
            turn++;
        }
    }
}
