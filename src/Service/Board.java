package Service;

public class Board {
    private final int height;
    private final int width;
    private Intersection[][] intersections;

    public Board(int height, int width) {
        this.height = height;
        this.width = width;
        this.intersections= new Intersection[width][height];
    }


}
