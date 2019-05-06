package Service;

import java.util.UUID;

public class Player {
    private String id;
    public enum side {
        BLACK,
        WHITE

    }
    private side color;

    public Player(side color) {
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
