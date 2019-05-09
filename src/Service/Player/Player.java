package Service.Player;

import java.util.UUID;

public class Player {
    private static final Player instances[] = new Player[2];
    private static int index;
    private static int timer;

    static {
        instances[0] = new Player(side.BLACK, 300);
        instances[1] = new Player(side.WHITE, 300);
    }

    private String id;
    private side color;
    public enum side {
        BLACK,
        WHITE

    }

    public static Player getPlayerInstance() {
        return instances[(index++)%2];
    }

    private Player () { }

    private Player(side color, int timer) {
        id= UUID.randomUUID().toString().replace("-", "");
        this.color = color;
        this.timer= timer;
    }

    public String getId() {
        return id;
    }


    public side getColor() {
        return color;
    }


    @Override
    public String toString() {
        return "Player(id,color) " +
                id + ", " +
                color
                ;
    }

    public static int getTimer() {
        return timer;
    }

    public static void setTimer(int time) {
        Player.timer = time;
    }
}
