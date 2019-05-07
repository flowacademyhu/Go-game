package View;

import Service.Board;
import Service.Chain.Chain;
import Service.Chain.Stone;
import Service.Player.Player;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameArea extends JFrame implements MouseListener {
   // private JButton[][] intersections;
    private JIntersection[][] intersections;
    private int dimension = 9;
    private Board board;
    private List<Chain> chainsToDestroy;


    public GameArea() throws HeadlessException {
        this.board= Board.getBoard(dimension);


        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GridLayout gridLayout= new GridLayout(dimension,dimension);
        setLayout(gridLayout);
        intersections= new JIntersection[dimension][dimension];
        setTitle("GO");

        for (int i = 0 ; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                intersections[i][j]= new JIntersection(i,j);
                intersections[i][j].setOpaque(true);
                intersections[i][j].addMouseListener(this);

                intersections[i][j].setBackground(Color.BLUE);
                add(intersections[i][j]);
            }
        }

        setVisible(true);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        JIntersection jIntersection=((JIntersection)e.getSource());
        int x =jIntersection.getxPosition();
        int y = jIntersection.getyPosition();
        Player player= Player.getPlayerInstance();
        chainsToDestroy =  board.addStone(x,y, player);

        if (player.getColor().toString().equalsIgnoreCase("BLACK")) {
            jIntersection.setBackground(Color.BLACK);
        } else if(player.getColor().toString().equalsIgnoreCase("WHITE")) {
            jIntersection.setBackground(Color.WHITE);
        }
        destroyChainsFromBoard(chainsToDestroy);


    }

    public void destroyChainsFromBoard(List<Chain> chainsToDestroy) {
        if (chainsToDestroy.size() == 0) {
            return;
        } else {
            for (var chain: chainsToDestroy) {
                for (var stone: chain.getStones()) {
                    intersections[stone.getX()][stone.getY()].setBackground(Color.BLUE);
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
