package Service;


import Service.Chain.Chain;
import Service.Chain.Stone;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Board {
    private static Board instance;
    private final int dimension;
    private Stone[][] intersections;
    private Set<Chain> chains;

    private Board(int dimension) {
        this.dimension = dimension;
        this.intersections= new Stone[dimension][dimension];
       this.chains= new HashSet<>();
    }

    public static Board getBoard(int dimension) {
        if(instance == null) {
            return new Board(dimension);
        } else {
            return instance;
        }
    }

    public Stone getStone(int x, int y) {
        return intersections[x][y];
    }

    public void printBoard() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j< dimension; j++) {
                if (intersections[i][j] == null) {
                    System.out.print("0 ");
                } else {
                    System.out.print("1 ");
                }
            }
            System.out.println("");
        }
    }


    public int getDimension() {
        return dimension;
    }

    public Stone[][] getIntersections() {
        return intersections;
    }

    public void setIntersections(Stone[][] intersections) {
        this.intersections = intersections;
    }

}
