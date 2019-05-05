package Service;

import java.util.HashSet;
import java.util.Set;

public class Chain {
    private Set<Stone> chain;
    private int liberty;
    private boolean isCaptured;
    private Player player;

    public Chain(Stone stone) {
        this.chain=new HashSet<>();
        //TODO getneightbours() for stone
        this.liberty=4;
        chain.add(stone);
        isCaptured=false;
        this.player=stone.getPlayer();
    }

    public Set<Stone> getChain() {
        return chain;
    }

    public void setChain(Set<Stone> chain) {
        this.chain = chain;
    }

    public int getLiberty() {
        return liberty;
    }

    public void setLiberty(int liberty) {
        this.liberty = liberty;
    }

    public boolean isCaptured() {
        return isCaptured;
    }

    public void setCaptured(boolean captured) {
        isCaptured = captured;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
