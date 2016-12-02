import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;

/**
 * Create a JFrame to hold the game board.
 *
 * Created by durner01 on 12/2/2016.
 */

public class Board {
    private JButton spaces[];

    Board(String title, int width, int height) {
        JFrame jf = new JFrame(title);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(width, height));
        jp.setBorder(BorderFactory.createLineBorder(Color.blue, 3));
        spaces = new JButton[width * height];
        for (int i = 0; i < width * height; i++) {
            spaces[i] = new JButton();
            jp.add(spaces[i]);
        }
        jf.add(jp);

        jf.setSize(width * 100, height * 100);
        jf.setVisible(true);
    }
}
