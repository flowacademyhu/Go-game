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

    public Stone getStone() {
        return stones.stream().findFirst().orElse(null);
    }

    public Set<Chain> neighbourAlies() {
        Set<Chain> neighbours = new HashSet<>();
        for (var stone: stones) {
            for (var neighbour: stone.getAliedNeighbours()) {
                if (this != neighbour.getChain()) {
                    neighbours.add(neighbour.getChain());
                }
            }
        }
        return neighbours;
    }

    public void concatenateChains(Set<Chain> neighbourchains) {

            for (var chain: neighbourchains) {
                this.stones.addAll(chain.getStones());

                for (var stone : chain.getStones()) {
                    stone.setChain(this);
                }
            }
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
