package View;

import javax.swing.*;
import java.awt.*;

public class GameArea extends JFrame {
    JButton[][] intersections;
    int dimension = 9;


    public GameArea() throws HeadlessException {
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GridLayout gridLayout= new GridLayout(dimension,dimension);
        setLayout(gridLayout);
        intersections= new JButton[dimension][dimension];
        setTitle("GO");

        for (int i = 0 ; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                intersections[i][j]= new JButton(" ");
                intersections[i][j].setOpaque(true);
                add(intersections[i][j]);

                intersections[i][j].setBackground(Color.WHITE);
                add(intersections[i][j]);
            }
        }

        setVisible(true);
    }


}
