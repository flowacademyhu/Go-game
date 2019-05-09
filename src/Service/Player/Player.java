package Service.Player;

import java.util.UUID;

public class Player {
    private final static int ENEMY_TIMER = 300;
    private static final Player instances[] = new Player[2];
    private static int index;
    private int enemyTimer;

    static {
        instances[0] = new Player(side.BLACK, ENEMY_TIMER);
        instances[1] = new Player(side.WHITE, ENEMY_TIMER);
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

    private Player(side color, int enemyTimer) {
        id= UUID.randomUUID().toString().replace("-", "");
        this.color = color;
        this.enemyTimer= enemyTimer;
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

    public  int getTimer() {
        return enemyTimer;
    }

    public void setEnemyTimer(int time) {
        this.enemyTimer = time;
    }
}
