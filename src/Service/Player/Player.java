package Service.Player;

import Service.Board;

import java.util.UUID;

public class Player {
    private static int instance;
    private String id;
    private side color;
    public enum side {
        BLACK,
        WHITE

    }

    public static Player getPlayer() {
        if(instance == 0) {
            instance++;
            return new Player(side.BLACK);
        } else {
            return new Player(side.WHITE);
        }
    }

    Player(side color) {
        id= UUID.randomUUID().toString().replace("-", "");
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public side getColor() {
        return color;
    }

    public void setColor(side color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Player(id,color) " +
                id + ", " +
                color
                ;
    }
}
