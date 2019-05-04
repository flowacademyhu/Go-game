package Service;

public class Stone {
    private final int x;
    private final int y;
    private final Player player;


    public Stone(int x, int y, Player player) {
        this.x = x;
        this.y = y;
        this.player=player;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public String toString() {
        return "Stone(x,y, playerColor)" + x+ ", " + y +
                ", " + player.getColor();
    }
}
