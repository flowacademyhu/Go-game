package Service;

public class Stone {
    private final int x;
    private final int y;
    private color side;
    private enum color {
        BLACK,
        WHITE
    }

    public Stone(int x, int y, color side) {
        this.x = x;
        this.y = y;
        if(side.equals(color.BLACK)){
            this.side = side;
        } else if (side.equals(color.WHITE)) {
            this.side = side;
        }

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public color getSide() {
        return side;
    }

    @Override
    public String toString() {
        return "Stone(x,y, side)" + x+ ", " + y +
                ", " + side;
    }
}
