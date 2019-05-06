package Service.Player;

import java.util.UUID;

public class Player {
    private static final Player instances[] = new Player[2];
    private static int index;

    static {
        instances[0] = new Player(side.BLACK);
        instances[1] = new Player(side.WHITE);
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

    private Player(side color) {
        id= UUID.randomUUID().toString().replace("-", "");
        this.color = color;
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
}
