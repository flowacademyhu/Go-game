package Service.Chain;

import Service.Player.Player;

import java.util.HashSet;
import java.util.Set;

public class Chain {

    private Set<Stone> stones;
    private int liberty;
    private Player player;

    Chain() {

    }

    Chain(Stone stone) {
        this.stones=new HashSet<>();
        this.liberty=stone.getPossibleLiberty()-stone.getNeighbours().size();
        stones.add(stone);
        this.player=stone.getPlayer();
    }

    public int countLiberty() {
        return stones.stream().map(stone -> stone.getPossibleLiberty()).reduce(0,Integer::sum)- stones.stream()
                .map(stone -> stone.getNeighbours().size()).reduce(0,Integer::sum);
    }

    public void addStones(Set<Stone> stones) {
        this.stones.addAll(stones);
    }

    public Set<Stone> getStones() {
        return stones;
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
