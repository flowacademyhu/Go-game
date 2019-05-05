package Service;


public class Board {
    private final int dimension;
    private Stone[][] intersections;

    public Board(int dimension) {
        this.dimension = dimension;
        this.intersections= new Stone[dimension][dimension];
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
    public String addStoneToBoard(Stone stone,int x,int y) {
        if (x < dimension && y < dimension && x >=0 && y >= 0) {
            if (intersections[x][y] == null) {
                intersections[x][y]=stone;
                return "stone has successfully added to board";
            }
            else return "there is alredy a stone";
        }
        return "outOfBoundException";
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
