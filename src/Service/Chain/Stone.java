package Service.Chain;

import Service.Board;
import Service.Player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Stone {

    private final int MAX_POSSIBLE_STONE_LIBERTY=4;

    private final int x;
    private final int y;
    private final Player player;
    private Board board;
    private Chain chain;

    Stone() {
        x=0;
        y=0;
        player=null;
    }
    Stone(int x, int y, Player player, Board board) {
        this.x = x;
        this.y = y;
        this.player=player;
        this.board= board;
    }

    public int getPossibleLiberty() {
        int liberty = MAX_POSSIBLE_STONE_LIBERTY;
        if (x == 0 || x == board.getDimension()-1) {
            liberty--;
        }
        if (y == 0 || y == board.getDimension()-1) {
            liberty--;
        }
        return liberty;
    }

    public List<Stone> getNeighbours() {
        List<Stone> neighbours= new ArrayList<>();
        leftNeighbour(neighbours);
        rightNeighbour(neighbours);
        topNeighbour(neighbours);
        botNeighbour(neighbours);
        return neighbours;
    }

    public List<Stone> getAliedNeighbours() {
        //TEST

        System.out.println("AliedNeighbours"+getNeighbours().stream().filter(stone -> stone.getPlayer() == player).collect(Collectors.toList()).size());
        return getNeighbours().stream().filter(stone -> stone.getPlayer() == player).collect(Collectors.toList());
    }

    public List<Stone> getEnemyNeighbours() {
        return getNeighbours().stream().filter(stone -> stone.getPlayer() != player).collect(Collectors.toList());
    }

    private List<Stone> leftNeighbour(List<Stone> neighbours) {
        if(x - 1 >= 0 && board.getStone(x-1,y) != null) {
        neighbours.add(board.getStone(x-1,y));
        }
    return neighbours;
    }

    private List<Stone> rightNeighbour(List<Stone> neighbours) {
        if(x +1 <board.getDimension() && board.getStone(x+1,y) != null) {
            neighbours.add(board.getStone(x+1,y));
        }
        return neighbours;
    }

    private List<Stone> topNeighbour(List<Stone> neighbours) {
        if(y -1 >= 0 && board.getStone(x,y-1) != null) {
            neighbours.add(board.getStone(x,y-1));
        }
        return neighbours;
    }

    private List<Stone> botNeighbour(List<Stone> neighbours) {
        if(y+1 < board.getDimension() && board.getStone(x,y+1) != null) {
            neighbours.add(board.getStone(x,y+1));
        }
        return neighbours;
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

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public String toString() {
        return "Stone(x,y, playerColor)" + x+ ", " + y +
                ", " + player.getColor();
    }

    public Chain getChain() {
        return chain;
    }

    public void setChain(Chain chain) {
        this.chain = chain;
    }
}
