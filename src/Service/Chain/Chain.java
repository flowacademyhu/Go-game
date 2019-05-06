package Service.Chain;

import Service.Board;
import Service.Player;

import java.util.HashSet;
import java.util.Set;

public class Chain {
    private Set<Stone> stones;
    private int liberty;
    private Player player;

    Chain(Stone stone) {
        this.stones=new HashSet<>();
        this.liberty=stone.getNeighbours().size();
        stones.add(stone);
        this.player=stone.getPlayer();
    }

    public Set<Stone> getStones() {
        return stones;
    }

    public void setStones(Set<Stone> stones) {
        this.stones = stones;
    }

    public int getLiberty() {
        return liberty;
    }

    public void setLiberty(int liberty) {
        this.liberty = liberty;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
